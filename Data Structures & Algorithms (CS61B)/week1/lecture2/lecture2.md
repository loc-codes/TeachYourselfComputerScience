# Lecture 2
## Defining Classes
- Every method is associated with a class
- To run a class, we must define a main method
- But, not all classes have a main method (eg: see Dog and Doglauncher example)
- Classes don't just contain methods, but also data
- Classes can be instantiated as objects

- Instance method: If the method is going to be invoked by an instance, then it should be non-static
- Idea: If you need to use my instance variables, the method must be non-static

- Create an array of objects:
- Dog[] dogs = new Dog[2];
- dogs[0] = new Dog(8);
- dogs[1] = new Dog(20);
- dogs[0].makeNoise();

- First we create a "new" array with a size (eg: 2)
- Then we create new Dog objects and put them in the array

## Static vs Instance Method
- Static method as invoked using the class name eg: Dog.makeNoise();
- Instance methods are invoked using an instance name eg: smallDog.makeNoise();
- Static methods can't access "my" instance variables, because there's no "me. Only the idea of Dog, not a particular dog

### Why Static Methods
- Some classes are never instantiated. For example, Math
- x = Math.round(5.6)
- There's not really such thing as an "instance" of Math. Below is less intuitive
    Math m = new Math();
    x = m.round(x)
- Static member (variable or method) should be referenced bh 

## Compilation
- javac HelloWorld.java
- java HelloWorld
- .class file has been type checked, distributed code is safer
- .class files are simpler for machine to execute

## Managing Complexity with Helper Functions
- Managing complexity is key to this course
- Helper methods: decompose large problems into smaller problems