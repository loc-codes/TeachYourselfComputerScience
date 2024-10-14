/*
 * Include the provided hash table library.
 */
#include "hashtable.h"

/*
 * Include the header file.
 */
#include "philspel.h"


/*
 * Include the utils module
 */
#include "utils.h"

/*
 * Standard IO and file routines.
 */
#include <stdio.h>

/*
 * General utility routines (including malloc()).
 */
#include <stdlib.h>

/*
 * Character utility routines.
 */
#include <ctype.h>

/*
 * String utility routines.
 */
#include <string.h>

/*
 * This hash table stores the dictionary.
 */
HashTable *dictionary;

/*
 * The MAIN routine.  You can safely print debugging information
 * to standard error (stderr) as shown and it will be ignored in 
 * the grading process.
 */
int main(int argc, char **argv) {
  if (argc != 2) {
    fprintf(stderr, "Specify a dictionary\n");
    return 0;
  }

  /*
   * Allocate a hash table to store the dictionary.
   */
  fprintf(stderr, "Creating hashtable\n");
  dictionary = createHashTable(2255, &stringHash, &stringEquals);

  fprintf(stderr, "Loading dictionary %s\n", argv[1]);
  readDictionary(argv[1]);
  fprintf(stderr, "Dictionary loaded\n");

  fprintf(stderr, "Processing stdin\n");
  processInput();

  /*
   * The MAIN function in C should always return 0 as a way of telling
   * whatever program invoked this that everything went OK.
   */
  return 0;
}

/*
 * This should hash a string to a bucket index.  Void *s can be safely cast
 * to a char * (null terminated string) and is already done for you here 
 * for convenience.
 */
unsigned int stringHash(void *s) {
  char *string = (char *)s;
  unsigned int hash = 5381;
  int c;
  while (*string) {
    c = *string;
    hash = hash * 33 + c;
    string += 1; // move by 1, as char is 1 byte
  }
  return hash;
}

/*
 * This should return a nonzero value if the two strings are identical 
 * (case sensitive comparison) and 0 otherwise.
 */
int stringEquals(void *s1, void *s2) {
  char *string1 = (char *)s1;
  char *string2 = (char *)s2;

  while (*string1 && *string2) {
      if (*string1 != *string2) {
          return 0;
      }
      string1 += 1; // move by 1, as char is 1 byte
      string2 += 1;
  }
  return *string1 == '\0' && *string2 == '\0';
}

/*
 * This function should read in every word from the dictionary and
 * store it in the hash table.  You should first open the file specified,
 * then read the words one at a time and insert them into the dictionary.
 * Once the file is read in completely, return.  You will need to allocate
 * (using malloc()) space for each word. 
 * 
 * You CANNOT assume that the specified file exists.  If the file does
 * NOT exist, you should print some message to standard error and call exit(1)
 * to cleanly exit the program. 
 * 
 * 
 * As described in the spec, you
 * can initially assume that no word is longer than 60 characters.  However,
 * for the final 20% of your grade, you cannot assumed that words have a bounded
 * length.  
 * 
 *
 * Since the format is one word at a time, with new lines in between,
 * you can safely use fscanf() to read in the strings until you want to handle
 * arbitrarily long dictionary chacaters.
 */
void readDictionary(char *dictName) {
  // -- TODO --f
  FILE *pDictionary = fopen(dictName, "r");
  if (pDictionary == NULL) {
    printf("Error: Could not open file\n");
    return;
  }
  char word[60];

  while (fscanf(pDictionary, "%s", word) != EOF) {
    
    char *key = malloc(strlen(word) + 1);
    char *data = malloc(strlen(word) + 1);
    strcpy(key, word);
    strcpy(data, word);

    //printf("Key: %s, Data: %s", *key, *data);
    // pass table in as param to readDict
    insertData(dictionary, key, data);
  }
  
  fclose(pDictionary);
}

/*
 * This should process standard input (stdin) and copy it to standard
 * output (stdout) as specified in the spec (e.g., if a standard 
 * dictionary was used and the string "this is a taest of  this-proGram" 
 * was given to stdin, the output to stdout should be 
 * "this is a teast [sic] of  this-proGram").  All words should be checked
 * against the dictionary as they are input, then with all but the first
 * letter converted to lowercase, and finally with all letters converted
 * to lowercase.  Only if all 3 cases are not in the dictionary should it
 * be reported as not found by appending " [sic]" after the error.
 *
 * Since we care about preserving whitespace and pass through all non alphabet
 * characters untouched, scanf() is probably insufficent (since it only considers
 * whitespace as breaking strings), meaning you will probably have
 * to get characters from stdin one at a time.
 *
 * Do note that even under the initial assumption that no word is longer than 60
 * characters, you may still encounter strings of non-alphabetic characters (e.g.,
 * numbers and punctuation) which are longer than 60 characters. Again, for the 
 * final 20% of your grade, you cannot assume words have a bounded length.
 */
void processInput() {
    // char input[100];
    int ch;
    char current_word[100];
    int word_index = 0;
    // const int CHUNK_SIZE = 128;

    while ((ch = getchar()) != EOF) {
        // Read one word at a time
        if (ch != ' ' && ch != '\n') {
            // Prevent buffer overflow
            if (word_index < sizeof(current_word) - 1) {
                current_word[word_index] = ch;
                word_index++;
            }
        } else {
            // Terminate the string
            current_word[word_index] = '\0';

            // Process the word
            //printf("Current word: %s\n", current_word);

            // Create different versions of the word
            char original_word[100];
            char lower_word[100];
            char title_word[100];

            strcpy(original_word, current_word);
            convertToLowercase(current_word, lower_word); // Convert to lowercase
            convertToTitleCase(current_word, title_word); // Convert to title case
            printf("Current word formats: %s %s % s\n", current_word, lower_word, title_word);

            // Search in the dictionary
            findData(dictionary, original_word);
            findData(dictionary, lower_word);
            findData(dictionary, title_word);

            // Reset word_index for the next word
            word_index = 0;
            memset(current_word, 0, sizeof(current_word));  // Clears the entire string (fills with zeros)
        }
    }
}
