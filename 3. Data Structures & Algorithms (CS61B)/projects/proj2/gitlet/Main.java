package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                String filename = args[1];
                Repository.add(filename);
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                String message = args[1];
                Repository.commit(message);
        }
    }
}
