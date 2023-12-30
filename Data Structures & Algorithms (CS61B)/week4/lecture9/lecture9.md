# Lecture 8 - Extends, Casting and HoF
## The Extends Keywords
- When a class if a hyponym of an interface, we used implements
- If you want one class to be a hyponym of another class, use extends!
- "Extends" inherits all attributes and methods, and all nested class
- Constructors are not inherited

## Is-a v Has-a
- Extends/inheritance should only extend for is-a relationships
- eg: en-suite is a bathroom
- but you should never extend a relationship like bathroom has a shower

## Encapsulation
### Complexity - The enemy
- When building large programs, our enemy is complexity
- Tools for managing complexity
  - Hierarchical abstraction
    - Create layers of abstraction with clear abstraction barriers
  - Design for change
    - Organise programs around objects
    - Let objects decide how things are done
    - Hide information others don't need

### Modules and Encapsulation
- A module is a set of methods that work together as a whole to perform some task/set of tasks
- A module is said to be encapsulated if its implementation is completely hidden
  and can be accessed only through a documented interface

### Cautionary Tale from Project 1b
- You want to think on the right level of abstraction
  - The autograder is a higher level of abstraction than the StudentArrayDeque
- More generally, imagine being a doctor. Do you need to work at the level of the external patient, internal patient, cell level? inside the cell
- As a user of ArrayDeque, you cannot observe its internals
  - Even when writing tests, you don't want to peer insider
- SIDE NOTE FOR MYSELF:
  - When communicating with customers, you are projecting the shadows for Plato's cave
    - They don't need to know the material of the objects you are holding up, they want the shadows explained

## Type Checking and Casting
- SLList sl = new VengefulSLLIst();
  - Allowed, RHS is a subclass of SLList
- VengefulSLList vsl = new SLLIst();
  - Not allowed, RHS is the super-class.


## Casting 
- Java has a special syntax for forcing the compile time type of any expression
- Put desired type in parentheses before expression
- Dog largerDog = maxDog(frank, frankJr)
- Poodle largestPoodle = (Poodle) maxDog(frank, frankJr)

- Casting is a powerful but dangerous tool
- Tells JAva to treat an expression as having a different compile time type
  - ie: tell compiler to ignore type checking duties

## Implementation Inheritance Cheatsheet
- VengefulSLList extends SLList means a VengefulSLList is an-SLList. 
- Inherits all members
  - Variables, methods and nested classes
  - Not constructors
  - Subclass constructor must invoke superclass constructor first
  - User super to invoke overridden superclass methods and constructors

- Invocation of overridden methods follows 2 simple rules:
  - Compiler plays it safe and only lets us do things allowed by static type
  - For overridden methods, the actual method invoked is based on dynamic type of invoking expression
    - DOES NOT APPLY TO OVERLOADED METHODS
  - Can use casting to overrule compiler type checking