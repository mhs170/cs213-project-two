package transactionmanager;

import java.util.Scanner;

public class TransactionManager {
    public void run(){
        System.out.println("Transaction Manager is running.\n");

        Scanner scanner = new Scanner(System.in);
        scannerLoop: while (scanner.hasNextLine()) {
            String commandLine  = scanner.nextLine();
            String[] inputs     = commandLine.split("\\s+");
            String command      = inputs[0];

            switch (command) {
                case "O" -> {}
                case "C" -> {}
                case "D" -> {}
                case "W" -> {}
                case "P" -> {}
                case "PI" -> {}
                case "UB" -> {}
                case "Q"    -> {
                    System.out.println("Transaction Manager is terminated.");
                    break scannerLoop;
                }
                case "" -> {}
                default -> System.out.println("Invalid command!");
            }
        }
    }
}
