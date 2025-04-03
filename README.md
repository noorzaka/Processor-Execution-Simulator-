# Processor Execution Simulator

## Introduction
The Processor Execution Simulator is designed to manage multiple tasks across multiple processors based on task priority, creation time, and execution time. It simulates task scheduling and execution, ensuring efficient utilization of processing resources.

## Features
- Simulates multiple processors executing tasks.
- Tasks are scheduled based on priority, execution time, and creation time.
- Implements abstraction, encapsulation, and efficient scheduling mechanisms.
- Uses a priority queue to optimize task retrieval.

## Class Relationships
1. **Simulator and Processor (Aggregation)**
   - The Simulator manages multiple Processor instances.
   - Processors exist independently and can be used elsewhere.

2. **Simulator and Task (Aggregation)**
   - The Simulator manages multiple Task instances.
   - Tasks are created and queued but exist independently of the Simulator.

3. **Simulator and Clock (Composition)**
   - The Simulator owns and manages a Clock instance.
   - If the Simulator is destroyed, the Clock is also destroyed.

4. **Simulator and Scheduler (Composition)**
   - The Scheduler is tightly integrated with the Simulator and does not exist independently.

5. **Scheduler and Task (Aggregation)**
   - The Scheduler manages tasks in a queue, but tasks exist independently.

6. **Processor and Task (Association)**
   - A Processor executes a Task, but tasks are not tied to a specific processor.

## Class Descriptions

### Task Class
- Represents a task with:
  - **ID**: Unique identifier.
  - **Creation Time**: When the task is created.
  - **Execution Time**: Number of cycles needed to complete the task.
  - **Priority**: High (1) or Low (0).
  - **Remaining Time**: Cycles required for completion.
- Implements the `Comparable` interface for sorting by creation time.

### Processor Class
- Represents a processor executing tasks.
- Attributes:
  - **ID**: Unique identifier.
  - **Current Task**: The task being executed.
  - **Remaining Time**: Time left for task completion.
- Methods:
  - `assignTask(Task task)`: Assigns a task to the processor.
  - `executeTask()`: Decreases remaining time by one cycle.
  - `isAvailable()`: Checks if the processor is idle.

### Scheduler Class
- Manages task scheduling using a priority queue.
- Attributes:
  - `PriorityQueue<Task> taskQueue`: A priority queue based on priority and execution time.
- Methods:
  - `addTask(Task task)`: Adds a task to the queue.
  - `getNextTask()`: Retrieves the highest-priority task.
  - `scheduleTasks(Processor[] processors)`: Assigns tasks to available processors.

### Clock Class
- Represents the system clock.
- Attributes:
  - `currentCycle`: Tracks the current simulation cycle.
- Methods:
  - `nextCycle()`: Increments the cycle count.

### Simulator Class
- Manages task scheduling and execution.
- Attributes:
  - **Processors**: Array of Processor objects.
  - **Scheduler**: Manages task execution.
  - **Clock**: Tracks simulation cycles.
  - **Tasks**: List of tasks loaded from a file.
- Methods:
  - `Simulator(int numProcessors, int totalCycles, String filePath)`: Initializes the simulator.
  - `runSimulation(int totalCycles)`: Runs the simulation.
  - `report()`: Displays processor execution status.

## Algorithm
1. **Initialization**
   - Create processors, scheduler, and clock.
   - Load tasks from a file, ensuring they are sorted by creation time.
2. **Simulation Loop**
   - Increment the clock cycle.
   - Add newly created tasks to the scheduler.
   - Assign available processors to tasks.
   - Execute tasks and update their remaining time.
   - Report the status of each processor.

## Exception Handling
1. **File Reading Errors**
   - Handles `IOException` when reading the task file.
   - Informs the user of invalid task formats.
2. **Task Sorting Errors**
   - Ensures tasks are sorted correctly before scheduling.

## Usage
1. Compile and run the program.
2. Provide the number of processors, simulation cycles, and task input file.
3. Observe processor execution and scheduling behavior in the output.



