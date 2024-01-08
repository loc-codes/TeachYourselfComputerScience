public class DogProblem {
    public static Dog[] largerThanFourNeighbours(Dog[] dogs) {
        Dog[] returnDogs = new Dog[dogs.length];
        int count = 0;
        for (int i = 0; i < dogs.length; i += 1){
            if (isBiggestOfFour(dogs, i)) {
                returnDogs[count] = dogs[i];
                count += 1;
            }
        }
        returnDogs = arrayWithNoNulls(returnDogs, count);
        return returnDogs;
    }

    public static Dog[] arrayWithNoNulls(Dog[] tempDogs, int count) {
        Dog[] noNullDogs = new Dog[count];
        for (int i = 0; i < count; i+= 1){
            noNullDogs[i] = tempDogs[i];
        }
        return noNullDogs;
    }

    // Returns true if dogs[i] is larger than its four neighbours
    public static boolean isBiggestOfFour(Dog[] dogs, int i) {
        boolean isBiggest = true;
        Dog currentDog = dogs[i];
        for (int j=i-2; j <= i+2; j += 1){
            if (validIndex(dogs, j) && !selfEvaluation(i, j)) {
                Dog neighbourDog = dogs[j];
                if (neighbourDog.weightInPounds >= currentDog.weightInPounds){
                    isBiggest = false;
                    break;
                } 
            }
        }
        return isBiggest;
    }

    public static boolean validIndex(Dog[] dogs, int i) {
        return (i >= 0) && (i < dogs.length);
    }

    public static boolean selfEvaluation(int i, int j) {
        return i == j;
    }

    public static void main(String[] args){
        Dog[] dogs = new Dog[]{
            new Dog(10),
            new Dog(15),
            new Dog(20),
            new Dog(15),
            new Dog(10),
            new Dog(5),
            new Dog(10),
            new Dog(15),
            new Dog(20),
            new Dog(22),
            new Dog(20)
        };

        Dog[] bigDogs = largerThanFourNeighbours(dogs);

        for (int i = 0; i < bigDogs.length; i += 1){
            System.out.println(bigDogs[i].weightInPounds + " ");
        }
        System.out.println();
    }
}
