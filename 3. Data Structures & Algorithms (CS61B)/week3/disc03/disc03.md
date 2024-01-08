# Discussion 3
## Content Review
### Primitive vs Reference Types
- Primitive types are represented by a certain number of bytes stored at the location of the variable in memory
    - eg: byte, short, long, float, double, boolean, char
    - Memory box: x [5] 
        - 5 is represented by bytes, we copy the primitive bytes (eg: 00000101 in 8 bit)
- Reference types are represented by a memory address stored at the location of the variable which points to where the full object is
    - Variable box contains an address which "points" to the object
    - To compare to objects, use equals as "=" looks for same memory address

### Pass by Values
- Means that when you call a function and give it some arguments, the function receives an exact copy of those arguments, tied to its own local variables
    - It is a shallow copy, meaning that if a pointer to an object is passed, only the pointer is copied, the object is memory is not
    - Deep copy creates a replica of the object at a new memory address

### Static vs Instance
- Static variables and functions belong to the whole class
- EG: Every 61B student shares the same professor, and if the professor were to change, it would change for everyone

- Instance variables and functions belong to each individual instance
- EG: Every 61B student has their own student id

### Linked Lists
- Modular lists that are made up of nodes that contain a value and a pointer to the next node
- To access values, you must use dot notation