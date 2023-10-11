package transactionmanager;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Cretaes a new Profile object
     *
     * @param fname first name of account holder
     * @param lname last name of account holder
     * @param dob   date of birth of account holder
     */
    Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Getter method to output first name of account holder
     *
     * @return first name of account holder
     */
    public String getFname() {
        return fname;
    }

    /**
     * Getter method to output last name of account holder
     *
     * @return last name of account holder
     */
    public String getLname() {
        return lname;
    }

    /**
     * Getter method to output date of birth of account holder
     *
     * @return date of birth of account holder
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Compare method to compare two profiles based on last name, first
     * name, and date of birth
     *
     * @param o the object to be compared.
     * @return result of the comparison
     */
    @Override
    public int compareTo(Profile o) {
        if (this.lname.compareTo(o.lname) == 0) {
            if (this.fname.compareTo(o.fname) == 0) {
                return this.dob.compareTo(o.dob);
            } else {
                return this.fname.compareTo(o.fname);
            }
        } else {
            return this.lname.compareTo(o.lname);
        }
    }
}
