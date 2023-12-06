public class ForMax {
    /** Returns the maximum value from m. */
    public static int forMax(int[] m) {
        int largest = 0;
        for (int i = 0; i < m.length; i += 1){
           if (m[i] > largest) {
               largest = m[i];
           }
        }
        return largest;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6}; 
       System.out.println(forMax(numbers));
    }
}