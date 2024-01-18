/**
 * MyPriorityQueue class that extends MaxHeap
 * Creates a priority queue for tasks based on the order to complete them
 * 
 * @author ctudel
 * @version Fall 2023
 * 
 */
public class MyPriorityQueue extends MaxHeap implements PriorityQueueInterface {
    private MaxHeap heap;

    /**
     * Creates a Priority Queue object
     */
    public MyPriorityQueue() {
       heap = new MaxHeap(); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(Object task) {
        // source: https://stackoverflow.com/questions/1893349/dynamically-converting-java-object-of-object-class-to-a-given-class-when-class-n
        Task newTask = Task.class.cast(task);
        
        try {
            // insert the new task into the priority queue
            heap.insert(heap.size, newTask);
        } 
        
        catch (HeapException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task dequeue() {
        Task max = null;

        try {
            // extract and return the first task in the priority queue
            max = heap.extractMax();
        } catch (HeapException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return max;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int timeToIncrementPriority, int maxPriority) {
        Task task = null;
        try {
            // updates all task elements from 0 to n elements
            for (int i = 0; i < heap.size; i++) {
                // gets the ith task from the heap
                task = heap.get(i);
                int currTaskPriority = task.getPriority();
                // increment ith task's waiting time
                task.incrementWaitingTime();

                // check if a task needs its priority incremented
                if(task.getWaitingTime() >= timeToIncrementPriority) {
                    // reset ith task's waiting time
                    task.resetWaitingTime();
                    // check the priority of the task is not at the highest possible priority
                    if(currTaskPriority < maxPriority) {
                        // increment ith task's priority
                        currTaskPriority += 1;
                        task.setPriority(currTaskPriority);
                        heap.increaseKey(i, task);
                    }
                }
            }
        } 
        // catch exception if thrown as print a message and stack trace
        catch(HeapException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}