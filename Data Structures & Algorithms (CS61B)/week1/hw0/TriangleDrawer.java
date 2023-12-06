public class TriangleDrawer {
    public static void drawTriangle(int N){
       int n = 1;
       String line = "*";
       while (n < N) {
          System.out.println(line);
          line = line + "*";
          n = n + 1;
       }
    }
 
    public static void main(String[] args) {
       drawTriangle(10);
    }
    
 }