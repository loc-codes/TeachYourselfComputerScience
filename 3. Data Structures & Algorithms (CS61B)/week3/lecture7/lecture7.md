# ALists, Resizing and SLists
## List Limitation - Arbitrary Retrieval
- Suppose we added get(int i), which returns the ith item from the list
- This would be slow compared to getLast for any middle indexes in the list
  - We have to scan each item until we find the middle item
- For now, we'll use arrays instead with no links

## Naive Array Lists
- Random access in arrays - retrieval from any position is very fast
- Independent* of array size

## Our Goal: AList.java
- We will figure out how to build an array version of a list

## Naive AList Code
AList Invariants
- The position of the next item to be inserted is always size
- size is always the number of items in the AList
- THe last item in the list is always in position size-1

## Resizing Arrays
- When the array gets too full, eg: addLast(11), just make a new array
- if size == items.length
- int[] a= new int[size+1]
- System.arraycopy(...)
- items = a; size += 1;
- items now points at copied array, and old array is handled by garbage collector