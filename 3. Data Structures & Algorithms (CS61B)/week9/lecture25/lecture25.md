# BSTs and Multi-Dimensional Data
## Search Trees
- Three different implementations of Tree Implementations of Sets/Maps
  - BST
  - 2-3 Tree
  - Red Black Tree
- Search Tree support very fast insert, remove and delete
  - Requires that data can be compared to each other with some total order
  - We used Comparable Interface as comparsion engine

## Expanding the power of our swt
- Say we want to add a select operation for this set: {1,4,5,6,9,11,14,17,20}
  - Select(int i)
    - select(0): 1
    - select(3): 6
- What about a rank operation - opposite of select
  - Rank (T x)
    - rank(1): 0
    - rank(6): 3
- What about subSet(T from, T to): Returns all items between from and to
  - subSet(4,9) returns {4,5,6,9}
- What about nearest(T x): Returns the value closest to x
  - Nearest(10): returns 9 or 11
  - But we need a notation of distance between 10 and 9 or 11
  - Implement by searching for N in the tree, and record the closest item seen on search path
  - Use similar ideas for select, rank and subSet
    - won't be implemented in lecture, but shows more uses for the tree

## Sets and Maps on 2D data
- So far we've only discussed one dimensional data
  - eg: 1 < 3 < 6 < 9
  - "cat" < "dog" < "zebra" on string length then alphabetical

## Motivation: 2D Range Finding and Nearest Neighbours
- 2D Range Searching: How many objects are in the highlighted rectangle
- Nearest: What is the closest object to the space horse
- Ideally, sorted data in a BST like format that allows for more efficient approaches than just iterating over all objects

## Building Trees of Two Dimensional Data
- The process of cutting of a tre search early is called "Pruning"
- On two dimensional data, if we build a tree based on x co-ord, a prune for x<-1.5
  - but if we build a tree based on y-co-ord, and prune for x < -1.5, theres no way without going over every entry!
    - therefore, the tree is not a useful structure

## Quadtrees and Generalising Trees
- Simplest solution conceptually
  - Every node has four children
    - Top left (northwest)
    - Top right (north east)
    - Bottom left (south west)
    - Bottom right (south east)
  - Given two coords (1.5, 1.6) and (1.0, 2.8)
    - (1.0, 2.8) is Northwest of (1.5, 1.6), structure tree accordingly
- Topology will determine if quadtree is bushy or spindly

## Quadtrees Properties
- Quadtrees are a form of spatial partitionings
  - Quadtrees: Each node "owns" 4 subspaces
    - Space is more finely divided in regions, where there are more points
    - Results in better runtime in many circumstances

## Quadtree Range Search Demo
- Quadtrees allow us to prune when performing a rectangle search
- Simple idea: Prune (ie: dont explore) subspaces that don't intersect with the query rectangle

## Higher Dimensional Data
- Suppose we want to store objects in 3D space
  - Quadtrees only have four directions, but in 3D, there are 8
  - One approach, use an Oct-tree
    - Very widely used in practice

## Even Higher Dimensional Space
- You want to organise data on a large number of dimensions
  - Dimensions aren't necessarily space, but just properties of objects
- Eg: You want to find a song with the following
  - Length between 3 and 6 minutes
  - Between 1000 and 20,000 listens
  - Between 120 to 150BPM
  - Were recorded after 2004
- In these cases, one somewhat common solution is a k-d tree
  - Data structure that handles arbitrary numbers of dimensions
    - k-d means k dimensional: eg k=2 dimensional, 2-d
  - Based idea for 2d
    - Root note partitions the entire space into left and right (x,y)
    - All depth 1 nodes partition subspace into up and down (by y)
    - All depth 2 nodes partition subspace into left and right (by x)
    - Break ties by saying equal in one dimension go right or up

## K-d trees
- Each point owns 2 subspaces for 2d
  - Similar to a quadtree

## K-d Nearest Neighbour
- nearest(A, (0,7))
- A = (2,3), dist(A) is sqrt((2-0)^2+(7-3)^2) = sqrt(4+16) = 4.5. 
  - current best distance as 4.5 < inf
- Traverse tree like we would insert, now look left as x=0 < x=2
- E = (1,5), dist(E)=sqrt(1+4)=2.2
  - 2.2 < 4.5, so new best
- Now look up as y=7>y=5
  - Children are null
  - we now finished exploring the good child
  - should we look at bad child? Yes -> there are possible smaller children
- Children are null again, so we looked both sides of e
  - go back up
- Back at a, best is E=2.2
  - Best possible point is (2,7)
    - 2 < 2.2, so explore bad child
- Look at B, B(4,2)
- Continues to go on recursively until all relevant children have been explored


## Nearest Method Pseudocode
nearest(Node n, Point goal, Node best)
if n is null, return best
if n.distance(goal) < best.distance(goal), best = n
if goal < n
  goodSide = n.leftChild //"left" is generalised
  badSide = n.rightChild //"right" is generalised
else
  goodSide = n.rightChild
  badSide = n.leftChild
best = nearest(goodSide, goal, best)
If badSide could have something useful
  best = nearest(badSide, goal, best)
return best

## Summary and Applications
Multidimensial has interesting operations to generalise
- Range finding
- Nearest
  - can be generalised to k-nearest

Most common approach is spatial partitioning:
- Uniform partitioning: Analogous to hashing
- Quadtree
- K-d Tree