# Disjoint Sets / Union Finds
## Disjoint Sets Data Structure
- Has 2 operations
  - connect(x,y)
    - connects x and y
  - isConnected(x,y)
    - returns true if x and y are connected
    - connections don't need to be direct

## Disjoint Sets on Integers
- To keep things simple
  - Force all items to be integers
  - Declare the number of items in advance, everything disconnect at the start
- Goal
  - Design an efficient implementation
  - Number of elements N can be huge
  - Number of method calls M can be huge
  - Calls to methods may be interspersed

## Naive Approach
- Connect two things: Record every single connecting line in some data structure
- Checking connectedness: Do some sort of (??) iteration over the lines to see if one thing can be reached

## Better Approach: Connected Components
- Only record the sets that each item belongs to
- For every item, its connected component is the set of all items that are connected to that item

## Quick Find
- Challenge: Pick Data Structures to Support Tracking of Sets
- Idea 1: List of Set of Integers
  - Complicated and slow!
  - Operations are linear when number of connections are small
  - Important Point: By deciding to use List of Sets, we are doomed to complexity and bad performance
  - Constructor, connect and isConnected are all O(N) complexity

- Idea 2: Just use a list of integers
  - Store the set number of each integer
  - See slide for possible implementation
  - Constructor and Connect are Theta(N)
  - isConnected is Theta(1)

- Idea 3: Quick Union
  - Still represent everything as connect components
  - Still represent connected components as list of integers
  - However, values will be chosen so that connect is fast
  - Rather than change the data structure, change the numbers stored in the structure
  - Idea: Assign each item a parent instead of an id
    - Results in tree-like shapes
  - QuickUnionDS defect: Trees can get tall. Results in potentially even worse performance than QuickFind if tree is imbalanced
    - Things would be fine if we just kept our tree balanced

- Idea 4: Weighted Quick Union
  - Suppose we are trying to connect(2,5). We have two choices
    - Make 5's root into child of 2's root - Height 2
    - Make 2's root into a child of 5's root - Height 3
  - See slide for illustration
  - Aim is to minimise height
  - Modify quick union to avoid tall trees
    - Track tree size (number of elements)
    - Always link root of smaller tree to larger tree
  - BUT WE WANT TO TRACK WEIGHT (NUMBER OF ELEMENTS) NOT HEIGHT (DISTANCE FROM ROOT TO FURTHEST LEAF)

- Implementing Idea 4
  - Minimal changes needed:
  - Use parent[] array as before
  - isConnected has no changes
  - connect needs to keep track of sizes
  - Two common approaches
    - Use values other than -1 in parent array for root nodes to track size
    - Create separate size array
  - Slide has useful details for lab

- Weighted Quick Union is at worst Theta(log N)
- Heighted Quick Union has the same order of growth
  - But significantly more complex
  - No performance gain for complexity gain
    - Tradeoff not worth it