import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulator {
    private final Processor[] processors;
    private final Scheduler scheduler;
    private final Clock clock;
    private final List<Task> tasks;

    public Simulator(int numProcessors, int totalCycles, String filePath) {
        processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor("P" + (i + 1));
        }
        scheduler = new Scheduler();
        clock = new Clock();
        tasks = new ArrayList<>();
        loadTasks(filePath);
    }
    private void loadTasks(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) {
                System.err.println("The file is empty.");
                return;
            }
            if (!line.matches("\\d+")) {
                System.err.println("The first line must contain the number of tasks.");
                return;
            }

            int numTasks = Integer.parseInt(line);
            int taskCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 3) {
                    System.err.println("Invalid task format: " + line);
                    continue;
                }

                String id = "T" + (tasks.size() + 1);
                int creationTime = Integer.parseInt(parts[0]);
                int executionTime = Integer.parseInt(parts[1]);
                int priority = Integer.parseInt(parts[2]);
                tasks.add(new Task(id, creationTime, executionTime, priority));
                taskCount++;
            }

            if (taskCount != numTasks) {
                System.err.println(" The number of tasks in the file (" + taskCount +
                        ") does not match the expected number of tasks (" + numTasks + ").");
            }

           // Collections.sort(tasks);
            System.out.println("Loaded " + tasks.size() + " tasks from file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void runSimulation(int totalCycles) {
        for (int cycle = 1; cycle <= totalCycles; cycle++) {
            clock.nextCycle();
            System.out.println("Cycle " + cycle + ":");

            List<Task> tasksToAdd = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getCreationTime() == clock.getCurrentCycle()) {
                    tasksToAdd.add(task);
                }
            }

            for (Task task : tasksToAdd) {
                scheduler.addTask(task);
                System.out.println("Task " + task.getId() + " created with priority " + task.getPriority() +
                        " and execution time " + task.getExecutionTime());
            }
            scheduler.scheduleTasks(processors);
            for (Processor processor : processors) {
                if (!processor.isAvailable()) {
                    processor.execute();
                }
                if (processor.isAvailable()) {
                    scheduler.scheduleTasks(new Processor[]{processor});
                }
            }

            report();
        }
    }

    private void report() {
        for (Processor processor : processors) {
            if (processor.getCurrentTask() != null) {
                System.out.println(processor.getId() + " is executing " + processor.getCurrentTask().getId() +
                        " (remaining time: " + processor.getRemainingTime() + ")");
            } else {
                System.out.println(processor.getId() + " is idle");
            }
        }
    }
}
