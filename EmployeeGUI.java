import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;

public class EmployeeGUI extends JPanel{
	
	// Employee Information
	//Employee emp;
	private String user;
	private String pw;
	
	// Employee Label
	JLabel employeeGUILabel;
	
	// Login window variables
	JPanel loginPanel;
	JLabel userLabel, pwLabel;
	JTextField userField, pwField;
	JButton loginButton, cancelButton;
	
	// Employee Window (Tabbed Pane)
	// Calendar, Patient Information, Billing, Search
	JTabbedPane jtp; 
	JPanel calTab, patientTab, billingTab, searchTab;
	
	// TAB 1: Calendar
	
	
	// TAB 2: Patient Information 
	JLabel lNameLabel, fNameLabel, mNameLabel, ssidLabel, dobLabel, 
		phoneLabel, streetLabel, cityLabel, stateLabel, zipLabel;
	
	/*
	 * note:
	 * -consider how to restrict how user can enter this in.
	 * -ex: have a month, date, year field
	 */
	
	JTextField lNameField, fNameField, mNameField, ssidField, dobField,
		phoneField, streetField, cityField, zipField;
	JComboBox statesCB;
	
	// TAB 3: Billing
	
	
	// TAB 4: Search
	

	
	// Default Constructor
	public EmployeeGUI() {
		
		// set up GUI
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//setSize(1200,500);
		setSize(1000, 500);
		employeeGUILabel = new JLabel("Employee PIMS"); // may not use
		
		// initialize login panel variables
		loginPanel = new JPanel(new GridLayout(0,2,5,5));
		
		userLabel = new JLabel("Username:");
		pwLabel = new JLabel("Password");
		userField = new JTextField(20);
		userField.setEditable(true);
		pwField = new JTextField(20);
		pwField.setEditable(true);
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		
		// set up login panel
		loginPanel.add(userLabel);
		loginPanel.add(userField);
		loginPanel.add(pwLabel);
		loginPanel.add(pwField);
		loginPanel.add(loginButton);
		loginPanel.add(cancelButton);
		
		// initialize JTabbedPane
		jtp = new JTabbedPane();
		calTab = new JPanel(new BorderLayout());
		patientTab = new JPanel(new GridLayout(0,2,5,5));
		billingTab = new JPanel(new BorderLayout());
		searchTab = new JPanel(new BorderLayout());
		
		// TAB 1: Calendar
		/*
		 * TO-DO : figure out calendar
		 */
		
		// TAB 2: Patient Information
		lNameLabel = new JLabel("Last Name");
		fNameLabel = new JLabel("First Name");
		mNameLabel = new JLabel("Middle Name");
		ssidLabel = new JLabel("Social Security Number");
		dobLabel = new JLabel("Date of Birth");
		phoneLabel = new JLabel("Telephone Number:");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("ZIP Code");

		lNameField = new JTextField(20);
		lNameField.setEditable(true);
		fNameField = new JTextField(20);
		fNameField.setEditable(true);
		mNameField = new JTextField(20);
		mNameField.setEditable(true);
		ssidField = new JTextField(20);
		ssidField.setEditable(true);
		dobField  = new JTextField(20);
		dobField.setEditable(true);
		phoneField = new JTextField(20);
		phoneField.setEditable(true);
		streetField = new JTextField(20);
		streetField.setEditable(true);
		cityField = new JTextField(20);
		cityField.setEditable(true);
		zipField = new JTextField(20);
		zipField.setEditable(true);
		

		String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", 
					"Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", 
					"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", 
					"Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", 
					"Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", 
					"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", 
					"New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
					"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", 
					"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", 
					"Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", 
					"Wyoming"};
		statesCB = new JComboBox<String>(states);
		
		/*
		 * NOTE: see how the combobox shows all the states
		 */
		
		// add components to tab
		patientTab.add(lNameLabel);
		patientTab.add(lNameField);
		patientTab.add(fNameLabel);
		patientTab.add(fNameField);
		patientTab.add(mNameLabel);
		patientTab.add(mNameField);
		patientTab.add(ssidLabel);
		patientTab.add(ssidField);
		patientTab.add(dobLabel);
		patientTab.add(dobField);
		patientTab.add(phoneLabel);
		patientTab.add(phoneField);
		patientTab.add(streetLabel);
		patientTab.add(streetField);
		patientTab.add(cityLabel);
		patientTab.add(cityField);
		patientTab.add(stateLabel);
		patientTab.add(statesCB);
		patientTab.add(zipLabel);
		patientTab.add(zipField);

		
		
		// add panels to tabbed pane
		jtp.add("Calendar", calTab);
		jtp.add("Patient Information", patientTab);
		jtp.add("Billing", billingTab);
		jtp.add("Search", searchTab);
		
		
		// set up main panel
		
		//add(employeeGUILabel, BorderLayout.PAGE_START);
		add(loginPanel, BorderLayout.CENTER);
		//add(jtp, BorderLayout.CENTER);
		validate();
		
		
		
		// various action listeners for buttons
		loginButton.addActionListener (e -> checkLogin());
		
		/*searchButton.addActionListener (e -> search ((String)(searchCB.getSelectedItem()), searchTF.getText()));
		expandButton.addActionListener (e -> expandAllNodes());
		collapseButton.addActionListener (e -> collapseAllNodes());
		sortCB.addActionListener(e -> updateSortByCB());
		sortButton.addActionListener(e -> {
			try {
				sort();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		});*/
	}// end constructor

	/*
	 * login()
	 * 
	 * -checks username and password for employee
	 * -returns true if credentials are correct
	 * -false if not, and error message will display
	 */
	private boolean checkLogin(){
		
		String title, toDisplay;
		
		title = "Login";
		
		toDisplay = "Login failed";
		
		// for now, as long as user and pw are not empty, one can log in	
		if (!userField.getText().equals("") && !pwField.getText().equals("")){
			
			/*
			 * need to validate user login
			 */
			
			toDisplay = "Login successful";
			remove(loginPanel);
			revalidate();
			repaint();
			
			setSize(1000,500);
			add(jtp, BorderLayout.CENTER);
			validate();
			
			JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.DEFAULT_OPTION);
			return true;
		}
		
		JOptionPane.showMessageDialog(this, toDisplay, title, JOptionPane.ERROR_MESSAGE);
		return false;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		EmployeeGUI testGUI = new EmployeeGUI();
		JFrame mainGUI = new JFrame();
		
		mainGUI.setLayout(new GridLayout(1,0));
		mainGUI.setSize(1000,600);
		
		mainGUI.add(testGUI);
		
		mainGUI.validate();

		mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGUI.setLocationRelativeTo(null); // GUI appear in center
		mainGUI.setVisible(true);
		
	}// end main
}