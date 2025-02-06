# Discussion 4 - RISC-V
## Overview
- Assembly Code is Archeicture Specific
- x86 has different assembly lang to ARM
- Simple Instructions, with Complex Expressions out of small parts


## 
## Calling Convention
## Lists

## Question 2: C to RISC-V
C code example
int x = 5, y[2];
y[0] = x;
y[1] = x * x;

Assembly Code after Compilation
x -> s0, &y -> s1
addi s0, x0, 5 # x0 is always zero, so this is setting reg s0 = 5
sw s0, 0(s1) # save word to location s1 with offset 0
mul t0, s0, s0 # t0 = 5 * 5 
sw t0, 4(s1) # save word to location s1 with offset 4 (ie: sizeof int)

## Question 3: Registers
In RISV-5, we have two methods: main memory and registers
Registers are much faster, but only have 32 bits
Always use named registers (s0, not x8)

x0 (alt zero) = zero
ra = return address register
sp = stack pointer, where stack ends
t0-t6 = temp registers
s0-s11 = saved registers
a0-a7 = arg registers, a0-a1 are also return value
Full table in disc sheet, all registers have an x address from x0-x31

Convert each instruction to other form:

add s0, zero, a1 -> add x8, x0, x11
or x18, x1, x30 -> or s2, ra, t5

## Question 4: Basic Instructions
Good summary table on disc sheet

An array in memory contains int* arr = {1,2,3,4,5,6,0}. Index 0 is held in s0

a. lw t0, 12(s0)  -> this is loading arr[3], which is 4
b. sw t0, 16(s0)  -> this is setting arr[4] = t0, which means array is now {1,2,3,4,4,6,0}

c. slli t1, t0, 2 -> shift to left by i=2, t0 = 4, t1 = 4 << 2 or 0100 << 2 = 10000 = 16
   add t2, s0, t1 -> t2 = s0 + t1 -> t2 = s0 + 16 -> t2 = arr[0 + 16/sizeof(int)] -> t2 = arr[4] -> 4 
   lw t3, 0(t2)   -> t3 = 4
   addi t3, t3, 1 -> t3 = 4 + 1 = 5
   sw t3, 0(t2)   -> sw t3, 0(t2) -> sw t3, 0(pointer that t2 points to) -> sw t3, arr[4] = 5
arr is now: {1,2,3,4,5,6,0}

## Question 7: RISC-V Calling Convention
If we have t0 before and after a function, we shouldn't expect t0 to be the same
This is because t0 may be used in the func

7.1 How do we pass arguments into functions
a0-a7 argument registers

7.2 How are values returned by functions
a0, a1

7.3 What is sp and how should it be used in the context of RISC-V functions?
Stack pointer, used to store values in memory 