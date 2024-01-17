# Lecture 18 - Red Black Trees
## 2-3 Trees Problems
- Real pain to implement and suffer from performance problems
  - Issues include
    - Maintaining different node types
    - Interconversion of nodes between 2 and 3 nodes
    - Walking up the tree to split nodes

## BST Structure and Tree Rotation
### BSTs
- 5 possible BSTs with the numbers 1,2,3
  - Tree you finish with depends on insertion order
- Given any BST, it is possible to move to a different configuration using "rotation"
  - Change to any insertion order
  - ie: we can get to a bushy tree

### Rotation definition
- rotateLeft(G): Let x be the right child of G
    - Make G the new left child of x
    - You can think of this as temporarily merging G and P
      - Then sending G down and left
    - Preserves search tree property -> no changes to semantics of tree

### Rotation for Balance
- Can shorten or length a tree
- Preserves search tree property
- This approach takes O(N)
  - But there's a better way to achieve balance through rotation, as we grow the tree

## Red Black Trees
### Search Trees
- Many types of search trees
  - Binary Search Tree -> can balance using rotation
  - 2-3 trees, balance by construction, no rotations required
- Let's try something strange
  - Build a BST that is "structurally identical" to a 2-3
    - We haven't defined "structurally identical"

### Representing 2-3 Tree as BST
- With 2 nodes/children, it is trivial
- Tricky at 3 nodes/children
- Every time we have a 3 nodes, create a dummy glue node
  - Result is inelegant with wasted link and code will be ugly

### Possibility 2: "Create glue link"
- Create glue links with the smaller item to the left
- Mark the glue links as "red"

### Left Leaning Red Black Tree
- LLRBs are normal BSTs
- There is a 1-1 correspondence between LLDB and an equivalent 2-3 tree
- Red links don't do anything special

### LLRB Operations
- Searching for key is easy! Treat like any BST
- Genera lly, an LLRB should be no more than 2x height of its 2-3 tree

### LLRB Properties
- No node has two red links (otherwise it'd be a 4 node which is disallowed)
- Every path from root to a leaf has same number of black links 
  - Because 2-3 trees have same number of links to every leaf
  - Therefore, LLRBs are balanced
- LLRBs are also logarithmic of items
  - 2x height of 2-3, therefore complexity is 2 log = log

### LLRB Construction
- Instead of building a 2-3 tree and converting (as 2-3 trees are complex to implement)
- Insert as usual into BST
- Use zero or more rotations to maintain 1-1 mapping

### Takeaway point
- As height will be approximately double, height is complexity of log n

## 1-1 Correspondence
- There exists a 1-1 mapping between 2-3 trees and LLRBs
- Implement an LLRB based on maintain 1-1 correspondence
- When performing LLRB ops, pretend you're a 2-3 tree
- Preservation of the correspondence will involve tree rotations
- 
### Design Task 1: Insertion of new item
- Always use red link 

### Design 2: Insertion on right
- Solve by: Add, then rotate left

### Design 3: Double left insertion
- Solve by rotateRight

### Design 4: Splitting temp 4 nodes
- Colour flip: flip the colours of all edges touching B
  - Doesn't change structure or shape - doesn't change balance/height

### and That's it!
- When inserting: use a red link
- If there is a right leaning 3 node, we have LEFT LEANING VIOLATION
  - Rotate left the appropriate node to fix
- If there are two consecutive left links, we have INCORRECT 4 NODE VIOLATION
  - Rotate right the appropriate node to fix
- If there are any nodes with two red children, we have TEMPORARY 4 NODE
  - Flip colors to emulate split

- One last detail: Cascading operations
  - It is possible that a rotation or slip will cause an additional violation that needs fixing

### THE RULES IN SHORT
- Right red link -> Rotate left
- Two left links -> Rotate right
- Red left and red right -> Flip

### Runtime Analysis
- Simple if you trust 2-3 tree runtime
- LLRB has height O(log n)
- Contains is trivially O(log n)
- Insert is O(log N)
  - O(log N) to add the new node
  - O(log n) rotation and color flip operations per insert

### Search Tree Summary
- Last 3 lectures
  - BST are simple but subject to imbalance
  - 2-3 Trees (B Trees) are balance but painful to implement
  - LLRBs insertion is simple to implement
- Java TreeMap is a red-black tree (not left leaning
  - Maintains correspondence with 2-3-4 tree
  - Allows glue links on either side

- Other implementations not covered in course
  - AVL trees, splay trees, treaps
- And other efficient ways to implement sets and maps entirely
  - Linked structures: skip lists are linked list with express lanes
  - Other ideas entirely: Hashing is the most common alternative

- But Red Black Tree is the most common real world tree, evident by implementation in Java