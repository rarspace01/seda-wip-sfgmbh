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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlRoom;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RoomTabBtnsControl;

public class RoomTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldSeats;
	private JTextField textFieldPCSeats;
	private JTable raumverwaltungTable;
	private JLabel lblBuilding;
	private JLabel lblLevel;
	private JLabel lblSeats;
	private JLabel lblPcseats;
	private JComboBox<String> comboBoxBuilding;
	private JComboBox<String> comboBoxLevel;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnRaumplanDrucken;
	private JTextPane tickerMsgPos1;
	private JButton btnFailureprompt;
	private JPanel uniIconPanel;
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public RoomTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][][grow][grow][grow][100px:100px:100px]", "[][][grow][]"));
		
		JLabel lblRaumverwaltung = new JLabel("Raumverwaltung");
		lblRaumverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRaumverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblBuilding = new JLabel("Stockwerke:");
		add(lblBuilding, "cell 2 0,aligny bottom");
		
		lblLevel = new JLabel("R\u00E4ume:");
		add(lblLevel, "cell 3 0,aligny bottom");
		
		lblSeats = new JLabel("Pl\u00E4tze:");
		add(lblSeats, "cell 4 0,aligny bottom");
		
		lblPcseats = new JLabel("PC-Pl\u00E4tze:");
		add(lblPcseats, "cell 5 0,aligny bottom");
		
		comboBoxBuilding = new JComboBox<String>();
		comboBoxBuilding.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxBuilding.addActionListener(new CmbboxFilter());
		
		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx center,growy");
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		comboBoxBuilding.setEditable(true);
		comboBoxBuilding.setAutoscrolls(true);
		add(comboBoxBuilding, "cell 2 1,growx");
		
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
		leftTopPanel.setBounds(0, 0, 130, 415);
		leftPanel.add(leftTopPanel);
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setText("LiveTickerNews:\r\n\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen.\r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.");
		tickerMsgPos1.setBounds(10, 11, 110, 387);
		leftTopPanel.add(tickerMsgPos1);
		
		organisationTableScrollPane = new JScrollPane();
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		raumverwaltungTable.setModel(ServiceManager.getInstance().getOrgaRoomTableModel());
		
		//register tablemodel on repository
		AppModel.getInstance().repositoryRoom.register(ServiceManager.getInstance().getOrgaRoomTableModel());
		
		raumverwaltungTable.getColumnModel().removeColumn(raumverwaltungTable.getColumn("roomid"));
		
		this.loadRooms();
		
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane.setViewportView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnEdit = new JButton("hinzuf\u00FCgen");
		btnEdit.addActionListener(new RoomTabBtnsControl("hinz"));
		btnEdit.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnEdit);
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.addActionListener(new RoomTabBtnsControl("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		btnDelete = new JButton("l\u00F6schen");
		btnDelete.addActionListener(new RoomTabBtnsControl("loschen"));
		btnDelete.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnDelete);
		
		btnRaumplanDrucken = new JButton("Raumplan");
		btnRaumplanDrucken.addActionListener(new RoomTabBtnsControl("Raumplan"));
		btnRaumplanDrucken.setBounds(6, 124, 94, 23);
		buttonPanel.add(btnRaumplanDrucken);
		
		btnFailureprompt = new JButton("Ausloggen");
		btnFailureprompt.addActionListener(new RoomTabBtnsControl("Fehlermeldung"));
		add(btnFailureprompt, "cell 2 3");
	}
	
	public void loadRooms(){
		
		CtrlRoom ctrlRoom= new CtrlRoom();
				
		ServiceManager.getInstance().getOrgaRoomTableModel().addRooms(ctrlRoom.getAllRooms());
	}
	
	public JTable getRaumverwaltungTable() {
		return raumverwaltungTable;
	}
	
}