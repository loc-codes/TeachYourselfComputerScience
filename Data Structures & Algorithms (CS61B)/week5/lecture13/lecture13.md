# Lecture 13 - Asymptotics and Writing Efficient Programs
## Writing efficient programs
- Efficiency comes in 2 flavours
  - Programming cost (course to date)
    - How long does it take to develop your programs
    - How readable/maintainable/debuggable are your programs
    - Majority of cost is in maintenance, not development
  - Execution cost (course part 2)
    - How much time does your program take to execute
    - How much memory is required?

## Example of Algorithm Cost
- Objective: Determine if a sorted array contains any duplicates.
- Silly algorithm, consider every pair, returning true if any match
- Better algorithm, for each A[i] look at A[i+1] and return true when you first see a match
  - False if you get to the end
- Our goal is to characterise the runtime of these two algos

## Techniques for measuring computational costs
- Technique 1: Measure execution time in seconds on the client side
  - Unix time command
  - Good: easy to measure
  - Bad: may require large amounts of computation time
    - results vary with machine, input data etc
- Technique 2A: Count possible operations for an array of size N = 10000
  - make a table, how many times does line 1 occur, line 2 etc
  - this gives a total count of operations in a machine independent way
  - BUT counts are tricky and tedious to compute
    - AND doesn't tell you time
- Technique 2B: Symbolic Count -> instead of hardcoded ints, use n to count operations
  - Again, machine independent 
  - AND tells you how algorithm scales!
  - Even more tedious to compute
- Why is the better algorithm "better"
  - Ok answer: Few operations
  - Better answer: Algorithm scales better
    - Parabolas grow quicker than linear lines

## Asymptotic Behaviour
- In most cases, we care only about asymptotic behaviour (where N gets very large)
  - Social network with billions of users
  - Logging of billions of transactions
  - Encoding of billions of bytes of video data
- Algorithms that scale well have better asymptotic runtime behaviour
- We'll refer to the shape of a runtime function as its order of growth
- See slide table, n=100000 operations, linear n is < 1 second, n^2 is 3 hours

## Intuitive Simplification 1: Consider only the worst case
- Justification: when comparing algorithms, we often care only about the worst case
- For a function that takes something like 100N^2 + 3N + 2N^3
  - The order of growth, the order of growth is cubic as for very large N, 2N^3 matter most

## Intuitive Simplification 2: Restrict attention to one operation
- Pick some representative operation to act as a proxy for the overall runtime
- We call our choice the "cost model"
  - Pick an operation with the largest power

## Intuitive Simplification 3: Ignore low order terms
- Overall runtime doesn't matter for large n, only the cost model

## Intuitive Simplification 4: Eliminate multiplicative constant
- It has no real meaning for large N
- So our worst case order of growth is n^2 for the "worse" algorithm we started with

## Summary of the analysis process
- Construct a table of exact counts of all possible operations
- Convert table into a worst case order of growth using 4 simplifications
- By using our simplifications from the outset, we can avoid building the table at all

## Simplified Analysis Process
- Choose a cost model
- Figure out the order of growth for the count of the representative operation by either
  - Making an exact count and discarding unnecessary
  - Using intuition and inspection to determine order of growth

## Formalising Order of Growth
- Given a function Q(N) we can apply the last two simplifications to yield order of growth
- eg: Q(N) = 3N^3 + N^2
- Order of growth: N^3
- Calc order of growth by working out what happens to N as it goes to infinity

## Big Theta
- Suppose we have a function R(N) with order of growth f(N)
- In Big Theta, we write R(N) C 0(f(n))
- Instead of order of growth, use big theta
- Sandwiched between k1*f(n) <= R(n) <= k2*f(n)

## Big O
- Big Theta can be thought of as equal
- Big O can be thought of as less than or equal
- So we remove the LHS of the sandwich
- R(N) <= k2*f(n)
- O can be thought that R(n) grows less than O at the worse case
