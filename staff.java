/**
 * @author Kamran Lotfian
 * basic staff class with its own unique toString method
 */
public class staff extends person {
    private int id, access_level;

    public staff(String f_name, String l_name, String m_name, String user_name, String password, int id, int access_level) {
        super(f_name, l_name, m_name, user_name, password, "1/1/1111");

        this.id = id;
    }

    public int getId() { return id; }

    public String toString() {
        String s = "*****Staff Details*****\n";
        s += super.toString();
        s += "\nID: " + id;

        return s;
    }

}
