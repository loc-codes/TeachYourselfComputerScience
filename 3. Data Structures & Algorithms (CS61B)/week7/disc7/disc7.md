# Discussion 7 - Asymptotics & Binary Search Trees
## Content Review
### Abstract Data Types
- Abstact Data Types are data structure where we know what they do, but not how.
- They are usually represented as interfaces or abstract classes in Java.
- List are ADT, but they could be an ArrayList or LinkedList. We don't care how the list 
works under the hood

### Binary Search Tree
- A specific data structure that allows us to quickl access elements in sorted order
- Several important properties:
	1. Ech node in a BST is a root of a smaller BST
	2. Every node to the left of the root has a value "lesser than" that of the root
	3. Every node to the right of the root has a value "greater than" that of the root
- BSTs can be bushy or spindly
	- Spindly trees are O(n)
	- Bush trees are O(log n)
- BST insertion
	- Always inserted as leaves
- BST Deletion
	- Always deleted via Hibbard Deletion
	- Deleting leaves are easy
	- If two children, we pick either leftmost node in right subtree or rightmost node in 
left subtree


### Asymptotitcs - Important Point
- Only valid on very large inputs, and comparisons are only useful when comparing inputs of 
different orders of magnitude
- While common themes are helpful, rules like "nested for loops" are always N^2 can lead you 
astray


