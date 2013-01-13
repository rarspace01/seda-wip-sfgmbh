package de.sfgmbh.comlayer.core.views;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.BaseBtnAddToStundenplan;
import de.sfgmbh.comlayer.core.controller.BaseBtnFailureprompt;
import de.sfgmbh.comlayer.core.controller.BaseBtnLogin;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.BaseRdbtnTopLeft;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChair;
import de.sfgmbh.comlayer.core.model.CmbboxFilterCourse;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.init.Bootstrap;

public class BaseTab extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// GUI components
	public JLayeredPane contentPane;
	public JTabbedPane mainTabbedContainerPane;
	public JPanel startScreenPanel;
	private JComboBox<String> comboBoxChairFilter;
	private JComboBox<String> comboBoxLecturerFilter;
	private JComboBox<String> comboBoxSemesterFilter;
	private JComboBox<String> comboBoxOrgaFilter;
	private JLabel lblLehrveranstaltung;
	private JLabel lblLehrstuhl;
	private JLabel lblDozent;
	private JLabel lblLoggedIn;
	private JLabel lblSemester;
	private JTable organisationTable;
	private JScrollPane mainTableScrollPane;
	private JPanel tickerJPanel;
	private JTextPane tickerMsgPos1;
	private JPanel panelLogin;
	private JPanel panelLogout;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPasswort;
	private JLabel lblBenutzername;
	private JButton btnNewButton;
	private JTextField txtBenutzername;
	private JPasswordField pwdPasswort;
	private JRadioButton rdbtnLehrveranstaltungen;
	private JRadioButton rdbtnRaume;
	private JButton btnFehlermeldung;
	private JButton btnLogout;
	private JLabel lblNewLabel;
	private JTable roomTable;
	
	

	/**
	 * Create the frame. v.175
	 */
	@SuppressWarnings({ })
	public BaseTab() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("UnivIS 2.0");
		createContents();
	}
	private void createContents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 648);
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
		
		lblLehrveranstaltung = new JLabel("Lehrveranstaltung:");
		startScreenPanel.add(lblLehrveranstaltung, "cell 2 0,alignx left,aligny bottom");
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		startScreenPanel.add(lblLehrstuhl, "cell 3 0,aligny bottom");
		
		lblDozent = new JLabel("Dozent:");
		startScreenPanel.add(lblDozent, "cell 4 0,aligny bottom");
		
		lblSemester = new JLabel("Semester:");
		startScreenPanel.add(lblSemester, "cell 5 0,aligny bottom");
		
		comboBoxOrgaFilter = new JComboBox<String>();
		comboBoxOrgaFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxOrgaFilter.addActionListener(new BaseCmbboxFilter());
		startScreenPanel.add(getLblNewLabel(), "cell 6 0,alignx center");
		startScreenPanel.add(getRdbtnLehrveranstaltungen(), "cell 0 0,aligny bottom");
		startScreenPanel.add(getRdbtnRaume(), "cell 0 1");
		comboBoxOrgaFilter.setAutoscrolls(true);
		comboBoxOrgaFilter.setEditable(true);
		comboBoxOrgaFilter.setModel(new CmbboxFilterCourse());
		startScreenPanel.add(comboBoxOrgaFilter, "cell 2 1,growx");
		
		comboBoxChairFilter = new JComboBox<String>();
		comboBoxChairFilter.setModel(new CmbboxFilterChair());
		comboBoxChairFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxChairFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxChairFilter.setEditable(true);
		comboBoxChairFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxChairFilter, "cell 3 1,growx");
		
		comboBoxLecturerFilter = new JComboBox<String>();
		comboBoxLecturerFilter.setModel(new CmbboxFilterLecturer());
		comboBoxLecturerFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxLecturerFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxLecturerFilter.setEditable(true);
		comboBoxLecturerFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxLecturerFilter, "cell 4 1,growx");
		
		comboBoxSemesterFilter = new JComboBox<String>();
		comboBoxSemesterFilter.setModel(new CmbboxFilterSemester());
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
		panel.setBounds(0, 6, 140, 313);
		tickerJPanel.add(panel);
		panel.setLayout(null);
		
		JTextPane tickerMsgPos2 = new JTextPane();
		tickerMsgPos2.setEditable(false);
		tickerMsgPos2.setBounds(6, 108, 123, 194);
		panel.add(tickerMsgPos2);
		tickerMsgPos2.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos2.setContentType("text/html");
		tickerMsgPos2.setText("<div style=\"font-family: Calibri, monospace;\"><strong>Logindaten:</strong><br>Dozenten:<br>Doz // Doz <br><br>Studenten:<br>Stud // Stud<br><br>Verwaltung:<br>Verw // Verw <br><br> Anderenfalls Fehler!</div>");
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setEditable(false);
		tickerMsgPos1.setBounds(6, 6, 123, 90);
		panel.add(tickerMsgPos1);
		tickerMsgPos1.setBackground(UIManager.getColor("Button.light"));
		tickerMsgPos1.setContentType("text/html");
		tickerMsgPos1.setText("<div style=\"font-family: font-family: Calibri, monospace;\"><strong>News:</strong><br> <span style=\"color:red\">Neu!</span> Testlogin jetzt m\u00F6glich!</div>");
		
		tickerJPanel.add(getPanelLogin());
		
		tickerJPanel.add(getPanelLogout());
		
		mainTableScrollPane = new JScrollPane();
		startScreenPanel.add(mainTableScrollPane, "cell 2 2 4 1,grow");
		
		mainTableScrollPane.setViewportView(getOrganisationTable());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		startScreenPanel.add(buttonPanel, "cell 6 2,grow");
		buttonPanel.setLayout(null);
		
		JButton btnAddToStundenplan = new JButton("+");
		btnAddToStundenplan.addActionListener(new BaseBtnAddToStundenplan("plus"));
		btnAddToStundenplan.setBounds(0, 29, 41, 23);
		buttonPanel.add(btnAddToStundenplan);
		
		btnFehlermeldung = new JButton("Fehlermeldung");
		btnFehlermeldung.addActionListener(new BaseBtnFailureprompt("error"));
		startScreenPanel.add(btnFehlermeldung, "cell 2 3");
		
	}
	
	public JScrollPane getMainTableScrollPane() {
		return mainTableScrollPane;
	}
	public JTable getOrganisationTable() {
		if (organisationTable == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);
			
			organisationTable = new JTable();
			organisationTable.setBackground(SystemColor.activeCaption);
			organisationTable.setShowVerticalLines(false);
			organisationTable.setModel(Bootstrap.serviceManager.getCoreBaseTableModel());
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
			organisationTable.getColumnModel().getColumn(8).setCellRenderer(center);
			organisationTable.getColumnModel().getColumn(1).setCellRenderer(center);
			organisationTable.getColumnModel().getColumn(6).setCellRenderer(center);
			organisationTable.getColumnModel().removeColumn(organisationTable.getColumn("Hidden"));
		}
		return organisationTable;
	}
	
	public JTable getRoomTable() {
		if (roomTable == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);	
		
		roomTable = new JTable();
		roomTable.setShowVerticalLines(false);
		roomTable.setModel(Bootstrap.serviceManager.getCoreRoomTableModel());
		roomTable.setBackground(SystemColor.activeCaption);
		mainTableScrollPane.setViewportView(roomTable);
		roomTable.getColumnModel().getColumn(0).setResizable(false);
		roomTable.getColumnModel().getColumn(0).setPreferredWidth(75);
		roomTable.getColumnModel().getColumn(0).setMinWidth(75);
		roomTable.getColumnModel().getColumn(0).setMaxWidth(75);
		roomTable.getColumnModel().getColumn(1).setResizable(false);
		roomTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		roomTable.getColumnModel().getColumn(1).setMinWidth(75);
		roomTable.getColumnModel().getColumn(1).setMaxWidth(75);
		roomTable.getColumnModel().getColumn(2).setResizable(false);
		roomTable.getColumnModel().getColumn(2).setMinWidth(75);
		roomTable.getColumnModel().getColumn(2).setMaxWidth(75);
		roomTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		roomTable.getColumnModel().getColumn(3).setMinWidth(50);
		roomTable.getColumnModel().getColumn(3).setMaxWidth(50);
		roomTable.getColumnModel().getColumn(4).setPreferredWidth(75);
		roomTable.getColumnModel().getColumn(4).setMinWidth(75);
		roomTable.getColumnModel().getColumn(4).setMaxWidth(75);
		roomTable.getColumnModel().getColumn(5).setResizable(false);
		roomTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		roomTable.getColumnModel().getColumn(5).setMinWidth(50);
		roomTable.getColumnModel().getColumn(5).setMaxWidth(50);
		roomTable.getColumnModel().getColumn(6).setResizable(false);
		roomTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		roomTable.getColumnModel().getColumn(6).setMinWidth(60);
		roomTable.getColumnModel().getColumn(6).setMaxWidth(60);
		roomTable.getColumnModel().getColumn(7).setResizable(false);
		roomTable.getColumnModel().getColumn(7).setPreferredWidth(70);
		roomTable.getColumnModel().getColumn(7).setMinWidth(70);
		roomTable.getColumnModel().getColumn(7).setMaxWidth(70);
		roomTable.getColumnModel().getColumn(8).setResizable(false);
		roomTable.getColumnModel().getColumn(8).setPreferredWidth(75);
		roomTable.getColumnModel().getColumn(8).setMinWidth(75);
		roomTable.getColumnModel().getColumn(8).setMaxWidth(75);
		roomTable.getColumnModel().getColumn(9).setPreferredWidth(75);
		roomTable.getColumnModel().getColumn(9).setMinWidth(75);
		roomTable.getColumnModel().getColumn(9).setMaxWidth(75);
		/*roomTable.getColumnModel().getColumn(8).setCellRenderer(center);
		roomTable.getColumnModel().getColumn(1).setCellRenderer(center);
		roomTable.getColumnModel().getColumn(6).setCellRenderer(center);
		roomTable.getColumnModel().removeColumn(roomTable.getColumn("Hidden"));*/
		
		}
		return roomTable;
	}
	
	public void setOrganisationTable(JTable organisationTable) {
		this.organisationTable = organisationTable;
	}
	public JLabel getLblBenutzername() {
		if (lblBenutzername == null) {
			lblBenutzername = new JLabel("Benutzername:");
		}
		return lblBenutzername;
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Einloggen");
			btnNewButton.addActionListener(new BaseBtnLogin());
		}
		return btnNewButton;
	}
	public JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new BaseBtnLogin("logout"));
		}
		return btnLogout;
	}
	public JTextField getTxtBenutzername() {
		if (txtBenutzername == null) {
			txtBenutzername = new JTextField();
			txtBenutzername.setColumns(10);
		}
		return txtBenutzername;
	}
	public JPasswordField getPwdPasswort() {
		if (pwdPasswort == null) {
			pwdPasswort = new JPasswordField();
		}
		return pwdPasswort;
	}
	public JRadioButton getRdbtnLehrveranstaltungen() {
		if (rdbtnLehrveranstaltungen == null) {
			rdbtnLehrveranstaltungen = new JRadioButton("Lehrveranstaltungen");
			rdbtnLehrveranstaltungen.addActionListener(new BaseRdbtnTopLeft("course"));
			buttonGroup.add(rdbtnLehrveranstaltungen);
			rdbtnLehrveranstaltungen.setSelected(true);
			rdbtnLehrveranstaltungen.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnLehrveranstaltungen;
	}
	public JRadioButton getRdbtnRaume() {
		if (rdbtnRaume == null) {
			rdbtnRaume = new JRadioButton("R\u00E4ume");
			rdbtnRaume.addActionListener(new BaseRdbtnTopLeft("room"));
			buttonGroup.add(rdbtnRaume);
			rdbtnRaume.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRaume;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
			lblNewLabel.setMaximumSize(new Dimension(50,50));
			
		}
		return lblNewLabel;
	}
	public JPanel getPanelLogin() {
		if (panelLogin == null) {
			panelLogin = new JPanel();
			panelLogin.setBounds(0, 319, 140, 150);
			panelLogin.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
			panelLogin.add(getLblBenutzername(), "cell 0 0");
			panelLogin.add(getTxtBenutzername(), "cell 0 1,growx");
			lblPasswort = new JLabel("Passwort:");
			panelLogin.add(lblPasswort, "cell 0 2");
			panelLogin.add(getPwdPasswort(), "cell 0 3,growx");
			panelLogin.add(getBtnNewButton(), "cell 0 4,alignx right");
		}
		return panelLogin;
	}
	public JPanel getPanelLogout() {
		if (panelLogout == null) {
			panelLogout = new JPanel();
			panelLogout.setBounds(0, 319, 140, 150);
			panelLogout.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
			lblLoggedIn = new JLabel("Du bist eingeloggt!");
			panelLogout.add(lblLoggedIn, "cell 0 0");
			panelLogout.add(getBtnLogout(), "cell 0 1,alignx left");
			panelLogout.setVisible(false);
		}
		return panelLogout;
	}
	/**
	 * @return the comboBoxLehrstuhlFilter
	 */
	public JComboBox<String> getComboBoxChariFilter() {
		return comboBoxChairFilter;
	}
	/**
	 * @return the comboBoxDozentFilter
	 */
	public JComboBox<String> getComboBoxLecturerFilter() {
		return comboBoxLecturerFilter;
	}
	/**
	 * @return the comboBoxSemesterFilter
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter;
	}
	/**
	 * @return the comboBoxVeranstaltungFilter
	 */
	public JComboBox<String> getComboBoxOrgaFilter() {
		return comboBoxOrgaFilter;
	}
}
