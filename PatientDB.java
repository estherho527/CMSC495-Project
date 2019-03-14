import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sqlitetutorial.net
 */
public class PatientDB {

    /**
     * Connect to the test.db database
     * @return the Connection object
     */

	// trim all whitespace for every variable and alias it to the parameter 
    String sql = "SELECT trim(l_name) l_name, trim(f_name) f_name, trim(m_name) m_name, trim(user_name) user_name, trim(password) password, "
    		+ "trim(dob) dob, trim(SSN) SSN, trim(zip) zip, trim(address) address, trim(city) city, trim(state) state, "
    		+ "trim(p_number) p_number, trim(policy) policy" +
            "       FROM patientinfo";

    ArrayList<patient> pat_list;
    ArrayList<patient> pat_listI;    

    private Connection connect() {
        // SQLite connection string
    	 String url = "jdbc:sqlite:src/patients.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the table
     */
    public ArrayList<patient> selectAll(){

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            pat_list = new ArrayList<patient>();
            // loop through the result set
            while (rs.next()) {
                pat_list.add(new patient(rs.getString("f_name"), rs.getString("l_name"),
                            rs.getString("m_name"), rs.getString("user_name"),
                            rs.getString("password"), rs.getString("dob"),
                            rs.getInt("SSN"),rs.getInt("zip"),rs.getString("address"),
                            rs.getString("city"),rs.getString("state"),rs.getString("p_number"),
                            rs.getBoolean("policy")));

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pat_list;
 
    }
    

    /**
     * add patient records to the patientinfo table
     */
    public void insertInto(String f_name, String l_name, String m_name, String user_name, String password, String dob,
                                         int SSN, int zip, String address, String city, String state, String p_number, boolean policy){

        
        
        String sqlINSERT = "INSERT INTO patientinfo (l_name, f_name, m_name, user_name, password, "
            + "dob, SSN, zip, address, city, state, p_number, policy)"
                
            + "VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        
        
        try (Connection connI = this.connect();
             PreparedStatement stmtI  = connI.prepareStatement(sqlINSERT))
             //ResultSet rsI    = stmtI.executeQuery(sqlINSERT))
            {
            
                stmtI.setString(1, f_name);
                stmtI.setString(2, l_name);
                stmtI.setString(3, m_name);
                stmtI.setString(4, user_name);
                stmtI.setString(5, password);
                stmtI.setString(6, dob);
                stmtI.setInt(7, SSN);
                stmtI.setInt(8, zip);
                stmtI.setString(9, address);
                stmtI.setString(10, city);
                stmtI.setString(11, state);
                stmtI.setString(12, p_number);
                stmtI.setBoolean(13, policy);
    
                stmtI.executeUpdate();
                //stmtI.close();
               
            } catch (SQLException e) {
                System.out.println(e.getMessage());
          }
    }                 
}
