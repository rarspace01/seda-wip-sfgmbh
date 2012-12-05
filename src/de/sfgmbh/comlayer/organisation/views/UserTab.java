package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.organisation.controller.BtnsNav;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.UserTabBtnsControl;
import de.sfgmbh.init.Bootstrap;

public class UserTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldPlatze;
	private JTextField textFieldPCPlatze;
	private JTable raumverwaltungTable;
	private JLabel lblBenutzerklasse;
	private JLabel lblLehrstuhl;
	private JLabel lblBenutzerkennung;
	private JLabel lblEmail;
	private JComboBox<String> comboBoxBenutzerklasse;
	private JComboBox<String> comboBoxStockwerk;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JScrollPane verwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnHinzufugen;
	private JButton btnAnfragen;
	private JButton btnLschen;
	private JButton btnLivetickerBearbeiten;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserTab() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		JLabel lblNutzerverwaltung = new JLabel("Nutzerverwaltung");
		lblNutzerverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblNutzerverwaltung, "cell 0 0,alignx center");
		
		lblBenutzerklasse = new JLabel("Benutzerklasse:");
		add(lblBenutzerklasse, "cell 2 0");
		
		lblLehrstuhl = new JLabel("Lehrstuhl (falls Dozent):");
		add(lblLehrstuhl, "cell 3 0");
		
		lblBenutzerkennung = new JLabel("Benutzerkennung:");
		add(lblBenutzerkennung, "cell 4 0");
		
		lblEmail = new JLabel("E-Mail:");
		add(lblEmail, "cell 5 0");
		
		comboBoxBenutzerklasse = new JComboBox<String>();
		comboBoxBenutzerklasse.setModel(new DefaultComboBoxModel(new String[] {"<alle>", "Dozenten", "Verwaltung", "Studenten"}));
		comboBoxBenutzerklasse.addActionListener(new CmbboxFilter());
		comboBoxBenutzerklasse.setEditable(true);
		comboBoxBenutzerklasse.setAutoscrolls(true);
		add(comboBoxBenutzerklasse, "cell 2 1,growx");
		
		comboBoxStockwerk = new JComboBox<String>();
		comboBoxStockwerk.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxStockwerk.addActionListener(new CmbboxFilter());
		comboBoxStockwerk.setEditable(true);
		comboBoxStockwerk.setAutoscrolls(true);
		add(comboBoxStockwerk, "cell 3 1,growx");
		
		textFieldPlatze = new JTextField();
		textFieldPlatze.setText("<alle>");
		textFieldPlatze.addActionListener(new CmbboxFilter());
		add(textFieldPlatze, "cell 4 1,growx");
		textFieldPlatze.setColumns(10);
		
		textFieldPCPlatze = new JTextField();
		textFieldPCPlatze.setText("<alle>");
		textFieldPCPlatze.addActionListener(new CmbboxFilter());
		textFieldPCPlatze.setColumns(10);
		add(textFieldPCPlatze, "cell 5 1,growx");
		
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setMaximumSize(new Dimension(140, 32767));
		leftPanel.setBorder(null);
		add(leftPanel, "cell 0 2,grow");
		
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(null);
		leftTopPanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		leftTopPanel.setBounds(0, 6, 140, 313);
		leftPanel.add(leftTopPanel);
		
		JLabel lblInfo = new JLabel("<html>LiveTickerNews:<br><br><b>Fehlermeldung 1: </b>Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. <br> <b>Fehlermeldung 2: </b>Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. <b>Fehlermeldung 3:</b> Es besteht keine Verbindung zur Datenbank.</html>");
		lblInfo.setBounds(6, 6, 134, 296);
		leftTopPanel.add(lblInfo);
		
		leftBottomPanel = new JPanel();
		leftBottomPanel.setBounds(0, 319, 140, 161);
		leftPanel.add(leftBottomPanel);
		leftBottomPanel.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		JLabel lblAndereBereiche = new JLabel("Verwaltungs-Bereiche:");
		leftBottomPanel.add(lblAndereBereiche, "cell 0 0");
		
		JButton btnRaumverwaltung = new JButton("R\u00E4ume");
		btnRaumverwaltung.addActionListener(new BtnsNav("Raumverw"));
		
		btnAnfragen = new JButton("Anfragen");
		btnAnfragen.addActionListener(new BtnsNav("Anfrageverw"));
		leftBottomPanel.add(btnAnfragen, "cell 0 1");
		leftBottomPanel.add(btnRaumverwaltung, "cell 0 2");
		
		btnLivetickerBearbeiten = new JButton("Live-Ticker");
		btnLivetickerBearbeiten.addActionListener(new BtnsNav("liveticker"));
		leftBottomPanel.add(btnLivetickerBearbeiten, "cell 0 3");
		
		JButton btnLehrstuhlverwaltung = new JButton("Lehrst\u00FChle");
		btnLehrstuhlverwaltung.addActionListener(new BtnsNav("Lehrstuhlverw"));
		leftBottomPanel.add(btnLehrstuhlverwaltung, "cell 0 4");
		
		verwaltungTableScrollPane = new JScrollPane();
		add(verwaltungTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setModel(Bootstrap.serviceManager.getOrgaUserTableModel());
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setViewportView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnHinzufugen = new JButton("hinzuf\u00FCgen");
		btnHinzufugen.addActionListener(new UserTabBtnsControl("hinz"));
		btnHinzufugen.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnHinzufugen);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new UserTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnLschen = new JButton("l\u00F6schen");
		btnLschen.addActionListener(new UserTabBtnsControl("loschen"));
		btnLschen.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnLschen);
		
	}
}