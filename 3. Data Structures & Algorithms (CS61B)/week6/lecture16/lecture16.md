# Lecture 16 - Abstract Data Types
## Interface vs Implementation
- In class
  - Developed A List and SLLIst which implement List61B
- In projects
  - Created deque interface implmented by AD and LLD
- Disjoint sets
  - Much richer set of possible implementations
    - ListOfSets
    - QuickFind
    - QuickUnion
    - WeightedQuickUnion

## Abstract Data Type
- An ADT is defined only by its operations, NOT its implementation
- Interface is shadows the cave dwellers see
- Use an Array Deque or Linked List Deque to cast shadows to the cave dwellers

## The Stack ADT
- LIFO
- push(int x): Puts x on top of the stack
- int pop(): Removes and returns the top item from the stack

## GrabBag ADT
- insert(int x): puts x into the grab bag
- int remove(): removes a random item from bag
- int sample(): Samples a random item from the bag (without removing)
- int size(): Number of items in the bag.

## ADT in Java
- Java has a syntax differentiation between ADT and implementations
  - Sometimes interfaces aren't pure ADTs with default methods
- Eg: List<Integer> L = new ArrayList<>();

## Collections
- Some of the most important interfaces in java.util are those that extent the Collection interface
- Lists, Sets and MAps
- Maps are associative arrays (or dictionaries in Python)

## Map Example
- Map<String, Integer> m = new TreeMap<>();
- String[] text = {"sumomo", "mo", "momo"};
for (String s : text) { 
    int currentCount = m.getOrDefault(s,0)
    m.put(s, currentCount + 1)
}
- Same idea as
m = {}
text = ["sumomo", "mo", "momo"]
for s in text:
  current_count = s.get(s,0)
  m[s] = current_count + 1

## Java Libaries
- Lists implemented by LinkedList and ArrayList
- Set implemented by HashSet and TreeSet
- Map implemented by HashMap and TreeMap

## Binary Search Trees
- Fundamental Problem with Linked List
  - Slow search, even though it's in order (eg: contains and add on SLL is Theta(n))
    - Could use skip list (but not covered in this class)

## Optimisation
- Change the Entry point
  - Move the pointer to the middle (sentinel is middle node, not left most node)
  - Turn links around (left point left from mid, right stays pointing right)
  - This halved the search time on average!
- Do even better: recursively work down
  - Take middle point of each half to make them quarters

## BST Definitions
- A tree consists of: 
  - A set of nodes
  - A set of edges that connects those nodes
    - Constant: There is exactly one path between any two nodees

## Rooted Trees and Rooted Binary Tree
- In a rooted tree, we call one node the root
- Every node N except the root has exactly one parent, defined as the first node on the path from N to the root
- Unlike (most) real trees, the root is depicted as top of the tree
- A node with no child is called a leaf
- In a rooted binary tree, every node has 0, 1 or 2 children/subtrees

## Binary Search Tree
One additional property
- For every node X in the tree
  - Every key in the left subtree is less than X's key
  - Every key in the right subtree is greater than X's key
- Ordering must complete complete, transitive and anti-symetric
- As in, given keys p and q
  - Exactly one of p < q or q< p are true
  - If p < q and q < r implies p < r
- One consequence of these rules: no duplicate keys!
  - Keep things simple: Most real world implementations follow this rule

## BST Operations
### BST Operations: Search
- If searchKey equals T.key, return
  - If searchKey < T.key, search T.left
  - If searchKey > T.key, search T.right
  - This is high level overview, pseudocode in slides
- Runtime is Theta(log N) as height of tree is ~log2(N)

### BST Operations: Insert
- Search for key
  - If found, do nothing
  - If not found
    - Create new node
    - Set appropriate link to left or right
- Again, pseudocode in slide

### BST Operations: Delete
- 3 Cases:
  1. Deletion key has no children
     - Just sever the parent's link
  2. Deletion key has one child
     - Maintain BST property
     - A child's child is definitely larger/smaller than parent
     - Safe to move child's child into child spot
  3. Deletion key has two children
     - Hibbard deletion
       - Choose either predecessor or successor and stick in root position
         - Move by: Delete predecessor then make it the root.

## Summary
- Abstract Data Types are defined in terms of operations, not implementaiton
  - What the user expects, not what the writer writes
- Severful useful ADTS: Disjoint Sets, Map, Set, List
- We;ve seen two ways to implement a Set (or Map): ArraySet using a BST
  - ArraySet: Theta(N)
  - BST: Theta(log N)
- BST Implementations
  - Search and insert are straightforward (but insert is a little tricky
    - Avoid arms length recursion
  - Deletion is more challenging - use Hibbard deletion

## Tips for BST Labe
- Code from class was naked Recursion, you BST Map will not be
- For each public method
  - put(K key, V value), create private recursive method put(K key, V value, Node n)
- When inserting, always set left and right pointers, even if nothing is changing
- Avoid "arms length base cases". Don't check if left or right is null