public class Task implements Comparable<Task> {
    private final String id;
    private final int creationTime;
    private final int executionTime;
    private final int priority;

    public Task(String id, int creationTime, int executionTime, int priority) {
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task o) {
        if (this.creationTime < o.creationTime) return -1;
        if (this.creationTime > o.creationTime) return +1;
        return 0;
    }
}
