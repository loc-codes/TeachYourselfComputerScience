# Lecture 23
##  BFS vs DFS for Path Finding
- Both are correct for all graphs
  - Both will give you a set of paths
- Output quality
  - BFS knows which paths are shorted
  - BFS is a 2-for-1 deal, no ton;y do you get paths, but your paths are also guaranteed to be the shortests
- Time efficiency: Is one more efficient than the other
  - Should be very similar O(V+E). Very careful analysis required to prove otherwise
- Space Efficiency
  - DFS is worse for spindly graphs
    - Call stack gets very deep
    - Computer needs Theta(V) memory to remember recursive calls
  - BFS is worse for absurdly bushy paths
    - Queue gets very large, in worst case, queue will require Theta(V) memory
- BFS is that a good choice for google maps navigation stlye app
  - Problem: BFS returns path with the shortest number of edges
  - Solution: We need an algorithm that takes int account edge distances/edge weights

## Dijkstra's Algorithm
- Observation: Solution will always be a path with no cycles (assuming positive weights)
- The answer to find the shortest path from a source to every other vertex
  - Will always be a tree!!!
  - Can think of as the union of the shortest path to all vertices

## Shortest Paths Tree
- The number of edges in a shortest path tree is always V -1
  - For each vertex, there is exactly only input edge (except source)

## Finding the Shortest Paths Tree
- Bad algo #1: Perform depth first search
  - When you visit v:
    - For each edge from v to w, if w is not already part of SPT, add the edge
  
- Bad algo #2: DDFS
  - For each edge from v to w, add edge to SPT only if that edges yields better distance
    - This is called "Edge Relaxation"
    - Relax all edges == Visit a vertex

- Dijkstra Algorithm: Perform a best first search (closest first)
  - When you visit v
    - For each from v to w, relax that edge
  - Improvements:
    - Use better edges if found
    - Traverse "best first"


## Dijkstra's Pseudocode
- PQ.add(source, 0)
- for other vertices v, PQ.add(v, infinity)
- While PQ is not empty
  - p = PQ.removeSmallest()
  - Relax all edges from P

- Relaxing an edge p -> q with weight w:
  - If distTO[p] + w < distTo[q]
    - distTo[q] = distTo[p] + w
    - edgeTo[q] = p
    - PQ.changePriority(q, distTo[q])

- Invariants
  - edgeTo[v] is best known as predecessor of v
  - distTo[v] is the best known total distance from source to v
  - PQ contains all unvisited vertices in order to distTo

- Always visits vertices in order of total distance from source
- Relaxation always fails on edges to visited vertices

## Guaranteed Optimality
- In a sentence, visit all vertices in order of best known distance from source
  - On visit, relax every dge from visited vertices
- Guaranteed to be optimal so long as there are no negative edges

## Algo Runtime
- In Pseduocode, only PQ operations and everything else in constant time
- PQ add, removeSmallest and changePriority are all logV
  - Number of Operations for PQ add, remove smallested is V
  - Changepriority runs E times

- So overall runtime O(V*log(v) + V*log(V) + E*log(V))
  - Assuming E > V, runtime is E * log(V)
