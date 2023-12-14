/** Used to 'test drive' to dog class */

public class DogLauncher {
    public static void main(String[] args) {
        Dog smallDog; //Declaration
        new Dog(20); //Instantiation
        smallDog = new Dog(5); //Instantiation and assignment
        Dog hugeDog = new Dog(150); //Declaration, Instantation and assignment on one line
        smallDog.makeNoise();
        hugeDog.makeNoise(); // Invocation

        Dog bigger = Dog.maxDog(smallDog, hugeDog);
        bigger.makeNoise(); // Expect woof
        System.out.println(bigger.binomen); // Bad style
        System.out.println(Dog.binomen); // Preferred reference to static method
    }
}
 