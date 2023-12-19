package deque;

public class LinkedListDeque<Type> {

    private class Node {
        public Type item;
        public Node next;
        public Node prev;

        public Node(Type i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque () {
        sentinel = new Node(null, sentinel, sentinel);
        size = 0;
    }

    private void initialiseList(Type elem){
        Node initialNode = new Node(elem, sentinel, sentinel);
        sentinel.next = initialNode;
        sentinel.prev = initialNode;
    }

    public void addFirst(Type elem){


        if (size == 0){
            initialiseList(elem);
        }
        else {
            Node NewNode = new Node(elem, sentinel, sentinel.next);
            sentinel.next.next.prev = NewNode;
        }
        size += 1;
    }

    public void addLast(Type elem){
        if (size == 0){
            initialiseList(elem);
        }
        else {
            Node NewNode = new Node(elem, sentinel.prev, sentinel);
            sentinel.prev = NewNode;
            sentinel.prev.prev.next = NewNode;
        }
        size += 1;
    }

    public Type removeFirst(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
            Type removedElem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return removedElem;
        }

    }

    public Type removeLast(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
            Type removedElem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return removedElem;
        }

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        Node p = sentinel.next;
        while (p.item != null){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
        //System.out.println("printDeque called");
    }

    public static void main (String[] args){

    }
}
