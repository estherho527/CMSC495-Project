import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;


import javax.swing.JComboBox;

public class MainGUI extends JFrame{

	public String guiTitle;
	JPanel employeePanel;
	
	// login GUI variables
	JPanel startPanel = new JPanel(new BorderLayout());
	JLabel loginLabel = new JLabel("Choose Login");
	JPanel loginChoicePanel = new JPanel(new FlowLayout());
	JButton employeeButton = new JButton("Employee");
	JButton patientButton = new JButton("Patient");
	
	
	
	// default constructor
	public MainGUI() {
		
		guiTitle = "PIMS";
		
		// sets up main GUI
		setTitle(guiTitle);
		setLayout(new GridLayout(1,0));
		setSize(500,300);
	

		startPanel.add(loginLabel, BorderLayout.PAGE_START);
		loginChoicePanel.add(employeeButton);
		loginChoicePanel.add(patientButton);
		startPanel.add(loginChoicePanel, BorderLayout.CENTER);
		
		add(startPanel,BorderLayout.CENTER);
		
		validate();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // GUI appear in center
		setVisible(true);
		
		employeeButton.addActionListener (e -> employeeMenu());
		patientButton.addActionListener (e -> patientMenu());
	}// end constructor
	
	/*
	 * 
	 */ 
	private void employeeMenu(){
		
		remove(startPanel);
		//startPanel.setVisible(false);
		revalidate();
		repaint();
		employeePanel = new EmployeeGUI();
		this.add(employeePanel, BorderLayout.CENTER);
		
		validate();
		/*
		 * TO_DO
		 * -employeeGUI looks funny. not filling up whole screen
		 */
		
		
	}// end employeeMenu
	
	/*
	 * 
	 */
	private void patientMenu(){
		
		
	}// end patientMenu
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		MainGUI testGUI = new MainGUI();
		
	}// end main
}
