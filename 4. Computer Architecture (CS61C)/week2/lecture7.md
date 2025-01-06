# Lecture 7 - More RISC-V, RISC-V Functions
## Review of Last LEcture
- RISC Design Principles
    - Smaller is faster: 32 registers, fewer instructions
    - Keep it simple: rigid syntax
- RISC V Registers: s0-s12, t0-t6, x0
    - No data types, just raw bits. Operations determine how bits are interpreted
- Memory is byte-addressed
    - No types! No automatic pointer arithmetic
- RISV-V Instructions
    - Arithmetic: add, sub, addi, mult, div (i is immediate, constant integer)
    - Data Transfer: lw, sw, lb, sb, lbu (not intuitive at first)
        - How to transfer data from CPU to RAM. Assembly runs on CPU. We load in assembly then store data in memory
    - Branching: beq, bne, bge, blt (used for if statements)
        - Unconditional branching (jumps, useful in loops): jal, j, jalr, jr

## Sign Extension Practice 
### Sign in Two's Complement
- How do we know if a binary two's complement number is negative
- Binary: 0b1000 0010 is neg, 0b0111 1111 is positive
    - The first bit indicates the sign
### Sign Extension
- If we want to take a 8 bit two's complement number and make it a 9-bit number, how would we do so?
- Naive: put zero at the front eg: 1111 1110 -> 0 1111 1110 -> but we lost the negative
- Answer: We replicate the most significant bit!
    - 0b 0000 0010 -> 0b 0 0000 0010 -> 0d (2)
    - 0b 1111 1110 -> 0b 1 1111 1110 -> 0d (-2) -> this is the same representation of -2 in both 8 bit and 9 bit

### Arithmetic Sign Extension
When doing math, immediate values are sign extended. Immediates can only be represented by 12 bits though!
addi t0, x0, -1 == addi t0, x0, 0xFFF
but t0 is 32 bits, so it is then converted to 0xFFFF FFFF before being added to t0

and check out this: t0, x0, 0xF77 in t0 is 0xFFFF FF77 (replicate the most signigicant bit all the way to the start!)

### Loading Sign Extension
- For assembly, this happens when we pull data of memory
- Byte in memory:
    - 0b1111 1110 (-2)
- Load byte -> register contents
0b XXXX XXXX XXXX XXXX XXXX XXXX 1111 1110
What do we do with the x values, we sign extend again
0b 1111 1111 1111 1111 1111 1111 1111 1110

Normal (signed) loads sign extend the most significant bit. Same idea for 0111 0111 -> 0000 0000 0000 0000 0000 0000 0111 0111

### Loading Sign Extension Syntax
Signed Loads
lb t0, 0(s0) -> loading 0b 0001 0001 -> 0000 0000 ... 0001 0001
lb t0, 1(s0) -> loading 0b 1000 0000 -> 1111 1111 ... 1000 0000
lb = load byte

Unsigned Loads do not sign extend, but fill with zero
lbu t0 1(s0) -> loading 0b 1000 0000 -> 0000 0000 ... 1000 0000
lbu = load byte unsigned

## Pseudo Instructions
### Assembly Instructions
A low-level programming language where the program instructions match a particular architecture's operations
But sometimes, for the programmer's beneit, it's useful to have additional instructions that aren't really implemented by hardware
    - Instead translated into real instructions

Example: mv dst,reg1 (ie: copy)
translates to addi dst,reg1,0

Load Immediate (li)
- li dst, imm
- Loads 32-bit immediate into dst
- utilises addi, lui

Load Address (la)
- la dst, label
- Loads address of specified label into dst

No Operation (nop)
- nop
- Do nothing

Even the j instruction is actually a pseudo instruction
Pseudo instructions are core to writing RISC assembly code
In short, convenient renamings of common instructions to make the code more readable

## C to RISC-V Practice
Example 1: Fast String Copy
char *p, *q;
while ( (*q++ = *p++) != '\0');
Copy everything in p to q, until q = p = null terminator (ie end of string)

What do we know about its structure:
Single while loop
Exit condition is an equality test

Converted Example 1
// copy String p to q
// p->s0, q->s1 (char* pointers)
Loop: lb t0, 0(s0)     // t0 = *p    // loadbyte register, offset
      sb t0, 0(s1)     // q* = t0    // storebyte register, offset
      addi s0, s0, 1   // p = p + 1
      addi s1, s1, 1   // q = q + 1
      beq t0,x0,Exit   // if p*==0, go to Exit
      j Loop           // jump back to loop
Exit:

## Functions in Assembly
### Six Steps of Calling a Function
1. Put arguments in a place where function can access them
2. Transfer control to the function
3. The function will acquire any local storage resources it needs
4. Function performs its desired task
5. Function puts return value in an accessible place and "cleans up"
6. Control is returned to you

### Steps 1 and 5: Where should we put the arguments and return values
- Registers way faster than memory, so use them whenever possible
- a0 to a7: eight argument registers to pass parameters
- a0 to a1: two argument registers also used to return values
- Order of arguments matter!
- If need extra space, use memory (the stack)

### Example func in assembly
void main(void) {
    a = 3
    b = a + 1
    a = add(a,b)
}

int add(int a, int b) {
    return a + b 
}

translated

main:
    addi a0, x0, 3
    addi a1, a0, 1
    jal ra, add       # jal = jump to add

add:
    add a0, a0, a1
    jr ra             # jump return, ra = return address


### More Registers
a0-a7: eight argument registers
a0-a1: two registers to return
sp: "stack pointer"
   - Holds the current memory address of the "bottom" of the stack
   - Stack goes downwards, keep track of the last stack location


### Steps 2 and 6: How do we Transfer Control
Jump (j)
Jump and Link (jal) <- used to invoke func
jal dst label

Jump and Link Register (jalr) <- used to invoke func
jalr dst src imm
"and Link": Save the location ofinstruction in a register before jumping

Jump Register (jr)
Used to return from a function (src = ra)
ra = return address register, used to save where a function is called from so we can get back


### Review Question
ret is a pseudocode instruction that can be used to retun from a function. Which real instructions(s) would you use to create ret?
Description: PC = R[1]
jalr x0, ra, 0


### 3: Local Storage for variable
Stack pointer (sp) holds the address of the bottom of the stack
- Decrement it (recall stack grows downwards)
- Then use store word to write to a variable
- To "clean up", increment the stack pointer

Store t0 to the stack
addi sp, sp, -4
sw t0, 0(sp)

## Function Calling Conventions: Function performs its desired task
### Which registers can we use
- Problem: How does the function know which registers are safe to use
- Function A may have been using t0 whn it called function b

### Example: sumSquare
int sumSquare(int x, int y) {
    return mult(x,x) + y
}

What do we need to save?
- Call to mult will overwrite ra, so save it
- Reusing a1, to pass 2nd argument to mult, but need current value (y) later. so save a1


### Calling Conventions
- CalleR: the calling function
- CalleE: the function being called

eg: sumSquare is the caller of mult, and mult is the callee
Register conventions: A set of generally accepted rules as to which registers will be unchanged after a procedure call (jal) and which may have changed

### Saved Registers (Callee Saved)
- These registers are expected to be the same before and after a function call
    - If calleE uses them, it must restore values before returning
    - This means save the old values, use the registers, then reload old values back into registers

- s0-s11 (saved registers)
- sp (stack pointer)
    - If not in same place, the caller won't be able to properly access its own stack variables
- It is the callee's job to save the s registers

### Volatile Registers (Caller Saved)
- These registers can be freely changed by the callEE
    - if callER needs them, it must save those values before making a prodedure call
    - t0-t6 (temp registers)
    - a0-a7 (return address and arguments)
    - ra (return address)
    - These will change if callEE invokes another function (nestered function means callEE is also callER)

### Register Conventions
- CallER Save:
    - Callee function can use them freely
- CallEE saved:
    - The callEE function must save them before modifying them, and restore them before returning

## How do we save registers? The Stack?
- Stack: Local variables and saved registers
- SP moves up and down the stack. The stack pointer is at the bottom

int sumSquare(int x, int y) {
    return mult(x,x) + y
}


sumSquare:
"PUSH" addi sp, sp -8 # make space on stack
       sw ra 4(sp) # save ret addr
       sw a1 0(sp) # save y
       add a1, a0, x0 # set 2nd mult arg
       jal ra, mult # call mult

"POP"  lw a1, 0(sp) # restore y
       add a0, a0, a1 # ret val = mult(x,x) + y
       lw ra, 4(sp) # get ret addr
       addi sp, sp, 8 # restore stack

       jr ra
mult: ...

notice first 2 lines of PUSH and last 2 lines of POP are reversed. Follow this boilerplate for best practice

advice: write everything first, then write your lw, sw, then count how many times you are calling lw & sw, and * 4

### Basic Structure of a Function
Prologue
    func label:
    add i sp, sp, -framesize
    sw ra, <framesize-4>(sp)
    #store other callee saved registers
    # save other registers if need be
Body
    WRITE ME FIRST!!
Epilogue
    # restore other regs if need be
    # restore other callee saved registers
    lw ra, <framesize-4>(sp)
    addi sp, sp, framesize
    jr ra


### Register Conventions IN SUMMARY
- CallER MUST save any volatile registers it is using onto the stack before making procedure/function call
- CallER can trust saved registers to maintain values
- CallEE must "save" any saved registers it intends to use by putting them on the stack before overwriting their values

## Choosing your Registers
- Minimise register footprint
    - Only use registers you absolutely need to 
    - Only save vlaues you absolutely have to
- Function does NOT call another function
    - Only use the t0-t6 registers
    - When using t registers and not calling another func, we don't need prologue and epilogue
    - Be lazy! Use register choices that minimise stack calls
- Function calls other functions:
    - Values you need through s0-s11
