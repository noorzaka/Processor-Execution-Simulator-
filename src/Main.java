import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter the number of processors: ");
        int numProcessors = myInput.nextInt();
        System.out.print("Enter the  number of cycles: ");
        int totalCycles = myInput.nextInt();
        System.out.print("Enter the name of the tasks file: ");
        String filePath = myInput.next();

        Simulator simulator = new Simulator(numProcessors, totalCycles, filePath);
        simulator.runSimulation(totalCycles);
    }
}
