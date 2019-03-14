/* Author: Ari Ohsie & Esther Ho
 * CMSC 495
 * PIMS Project
 *
 * File Name: PatientGUI.java
 *
 *
 *
 * DISCLAIMER: EmployeeGUI & PatientGUI use code from the following project for the calendar
 *  https://github.com/LGoodDatePicker/LGoodDatePicker
 */

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@SuppressWarnings("serial")
public class PatientGUI extends JPanel {

    patient patient;

    // Choose Login Type Panel
    private JPanel chooseLoginTypePanel;
    private GridBagConstraints chooseLoginTypePanelConstraints;
    private JLabel clt_chooseLabel;
    private JButton clt_existingPatientButton;
    private JButton clt_newPatientButton;

    // Login Panel
    private JPanel loginPanel;
    private GridBagConstraints loginConstraints;
    private JLabel logInLabel, login_usernameLabel, login_passwordLabel;
    private JTextField login_usernameTextField;
    private JPasswordField login_passwordField;
    private JButton loginButton, login_cancelButton;

    // New Patient Login panel
    private JPanel newPatientLoginPanel;
    private GridBagConstraints newPatientLoginConstraints;
    private JLabel npLogin_createNewPatientLabel, npLogin_usernameLabel, npLogin_passwordLabel;
    private JTextField npLogin_usernameTextField;
    private JPasswordField npLogin_passwordField;
    private JButton npLogin_submitButton, npLogin_cancelButton;

    // New Patient Info panel
    private JPanel newPatientInfoPanel;
    private GridBagConstraints newPatientInfoPanelConstraints;
    private JLabel npInfo_firstNameLabel, npInfo_middleNameLabel, npInfo_lastNameLabel,
            npInfo_SSNLabel, npInfo_DOBLabel, npInfo_phoneNumberLabel,
            npInfo_addressLabel, npInfo_cityLabel, npInfo_stateLabel, npInfo_zipCodeLabel;
    private JTextField npInfo_firstNameTextField, npInfo_middleNameTextField, npInfo_lastNameTextField,
            npInfo_SSNTextField, npInfo_DOBTextField, npInfo_phoneNumberTextField,
            npInfo_addressTextField, npInfo_cityTextField, npInfo_zipCodeTextField;
    private String[] npInfo_states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
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
    private JComboBox<String> npInfo_stateComboBox;
    private JButton npInfo_submitNewInfoButton, npInfo_cancelButton;

    // Tabbed Pane
    private JTabbedPane tabbedPane;

    // Tab 1: Patient Info
    private JPanel patientInfoPanel;
    private GridBagConstraints patientInfoPanelConstraints;
    private JLabel pInfo_firstNameLabel, pInfo_middleNameLabel, pInfo_lastNameLabel,
            pInfo_SSNLabel, pInfo_DOBLabel, pInfo_phoneNumberLabel,
            pInfo_addressLabel, pInfo_cityLabel, pInfo_stateLabel, pInfo_zipCodeLabel,
            pInfo_patient_userLabel, pInfo_patient_pwLabel;
    private JTextField pInfo_firstNameTextField, pInfo_middleNameTextField, pInfo_lastNameTextField,
            pInfo_SSNTextField, pInfo_DOBTextField, pInfo_phoneNumberTextField,
            pInfo_addressTextField, pInfo_cityTextField, pInfo_zipCodeTextField,
            pInfo_patient_userField, pInfo_patient_pwField;
    private JComboBox<String> pInfo_stateComboBox;
    private JButton pInfo_updateInfoButton;

    // Tab 2: Appointments
    private JPanel appointmentsPanel;
    private GridBagConstraints appointmentConstraints;
    private JLabel app_chooseDateAndTimeLabel, app_lookUpAppointmentLabel;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private JButton app_requestAppointmentButton, app_cancelAppointmentButton, app_lookUpAppointmentButton;
    private JTextField app_lookUpAppointmentTextField;

    // constructor
    public PatientGUI() {
        initialize();
    }

    // initialize()
    private void initialize() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // setBackground(MainGUI.backgroundColor);

        initializeMainPanel();

        // mainPanel is first thing to see
        add(chooseLoginTypePanel);

        initializeLoginPanel();
        initializeCreateNewPatientPanel();
        initializeCreateNewPatientInfoPanel();

        // Tabbed Panel
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(MainGUI.backgroundColor);
        tabbedPane.setForeground(MainGUI.fontColor);

        initializePatientInfoTab();
        initializeAppointmentsTab();

        // add patient and appointments panels to tabbed pane
        tabbedPane.add("Patient Information", patientInfoPanel);
        tabbedPane.add("Appointments", appointmentsPanel);

        // create ActionListeners for all the buttons

        // Choose Login Type Panel
        clt_existingPatientButton.addActionListener(e -> clt_existingPatient());
        clt_newPatientButton.addActionListener(e -> clt_newPatient());

        // Login Panel
        loginButton.addActionListener(e -> login());
        login_cancelButton.addActionListener(e -> login_cancel());

        // New Patient Login Panel
        npLogin_submitButton.addActionListener(e -> npl_submit());
        npLogin_cancelButton.addActionListener(e -> npl_cancel());

        // New Patient Info Panel

        // submits a new patient info into the system
        npInfo_submitNewInfoButton.addActionListener(e -> npi_submit());

        // brings user back to main panel
        npInfo_cancelButton.addActionListener(e -> npi_cancel());

        // Patient Info Tab
        pInfo_updateInfoButton.addActionListener(e -> pInfo_updateInfo());

        // Appointments Tab Listeners
        app_requestAppointmentButton.addActionListener(e -> app_requestAppointment());
        app_lookUpAppointmentButton.addActionListener(e -> app_lookUpAppointment());
        app_cancelAppointmentButton.addActionListener(e -> app_cancelAppointment());

    } // end initialize()

/* START initialize() related functions*/

    // main panel
    private void initializeMainPanel(){

        // Main panel
        chooseLoginTypePanel = new JPanel(new GridBagLayout());
        chooseLoginTypePanelConstraints = new GridBagConstraints();
        chooseLoginTypePanel.setBackground(MainGUI.backgroundColor);

        // create label
        clt_chooseLabel = new JLabel("Choose New or Existing Patient");

        clt_chooseLabel.setForeground(MainGUI.fontColor);

        // create buttons
        clt_existingPatientButton = new JButton("Existing Patient");
        clt_newPatientButton = new JButton("New Patient");

        clt_existingPatientButton.setForeground(MainGUI.fontColor);
        clt_newPatientButton.setForeground(MainGUI.fontColor);

        // set label font
        clt_chooseLabel.setFont(new java.awt.Font(clt_chooseLabel.getFont().getFontName(), Font.PLAIN, 40));

        // set constraints for components and add
        // to the main panel

        chooseLoginTypePanelConstraints.gridx = 10;
        chooseLoginTypePanelConstraints.gridy = 10;
        chooseLoginTypePanelConstraints.weighty = 0.2;
        chooseLoginTypePanelConstraints.anchor = GridBagConstraints.CENTER;
        chooseLoginTypePanelConstraints.insets = new Insets(40, 0, 0, 0);
        chooseLoginTypePanel.add(clt_chooseLabel, chooseLoginTypePanelConstraints);

        // add existing patient button
        chooseLoginTypePanelConstraints.gridy = 20;
        chooseLoginTypePanelConstraints.weighty = 1;
        chooseLoginTypePanelConstraints.ipady = 15;
        chooseLoginTypePanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        chooseLoginTypePanelConstraints.insets = new Insets(30, 150, 0, 0);
        chooseLoginTypePanel.add(clt_existingPatientButton, chooseLoginTypePanelConstraints);

        // add new patient button
        chooseLoginTypePanelConstraints.ipadx = 20;
        chooseLoginTypePanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        chooseLoginTypePanelConstraints.insets = new Insets(30, 0, 0, 150);
        chooseLoginTypePanel.add(clt_newPatientButton, chooseLoginTypePanelConstraints);
    }// end chooseNewOrExisting

    // Login Panel
    private void initializeLoginPanel(){

        // Login Panel
        loginPanel = new JPanel(new GridBagLayout());
        loginConstraints = new GridBagConstraints();

        loginPanel.setBackground(MainGUI.backgroundColor);

        logInLabel = new JLabel("Patient Login");
        login_usernameLabel = new JLabel("Username:");
        login_passwordLabel = new JLabel("Password:");

        logInLabel.setFont(new java.awt.Font(logInLabel.getFont().getFontName(), Font.PLAIN, 40));

        logInLabel.setForeground(MainGUI.fontColor);
        login_usernameLabel.setForeground(MainGUI.fontColor);
        login_passwordLabel.setForeground(MainGUI.fontColor);

        login_usernameTextField = new JTextField(12);
        login_passwordField = new JPasswordField(12);

        loginButton = new JButton("Login");
        login_cancelButton = new JButton("Cancel");

        loginButton.setForeground(MainGUI.fontColor);
        login_cancelButton.setForeground(MainGUI.fontColor);

        //add components to login screen

        // add login label
        loginConstraints.gridx = 10;
        loginConstraints.gridy = 10;
        loginConstraints.weighty = 0.2;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTH;
        loginConstraints.insets = new Insets(50, 5, 0, 30);
        loginPanel.add(logInLabel, loginConstraints);

        // username label
        loginConstraints.gridy = 20;
        loginConstraints.weighty = 0;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(40, 0, 0, 10);
        loginPanel.add(login_usernameLabel, loginConstraints);

        // add password label
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(10, 0, 0, 10);
        loginPanel.add(login_passwordLabel, loginConstraints);

        // add username label
        loginConstraints.gridx = 20;
        loginConstraints.gridy = 20;
        loginConstraints.gridwidth = 20;
        loginConstraints.insets = new Insets(40, 0, 0, 10);
        loginPanel.add(login_usernameTextField, loginConstraints);

        // add password field
        loginConstraints.gridy = 30;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(12, 0, 0, 10);
        loginPanel.add(login_passwordField, loginConstraints);

        // add login button
        loginConstraints.gridx = 10;
        loginConstraints.gridy = 40;
        loginConstraints.weighty = 1;
        loginConstraints.ipadx = 15;
        loginConstraints.ipady = 5;
        loginConstraints.gridwidth = 20;
        loginConstraints.anchor = GridBagConstraints.NORTHWEST;
        loginConstraints.insets = new Insets(30, 15, 0, 0);
        loginPanel.add(loginButton, loginConstraints);

        // add cancel button
        loginConstraints.gridx = 20;
        loginConstraints.gridy = 40;
        loginConstraints.ipadx = 10;
        loginConstraints.gridwidth = 10;
        loginConstraints.insets = new Insets(30, 35, 0, 0);
        loginPanel.add(login_cancelButton, loginConstraints);

    }// end initializeLoginPanel

    private void initializeCreateNewPatientPanel(){

        // Create new patient panel

        newPatientLoginPanel = new JPanel(new GridBagLayout());
        newPatientLoginConstraints = new GridBagConstraints();

        newPatientLoginPanel.setBackground(MainGUI.backgroundColor);

        npLogin_createNewPatientLabel = new JLabel("New Patient");
        npLogin_usernameLabel = new JLabel("Username:");
        npLogin_passwordLabel = new JLabel("Password:");

        npLogin_createNewPatientLabel.setFont
                (new java.awt.Font(npLogin_createNewPatientLabel.getFont().getFontName(), Font.PLAIN, 40));

        npLogin_createNewPatientLabel.setForeground(MainGUI.fontColor);
        npLogin_usernameLabel.setForeground(MainGUI.fontColor);
        npLogin_passwordLabel.setForeground(MainGUI.fontColor);

        npLogin_usernameTextField = new JTextField(12);
        npLogin_passwordField = new JPasswordField(12);

        npLogin_submitButton = new JButton("Submit");
        npLogin_cancelButton = new JButton("Cancel");

        npLogin_submitButton.setForeground(MainGUI.fontColor);
        npLogin_cancelButton.setForeground(MainGUI.fontColor);

        // add components to create new patient panel
        newPatientLoginConstraints.gridx = 10;
        newPatientLoginConstraints.gridy = 0;
        newPatientLoginConstraints.weighty = 0.2;
        newPatientLoginConstraints.gridwidth = 20;
        newPatientLoginConstraints.anchor = GridBagConstraints.NORTH;
        newPatientLoginConstraints.insets = new Insets(50, 10, 0, 30);
        newPatientLoginPanel.add(npLogin_createNewPatientLabel, newPatientLoginConstraints);

        // add username label
        newPatientLoginConstraints.gridy = 10;
        newPatientLoginConstraints.weighty = 0;
        newPatientLoginConstraints.gridwidth = 10;
        newPatientLoginConstraints.anchor = GridBagConstraints.CENTER;
        newPatientLoginConstraints.insets = new Insets(40, 0, 0, 10);
        newPatientLoginPanel.add(npLogin_usernameLabel, newPatientLoginConstraints);

        // add password label
        newPatientLoginConstraints.gridy = 20;
        newPatientLoginConstraints.gridwidth = 10;
        newPatientLoginConstraints.insets = new Insets(10, 0, 0, 10);
        newPatientLoginPanel.add(npLogin_passwordLabel, newPatientLoginConstraints);

        // add username field
        newPatientLoginConstraints.gridx = 20;
        newPatientLoginConstraints.gridy = 10;
        newPatientLoginConstraints.gridwidth = 20;
        newPatientLoginConstraints.insets = new Insets(40, 0, 0, 10);
        newPatientLoginPanel.add(npLogin_usernameTextField, newPatientLoginConstraints);

        // add password field
        newPatientLoginConstraints.gridy = 20;
        newPatientLoginConstraints.gridwidth = 10;
        newPatientLoginConstraints.insets = new Insets(12, 0, 0, 10);
        newPatientLoginPanel.add(npLogin_passwordField, newPatientLoginConstraints);

        // add submit button
        newPatientLoginConstraints.gridx = 10;
        newPatientLoginConstraints.gridy = 30;
        newPatientLoginConstraints.weighty = 1;
        newPatientLoginConstraints.ipadx = 15;
        newPatientLoginConstraints.ipady = 5;
        newPatientLoginConstraints.gridwidth = 20;
        newPatientLoginConstraints.anchor = GridBagConstraints.NORTHWEST;
        newPatientLoginConstraints.insets = new Insets(30, 10, 0, 0);
        newPatientLoginPanel.add(npLogin_submitButton, newPatientLoginConstraints);

        // add cancel button
        newPatientLoginConstraints.gridx = 20;
        newPatientLoginConstraints.ipadx = 10;
        newPatientLoginConstraints.gridwidth = 10;
        newPatientLoginConstraints.insets = new Insets(30, 45, 0, 0);
        newPatientLoginPanel.add(npLogin_cancelButton, newPatientLoginConstraints);

    } // end initializeCreateNewPatientPanel

    private void initializeCreateNewPatientInfoPanel(){

        // create the patient info panel
        newPatientInfoPanel = new JPanel(new GridBagLayout());
        newPatientInfoPanel.setBackground(MainGUI.backgroundColor);
        
        newPatientInfoPanelConstraints = new GridBagConstraints();
        npInfo_firstNameLabel = new JLabel("First Name:");
        npInfo_middleNameLabel = new JLabel("Middle Name:");
        npInfo_lastNameLabel = new JLabel("Last Name:");
        npInfo_SSNLabel = new JLabel("Social Security #:");
        npInfo_DOBLabel = new JLabel("Date of Birth:");
        npInfo_phoneNumberLabel = new JLabel("Phone Number:");
        npInfo_addressLabel = new JLabel("Address:");
        npInfo_cityLabel = new JLabel("City:");
        npInfo_stateLabel = new JLabel("State:");
        npInfo_zipCodeLabel = new JLabel("Zip Code:");
        npInfo_firstNameTextField = new JTextField(12);
        npInfo_middleNameTextField = new JTextField(12);
        npInfo_lastNameTextField = new JTextField(12);
        npInfo_SSNTextField = new JTextField(12);
        npInfo_DOBTextField = new JTextField(12);
        npInfo_phoneNumberTextField = new JTextField(12);
        npInfo_addressTextField = new JTextField(12);
        npInfo_cityTextField = new JTextField(12);
        npInfo_zipCodeTextField = new JTextField(12);
        npInfo_stateComboBox = new JComboBox<>(npInfo_states);
        npInfo_submitNewInfoButton = new JButton("Submit Information");
        npInfo_cancelButton = new JButton("Cancel");

        npInfo_firstNameLabel.setForeground(MainGUI.fontColor);
        npInfo_middleNameLabel.setForeground(MainGUI.fontColor);
        npInfo_lastNameLabel.setForeground(MainGUI.fontColor);
        npInfo_SSNLabel.setForeground(MainGUI.fontColor);
        npInfo_DOBLabel.setForeground(MainGUI.fontColor);
        npInfo_phoneNumberLabel.setForeground(MainGUI.fontColor);
        npInfo_addressLabel.setForeground(MainGUI.fontColor);
        npInfo_cityLabel.setForeground(MainGUI.fontColor);
        npInfo_stateLabel.setForeground(MainGUI.fontColor);
        npInfo_zipCodeLabel.setForeground(MainGUI.fontColor);

        npInfo_submitNewInfoButton.setForeground(MainGUI.fontColor);
        npInfo_cancelButton.setForeground(MainGUI.fontColor);

        // set the constraints for each component and add
        // them to the patient info panel

        // add last name label
        newPatientInfoPanelConstraints.gridx = 10;
        newPatientInfoPanelConstraints.gridy = 10;
        newPatientInfoPanelConstraints.weightx = 1;
        newPatientInfoPanelConstraints.weighty = 0.4;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);
        newPatientInfoPanel.add(npInfo_lastNameLabel, newPatientInfoPanelConstraints);

        // add first name label
        newPatientInfoPanelConstraints.gridx = 20;
        newPatientInfoPanel.add(npInfo_firstNameLabel, newPatientInfoPanelConstraints);

        // add middle name label
        newPatientInfoPanelConstraints.gridx = 30;
        newPatientInfoPanel.add(npInfo_middleNameLabel, newPatientInfoPanelConstraints);

        // add SSN label
        newPatientInfoPanelConstraints.gridx = 10;
        newPatientInfoPanelConstraints.gridy = 20;
        newPatientInfoPanel.add(npInfo_SSNLabel, newPatientInfoPanelConstraints);

        // add DOB label
        newPatientInfoPanelConstraints.gridy = 30;
        newPatientInfoPanel.add(npInfo_DOBLabel, newPatientInfoPanelConstraints);

        // add phone number label
        newPatientInfoPanelConstraints.gridy = 40;
        newPatientInfoPanelConstraints.weighty = 1;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        newPatientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);
        newPatientInfoPanel.add(npInfo_phoneNumberLabel, newPatientInfoPanelConstraints);

        // add address label
        newPatientInfoPanelConstraints.gridy = 50;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);
        newPatientInfoPanel.add(npInfo_addressLabel, newPatientInfoPanelConstraints);

        // add city label
        newPatientInfoPanelConstraints.gridy = 60;
        newPatientInfoPanelConstraints.weighty = 0.4;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);
        newPatientInfoPanel.add(npInfo_cityLabel, newPatientInfoPanelConstraints);

        // add state label
        newPatientInfoPanelConstraints.gridy = 70;
        newPatientInfoPanel.add(npInfo_stateLabel, newPatientInfoPanelConstraints);

        // add zip code label
        newPatientInfoPanelConstraints.gridy = 80;
        newPatientInfoPanel.add(npInfo_zipCodeLabel, newPatientInfoPanelConstraints);

        // add last name textfield
        newPatientInfoPanelConstraints.gridy = 10;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        newPatientInfoPanel.add(npInfo_lastNameTextField, newPatientInfoPanelConstraints);

        // add first name textfield
        newPatientInfoPanelConstraints.gridx = 20;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);
        newPatientInfoPanel.add(npInfo_firstNameTextField, newPatientInfoPanelConstraints);

        // add middle name textfield
        newPatientInfoPanelConstraints.gridx = 30;
        newPatientInfoPanel.add(npInfo_middleNameTextField, newPatientInfoPanelConstraints);

        // add SSN textfield
        newPatientInfoPanelConstraints.gridx = 10;
        newPatientInfoPanelConstraints.gridy = 20;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        newPatientInfoPanel.add(npInfo_SSNTextField, newPatientInfoPanelConstraints);

        // add DOB textfield
        newPatientInfoPanelConstraints.gridy = 30;
        newPatientInfoPanel.add(npInfo_DOBTextField, newPatientInfoPanelConstraints);

        // add phone number textfield
        newPatientInfoPanelConstraints.gridy = 40;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        newPatientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);
        newPatientInfoPanel.add(npInfo_phoneNumberTextField, newPatientInfoPanelConstraints);

        // add address textfield
        newPatientInfoPanelConstraints.gridy = 50;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);
        newPatientInfoPanel.add(npInfo_addressTextField, newPatientInfoPanelConstraints);

        // add city textfield
        newPatientInfoPanelConstraints.gridy = 60;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        newPatientInfoPanel.add(npInfo_cityTextField, newPatientInfoPanelConstraints);

        // add state combobox
        newPatientInfoPanelConstraints.gridy = 70;
        newPatientInfoPanel.add(npInfo_stateComboBox, newPatientInfoPanelConstraints);

        // add zip code textfield
        newPatientInfoPanelConstraints.gridy = 80;
        newPatientInfoPanel.add(npInfo_zipCodeTextField, newPatientInfoPanelConstraints);

        // add submitNewInfo button
        newPatientInfoPanelConstraints.gridx = 20;
        newPatientInfoPanelConstraints.ipady = 10;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 15, 0);
        newPatientInfoPanel.add(npInfo_submitNewInfoButton, newPatientInfoPanelConstraints);

        // add cancel button
        newPatientInfoPanelConstraints.gridx = 20;
        newPatientInfoPanelConstraints.ipadx = 50;
        newPatientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        newPatientInfoPanelConstraints.insets = new Insets(0, 0, 15, 50);
        newPatientInfoPanel.add(npInfo_cancelButton, newPatientInfoPanelConstraints);

    } // end initializeCreateNewPatientInfoPanel

    private void initializePatientInfoTab(){

        // create the patient info panel
        patientInfoPanel = new JPanel(new GridBagLayout());
        patientInfoPanel.setBackground(MainGUI.backgroundColor);
        patientInfoPanelConstraints = new GridBagConstraints();

        pInfo_firstNameLabel = new JLabel("First Name:");
        pInfo_middleNameLabel = new JLabel("Middle Name:");
        pInfo_lastNameLabel = new JLabel("Last Name:");
        pInfo_SSNLabel = new JLabel("Social Security #:");
        pInfo_DOBLabel = new JLabel("Date of Birth:");
        pInfo_phoneNumberLabel = new JLabel("Phone Number:");
        pInfo_addressLabel = new JLabel("Address:");
        pInfo_cityLabel = new JLabel("City:");
        pInfo_stateLabel = new JLabel("State:");
        pInfo_zipCodeLabel = new JLabel("Zip Code:");
        pInfo_patient_userLabel = new JLabel("Username:");
        pInfo_patient_pwLabel = new JLabel("Password:");

        pInfo_firstNameTextField = new JTextField(12);
        pInfo_middleNameTextField = new JTextField(12);
        pInfo_lastNameTextField = new JTextField(12);
        pInfo_SSNTextField = new JTextField(12);
        pInfo_DOBTextField = new JTextField(12);
        pInfo_phoneNumberTextField = new JTextField(12);
        pInfo_addressTextField = new JTextField(12);
        pInfo_cityTextField = new JTextField(12);
        pInfo_zipCodeTextField = new JTextField(12);
        pInfo_patient_userField = new JTextField(12);
        pInfo_patient_pwField = new JTextField(12);

        pInfo_stateComboBox = new JComboBox<>(npInfo_states);

        pInfo_updateInfoButton = new JButton("Update Information");


        pInfo_firstNameLabel.setForeground(MainGUI.fontColor);
        pInfo_middleNameLabel.setForeground(MainGUI.fontColor);
        pInfo_lastNameLabel.setForeground(MainGUI.fontColor);
        pInfo_SSNLabel.setForeground(MainGUI.fontColor);
        pInfo_DOBLabel.setForeground(MainGUI.fontColor);
        pInfo_phoneNumberLabel.setForeground(MainGUI.fontColor);
        pInfo_addressLabel.setForeground(MainGUI.fontColor);
        pInfo_cityLabel.setForeground(MainGUI.fontColor);
        pInfo_stateLabel.setForeground(MainGUI.fontColor);
        pInfo_zipCodeLabel.setForeground(MainGUI.fontColor);
        pInfo_patient_userLabel.setForeground(MainGUI.fontColor);
        pInfo_patient_pwLabel.setForeground(MainGUI.fontColor);

        pInfo_updateInfoButton.setForeground(MainGUI.fontColor);

        // set the constraints for each component and add
        // them to the patient info panel

        // add last name label
        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.weightx = 1;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);
        patientInfoPanel.add(pInfo_lastNameLabel, patientInfoPanelConstraints);

        // add first name label
        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanel.add(pInfo_firstNameLabel, patientInfoPanelConstraints);

        // add middle name label
        patientInfoPanelConstraints.gridx = 30;
        patientInfoPanel.add(pInfo_middleNameLabel, patientInfoPanelConstraints);

        // add SSN label
        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;
        patientInfoPanel.add(pInfo_SSNLabel, patientInfoPanelConstraints);

        // add username label
        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanel.add(pInfo_patient_userLabel, patientInfoPanelConstraints);

        // add password label
        patientInfoPanelConstraints.gridx = 30;
        patientInfoPanel.add(pInfo_patient_pwLabel, patientInfoPanelConstraints);

        // add DOB label
        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 30;
        patientInfoPanel.add(pInfo_DOBLabel, patientInfoPanelConstraints);

        // add phone number label
        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.weighty = 1;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        patientInfoPanelConstraints.insets = new Insets(10, 20, 0, 0);
        patientInfoPanel.add(pInfo_phoneNumberLabel, patientInfoPanelConstraints);

        // add address label
        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHWEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 10, 0);
        patientInfoPanel.add(pInfo_addressLabel, patientInfoPanelConstraints);

        // add city label
        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.weighty = 0.4;
        patientInfoPanelConstraints.anchor = GridBagConstraints.WEST;
        patientInfoPanelConstraints.insets = new Insets(0, 20, 0, 0);
        patientInfoPanel.add(pInfo_cityLabel, patientInfoPanelConstraints);


        // add state label
        patientInfoPanelConstraints.gridy = 70;
        patientInfoPanel.add(pInfo_stateLabel, patientInfoPanelConstraints);

        // add zip code label
        patientInfoPanelConstraints.gridy = 80;
        patientInfoPanel.add(pInfo_zipCodeLabel, patientInfoPanelConstraints);

        // add last name textfield
        patientInfoPanelConstraints.gridy = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        patientInfoPanel.add(pInfo_lastNameTextField, patientInfoPanelConstraints);

        // add first name textfield
        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);
        patientInfoPanel.add(pInfo_firstNameTextField, patientInfoPanelConstraints);

        // add middle name textfield
        patientInfoPanelConstraints.gridx = 30;
        patientInfoPanel.add(pInfo_middleNameTextField, patientInfoPanelConstraints);

        // add SSN textfield
        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        patientInfoPanel.add(pInfo_SSNTextField, patientInfoPanelConstraints);

        // add username textfield
        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 60);
        patientInfoPanel.add(pInfo_patient_userField, patientInfoPanelConstraints);

        // add password textfield
        patientInfoPanelConstraints.gridx = 30;
        patientInfoPanel.add(pInfo_patient_pwField, patientInfoPanelConstraints);

        // add DOB textfield
        patientInfoPanelConstraints.gridx = 10;
        patientInfoPanelConstraints.gridy = 30;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        patientInfoPanel.add(pInfo_DOBTextField, patientInfoPanelConstraints);

        // add phone number textfield
        patientInfoPanelConstraints.gridy = 40;
        patientInfoPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        patientInfoPanelConstraints.insets = new Insets(10, 0, 0, 40);
        patientInfoPanel.add(pInfo_phoneNumberTextField, patientInfoPanelConstraints);

        // add address textfield
        patientInfoPanelConstraints.gridy = 50;
        patientInfoPanelConstraints.anchor = GridBagConstraints.SOUTHEAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 10, 40);
        patientInfoPanel.add(pInfo_addressTextField, patientInfoPanelConstraints);

        // add city text field
        patientInfoPanelConstraints.gridy = 60;
        patientInfoPanelConstraints.anchor = GridBagConstraints.EAST;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 0, 40);
        patientInfoPanel.add(pInfo_cityTextField, patientInfoPanelConstraints);

        // add staet combo box
        patientInfoPanelConstraints.gridy = 70;
        patientInfoPanel.add(pInfo_stateComboBox, patientInfoPanelConstraints);

        // add zip code textfield
        patientInfoPanelConstraints.gridy = 80;
        patientInfoPanel.add(pInfo_zipCodeTextField, patientInfoPanelConstraints);

        // add updateinfo button
        patientInfoPanelConstraints.gridy = 80;
        patientInfoPanelConstraints.gridx = 20;
        patientInfoPanelConstraints.ipady = 10;
        patientInfoPanelConstraints.anchor = GridBagConstraints.CENTER;
        patientInfoPanelConstraints.insets = new Insets(0, 0, 13, 0);
        patientInfoPanel.add(pInfo_updateInfoButton, patientInfoPanelConstraints);


    } // end initializePatientInfoTab

    private void initializeAppointmentsTab(){

        // create appointments panel
        appointmentsPanel = new JPanel(new GridBagLayout());
        appointmentsPanel.setBackground(MainGUI.backgroundColor);
        appointmentConstraints = new GridBagConstraints();

        app_chooseDateAndTimeLabel = new JLabel("Select Date and Time For Appointment");
        app_lookUpAppointmentLabel = new JLabel("Look Up Existing Appointment");

        // set the label font
        app_chooseDateAndTimeLabel.setFont(new java.awt.Font(app_chooseDateAndTimeLabel.getFont().getFontName(), Font.PLAIN, 40));
        app_lookUpAppointmentLabel.setFont(new java.awt.Font(app_lookUpAppointmentLabel.getFont().getFontName(), Font.PLAIN, 25));

        datePicker = createDatePicker();
        timePicker = createTimePicker();

        app_requestAppointmentButton = new JButton("Request Appointment");
        app_cancelAppointmentButton = new JButton("Cancel Appointment");
        app_lookUpAppointmentButton = new JButton("Look Up Appointment");

        app_chooseDateAndTimeLabel.setForeground(MainGUI.fontColor);
        app_lookUpAppointmentLabel.setForeground(MainGUI.fontColor);

        app_requestAppointmentButton.setForeground(MainGUI.fontColor);
        app_cancelAppointmentButton.setForeground(MainGUI.fontColor);
        app_lookUpAppointmentButton.setForeground(MainGUI.fontColor);

        app_lookUpAppointmentTextField = new JTextField(15);
        app_lookUpAppointmentTextField.setEditable(false);
        app_lookUpAppointmentTextField.setBackground(Color.white);

        // set the constraints for each component and add
        // them to the appointments panel

        // add choose date and time label
        appointmentConstraints.gridx = 10;
        appointmentConstraints.gridy = 10;
        appointmentConstraints.weighty = 0.2;
        appointmentConstraints.anchor = GridBagConstraints.NORTH;
        appointmentConstraints.insets = new Insets(20, 0, 0, 0);
        appointmentsPanel.add(app_chooseDateAndTimeLabel, appointmentConstraints);

        // add date picker
        appointmentConstraints.gridy = 20;
        appointmentConstraints.anchor = GridBagConstraints.CENTER;
        appointmentConstraints.insets = new Insets(20, 0, 0, 110);
        appointmentsPanel.add(datePicker, appointmentConstraints);

        // add time picker
        appointmentConstraints.insets = new Insets(20, 160, 0, 0);
        appointmentsPanel.add(timePicker, appointmentConstraints);

        // add request appointment button
        appointmentConstraints.gridy = 30;
        appointmentConstraints.weighty = 1;
        appointmentConstraints.ipady = 10;
        appointmentConstraints.anchor = GridBagConstraints.NORTH;
        appointmentConstraints.insets = new Insets(30, 0, 0, 170);
        appointmentsPanel.add(app_requestAppointmentButton, appointmentConstraints);

        // add cancel appointment button
        appointmentConstraints.ipady = 10;
        appointmentConstraints.insets = new Insets(30, 170, 0, 0);
        appointmentsPanel.add(app_cancelAppointmentButton, appointmentConstraints);

        // add lookup appointment label
        appointmentConstraints.ipady = 0;
        appointmentConstraints.gridy = 40;
        appointmentConstraints.weighty = 0.2;
        appointmentConstraints.insets = new Insets(0, 0, 0, 0);
        appointmentsPanel.add(app_lookUpAppointmentLabel, appointmentConstraints);

        // add lookup appointment button
        appointmentConstraints.ipady = 10;
        appointmentConstraints.weighty = 1;
        appointmentConstraints.gridy = 50;
        appointmentConstraints.insets = new Insets(0, 0, 0, 195);
        appointmentsPanel.add(app_lookUpAppointmentButton, appointmentConstraints);

        // add lookup appointment textfield
        appointmentConstraints.ipady = 5;
        appointmentConstraints.insets = new Insets(5, 180, 0, 0);
        appointmentsPanel.add(app_lookUpAppointmentTextField, appointmentConstraints);

    } // end initializeAppointmentsTab

/* END initialize() related functions*/

/* START Action Listener related functions*/

    // Choose Login Type Panel

    private void clt_existingPatient(){

        remove(chooseLoginTypePanel);
        add(loginPanel);

        repaint();
        revalidate();

    } // end clt_existingPatient

    private void clt_newPatient(){

        remove(chooseLoginTypePanel);
        add(newPatientLoginPanel);

        repaint();
        revalidate();

    } // end clt_newPatient

    // Login Panel

    private void login(){

        if (String.valueOf(login_usernameTextField.getText()).equals(""))
            JOptionPane.showMessageDialog
                    (null, "Must Enter A Username");

        else if (String.valueOf(String.valueOf(login_passwordField.getPassword())).equals(""))
            JOptionPane.showMessageDialog
                    (null, "Must Enter A Password");

        else if (MainGUI.pimsSystem.patient_exists
                (login_usernameTextField.getText(), String.valueOf(login_passwordField.getPassword()))) {

            remove(loginPanel);
            add(tabbedPane);

            JOptionPane.showMessageDialog
                    (null, "Login Successful");

            repaint();
            revalidate();

            // set info into patient info panel
            patient = MainGUI.pimsSystem.setPatientDetails
                    (login_usernameTextField.getText(), String.valueOf(login_passwordField.getPassword()));

            if (patient != null) {

                pInfo_firstNameTextField.setText(patient.getF_name());
                pInfo_lastNameTextField.setText(patient.getL_name());
                pInfo_middleNameTextField.setText(patient.getM_name());
                pInfo_SSNTextField.setText(String.valueOf(patient.getSSN()));
                pInfo_DOBTextField.setText(patient.getDob());
                pInfo_phoneNumberTextField.setText(patient.getP_number());
                pInfo_addressTextField.setText(patient.getAddress());
                pInfo_cityTextField.setText(patient.getCity());
                pInfo_stateComboBox.setSelectedItem(patient.getState());
                pInfo_zipCodeTextField.setText(String.valueOf(patient.getZip()));
                pInfo_patient_userField.setText(patient.getUser_name());
                pInfo_patient_pwField.setText(patient.getPassword());

            } else
                JOptionPane.showMessageDialog(null, "Error");

            // reset username and password fields
            login_usernameTextField.setText("");
            login_passwordField.setText("");


        } else
            JOptionPane.showMessageDialog
                    (null, "Invalid Password or Username");

    } // end login

    private void login_cancel(){

        remove(loginPanel);
        add(chooseLoginTypePanel);

        repaint();
        revalidate();

        // reset username and password fields
        login_usernameTextField.setText("");
        login_passwordField.setText("");

    } // end login_cancel

    // New Patient Login Panel

    private void npl_submit(){

        if (String.valueOf(npLogin_usernameTextField.getText()).equals(""))

            JOptionPane.showMessageDialog
                    (null, "Must Enter A Username");

        else if (String.valueOf(String.valueOf(npLogin_passwordField.getPassword())).equals(""))

            JOptionPane.showMessageDialog
                    (null, "Must Enter A Password");

        else if (!MainGUI.pimsSystem.patient_exists
                (npLogin_usernameTextField.getText(), String.valueOf(npLogin_passwordField.getPassword()))) {
            remove(newPatientLoginPanel);
            add(newPatientInfoPanel);
            JOptionPane.showMessageDialog
                    (null, "Submission Successful");
            repaint();
            revalidate();
        } else
            JOptionPane.showMessageDialog
                    (null, "This Username and Password are Already Taken");

    } // end npl_submit

    private void npl_cancel(){
        remove(newPatientLoginPanel);
        add(chooseLoginTypePanel);
        repaint();
        revalidate();
        // reset username and password fields
        npLogin_usernameTextField.setText("");
        npLogin_passwordField.setText("");
    } // end npl_cancel

    // New Patient Info Panel

    private void npi_submit(){

        // values to test if there are no input errors
        boolean emptyFields = true, illegalFields = true;

        String errorMessage = "Must Enter";

        if (String.valueOf(npInfo_firstNameTextField.getText()).equals("")) {

            errorMessage += " First Name,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_lastNameTextField.getText()).equals("")) {

            errorMessage += " Last Name,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_SSNTextField.getText()).equals("")) {

            errorMessage += " Social Security #,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_DOBTextField.getText()).equals("")) {

            errorMessage += " Date of Birth,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_phoneNumberTextField.getText()).equals("")) {

            errorMessage += " Phone Number,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_addressTextField).equals("")) {

            errorMessage += " Street,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_cityTextField).equals("")) {

            errorMessage += " City,";
            emptyFields = false;
        }

        if (String.valueOf(npInfo_zipCodeTextField).equals("")) {

            errorMessage += " Zip Code,";
            emptyFields = false;
        }

        // if there's no middle name, the text field
        // is set to "N/A"

        String middleName;

        if (String.valueOf(npInfo_middleNameTextField.getText()).equals(""))
            middleName = "N/A";

        else middleName = npInfo_middleNameTextField.getText();


        // throws error if last name has characters other than letters
        if (npInfo_lastNameTextField.getText().length() > 0) {

            for (int i = 0; i < npInfo_lastNameTextField.getText().length(); i++) {

                if (!Character.isLetter(npInfo_lastNameTextField.getText().charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "Last Name Must Have Only Letters");

                    illegalFields = false;
                    break;

                }
            }
        }


        // throws error if first name has characters other than letters
        if (npInfo_firstNameTextField.getText().length() > 0) {

            for (int i = 0; i < npInfo_firstNameTextField.getText().length(); i++) {

                if (!Character.isLetter(npInfo_firstNameTextField.getText().charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "First Name Must Have Only Letters");

                    illegalFields = false;
                    break;

                }
            }
        }


        // throws error if middle name has characters other than letters
        if (npInfo_middleNameTextField.getText().length() > 0 &&

                !String.valueOf(npInfo_middleNameTextField.getText()).equals("N/A")) {

            for (int i = 0; i < npInfo_middleNameTextField.getText().length(); i++) {

                if (!Character.isLetter(npInfo_middleNameTextField.getText().charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "Middle Name Must Have Only Letters");

                    illegalFields = false;
                    break;

                }
            }
        }


        // throws error if SSN has characters other than numbers, or has less/more than 4 digits
        if (npInfo_SSNTextField.getText().length() > 0 && npInfo_SSNTextField.getText().length() != 4) {

            JOptionPane.showMessageDialog
                    (null, "Social Security # Must Have 4 Characters");

            illegalFields = false;

        } else if (npInfo_SSNTextField.getText().length() == 4) {

            for (int i = 0; i < 4; i++) {

                if (!Character.isDigit(npInfo_SSNTextField.getText().charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "Social Security # Must Have Only Numbers");

                    illegalFields = false;
                    break;

                }
            }
        }


        // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"
        if (npInfo_DOBTextField.getText().length() > 0 && npInfo_DOBTextField.getText().length() != 10) {

            JOptionPane.showMessageDialog
                    (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

            illegalFields = false;

        } else if (npInfo_DOBTextField.getText().length() == 10) {

            if (!dobParser(npInfo_DOBTextField.getText())) {

                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");

                illegalFields = false;

            }
        }
        // throws error if phone number isn't formatted correctly - "###-###-####"
        if (npInfo_phoneNumberTextField.getText().length() > 0 && npInfo_phoneNumberTextField.getText().length() != 12) {
            JOptionPane.showMessageDialog
                    (null, "Phone Number Must be formatted \"###-###-####\"");
            illegalFields = false;
        } else if (npInfo_phoneNumberTextField.getText().length() == 12) {
            if (!phoneNumberParser(npInfo_phoneNumberTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            }
        }
        // throws error if address has characters other than letters and numbers
        if (npInfo_addressTextField.getText().length() > 0) {
            for (int i = 0; i < npInfo_addressTextField.getText().length(); i++) {
                if (!Character.isLetter(npInfo_addressTextField.getText().charAt(i)) &&
                        !Character.isDigit(npInfo_addressTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Address Must Have Only Numbers and Letters");
                    illegalFields = false;
                }
            }
        }

        // throws error if city has characters other than letters
        if (npInfo_cityTextField.getText().length() > 0) {
            for (int i = 1; i < npInfo_cityTextField.getText().length(); i++) {
                if (!Character.isLetter(npInfo_cityTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "City Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }
        // throws error if zip code has characters other than numbers, or has less/more than 4 digits
        if (npInfo_zipCodeTextField.getText().length() > 0 && npInfo_zipCodeTextField.getText().length() != 5) {
            JOptionPane.showMessageDialog
                    (null, "Zip Code Must Have 5 Characters");
            illegalFields = false;
        } else if (npInfo_zipCodeTextField.getText().length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(npInfo_zipCodeTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Zip Code Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }
        // checks if there are no input errors
        if (emptyFields && illegalFields) {
            if (!MainGUI.pimsSystem.add_patient(npInfo_firstNameTextField.getText(),
                    npInfo_lastNameTextField.getText(), middleName, npLogin_usernameTextField.getText(),
                    String.valueOf(npLogin_passwordField.getPassword()), npInfo_DOBTextField.getText(),
                    Integer.parseInt(npInfo_SSNTextField.getText()), Integer.parseInt(npInfo_zipCodeTextField.getText()),
                    npInfo_addressTextField.getText(), npInfo_cityTextField.getText(),
                    String.valueOf(npInfo_stateComboBox.getSelectedItem()), npInfo_phoneNumberTextField.getText(), true))
                JOptionPane.showMessageDialog
                        (null, "This Patient Is Already In System");
            else {
                // set the patient info panel in the tabbed pane to
                // to info from the create new info patient panel
                pInfo_firstNameTextField.setText(npInfo_firstNameTextField.getText());
                pInfo_middleNameTextField.setText(middleName);
                pInfo_lastNameTextField.setText(npInfo_lastNameTextField.getText());
                pInfo_SSNTextField.setText(npInfo_SSNTextField.getText());
                pInfo_DOBTextField.setText(npInfo_DOBTextField.getText());
                pInfo_phoneNumberTextField.setText(npInfo_phoneNumberTextField.getText());
                pInfo_addressTextField.setText(npInfo_addressTextField.getText());
                pInfo_cityTextField.setText(npInfo_cityTextField.getText());
                pInfo_zipCodeTextField.setText(npInfo_zipCodeTextField.getText());
                pInfo_stateComboBox.setSelectedItem(npInfo_stateComboBox.getSelectedItem());
                pInfo_patient_userField.setText(npLogin_usernameTextField.getText());
                pInfo_patient_pwField.setText(String.valueOf(npLogin_passwordField.getPassword()));
                remove(newPatientInfoPanel);
                add(tabbedPane);
                JOptionPane.showMessageDialog
                        (null, "Submission Successful");
                repaint();
                revalidate();
            }

        } else if (!String.valueOf(errorMessage).equals("Must Enter")) {
            JOptionPane.showMessageDialog(null, errorMessage);
        }

    } // end npi_submit

    private void npi_cancel(){


        remove(newPatientInfoPanel);
        add(chooseLoginTypePanel);

        repaint();
        revalidate();

        // reset username and password fields
        npLogin_usernameTextField.setText("");
        npLogin_passwordField.setText("");

    } // end npi_cancel

    // Patient Info Tab Listeners
    private void pInfo_updateInfo(){

        // values to test if there are no input errors
        boolean emptyFields = true, illegalFields = true;

        String errorMessage = "Must Enter";

        if (String.valueOf(pInfo_firstNameTextField.getText()).equals("")) {

            errorMessage += " First Name,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_lastNameTextField.getText()).equals("")) {

            errorMessage += " Last Name,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_SSNTextField.getText()).equals("")) {

            errorMessage += " Social Security #,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_DOBTextField.getText()).equals("")) {

            errorMessage += " Date of Birth,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_phoneNumberTextField.getText()).equals("")) {

            errorMessage += " Phone Number,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_addressTextField).equals("")) {

            errorMessage += " Street,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_cityTextField).equals("")) {

            errorMessage += " City,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_zipCodeTextField).equals("")) {

            errorMessage += " Zip Code,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_patient_userField).equals("")) {

            errorMessage += " Username,";
            emptyFields = false;
        }

        if (String.valueOf(pInfo_patient_pwField).equals("")) {

            errorMessage += " password,";
            emptyFields = false;
        }

        // if there's no middle name, the text field
        // is set to "N/A"
        String middleName;

        if (String.valueOf(pInfo_middleNameTextField.getText()).equals(""))
            middleName = "N/A";

        else middleName = pInfo_middleNameTextField.getText();


        // throws error if last name has characters other than letters
        if (pInfo_lastNameTextField.getText().length() > 0) {

            for (int i = 0; i < pInfo_lastNameTextField.getText().length(); i++) {

                if (!Character.isLetter(pInfo_lastNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Last Name Must Have Only Letters");

                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if first name has characters other than letters
        if (pInfo_firstNameTextField.getText().length() > 0) {

            for (int i = 0; i < pInfo_firstNameTextField.getText().length(); i++) {

                if (!Character.isLetter(pInfo_firstNameTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "First Name Must Have Only Letters");

                    illegalFields = false;
                    break;
                }
            }
        }


        // throws error if middle name has characters other than letters
        if (pInfo_middleNameTextField.getText().length() > 0 &&
                !String.valueOf(pInfo_middleNameTextField.getText()).equals("N/A")) {

            for (int i = 0; i < middleName.length(); i++) {

                if (!Character.isLetter(middleName.charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "Middle Name Must Have Only Letters");

                    illegalFields = false;
                    break;
                }
            }
        }

        // throws error if SSN has characters other than numbers, or has less/more than 4 digits
        if (pInfo_SSNTextField.getText().length() > 0 && pInfo_SSNTextField.getText().length() != 4) {

            JOptionPane.showMessageDialog
                    (null, "Social Security # Must Have 4 Characters");

            illegalFields = false;

        } else if (pInfo_SSNTextField.getText().length() == 4) {

            for (int i = 0; i < 4; i++) {

                if (!Character.isDigit(pInfo_SSNTextField.getText().charAt(i))) {

                    JOptionPane.showMessageDialog
                            (null, "Social Security # Must Have Only Numbers");

                    illegalFields = false;
                    break;
                }
            }
        }
        // throws error if DOB isn't formatted correctly - "MM/DD/YYYY"

        if (pInfo_DOBTextField.getText().length() > 0 && pInfo_DOBTextField.getText().length() != 10) {
            JOptionPane.showMessageDialog
                    (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
            illegalFields = false;
        } else if (pInfo_DOBTextField.getText().length() == 10) {
            if (!dobParser(pInfo_DOBTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Date of Birth must be formatted \"MM/DD/YYYY\"");
                illegalFields = false;
            }
        }
        // throws error if phone number isn't formatted correctly - "###-###-####"

        if (pInfo_phoneNumberTextField.getText().length() > 0 && pInfo_phoneNumberTextField.getText().length() != 12) {
            JOptionPane.showMessageDialog
                    (null, "Phone Number Must be formatted \"###-###-####\"");
            illegalFields = false;
        } else if (pInfo_phoneNumberTextField.getText().length() == 12) {
            if (!phoneNumberParser(pInfo_phoneNumberTextField.getText())) {
                JOptionPane.showMessageDialog
                        (null, "Phone Number Must be formatted \"###-###-####\"");
                illegalFields = false;
            }
        }
        // throws error if address has characters other than letters and numbers

        if (pInfo_addressTextField.getText().length() > 0) {
            for (int i = 0; i < pInfo_addressTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_addressTextField.getText().charAt(i)) &&
                        !Character.isDigit(pInfo_addressTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Address Must Have Only Numbers and Letters");
                    illegalFields = false;
                }
            }
        }

        // throws error if city has characters other than letters
        if (pInfo_cityTextField.getText().length() > 0) {
            for (int i = 1; i < pInfo_cityTextField.getText().length(); i++) {
                if (!Character.isLetter(pInfo_cityTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "City Must Have Only Letters");
                    illegalFields = false;
                    break;
                }
            }
        }
        // throws error if zip code has characters other than numbers, or has less/more than 4 digits
        if (pInfo_zipCodeTextField.getText().length() > 0 && pInfo_zipCodeTextField.getText().length() != 5) {
            JOptionPane.showMessageDialog
                    (null, "Zip Code Must Have 5 Characters");
            illegalFields = false;
        } else if (pInfo_zipCodeTextField.getText().length() == 5) {
            for (int i = 0; i < 5; i++) {
                if (!Character.isDigit(pInfo_zipCodeTextField.getText().charAt(i))) {
                    JOptionPane.showMessageDialog
                            (null, "Zip Code Must Have Only Numbers");
                    illegalFields = false;
                    break;
                }
            }
        }

        // checks if there are no input errors
        patient = MainGUI.pimsSystem.patient_details
                (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_SSNTextField.getText()));

        if (emptyFields && illegalFields && patient != null) {
            JOptionPane.showMessageDialog
                    (null, "Information Updated");
            patient.setL_name(pInfo_lastNameTextField.getText());
            patient.setF_name(pInfo_firstNameTextField.getText());
            patient.setM_name(middleName);
            patient.setSSN(Integer.parseInt(pInfo_SSNTextField.getText()));
            patient.setDob(pInfo_DOBTextField.getText());
            patient.setP_number(pInfo_phoneNumberTextField.getText());
            patient.setAddress(pInfo_addressTextField.getText());
            patient.setCity(pInfo_cityTextField.getText());
            patient.setState(String.valueOf(pInfo_stateComboBox.getSelectedItem()));
            patient.setZip(Integer.parseInt(pInfo_zipCodeTextField.getText()));
            patient.setUser_name(pInfo_patient_userField.getText());
            patient.setPassword(pInfo_patient_pwField.getText());
        } else if (!String.valueOf(errorMessage).equals("Must Enter"))
            JOptionPane.showMessageDialog(null, errorMessage);
        else if (patient == null)
            JOptionPane.showMessageDialog(null, "Error");
    } // end pInfo_updateInfo

    // Appointments Tab listeners
    private void app_requestAppointment(){

        patient = MainGUI.pimsSystem.patient_details
                (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_SSNTextField.getText()));

        if (patient != null) {

            String message = MainGUI.pimsSystem.add_date(datePicker.getText(), timePicker.getText(), patient);
            JOptionPane.showMessageDialog(null, message);

        } else JOptionPane.showMessageDialog(null, "Error");
    }// end app_requestAppointment

    private void app_lookUpAppointment(){

        patient = MainGUI.pimsSystem.patient_details
                (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_SSNTextField.getText()));

        String appointment = MainGUI.pimsSystem.lookUpAppointmentDate(patient);

        if (String.valueOf(appointment).equals(""))
            JOptionPane.showMessageDialog
                    (null, "You Don't Have An Appointment Scheduled At This Time");
        else app_lookUpAppointmentTextField.setText(appointment);
    }// end app_lookUpAppointment

    private void app_cancelAppointment(){

        patient = MainGUI.pimsSystem.patient_details
                (pInfo_lastNameTextField.getText(), Integer.parseInt(pInfo_SSNTextField.getText()));

        if (!MainGUI.pimsSystem.patient_delete_date(patient))
            JOptionPane.showMessageDialog
                    (null, "You Have No Appointment Scheduled At This Time");
        else {
            JOptionPane.showMessageDialog(null, "Appointment Deleted");
            app_lookUpAppointmentTextField.setText("");
        }
    }// end app_cancelAppointment

/* END Action Listener related functions*/

/* START Helper Functions */

    // method to parse the DOB and make
    // sure it's in the "MM/DD/YYYY" format
    private boolean dobParser(String string) {
        if (!Character.isDigit(string.charAt(0)))
            return false;
        else if (!Character.isDigit(string.charAt(1)))
            return false;
        else if (string.charAt(2) != '/')
            return false;
        else if (!Character.isDigit(string.charAt(3)))
            return false;
        else if (!Character.isDigit(string.charAt(4)))
            return false;
        else if (string.charAt(5) != '/')
            return false;
        else if (!Character.isDigit(string.charAt(6)))
            return false;
        else if (!Character.isDigit(string.charAt(7)))
            return false;
        else if (!Character.isDigit(string.charAt(8)))
            return false;
        else if (!Character.isDigit(string.charAt(9)))
            return false;
        return true;
    } // end dobParser


    // method to parse the phone number and make
    // sure it's in the "###-###-####" format
    private boolean phoneNumberParser(String string) {
        if (!Character.isDigit(string.charAt(0)))
            return false;
        else if (!Character.isDigit(string.charAt(1)))
            return false;
        else if (!Character.isDigit(string.charAt(2)))
            return false;
        else if (string.charAt(3) != '-')
            return false;
        else if (!Character.isDigit(string.charAt(4)))
            return false;
        else if (!Character.isDigit(string.charAt(5)))
            return false;
        else if (!Character.isDigit(string.charAt(6)))
            return false;
        else if (string.charAt(7) != '-')
            return false;
        else if (!Character.isDigit(string.charAt(8)))
            return false;
        else if (!Character.isDigit(string.charAt(9)))
            return false;
        else if (!Character.isDigit(string.charAt(10)))
            return false;
        else if (!Character.isDigit(string.charAt(11)))
            return false;
        return true;
    } // end phoneNumberParser

/* END helper functions */

/* START Appointments Tab: DatePicker & TimePicker related methods */

    // method to create a date picker
    private DatePicker createDatePicker() {

        DatePickerSettings datePickerSettings = new DatePickerSettings();

        datePickerSettings.setAllowEmptyDates(false);
        datePickerSettings.setAllowKeyboardEditing(false);

        DatePicker datePicker = new DatePicker(datePickerSettings);

        // If today is Saturday or Sunday, this sets the default
        // to the following Monday
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
            datePicker.setDate(LocalDate.now().plusDays(3));
        } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
            datePicker.setDate(LocalDate.now().plusDays(2));
        } else datePicker.setDate(LocalDate.now().plusDays(1));

        // Veto Policy to disallow weekends
        datePickerSettings.setVetoPolicy(new VetoDates());

        return datePicker;

    } // end createDatePicker

    // method to create a time picker
    private TimePicker createTimePicker() {

        TimePickerSettings timeSettings = new TimePickerSettings();
        timeSettings.initialTime = LocalTime.of(9, 0);
        timeSettings.setAllowKeyboardEditing(false);
        timeSettings.generatePotentialMenuTimes
                (TimePickerSettings.TimeIncrement.OneHour,
                        null, null);

        TimePicker timePicker = new TimePicker(timeSettings);
        timeSettings.setVetoPolicy(new VetoTimes());

        return timePicker;

    } // end createTimePicker

/* END of TimePicker & DatePicker related methods & private classes */

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

}// end PatientGUI