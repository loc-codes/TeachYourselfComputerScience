# Discussion 6
## Content Review
### Disjoint Sets
  - QuickFind uses an array of integer to track set which each element belongs to
  - QuickUnion stores the parent of each node rather than the set
    - merges sets by setting the parent of one root to the other
  - WeightedQuickUnion
    - Same as quickUnion except it decides which set is merged into which by size, reducing stringiness
  - WeightedUnion with Path Compression
    - Sets the parent of each node to the set's root whenever find is called on it

### Asymptotics
- Allow us to evaluate the performance of our programs using math
  - We ignore all constants and only care about the value with reference to the input defined as N
  - What term matters as N goes to infinity/for very large n

- Big O: The upper bound in terms of the input (This is the worst case)
- Big Omega: Lower bound in terms of input (This is the best case)
- Big Theta: The tightest bound, which only exists when tightest upper bound and tightest lower bound converge to same value

- If a worksheet says worst case, use big Theta. 
  - We ignore cases where upper bound and worst case diverge
