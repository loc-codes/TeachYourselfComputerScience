# Lecture 6 - Introduction to RISC-V

## Review of Last Lecture
- C Memory Layout
    - Stack: local variables, grows downwards
    - Static data: global variables
    - Code: Machine instructions
    - Heaps: Dynamic storage using malloc and free
        - Must be used carfully

## Assembly - Assembly Language
- A low level programming language where the program instructions match a particular architecture operations
- Splits a program into many small instructions that each do one single part
    - C: a = (b+c) - (d+e)
    - Assembly: add t1, s3, s4
                add t2, s1, s2
                sub s0, t2, t1

- Each architecture has a different set of operations that it supports
- Assembly is not portable
- Mainstream Instruction Set Architectures
    - Intel & AMD: x86 -> Super long and difficult
    - ARM: Arm Architecture -> 
    - RISC-V: Versatile and open source, designed for cloud computing & academic instruction

- RISC-V = Reduced Instruction Set Computer v5

### What instructions should assembly include?
- Add, subtract, bit shift
- Read and write memory
- But what about?
    - Only run the next instruction if these two values are equal
    - Perform four pairwise multiplicates simultaneously
    - Add 2 ascii numbers together ('2' + '3' = '5')

### RISC Dominates Modern Computing
- History: CISC, complete instruction set, add a new command for each new operation
- RISC dominates modern computing
- RISC-V is 32-bit, 64-bit and 128-bit


## Registers
### Hardware uses registers for variables
- Unlike C, assembly doesn't have variable
- Instead assembly uses registers to store values
- Registers are:
    - Small memories of a fixed size (32-bit in our case)
    - Can be read or written
    - Limited in number (32 registers on our system)
    - Very fast and low power to access

### Registers vs Memory
- What if more variables than registers
    - Keep most frequently used in registers and move rest to memory
    - Called spilling to memory
- Why are not all variables in memory
    - Smaller is faster: 100-500 times faster
    - Memory Hierarchy:
        - Registers: 32 registers * 32 bits = 128 bytes
        - RAM: 4-32 GB
        - SSD: 100-1000gb

## Great Idea #3: Principle of Locality/Memory Hierarchy
- Regusters are on the CPU
- CPU Cache
- Physical Memory/RAM
- Solud State Memory: More Volatile flash based memory (can unplug and memory is held)
- Virtual Memory: File based memory

- Top is faster, bottom is slower

## Risc V - How many registers
- Tradeoff between speed and availability
    - More registers -> can house more variables
    - simultanuously: all registers are slow
- RISV has 32 registers (x0 - x31)
    - Each register is 32 bits wide and holds a word
    - A word is a fixed sized piece od data handled as a unit by the instruction set

### RISCV Registers
- Register denoated by 'x' can be reference by number (x0-x31) or name
- Registeers that hold programmer variables
    - s registers are called save registers, holds programmer variables
    - t registers are called temp registers, holds temp vars
- OTher registers have special purposes we'll discuss later

- Registers have no type, type is a C concept
    - Operation neing performed determines how register contents are treated

## A Special Register
- Most important number in programming? 0
- The Zero register: 0 appears so often in code and is so useful that it has its own register
- Register zero (x0 or zero) always has value - and cannot be changed!
    - any instruction writing to x0 has no effect

## Registers Summary
- In high level languages, number of var only limited by available memory
- ISAs have a fixed, small number of operands called registers
    - Special locations built directory into hardware
    - Registers are EXTREMELY FAST
    - Drawback: Operations can only be performed on these predetermined number of registers