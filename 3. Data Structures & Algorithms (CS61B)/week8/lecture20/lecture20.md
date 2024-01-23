# Lecture 20 - Heaps and Priority Queues
## Priority Queue Interface
/** (Min) Priority Queue: AlLowing tracking and removal of
the smallest item in a priority queue. */
public interface MinPQ<Item extends Comparable<Item> {
    /** Adds the item to the priority queue. */ 
    public void add(Item x);

    /** Returns the smallest item in the priority queue. */ 
    public Item getSmallest();

    /** Removes the smallest item from the priority queue. */ 
     public Item removeSmallest ();

    /** Returns the size of the priority queue. */ 
    public int size(); 
}

- Think of it "bag of stuff" that you can only interact with the smallest items/best item
  - still has "add" operation for adding things to bag

### Naive Approach
- Create a list of all messages sent to a person on a day
  - Sort it by a comparator
  - Return M messages that are the shortest (or whatever metric you want)
- Potentially uses a huge amount of memory Theta(N) we N is number of messages
- Goal: Do this in Theta(M) memory using MinPQ

### Better Implementation: Track the M Best
- Can track top M messages using M memory. 
  - API for MinPQ also makes code very simple
    - Don't need to do explicit comparisons

### Possibilities for implenting MinPQ?
- Ordered array
- Bushy BST
- Hash Table


| Operation      | Ordered Array | Bushy BST  | Hash Table | Heap |
|:---------------|:--------------|:-----------|:-----------|:-----|
| add            | O(N)          | O(log N)   | 0(1)       |      |
| getSmallest    | O(1)          | O(log N)   | 0(N)       |      |
| removeSmallest | O(N)          | O(log N)   | 0(N)       |      |
| Caveats        |               | Dups tough |            |      |

## Heaps
### Introducing the Heap
- BSTs would work, but need to be kept bushy and duplicates are awkward
- Binary min-heap: Binary tree that is complete and obeys min-heap property
- Min-heap: Every node is less than or equal to both of its children
- Complete: missing items only at the bottom level (if any), all nodes are as far left as possible

### What Good are Heaps
- Heaps lend themselves very naturally to implemntation of a priorty queue
  - getSmallest always returns the root!

### Adding to a heap - My Thinking before answer
Is 3 <= 1: No, look right
Is 3 <= 5: Yes, make 3 take 5's place
Is 5 <= 6: No, look right
Is 5 <= 5: Yes, make 5 take 5's place
Is 5 <= 8: No, look right
Is 5 <= null: Its blank, we done

Generalising, look left, if <= take place, else look right
Keep going until you find a null

### Remove Smallest - My Thinking before answer
Remove 1, compare left and right, take smallest
Make the smallest of 1's children come up, repeat
If we only have 1 null child, we have to recurse down the left branch, adding the 1 non-null child

### Adding Actual Answer
- Add 3 to end of heap temporarily
  - Is 3 >= parent: 3 >= 5. True
  - Swap spots
- Keep going up until a 3 >= parent is false

- Dependency: Path to next addition

### Remove Smallest
- Swap the smallest with the last inserted item
- last inserted item is now the root.
- sink root down, following the path of the smallest child at each layer
  - if tie, go left

### Heap Operations Summary
- Given a heap, how do we implement PQ
- getSmallest() = return item in root node
- add(x) - place new employee in the last position, promote as high as possible
- removeSmallest() - assasinate the president, promote the rightmost person in the company, then demote repeatedly always taking the better candidate

## How do we represent a tree in Hava?
### Approach 1a
public class TreelA<Key> {
    Key k; // e.g. O 
    TreelA left;
    TreelA middle;
    TreelA right;
}

- Very useful box and pointer representation in the slides!
    - Each box of the above is a linked list of 4 items, left point to left linked list of 4, middle point to mid linked list of 4 etc

### Approach 1b
public class Tree1B<Key> {
    Key k; //e.g. O 
    TreelB [] children;
}

- Variable width nodes of how ever many children we want
- Again, useful box and pointer representation in slide

### Approach 1c
public class Tree1C‹Key> {
    Key k; // e.g. 0
    Tree1C favoredchild;
    Tree1C sibling;
}

- Again, picture in slides

### Approach 2
- Store keys in an array. Store parentIds in an array.
- Similar idea to disjointSets

public class Tree2<Key> {
    key[] keys;
    int[] parents;
}

### Approach 3
- Store keys in an array, don't store structure anywhere
- To interpret array: Simply assume tree is complete
- Obviously only works for complete trees

public class Tree3<Key> {
    key[] keys;
}

public void swim(int k) {
    if (keys[parent (k)] > keys [k]) {
        swap (k, parent (k));
        swim(parent (k));
    }
}

- How do we find parent?
  - parent can be found by (k-1)/2
  public int parent(int k) {
      return (k - 1)
  }

### Approach 3b - Cleaner approach 3
- Offset everything by 1/have a sentinel entry
- [null, k, e, v]
- leftChild = k * 2
- rightChild = k * 2 + 1
- parent(k) = k/2 (Java division rounds down)

### Heap Implementation of a Priority Queue - Performance
| Operation      | Ordered Array | Bushy BST  | Hash Table | Heap     |
|:---------------|:--------------|:-----------|:-----------|:---------|
| add            | O(N)          | O(log N)   | 0(1)       | O(log N) |
| getSmallest    | O(1)          | O(log N)   | 0(N)       | O(1)     |
| removeSmallest | O(N)          | O(log N)   | 0(N)       | O(log N) |

- Array based heaps take 1/3 less memory than explicitly representing a tree

## Data Structures Summary so far
### The search problem
- Given a stream of data, retrieve information of interest

### Search Data Structures
| Name          | Storage Operations             | Primary Retrieval Operation       | Retrieve By    | 
|:--------------|:-------------------------------|:----------------------------------|:---------------|
| List          | add(key) and insert(key,index) | get - returns key                 | index          | 
| Map           | put(key, value)                | get - returns value               | key identity   |
| Set           | add(key)                       | containsKey - returns t/f         | key identity   |
| PQ            | add(key)                       | getSmallest - returns smallestKey | key order      |
| Disjoint Sets | connect(int1, int2)            | isConnect(int1, int2)             | two int values |

- Set & Map Implementations
  - BST (bad performance)
  - 2-3 Tree (good)
  - Red Black Tree (good)
  - Hash Tables (good)
    - Seperate Chaining HT (think as linked list)
    - Linear Probing HT (think as resizing array)

- List, Stack & Deque Implementation
  - Linked List
  - Resizing Array

- Priority Queue
  - Ordered Array (bad)
  - LLRB - fast but hard to handle dups
  - Heap

- DisjointSets
  - Quick Find and Quick Unions (bad)
  - Weighted QU with/without path compression (good)

### Abstraction often happens in layers
- PQ -> Heap Ordered Tree -> Tree -> Tree Approach 1, 2 or 3?

- External Chaining Hash Table -> Array of Buckets -> What's a bucket -> ArrayList, Resizing Array, Linked List

### Goals of Computer Science
- Managing complexity with good layers of abstraction
  - Performance complexity and style/engineering complexity

### Data Structures in Summary
- Data Structure: A particular way of organising data
  - We've cover abstract data types, their common implementations and the tradeoffs
- We'll do 2 more
  - The quadtree - next lecture
  - The graph - the next few lectures
