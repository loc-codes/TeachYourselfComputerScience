# Lecture 30 - Sorting II: Quick Sort
## Insertion Sort Sweet Spots
- As mentioned last lecture, on arrays with a small number of inversions, insertion sort is extremely fast
- Define an "almost sorted array" as one in which number of inversions <= cN for some c, Insertion sort is excellent. say c = 2, then runtime Theta(2N) = Theta(N)

- Less obvious: For small arrays (N < 15 or so), insertion sort is fastest
  - More empirical and not asymptotic as small n and proven by expirement
  - Rough idea: Divide and conquer algorithms for small N do too much dividing for the amount of conquering
    - Insertion sort goes straight to conquering

## Quick Sort - Backstory, Partitioning
- Core ideas so far:
  - Selection sort: find smallest item and put it at front
  - Heap sort: special case of selection sort
  - Merge sort: merge two sorted halved into one sorted whole
  - Insertion sort: Figure out where to insert the current item
- Quicksort:
  - Much stranger core idea: Partitioning

## Core Idea
To partition an array a[] on element x=a[i] is to rearrange a[] so that:
- x moves to position j (which may be same as i)
- All entries to left of x are <= x
- All entries to right of x are >= x

## Partition Sort aka Quick Sort
- Have some partition algorithm (should be less than Theta(N))
  - Example, build a BST
  - Example, build a new list, scan through original list twice
- Observations after first partition sub-sort
  - 5 in example is exactly where it'd be if the array were sorted
  - Can sort two halves separately (recursively partition the other halves)
    - Creates two sub partition problems after each successful partition

## Quick Sort in words
- Quick sorting N items
  - Partition the left most item
  - Quicksort left half
  - Quicksort right half
  - Repeat for each half until left half and right half are both size 0

- The result looks like the construction of a BST

## Quicksort Analysis
- Creator got lucky, it is empirically the fastest sort
  - But he didn't know that at the time
- Theoretical analysis:
  - Partitioning costs Theta(K) time, where Theta(K) is the number of elements being partitioned
    - As explored earlier in lecture
  - The interesting twist: Overall runtime will depend crucially on where pivot ends up

## Quicksort Runtime
- Best Case: Pivot always lands in middle for each partition
  - ie: each recursive sub problem has pivot land in middle
  - This best case runtime is Theta(N log N)
- Worst case: Pivot always lands at beginning of the array
  - Creates only one recursive problem
  - Theta(N^2)

## Comparison to Mergesort
- Theoretical analysis:
- Best case: Theta(N log N)
- Worst case Theta(N^2)
- But merge sort is Theta(N log N for best and worst
- Recall that Theta(N Log N) vs Theta(N^2) is a really big deal
- But on average, Quick sort is Theta(N log N)
  - Empirically the fastest!
  - In studies, Mean Number is ~2N ln N
    - STD is ~0.65N
    - So Theta(N log N) with extremely high probability

## Intuition: Quicksort is BST sort!!!
- When you partition, it's like saying the pivot is the root
  - Remember a randomly inserted into a BST takes O(N log N), so Quick Sort on average is O(n log n)

## Avoiding the Worst Quicksort
- Performance depends critically on the pivot selected!
- Bad ordering: Array already in sorted or almost sorted order
- Bad element: arrays with duplicates
- What can we do to avoid worst case
- Left most element is always chosen as pivot
- Partitioning algorithm preserves the relative order or <= and >= items

- Ways to solve!
  - Always use median as the pivot
  - Shuffle before quicksorting