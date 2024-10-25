#include "hashtable.h"
#include <stdlib.h>
#include <stdio.h>

/*
 * This creates a new hash table of the specified size and with
 * the given hash function and comparison function.
 */
// Sample Call: createHashTable(2255, &stringHash, &stringEquals)
HashTable *createHashTable(int size, unsigned int (*hashFunction)(void *),
                           int (*equalFunction)(void *, void *)) {
  int i = 0;
  HashTable *newTable = malloc(sizeof(HashTable));
  newTable->size = size;
  newTable->data = malloc(sizeof(HashBucket *) * size);
  for (i = 0; i < size; ++i) {
    newTable->data[i] = NULL;
  }
  newTable->hashFunction = hashFunction;
  newTable->equalFunction = equalFunction;
  return newTable;
}

HashBucket *findBucket(HashTable *table, void *key, int *indexOut) {
    // Take in a table and key, and return pointer to start of HashBucket at key
    HashFunction hashFunction = table->hashFunction;
    int index = hashFunction(key) % table->size;
    *indexOut = index;
    //printf("Found bucket\n");
    HashBucket *start = table->data[index];
    return start;
}

/*
 * This inserts a key/data pair into a hash table.  To use this
 * to store strings, simply cast the char * to a void * (e.g., to store
 * the string referred to by the declaration char *string, you would
 * call insertData(someHashTable, (void *) string, (void *) string).
 * Because we only need a set data structure for this spell checker,
 * we can use the string as both the key and data.
 */
void insertData(HashTable *table, void *key, void *data) {
  // 1. Find the right hash bucket location with table->hashFunction.
  int index;
  int *pIndex = &index;
  HashBucket *current = findBucket(table, key, pIndex);


  // 2. Allocate a new hash bucket struct.
  HashBucket *newBucket = malloc(sizeof(HashBucket));;
  newBucket->key = key;
  newBucket->data = data;
  newBucket->next = NULL;

  // 3. Append to the linked list or create it if it does not yet exist. 
  // If: No existing entries, place the new bucket directly
  if (current == NULL) {
      table->data[index] = newBucket;
  }
  // Else: Traverse the linked list to find the end
  else {
      while(current->next != NULL) {
          current = current->next;
      }
      current->next = newBucket;
  }
}

/*
 * This returns the corresponding data for a given key.
 * It returns NULL if the key is not found. 
 */
void *findData(HashTable *table, void *key) {
  // -- TODO --
  // HINT:
  // 1. Find the right hash bucket with table->hashFunction.
  int index;
  int *pIndex = &index;
  HashBucket *current = findBucket(table, key, pIndex);
  // 2. Walk the linked list and check for equality with table->equalFunction.
  equalFunction equalFunc = table->equalFunction;
  while (current != NULL) {
      if (equalFunc(key, current->key)) {
          return current->data;
      }
      current = current->next;
  }
  return NULL;
}
