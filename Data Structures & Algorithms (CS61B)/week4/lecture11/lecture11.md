# Lists and Sets in Java
## Summary of Lecture Code
- Added an exception if a user tried to add null to the set
- Added support for ugly then nice iteration
  - Ugly Iteration: create subclass with next and hasNext methods
  - Nice iteration: Declaring that ArraySet implements iterable
- Added toString() method
- Added an equals(Object) method
  - Make sure to deal with null and non-ArraySet arguments
  - Used getClass to check teh class of the passed object
- 