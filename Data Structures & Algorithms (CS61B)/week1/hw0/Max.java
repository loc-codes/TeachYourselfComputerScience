public class Max {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int x = 0;
        int largest = 0;
        while (x < m.length){
           if (m[x] > largest) {
               largest = m[x];
           }
           x = x + 1;
        }
        return largest;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
       System.out.println(max(numbers));
    }
}