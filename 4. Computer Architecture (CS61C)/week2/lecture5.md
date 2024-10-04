# Lecture 5 - Floating Points
## Floating Point
### Number Representation Revisit
- Given 32 bits, we can represented
    - Signed and Unsigned
    - 4 Characters (ASCII)
    - Instructions & Addresses
- How do we encode the following?
    - Real Numbers (eg: 3.14159)
    - Very large and very small numbers  (eg: 6.626*10^-34)
    - A: Using Floating Points

### Reasoning about Fractions
- Why can't we represent fractions?
    - The lowest power of 2 is 2^0, so the smallest difference between any two numbers is 2^0 = 1
    - So we can't represent 1/2 because 0.5 < 1

### Represntation of Fractions in Decimal
- Decimal "point" signififes boundary between int and fraction parts
- eg: 6 digit:
12.3406
= 1 * 10^1 + 2^10^0 + 3*10^-1 + 4*10^-2 + 6*10^-4
- Not much range in 4 digits, we can only represent 0 to 99.9999
- But lots of "precision", lowest power is 10^-4

### Representation of Fractions in Binary
- New Idea: Introduce a fixed "Binary Point" that signifies boundary between negative & nonnegative powers
- 0b xx.xxxx
= 2^1+2^0+2^-1+...+2^-4
- Example 10.1010 = 2.625 in decimal
- The smallest difference between two numbers is 2^-4 = 1/16
- Binary point numbers that match the 6-bit format above range from 0 (00.0000) to 3.9375(11.1111)
- Good precision, but range is decreased
- Here we have 6 bits, but ints have 32 bits
- And where do we put the binary point? How do we change it's position

### Secientific Notation (The Answer)
2.625 * 10^23
significand = 2.625
radix (base) = 10 in this instance
exponent = 23
Normalised form: Exactly one digit (non-zero) to left of decimal point (the point floats to be in the standard position)

Alternatives to representation 1/1,000,000,000
Normalized: 1.0*10^-9 (Floating point is always after the x*10^0 digit)
Not Normalized: 0.1*10^-8, 10.0*10^-10

### Scientific Noptation in Binary
1.0101 * 2^1
Computer arithmetic supports this called floating point due to the floating of the binary point
- Declare such variable in C as float

Significand = 1.0101 with BINARY POINT (NOT DECIMAL POINT!)
Radix (Base) = 2
Exponent = 1

- This achieves a good range with good precision with limited significant figures


### Translating To and From Scientific Notation
- Consider 1.011 * 2^4
- Conver to ordinary number, shift the decimal to the right by 4
- Shift 1 = 10.11*2^3
- Shift last 3 = 10110
- 10110two = 22ten

- For negative exponents, shift decimal to the left
1.011*2^-2 => 0.01011 = 0.34375

- Go from ordinary number to scienfitic notation by shifting untiul in normalized form
101.001 => 1.101001 * 2^3
- But how can we represent this in 32 bits? There is a IEEE Binary Floating Point Standard

### Floating Point Encoding: Single Precision
- Use normalized, Base 2 scientific notation
+1.xx...x * 2^yy...y

Split 32 but word in 3 fields
- 1 bit for Sign (1 is negative, 0 is positive)
- 8 bits for exponent: Exponent represents y's
- 23 buts for significand: Significant represents x's
- Key idea: more like Sign & Magnitude than 2's complement

### The Exponent Field
- Why use biased notation for exponent
    - We want floating point numbers to look small when teir actual value is small, so we can compare with int
    - We don't like how -1 looks bigger than 0

- Recall first bit denotes sign
    - Thus floating point resembles sign and magnitude

- Sign  - Exponent - Significand
   1bit -  8 bits  -   23 bits

Use biased noation, but with bias of -127
- Read exponent field as unsigned, add bias of -127 to get actual exponent
- Exponent field: 0-255
- With bias, actual exponent is -127 to 128
- To encoe in biased notation, subtract the bias, then encode in unsigned
- If we had 2^1, exp = 1 => 128 = 1000000two

### The Significan Field

Representation: (-1)^sign * (1.Significand) * 2^(Exponent-127)

1*Significand
- What does this mean
- This of it as: (1+Value of Significan)
- Since signifcand represnts all the negative powers of 2, its total value is always < 1
- Example: 1.0101to = 1+2^-2+2^-4 = 1.3125


### Example

0     011 0111 1   100 0000 0000 0000 0000 0000 two
Sign   Exponent      Significand

=-1^0* 1.1two * 2^(127-127)
  S    signif    exp
= 1.1two * 2^0
1.1two = 1*2^0+1*2^-1 = 1.5ten
Rember, it's NOT 0.1two

### Double Precision FP Encoding
- NExt multiple of word size (64 bits)
- Sign is still 1 bit
- Exponent is still 11 bits
- Significand is 32 of 52
- Doubles give double precision, but no increased range
- Exponent bias is 2^10-1 = 1023
- C variable declared as double

- So exponent is from 1 to 254, we can represnt a floating point
- But what about exponent 0 & 255

### Reprenting Zero
- Using scientific notation formula, we can't represent zero
- eg: 0x0000 0000 is 1.0 *2^-127 != 0
- Special Case: exp and significand arre all zeros
- Two zeros!

#### Representing +- Infinity
- Division by zero
    - Infinity is a number
    - Okay to do fuurther comparsion/operations
        - Eg: inf + 1 = inf
- Representation: Max exponent = 255
- all zero significand

So 
|Exp|Sig|Meaning|
|---|---|-------|
| 0 | 0 | +- 0  |
| 0 |!=0|   ?   |
|255| 0 | +-inf |
|255|!=0|   ?   |

The two ? cases explained below


### Representing NaN
0/0, sqrt(-4), inf-inf
    - Useful for debugging
    - Op(NaN, some number) = NaN
MAx eponent = 255
Non-zero significand


### Repreenting Very Small NUmbers
- What are the normal numbers closest to 0?
- a = 1.0...0two*2^(1-127) = (1+0) * 2^-126 = 1^-126
- b = 1.0...1two*2^(1=127) = (1+2^-23)*2^-126 = 2^-126 + 2*-149

So gap between a and b is 2^-149
And gap between 0 and a is 2^-126

Gap between a and b is bigger than a to 0!
- So we want to reprent numbers between 0 and a
- How? The implicit 1 forces the 2^-126 term to stay
- Solution, take out the leading one


### Denorm Numbers
- No leading 1
- Careful! Implicit exponent is -126 when Exp 0x00
- The "binary point" moves one more bit to the left of the leading bit

### The gaps now
- Smallest denorm: +-0.0...01two*2^-126 = +- 2^-149
- Largest denorm: += 0.1...1two*2^-126 = 2^-126 - 2^149
- Smallest Norm = 2^-126
- All denorm numbers are smaller than norms!

So 
|Exp|Sig|Meaning|
|---|---|-------|
| 0 | 0 | +- 0  |
| 0 |!=0|Denorms|
|255| 0 | +-inf |
|255|!=0|  NaN  |


### Floating Point Limitations
- If X is too large? abs(x) > 2^128
- Overflow! Exponent is larger than can be represented

- What is x too small? (0 < abs(x) < 2^-149)
- Underflow!

If Results run of the end of significand (eg: Pi)
- Rounding occurs and can lead to unexpected results
- FP Has different rounding modes


### Floating Point Gaps
- Does adding 0x0000 0001 always add the same value to the floating point number?
- NO  - it's value depends on the exponent field
ex: 1.0 * 2^2 = 4
    1.0 * 2^3 = 8

- Thus floating points are quite different from number representations learned so far
- Distribution of values is denser toward zero
- Higher exponents, less precision

### Floating Pointer Limits
- FP Addition IS NOT associative
    - Small + Big + Small != Small + Small + Big
    - This is due to rounding errors: FP approximates results because it only has 23 bits for Signifcand
- Despite being seemingly "more accurate", FP cannot represent all integers
- eg: 2^24 + 1 = 16777216 (fp) but 16777217 (actual)
- Because 16777217 is not representable in FP 