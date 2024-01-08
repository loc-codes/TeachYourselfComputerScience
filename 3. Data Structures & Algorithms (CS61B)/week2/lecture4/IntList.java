public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    //Return the size of the list using recursion
    public int size() {
        // Thinking:
        // What's the size? the size of the number of elements.
        // Or how many calls I can make until rest = null
        if (this.rest == null) {
            return 1;
        } else {
            return 1 + this.rest.size();
        }
    }

    //Return the size of list using iteration
    public int iterativeSize() {
        //Thinking: What's the size
        //While some variable is not null, I can count
        IntList pointer = this;
        int totalSize = 0;
        while (!(pointer == null)){
            totalSize += 1;
            pointer = pointer.rest;
        }
        return totalSize;
    }

    public int recursiveGet(int i){
        if (i == 0){
            return first;
        }
        else {
            return rest.recursiveGet(i-1);
        }
    }
    public int iterativeGet(int i) {
        int count = 0;
        IntList L = this;
        while (count < i){
            L = L.rest;
            count += 1;
        }
        return L.first;
    }

    public static void main(String[] args){
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        //System.out.println(L.size());
        //System.out.println(L.iterativeSize());
        System.out.println(L.recursiveGet(2));
    }
}
