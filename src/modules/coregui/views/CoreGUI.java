package modules.coregui.views;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.table.*;
import javax.swing.border.*;
import services.*;
import modules.coregui.controller.*;

public class CoreGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// GUI components
	private JLayeredPane contentPane;
	public JTabbedPane mainTabbedContainerPane;
	public JPanel startScreenPanel;
	private JRadioButton rdbtnLehrveranstaltungen;
	private JRadioButton rdbtnRume;
	private JComboBox<String> comboBoxVeranstaltungFilter;
	private JLabel lblLehrveranstaltung;
	private JLabel lblLehrstuhl;
	private JLabel lblDozent;
	private JComboBox<String> comboBoxLehrstuhlFilter;
	private JComboBox<String> comboBoxDozentFilter;
	private JComboBox<String> comboBoxSemesterFilter;
	private JLabel lblSemester;
	private JTable veranstaltungsTable;
	private JScrollPane mainTableScrollPane;
	private JPanel tickerJPanel;
	private JTextPane tickerMsgPos1;
	private JPanel panel_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordField;
	private JTextField txtBenutzername;
	private JLabel lblPasswort;
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CoreGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		startScreenPanel.setLayout(new MigLayout("", "[grow][][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		rdbtnLehrveranstaltungen = new JRadioButton("Lehrveranstaltungen");
		buttonGroup.add(rdbtnLehrveranstaltungen);
		rdbtnLehrveranstaltungen.setMargin(new Insets(0, 0, 0, 0));
		rdbtnLehrveranstaltungen.setSelected(true);
		startScreenPanel.add(rdbtnLehrveranstaltungen, "cell 0 0");
		
		lblLehrveranstaltung = new JLabel("Lehrveranstaltung:");
		startScreenPanel.add(lblLehrveranstaltung, "cell 2 0,alignx left,aligny center");
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		startScreenPanel.add(lblLehrstuhl, "cell 3 0");
		
		lblDozent = new JLabel("Dozent:");
		startScreenPanel.add(lblDozent, "cell 4 0");
		
		lblSemester = new JLabel("Semester:");
		startScreenPanel.add(lblSemester, "cell 5 0");
		
		rdbtnRume = new JRadioButton("R\u00E4ume");
		buttonGroup.add(rdbtnRume);
		rdbtnRume.setMargin(new Insets(0, 0, 0, 0));
		startScreenPanel.add(rdbtnRume, "cell 0 1");
		
		comboBoxVeranstaltungFilter = new JComboBox<String>();
		comboBoxVeranstaltungFilter.addKeyListener(new VeranstaltungsFilter());
		comboBoxVeranstaltungFilter.addActionListener(new VeranstaltungsFilter());
		comboBoxVeranstaltungFilter.setAutoscrolls(true);
		comboBoxVeranstaltungFilter.setEditable(true);
		comboBoxVeranstaltungFilter.setModel(new DefaultComboBoxModel(new String[] {"<alle>", "WI Projekt", "Standards und Netzwerke"}));
		startScreenPanel.add(comboBoxVeranstaltungFilter, "cell 2 1,growx");
		
		comboBoxLehrstuhlFilter = new JComboBox<String>();
		comboBoxLehrstuhlFilter.setModel(new DefaultComboBoxModel(new String[] {"<alle>", "Lehrstuhl Eins", "Lehrstuhl Zwei", "SEDA", "IISM", "ISDL"}));
		comboBoxLehrstuhlFilter.setEditable(true);
		comboBoxLehrstuhlFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxLehrstuhlFilter, "cell 3 1,growx");
		
		comboBoxDozentFilter = new JComboBox<String>();
		comboBoxDozentFilter.setModel(new DefaultComboBoxModel(new String[] {"<alle>", "Sinz", "Ferstel", "Wolf", "Krieger"}));
		comboBoxDozentFilter.setEditable(true);
		comboBoxDozentFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxDozentFilter, "cell 4 1,growx");
		
		comboBoxSemesterFilter = new JComboBox<String>();
		comboBoxSemesterFilter.setModel(new DefaultComboBoxModel(new String[] {"<alle>", "WS 12 / 13", "SS 13", "WS 13 /14"}));
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
		tickerMsgPos2.setBounds(6, 108, 123, 90);
		panel.add(tickerMsgPos2);
		tickerMsgPos2.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos2.setContentType("text/html");
		tickerMsgPos2.setText("<div style=\"font-family:arial\"><strong>Meldung:</strong><br> In 5 Minuten beginnt SEDA-DMS-B in Raum WP3/04.003</div>");
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBounds(6, 6, 123, 90);
		panel.add(tickerMsgPos1);
		tickerMsgPos1.setBackground(UIManager.getColor("Button.light"));
		tickerMsgPos1.setContentType("text/html");
		tickerMsgPos1.setText("<div style=\"font-family:arial\"><strong>News:</strong><br> <span style=\"color:red\">Alle</span> Vorlesungen n\u00E4chste Woche entfallen!</div>");
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 319, 140, 161);
		tickerJPanel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		
		JButton btnTestLoginAs_1 = new JButton("Test: Login as Ver.");
		panel_1.add(btnTestLoginAs_1, "cell 0 0");
		btnTestLoginAs_1.addActionListener(new VerwLoginButton());
		
		JButton btnTestLoginAs = new JButton("Test: Login as Doz.");
		panel_1.add(btnTestLoginAs, "cell 0 1");
		btnTestLoginAs.addActionListener(new DozLoginButton());
		
		JButton btnNewButton = new JButton("Test: Login as Stud");
		panel_1.add(btnNewButton, "cell 0 2");
		
		txtBenutzername = new JTextField();
		txtBenutzername.setText("Benutzername");
		panel_1.add(txtBenutzername, "cell 0 3,growx");
		txtBenutzername.setColumns(10);
		
		lblPasswort = new JLabel("Passwort:");
		panel_1.add(lblPasswort, "cell 0 4");
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Passwort");
		panel_1.add(passwordField, "cell 0 5,growx");
		btnNewButton.addActionListener(new StudLoginButton());
		
		mainTableScrollPane = new JScrollPane();
		startScreenPanel.add(mainTableScrollPane, "cell 2 2 4 1,grow");
		
		veranstaltungsTable = new JTable();
		mainTableScrollPane.setViewportView(veranstaltungsTable);
		veranstaltungsTable.setBackground(SystemColor.activeCaption);
		veranstaltungsTable.setShowVerticalLines(false);
		veranstaltungsTable.setModel(Bootstrap.serviceManager.getVTableModel());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		startScreenPanel.add(buttonPanel, "cell 6 2,grow");
		buttonPanel.setLayout(null);
		
		JButton btnAddToStundenplan = new JButton("+");
		btnAddToStundenplan.addActionListener(new AddToStundenplanButton());
		btnAddToStundenplan.setBounds(10, 30, 41, 23);
		buttonPanel.add(btnAddToStundenplan);
		
		JLabel lblLehrveranstaltungenAuswhlenUnd = new JLabel("<html>Lehrveranstaltungen ausw\u00E4hlen und zur Sammlung zum Stundenplan hinzuf\u00FCgen!</html>");
		lblLehrveranstaltungenAuswhlenUnd.setToolTipText("");
		lblLehrveranstaltungenAuswhlenUnd.setVerticalAlignment(SwingConstants.TOP);
		lblLehrveranstaltungenAuswhlenUnd.setBounds(0, 64, 100, 105);
		buttonPanel.add(lblLehrveranstaltungenAuswhlenUnd);
		veranstaltungsTable.getColumnModel().getColumn(0).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		veranstaltungsTable.getColumnModel().getColumn(0).setMinWidth(50);
		veranstaltungsTable.getColumnModel().getColumn(0).setMaxWidth(50);
		veranstaltungsTable.getColumnModel().getColumn(1).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(1).setPreferredWidth(25);
		veranstaltungsTable.getColumnModel().getColumn(1).setMinWidth(25);
		veranstaltungsTable.getColumnModel().getColumn(1).setMaxWidth(25);
		veranstaltungsTable.getColumnModel().getColumn(2).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(2).setMinWidth(75);
		veranstaltungsTable.getColumnModel().getColumn(2).setMaxWidth(75);
		veranstaltungsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		veranstaltungsTable.getColumnModel().getColumn(3).setMinWidth(80);
		veranstaltungsTable.getColumnModel().getColumn(3).setMaxWidth(200);
		veranstaltungsTable.getColumnModel().getColumn(4).setPreferredWidth(70);
		veranstaltungsTable.getColumnModel().getColumn(4).setMinWidth(70);
		veranstaltungsTable.getColumnModel().getColumn(4).setMaxWidth(200);
		veranstaltungsTable.getColumnModel().getColumn(5).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(5).setPreferredWidth(30);
		veranstaltungsTable.getColumnModel().getColumn(5).setMinWidth(30);
		veranstaltungsTable.getColumnModel().getColumn(5).setMaxWidth(30);
		veranstaltungsTable.getColumnModel().getColumn(6).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		veranstaltungsTable.getColumnModel().getColumn(6).setMinWidth(60);
		veranstaltungsTable.getColumnModel().getColumn(6).setMaxWidth(60);
		veranstaltungsTable.getColumnModel().getColumn(7).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(7).setPreferredWidth(70);
		veranstaltungsTable.getColumnModel().getColumn(7).setMinWidth(70);
		veranstaltungsTable.getColumnModel().getColumn(7).setMaxWidth(70);
		veranstaltungsTable.getColumnModel().getColumn(8).setResizable(false);
		veranstaltungsTable.getColumnModel().getColumn(8).setPreferredWidth(32);
		veranstaltungsTable.getColumnModel().getColumn(8).setMinWidth(32);
		veranstaltungsTable.getColumnModel().getColumn(8).setMaxWidth(32);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		veranstaltungsTable.getColumnModel().getColumn(8).setCellRenderer(center);
		veranstaltungsTable.getColumnModel().getColumn(1).setCellRenderer(center);
		veranstaltungsTable.getColumnModel().getColumn(6).setCellRenderer(center);
	}
}
