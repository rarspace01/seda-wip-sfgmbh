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
import de.sfgmbh.comlayer.organisation.controller.RoomTabBtnsControl;
import de.sfgmbh.init.Bootstrap;

public class RoomTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldSeats;
	private JTextField textFieldPcSeats;
	private JTable raumverwaltungTable;
	private JLabel lblGebude;
	private JLabel lblStockwerk;
	private JLabel lblPltze;
	private JLabel lblPcpltze;
	private JComboBox<String> comboBoxRoom;
	private JComboBox<String> comboBoxBuilding;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JPanel leftBottomPanel;
	private JScrollPane verwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnHinzufugen;
	private JButton btnAnfragen;
	private JButton btnLschen;
	private JButton btnRaumplanDrucken;
	private JButton btnLivetickerBearbeiten;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RoomTab() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px,grow][10px:10px:10px][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		JLabel lblRaumverwaltung = new JLabel("Raumverwaltung");
		lblRaumverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRaumverwaltung, "cell 0 0,alignx center");
		
		lblGebude = new JLabel("Raum:");
		add(lblGebude, "cell 2 0");
		
		lblStockwerk = new JLabel("Geb\u00E4ude:");
		add(lblStockwerk, "cell 3 0");
		
		lblPltze = new JLabel("Pl\u00E4tze:");
		add(lblPltze, "cell 4 0");
		
		lblPcpltze = new JLabel("PC-Pl\u00E4tze:");
		add(lblPcpltze, "cell 5 0");
		
		comboBoxRoom = new JComboBox<String>();
		comboBoxRoom.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxRoom.addActionListener(new CmbboxFilter());
		comboBoxRoom.setEditable(true);
		comboBoxRoom.setAutoscrolls(true);
		add(comboBoxRoom, "cell 2 1,growx");
		
		comboBoxBuilding = new JComboBox<String>();
		comboBoxBuilding.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxBuilding.addActionListener(new CmbboxFilter());
		comboBoxBuilding.setEditable(true);
		comboBoxBuilding.setAutoscrolls(true);
		add(comboBoxBuilding, "cell 3 1,growx");
		
		textFieldSeats = new JTextField();
		textFieldSeats.setText("<alle>");
		textFieldSeats.addActionListener(new CmbboxFilter());
		add(textFieldSeats, "cell 4 1,growx");
		textFieldSeats.setColumns(10);
		
		textFieldPcSeats = new JTextField();
		textFieldPcSeats.setText("<alle>");
		textFieldPcSeats.addActionListener(new CmbboxFilter());
		textFieldPcSeats.setColumns(10);
		add(textFieldPcSeats, "cell 5 1,growx");
		
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
		
		JLabel lblInfo = new JLabel("<html>LiveTickerNews:<br><br><b>Fehlermeldung 1: </b>Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. <br> <b>Fehlermeldung 2: </b>Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. <b>Fehlermeldung 3:</b> Es besteht keine Verbindung zur Datenbank.</html>\r\n");
		lblInfo.setBounds(6, 6, 124, 296);
		leftTopPanel.add(lblInfo);
		
		leftBottomPanel = new JPanel();
		leftBottomPanel.setBounds(0, 319, 149, 161);
		leftPanel.add(leftBottomPanel);
		leftBottomPanel.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		JLabel lblAndereBereiche = new JLabel("Verwaltungs-Bereiche:");
		leftBottomPanel.add(lblAndereBereiche, "cell 0 0");
		
		JButton btnNutzerverwaltung = new JButton("Nutzer");
		btnNutzerverwaltung.addActionListener(new BtnsNav("Nutzerverw"));
		leftBottomPanel.add(btnNutzerverwaltung, "cell 0 1");
		
		btnAnfragen = new JButton("Anfragen");
		btnAnfragen.addActionListener(new BtnsNav("Anfrageverw"));
		leftBottomPanel.add(btnAnfragen, "cell 0 2");
		
		btnLivetickerBearbeiten = new JButton("LiveTicker");
		btnLivetickerBearbeiten.addActionListener(new BtnsNav("liveticker"));
		leftBottomPanel.add(btnLivetickerBearbeiten, "cell 0 3");
		
		JButton btnLehrstuhlverwaltung = new JButton("Lehrst\u00FChle");
		btnLehrstuhlverwaltung.addActionListener(new BtnsNav("Lehrstuhlverw"));
		leftBottomPanel.add(btnLehrstuhlverwaltung, "cell 0 4");
		
		verwaltungTableScrollPane = new JScrollPane();
		add(verwaltungTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setModel(Bootstrap.serviceManager.getOrgaRoomTableModel());
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setViewportView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnHinzufugen = new JButton("hinzuf\u00FCgen");
		btnHinzufugen.addActionListener(new RoomTabBtnsControl("hinz"));
		btnHinzufugen.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnHinzufugen);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new RoomTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnLschen = new JButton("l\u00F6schen");
		btnLschen.addActionListener(new RoomTabBtnsControl("loschen"));
		btnLschen.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnLschen);
		
		btnRaumplanDrucken = new JButton("Raumplan");
		btnRaumplanDrucken.addActionListener(new RoomTabBtnsControl("Raumplan"));
		btnRaumplanDrucken.setBounds(6, 124, 94, 23);
		buttonPanel.add(btnRaumplanDrucken);
		
	}
}