package transactionmanager;

public class CollegeChecking extends Checking {
    private Campus campus; //campus code
    public static final double ANNUAL_INTEREST_RATE = 0.01;
    public static final double MONTHLY_FEE = 0;

    /**
     * Creates a new college checking account
     * @param holder Profile information of the account holder
     * @param balance Balance of the college checking account
     * @param campus Campus code associated with account
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }
}
