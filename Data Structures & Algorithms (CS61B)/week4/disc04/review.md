# Content Review
## Classes (EXTENDS)
- Subclasses (or child classes) are classes that extend 
another class
  - This means they have access to all functions and 
variables of parent class
  - Subclass IS-A type of the parent class
  - eg: Golden Retriever is a Dog, but not a Dog is a Golden 
Retriever (Golden Retriever is sub-class)

- Superclasses or parent classes that are classes that are 
extended by another class

## Interfaces (IMPLEMENTS)
- Are implemented by classes
- They describe a narrow ability that can apply to many 
classes that may or may not be related to one another
- They are like abstract classes in that they do not usually 
implement methods they specify

- Can implement multiple interfaces, but only extend one 
class

## Methods
- Method overloading is done when there are multiople methods 
with the same name and return type, but different parameters

- Method overriding is done when a subclass has a method with 
the exact same function signature as a method in its 
superclasss

## Casting
- Allows our compiler to overlook cases where we are calling 
a method that belongs to a subclass on a variable that is 
statically typed to the superclass

## Dynamic Method Selection
Your computer..
  - at copile time
    - Check for valid variable assignments
    - Check for valid method calls (only considering static 
type and static types superclasses)
  - at run time
    - check for overriden methods
    - ensure casted objects can be assigned to their 
variables
