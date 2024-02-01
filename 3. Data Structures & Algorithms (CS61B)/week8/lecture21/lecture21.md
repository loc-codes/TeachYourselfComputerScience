# Lecture 21 - Tree Traversals and Graph
## Trees and Traversals
- A tree consists of:
  - A set of nodes
  - A set of edges that connect those nodes
    - Constraint: There is exactly one path between any two nodes

- A rooted tree is a tree where we've chosen on node as the root
  - Every node N except the root has exactly one parent
    - Defined as the first node on the path from N to the root
    - A node with no child is called a leaf

- We've seen trees as nodes in a specific data structure
  - Search trees, tries, heaps, disjoint sets
- Trees are a more general concept
  - Eg: Family trees, Org charts
  - Decision trees (eg: Flowcharts, DRSABC CPR)

- Suppose you want to iterate over a tree (like all files in folder called 61b)
  - File systems on computers are trees
  - What you might call "tree iteration" is actually called "tree traversal"
- Unlike lists, there are many orders in which we might visit the nodes

### Tree Traversal Orderings
- Level Order
  - Vist top to bottom, left to right
- Depth first traversals
  - 3 types: Preorder, Inorder, Postorder
  - Basic idea: Traverse deep nodes (eg: leaves) before shallow ones (eg: root)
- Tree used for examples
      D
    /   \
   B     F
 /  \   / \
A    C E    G

### Depth First Traversals
- Preorder: D, B, A, C, F, E, G
- Visit a node, then traverse its children
preOrder(BSTNode x) {
   if (x == null) return;
   print(x.key)
   preOrder(x.left)
   preOrder(x.right)
}

- Inorder: A, B, C, D, E, F, G
- Traverse left child, visit, traverse right
inOrder(BSTNode x) {
    if (x==null) return;
    inOrder(x.left)
    print(x.key)
    inOrder(x.right)
}

- postOrder: A C B E G F D
- Traverse left and right, then visit
postOrder(BSTNode x) {
    if (x==null) return;
    postOrder(x.left)
    postOrder(x.right)
    print(x.key)
}

### A Useful Visual Trick for Humans
- Trace a path around the graph, from the top going counterwise
  - Preorder: Visit everytime we pass the LEFT of a node
  - Inorder: Visit when you cross the BOTTOM of a node
  - Postorder: Visit when you cross the LEFT

### Real World Traversals
- Directory printing is pre-order using tree command
- Gathering total file sizes in a directory: Use post-order

## Graphs
- Trees are great for representing strict hierarchical relationships
  - But not every relationship is hierarchical
    - eg: Euler's bridges
    - Not a tree! contains cycles
 ### Definition
- A graph consists of:
  - A set of nodes
  - A set of zero or more edges
  - All trees are graphs!!
- A simple graph
  - No edges that connect a vertex to itself (" no loops)
  - No two edges that connect the same verticles ("no parallel edges")

- In 61B, we will use Graph as shorthand for simple cycle

### Graph Types
- Acyclic vs Cyclic
- Directed vs Undirected
- Acyclic, Connected Graphs are trees
- Connected Graphs have edges between all vertices

### More Terminology
- Set Vertices aka Nodes
- Set of Edges aka Pairs of Vertices
- Vertices with an edge between are adjacent
- Vertices or edges may have weights aka labels
- A path is a sequence of vertices connected by edges
  - Of length x (x number of edges)
- A cycle is a pth who first and last vertice are the same
  - A graph with a cycle is called "cyclic"
- Two vertcies are connected if there is a path between them
  - If all vertices are connected, we say the graph is connected
- Degree: how many edges are touching

### Directed Graph
- Edge captures "is-a-type-of" relationship
- eg: miracle is a type of lucky event is type of event

## Graph Problems
### Graph Queries
- What is the shortest route from S to T
  - What is the longest without cycles
- Are there cycles?
- Is there a tour you can take that only uses each node exactly once?
- Is there a tour that uses each edge exactly once
- All solved with traversals

### Graph Queries in Theory
- s-t Path problem: Is there a path between vertices s and t
- Connectivity problem: Is the graph connected: is there a path between all vertices
- Bi-connectivity problem: Is there a vertex whose remove disconnects the graph
  - If yes, may be bad graph design with single path of failure
- Shortest s-t path
- Cycle detection problem: Does the graph contain any cycles
- Euler Tour problem: Is there a cycle that uses every edge once
- Hamilton Tour: Is there a cycle that uses every vertex exactly once
- Planarity: Can you draw the graph on paper with no crossing edges

### Graph Problem Difficulty
- Euler Tour and Hamilton Tour
- An efficient Euler tour algorithm O(# edges) was found in 1873
- Despite decades of intense study, no efficient algorithm for a hamilton tour exists
  - Best algorithms are exponential time

## Depth First Traversal
- s-t Connectivity
  - Given source vertex s and target vertex t, is there a path between s and t
- connected(s,t)
  - Mark s == t? If so, return true
  - Otherwise, if connected(v,t) for any unmarked neighbor v of s, return true
  - Return false

- The idea of exploring a neighbour's entire subgraph before moving on the next neightbour is known as Depth First Traversal
- Go as deep as you can go, until you go to another neighbour

### The Power of Depth First Search
- Very powerful technique that can be used for many types of graph problems
- Example
  - Mark v
  - For each unmarked adjacent vertex w:
    - Set edgeTo[w] = v
    - dfs(w)

## Tree vs Graph Traversals
- Many tree traversals
  - Preorder, inorder, postorder, level order
  - Preorder: act, left, right
  - Inorder: left, act, right
  - Postorder: left, right, act
- DepthFirstPaths in Graphs is called DFS Preorder
  - DFS Preorder: Action is before DFS calls to neighbour
    - Our action was setting edgeTo
    - Example: edgeTo[1] was set before DFS calls to neighnors 2 and 4
  - DFS preorders are not unique due to tie-breaking
    - Take highest or lowest neighbour first?

- DFS Postorder: Action AFTER DFS calls to neighbours
  - edgeTo[1] would be set AFTER DFS call
  - Eg: 
  - mark(s)
  - For each unmarked neighbour n of s, dfs(n)
  - print(s)

- BFS: Breadth first search - analog of level order search
  - Search is wide, not deep


        
