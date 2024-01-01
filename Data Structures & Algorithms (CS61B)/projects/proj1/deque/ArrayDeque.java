package deque;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ArrayDeque <T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int capacity = 8;
    public int frontIndex = 4;
    public int backIndex = 5;

    // Creates an empty list
    public ArrayDeque(){
        items = (T []) new Object[capacity];
        size = 0;
    }

    private void resize(int capacity) {
        T[] resizedArray = (T []) new Object[capacity];
        // This variable accounts for "wrapping" of front index. Eg: if front index = 8, capacity = 8, oneAfter will be 0 not 9
        int oneAfterFrontIndex = updateIndex(frontIndex + 1);
        //Handles resizing down of lists that don't wrap eg: frontIndex = 17, backIndex = 49, capacity = 128.
        if (backIndex - frontIndex == capacity) {
            System.arraycopy(items, oneAfterFrontIndex,  resizedArray, 0, size);
        }
        // Handles up and down sizing of all other lists, including wrapped lists
        else {
            System.arraycopy(items, oneAfterFrontIndex,  resizedArray, 0, this.capacity-oneAfterFrontIndex);
            System.arraycopy(items,0,  resizedArray, this.capacity-oneAfterFrontIndex, backIndex);
        }
        items = resizedArray;
        frontIndex = capacity - 1;
        backIndex = size;
        this.capacity = capacity;
    }

    public int updateIndex(int index) {
        if (index == capacity) {
            index = 0;
        }
        else if (index == -1){
            index = capacity - 1;
        }
        return index;
    }

    public void addFirst(T elem){
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[frontIndex] = elem;
        size += 1;
        frontIndex = updateIndex(frontIndex -1);
        return;
    }

    public void addLast(T elem){
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[backIndex] = elem;
        size += 1;
        backIndex = updateIndex(backIndex + 1);
    }


    public T get(int i) {
        int getIndex = frontIndex+i+1;
        if (getIndex >= capacity) {
            getIndex = getIndex-capacity;
        }
        return items[getIndex];
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size-1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T removeFirst(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else if ((size < items.length / 4) && (size >= 16)) {
            resize(items.length / 4);
        }
            T removedElem = getFirst();
            frontIndex = updateIndex(frontIndex + 1);
            items[frontIndex] = null;
            size -= 1;
            return removedElem;
        }

    public T removeLast(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else if ((size < items.length / 4) && (size >= 16)) {
            resize(items.length / 4);
        }
        T removedElem = getLast();
        backIndex = updateIndex(backIndex -1);
        items[backIndex] = null;
        size -= 1;
        return removedElem;
    }

    public void printDeque(){
        for (int i = 0; i < size; i += 1){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public boolean equals(Object other){
        if (this == other) {return true;}
        if (other instanceof ArrayDeque otherArray) {
            if (otherArray.size != this.size) {
                return false;
            }
            for (int i = 0; i < size; i += 1){
                if (this.get(i) != otherArray.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Iterator<T> iterator(){
        return new ArrayDeque.ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;

        public ArrayListIterator() {
            index = frontIndex + 1;
        }

        public boolean hasNext() {
            return index != backIndex;
        }

        public T next() {
            T returnItem = items[index];
            index = updateIndex(index + 1);
            return returnItem;
        }
    }
}
