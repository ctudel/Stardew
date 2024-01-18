import java.util.Arrays;

/**
 * MaxHeap object class
 * creates a heap that maintains the max heap property
 * 
 * @author ctudel
 * @version Fall 2023
 * 
 */
public class MaxHeap {
    private final int DEFAULT_CAPACITY = 10;
    protected Task[] heap;
    protected int size; 

    /**
     * creates a Maxheap object with given default values.
     */
    public MaxHeap() {
        heap = new Task[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * creates a MaxHeap object with any passed in Task object array.
     * @param taskArray task object(s) filled array.
     */
    public MaxHeap(Task[] taskArray) {
        heap = taskArray;
        size = heap.length;
    }
    
    /**
     * check heap has no elements
     * @return returns true if heap has no elements, 
     * otherwise returns false.
     */
    public boolean isEmpty() {
        if (this.size < 1) return true;
        else return false;
    }

    /**
     * gets greatest priority task from heap and returns it.
     * @return returns the task with the greatest priority in the heap.
     * @throws HeapException HeapException is thrown if the heap is empty
     */
    public Task max() throws HeapException {
        if(isEmpty()) {throw new HeapException("Error: heap overflow");}
        // turn heap into a max-heap
        buildMaxHeap();
        // return the highest priority task in the heap keeping encapsulation
        Task retVal = heap[0];
        return retVal;
    }

    /**
     * removes greatest priority task from the heap and returns it.
     * @return returns the removed task.
     * @throws HeapException HeapException is thrown if the heap is empty
     */
    public Task extractMax() throws HeapException {
        Task max = max();
        heap[0] = heap[size - 1];
        size = size - 1;
        heapify(0);
        return max;
    }

    /**
     * replaces or places a task at a given position and maintains max heap property.
     * @param position the index at which the new task is placed
     * @param task a task to insert into the heap
     */
    public void increaseKey(int position, Task task) throws HeapException {

        if(task.compareTo(heap[position]) < 0) {
            throw new HeapException("Error: Ensure new task is greater than the old task");
        }

        // insert new task at given index
        heap[position] = task;
        // set i to new task index
        int i = position;
        // set parent variable to the nearest node that is a parent with two children
        int parent = (i - 1) / 2;

        // loop up the tree so long as the parent task is less than the new task
        // and i is greater than 0
        while (i > 0 && heap[parent].compareTo(heap[i]) < 0) {
            // exchange A[i] with A[parent]
            Task smallerTask = heap[parent];
            heap[parent] = heap[i];
            heap[i] = smallerTask;
            // set i to parent's previous position
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /**
     * adds a given task into the heap and maintains max heap property.
     * @param position index at which to insert the new task.
     * @param task a task to be inserted into the heap.
     * @throws HeapException
     */
    public void insert(int position, Task task) throws HeapException {
        if (position == heap.length) expandHeap();

        size++;
        Task tempTask = new Task(Integer.MAX_VALUE, null, null);
        heap[size - 1] = tempTask;
        
        increaseKey((size - 1), task);
    }

    /**
     * gets a task in the heap at a specified index.
     * @param index the index position at which to retrieve a task.
     * @return returns the task at a specified index in the heap.
     */
    public Task get(int index) {
        Task task = heap[index];
        return task;
    }

    /**
     * returns the last index number from a heap array of 0 to n elements.
     * @return returns the last index number of a heap array.
     */
    public int size() {
        int size = this.size;
        return size;
    }
    
    /**
     * makes a parent node at specified index larger than all children nodes
     * down to n.
     * @param index the index of a parent node.
     */
    private void heapify(int index) {
        int parent = index;
        int left = 2*index + 1;
        int right = 2*index + 2;
        int largest = parent;

        if (left < size && heap[left].compareTo(heap[parent]) > 0) {
            largest = left;
        }
        
        if (right < size && heap[right].compareTo(heap[parent]) > 0) {
            largest = right;
        }

        if (largest != parent) {
            Task smallerTask = heap[parent];
            heap[parent] = heap[largest];
            heap[largest] = smallerTask;
            // recurrsively goes down the tree
            heapify(largest);
        }
    }

    /**
     * makes a heap into a max-heap.
     */
    private void buildMaxHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * helper function that doubles a heap's array capacity.
     */
    private void expandHeap() {
        int newCapacity = heap.length * 2;
        heap = Arrays.copyOf(heap, newCapacity);
    }

    /**
     * checks if a heap is a max heap
     * @return return true if a heap is a max heap, false otherwise
     */
    protected boolean validMaxHeap() {
        int i = 0;
        // checks all parent nodes are greater than their children nodes
        while (i < size) {
            int parent = i;
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;

            if(left < size && heap[parent].compareTo(heap[left]) < 0) {
                return false;
            }

            if(right < size && heap[parent].compareTo(heap[right]) < 0) {
                return false;
            }

            i++;
        }

        return true;
    }
}
