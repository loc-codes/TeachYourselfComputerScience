import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class ArraySet<T> implements Iterable<T>{
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1){
            if (x.equals(items[i])) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("You can't add null to an ArraySet");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    public int size() {
        return size;
    }

    // returns an iterator into me
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int position;

        public ArraySetIterator() {
            position = 0;
        }
        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = items[position];
            position += 1;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1){
            returnSB.append(items[i].toString());
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (other instanceof ArraySet oas) {
            if (oas.size != this.size) {
                return false;
            }
            //check all this items are in the other array set oas
            for (T x: this) {
                if (!oas.contains(x)){
                    return false;
                }
            }
            return true;
        }
        // o is not an arrayset, so return false
        return false;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));

        System.out.println(aset);
    }
}