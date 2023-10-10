package transactionmanager;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Cretaes a new Profile object
     * @param fname first name of account holder
     * @param lname last name of account holder
     * @param dob date of birth of account holder
     */
    Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Getter method to output first name of account holder
     * @return first name of account holder
     */
    public String getFname(){
        return fname;
    }

    /**
     * Getter method to output last name of account holder
     * @return last name of account holder
     */
    public String getLname(){
        return lname;
    }

    /**
     * Getter method to output date of birth of account holder
     * @return date of birth of account holder
     */
    public Date getDob(){
        return dob;
    }

    @Override
    public int compareTo(Profile o) {
        return 0; //REPLACE WITH REAL METHOD
    }
}
