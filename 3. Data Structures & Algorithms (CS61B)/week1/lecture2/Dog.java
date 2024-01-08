public class Dog { 
    public int weightInPounds;  /** Instance variable */
    public static String binomen = "Canis familiaris"; /**Static vartiable */

    /** Constructor for dog */
    public Dog(int w){
        weightInPounds = w;
    }

    public void makeNoise() { /** Non static method: aka Instance method*/
        if (weightInPounds < 10) {
            System.out.println("Yip!");
        }
        else if (weightInPounds < 30) {
            System.out.println("Bark!");
        }
        else {
            System.out.println("woof!");
        }
        
    }

    public static Dog maxDog(Dog d1, Dog d2) { /** Static method */
        if (d1.weightInPounds > d2.weightInPounds) {
            return d1;
        }
        return d2;
    }

    
}