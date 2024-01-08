public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }
    public static void main(String[] args) {
        System.out.println(larger(10, 0));
    }
}

/**
 1. To declare a function in Java, use public static (for today)
 2. All parameters of a function must have a type, 
 and the function must have return type
 3. All functions must be part of a cash
    Therefore, all functions are methods
 */