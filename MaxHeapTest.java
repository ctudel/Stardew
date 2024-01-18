import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for MaxHeap object functionality
 * 
 * @author ctudel
 * @version Fall 2023
 */
public class MaxHeapTest {
    private final Task TASK_A = new Task(5, TaskInterface.TaskType.MINING, "Mine six rocks.");
    private final Task TASK_B = new Task(6, 7, TaskInterface.TaskType.FEEDING, "Eat an apple.");
    private final Task TASK_C = new Task(7, 3, TaskInterface.TaskType.FORAGING, "Hunt in the forest.");
    private final Task TASK_D = new Task(9, 6, TaskInterface.TaskType.SOCIALIZING, "Talk to Norma.");
    private final Task TASK_E = new Task(10, 7, TaskInterface.TaskType.FARM_MAINTENANCE, "Pull weeds.");

    private final Task[] TASKS_SORTED = {TASK_E, TASK_D, TASK_C, TASK_B, TASK_A};
    private final Task[] TASKS_UNSORTED = {TASK_A, TASK_B, TASK_C, TASK_D, TASK_E};
    private final Task[] TASKS_RANDOM = {TASK_D, TASK_A, TASK_B, TASK_E, TASK_C};
    private MaxHeap actual;
    private boolean bool;

    /**
     * helper function that calls insert on any given array for 1 to n elements.
     * @param array array filled with elements to be inserted into a MaxHeap object.
     * @param heap MaxHeap object that needs elements inserted into it.
     */
    public void importArray(Task[] array, MaxHeap heap) throws HeapException {
        for (int i = 0; i < array.length; i++) {
            heap.insert(heap.size, array[i]);
        }
    }

    /**
     * checks if a heap is sorted from greatest to least priority.
     * @return returns true if heap is sorted from greatest to least priority.
     * @return returns false otherwise.
     */
    public boolean checkIfSorted(MaxHeap heap) {
        for (int i = 0; i < heap.size(); i++) {
            if (i + 1 == heap.size) {
                break;
            }

            if(heap.get(i).compareTo(heap.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if any given MaxHeap object is a max heap.
     * @param heap passed in MaxHeap object
     */
    public boolean checkIfMaxHeap(MaxHeap heap) {
        this.bool = heap.validMaxHeap();
        return bool;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++    TESTING STARTS HERE    +++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++
    //++++++++++++++++++++++++++++++++++++++++++++++

    /** 
     * testValidateHeapSorted()
     * tests helper functions work as expected.
     */
    @Test
    public void testValidateHeapSorted() throws HeapException {
        // creates a heap that is sorted and is a max heap
        actual = new MaxHeap(TASKS_SORTED);
        assertTrue(checkIfSorted(actual));
        assertTrue(checkIfMaxHeap(actual));
    }

    /** 
     * testValidateHeapUnsorted()
     * tests helper functions work as expected.
     */
    @Test
    public void testValidateHeapUnsorted() throws HeapException {
        // creates a heap that is unsorted and is not a max heap
        actual = new MaxHeap(TASKS_UNSORTED);
        assertFalse(checkIfSorted(actual));
        assertFalse(checkIfMaxHeap(actual));
    }

    /**
     * testIsEmpty()
     * tests true is returned for an empty heap.
     */
    @Test
    public void testIsEmpty() {
        actual = new MaxHeap();
        assertTrue(actual.isEmpty());
    }

    /**
     * testIsNotEmpty()
     * tests false is returned for an non-empty heap. 
     */
    @Test
    public void testIsNotEmpty() {
        actual = new MaxHeap(TASKS_SORTED);
        assertFalse(actual.isEmpty());
    }  

    // Note: testing increaseKey, buildMaxHeap, etc. Use a mix of created arrays with insert and manually.

    /**
     * testInsert()
     * test the MaxHeap property is maintained after each insertion of an element.
     */
    @Test
    public void testInsert() throws HeapException {
        // expected MaxHeap
        Task[] expected = {TASK_B, TASK_A};
        // create new MaxHeap object
        actual = new MaxHeap();
        // insert tasks int MaxHeap object
        actual.insert(0, TASK_A);
        actual.insert(1, TASK_B);
        // check actual MaxHeap is equal to expected 
        assertEquals(actual.get(0), expected[0]);
        assertEquals(actual.get(1), expected[1]);
        assertTrue(checkIfMaxHeap(actual));
    }

    /**
     * testInsertAascending()
     * test the MaxHeap property is maintained after each insertion
     * of elements with priority 1 to n.
     */
    @Test
    public void testInsertAscending() throws HeapException {
        actual = new MaxHeap();
        importArray(TASKS_UNSORTED, actual);
        assertTrue(checkIfMaxHeap(actual));
    }

    /**
     * testInsertDecending()
     * test the MaxHeap property is maintained after each insertion
     * of elements with priority n down to 1.
     */
    @Test
    public void testInsertDecending() throws HeapException {
        actual = new MaxHeap();
        importArray(TASKS_SORTED, actual);
        assertTrue(checkIfMaxHeap(actual));
    }

    /**
     * test the MaxHeap property is maintained after each insertion of an element.
     */
    @Test
    public void testInsertRandom() throws HeapException {
        actual = new MaxHeap();
        importArray(TASKS_RANDOM, actual);
        assertTrue(checkIfMaxHeap(actual));
    }

    /**
     * testExpandHeap()
     * tests the size of a heap array is increased if full.
     */
    @Test
    public void testExpandHeap() throws HeapException {
        int expected = TASKS_RANDOM.length + TASKS_SORTED.length + TASKS_UNSORTED.length;

        actual = new MaxHeap(TASKS_RANDOM);
        importArray(TASKS_UNSORTED, actual);
        importArray(TASKS_SORTED, actual);
        // checks that the MaxHeap array expands in size
        // to make space for a large amount of elements
        assertEquals(expected, actual.size);

    }


    /**
     * testTwoExtractMax()
     * tests the task with the greatest priority is extracted from the heap each time.
     */
    @Test
    public void testTwoExtractMax() throws HeapException {
        Task[] expected = {TASK_A, TASK_E};
        actual = new MaxHeap(expected);
        // call extractMax and verify actual max values are equal to the expected max values
        assertEquals(TASK_E, actual.extractMax());
        assertEquals(TASK_A, actual.extractMax());
    }

    /**
     * testThreeExtractMax()
     * tests the task with the greatest priority is extracted from the heap each time.
     */
    @Test
    public void testThreeExtractMax() throws HeapException {
        Task[] expected = {TASK_A, TASK_C, TASK_E};
        actual = new MaxHeap(expected);
        // call extractMax and verify actual max values are equal to the expected max values
        assertEquals(TASK_E, actual.extractMax());
        assertEquals(TASK_C, actual.extractMax());
        assertEquals(TASK_A, actual.extractMax());
    }

    /**
     * testTwoIncreaseKey()
     * tests the MaxHeap property is maintained after each replacement of a task.
     */
    @Test
    public void testTwoIncreaseKey() throws HeapException {
        Task[] expected = {TASK_B, TASK_A};
        actual = new MaxHeap(expected);
        // call increase key with last position
        actual.increaseKey(1, TASK_D);
        // check heap is a MaxHeap and values are sorted correctly
        assertEquals(TASK_D, actual.get(0));
        assertEquals(TASK_B, actual.get(1));
    }

    /**
     * testThreeIncreaseKey()
     * tests the MaxHeap property is maintained after each replacement of a task.
     */
    @Test
    public void testThreeIncreaseKey() throws HeapException {
        Task[] expected = {TASK_C, TASK_B, TASK_A};
        actual = new MaxHeap(expected);
        // call increase key on last position
        actual.increaseKey(2, TASK_D);
        // check heap is a MaxHeap and values are sorted correctly
        assertEquals(TASK_D, actual.get(0));
        assertEquals(TASK_B, actual.get(1));
        assertEquals(TASK_C, actual.get(2));
    }
} 
