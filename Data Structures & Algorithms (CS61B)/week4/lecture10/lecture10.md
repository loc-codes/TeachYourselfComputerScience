# Lecture 9 - Subtype Polymorphism and HoF
## Warmup - Dynamic Method Selection
- The rules
  - Compiler allows memory box to hold any subtype
  - Compiler allows calls based on static type
  - Overridden non-static methods are selected at run time based on dynamic typ
  - Everything else is based on static type
  - COMPILER ONLY CONSIDERS STATIC TYPE!
  - Dynamic type is also called run-time type

## Subtype Polymorphism
- Polymorphism: providing a single interface to entities of different types
- Subtypes eg: Dog is a subtype of Object
  - Java automatically selects the right behaviour using dynamic method selection
    - first look in the subtype, then the parent type

## Subtype Polymorphism vs Explicit HoF
- Explicit HoF is more functional, subtype polymorphism is object-oriented using object methods

## Comparables
- Two issues with OurComparable
  - Awkward casting to/from objects
  - Its custom, we made it up. 
    - So it doesn't work with built in type (eg: compare 2 strings)
    - We have to extend further methods eg: Median, Max etc
- Use built-in interface called Comparable
  - Takes a generic type <T>
  
## Comparators
- We don't want to compare objects in the same way every time
- We have been comparing by size, but maybe we want to compare/order by alphabetical order

## Summary
- Interfaces provide us with the ability to make callbacks:
  - A function needs the help of another function that might not have been written yet
    - eg: max needs compareTo
    - The helping function is sometimes called a callback
- In Java, we do this by wrapping up the needed function in an interface
  - eg: Arrays.sort needs compare which lives inside comparator interface