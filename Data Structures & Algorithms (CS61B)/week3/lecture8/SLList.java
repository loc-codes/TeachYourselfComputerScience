
// List of integers which hides the usability issues of IntList
public class SLList<Type> implements List61B<Type> {
    private class Node { // Nested class
        public Type item;
        public Node next;

        public Node(Type i, Node n) {
            item = i;
            next = n;
        }
    }

    // The first item if it exists, is at sentinel.next
    private Node sentinel;
    private int size;

    public SLList (Type x) {
        sentinel = new Node(x, null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    //Adds x to the front of the list behind sentinel
    public void addFirst (Type x) {
        sentinel.next = new Node(x, null);
        size += 1;
    }

    // Returns the first item in the list
    public Type getFirst () {
        return sentinel.next.item;
    } 
    
    public Type get(int position){
        if (position == 0){
            return getFirst();
        }
        Node currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != null){
            position -= 1;
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    public void addLast (Type x) {
        Node p = sentinel;
        size += 1;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    public int size () {
        return size;
    }

    public void insert(Type item, int position) {
        if (sentinel.next == null || position == 0) {
            addFirst(item);
            return;
        }

        Node currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != null) {
            position -= 1;
            currentNode = currentNode.next;
        }

        Node newNode = new Node(item, currentNode.next);
        currentNode.next = newNode;
    }

    @Override
    public void print() {
        for (Node p = sentinel.next; p != null; p = p.next) {
            System.out.println(p.item + " ");
        }
    }
}
