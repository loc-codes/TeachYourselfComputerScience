# Lecture 6 - More Lists
## One Downside of current SLList
- Inserting at the back of an SSList is slow for large lists
- Also removing near the back is slow

## Solution: DLList
- SLList is a singly linked list
- DLList is a doubly linked list -> each node links to prev and next
- With a single sentinel, the issue is all our functions have to check if last is the sentinel
- Alternatively, we have two sentinels, start and end sentinel

## Even better: Circular Sentinel DDList
- Instead of last_item.next == null, we have last.next == sentinel
- This is the preferred approach to Project 1

## DLList Summary
- See slide, may be useful for Proj1

## Generic Lists
- So far, we only support lists of integers
- Java allows us to defer type selection until declaratation
  - Specify in declaration and instantiation

## Generics
- When declaring you data structure, use the reference type
  - eg: int = Integer

## Introduction to AList
- Array based list

## Getting Memory Boxes
- Memory boxes occur when we declare variables or instantiate objects
- int x; (memory box of 32 bits)
- Walrus w1 (memory box of 64 bits that stores Walrus references)
- Arrays are a special king of object which consists of a nunbered sequnce of memory boxes
  - To get ith item of array i, use A[i]
  - This is unlike class instances which have named memory boxes

## Arrays
- Arrays consist of:
  - Fixed integer length
  - A sequence of N memory boxes where N=length
- Arrays DO NOT have methods
- Instantiated with new (most of the time)
- Three valid notations
  - y = new int[3]; creates array containing 3 int boxes with default value (32 x 3 = 96 bits)
  - x = new int[]{1,2,3,4,5};
  - int[] w = {9,10,11,12,13};

## Array vs Classes
- Arrays and Classes can both be used to organise a bunch of memory boxes
  - Array boxes are accessed using [] notation
  - Class boxes are accessed using dot notation
  - Array boxes are must all be the same type
  - Class boxes may be of different types
  - Both have a fixed number of boxes (in Java)
- Array indices can be computed at run time
- The only easy way to access a class is hard-coded dot notation
  - eg: fieldOfInterest = "mass"
  - double w = p.mass (makes sense)
  - double m = p.fieldOfInterest