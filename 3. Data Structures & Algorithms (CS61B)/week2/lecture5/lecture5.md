# Lecture 5 - Linked Lists
## Recursive Implementation of a List
- While functional, "naked" link lists like IntList from the last lecture is hard to use
- Users of this class need to know references very well and think recursively
- It's not easy for all users
- Idea, have an IntNode class instead of IntList

## SLLists vs IntLists
- SLList is much simpler to use, use the methods provided
- SLLIst acts as a middle man between the user and raw data structure
- Use private methods to prevent direct access

## Private Methods and Access
- Hide implementation details from teh users of your class
- Less for user of class to understand
- Car analogy: 
  - Public methods: Pedals, Steering Wheel
  - Private Methods: Fuel Line, Rotary Valve
- Access control does not prevent bad actors, it just signals to other programmers to please not use this

## Why nest classes?
- Nested classes are useful when a class doesn't stand on its own


## Improvement 5: Fast Size
- Solution: Maintain a special size variable that caches the size of the list
- Caching = putting aside data to speed up retrieval
- No such thing as a free lunch: slows down the add methods slightly
- But the tradeoffs are worth it

## SLList - Middle Man
- Middleman data structures allow us to store meta information about the entire naked structure
- eg: size of the LList as a attribute of instances of SSList

## Sentinel Nodes
- The empty list is just the sentinel node
- A list with 3 numbers has a sentinel node and 3 nodes that contain real data

## Invariant
- Condition that is guaranteed to be true during code execution
- SLList with a sentinal node has at least the following invariants
  - Sentinel reference always points to sentinel node
  - The first node is always at sentinel.next
  - The size variable is always the total number of items that have been added
- Invariants make it easier to simplify/reason about code
