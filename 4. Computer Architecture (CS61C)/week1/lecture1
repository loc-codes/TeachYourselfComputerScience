# Lecture 1
## Overview
- How do computer processors and memories work
- Introduction to systems & computer archiecture 

## CS61C for Software Dev
- Know when performance matters (eg: taking advantage of parallelism)
- Understand the differences between programming languages under the hood
- Design large systems and abstractions in hardware
- Security/Memory Leaks
- Design methodology

## Six Great Ideas in Computer Archietecture
- Abstraction
- Technology Trends
- Principle of Locality/Memory Hierarchy
- Parallelism
- Performance Measurement & Improvement
- Depedendability via Redundancy

## Great Idea #1: Abstraction
- High Level Language Program eg: C
-> Assembly Language Program eg: RISCV (assembly language)
-> Machine Language Program RISCV (binary)
-> Machine interpretation
-> Hardward Architecture Description
-> Architecture Implementation
-> Logic Circuit Description

## Number Representation
- We represent number in base 10
    - Mayans use base 20
    - Babylonians use base 59
    - Base 12 for americans
- Computers are in base 2
    - Eletrical signals have a larger range of error in base 2 (round down or round up). Less chance of corruption

## Big Idea: Bits can represent anything!
- Characters (ASCII is 7 bits)
- Numbers in base 10 (eg: 101 = 5)
- Logical values 
- Anything

- If we increment 111 in 3 bits, we go back to 0 (eg: 111 + 001 = 000). Numbers "wrap"

## Signed Representation
- n bits represents 2^n things
- maybe we make 50/50 pos negative.

## Sign and Magnitude.
- Or...
- First bit gives the sign, treat the rest as unsigned
- eg: 001 = +1, 101 = -1
- We have 2 zeros in this scenario: 000 and 100
- Most positive number is 011 = 2^(n-1) - 1 in base ten
- How do we increment?? Doesn't work wll

## Bias Notation
- Like unsigned, but "shift" so zero is roughly in the middle
- eg: 000 = -3
      001 = -2
      010 = -1
      011 = 0
      100 = 1
      101 = 2
      110 = 3
      111 = 4
- Zero is 01....1 = 0 in base ten
- Most neg number = -(2^(n-1)-1)
- Most pos number = 2^(n-1)
- Increment, like unsigned

## One's complement
- New negation procedure, complement the bits
eg: 000 = 0
    001 = 1
    110 = -1
    010 = 2
    101 = -2

- Negatives are eveything in the positve, flipped 
- Zero = 2 zeros again 0...0 and 1...1
- Most neg = -(2^(n-1)-1)
- Most pos = 2^(n-1)-1
- Increment? Really hard

## Two's Complement
- shift the negative #s by 1:
000 = 0
001 = 1
010 = 2
011 = 3
100 = -4
101 = -3
110 = -2
111 = -1

Zero = 000
Most negative: most significant binary = 100 = -2^(n-1)
Most postive: opposite of most neg = 011 = 2^(n-1)-1
Increment: same as unsigned! awesome

## Two's Complement in Summary
- Used by all modern hardware
- Roughly evenly split between positive and negative
- Can still use MSB as sign bit (aha! look at that)
- To negate: Flip the bits and add 1
  eg: +7 = 0000 0111 -> -7 = 1111 1001
- So 5 bits represents -16 to +15 in two's complement

## Overflow in Computers
- Numbers in a computer: Numbers really have infinite digits, but hardware can only store a finite number of them
- Usually ignore leading zeros
- Leftmost is most significant bit (MSB), righmost is least sig bit (LSB)
- Overlfow is when the result of an arithmetic operation can't be represented by finite hardware bits
    - Result is mathemetically incorrect
    - eg: add 1 to largest bit (eg: add 1 to 1111 becomes 0000)
    - Solution: add more bits

## Sign Extension
- Say we want to represent same number as before, using more bits

## Useful things for this course
- Translate Hex -> Binary ONLY
- Traslate Binary -> Decimal
- Direct to Hex to Decimal is really hard, easiest to go hex to binary then decimal

- Powers of 2 to heart (2):
  2^8 = 256, 2^9 = 512, 2^10 = 1024
- 2^10 = kibi, 2^20 = mebi, 2^30 = gibi, 2^40 = tebi, 2^50 = pebi
- 2^60 = exbi, 2^70 = zebi, 2^80 = yobi
- We won't use >2^50 generally

- 2^12 = 2^2 * 2^10
       = 4 kibi
- 8 gibi = 2^3 * 2^30

## Go from Smaller Base to Larger Base
- Base 2 - Base 10
- 0b 1001 -> Base 10
- 0b 2^3*1 + 2^2*0 + 2^1*0 + 2^0*1
- 8 + 0 + 0 + 1 = 9
- Larger -> Smaller: Base 10 to 2
- 14 -> keep dividing by 2
- 14/2 = 7 (0 remainder) LSB
- 7/2 = 3 (1 remainder)
- 3/2 = 1 (1 remainder)
- 1/2 = 0 (1 remainder) MSB
= 0b 1110 <- LSB on RHS

OR 14 = 16-2
so 0b 10000 <- need to carry 2s
  -0b 00010
      ------
      01110

## Binary -> Hex
0b 1001 1000 1111 0011
if I group into bits of 4, I now know how to convert into Hex nibbles
    9    8    F    3   -> 0x 98F3
 
## Hex -> Binary
0x 213
0b 0010 0001 0011

## Binary -> Base 4
0b 11 11 00 10 01
Base 4: 33021

## Binary -> Base 8
0b 1 111 001 001
=  1  7   1   1

##  Binary Conversions
31 -> 0b 011111 (looks like 32, or 32 -1)
32 -> 0b 100000 (flip the sign)
33 -> 0b 100001 (looks like 33, add a bit)

## Bias Notation
Unsigned value + bias = "actual value"
0b 101 = 5
+ bias: -5
----------
5+(-5) = 0 (zero is the actual value)

## Two's Complement, Conversion to Decimal
- Base representation example, 4 bits: 0b 1110 
- 0b 1110 = -2^3 * 1 + 2^2 * 1 + 2^1 * 1 + 2^0 * 0
- 0b 1110 = 2^2 + 2^1 - 2^3
          = -2

## Two's Complement, Flipping Bits and adding 1
0b 1110
0b 0001 (flip)
0b 0010 (add 1) 

0b0010 = 2, if we change the sign in decimal, we get -2!

## Unsigned and Two's Complement Addition
0b  11011
0b  01011
----------
    00110 with a "carry out/overflow" of 1

Basic idea is to "carry" the 1 when we go over base 2, similar to how we carry the tens/hundreds etc digit when adding in base 10

The overflow
0b 11011 = 27 and 0b 01011 = 11
Add them together, we get 38, BUT 00110 = 6. So the carry over of 1, means that we can represent this as 38 (6+32) assuming we have enough bit space

## Two's complement addition
0b  11011  -5
0b  01011  11
---------
            6 (no overflow!)

Two's complement 
- can overflow when
   - add positive and positve
   - add negative and negative number

- cannot overflow when
   - adding pos and neg
   - adding neg and pos

If one of the most significant bits is 1 (negative sign), then we can't overflow


