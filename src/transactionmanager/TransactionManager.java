package transactionmanager;

import java.util.Scanner;

public class TransactionManager {

    /**
     * Create and return Date object based on mm/dd/yyyy string input.
     * If input is invalid, then return null.
     * @param date a string in mm/dd/yyyy format
     * @return Date object if valid date string, null if invalid
     */
    private Date createDate(String date) {
        String dateFormatRegex =
                "^(\\d{1,2}(/)\\d{1,2}(/)\\d{4})$";
        if(!date.matches(dateFormatRegex)) return null;

        String[] splitDate = date.split("/");
        int month = Integer.parseInt(splitDate[0]);
        int day = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        Date dateObj = new Date(year, month, day);
        if(!dateObj.isValid()) return null;

        return dateObj;
    }

    /**
     * Create checking account
     * @param firstName first name of user
     * @param lastName last name of user
     * @param dateOfBirth DOB of user, in past
     * @param initialDeposit initial deposit for account
     */
    private void OpenCheckingAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit
    ) {

    }

    /**
     * Create college checking account
     * @param firstName first name of user
     * @param lastName last name of user
     * @param dateOfBirth DOB of user, in past
     * @param initialDeposit initial deposit for account
     * @param campusCode 0, 1, 2 codes for different campuses
     */
    private void OpenCollegeCheckingAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit,
            int campusCode
    ) {
        if(campusCode != 0 && campusCode != 1 && campusCode != 2) {
            System.out.println("Invalid campus code.");
        }
    }

    /**
     * Create savings account
     * @param firstName first name of user
     * @param lastName last name of user
     * @param dateOfBirth DOB of user, in past
     * @param initialDeposit initial deposit for account
     * @param loyalCustomer 0 or 1 based on whether customer is loyal
     */
    private void OpenSavingsAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit,
            int loyalCustomer
    ) {
        if(loyalCustomer != 0 && loyalCustomer != 1) {
            System.out.println("Invalid command.");
        }
    }

    /**
     * Create money market account
     * @param firstName first name of user
     * @param lastName last name of user
     * @param dateOfBirth DOB of user, in past
     * @param initialDeposit initial deposit for account
     */
    private void OpenMoneyMarketAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit
    ) {
        if(initialDeposit < 2000.00) {
            System.out.println(
                    "Minimum of $2000 to open a Money Market account."
            );
        }
    }

    /**
     * Parses input and runs the proper create account method
     * @param inputs array of user inputted strings
     */
    private void OpenAccount(String[] inputs) {
        try {
            String accountType  = inputs[1];
            String firstName    = inputs[2];
            String lastName     = inputs[3];
            String dateOfBirthStr  = inputs[4];
            double initialDeposit = Integer.parseInt(inputs[5]);

            Date dateOfBirth = createDate(dateOfBirthStr);
            if(dateOfBirth == null) {
                System.out.printf("DOB invalid: %s not a" +
                        " valid calendar date!%n", dateOfBirthStr);
            }

            switch (accountType) {
                case "C" -> OpenCheckingAccount(
                        firstName, lastName, dateOfBirth, initialDeposit
                );
                case "CC" -> OpenCollegeCheckingAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        Integer.parseInt(inputs[5]) //campus code
                );
                case "S" -> OpenSavingsAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        Integer.parseInt(inputs[5]) //loyalty
                );
                case "MM" -> OpenMoneyMarketAccount(
                        firstName, lastName, dateOfBirth, initialDeposit
                );
                default -> System.out.println("Invalid command!");
            }
        } catch(IndexOutOfBoundsException exp) {
            System.out.println("Missing data for opening an account.");
        } catch(NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Runs the user-input TransactionManager
     */
    public void run(){
        System.out.println("Transaction Manager is running.\n");

        Scanner scanner = new Scanner(System.in);
        scannerLoop: while (scanner.hasNextLine()) {
            String commandLine  = scanner.nextLine();
            String[] inputs     = commandLine.split("\\s+");
            String command      = inputs[0];

            switch (command) {
                case "O" -> OpenAccount(inputs);
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
