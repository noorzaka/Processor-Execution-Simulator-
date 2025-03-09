import java.util.PriorityQueue;

public class Scheduler {
    private final PriorityQueue<Task> taskQueue;

    public Scheduler() {
        taskQueue = new PriorityQueue<>((t1, t2) -> {
            if (t1.getPriority() != t2.getPriority()) {
                return t2.getPriority() - t1.getPriority();
            }
            if (t1.getExecutionTime() != t2.getExecutionTime()) {
                return t2.getExecutionTime() - t1.getExecutionTime();
            }
            return t1.getCreationTime() - t2.getCreationTime();
        });
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    private Task getNextTask() {
        return taskQueue.poll();
    }

    public void scheduleTasks(Processor[] processors) {
        for (Processor processor : processors) {
            if (processor.isAvailable()) {
                Task nextTask = getNextTask();
                if (nextTask != null) {
                    processor.assignTask(nextTask);
                }
            }
        }
    }
}
