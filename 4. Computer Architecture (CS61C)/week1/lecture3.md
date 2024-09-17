# Lecture 3 - C Arrays, Strings & Pointers
## Review
- Pointers hold addresses
    - Address vs Value
    - Allow for efficient code but prone to error

- C functions pass by value
    - Passing pointers circumvent this

## C Operators
Syntax: . to access structure & union members
        -> to access structure & union members through pointer

Usual assignment and equality
a = b is assignment
a == b is equality

## Arrays
- Modern machines are byte addresabable
- Memory is composed of byte/8 bit storage cells, each has unique address
- C pointer is an abstracted memory address
- Type declaration tells compilers how many bytes to fetch on each access through pointer
- 

### sideof()
- Integer and pointer sizes are machine dependent, how do we tell
- sizeof() operator returns size in bytes of a variable
- acts different with array and structs
    - arrays returns the size of the whole array
    - struct returns size of instance of struct

- padding are empty bytes that between different struct variable addresses, so the data can be "aligned"

### Array Basics
int ar[2]; declares a 2-element integer array
int arr[] = {785, 635}; declares and initialises a 2 element array

Accessing elements ar[index], same as java and python

- An array in C does not know its own length, and its bounds are not checked
- Mistakes with array bounds causes segmentation faults and bus errors
    - Be careful! These are difficult to find

### Accessing an array
-  Array size n: access entries 0 to n-1
- Use seperate variables for array declaration and array bound to be reused

bad pattern, hardcoding
int ar[10];
for (int i; i<10; i++) {...}

better pattern
cont int ARRAY_SIZE = 10 <- Array size source of truth
int ar[ARRAY_SIZE]
for (int i=-; i < ARRAY_SIZE; i++)

Arrays are almost identical to pointers
- char *buffer and char buffer[] are nearly identifical declarations
- KEY CONCEPT: An array variuable look slike a pointer to the 0th element
- ar[0] same as *ar; ar[2] same as *(ar+2)
- We can use pointer arthmetic to conviently access arrays

- An array variable is read-only (No assignment!)

- ar[i] is treated as *(ar+i)

- A useful abstraction may be to think of int *p as p[0]
- If I print this, it gives me the index in memory ie: p[0]
- If I print *p (value at p[0]), it gives me an integer
- Everything is an array, but ints are array of size 1

so this example
int *p, a[4], x;
p = &x
*p =1 ie p[0] = 1
this means x = 1

- Note: arrays are not strictly variables, they are an abstraction on a sequence of variables and memory addresses
- Array size gets lost when passed to a function, it on gets a passed the sequence of variables/addresses in memory
- If I call sizeof() array in function scope, it returns sizeof first element
