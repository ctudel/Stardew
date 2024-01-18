import java.util.Random;

/**
 * TaskGenerator class that implements the TaskGeneratorInterface
 * 
 * creates game logic for Stardew Valley with probability to generate
 * tasks and update others
 * 
 * @author ctudel
 * @version Fall 2023
 * 
 */
public class TaskGenerator implements TaskGeneratorInterface {
    private Random rand;
    private int energy;
    private double taskGenerationProbability;

    /**
     * Create Task Generator Object
     * @param taskGenerationProbability
     */
    public TaskGenerator(double taskGenerationProbability) {
        this.taskGenerationProbability = taskGenerationProbability;
        this.energy = TaskGeneratorInterface.DEFAULT_ENERGY;
        rand = new Random();
    }

    /**
     * Create Task Generator Object with given generator seed
     * @param taskGenerationProbability
     * @param seed
     */
    public TaskGenerator(double taskGenerationProbability, long seed) {
        this(taskGenerationProbability);
        rand = new Random(seed);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
        Task task = new Task(hourCreated, taskType, taskDescription);
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
        int energyCost = taskType.getEnergyPerHour();
        // decrement energy based on the current task
        this.energy -= energyCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCurrentEnergyStorage() {
        energy = TaskGeneratorInterface.DEFAULT_ENERGY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentEnergyStorage() {
        int energy = this.energy;
        return energy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentEnergyStorage(int newEnergyNum) {
       this.energy = newEnergyNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean generateTask() {
        double genProb = rand.nextDouble();

        // check chances of a task generating
        if(genProb < taskGenerationProbability) {
            // check energy has not depleted
            if (energy < 0) return false;
            // return true if generating a task is possible
            return true;
        }

        else {
            // return false if generating a task is not possible
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        TaskInterface.TaskType mining = TaskInterface.TaskType.MINING;
        double currEnergy = getCurrentEnergyStorage();
        // get the probability amount of passing out
        double passoutProb = task.getTaskType().getPassingOutProbability();
        // get the probability amount of death
        double deathProb = task.getTaskType().getDyingProbability();
        
        // check passing out is likely
        if(unluckyProbability <= passoutProb) {
            // check death is likely while mining
            if(unluckyProbability <= deathProb && task.getTaskType().equals(mining)) {
                // energy storage decreases by 75% for death
                currEnergy -= (currEnergy * 0.75);
                this.setCurrentEnergyStorage((int)(currEnergy));
                return TaskGeneratorInterface.DEATH;    // returns 2 for death
            }

            else {
                // energy storage decrease by 50% for passing out
                currEnergy /= 2;
                this.setCurrentEnergyStorage((int)(currEnergy));
                return TaskGeneratorInterface.PASSED_OUT;   // returns 1 for passing out
            }
       }
       // player is lucky and does not encounter passing out or death
       else return TaskGeneratorInterface.SURVIVED;     // returns 0 for surviving
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(Task task, TaskInterface.TaskType taskType) {
        // returns string for a mining task
        if(taskType == Task.TaskType.MINING) {
            return "     Mining " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
        }
        // returns string for a fishign task
        if(taskType == Task.TaskType.FISHING) {
            return "     Fishing " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")" ;
        }
        // returns string for a farm maintenance task
        if(taskType == Task.TaskType.FARM_MAINTENANCE) {
            return "     Farm Maintenance " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
        }
        // returns string ofr a foraging task
        if(taskType == Task.TaskType.FORAGING) {
            return "     Foraging " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")" ;
        }
        // returns string for a feeding task
        if(taskType == Task.TaskType.FEEDING) {
            return "     Feeding " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
        }
        // returns string for a socializing task
        if(taskType == Task.TaskType.SOCIALIZING) {
            return "     Socializing " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
        }
        // returns string for no current task
        else { return "nothing to see here..."; }

    }
    
}
