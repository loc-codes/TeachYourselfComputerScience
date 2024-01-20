# Lecture 19 - Hashmaps and Hashsets
## Intro
### Limits of Search Tree Based Sets
- Our search tree based sets require items to be comparable
  - Need to be able to ask is X < Y
  - Could we somehow avoid the need for comparable objects
- Our search tree have excellent performance, but could maybe be better?
  - Theta(log N) is amazing - 1 billion items has height 30
    - But could it be better?

### Using Data as an index
- One extreme approach: Create an array of booleans indexed by data!
  - Initially all values are false
  - When an item is added, set appropriate index to true

- Obvious issues
  - Extremely wasteful of memory. To support checking presence of all positive integers, we need infinite/sufficiently large booleans
  - Need a way to generalise beyond integers


## DataIndexedEnglishWordSet
### Generalising the DataIndexedIntegerSet Idea
- Key Question: What is the cat-th element of a list
- One idea: use the first letter of the word as an index
  - a = 1, b = 2, ..., z = 26

- What's wrong with this approach?
  - Other words start with c
  - contains("chupacabra"): true
  - Can't store "=98sdada"

## Avoiding collisions
- Use all digits by multiplying each by a power of 27
- (3 x 27^2) + (1 * 27^1) + (20 * 27^0) = 2234
  - This is just a sequence of numbers in base 27 (ie: for the alphabet)
- Index for cat is 2234 in the set
  - Using base 27, no other word will occur at 2234

## DataIndexedStringSet
- What if we want to store strings like "2pac" and "eGg!"
- To understand what value, let's discuss ASCII Standard

### ASCII Characters
- Each possible character is assigned a value between 0 and 127
- 33 to 126 are printable
  - First 32 are non printable (eg: newline)
- For example char character = 'D' is equiv to char character = 68

- We can store strings like 2pac in base 126. This ensure unique values for all possible English strings
- What if we want to go beyond ASCII (eg: Chinese)
  - Java is also supports other character sets for other languages
  - char character = 40140 is a chinese character
  - This is called as Unicode

## Integer Overflow and Hash Codes
- IN Java, the largest possible integer is 2,147,484,647
  - If you go over this limit, you overflow, starting back at the smallest int
  - In other words, one after 2,147,484,647 is -2,147,483,648

### Consequences of Overflow: Collisions
- Because Java has max integer, we won't get the number get exist
- Base 126 overflows for short strings eg: Omens = 28,196,917,171

### Hashcodes and Pigeonhole Principle 
- Official term of what we are computing is "hash code"
- Our target set is the set of Java integers, which is ~4.3 billion

- Pigeonhole principle tells us that if there are more than 4.3 billion possible items, multiple items will share a hash code
  - If we have 4.3 billion boxes, and a larger number of strings (pigeons), there will be at multiple strings in each box
  - Collision are inevitable

### Two Fundamental Challenges
- Collision Handling
- How do we compute a hash code for arbitrary objects
  - For strings, this is relatively straightforward (base 27, base 126 for ascii)

## Hash Tables: Handling Collisions
### Resolving Ambiguity
- Pigeonhole principle tells us that collisions are inevitable due to integer overflow
  - moo and Nep might both be 718

- Suppose N items have the same numerical representation
  - Instead of storing true in position h, store a bucket of these N items at position h
- How do we implement "bucket"
  - Linked List, ArrayList, Array Set, doesn't really matter


### Separate Chaining Data Indexed Array
- Each bucket in our array is initially empty.
- When an item x gets added at index h
  - If bucket is empty, we create a new list contain x and store it at index h
  - If h is already a list, add x to list if it is not already present
- We might call this a separate chaining data index array

### Separate Chaining Performance
- contains and add will be Theta(Q) where Q is length of longest list

### Saving Memory Using Separate Chaining
- We don't really need billions of buckets
- If we have 10 buckets, put it in bucket = hashCode % 10

### THe Hash Table
- What we've just created here is called a hash table
  - Data is converted by hash function into an int representation called hash code
  - Hash code is then reduced to a valid index (eg: % 10), usually using the modulus operator
- See slide for flow chart/graph

## Hash Table Performance
### Hash Table Runtime
- Good news: we use way less memory and can now handle arbitrary data
- Bad news: Worst case runtime is now Theta(Q), where Q is length of longest list

### Hash Table Runtime
- For a hash table above with 5 buckets, give the order of growth of Q with represent to N
- In best case, length of longest lsit will be N/5
- Worst case, it will be N
- Therefore, in both cases, Q(N) is Theta(N)

### Improving the Hash Table
- Suppose we have:
  - A fixed number of buckets M
  - An increasing number of items N

- Major problem: Even if items are spread out evenly, lists are of length Q = N/M
- How can we improve our design to guarantee N/M is Theta(1)
  - Make N and M the same by allowing for an increasing number of buckets

### Hash Table Increasing Buckets
- Suppose we have:
  - An increasing number of buckets M
  - An increasing number of items N
- As long as M = O(N), then O(N/M) = O(1)

- Example strategy: When N/M is >= 1.5, double M
  - We call this resizing
  - We call N/M the load factor or how full the hash table is

- M is the modulus or modding function
  - When we double M, we a modding for 4 to modding by 8

### Resize Runtime
- Resize takes Theta(N) time. Have to redistribute all items
- Most add operations will be Theta(1), om will have Theta(N) time to resize
  - Similar to our ALists, as long as we resize by multiplicative factor, average runtime is still Theta(1)

## Hash Tables in Java
### Ubiquity of Hash tables
Hash tables are popular implementation of sets and maps
- Great performance in practice
- Don't require items to be comparable
- Implementation is simple compared to RB Trees

- How does Java HashMap know how to compute each object's hash code
  - We don't require Objects the implement Hashable
  - All Objects must have .hashCode() method

### Using Negative hash codes in Java
- Unfortunately -1 % 4 = -1 will result in index errors
- Use Math.floorMod instead -> Math.floorMod(-1,4) = 3

### Two important Warnings
1. Never store objects that can change in HashSet or HashMap
   - If an object's variables changes, then its hashCode changes, may result in items getting lost
2. Never override equals without also overriding hashCode
   - Can lead to items getting lost and weird behaviour

### Typical Hash Code Base is a small prime
- Prime
  - Never even, avoids overflow issues
  - Lowe chance of resulting hashCode having a bad relationship with the number of buckets
- Why small?
  - Lower cost to compute