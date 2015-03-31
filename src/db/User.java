package db;

public class User {
    private final String username;
    private final boolean doctorPrivileges;
    
    private String firstName;
    private String lastName;
    private String specialty;
    private String pagerNumber;
    
    /**
     * Simple constructor for only required information.
     * @param username
     * @param firstName
     * @param lastName
     * @param doctorPrivileges
     */
    public User(String username, String firstName, String lastName, boolean doctorPrivileges) {
        this(username, firstName, lastName, null, null, doctorPrivileges);
    }
    
    /**
     * Full constructor when all information is known.
     * @param username
     * @param firstName
     * @param lastName
     * @param specialty
     * @param pagerNumber
     * @param doctorPrivileges 
     */
    public User(String username, String firstName, String lastName, String specialty, String pagerNumber, boolean doctorPrivileges) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.pagerNumber = pagerNumber;
        this.doctorPrivileges = doctorPrivileges;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public boolean hasDoctorPrivileges() {
        return doctorPrivileges;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setPagerNumber(String pagerNumber) {
        this.pagerNumber = pagerNumber;
    }
    
    @Override
    public String toString() {
        String str = username + ": " + firstName + " " + lastName;
        str += doctorPrivileges ? " (doctor)" : " (nurse)";
        if(specialty != null)
            str += "   Specialty: " + specialty;
        if(pagerNumber != null)
            str += "   Pager: " + pagerNumber;
        
        return str;
    }
}
