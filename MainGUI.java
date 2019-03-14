/* Author: Esther Ho & Ari Ohsie
 * CMSC 495
 * PIMS Project
 *
 * File Name: MainGUI.java
 *
 *
 *
 * DISCLAIMER: EmployeeGUI & PatientGUI use code from the following project for the calendar
 *  https://github.com/LGoodDatePicker/LGoodDatePicker
 */
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{

    public static system pimsSystem;
    private String guiTitle;

    private JPanel employeePanel, patientPanel;

    public static Color patientColor = new Color(63, 196, 243);
    public static Color employeeColor = new Color(75, 175, 255);
    public static Color backgroundColor = new Color (174, 224, 249);
    public static Color fontColor = new Color(12, 45, 61);

    // icon
    private ImageIcon pimsTitleLogo = createImageIcon
            ("image/logo_name_slogan_BORDER.png", "PIMS title logo");
    

    // "Start Panel" - login GUI variables
    private JPanel startPanel = new JPanel(new BorderLayout());
    private JLabel iconLabel = new JLabel(pimsTitleLogo, JLabel.CENTER);
    private JLabel welcomeLabel_1 = new JLabel("Welcome to PIMS", SwingConstants.CENTER);
    private JLabel chooseEmployeeOrPatientLabel =
            new JLabel("Choose Employee or Patient", SwingConstants.CENTER);

    /* NEW JLabel To Distinguish Employee & Patient menu
     *  - to be displayed in Left or Center on top, in the same line where the backToLoginButton is
     */
    private JPanel welcomePanel = new JPanel(new GridBagLayout());

    private JButton employeeButton = new JButton("Employee");
    JButton patientButton = new JButton("Patient");


    // Back Button to go back to Login
    private ImageIcon pimsLogo = createImageIcon("image/logo only.png", "PIMS name logo");

    private JLabel employeeMenuLabel = new JLabel("Employee Menu", pimsLogo, SwingConstants.CENTER);
    JLabel patientMenuLabel= new JLabel("Patient Menu", pimsLogo, SwingConstants.CENTER);

    private JButton backToLoginPatientButton = new JButton("Back to \"Welcome\" Page");
    private JButton backToLoginEmployeeButton = new JButton("Back to \"Welcome\" Page");
    private JPanel backPatientPanel = new JPanel(new GridBagLayout());
    private JPanel backEmployeePanel = new JPanel(new GridBagLayout());

    // default constructor
    public MainGUI() {

        guiTitle = "PIMS";

        // initialize system object
        pimsSystem = new system();

        // sets up main GUI
        setTitle(guiTitle);
        setLayout(new BorderLayout());
        setSize(1000,600);
        setResizable(false);

        // set up start Panel
        welcomeLabel_1.setFont(new java.awt.Font(welcomeLabel_1.getFont().getFontName(), Font.PLAIN, 40));
        //welcomeLabel2.setFont(new java.awt.Font(welcomeLabel2.getFont().getFontName(), Font.PLAIN, 40));
        chooseEmployeeOrPatientLabel.setFont(new java.awt.Font(chooseEmployeeOrPatientLabel.getFont().getFontName(), Font.PLAIN, 30));

        welcomeLabel_1.setForeground(MainGUI.fontColor);
        chooseEmployeeOrPatientLabel.setForeground(MainGUI.fontColor);
        welcomePanel.setBackground(backgroundColor);

        GridBagConstraints welcomePanelConstraints = new GridBagConstraints();

        welcomePanelConstraints.gridx = 10;
        welcomePanelConstraints.gridy = 10;
        welcomePanelConstraints.anchor = GridBagConstraints.NORTH;
        welcomePanelConstraints.insets = new Insets(10, 0, 0, 0);
        welcomePanel.add(iconLabel, welcomePanelConstraints);

        welcomePanelConstraints.weighty = 0.4;
        welcomePanelConstraints.anchor = GridBagConstraints.CENTER;
        welcomePanelConstraints.insets = new Insets(150, 0, 0, 0);
        welcomePanel.add(welcomeLabel_1, welcomePanelConstraints);


        employeeButton.setOpaque(true);
        patientButton.setOpaque(true);
        employeeButton.setBackground(employeeColor);
        patientButton.setBackground(patientColor);
        employeeButton.setForeground(MainGUI.fontColor);
        patientButton.setForeground(MainGUI.fontColor);

        welcomePanelConstraints.gridy = 20;
        welcomePanelConstraints.weighty = 0;
        welcomePanelConstraints.insets = new Insets(0, 0, 0, 0);
        welcomePanel.add(chooseEmployeeOrPatientLabel, welcomePanelConstraints);

        welcomePanelConstraints.gridy = 30;
        welcomePanelConstraints.weighty = 1;
        welcomePanelConstraints.ipadx = 20;
        welcomePanelConstraints.ipady = 15;
        welcomePanelConstraints.anchor = GridBagConstraints.NORTH;
        welcomePanelConstraints.insets = new Insets(40, 0, 0, 150);
        welcomePanel.add(employeeButton, welcomePanelConstraints);

        welcomePanelConstraints.gridy = 30;
        welcomePanelConstraints.ipadx = 30;
        welcomePanelConstraints.insets = new Insets(40, 150, 0, 0);
        welcomePanel.add(patientButton, welcomePanelConstraints);

        startPanel.add(welcomePanel, BorderLayout.CENTER);

        // Add start panel to JFrame
        add(startPanel,BorderLayout.CENTER);

        patientMenuLabel.setFont(new java.awt.Font(patientMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        employeeMenuLabel.setFont(new java.awt.Font(employeeMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        patientMenuLabel.setForeground(fontColor);
        employeeMenuLabel.setForeground(fontColor);

        // Add labels to back patient &  back employee panel
        // BACK PANELS

        patientMenuLabel.setFont(new java.awt.Font(patientMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        patientMenuLabel.setHorizontalAlignment(JLabel.LEFT);
        patientMenuLabel.setVerticalAlignment(JLabel.CENTER);
        patientMenuLabel.setForeground(MainGUI.fontColor);
        employeeMenuLabel.setFont(new java.awt.Font(employeeMenuLabel.getFont().getFontName(), Font.PLAIN, 30));
        employeeMenuLabel.setHorizontalAlignment(JLabel.LEFT);
        employeeMenuLabel.setVerticalAlignment(JLabel.CENTER);
        employeeMenuLabel.setForeground(MainGUI.fontColor);

        // Add label & back button to back patient and employee panels

        backPatientPanel.setBackground(patientColor);
        backEmployeePanel.setBackground(employeeColor);

        backToLoginPatientButton.setForeground(MainGUI.fontColor);
        backToLoginEmployeeButton.setForeground(MainGUI.fontColor);

        GridBagConstraints backButtonConstraints = new GridBagConstraints();

        backButtonConstraints.weightx = 1;
        backButtonConstraints.ipadx = 20;
        backButtonConstraints.ipady = 10;
        backButtonConstraints.anchor = GridBagConstraints.NORTHWEST;
        backButtonConstraints.insets = new Insets(10, 10, 0, 10);

        backPatientPanel.add(patientMenuLabel, backButtonConstraints);

        backEmployeePanel.add(employeeMenuLabel, backButtonConstraints);


        backButtonConstraints.anchor = GridBagConstraints.NORTHEAST;

        backPatientPanel.add(backToLoginPatientButton, backButtonConstraints);

        backEmployeePanel.add(backToLoginEmployeeButton, backButtonConstraints);

        repaint();
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // GUI appear in center
        setVisible(true);

        // Action Listeners
        employeeButton.addActionListener (e -> employeeMenu());

        patientButton.addActionListener (e -> patientMenu());

        backToLoginPatientButton.addActionListener(e -> returnToLogin());

        backToLoginEmployeeButton.addActionListener (e -> returnToLogin());

    }// end constructor

    /*
     * employeeMenu()
     */
    private void employeeMenu(){

        remove(startPanel);
        revalidate();
        repaint();

        employeePanel = new EmployeeGUI();
        this.add(backEmployeePanel, BorderLayout.PAGE_START);
        this.add(employeePanel, BorderLayout.CENTER);

        validate();

    }// end employeeMenu


    /*
     * patientMenu()
     */
    private void patientMenu(){

        remove(startPanel);
        revalidate();
        repaint();

        patientPanel = new PatientGUI();
        this.add(backPatientPanel, BorderLayout.PAGE_START);
        this.add(patientPanel, BorderLayout.CENTER);

        validate();

    }// end patientMenu


    /*
	 * returnToLogin()
	 */
    private void returnToLogin(){

        getContentPane().removeAll();
        revalidate();
        repaint();

        this.add(startPanel, BorderLayout.CENTER);

        revalidate();
        repaint();

    }// end patientMenu

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = MainGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /*
     * MAIN to run entire project
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        MainGUI testGUI = new MainGUI();

    }// end main

}// end MainGUI