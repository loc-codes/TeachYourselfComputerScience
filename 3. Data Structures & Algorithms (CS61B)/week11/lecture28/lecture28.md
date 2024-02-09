# Lecture 28 - Reductions and Decompositions
## Topological Sorting
- Perform a DFS traversal from every vertex with in-degree 0 (analogous to a root note)
  - DO NOT clear markings between traversals
- Record DFS postorder in a list eg: [7,4,1,3,0,6,5,2]
- Topological ordering is given by the reverse of that list
  - [2,5,6,0,3,1,4,7]
- Does not work for cycles, but no idea of topological sorting for cycles

- The reason it's called a topological sort
    - Think of this process as sorting our nodes so they appear in order consistent with edges
    - When nodes are sorted in diagram, all arrows point rightward

- A lot of algorithms are fundamentally run DFS or BFS and DO SOMETHING
    - Be aware of that when "DFS" is said, they sometimes mean restarts
      - For valid path/reachability, no restarts
      - For topological sorts, you need restarts

## Directed Acrylic Graphs
- Topological sort only exists if the graph is directed acyclic graph (DAG)

## Shortest Paths on DAGs
- Visit all vertices in topological order
- Similar idea: Run Dijkstra's but use the topological sort as fringe instead of PQ
- Exploits what of topological order meaning you don't need to worry about upsteam & downstream

## Longest Paths Problem
- Best known algorithm is exponential!!!
- Perhaps most important unsolved problem in maths
  - This is like travelling salesman problem, and a bunch of related problems

## Longest Paths algo
- Form a new copy of the graph G' with the signs of all edge weights flipped
- Run DAGSPT on G' yielding result X
- Flip sign of all values in X.distTo.X.edgeTo

## Reductions
- If any subroutine for task Q can be used to solve P, we say P reduces Q
- Reduction is more than sign flipping!
- Arguably, we've been doing reduction throughout the course
  - Abstract lists reduce to arrays or linked lists
- These examples aren't reductions exactly!
  - A better term would be decomposition
    - Take a complex task and break it into smaller parts. This is the heart of computer science
      - Use appropriate abstractions to make problem solving easier!
