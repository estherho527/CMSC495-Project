/**
 * @author Kamran Lotfian
 *	The person class which is extended by both the patient and staff class
 */
public class person {
    private String f_name, l_name, m_name, user_name, password, dob;

    public person(String f_name, String l_name, String m_name, String user_name, String password, String dob) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.m_name = m_name;
        this.user_name = user_name;
        this.password = password;
        this.dob = dob;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getL_name() { return l_name; }

    public String getF_name() { return f_name; }

    public String getM_name() { return m_name; }

    public String getUser_name() { return user_name; }

    public String getPassword() { return password; }

    public String getDob() { return dob; }

    public String toString() {
        String s = "First Name: " + f_name;
        s += "\nLast Name: " + l_name;
        s += "\nMiddle Name: " + m_name;
        s += "\nDate of Birth: " + dob;

        return s;
    }
}
