/**
 * Task obect class
 * 
 * creates a task that is used to represent quest tasks in Stardew Valley
 * and hold priority over other tasks
 * 
 * @author ctudel
 * @version Fall 2023
 * 
 */
public class Task implements TaskInterface, Comparable<Task>{
    private final int DEFAULT_WAIT_TIME = 0;
    private final int DEFAULT_PRIORITY = 0;

    private int priority;
    private TaskInterface.TaskType taskType;
    private int waitTime;
    protected int hourCreated;
    private String description;


    /**
     * creates a task object with default waitTime and priority values.
     * @param hourCreated the hour task was created
     * @param type task type of task object
     * @param description a task description
     */
    public Task(int hourCreated, TaskInterface.TaskType type, String description) {
        this.waitTime = DEFAULT_WAIT_TIME;
        this.priority = DEFAULT_PRIORITY;
        this.hourCreated = hourCreated;
        this.description = description;
        this.taskType = type;
    }
    
    /**
     * creates a task object with a specified initial priority.
     * @param priority priority task
     * @param hourCreated the hour task was created
     * @param type task type of task object
     * @param description a task description
     */
    public Task(int priority, int hourCreated, TaskInterface.TaskType type, String description) {
        this(hourCreated, type, description);
        this.priority = priority;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getPriority() {
        int priority = this.priority;
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskInterface.TaskType getTaskType() {
        TaskInterface.TaskType type = this.taskType;
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskDescription() {
        String desc = this.description;
        return desc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementWaitingTime() {
        this.waitTime++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetWaitingTime() {
       this.waitTime = DEFAULT_WAIT_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWaitingTime() {
        int waitTime = this.waitTime;
        return waitTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Task o) {
        // check this task has a greater priority than other task
        if(this.getPriority() > o.getPriority()) return 1;
        if(this.getPriority() < o.getPriority()) return -1;
        // check this task was created before other task
        if(this.hourCreated < o.hourCreated) return 1;
        if(this.hourCreated > o.hourCreated) return -1;
        // this task has the same priority and was created same time as other task
        else return 0;
    } 

    /**
     * {@inheritDoc}
     */
    public String toString() {
        // return string for mining task
        if(taskType == Task.TaskType.MINING) {
            return "MINING " + this.getTaskDescription() + "at Hour: " + hourCreated + ":00";
        }
        // return string for fishing task
        if(taskType == Task.TaskType.FISHING) {
            return "FISHING " + this.getTaskDescription() + " at Hour: " + hourCreated + ":00";
        }
        // return string for farm maintenance task
        if(taskType == Task.TaskType.FARM_MAINTENANCE) {
            return "FARM_MAINTENANCE " + this.getTaskDescription() + " at Hour: " + hourCreated + ":00";
        }
        // return string for foraging task
        if(taskType == Task.TaskType.FORAGING) {
            return "FORAGING " + this.getTaskDescription() + " at Hour: " + hourCreated + ":00";
        }
        // return string for feeding task
        if(taskType == Task.TaskType.FEEDING) {
            return "FEEDING " + this.getTaskDescription() + " at Hour: " + hourCreated + ":00";
        }
        // return string for socializing task
        if(taskType == Task.TaskType.SOCIALIZING) {
            return "SOCIALIZING " + this.getTaskDescription() + " at Hour: " + hourCreated + ":00";
        }
        // return string for no current task
        else { return "nothing to see here"; }
    }
}