import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort {
    // Test the Sort.sort method
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);

        assertArrayEquals(expected, input);
    }

    @Test
    public void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};


        Sort.swap(input, a, b);
        assertArrayEquals(expected, input);
    }

    // Test the sort.findSmallest method
    @Test
    public void testFindSmallest() {
        String[] input1 = {"i", "have", "an", "egg"};
        String[] input2 = {"here", "are", "some", "pigs"};
        int expected1 = 2;
        int expected2 = 3;

        int actual1 = Sort.findSmallest(input1, 0);
        assertEquals(expected1, actual1);


        int actual2 = Sort.findSmallest(input2, 2);
        assertEquals(expected2, actual2);
    }

//    public static void main(String[] args) {
//        testFindSmallest();
//        testSwap();
//        testSort();
//    }
}
