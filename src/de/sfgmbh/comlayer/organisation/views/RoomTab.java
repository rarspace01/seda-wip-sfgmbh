package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.organisation.controller.CtrlRoom;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoom;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLevel;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RoomTabActionListener;
import de.sfgmbh.comlayer.organisation.controller.RoomTabCmbboxFilter;

/**
 * Tab for the room organization
 * 
 * @author anna - main parts of the gui
 * @author denis - methods
 *
 */
public class RoomTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldSeats;
	private JTextField textFieldPCSeats;
	private JTable roommanagementTable;
	private JLabel lblBuilding;
	private JLabel lblSeats;
	private JLabel lblPcseats;
	private JComboBox<String> comboBoxLevel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnRoomplanPrint;
	private JPanel uniIconPanel;
	private JLabel lblRoom;
	private JTextField txtRoom;
	private TableRowSorter<TableModel> rowSorter;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({})
	public RoomTab() {

		initialize();
	}

	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow 118][grow][grow][100px:100px:100px]", "[][][grow]"));

		JLabel lblRoommanagement = new JLabel("Raumverwaltung");
		lblRoommanagement.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRoommanagement, "cell 0 0,alignx center,aligny bottom");

		lblBuilding = new JLabel("Stockwerke:");
		add(lblBuilding, "cell 2 0,aligny bottom");
		add(getLblRoom(), "cell 3 0,aligny bottom");

		lblSeats = new JLabel("Mehr Plätze als:");
		add(lblSeats, "cell 4 0,aligny bottom");

		lblPcseats = new JLabel("Mehr PC-Plätze als:");
		add(lblPcseats, "cell 5 0,aligny bottom");

		comboBoxLevel = new JComboBox<String>();
		comboBoxLevel.setModel(new CmbboxFilterLevel(comboBoxLevel));
		comboBoxLevel.addActionListener(new RoomTabCmbboxFilter());

		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx right,aligny top");
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class
				.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50, 50));
		uniIconPanel.add(lblUniIcon);

		comboBoxLevel.setEditable(true);
		comboBoxLevel.setAutoscrolls(true);
		add(comboBoxLevel, "cell 2 1,growx");

		textFieldSeats = new JTextField();
		textFieldSeats.setText("0");
		textFieldSeats.addActionListener(new RoomTabCmbboxFilter());
		add(getTxtRoom(), "cell 3 1,growx");
		add(textFieldSeats, "cell 4 1,growx");
		textFieldSeats.setColumns(10);

		textFieldPCSeats = new JTextField();
		textFieldPCSeats.setText("0");
		textFieldPCSeats.addActionListener(new RoomTabCmbboxFilter());
		textFieldPCSeats.setColumns(10);
		add(textFieldPCSeats, "cell 5 1,growx");

		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow, aligny top");

		organisationTableScrollPane = new JScrollPane();
		organisationTableScrollPane.setToolTipText("");
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");

		roommanagementTable = new JTable();
		roommanagementTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		roommanagementTable.setModel(ViewManager.getInstance()
				.getOrgaRoomTableModel());

		// register tablemodel on repository
		AppModel.getInstance().getRepositoryRoom()
				.register(ViewManager.getInstance().getOrgaRoomTableModel());

		// hide column where the room id is stored
		roommanagementTable.getColumnModel().removeColumn(
				roommanagementTable.getColumn("roomid"));

		

		roommanagementTable.setShowVerticalLines(false);
		roommanagementTable.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane.setViewportView(roommanagementTable);
		
		// Enable table sorting for the model
		rowSorter = new TableRowSorter<TableModel>();
		roommanagementTable.setRowSorter(rowSorter);
		rowSorter.setModel(ViewManager.getInstance()
				.getOrgaRoomTableModel());
		rowSorter.sort();

		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");

		btnEdit = new JButton("hinzuf\u00FCgen");
		btnEdit.setToolTipText("<html>Klicken Sie hier, <br>um einen neuen Raum hinzuzufügen</html>");
		btnEdit.addActionListener(new RoomTabActionListener("add"));
		btnEdit.setBounds(6, 11, 88, 23);
		buttonPanel.add(btnEdit);

		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.setToolTipText("<html>Selektieren Sie einen Raum <br> und klicken Sie auf bearbeiten um Eigenschaften zu ändern</html>");
		btnBearbeiten.addActionListener(new RoomTabActionListener("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel.add(btnBearbeiten);

		btnDelete = new JButton("l\u00F6schen");
		btnDelete.setToolTipText("<html>Selektieren Sie einen Raum um ihn zu löschen</html>");
		btnDelete.addActionListener(new RoomTabActionListener("del"));
		btnDelete.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnDelete);

		btnRoomplanPrint = new JButton("Raumplan");
		btnRoomplanPrint.setToolTipText("<html>Selektieren Sie einen Raum<br> und klicken Sie hier um einen Wochenplan <br>zur Belegung des Raumes zu erhalten</html>");
		btnRoomplanPrint
				.addActionListener(new RoomTabActionListener("plan"));
		btnRoomplanPrint.setBounds(6, 145, 88, 23);
		buttonPanel.add(btnRoomplanPrint);
		
		this.loadRooms();
	}
	/**
	 * loads all the {@link Room}s through the interface to the tablemodel
	 */
	public void loadRooms() {

		IntfCtrlRoom ctrlRoom = new CtrlRoom();

		//this.getRoommanagementTable().getModel().
		
		ViewManager.getInstance().getOrgaRoomTableModel()
				.addRooms(ctrlRoom.getAllRooms());
	}
	/**
	 * 
	 * @return the raumverwaltungTable
	 */
	public JTable getRoommanagementTable() {
		return roommanagementTable;
	}

	/**
	 * @return the textFieldSeats
	 */
	public JTextField getTextFieldSeats() {
		return textFieldSeats;
	}

	/**
	 * @return the textFieldPCSeats
	 */
	public JTextField getTextFieldPCSeats() {
		return textFieldPCSeats;
	}

	/**
	 * @return the comboBoxLevel
	 */
	public JComboBox<String> getComboBoxLevel() {
		return comboBoxLevel;
	}
	/**
	 * 
	 * @return the lblRoom
	 */
	public JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Raum:");
		}
		return lblRoom;
	}
	/**
	 * 
	 * @return the txtRoom
	 */
	public JTextField getTxtRoom() {
		if (txtRoom == null) {
			txtRoom = new JTextField();
			txtRoom.setColumns(10);
			txtRoom.addActionListener(new RoomTabCmbboxFilter());
		}
		return txtRoom;
	}

	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter;
	}
}