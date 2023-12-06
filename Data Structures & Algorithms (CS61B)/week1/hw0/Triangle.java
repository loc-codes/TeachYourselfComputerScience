public class Triangle {
    public static void main(String[] args) {
        String line = "*";
        while (!line.equals("*****")) {
            System.out.println(line);
            line = line + "*";
        }
    }
}