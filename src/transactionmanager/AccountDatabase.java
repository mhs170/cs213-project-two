package transactionmanager;

public class AccountDatabase {
    private static final int NOT_FOUND = -1;
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    /**
     * Method that searches for an account in the database
     * @param account Account to search for
     * @return index of the account in the array if found, otherwise returns -1.
     */
    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
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
     * @param account account to search for
     * @return true if found, false otherwise.
     */
    public boolean contains(Account account) {
        if (find(account) != NOT_FOUND) {
            return true;
        }
        return false;
        //overload if necessary
    }

    /**
     * Method that opens an account (adds account to end of array)
     * @param account account to add to database
     * @return true after account is added
     */
    public boolean open(Account account) {
        if (numAcct == accounts.length) {
            grow();
        }
        accounts[numAcct] = account;
        numAcct += 1;
        return true;
    } //add a new account

    /**
     * Method that closes an account (removes account from array)
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
        if (contains(account)){

        }
        return false;
    } //check if account exists using contains(), update balance, return
    // false if insufficient fund

    public void deposit(Account account) {
        if (contains(account)){

        }
    } //check if account exists using contains(), update balance.

    public void printSorted() {
    } //sort by account type and profile

    public void printFeesAndInterests() {
    } //calculate interests/fees

    public void printUpdatedBalances() {
    } //apply the interests/fees
}