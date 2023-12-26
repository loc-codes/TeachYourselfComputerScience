public class VengefullSLList<Item> extends SLList<Item>{
    SLList<Item> deletedItems;

    public VengefullSLList() {
        super();
        deletedItems = new SLList<Item>();
    }

    public VengefullSLList(Item x) {
        super(x);
        deletedItems = new SLList<Item>();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    public void printLostItems() {
        deletedItems.print();
    }

    public static void main(String[] args){
        VengefullSLList<Integer> vs1 = new VengefullSLList<Integer>(0);
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(15);

        vs1.removeLast();
        vs1.removeLast();

        System.out.println("The fallen are: ");
        vs1.printLostItems();
    }
}
