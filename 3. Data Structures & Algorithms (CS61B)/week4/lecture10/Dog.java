import java.util.Comparator;

public class Dog implements Comparable<Dog>{
    private String name;
    public int size;

    public Dog(String n, int s){
        name = n;
        size = s;
    }

    public void bark(){
        System.out.println(name + " says: bark");
    }

    // Returns negative int if this dog is less than the dog pointed at by O, and so forth
    public int compareTo(Dog otherDog) {
        return this.size - otherDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog dog1, Dog dog2){
            return dog1.name.compareTo(dog2.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
