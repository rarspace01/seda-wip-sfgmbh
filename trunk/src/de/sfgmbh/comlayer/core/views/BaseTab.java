package de.sfgmbh.comlayer.core.views;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import de.sfgmbh.comlayer.core.controller.BaseBtnAddToStundenplan;
import de.sfgmbh.comlayer.core.controller.BaseBtnFailureprompt;
import de.sfgmbh.comlayer.core.controller.BaseBtnLogin;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.BaseRdbtnTopLeft;
import de.sfgmbh.comlayer.core.model.BaseCmbboxModelFilter;
import de.sfgmbh.init.Bootstrap;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class BaseTab extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// GUI components
	private JLayeredPane contentPane;
	public JTabbedPane mainTabbedContainerPane;
	public JPanel startScreenPanel;
	private JComboBox<String> comboBoxOrganisationFilter;
	private JLabel lblCourse;
	private JLabel lblProfessorship;
	private JLabel lblLecturer;
	private JComboBox<String> comboBoxProfessorshipFilter;
	private JComboBox<String> comboBoxLecturerFilter;
	private JComboBox<String> comboBoxSemesterFilter;
	private JLabel lblSemester;
	private JTable organisationTable;
	private JScrollPane mainTableScrollPane;
	private JPanel tickerJPanel;
	private JTextPane tickerMsgPos1;
	private JPanel panel_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPassword;
	private JLabel lblUsername;
	private JButton btnNewButton;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JRadioButton rdbtnCourses;
	private JRadioButton rdbtnRooms;
	private JButton btnFailureprompt;
	private JTextPane tickerMsgPos3;
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public BaseTab() {
		setTitle("UnivIS 2.0");
		createContents();
	}
	private void createContents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 593);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		mainTabbedContainerPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedContainerPane.setVisible(false);
		contentPane.add(mainTabbedContainerPane, "name_5247024799318");
		
		startScreenPanel = new JPanel();
		startScreenPanel.setMaximumSize(new Dimension(10, 32767));
		contentPane.setLayer(startScreenPanel, 1);
		contentPane.add(startScreenPanel, "name_5256771068822");
		startScreenPanel.setLayout(new MigLayout("", "[grow][][grow][grow][grow][grow][grow]", "[][][grow][]"));
		startScreenPanel.add(getRdbtnLehrveranstaltungen(), "cell 0 0");
		
		lblCourse = new JLabel("Lehrveranstaltung:");
		startScreenPanel.add(lblCourse, "cell 2 0,alignx left,aligny center");
		
		lblProfessorship = new JLabel("Lehrstuhl:");
		startScreenPanel.add(lblProfessorship, "cell 3 0");
		
		lblLecturer = new JLabel("Dozent:");
		startScreenPanel.add(lblLecturer, "cell 4 0");
		
		lblSemester = new JLabel("Semester:");
		startScreenPanel.add(lblSemester, "cell 5 0");
		
		comboBoxOrganisationFilter = new JComboBox<String>();
		comboBoxOrganisationFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxOrganisationFilter.addActionListener(new BaseCmbboxFilter());
		startScreenPanel.add(getRdbtnRaume(), "cell 0 1");
		comboBoxOrganisationFilter.setAutoscrolls(true);
		comboBoxOrganisationFilter.setEditable(true);
		comboBoxOrganisationFilter.setModel(new BaseCmbboxModelFilter("Veranst"));
		startScreenPanel.add(comboBoxOrganisationFilter, "cell 2 1,growx");
		
		comboBoxProfessorshipFilter = new JComboBox<String>();
		comboBoxProfessorshipFilter.setModel(new BaseCmbboxModelFilter("Lehrstuhl"));
		comboBoxProfessorshipFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxProfessorshipFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxProfessorshipFilter.setEditable(true);
		comboBoxProfessorshipFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxProfessorshipFilter, "cell 3 1,growx");
		
		comboBoxLecturerFilter = new JComboBox<String>();
		comboBoxLecturerFilter.setModel(new BaseCmbboxModelFilter("Doz"));
		comboBoxLecturerFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxLecturerFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxLecturerFilter.setEditable(true);
		comboBoxLecturerFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxLecturerFilter, "cell 4 1,growx");
		
		comboBoxSemesterFilter = new JComboBox<String>();
		comboBoxSemesterFilter.setModel(new BaseCmbboxModelFilter("Sem"));
		comboBoxSemesterFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter.setEditable(true);
		comboBoxSemesterFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxSemesterFilter, "cell 5 1,growx");
		
		tickerJPanel = new JPanel();
		tickerJPanel.setMinimumSize(new Dimension(140, 10));
		tickerJPanel.setBorder(null);
		tickerJPanel.setMaximumSize(new Dimension(140, 32767));
		startScreenPanel.add(tickerJPanel, "cell 0 2,grow");
		tickerJPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		panel.setBounds(0, 6, 140, 334);
		tickerJPanel.add(panel);
		panel.setLayout(null);
		
		JTextPane tickerMsgPos2 = new JTextPane();
		tickerMsgPos2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		tickerMsgPos2.setBounds(6, 63, 123, 176);
		panel.add(tickerMsgPos2);
		tickerMsgPos2.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos2.setContentType("text/html");
		tickerMsgPos2.setText("<div style=\"font-family:arial\" size=\"10\"><strong>Info:</strong><br>Logindaten: <br>Dozenten: Doz // Doz <br>Studenten: Stud // Stud<br> Verwaltung: Verw // Verw <br> Anderenfalls Fehler! Oder:</div>");
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBounds(6, 6, 123, 57);
		panel.add(tickerMsgPos1);
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setContentType("text/html");
		tickerMsgPos1.setText("<div style=\"font-family:arial\" size=\"11\"><strong>News:</strong><br> <span style=\"color:red\">Neu!</span> Testlogin jetzt m\u00F6glich!</div>");
		
		tickerMsgPos3 = new JTextPane();
		tickerMsgPos3.setFont(new Font("Arial", Font.BOLD, 14));
		tickerMsgPos3.setText("Fehler: Es wurde keine Lehrveranstaltung oder Meldung gefunden!");
		tickerMsgPos3.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos3.setBounds(6, 242, 124, 90);
		panel.add(tickerMsgPos3);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 337, 140, 143);
		tickerJPanel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		panel_1.add(getLblUsername(), "cell 0 0");
		panel_1.add(getTxtBenutzername(), "cell 0 1,growx");
		tickerMsgPos2.setBounds(6, 108, 123, 194);
		panel.add(tickerMsgPos2);
		tickerMsgPos2.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos2.setContentType("text/html");
		tickerMsgPos2.setText("<div style=\"font-family: Calibri, monospace;\"><strong>Logindaten:</strong><br>Dozenten:<br>Doz // Doz <br><br>Studenten:<br>Stud // Stud<br><br>Verwaltung:<br>Verw // Verw <br><br> Anderenfalls Fehler!</div>");
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBounds(6, 6, 123, 90);
		panel.add(tickerMsgPos1);
		tickerMsgPos1.setBackground(UIManager.getColor("Button.light"));
		tickerMsgPos1.setContentType("text/html");
		tickerMsgPos1.setText("<div style=\"font-family: font-family: Calibri, monospace;\"><strong>News:</strong><br> <span style=\"color:red\">Neu!</span> Testlogin jetzt m\u00F6glich!</div>");
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 319, 140, 161);
		tickerJPanel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		panel_1.add(getLblBenutzername(), "cell 0 0");
		panel_1.add(getTxtBenutzername(), "cell 0 1,growx");
		
		lblPassword = new JLabel("Passwort:");
		panel_1.add(lblPassword, "cell 0 2");
		panel_1.add(getPwdPasswort(), "cell 0 3,growx");
		panel_1.add(getBtnNewButton(), "cell 0 4 1 2,alignx right");
		
		mainTableScrollPane = new JScrollPane();
		startScreenPanel.add(mainTableScrollPane, "cell 2 2 4 1,grow");
		
		organisationTable = new JTable();
		mainTableScrollPane.setViewportView(organisationTable);
		organisationTable.setBackground(SystemColor.activeCaption);
		organisationTable.setShowVerticalLines(false);
		organisationTable.setModel(Bootstrap.serviceManager.getCoreBaseTableModel());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		startScreenPanel.add(buttonPanel, "cell 6 2,grow");
		buttonPanel.setLayout(null);
		
		JButton btnAddToStundenplan = new JButton("+");
		btnAddToStundenplan.addActionListener(new BaseBtnAddToStundenplan("plus"));
		btnAddToStundenplan.setBounds(0, 29, 41, 23);
		buttonPanel.add(btnAddToStundenplan);
		startScreenPanel.add(getBtnFehlermeldung(), "cell 2 3");
		organisationTable.getColumnModel().getColumn(0).setResizable(false);
		organisationTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		organisationTable.getColumnModel().getColumn(0).setMinWidth(50);
		organisationTable.getColumnModel().getColumn(0).setMaxWidth(50);
		organisationTable.getColumnModel().getColumn(1).setResizable(false);
		organisationTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		organisationTable.getColumnModel().getColumn(1).setMinWidth(25);
		organisationTable.getColumnModel().getColumn(1).setMaxWidth(25);
		organisationTable.getColumnModel().getColumn(2).setResizable(false);
		organisationTable.getColumnModel().getColumn(2).setMinWidth(75);
		organisationTable.getColumnModel().getColumn(2).setMaxWidth(75);
		organisationTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		organisationTable.getColumnModel().getColumn(3).setMinWidth(80);
		organisationTable.getColumnModel().getColumn(3).setMaxWidth(200);
		organisationTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		organisationTable.getColumnModel().getColumn(4).setMinWidth(70);
		organisationTable.getColumnModel().getColumn(4).setMaxWidth(200);
		organisationTable.getColumnModel().getColumn(5).setResizable(false);
		organisationTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		organisationTable.getColumnModel().getColumn(5).setMinWidth(30);
		organisationTable.getColumnModel().getColumn(5).setMaxWidth(30);
		organisationTable.getColumnModel().getColumn(6).setResizable(false);
		organisationTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		organisationTable.getColumnModel().getColumn(6).setMinWidth(60);
		organisationTable.getColumnModel().getColumn(6).setMaxWidth(60);
		organisationTable.getColumnModel().getColumn(7).setResizable(false);
		organisationTable.getColumnModel().getColumn(7).setPreferredWidth(70);
		organisationTable.getColumnModel().getColumn(7).setMinWidth(70);
		organisationTable.getColumnModel().getColumn(7).setMaxWidth(70);
		organisationTable.getColumnModel().getColumn(8).setResizable(false);
		organisationTable.getColumnModel().getColumn(8).setPreferredWidth(32);
		organisationTable.getColumnModel().getColumn(8).setMinWidth(32);
		organisationTable.getColumnModel().getColumn(8).setMaxWidth(32);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		organisationTable.getColumnModel().getColumn(8).setCellRenderer(center);
		organisationTable.getColumnModel().getColumn(1).setCellRenderer(center);
		organisationTable.getColumnModel().getColumn(6).setCellRenderer(center);
	}
	public JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Benutzername:");
		}
		return lblUsername;
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Einloggen");
			btnNewButton.addActionListener(new BaseBtnLogin());
		}
		return btnNewButton;
	}
	private JButton getBtnFehlermeldung() {
		if (btnFailureprompt == null) {
			btnFailureprompt = new JButton("Fehlermeldung");
			btnFailureprompt.addActionListener(new BaseBtnFailureprompt("error"));
		}
		return btnFailureprompt;
	}
	public JTextField getTxtBenutzername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}
	public JPasswordField getPwdPasswort() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
		}
		return pwdPassword;
	}
	public JRadioButton getRdbtnLehrveranstaltungen() {
		if (rdbtnCourses == null) {
			rdbtnCourses = new JRadioButton("Lehrveranstaltungen");
			rdbtnCourses.addActionListener(new BaseRdbtnTopLeft());
			buttonGroup.add(rdbtnCourses);
			rdbtnCourses.setSelected(true);
			rdbtnCourses.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnCourses;
	}
	public JRadioButton getRdbtnRaume() {
		if (rdbtnRooms == null) {
			rdbtnRooms = new JRadioButton("R\u00E4ume");
			rdbtnRooms.addActionListener(new BaseRdbtnTopLeft());
			buttonGroup.add(rdbtnRooms);
			rdbtnRooms.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRooms;
	}
	
}
