public class Maximiser {
    public static Comparable max(Comparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i += 1){
            int cmp = items[i].compareTo(items[maxIndex]);
            if (cmp >= 0){
                maxIndex = i;
            }
        }
        return items[maxIndex];
    }
}
