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
     * Print that the account is opened, or it's already in database
     * @param profile the profile of the account
     * @param accountType the account type
     */
    private void printStatus(
            Profile profile, String accountType, boolean opened
    ) {
        String extra;
        if(opened) extra = "opened.";
        else extra = "is already in the database.";

        System.out.printf("%s %s %s(%s) %s\n",
                profile.getFname(),
                profile.getLname(),
                profile.getDob(),
                accountType,
                extra);
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
            double initialDeposit,
            AccountDatabase database
    ) {
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        Checking account = new Checking(profile, initialDeposit);
        boolean openSuccess = database.open(account);
        printStatus(profile, "C", openSuccess);
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
            int campusCode,
            AccountDatabase database
    ) {
        Campus campus;
        switch(campusCode) {
            case 0 -> campus = Campus.NEWBRUNSWICK;
            case 1 -> campus = Campus.NEWARK;
            case 2 -> campus = Campus.CAMDEN;
            default -> {
                System.out.println("Invalid campus code.");
                return;
            }
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        CollegeChecking account = new CollegeChecking(profile, initialDeposit, campus);
        boolean openSuccess = database.open(account);
        printStatus(profile, "CC", openSuccess);
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
            int loyalCustomer,
            AccountDatabase database
    ) {
        boolean isLoyal;
        if(loyalCustomer == 0) {
            isLoyal = false;
        }
        else if (loyalCustomer == 1) {
            isLoyal = true;
        }
        else {
            System.out.println("Invalid command.");
            return;
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        Savings account = new Savings(profile, initialDeposit, isLoyal);
        boolean openSuccess = database.open(account);
        printStatus(profile, "S", openSuccess);
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
            double initialDeposit,
            AccountDatabase database
    ) {
        if(initialDeposit < 2000.00) {
            System.out.println(
                    "Minimum of $2000 to open a Money Market account."
            );
            return;
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        MoneyMarket account = new MoneyMarket(profile, initialDeposit, true, 0);
        boolean openSuccess = database.open(account);
        printStatus(profile, "MM", openSuccess);
    }

    /**
     * Parses input and runs the proper create account method
     * @param inputs array of user inputted strings
     */
    private void OpenAccount(String[] inputs, AccountDatabase database) {
        try {
            String accountType  = inputs[1];
            String firstName    = inputs[2];
            String lastName     = inputs[3];
            String dateOfBirthStr  = inputs[4];
            double initialDeposit = Double.parseDouble(inputs[5]);


            Date dateOfBirth = createDate(dateOfBirthStr);
            if(dateOfBirth == null) {
                System.out.printf("DOB invalid: %s not a" +
                        " valid calendar date!%n", dateOfBirthStr);
                return;
            }

            if(initialDeposit <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative.");
                return;
            }

            switch (accountType) {
                case "C" -> OpenCheckingAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        database
                );
                case "CC" -> OpenCollegeCheckingAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        Integer.parseInt(inputs[6]), //campus code
                        database
                );
                case "S" -> OpenSavingsAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        Integer.parseInt(inputs[6]), //loyalty
                        database
                );
                case "MM" -> OpenMoneyMarketAccount(
                        firstName, lastName, dateOfBirth, initialDeposit,
                        database
                );
                default -> System.out.println("Invalid command!");
            }
        } catch(IndexOutOfBoundsException exp) {
            System.out.println("Missing data for opening an account.");
        }
    }

    /**
     * Close an account
     * @param inputs user inputted strings that identify account
     */
    private void CloseAccount(String[] inputs) {
        String accountType  = inputs[1];
        String firstName    = inputs[2];
        String lastName     = inputs[3];
        String dateOfBirthStr  = inputs[4];


    }

    /**
     * Deposit to an account
     * @param inputs user inputted strings that identify account
     */
    private void DepositToAccount(String[] inputs) {
        try {
            String accountType  = inputs[1];
            String firstName    = inputs[2];
            String lastName     = inputs[3];
            String dateOfBirthStr  = inputs[4];
            double amount = Double.parseDouble(inputs[5]);
            if(amount <= 0) {
                System.out.println("Deposit - amount cannot be" +
                        " 0 or negative.");
                return;
            }
            Profile dummyProfile = new Profile(firstName,lastName,dateOfBirthStr);
            Account dummyAccount = new Account(Profile, amount, 0, 0) {
                @Override
                public double monthlyInterest() {
                    return 0;
                }

                @Override
                public double monthlyFee() {
                    return 0;
                }

                @Override
                public int compareTo(Account o) {
                    return 0;
                }
            };

        } catch (NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Withdraw from an account
     * @param inputs user inputted strings that identify account
     */
    private void WithdrawFromAccount(String[] inputs) {
        try {
            String accountType  = inputs[1];
            String firstName    = inputs[2];
            String lastName     = inputs[3];
            String dateOfBirthStr  = inputs[4];
            double amount = Double.parseDouble(inputs[5]);

        } catch (NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }
    /**
     * Runs the user-input TransactionManager
     */
    public void run(){

        System.out.println("Transaction Manager is running.\n");

        AccountDatabase database =
                new AccountDatabase(new Account[]{}, 0);

        Scanner scanner = new Scanner(System.in);
        scannerLoop: while (scanner.hasNextLine()) {
            String commandLine  = scanner.nextLine();
            String[] inputs     = commandLine.split("\\s+");
            String command      = inputs[0];

            switch (command) {
                case "O" -> OpenAccount(inputs, database);
                case "C" -> CloseAccount(inputs);
                case "D" -> DepositToAccount(inputs);
                case "W" -> WithdrawFromAccount(inputs);
                case "P" -> {database.printSorted();}
                case "PI" -> {database.printFeesAndInterests();}
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
