package transactionmanager;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public Date getDob(){
        return dob;
    }
}
