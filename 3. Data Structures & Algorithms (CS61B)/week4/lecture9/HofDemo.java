// Demonstrates high order functions in Java
public class HofDemo {
    public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args){
        IntUnaryFunction tenX = new Tenx();
        int x = 2;
        System.out.println(do_twice(tenX, x));
    }
}
