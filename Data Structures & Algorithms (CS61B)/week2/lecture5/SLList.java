
// List of integers which hides the usability issues of IntList
public class SLList {
    private static class IntNode { // Nested class
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    // The first item if it exists, is at sentinel.next
    private IntNode sentinel;
    private int size;

    public SLList () {
        sentinel = new IntNode(1, null); // Initalise sentinel with any int
        size = 0;
    }
    public SLList (int x) {
        sentinel = new IntNode(x, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    //Adds x to the front of the list behind sentinel
    public void addFirst (int x) {
        sentinel.next = new IntNode(42, null);
        size += 1;
    }

    // Returns the first item in the list
    public int getFirst () {
        return sentinel.next.item;
    }

    public void addLast (int x) {
        IntNode p = sentinel;
        size += 1;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    public int size () {
        return size;
    }

    public static void main(String[] args){
        // Creates a list of one integer, namely 10.
        SLList L = new SLList(); // easy to instantiate that IntList
//        L.addFirst(10);
//        L.addFirst(5);
        //System.out.println(L.getFirst());
        L.addLast(20);
        System.out.println(L.size());
    }


}
