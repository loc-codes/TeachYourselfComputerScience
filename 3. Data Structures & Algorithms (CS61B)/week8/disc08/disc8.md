# Discussion - LLRB and Hashing
## B - Trees (2-3 Trees)
- Trees that serve a similar function to binary trees while ensuring a bushy structure

- Each node can have up to 2 items and 3 children
- All leaves are the same distance from the root, which makes get operations take O(log n) time

- When adding to a B-Tree, you first start by adding to the leaf, then push the excess items up the tree, until each node has < 2 items and  < 3 children

## Left Leaning Red Black Trees
- LLRBs are a representation of B-trees that we use becuase it is easier to work with in code.
- IN an LLRB, each multi node in a 2-3 tree is represented using a red connection to the left side
    - You can draw a box around the red connections to make it clear where the 2-3 trees are

## LLRB Balancing Operations
- rotateLeft, rotateRight and colorFlip

## Hashing
- Hash function are functions that represent an object using an integer
- We use them to figure out which bucket of our hashset the item should go in
- Once we have a hash for our object, we use mod to find out which bucket it goes into
- In each bucket, we deal with having lots of items by chaining the items using .equals to find what we are looking for
    - Buckets are a list
    - We use external chaining
    - Say we have [0, 1, 2, 3, 4] - 5 buckets
    - and we want to hash set 0,3 & 5.
    - We get [[0,5], null, null, 3, null]
    - To find 5, we go 5 % mod = 5 % 5 = 0
        - Then iterate through the bucket, if 5 in bucket, true. else false
    - To find 6, we go 6 % 5 = 1. 
        - bucket is null, nothing to iterate, return false


- Collisions: When two objects have the same hashcodes, but they are not the same object

- .equal -> hashcode
- hashcode does not -> .equal
    - This is a collision

- Hashcodes must be deterministic, and when two objects are equal, it returns the same hashcode

