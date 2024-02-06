# Tries - A new Set and Maps
## Abstract Data Types and Implementations
- Tries are a new IMPLEMENTATION of sets and maps

## BST and Hash Table Set Runtimes
- Our runtimes are very very fast, add and contains are logN for BST and constant on average for Hash Table

## Special Case 2: String Keyed Maps
- Suppose we know our keys are always strings
  - Can use special data structure known as Trie
  - Basic idea: Store each letter of the string as a node in a tree
  - 'Tries' have great performance for get, add and contains

## Tries: Seach Hits and Misses
- Mark each node the ends a word in some way
  - Allows you make sure "Sam" returns true but "sa" does not

Two ways to search a miss
- If the final node is white
- If we fall off the tree (key is not in children)

## Trie Maps
- Tries can also be maps of course
  - Have nodes with keys and values, keys are always a single letter

## Trie
- Short for Retrieval Tree
- Suggested it should be pronounced "tree" but everyone pronounces "trie"

## Trie Implementation and Performance
public class TrieSet {
    private static final int R = 128; //ASCII, max number of children
    private Node root; //root of trie

    private static class Node {
        private char ch;
        private boolean isKey;
        private DataIndexCharMap next;
        private Node(char c, boolean b, int r) {
            ch = c; 
            isKey = b;
            next = new DataIndexedCharMap<Node>(R)
        }
    }
}

- Since we know our keys are characters, can use a DataIndexedCharMap
  - It is a map from DataIndex to some character value

- Observation: The letter stroed inside each node is actually redundant
  - We can remove char ch from each Node and tings work fine!

## Trie Performance
- Given a Trie with N Keys
  - Add and Contains runtime is constant, runtime is total independent of the number of keys N
- Or in L terms, the length of the key
  - Add: Theta(L)
  - Contains: Theta(L)
- There are no special caveats like "on average" for HashMaps

## Downsides
- Wasteful: Huge memory cost of storing R links per node
  - Most links are unused in real world usage

## Alternate Child Tracking Strategies
- Using a DataIndexCharMap, it is very memory wasteful

## Alternative Ideas
- Use BST or Hashmaps as the nodes, see slides for visualisation
    - Using a BST or Hashtable will take slighlty more time
      - BST is O(Log R), where R is size of alphabet
      - Worst case, HT is O(R) where R is size of alphabet if lots of collisions
    - Since R is fixed, we can think of all 3 as Theta(1)

## In Summary
- When our keys are strings, Tries give us slightly better performance on contains and add
  - Using BST or Hash Table will be slighlty slower, but more memory efficient
  - Would have to do computational experiments to see whats best
- Tries really shine in their efficiency of special string operations!!

## Trie Specic Operations
- Main appeal of tries is their ability to efficiently support string specific operations like prefix matching
- Find all keysWithPrefix("sa")
- longestPrefixOf("sample")
- collect(): get all keys in a Trie

- Tries really naturally support these operations, hard to implement with HashTable and BST
