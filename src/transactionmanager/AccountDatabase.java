package transactionmanager;

public class AccountDatabase {
    private static final int NOT_FOUND = -1;
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    /**
     * Create a new Accounts Database
     *
     * @param accounts initial array
     * @param numAcct  initial number of accounts
     */
    AccountDatabase(Account[] accounts, int numAcct) {
        this.accounts = accounts;
        this.numAcct = numAcct;
    }

    /**
     * Method that searches for an account in the database
     *
     * @param account Account to search for
     * @return index of the account in the array if found, otherwise
     * returns -1.
     */
    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].compareTo(account) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search for an account in the array

    /**
     * Method to increase the capacity of the array by 4.
     */
    private void grow() {
        Account[] newEventArray = new Account[accounts.length + 4];
        for (int i = 0; i < accounts.length; i++) {
            newEventArray[i] = accounts[i];
        }
        accounts = newEventArray;
    } //increase the capacity by 4

    /**
     * Method that checks if an account is in the database.
     *
     * @param account account to search for
     * @return true if found, false otherwise.
     */
    public boolean contains(Account account) {
        return find(account) != NOT_FOUND;
        //overload if necessary
    }

    /**
     * Method that opens an account (adds account to end of array)
     *
     * @param account account to add to database
     * @return true after account is added
     */
    public boolean open(Account account) {
        if (contains(account)) {
            return false;
        }
        if (numAcct == accounts.length) {
            grow();
        }
        accounts[numAcct] = account;
        numAcct += 1;
        return true;
    } //add a new account

    /**
     * Method that closes an account (removes account from array)
     *
     * @param account account to close/remove
     * @return true after account is removed, false if account is not found
     */
    public boolean close(Account account) {
        int accountIndex = find(account);
        if (accountIndex != NOT_FOUND) {
            for (int i = accountIndex; i < accounts.length - 1; i++) {
                accounts[i] = accounts[i + 1]; //removes the event
            }
            accounts[accounts.length - 1] = null; //sets last element to null
            numAcct -= 1;
            return true;
        }
        return false;
    } //remove the given account

    public boolean withdraw(Account account) {
        int amountToWithdraw = dummyAccount.balance;
        if (contains(account)) {
        }
        return false;
    } //check if account exists using contains(), update balance, return
    // false if insufficient fund

    public void deposit(Account account) {

        if (contains(account)) {

        }
    } //check if account exists using contains(), update balance.

    /**
     * Method to display all the accounts in the account database, sorted
     * by the account types. For the same account type, sort by the account
     * holder’s profile (last name, first name and dob.)
     */
    public void printSorted() {
        if (numAcct == 0) {
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("*Accounts sorted by account type and profile.");
        for (int i = 0; i < numAcct - 1; i++) {
            for (int j = 0; j < numAcct - i - 1; j++) {
                if (compareAccountType(accounts[j], accounts[j + 1]) > 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[j + 1];
                    accounts[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");
    } //sort by account type and profile

    /**
     * Method that compares two account types alphabetically
     * @param account1 1st account to compare
     * @param account2 2nd account to compare
     * @return result of the comparison
     */
    private int compareAccountType(Account account1, Account account2) {
        int accountTypeComparison =
                account1.getClass().getSimpleName().compareTo(account2.
                        getClass().getSimpleName());

        if (accountTypeComparison == 0) {
            // If account types are the same, compare by profile information
            return account1.getHolder().compareTo(account2.getHolder());
        }

        return accountTypeComparison;
    }

    public void printFeesAndInterests() {
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("*list of accounts with fee and monthly interest");
        for (int i = 0; i < numAcct - 1; i++) {
            for (int j = 0; j < numAcct - i - 1; j++) {

            }
        }
    } //calculate interests/fees

    public void printUpdatedBalances() {} //apply the interests/fees
}