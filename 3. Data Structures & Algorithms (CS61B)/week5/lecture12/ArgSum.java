public class ArgSum {
    // Prints out the sum of arguments, assuming they are integers
    public static void main(String[] args){
        int index = 0;
        int sum = 0;
        while (index < args.length) {
            sum = sum + Integer.parseInt(args[index]);
            index += 1;
        }
        System.out.print(sum);
    }
}
