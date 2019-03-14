import java.util.ArrayList;

/**
 * @author Kamran Lotfian
 *	basic patient class with its own unique toString method
 */
public class patient extends person{
    private int SSN, zip;
    private String address, p_number, city, state;
    private boolean policy;
    private ArrayList<String> apptPaymentHistory;

    public patient(String f_name, String l_name, String m_name, String user_name, String password, String dob,
                   int SSN, int zip, String address, String city, String state, String p_number, boolean policy) {

        super(f_name, l_name, m_name, user_name, password, dob);

        this.SSN = SSN;
        this.zip = zip;
        this.address = address;
        this.p_number = p_number;
        this.city = city;
        this.state = state;
        this.policy = policy;
        apptPaymentHistory = new ArrayList<String>();
        apptPaymentHistory.add("Payment & Appointment History for: " + l_name + ", " + f_name);
    }

    @Override
    public void setL_name(String l_name) {
        super.setL_name(l_name);
    }

    @Override
    public void setM_name(String m_name) {
        super.setM_name(m_name);
    }

    @Override
    public void setF_name(String f_name) {
        super.setF_name(f_name);
    }

    @Override
    public void setDob(String dob) {
        super.setDob(dob);
    }

    @Override
    public void setUser_name(String user_name) {
        super.setUser_name(user_name);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setP_number(String p_number) {
        this.p_number = p_number;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setPolicy(boolean policy) {
        this.policy = policy;
    }

    @Override
    public String getL_name() {
        return super.getL_name();
    }

    @Override
    public String getF_name() {
        return super.getF_name();
    }

    @Override
    public String getM_name() {
        return super.getM_name();
    }

    @Override
    public String getDob() {
        return super.getDob();
    }

    @Override
    public String getUser_name() {
        return super.getUser_name();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getP_number() {
        return p_number;
    }

    public int getZip() { return zip; }

    public int getSSN() { return SSN; }

    public boolean isPolicy() { return policy; }

    public ArrayList<String> getApptPaymentHistory() {
        return apptPaymentHistory;
    }

    public String toString() {
        String s = "*****Patient Report*****\n";
        s += super.toString();
        s += "\nSSN: " + SSN;
        s += "\nZip Code: " + zip;
        s += "\nAddress: " + address;
        s += "\nCity: " + city;
        s += "\nPhone Number: " + p_number;

        return s;
    }
}
