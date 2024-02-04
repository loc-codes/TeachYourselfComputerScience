# Lecture 22 - Graph Traversals
## BredthFirstPaths
- Act in order of distance from s:
  - Analogous to "level order". Search is wide, not deep
- Graph traversals are not unique - tree traversals are unique

## Shortest Paths - BFS
- Goal: FInd shortest path between s and every other vertex
- Initialise a queue with a starting vertex s and mark that vertex
  - Queue is a list that has two operations: enqueue (aka addLast) and dequeue (aka removeFirst)
  - Call the queue the fringe
    - The edge of what you're exploring
- Side note: Queue is opposite of a stack. Stack has push(addFirst) and pop(removeFirst)
- Repeat until queue is empty
  - Remove vertex v from the front of the queue
  - For each unmarked neighbour n of v:
    - Mark n, add n to fringe
    - Set edgeTo[m] = v, set distTo[n] = distTo[v] + 1

- We can now retrieve all distances and edges in constant time
  - We end up with an array of all edgeTo and distTo

## Graph API
- We want to build graph algorithms, what's our interface?
- An API for graphs
  - For our purposes today, these are Graph methods including their signatures and behaviours
  - Defines how Graph client programmers must thing
- An underlying data structure to represent our graphs

- Our choices can have profound implications on:
  - Runtime
  - Memory usage
  - Difficulty of implementation

### Decision 1: Integer Vertices
- Common convention: Number nodes irrespective of "label", and use number throughout the graphj implementation
  - To lookup vertex by Label, you'd need to use Map<Label, Integer>

### Graph API from Textbook
public class Graph {
    public Graph(int V):    create empty Graph with v vertices
    public void addEdge(int v, int w): add an edge v-w
    Iterable<Integer> adj(int v): vertices adjacent to v
    int V();    number of vertices
    int E();    number of edges
}

// Degree of a vertex in a graph
public static int degree(Graph G, int V) {
    int degree = 0
    for (int w: G.adj(v)) {
        degree += 1        
    }
    return degree;
}

public static void print(Graph g) {
    for (int v = 1; v <= g.V(); v++) {
        for (int w: g.adj()) {
            System.out.print(v + "-" + w)
        }
    }
}

## Graph Representations and Graph Algorithm Runtime
- In choosing the underlying data structure to represent our graphs will have a big impact on runtime and memory usage

### Graph Representation 1: Adjacency Matrix
- For undirected graph,each edge is represented twice in the matrix
    - Simplicity at the expense of space
    - Total runtime to iterate over all neighbours of v is THeta(V)
      - Underlying code has to iterate through entire aarray to handle next() and hasNext()

### Graph Representation 2: Edge Sets: Collection of all edges
- Where each edges is a pair of ints

### Representation 3: Adjacency lists
- Common approach: Maintain array of lists indexed by vertex number
- Most popular approach for representing graphs
  - Very similar to chaining hash table
- Most popular way graphs are represented in practice
  - As graphs are generally sparse
- Runtime
  - All cases Theta(V+E)
    - Create V iteratorss
    - Print E times
  - best case: Theta(V), worst case: V^2

### Graph Representations
- Runtime analysis on slide table
- In practice, adjacency lists are most common
  - Many graph algorithms rely heavily on adj(s)
  - Most graphs are sparses (not many edges in each bucket)

## Depth First Search Implementation
- Common design pattern in graph algos: Decouple type from processing algorithm
- Pass the graph to a graph-processing method (or constructor) in a client class
- Query the client class for information

public class Paths {
    public Paths(Graph G, int s) find all paths from G
    boolean hasPathTo(int v): is there a path from s to v
    Iterable<Integer> pathTo(int v): path from s to v (if any)
}

- Think of depth first search as looking as far down a branch as possible before getting back to root of multiple branches
### Runtime fir DepthFirst Paths
- Worst case runtime for DFS is O(V+E)
- Each vertex is visited at most once
- Each edges is considered at most twice (O(E))
- Therefore, no more than V + 2E, so runtime is O(V+E)
- Could we say runtime is O(E)?
  - Argument: Can only cisit a vertex if there is an edge to it
    - # of DFS calls is bounded above by E
    - So why not say O(E)?
- The reason why WE CAN'T DO O(E) because
- Constructor has to create an all false maarked array
- This marking of all vertices as false takes O(V) time
- Our cost model earlier of (dfs call + marked check) is incorrect

### BreadthFirstPaths Implementation
- Efficiency is also O(V + E)
- Theta(V) space
- No real reason to use depthFirstPaths - breadthFirstPaths gets you all paths, DFS gets you one path

## Summary
- BFS: Uses a queue instead of recursion to track waht work needs to be done
- Graph API
  - Lecture code is one possible
- Implementations
  - Adjacency matrix
  - List of edges
  - Adjacency list (most common in practice)
- Choice of implemntation has big impact on runtime and memory
  - DFS and BFS with list is O(V+E)
  - with matrix is O(V^2)