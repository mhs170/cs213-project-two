package transactionmanager;

public class Savings extends Account {
    protected boolean isLoyal; //loyal customer status
    public static final double ANNUAL_INTEREST_RATE = 0.04;
    public static final double MONTHLY_FEE = 25;
    public static final double INTEREST_IF_LOYAL = 0.0425;
    public static final double BALANCE_TO_WAIVE_FEE = 500;
    public static final int MONTHS_IN_YEAR = 12;

    /**
     * Creates a new saving's account
     *
     * @param holder  Profile information of the account holde
     * @param balance account's balance
     */
    public Savings(Profile holder, double balance) {
        super(holder, balance, ANNUAL_INTEREST_RATE, MONTHLY_FEE);
    }

    /**
     * Method to calculate and set account's monthly Interest
     *
     * @return account's monthly interest, 4% if not a loyal customer, 4
     * .25% if is a loyal customer
     */
    @Override
    public double monthlyInterest() {
        if (isLoyal) {
            return INTEREST_IF_LOYAL / MONTHS_IN_YEAR;
        }
        return ANNUAL_INTEREST_RATE / MONTHS_IN_YEAR;
    }

    /**
     * Method to set account's monthly Fee
     *
     * @return account's monthly fee, 0 if balance >= $500
     */
    @Override
    public double monthlyFee() {
        if (balance < BALANCE_TO_WAIVE_FEE) {
            return MONTHLY_FEE;
        }
        return 0;
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
