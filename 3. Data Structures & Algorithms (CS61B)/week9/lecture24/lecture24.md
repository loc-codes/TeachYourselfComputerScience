# Lecture 24 - Minimum Spanning Trees
## Spanning Trees
- Given an undirected graph, a spanning tree T is a subgraph of G, where T:
  - Is connected 
  - Is acyclic (first two properties are tree properties)
  - Includes all vertices (spanning property)

- A minimum spanning tree is a spanning tree of a minimum total weight
- We don't care if spanning tree is bushy, only if it has min total weights

## MST
- For V vertices, we should have V-1 edges in our minimum spanning tree
- Shortest paths tree depends on the strart vertex
  - Because it tells you how ot get from source to everything
- But there is no source for a MST
- Nonetheless, the MST sometimes happens to be an SPT for a specific vertex

## The Cut Property
- A cut is an assignment of a graph's nodes to two non-empty sets
- A crossing edge is an edge which connects a node from one set to a node from the other set
- Cut property: For any given cut, minimum weight crossing edge is in the MST
 
## Generic MST Finding Algo
- Start with no edges
  - Find a cut with no crossing edges in the MST
  - Add smallest crossing edges
  - Repeat for V-1 edges
- This should work, but we need some way of finding a correct cut
  - Randomness is not a good way to go!

## Prim's Algorithm
- Start with some arb start node
- Repeatedly add the shortest edge (mark edge) that has one node inside the MST under construction
- Repeat for V-1 node

## Prim's Algo Implementation
- Natural implementation of conceptual version of Prim's algo is highly inefficient
  - Example: iterate over purple edges shown is unnecessary and slow
- Can use some cleverness and a PQ
- Very similar to Dijkstra's!

## Prim's vs Dijkstra's
- Almost exactly the same in the second implementation
  - Dijktra considers dinstance from source
  - Prim's considers considers distance from tree

## Prim's Runtime
- Assume all PQ operations take log(V) time
- Exactly same analysis as Dijkstra's, overall runtime is:
  - O(V*log(V)) + V*log(V) + E*log(E)
  - Assuming E > V, this is O(E log V)

## Kruskal's ALgorithm
- An efficient implementation is a lot easy than Prim's
- Add each edge assuming it doesn't cause a cycle
  - Cut principle in disguise!
- Consider edges in order of increasing weight
  - Add to MST unless cycle is created
  - Repeat until V-1 edges

## Implementation
- Insert all edges into a PQ
- Repeat: Remove smallest weight edge from front of queue, add to MST if no cycle created
- Use Weighted Quick Union object and ask "isConnected" to determine cycle
  - If isConnected() false, connect that edge to MST

## Kruskal's Implementation & Runtime
- Assume all PQ operations take O(logV) time
- Assume all WQU operations take O(logV)
- Runtime is O(E log E) -> longest operation is to create the PQ
  - If PQ is already sorted, O(E log V)
- Difference between E log V and E log E is marginal.