# Lecture 4 - C Memory Management and Usage
## Review
- Pointers and arrays are very similar
- Strings are just char pointers/arrays with a nll terminator at the end
- Pointer arithmetic moves the pointer by the size of the things it's pointing to
- Pointers are the source of many C bugs!
- Week 1: Still focusing on High Level Languages, such as C

## C Memory Layout
- Program's address space contains 4 regions
    - Stack: Local variables, grows downward for FFFFFFFF hex
    - Heap: space requested via malloc() and used with pointers. Resizes dynamically, grows upward
    - Status Data: Global and static variables, does not grow or shrink
    - Code: loaded when the program starts, does not changes
- OS prevents access between stack and heap via virtual memory
    - Prevents corrupting of variables if they overlap


### Where do Varaibles Go?
- Declared outside a function
    - Static data
- Declared inside a function
    - Stack
    - main() is a function
    - freed when function returns
- Dynamically allocated
    - Heap
    - ie: malloc

- Example: 
include <stdio.h>

int varGlobal;

int main() {
    int varLocal;
    int *varDyn = malloc(sizeof(int))
}


Breakdown
- varGlobal& main are in static data 
- varLocal is on the stack (declared inside function)
- *varDyn is on heap, because malloc

### The Stack
- Each stack frame is a contigious block of memory holding the local variables of a single procedure
- A stack frame includes:
    - Location of caller function
    - Function arguments
    - Space for local variables
- Stack pointer tells where lowest stack frame is
- When procedure ends, stack pointer is moved back
    - Frees memory for future stack frames
    - Data remains as garbage!

- Stack is Last in First Out data structure
    - Stack grows downward, and then freed as functions return. Stack pointer moves down which more frames, and moves up as functions return

- Never return pointers local variable from function!

### Static Data
- Place for variable that persist
- Data not subject to comings and goings like function calls
    - Examples: String literals and global variables
    - Global variables declared outside of function
    - String literal example: char * str = "hi"
    - Do not mistake with char str[] = "hi"
        - This will put str on the stack!

### Code 
- Copy of your code goes here
    - C code becomes data too!
- Does (should) not change
    - Typically read only

## Adressing and Endianness
### Addresses
- The size of an address (an therefore the size of poiunter) in bytes depnds on architecture (eg: 32-bit Windows, 64-bit Mac OS)
    - eg: 32-but has 2^32 possible addresses
    - In this class, assume every machine is 32 bit machine unless told otherwise

- If a machine is byte addressed, then each of its addresses pointts to a unique byte
- Q: on a byte addresses machine, how can we order bytes of an integer in memory?

### Endianness
- Big Endian: Descending numerical significance with ascending memory addressing
- Little Endian: Ascending numerical significance with ascending memory addresses

- In what order are the bytes within a data type stored in memory?
- For example, 4 bit integer "28" stored at address 20
    - 28 = 0x 00 00 00 1C

    16  20  24
    ""  28  ""

19  20  21  22  23  24
""                  ""  4 bit allocation for integer, between 20 - 23
    1C  00  00  00      Little Endian, ascending numeric significance with ascending address
    00  00  00  1C      Big Endian, descending numerical significance with ascending address
    
In this class, we will assume little endian unless otherwise

### Common Mistakes
- Endianess ONLY APPLIES to values that occupy multiple bytes
- Endianness refers to STORAGE IN MEMORY NOT number representation
- char c = 97, this is 1 byte
    - So c == 0b01100001 in both big and little endian, as it isn't accross bytes
- Arrays and pointer still have the same order, regardless of endianness

## Dynamic Memory Allocation & the Heap
### Dynamic Memory Allocation
- Want persisting memory (like static) even when we don't know size at compile time
    - eg: input files, user interation
    - Stack won't work becasue stack frames don't persist

- Dynamically allocated memory goes on the heap
    - More permanent than Stack
- Need as much space as possible without interfering with Stack
    - Start at opposite end and grow towards stack

### sizeof()
- If integer sizes are machine depdendent, how do we tell?
- Use sizeof() operator
    - Return size in number of char-sized units of a variable or data type name
    - examples, int x; sizeof(x); sizeof(int);
    - sizeof(char) is ALWAYS 1

### sizeof() and Arrays
- Can we use sizeof to determine length of an array
- Generally to get the number of elements, I could do: sizeof(a) / sizeof(int)

### Allocating Memory
- malloc(), calloc() and realloc()
- malloc(n)
    - Allocates a bontinous block of n bytes of uninitalize memory (contains garbage)
    - Returns a pointer to the beginning of the allocated block; NULL indicates failed request
        - ALWAYS CHECK THIS
    - Different blocks not necessarily adjacent

### Using malloc()
- Almost always used for arrays or structs
- Good practice to use sizeof() and typecasting
- int *p (int *) malloc(n*sizeof(int))
    - sizeof() makes code more portable
    - malloc() returns void *; typecast will help you catch coding errors when pointer types don't match
- Can use array or pointer syntax to access

### Releasing Memory
- Release memory on the Heap using free()
    - Memory is limited, release when done
- free(p)
    - Pass it pointer p to beginning of allocated block; releases the whole block
    - This pointer is given by m/c/realloc(), if not this pointer, throws system exception
    - Don't call free() on a block that has already been released
    - Makre sure you don't lose the original address
        - p++ is a BAD IDEA; use a seperate pointer

### calloc(size_t nmemb, size_t size)
- nmemb = number of members
- size_t = size of each member
- Like malloc, except it inialises memory to 0

### realloc
- What happens when I need more or less memory in an array
- void *realloc(void *ptr, size_t size)
    - Takes in a ptr that has been the return of malloc/calloc/realloc and a new size
    - Returns a pointer with now size space (or NULL) and copies any contents from ptr
- realloc can move or keep the address the same
- DO NOT RELY ON OLD POINTER VALUES, always use the pointer returned by realloc

### Memory Errors
- Segmentation Fault
    - Attempts to access memory not allocated to it
    - Common in CS61C

- Bus Error
    - Invalid address alignment
    - Less common in CS61C

### Common Memory Problems
1. Using uninitalised values
2. Using memory that you don't own
    - NULL or garbage data as pointer
    - Deallocated stack or heap variable
    - Out of bounds reference to stack or heap array
3. Freeing invalid memory
4. Memory leaks

### Using unitalised values
void foo(int *p) {
    int j;
    *p = j; <- j is unitialised (garbage), copied to p
}

### Using Memory you don't own
- Common, trying to interact with a NULL
- eg: NULL->next, there's no pointer coming from NULL

## Memory Leaks
- Remember Java has garbage collection but C doesn;t
- Memory Leak: when you allocate memory but lose the pointer necessary to free it
- Rule of Thumb: More mallocs than frees probable means a memory leak
- Potential Memory Leak: Changing pointer, do you still have a copy to use free later?

### Debugging Tools
- Valgrind
- Runtime analysis tools for finding memory errors
- Dynamic analysis tool: collects information on memory management while program runs
- No tool is guaranteed to find ALL memory bugs; this is a very callenging programming language research problem

## C Wrap-Up: Create a Linked List
- We want to generate a linked list of strings
- This example uses structs, pointers, malloc() and free()

struct Node {
    char *value;
    struct Node *next; <- The link of the linked list, pointer to next Node
} node;

- Want to write addNode to support functionality as shown:
char *s1 = "start", *s2 = "middle", *s3 = "end" (stored in static memory as string literals)
struct node *theList = NULL <- end of list
theList = addNode(s3, theList);
theList = addNode(s2, theList);
theList = addNode(s1, theList);

node *addNode(char *s, node *list) {
    node *new = (node *) malloc(sizeof(NodeStruct));
    new->value = (char *) malloc (strlen(s) + 1);
    strcpy(new->value, s);
    new->next = list
    return new;
}

node *deleteNode(node *list) {
    node *temp = list->next;
    free(list->value);
    free(list);
    return temp;
}