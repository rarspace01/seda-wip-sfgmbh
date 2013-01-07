package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.BtnsNav;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RoomTabBtnsControl;
import de.sfgmbh.comlayer.organisation.controller.UserTabBtnsControl;
import de.sfgmbh.init.Bootstrap;

public class UserTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable raumverwaltungTable;
	private JTextField textFieldSeats;
	private JTextField textFieldPCSeats;
	private JLabel lblUserclass;
	private JLabel lblLecturer;
	private JLabel lblAuthentification;
	private JLabel lblEmail;
	private JComboBox<String> comboBoxUserclass;
	private JComboBox<String> comboBoxLevel;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnEdit;
	private JButton btnRequest;
	private JButton btnDelete;
	private JButton btnLivetickerEdit;
	private JTextPane tickerMsgPos1;
	private JButton btnFailureprompt;
	private JPanel panel;
	private JLabel uniIconJLbl;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public UserTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[grow][][grow][]"));
		
		JLabel lblNutzerverwaltung = new JLabel("Nutzerverwaltung");
		lblNutzerverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblNutzerverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblUserclass = new JLabel("Benutzerklasse:");
		add(lblUserclass, "cell 2 0,aligny bottom");
		
		lblLecturer = new JLabel("Lehrstuhl (falls Dozent):");
		add(lblLecturer, "cell 3 0,aligny bottom");
		
		lblAuthentification = new JLabel("Benutzerkennung:");
		add(lblAuthentification, "cell 4 0,aligny bottom");
		
		lblEmail = new JLabel("E-Mail:");
		add(lblEmail, "cell 5 0,aligny bottom");
		
		comboBoxUserclass = new JComboBox<String>();
		comboBoxUserclass.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>", "Dozenten", "Verwaltung", "Studenten"}));
		comboBoxUserclass.addActionListener(new CmbboxFilter());
		
		panel = new JPanel();
		add(panel, "cell 6 0,alignx center,growy");
		uniIconJLbl = new JLabel("");
		uniIconJLbl.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		uniIconJLbl.setMaximumSize(new Dimension(50,50));
		panel.add(uniIconJLbl);
		comboBoxUserclass.setEditable(true);
		comboBoxUserclass.setAutoscrolls(true);
		add(comboBoxUserclass, "cell 2 1,growx");
		
		comboBoxLevel = new JComboBox<String>();
		comboBoxLevel.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxLevel.addActionListener(new CmbboxFilter());
		comboBoxLevel.setEditable(true);
		comboBoxLevel.setAutoscrolls(true);
		add(comboBoxLevel, "cell 3 1,growx");
		
		textFieldSeats = new JTextField();
		textFieldSeats.setText("<alle>");
		textFieldSeats.addActionListener(new CmbboxFilter());
		add(textFieldSeats, "cell 4 1,growx");
		textFieldSeats.setColumns(10);
		
		textFieldPCSeats = new JTextField();
		textFieldPCSeats.setText("<alle>");
		textFieldPCSeats.addActionListener(new CmbboxFilter());
		textFieldPCSeats.setColumns(10);
		add(textFieldPCSeats, "cell 5 1,growx");
		
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setMaximumSize(new Dimension(140, 32767));
		leftPanel.setBorder(null);
		add(leftPanel, "cell 0 2,grow");
		
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(null);
		leftTopPanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		leftTopPanel.setBounds(0, 6, 140, 426);
		leftPanel.add(leftTopPanel);
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen.\r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.\r\n");
		tickerMsgPos1.setBounds(10, 11, 120, 415);
		leftTopPanel.add(tickerMsgPos1);
		/*
		JLabel lblAndereBereiche = new JLabel("Verwaltungs-Bereiche:");
		leftBottomPanel.add(lblAndereBereiche, "cell 0 0");
		
		JButton btnRaumverwaltung = new JButton("R\u00E4ume");
		btnRaumverwaltung.addActionListener(new BtnsNav("Raumverw"));
		
		btnRequest = new JButton("Anfragen");
		btnRequest.addActionListener(new BtnsNav("Anfrageverw"));
		leftBottomPanel.add(btnRequest, "cell 0 1");
		leftBottomPanel.add(btnRaumverwaltung, "cell 0 2");
		
		btnLivetickerEdit = new JButton("Live-Ticker");
		btnLivetickerEdit.addActionListener(new BtnsNav("liveticker"));
		leftBottomPanel.add(btnLivetickerEdit, "cell 0 3");
		
		JButton btnLehrstuhlverwaltung = new JButton("Lehrst\u00FChle");
		btnLehrstuhlverwaltung.addActionListener(new BtnsNav("Lehrstuhlverw"));
		leftBottomPanel.add(btnLehrstuhlverwaltung, "cell 0 4");
		*/
		organisationTableScrollPane = new JScrollPane();
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setModel(Bootstrap.serviceManager.getOrgaUserTableModel());
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane.setViewportView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnEdit = new JButton("hinzuf\u00FCgen");
		btnEdit.addActionListener(new UserTabBtnsControl("hinz"));
		btnEdit.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnEdit);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new UserTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new UserTabBtnsControl("loschen"));
		btnDelete.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnDelete);
		
		btnFailureprompt = new JButton("Ausloggen");
		btnFailureprompt.addActionListener(new RoomTabBtnsControl("Fehlermeldung"));
		add(btnFailureprompt, "cell 2 3");
	}
	
}