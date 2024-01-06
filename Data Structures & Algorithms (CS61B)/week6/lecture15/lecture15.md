# Asymptotics 2 - Examples of calculating run time
## Loops Example 1: Based on Exact Count
- Find order of growth of the worst case runtime of dup1
-  Example nested for loop
  - C = 1 + 2 + 3 + (N-3) + (N-2) + (N-1) = N(N-1)/2
  - This belongs to Theta(n^2), largest term is n^2

## Loops Example 2:
- Find a simple f(N) such that the runtime R(N) C Theta(f(N)).
- By simple, we mean no unnecessary multiplicative constants or additive terms

- Lets say N = 4
- for (int i = 1; i <= N; i = i * 2)
  - i = 1
  - i = 2
  - i = 4 -> 3 operations
- for (int j = 0; j < i; j += 1)
  - j = 0, i = 1
  - j = 1, i = 1 -> complete loop 1
  - j = 0, i = 2; 
  - j = 1, i = 2
  - j = 2, i = 2 -> complete 2 loop
8 operations

n = 8
- for (int i = 1; i <= N; i = i * 2) 
    - i = 1
    - i = 2
    - i = 4 -> 
    - i = 8 -> 4 operations
    - this feels like log n
- for (int j = 0; j < i; j += 1)
    - j = 0, i = 1
    - j = 1, i = 1 -> complete loop 1
    - j = 0, i = 2;
    - j = 1, i = 2
    - j = 2, i = 2 -> complete 2 loop
    - j = 0; i = 4 -> 
    - j = 1; i = 4 -> 
    - j = 2; i = 4 -> 
    - j = 3; i = 4 -> 
    - j = 4; i = 4 -> complete 3 loop
- Feeling is N Log N

- ANSWER: See slide, build a cost model counting the println("hello") calls
- C(N) = 1 + 2 + 4 + ... + N, if N is a power of 2
- Answer: Runtime is Theta(N)
  - Compute this exactly 1 + 2 + 4 + ... + N = 2N - 1
    - As the number of computations steps at 1, 2, 4, 8, 16 etc
    - This is where 1 + 2 + 4 ... + N comes from

## Useful Tips
- No magic shortcut for these problems
  - We just saw 2 nested for loops that have runtime of N, you can't smell what code runtime is
- Useful identities
  - 1 + 2 + 3 + ... + Q = Q(Q+1/2) = Theta(Q^2)
  - 1 + 2 + 4 + 8 + ... Q = 2Q-1 = Theta(Q)
  - These sums will come out over and over again

- Strategies
  - Find exact sum
  - Write out examples
  - Draw pictures

## Recursion
public static int f(int n) {
  if (n <= 1) return 1
  return f3(n-1) + f3(n-1)
}

This grows at 2^n
- Everytime we increase n by 1, we double the work
- Cost Model, count number of calls to f3
- C(1) = 1 = 1
- C(2) = 1 + 2 = 3
- C(3) = 1 + 2 + 4 = 7
- C(4) = 1 + 2 + 4 + 8 = 15
- C(5) = 1 + 2 + 4 + 8 + 16 = 31
- C(6) = 1 + 2 + 4 + 8 + 16 + 32 = 63
- C(N) = 1 + 2 + 4 + 8 + 16 + ... + 2^(N-1)
- C(N) = 2^0 + 2^1 + 2^2 + ... + 2^(N-1)
- This is the Sums of the First Powers of 2 -> From last slide
- 1 + 2 + 4 + 8 + ... + Q = 2Q-1
- C(N) = 2Q - 1 = 2(2^n-1) - 1 = 2^n - 1 (as 2^n-1 is Q)

- Since work during each call is constant
- R(N) = Theta(2^N)

- Another method
  - If C(1) = 1
  - C(N) = 2C(N-1) + 1
  - Next slide has solution

## Binary Search
Finding a key in a sorted sorted
Compare key against middle entry
- Too small, go left
- Too big, go right
- Equal, found
- Formula for how many items remain N = high - low + 1
- Midpoint is size-0//2

- If array length = 1
  - Runtime is 1
- [1, 2, 3] -> 4
  - lo = 0, hi = 2
  - mid = 1
- x > mid
- [3]
  - lo = 2, hi = 2
2 operations

array len 9 [0,1,2,3,4,5,6,7,8] find 9
- mid = 5
- [5,6,7,8]
- lo = 0, hi = 3
- [6,7,8]
- [8]
3 operations

- Feels logarithmic as we are halving, rather than doubling. doubling is exponentiall
- We cut the array in half until we have 1 item left
- If C is number of calls to binarySearch, solve for 1 = N/2^c
- C = log2(N)
- This is intuitive proof, slides have exact count proof
- LogN for huge datasets is practically equivalent to constant time
  - Log is really good!

## Selection sort: A prelude to MergeSort
- Find the smallest unfixed item, move it to the front and fix it
- Sort the remaining unfixed items using selection sort
- Theta(N^2) - by geometry, the area is a triangle ie (s^2)/2
- Or look at last two unfixed items
- 6 + 5 + 4 + 3 + 2 
  - OR 2 + 3 + 4 + 5 + 6 + ... + N
  - This is the Q(Q+1)/2 identity, so O(N^2)

- Given runtime is quadratic, for N = 64, we might say the runtime for selection sort is 2048 arbitrary units of time
  - For N = 6, we get ~36AU

## The Merge Operation
- Given two sorted arrays, the merge combines them into a single array by successivle copying the smallest item for the two arrays into a target array
- Merge Runtime -> O(N) with array writes as cost model, merge does N writes 

## Using Merge to Speed Up the Sorting Process
- Selection sort the left half O(N^2)
- Selection sort the right half O(N^2)
- Merge the results: O(N)

- N = 64 ~ 1088 AU
  - Merge is 64AU
  - Selection sort is ~2 * 512 = 1024AU
- Still Theta(N^2), but faster since N + 2*(N/2)^2 < N^2
  - Or in arbitrary time ~1088 vs 2048 AU for N=64

## Two Merge Layers
- Can do even better by adding a second layer of merges
- ~640AU
- Merge: 64 AU + 2*32 AU
- Selection Sort: 4 * 128 AU
- This 4 times as fast as original algo

## Mergesort
- If you merge all the way down, theres no selection sort
- If array of size 1, return
- Merge Sort the left half O(??)
- Mergesort the right half: O(??)
- Merge the results: O(N)
- For N = 64, runtime of merge all the way down is ~384AU
- Top Layer ~ 64 = 64
- Second Layer ~ 32*2
- Third Layer ~ 16 * 4
- Overall runtime is ~64K where k is number of layers
- k = log2(64) = 6 so 384 AU

## Mergesort Order of Growth
- Worst case run time: Theta(N Log N)
- Every level takes ~N AU
- Top level takes ~N
- Next Level takes ~N/2 + ~N/2
- and so on

- This total runtime is ~Nk where k is number of levels
  - How many levels, goes until we get to size 1
  - k = log2(N)
- Overall runtime is Theta(n log n)

## Linear vs Linearithmic (N log N) vs Quadratic
- N log N is basically as good as N, and is vastly better than N^2
- For N = 1,000,0000, log N = 20
- At 1 million operations, linear time is 1 second, n log n is 20 seconds, n^2 is 12 days

## Summary
- Theoretical analysis of algo performances requires careful thoguht
- There are NO MAGIC SHORTCUTS
- In our course, its ok to do exact counting and intuitive analysis
- Know how to sum 1 + 2 + 3 + ... + N
- and 1 + 2 + 4 + ... + N
- Most runtime problems in this class resemble one of the 5 problems today
- N^2 to Nlog N is an enormous difference
- Going from N Log N to N is nice, but not radical



