# Lecture 8 - Inheritance
## Method Overloading in Java
- Java allows multiple methods with the same name
- Downsides: Repeated code, source code is unnecessarily long, hard to debug
  - Also, suppose we make another list type someday, we need another function

## Simple Hyponymic Relationships in Java
- Step 1: Define a reference type for our hypernym (List61B.java)
  - Keyword interface instead of class
  - Interface is a spec of what a List is able to do, not how to do it.
- Step 2: Specify that SLLists and Alists are hyponyms of that type

## Overriding vs Overloading
- If a subclass has a method with the exact same signature as in the superclass, 
  we say the subclass overrides the method
- Overloaded - methods with same name but different signatures are overloaded
- Overrides only applies in inheritance with same method signature

## @Override Annotation
- Means compile fails if method is not overrided from inherited interface
- Catches typos
- Readable and helps programmer know method is inherited

## Interface Inheritance
- Specifying the capabilities of a sublclass using the implements keyword
- Is known as interface inheritance

- Interface: The list of all method signatures
- Inheritance: The subclass inherits the interface from a superclass
- Speicifed what the subclass can do, but not how
- Subclasses must override ALL these methods
  - Otherwise compilation fail
- Powerful as it allows code to generalise

## Copying the Bits
- Two seemingly contradictory facts:
1. When you set x = y or pass a parameter, you're just copying the bits
2. A memory bxo can only hold 64 bit address for the appropriate type

- If X is a superclass of Y, then memory boxes for X may contain Y
- An AList is a list
- Therefor,  list memory boxes can hold AList's

## Implementation Inheritance
- Interface inheritance: Subclass inherits signatures, not implementation
- For better or worse, Java allows implementation inheritance