public class Processor {
    private final String id;
    private  Task currentTask;
    private  int remainingTime;

    public Processor(String id) {
        this.id = id;
        this.currentTask = null;
        this.remainingTime = 0;
    }

    public String getId() {
        return id;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public boolean isAvailable() {
        return currentTask == null;
    }

    public void assignTask(Task task) {
        this.currentTask = task;
        this.remainingTime = task.getExecutionTime();
    }

    public void execute() {
        if (currentTask != null) {
            remainingTime--;
            if (remainingTime == 0) {
                currentTask = null;
            }
        }
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}
