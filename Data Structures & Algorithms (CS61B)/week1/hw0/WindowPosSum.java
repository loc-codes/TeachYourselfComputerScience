public class WindowPosSum  {
    /** Returns the maximum value from m. */
    public static void windowPosSum(int[] a, int n) {
       for (int i = 0; i < a.length; i += 1) {
          if (a[i] < 0) {
              continue;
          }
          int newNum = 0;
          for (int j = i; j <= i+n; j += 1) {
             if (j >= a.length) {
                 break;
             }
             newNum += a[j]; 
          }
          a[i] = newNum;
       }
    }
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, -3, 4, 5, 4};
        int n = 3; 
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}