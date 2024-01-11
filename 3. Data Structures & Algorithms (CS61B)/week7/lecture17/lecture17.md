# Lecture 17
## Tree Height: Bushy Trees vs Spindly Trees
- For every item added to a spindly tree, height is N+1 and runtime is Theta(N)
  - Degenerates into linked list
- For every item added to a binary tree, runtime is Theta(log(N))

## The Usefulness of Big O
- Big O is less precise than Theta, but its still useful
  - Allows us to make simple blanket statements
    - BST is O(Log) instead of BST is Theta(log N) in worst case
  - Sometimes we don't know the exact runtime, so use O to give an upper bound
    - Eg: current best algorithm for some problem is O(N), maybe research will determine a quicker way

## Height, Depth and Performance
- The depth of a node is how far from the root
  - eg: depth(g) = 2
- Height of tree is the depth of its deepest leaf
  - eg: height(T) = 4
- Average depth is the average depth of a tree's nodes

![Screenshot 2024-01-11 at 7.36.16 pm.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fw2%2Fl18rs6rx3lb2w5sygs4019_40000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Wlbfv8%2FScreenshot%202024-01-11%20at%207.36.16%20pm.png "Slide")

- Height of tree determines the worst case runtime to find a node
- The average depth determine the average case runtime to find a node

## Random Add Trees
- Random trees are bushy, not spindly on average
  - Average runtime of contains is Theta(log N)
  - Even random trees with deletions are random!
-  But we can't use random adds for certain data (eg: dates)

## B-trees / 2-3 Trees / 2-3-4 Trees
## Avoiding Imbalance
- Crazy idea: Never add lead leaves at the bottom
  - Tree can never get imbalanced
  - "Overstuff" the leaf nodes, because leaf depths never change
    - Logically consistent, but very weird data strucutre
    - Problem with the idea: eventually you get a leaf thats just a linked list
- Solution: Set a limit L on number of items in a leaf
  - Say L=3
- If any node has more than L items, give an item to parent
- Problem now:
  - Set is not ordered!
- Solution: Node splitting
  - Now logically consistent and not so weird
## Perfect Balance
- Observation: Splitting trees have perfect balance
- If we split the root, every node gets pushed down one level
- If we split a leaf or internal, height doesn't changce

## Naming
- The real name of splitting trees is B-trees
- B-trees of order L=3 (like today) are called 2-3-4 tree or 2-4 tree
  - 2-3-4 referrs to the number of children that a node can have
- B-trees of order L=2 are also called 2-3 tree

- 2-3-4 trees (L=3)
  - max 3 items per node
  - max 4 non-null children per node
- 2-3 trees (L=2)
  - Max 2 items with node
  - max 3 non-null children per node

## B-Tree Bushy Invariants
- You add elements in order you choose, and we get different heights
  - BUT you are guaranteed to be bushy
- The Invariants that guarantee bushyness
  - All leaves must be the same distance from the source
    - ie: all leave have same depth
  - A non-leaf node with k items must have exactly k+1 children

## B-Tree Runtime Analysis
- L: Max number of items oper node
- Height: Between ~Log{l+1}(N) and ~Log{2}(N)
  - note Log2(N) = Log{2}(N)
- Since both are logs, we drop the base and say height is Theta(log N)

## Runtime for contains
- Worst case number of nodes to inspect: H + 1
- Worst case number of items to inspect per node: L
- Overall runtime: O(HL)
- Since H=Theta(log N)
  - Overall runtime is O(L * log N)
  - L is a constant (eg: 2 for 2-3 tree)
  - Therefore, O(log N)
- Therefore contains and add are both O(log N)

## Summary
- BSTs have best case height Theta(log N) and worst case height Theta(N)

- B-Trees are a modification of the BST that avoids Theta(N) worst case
  - Nodes may contain between 1 and L items
  - Contains work almost exactly like a normal BST
  - add works by adding items ot existing lists
    - If nodes are too full, they split
  - Resulting tree has perfect balance
    - Runtime for operations is O(log N)
  - B trees are more complex, but they efficiently handle ANY insertion order