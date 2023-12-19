
// List of integers which hides the usability issues of IntList
public class SLList<Placeholder> {
    private class StuffNode { // Nested class
        public Placeholder item;
        public StuffNode next;

        public StuffNode(Placeholder i, StuffNode n) {
            item = i;
            next = n;
        }
    }

    // The first item if it exists, is at sentinel.next
    private StuffNode sentinel;
    private int size;

    public SLList (Placeholder x) {
        sentinel = new StuffNode(x, null);
        sentinel.next = new StuffNode(x, null);
        size = 1;
    }

    //Adds x to the front of the list behind sentinel
    public void addFirst (Placeholder x) {
        sentinel.next = new StuffNode(x, null);
        size += 1;
    }

    // Returns the first item in the list
    public Placeholder getFirst () {
        return sentinel.next.item;
    }

    public void addLast (Placeholder x) {
        StuffNode p = sentinel;
        size += 1;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new StuffNode(x, null);
    }

    public int size () {
        return size;
    }

//    public static void main(String[] args){
//        // Creates a list of one integer, namely 10.
//        SLList L = new SLList(); // easy to instantiate that IntList
////        L.addFirst(10);
////        L.addFirst(5);
//        //System.out.println(L.getFirst());
//        L.addLast(20);
//        System.out.println(L.size());
//    }


}
