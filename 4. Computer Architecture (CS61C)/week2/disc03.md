# Discussion 3
## Question 1 - Precheck
1.1 False: FP aim to represent a large range of value, a high amount of precision and real arithmetic results (unless errors due to rounding)
1.2 True: as you go up the number line, the exponent increases significantly increasing the gaps
1.3 False: Floating points are an approximation, so cuts off certain vlues. This means order matters


## Review
Floating Point Representation: 

1 bit for sign
8 bit for exponent
23 bit for significand

Formula for Normalised Floats: (-1)^sign * 2^(Exp-Bias) * 1.significand
For Denorm:  (-1)^sign * 2^(Exp-Bias) * 0.significand

## Question 2
### 2.1 - How many zeros can be represented using a float?
2 => we have a positive + negative 
We can represent (2 * 2^23)^64
2 for the sign
2^23 for the signficant
8 for the exponent

### 2.2. What is the largest finite positive vlaue that can be stored using single precision float?
Signed bit: 0 (+)
Exponent: 1111 1110 = 254
Significand: 111 1111 1111 1111 1111 1111
bias = -(2^(n-1)-1)^-127
Result: 0x 7F7FFFFF = (1 + (1-2^-23)) * 2^(254-bias=127)
= (1 + (1-2^-23)) * 2^(127)

### 2.3 Smallest positive normalised val that can be stored
Signed Bit: 0 (+)
Exponent: 0000 0001 = 1 (0 gives denorm)
Exponent after bias: = -127
Significand: 000 0000 0000 0000 0000 0000



## 3. More Floating Point Representation
Not every number can be represented using floating point as it's base 2. For instance, 1/3 can only be approximated and thusmust be rounded in any attempt to represent it

### 3.1 What is the next smallest number larger than 2 that can be represent completely
Represent 2 as floating point

(-1)^0 * 2^1 * 1.00....0
Add the smallest representable number to 2
ie: add (2^-(23)+1)*2^1 = 2 + 2^-22

### 3.2 Smallest number larger than 4 that can be represented
Same logic as before 
(1+2^-23)*4^1 = 4 + 2^-21

Notice that the power is different, so the smallest number depends on the base

Notice the increase is 2^-22 * 2^21 = 2 ie: double the step

### 3.3 Define the sepsize to be the distance between some value x and the smallest value larger than x can be completely represent. What is the step size for 2? 4?
The step size for 2 is 2^-22
The step size for 4 is 2^-21

### 3.4 Can we generalise for normalised values?
Format: exponent + exponent * y
Our Number: 2^(x-127) + 2^(x-127) * y
Next Number: 2^(x-127) + 2^(x-127) * y + ___
2^(x-127) + 2^(x-127) * y + 2^-23*2^(x-127)

