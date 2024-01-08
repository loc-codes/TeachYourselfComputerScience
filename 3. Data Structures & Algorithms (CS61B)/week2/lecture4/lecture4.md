# Lecture 4
## Bits
- Your computer stores information in "memory" -> sequences of binary
- 72 is stored as 01001000, as if H
- True is stored as 00000001

- Each Java type has a different way to interpret the bits
  - 8 primitive types in Java: byte, short, int, long, float, double, bool and char

## Declaring a variable (simplified)
- When you declare a variable of a certain type in Java
  - Your computer sets aside exactly enough bits ot hold a thing of that time
  - eg: Int is also 32 bits
  - eg: double is 64 bits
- Java creates an internal table that maps each variable name to a location
- Java does NOT write anything into the reserve boxes
  - Java does not let you access variables that are uninitialised

## The golden rules of equals
- Given variables y and x
  - y = x copied the bits from x into y
  - eg: x = 2, copies the bits 10
  - eg: y = 3, copies the bits 11
  - eg: y = x, copies the bits 10

## Reference types
- Any non-primitive type is a reference type

## Class Instantiations
- When we instantiate an Object
  - Java allocates a box/frame for bits for each instance
  - The constructor then fills the box with values

## Reference Type Variable Declarations
- When we declare a variable of any reference type
  - Java allocates a box of size 64 bits
  - This can be either set to:
    - Null (all zeros)
    - The 64-bit address of a specific instance of that class (returned by new)

## Box and Pointer Notation
- As 64 bit representations are meaningless to humans
  - Represent all zero addresses as null
  - Non zero addresses as arrows
  - This is called box and pointer notation

## Again: The Golden Rule of Equals
- Just as with primitive types, the equal signs COPIES the bits
- In terms of our visual metaphor, we copy the arrow by making the arrow
  in box b point as the same instance as a

## Parameter Passing and Golden Rule
- Given variable b and a:
  - b = a copies all bits from a into b
- Passing parameters obeys the same rule: Simply copy the bits to the new scope/frame

## Summary
- 9 types of variables
  - The 8 primitives
  - The 9th type are references (arrows) to Objects
- In box and pointer notation, each variable is drawn as a labelled box and values shown in box
- The golden rule: b = a COPIES THE BITS from a into b
- Passing parameters COPIES THE BITS


## Declaration and Instantiation of Arrays
- Arrays are also Objects
- Objects are instantiated using the new keyword
- Declaration creates a 64 bit box intended for storing a refence to in array.
- Declaration: The creation of the box "a"
- Assignment: Pointing the arrow at the object "=" 
- Instantiation: creation of the object "new b"
- 
## IntList and Linked Data Structures
- Lets define intList