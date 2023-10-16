package transactionmanager;

import java.util.Calendar;
import java.util.Scanner;


/**
 *  This class parses user input and interacts with the backend model,
 *  then prints output
 *
 * @author Mohammed Salama, Dakshal Panicker
 */
public class TransactionManager {

    /**
     * Method to test if account holder is over 16
     *
     * @param dob date of birth
     * @return true if over 16, false otherwise
     */
    private boolean overSixteen(Date dob) {
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) + 1;
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        int ageDifference = currentYear - dob.getYear();

        if (currentMonth < dob.getMonth() || (currentMonth == dob.getMonth()
                && currentDay < dob.getDay())) {
            ageDifference--;
        }

        return ageDifference >= 16;
    }

    /**
     * Method to test if account holder is under 24
     *
     * @param dob date of birth
     * @return true if under 24, false otherwise
     */
    private boolean underTwentyFour(Date dob) {
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentMonth = today.get(Calendar.MONTH) + 1;
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        int ageDifference = currentYear - dob.getYear();

        if (currentMonth < dob.getMonth() || (currentMonth == dob.getMonth()
                && currentDay < dob.getDay())) {
            ageDifference--;
        }

        return ageDifference < 24;
    }

    /**
     * Create and return Date object based on mm/dd/yyyy string input.
     * If input is invalid, then return null.
     *
     * @param date a string in mm/dd/yyyy format
     * @return Date object if valid date string, null if invalid
     */
    private Date createDate(String date) {
        String dateFormatRegex =
                "^(\\d{1,2}(/)\\d{1,2}(/)\\d{4})$";
        if (!date.matches(dateFormatRegex)) return null;

        String[] splitDate = date.split("/");
        int month = Integer.parseInt(splitDate[0]);
        int day = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);

        Date dateObj = new Date(year, month, day);
        if (!dateObj.isValid()) return null;

        return dateObj;
    }

    /**
     * Validate a date
     *
     * @param date the date to validate
     * @return true if valid, false otherwise
     */
    private boolean dateIsValid(Date date, String dateStr) {
        if (date == null) {
            System.out.printf("DOB invalid: %s not a" +
                    " valid calendar date!%n", dateStr);
            return false;
        }

        if (date.isToday() || date.isInFuture()) {
            System.out.printf("DOB invalid: %s cannot" +
                    " be today or a future day.%n", dateStr);
            return false;
        }
        return true;
    }

    /**
     * Print some account status
     *
     * @param profile     the profile of the account
     * @param accountType the account type
     * @param status      the status to print afterwards
     */
    private void printStatus(
            Profile profile, String accountType, String status
    ) {
        System.out.printf("%s %s %s(%s) %s\n",
                profile.getFname(),
                profile.getLname(),
                profile.getDob(),
                accountType,
                status);
    }

    /**
     * Print that the account is opened, or it's already in database
     *
     * @param profile     the profile of the account
     * @param accountType the account type
     */
    private void printOpenStatus(
            Profile profile, String accountType, boolean opened
    ) {
        String status;
        if (opened) status = "opened.";
        else status = "is already in the database.";
        printStatus(profile, accountType, status);
    }

    /**
     * Print that the account is opened, or it's already in database
     *
     * @param profile     the profile of the account
     * @param accountType the account type
     */
    private void printWithdrawStatus(
            Profile profile, String accountType, boolean withdrawSuccess
    ) {
        String status;
        if (withdrawSuccess) status = "Withdraw - balance updated.";
        else status = "Withdraw - insufficient fund.";
        printStatus(profile, accountType, status);
    }

    private void printDepositStatus(
            Profile profile, String accountType, boolean depositSuccess
    ) {
        String status;
        if (depositSuccess) status = "Deposit - balance updated.";
        else status = "Deposit - amount cannot be 0 or negative.";
        printStatus(profile, accountType, status);
    }

    /**
     * Create checking account
     *
     * @param firstName      first name of user
     * @param lastName       last name of user
     * @param dateOfBirth    DOB of user, in past
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
        printOpenStatus(profile, "C", openSuccess);
    }

    /**
     * Create college checking account
     *
     * @param firstName      first name of user
     * @param lastName       last name of user
     * @param dateOfBirth    DOB of user, in past
     * @param initialDeposit initial deposit for account
     * @param campusCode     0, 1, 2 codes for different campuses
     */
    private void OpenCollegeCheckingAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit,
            int campusCode,
            AccountDatabase database
    ) {
        if (!underTwentyFour(dateOfBirth)){
            System.out.println("DOB invalid: " + dateOfBirth + " over 24.");
            return;
        }
        Campus campus;
        switch (campusCode) {
            case 0 -> campus = Campus.NEW_BRUNSWICK;
            case 1 -> campus = Campus.NEWARK;
            case 2 -> campus = Campus.CAMDEN;
            default -> {
                System.out.println("Invalid campus code.");
                return;
            }
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        CollegeChecking account = new CollegeChecking(profile,
                initialDeposit, campus);
        boolean openSuccess = database.open(account);
        printOpenStatus(profile, "CC", openSuccess);
    }

    /**
     * Create savings account
     *
     * @param firstName      first name of user
     * @param lastName       last name of user
     * @param dateOfBirth    DOB of user, in past
     * @param initialDeposit initial deposit for account
     * @param loyalCustomer  0 or 1 based on whether customer is loyal
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
        if (loyalCustomer == 0) {
            isLoyal = false;
        } else if (loyalCustomer == 1) {
            isLoyal = true;
        } else {
            System.out.println("Invalid command.");
            return;
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        Savings account = new Savings(profile, initialDeposit, isLoyal);
        boolean openSuccess = database.open(account);
        printOpenStatus(profile, "S", openSuccess);
    }

    /**
     * Create money market account
     *
     * @param firstName      first name of user
     * @param lastName       last name of user
     * @param dateOfBirth    DOB of user, in past
     * @param initialDeposit initial deposit for account
     */
    private void OpenMoneyMarketAccount(
            String firstName,
            String lastName,
            Date dateOfBirth,
            double initialDeposit,
            AccountDatabase database
    ) {
        if (initialDeposit < 2000.00) {
            System.out.println(
                    "Minimum of $2000 to open a Money Market account."
            );
            return;
        }
        Profile profile = new Profile(firstName, lastName, dateOfBirth);
        MoneyMarket account = new MoneyMarket(profile, initialDeposit,
                true, 0);
        boolean openSuccess = database.open(account);
        printOpenStatus(profile, "MM", openSuccess);
    }

    /**
     * Parses input and runs the proper create account method
     *
     * @param inputs array of user inputted strings
     */
    private void OpenAccount(String[] inputs, AccountDatabase database) {
        try {
            String accountType = inputs[1];
            String firstName = inputs[2];
            String lastName = inputs[3];
            String dateOfBirthStr = inputs[4];
            double initialDeposit = Double.parseDouble(inputs[5]);


            Date dateOfBirth = createDate(dateOfBirthStr);
            if (!dateIsValid(dateOfBirth, dateOfBirthStr)) return;

            if (!overSixteen(dateOfBirth)){
                System.out.println("DOB invalid: " + dateOfBirth + " under 16.");
                return;
            }

            if (initialDeposit <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative" +
                        ".");
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
        } catch (IndexOutOfBoundsException exp) {
            System.out.println("Missing data for opening an account.");
        } catch (NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Method that prints result of close method
     *
     * @param profile     Profile associated with account
     * @param accountType type of account
     * @param closed      if account is closed, true
     */
    private void printCloseStatus(
            Profile profile, String accountType, boolean closed
    ) {
        if (closed) {
            String status = "has been closed.";
            printStatus(profile, accountType, status);
        }
    }

    /**
     * Close Checking account
     *
     * @param accountType account type
     * @param firstName   first name of holder
     * @param lastName    last name of holder
     * @param dob         date of birth of holder
     */
    private void CloseCheckingAccount(String accountType, String firstName,
                                      String lastName, Date dob,
                                      AccountDatabase database) {
        Profile holder = new Profile(firstName, lastName, dob);
        Checking account = new Checking(holder, 0);
        if (!database.contains(account)){
            printStatus(holder, accountType, "is not in the database.");
        }
        boolean close = database.close(account);
        printCloseStatus(holder, accountType, close);
    }

    /**
     * Close College Checking account
     *
     * @param accountType account type
     * @param firstName   first name of holder
     * @param lastName    last name of holder
     * @param dob         date of birth of holder
     */
    private void CloseCollegeCheckingAccount(String accountType,
                                             String firstName,
                                             String lastName, Date dob,
                                             AccountDatabase database) {
        Profile holder = new Profile(firstName, lastName, dob);
        CollegeChecking account = new CollegeChecking(holder, 0,
                Campus.NEWARK);
        if (!database.contains(account)){
            printStatus(holder, accountType, "is not in the database.");
        }
        boolean close = database.close(account);
        printCloseStatus(holder, accountType, close);
    }

    /**
     * Close Savings account
     *
     * @param accountType account type
     * @param firstName   first name of holder
     * @param lastName    last name of holder
     * @param dob         date of birth of holder
     */
    private void CloseSavings(String accountType, String firstName,
                              String lastName, Date dob,
                              AccountDatabase database) {
        Profile holder = new Profile(firstName, lastName, dob);
        Savings account = new Savings(holder, 0, false);
        if (!database.contains(account)){
            printStatus(holder, accountType, "is not in the database.");
        }
        boolean close = database.close(account);
        printCloseStatus(holder, accountType, close);
    }

    /**
     * Close Money Market Savings account
     *
     * @param accountType account type
     * @param firstName   first name of holder
     * @param lastName    last name of holder
     * @param dob         date of birth of holder
     */
    private void CloseMoneyMarket(String accountType, String firstName,
                                  String lastName, Date dob,
                                  AccountDatabase database) {
        Profile holder = new Profile(firstName, lastName, dob);
        MoneyMarket account = new MoneyMarket(holder, 0, false
                , 0);
        if (!database.contains(account)){
            printStatus(holder, accountType, "is not in the database.");
        }
        boolean close = database.close(account);
        printCloseStatus(holder, accountType, close);
    }

    /**
     * Close an account
     *
     * @param inputs user inputted strings that identify account
     */
    private void CloseAccount(String[] inputs, AccountDatabase database) {
        try {
            String accountType = inputs[1];
            String firstName = inputs[2];
            String lastName = inputs[3];
            String dateOfBirthStr = inputs[4];

            Date dob = createDate(dateOfBirthStr);
            if(!dateIsValid(dob, dateOfBirthStr)) return;

            if (!dateIsValid(dob, dateOfBirthStr)){
                return;
            }

            switch (accountType) {
                case "C" -> CloseCheckingAccount(
                        accountType, firstName, lastName, dob,
                        database
                );
                case "CC" -> CloseCollegeCheckingAccount(
                        accountType, firstName, lastName, dob,
                        database
                );
                case "S" -> CloseSavings(
                        accountType, firstName, lastName, dob,
                        database
                );
                case "MM" -> CloseMoneyMarket(
                        accountType, firstName, lastName, dob,
                        database
                );

                default -> System.out.println("Invalid command!");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for closing an account.");
        }

    }

    /**
     * Deposit to an account
     *
     * @param inputs user inputted strings that identify account
     */
    private void DepositToAccount(String[] inputs, AccountDatabase database) {
        try {
            String accountType = inputs[1];
            String firstName = inputs[2];
            String lastName = inputs[3];
            String dateOfBirthStr = inputs[4];
            double amount = Double.parseDouble(inputs[5]);

            if (amount <= 0) {
                System.out.println("Deposit - amount cannot be" +
                        " 0 or negative.");
                return;
            }

            Date dateOfBirth = createDate(dateOfBirthStr);
            if (!dateIsValid(dateOfBirth, dateOfBirthStr)) return;

            Profile dummyProfile = new Profile(firstName, lastName,
                    dateOfBirth);
            Account dummyAccount;
            switch (accountType) {
                case "C" -> dummyAccount = new Checking(dummyProfile, amount);
                case "CC" -> dummyAccount = new CollegeChecking(dummyProfile,
                        amount, Campus.NEWARK);
                case "S" -> dummyAccount = new Savings(dummyProfile, amount,
                        false);
                case "MM" ->
                        dummyAccount = new MoneyMarket(dummyProfile, amount
                                , false, 0);
                default -> {
                    System.out.println("Invalid command.");
                    return;
                }
            }

            if (!(database.contains(dummyAccount))) {
                printStatus(dummyProfile, accountType,
                        "is not in the database.");
                return;
            }
            database.deposit(dummyAccount);
            printDepositStatus(dummyProfile, accountType, true);

        } catch (NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Withdraw from an account
     *
     * @param inputs user inputted strings that identify account
     */
    private void WithdrawFromAccount(String[] inputs,
                                     AccountDatabase database) {
        try {
            String accountType = inputs[1];
            String firstName = inputs[2];
            String lastName = inputs[3];
            String dateOfBirthStr = inputs[4];
            double amount = Double.parseDouble(inputs[5]);

            if (amount <= 0) {
                System.out.println("Withdraw -" +
                        " amount cannot be 0 or negative.");
                return;
            }

            Date dateOfBirth = createDate(dateOfBirthStr);
            if (!dateIsValid(dateOfBirth, dateOfBirthStr)) return;

            Profile profile = new Profile(firstName, lastName, dateOfBirth);

            Account dummy;
            switch (accountType) {
                case "C" -> dummy = new Checking(
                        profile, amount);
                case "CC" -> dummy = new CollegeChecking(
                        profile, amount, Campus.NEWARK);
                case "S" -> dummy = new Savings(
                        profile, amount, false);
                case "MM" -> dummy = new MoneyMarket(
                        profile, amount, false, 0);
                default -> {
                    System.out.println("Invalid command.");
                    return;
                }
            }

            if (!(database.contains(dummy))) {
                printStatus(profile, accountType,
                        "is not in the database.");
                return;
            }

            boolean isSuccess = database.withdraw(dummy);
            printWithdrawStatus(profile, accountType, isSuccess);

        } catch (NumberFormatException exp) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Runs the user-input TransactionManager
     */
    public void run() {

        System.out.println("Transaction Manager is running.\n");

        AccountDatabase database =
                new AccountDatabase(new Account[]{}, 0);

        Scanner scanner = new Scanner(System.in);
        scannerLoop:
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine();
            String[] inputs = commandLine.split("\\s+");
            String command = inputs[0];

            switch (command) {
                case "O" -> OpenAccount(inputs, database);
                case "C" -> CloseAccount(inputs, database);
                case "D" -> DepositToAccount(inputs, database);
                case "W" -> WithdrawFromAccount(inputs, database);
                case "P" -> {
                    database.printSorted();
                }
                case "PI" -> {
                    database.printFeesAndInterests();
                }
                case "UB" -> {
                    database.printUpdatedBalances();
                }
                case "Q" -> {
                    System.out.println("Transaction Manager is terminated.");
                    break scannerLoop;
                }
                case "" -> {
                }
                default -> System.out.println("Invalid command!");
            }
        }
    }
}
