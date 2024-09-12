# Lecture 2 - The C Programming Language & Points
### Review
- Take the 4 bit number x = 0b1010
- Which number does x not represent
- Unsigned it represents = 10
- Two's complement, it represents = -2^3 + 2^1 = -6
- Sign and Magnitude it represents -2
- So from the options [-4, -6, 10, -2] it can't represent -4
- ^ confirmed correct by lecture

- In biased notation, we get 2^3 + 2^1 - (2^3-1) = 3
- One's complement, we flip the bits 0b0101 = 5 -> x = -5
- Two's complement, you can also flip the bits and add 1
    eg: 0101 = 5 = 5+1 = 6 -> x = -6

### Overview - Coming Lectures
- Higher Level Languages: Python and Java
- Lower Level, but still high level: C
- C maps closely to assembly
- We learn assembly, then we learn machine language

- For this week, we are focusing on C

### Introduction to C
- C is not a "very high level" language, nor a big one.
    - But it's abssence of restrictions makes it more convenient and effctive for many tasks than other languages

- With C, we can write programs that allow us to exploit underlying features of the architecture
- We have to manually manage the memory, unlike Python
- We will implement a Python Library in C (NumC)

### C Concepts
- Compiler: Creates useable programs from C source code
    - Translates your code into machine code
- Typed variables: Must declare teh kind of data the variable will contain
- Typed functions: Must declare return type
- Header Files: Allows you to declare functions and variables in separate files
- Structs: Groups of related values - very primitive class, only allows data, not methods
- Enums: List of predfined values
- Pointers: Aliases to other variables

## Compilation
- C is a compiled languaged
- C compilers map C programs into architecture specific machine code (strings of 0s and 1s)
- Unlike Java, which converts to architecture-indepdent bytecode run on the JVM
- Unlike Python, which direcly interprets the code
- Main difference is WHEN your program is mapped to low-level machine instructions

### Compilation Advantages
- EXcellent run-time performance, optimises for given architecture
- Fair compilation time: enhacements in compilation procedure (makefiles) allows us to recompile only the modified files

### Compilation Disadvantages
- Compile files including the exe are architecture specific (CPU type and OS)
    - exe must be rebuilt on each new system
    - "Porting" your code to new architecture
- Edit -> Compile -> Run -> Repeat iteration cycle can be slow

## Vriable Types
### Typed Variables in C
int x = 2
float y = 1.618
char z = 'A'

Types:
- int
- short int (smaller int size)
- long int (larger signed int)
- char (single text character)
- float
- double (greater precision FP number)

- interger sizes are machine dependent! Common size is 4 or 8 bytes (32-64 bit), but can't ever assume this
- Can add "unsigned" before int or char

### Characters
- Encode characters as numbers, same as everything
- ASCII defines 128 different chars
- Char takes up 1 byte of space
- 7 bits is enough to store a char (2^7 = 128 different characters), but we add a bit to round up to 1 byte since computers usually deal with multiples of bytes

### Typecasting in C
- C is a weakly typed language
- You can explicitly typecast from any type to another
- This possible because everything is stored as bits
    - Bitz are bitz!!
    - More freedom, but easier to shoot yourself in the foot
- Be careful with type casting

### Types Functions in C
prototypes
int my_func(int,int)
void sayHello()

definitions
int my_func(int x, int y) {
    sayHello()
    return x * y
}

void sayHello() {
    printf("Hello\n")
}

- Declaring the prototype of a function allows you to use before the function defintion (notice sayHello() was defined after my_func, but myfunc calls it)

- Prototypes are usually in a headerfile in a different file

### Structs in C
- Way of defining compound data types
- A structured group of variable, possibly including other structs
- Think fo it as an instruction to C on how to arrange a bunch of bits in a bucket

- typedef struct {
    int lengthInSeconds
    int yearRecorded
    song
}

- Song song 1
- song.lengthInSeconds = 213

- C does not have class! C++ does

### Structs Alignment and Padding
- They provide enough space and align the data with padding
Struct foo {
    int a;
    char b;
    struct foo *c;
}
- The actual layout on a 32 bit architeture would be:
    - 4 bytes for a (int)
    - 1 byte for b (char)
    - 3 ununsed bytes (pad rest of them, so char + unused = 4 bytes)
    - 4 byes for C
    - sizeof(struct foo) == 12
    - Pointers on 32 bits are 4 bytes (a byte is 8 bit)

### Unions in C
- A union is also an instruction on how to arrange a bunch of bits
- union foo {
    int a;
    char b;
    union foo *c;
}
- Provides enough space for largest element
union foo f
f.a = 0xDEADB33F (treat f as an interger and stor that value)
f.c = &f (treat f as a pointer of type "union foo" and store the address of f in iteself)


### Differences between C and Java
Type of Language: Function vs Object Oriented
Programming Unit: Function vs Class or ADT
Compilation: Creates machine dependent code vs Creates machine independent bytecode
Execution: Loads and executes the program vs JVM interprets the bytecode
Memory Management: Manual (Malloc, free) vs Automatic Garbage Collection


## C Syntax and Control Flow
- Java and C operators are nearly identical
- Only differences is member selection: ., ->
                   and cond evaluation: ? for ternary

### Generic C Program Layout
#include <system_files>
#include "local_files"
#define macro_name macro_expr
/* declare functions */
/* declare global variables and structs */
int main(int arg c, char *argv[]) {
    return 0 
}

- you must return 0 and return type must be int as this is the exit code

### C Syntax: main
- to get arguments to the main function, use
  int main(int argc, char *argv[])

-argc = argument count
-argv = argument value is an array containing pointer to the arguments as strings

eg: ./foo hello 87
argv[0] = "./foo"
argv[1] = "hello"
argv[2] = "87" ITS A STRING NOT AN INT!!

### C Syntax: Variable Declarations
- All variable declarations must appear before they are used
- Variable may be initialized in its declaration, if not, it holds garbage!
- Variables of the same type may be declared on the same line:

Correct: int x;
         int a, b=10, c;

Incorrect: short x=1, float y=1.0
           z = 'c'

### C Syntax: True or False
- No explicit boolean type in C
- FALSE in C? is 0 and NULL
- TRUE in C: anything that isn't FALSE
- Same idea as scheme, only #f is false

### C Syntax: Control Flow
if (exp) {statement}
if (exp) {statement1}
else (statement 1)
NO ELIF!!!

while (expression) {
    statement
}
do {
    statement
} while (expression)

for (initialize; check; update) {
    statement
}

switch (expression) {
    case const1 statements
    case const2 statements
    default:
}
break

### Switch and Break
- Case statement switch requires proper placement of break to work properely
- Fall through effect: will execute all cases until break is found
switch (ch) {
    case '+': ...
    case '-': ... break
}

## Pointers
### Address vs Value
- Consider memory to be a huge single array
- Each cell/entry of the array has an address
- Each cell also stores some value
- Don't confuse the address referring to a memory location with the value stored there
- Think of address as a key, value is the value

eg:
[101] [102] [103] [104] [105] ADDRESSES
[]    [23]    []     []    [] VALUES
[]    [foo]   []     []    [] VARIABLES

102 is the cell address
23 is the value in cell 102
foo is the variable name for value 23 at address 102

### Pointers
- A pointer is a variable that contains an address
- An address refers to a particular memory location, usually also associate with a variable name
- Name comes from thef act that you can say it points to a memory location

- in box 106, I could do this:
  [102]   [106]
  [23]    [102]
  [foo]   [bar]

- and now bar is pointing at memory address 102, which is pointing at value 23

### Pointer Syntax
- int *bar
- Decalre variable bar as address of an int

bar = &foo
- Assigns address of foo to bar
- & is called the address operator in this context

spam = *bar
- Assigns the value at address in bar to spam
- * called the dereference operator in this context

### Pointer Example
int x
int p*
x = 3
p = &x (p is an arrow at x ie: p -> x -> 3)
*p = 5 this means x = 5 (ie: p -> x -> 5)
y = p*, so y -> 5 AND p -> x -> 5 (BUT X AND P DON'T POINT AT Y)

### Pointer Types
- Pointers are used to point to one kind of data
- Pointers can point at points eg: int **pp

- Exception is the type void *, which can point to anything (generic pointer)
- Use sparingly to help avoid program bugs and other bad things

Functions can return pointers
- Placement of * does not matter to the compiler, but it might to you

- int* x is the same as int *x

int *x, y, z is the same as int* x,y,z
BUT not the same as int *x, *y, *z
basically, the space placement doesn;t matter int* x (* ) is int *x ( *)

### Pointers and Parameter Passing
- Java and C pass parameters "by value"
- Procedure/function/method gets a copy of the parameter, so changing the copy does not change the original

void addOne (int x) { x = x + 1 }
int y = 3;
addOne(y); <- y remains equal to 3

BUT, lets say I want to change the value.
I can pass by ference, function accepts a pointer and then modifies the value by dereferencing it

void addOne (int *p) {
    *p = *p + 1;
}

int y = 3
addOne(&y) <- y now equals 4

&y is a pointer/arrow at y


### Pointers in C
Why use pointers?
- When passing a large struct or array, it's a lot faster to pass a pointer than a copy
- In general, pointers allow cleaner, more compact code

Careful: Pointers are likely the single largest source of bugs in C
- Most problematic with dynamic memory management (covered later)
- Memory leaks and dangling references

### Pointer Bugs
- Local variables in C are not initialized, they may contain anything (aka Garbage)
- Declaring a pointer just allocates space to hold the pointer
    - It does not allocate the thing being pointed to

### Summary
C is an efficient compiled language, but leaves safety to the programmer
- Weak type safety
- Variables not auto-initialised

Points is a C abstraction of a data address
- Each memeory location has an addres and a value stored at it
    - Think of it like a massive dictionaru
- * follows a pointer to its values
- & gets the address of a value/variable

- C functions "pass by value" (same behaviour as Java, Python)
