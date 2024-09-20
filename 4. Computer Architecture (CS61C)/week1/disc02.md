# Discussion 2
## Agenda
1. C Primer
2. Pointes
3. Arrays & Strings
4. Memory Hierarchy

## C Primer
- Compiled language, from high level code to architecture specific byte code
    - Fast!

- Date types: int, float, double, char
    - Ints: 4 bytes on 32 bit sys, 8 bytes on 64 bit
    - Char: 1 byte

- Weak typing (cast from any type to another)
    - Works becasue bits are bits
    - 67 in binary can represent int 67 and an ascii char

- Pass-By-Value
    - Pointers are useful
    - We send in the entire variable
    - Use pointers to be more efficient in passing arguments

- Get to know precedence table
- Variables must be declared, if not, they are "garbage"
- No boolean
    - 0/NULL are false-y

## Memory Hierarchy
- Stack: Grows down, this is where we store function frame
          Never return pointer pointing to stack variable
- Heap: Dynamic memory allocation (malloc, realloc)
    - Heaps grow up, nothing as complex as heaps in CS61B
- Static: Global consts, static variables
    - String literals, but not char arrays
- Text: Code!