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
import de.sfgmbh.comlayer.core.controller.BaseBtnLogin;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.BaseRdbtnTopLeft;
import de.sfgmbh.comlayer.core.model.BaseCmbboxModelFilter;
import de.sfgmbh.init.Bootstrap;

import net.miginfocom.swing.MigLayout;

public class BaseTab extends JFrame{

	private static final long serialVersionUID = 1L;
	
	// GUI components
	private JLayeredPane contentPane;
	public JTabbedPane mainTabbedContainerPane;
	public JPanel startScreenPanel;
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
	private JLabel lblPasswort;
	private JLabel lblBenutzername;
	private JButton btnNewButton;
	private JTextField txtBenutzername;
	private JPasswordField pwdPasswort;
	private JRadioButton rdbtnLehrveranstaltungen;
	private JRadioButton rdbtnRaume;
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public BaseTab() {
		createContents();
	}
	private void createContents() {
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
		startScreenPanel.add(getRdbtnLehrveranstaltungen(), "cell 0 0");
		
		lblLehrveranstaltung = new JLabel("Lehrveranstaltung:");
		startScreenPanel.add(lblLehrveranstaltung, "cell 2 0,alignx left,aligny center");
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		startScreenPanel.add(lblLehrstuhl, "cell 3 0");
		
		lblDozent = new JLabel("Dozent:");
		startScreenPanel.add(lblDozent, "cell 4 0");
		
		lblSemester = new JLabel("Semester:");
		startScreenPanel.add(lblSemester, "cell 5 0");
		
		comboBoxVeranstaltungFilter = new JComboBox<String>();
		comboBoxVeranstaltungFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxVeranstaltungFilter.addActionListener(new BaseCmbboxFilter());
		startScreenPanel.add(getRdbtnRaume(), "cell 0 1");
		comboBoxVeranstaltungFilter.setAutoscrolls(true);
		comboBoxVeranstaltungFilter.setEditable(true);
		comboBoxVeranstaltungFilter.setModel(new BaseCmbboxModelFilter("Veranst"));
		startScreenPanel.add(comboBoxVeranstaltungFilter, "cell 2 1,growx");
		
		comboBoxLehrstuhlFilter = new JComboBox<String>();
		comboBoxLehrstuhlFilter.setModel(new BaseCmbboxModelFilter("Lehrstuhl"));
		comboBoxLehrstuhlFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxLehrstuhlFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxLehrstuhlFilter.setEditable(true);
		comboBoxLehrstuhlFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxLehrstuhlFilter, "cell 3 1,growx");
		
		comboBoxDozentFilter = new JComboBox<String>();
		comboBoxDozentFilter.setModel(new BaseCmbboxModelFilter("Doz"));
		comboBoxDozentFilter.addKeyListener(new BaseCmbboxFilter());
		comboBoxDozentFilter.addActionListener(new BaseCmbboxFilter());
		comboBoxDozentFilter.setEditable(true);
		comboBoxDozentFilter.setAutoscrolls(true);
		startScreenPanel.add(comboBoxDozentFilter, "cell 4 1,growx");
		
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
		panel.setBounds(0, 6, 140, 313);
		tickerJPanel.add(panel);
		panel.setLayout(null);
		
		JTextPane tickerMsgPos2 = new JTextPane();
		tickerMsgPos2.setBounds(6, 108, 123, 176);
		panel.add(tickerMsgPos2);
		tickerMsgPos2.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos2.setContentType("text/html");
		tickerMsgPos2.setText("<div style=\"font-family:arial\"><strong>Info:</strong><br>Logindaten: <br>Dozenten: Doz // Doz <br>Studenten: Stud // Stud<br> Verwaltung: Verw // Verw <br> Anderenfalls Fehler!</div>");
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBounds(6, 6, 123, 90);
		panel.add(tickerMsgPos1);
		tickerMsgPos1.setBackground(UIManager.getColor("Button.light"));
		tickerMsgPos1.setContentType("text/html");
		tickerMsgPos1.setText("<div style=\"font-family:arial\"><strong>News:</strong><br> <span style=\"color:red\">Neu!</span> Testlogin jetzt m\u00F6glich!</div>");
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 319, 140, 161);
		tickerJPanel.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		panel_1.add(getLblBenutzername(), "cell 0 0");
		panel_1.add(getTxtBenutzername(), "cell 0 1,growx");
		
		lblPasswort = new JLabel("Passwort:");
		panel_1.add(lblPasswort, "cell 0 2");
		panel_1.add(getPwdPasswort(), "cell 0 3,growx");
		panel_1.add(getBtnNewButton(), "cell 0 4,alignx right");
		
		mainTableScrollPane = new JScrollPane();
		startScreenPanel.add(mainTableScrollPane, "cell 2 2 4 1,grow");
		
		veranstaltungsTable = new JTable();
		mainTableScrollPane.setViewportView(veranstaltungsTable);
		veranstaltungsTable.setBackground(SystemColor.activeCaption);
		veranstaltungsTable.setShowVerticalLines(false);
		veranstaltungsTable.setModel(Bootstrap.serviceManager.getCoreBaseTableModel());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		startScreenPanel.add(buttonPanel, "cell 6 2,grow");
		buttonPanel.setLayout(null);
		
		JButton btnAddToStundenplan = new JButton("+");
		btnAddToStundenplan.addActionListener(new BaseBtnAddToStundenplan());
		btnAddToStundenplan.setBounds(0, 29, 41, 23);
		buttonPanel.add(btnAddToStundenplan);
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
			rdbtnLehrveranstaltungen.addActionListener(new BaseRdbtnTopLeft());
			buttonGroup.add(rdbtnLehrveranstaltungen);
			rdbtnLehrveranstaltungen.setSelected(true);
			rdbtnLehrveranstaltungen.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnLehrveranstaltungen;
	}
	public JRadioButton getRdbtnRaume() {
		if (rdbtnRaume == null) {
			rdbtnRaume = new JRadioButton("R\u00E4ume");
			rdbtnRaume.addActionListener(new BaseRdbtnTopLeft());
			buttonGroup.add(rdbtnRaume);
			rdbtnRaume.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRaume;
	}
}
