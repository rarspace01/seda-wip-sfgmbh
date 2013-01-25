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
 * @author anna
 * @author denis
 * 
 */
public class RoomTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldSeats_;
	private JTextField textFieldPCSeats_;
	private JTable roommanagementTable_;
	private JLabel lblBuilding_;
	private JLabel lblSeats_;
	private JLabel lblPcseats_;
	private JComboBox<String> comboBoxLevel_;
	private JScrollPane organisationTableScrollPane_;
	private JPanel buttonPanel_;
	private JButton btnEdit_;
	private JButton btnDelete_;
	private JButton btnRoomplanPrint_;
	private JLabel lblRoom_;
	private JTextField txtRoom_;
	private TableRowSorter<TableModel> rowSorter_;

	/**
	 * Create the frame.
	 */
	public RoomTab() {

		initialize();
	}

	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout(
				"",
				"[140px:140px:140px][10px:10px:10px][grow][grow 118][grow][grow][100px:100px:100px,right]",
				"[][][grow]"));

		JLabel lblRoommanagement = new JLabel("Raumverwaltung");
		lblRoommanagement.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRoommanagement, "cell 0 0,alignx center,aligny bottom");

		lblBuilding_ = new JLabel("Stockwerke:");
		add(lblBuilding_, "cell 2 0,aligny bottom");
		add(getLblRoom(), "cell 3 0,aligny bottom");

		lblSeats_ = new JLabel("Mehr Plätze als:");
		add(lblSeats_, "cell 4 0,aligny bottom");

		lblPcseats_ = new JLabel("Mehr PC-Plätze als:");
		add(lblPcseats_, "cell 5 0,aligny bottom");

		comboBoxLevel_ = new JComboBox<String>();
		comboBoxLevel_
				.setToolTipText("<html>Sie können die Liste der <br> Räume über diesen<br>Filter begrenzen</html>");
		comboBoxLevel_.setModel(new CmbboxFilterLevel(comboBoxLevel_));
		comboBoxLevel_.addActionListener(new RoomTabCmbboxFilter());

		JLabel lblUniIcon_ = new JLabel();
		lblUniIcon_
				.setIcon(new ImageIcon(
						BaseTab.class
								.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon_.setMaximumSize(new Dimension(50, 50));
		add(lblUniIcon_, "cell 6 0,alignx right,aligny top");

		comboBoxLevel_.setEditable(true);
		comboBoxLevel_.setAutoscrolls(true);
		add(comboBoxLevel_, "cell 2 1,growx");

		textFieldSeats_ = new JTextField();
		textFieldSeats_
				.setToolTipText("<html>Sie können die Liste der <br> Räume über diesen<br>Filter begrenzen</html>");
		textFieldSeats_.setText("0");
		textFieldSeats_.addActionListener(new RoomTabCmbboxFilter());
		add(getTxtRoom(), "cell 3 1,growx");
		add(textFieldSeats_, "cell 4 1,growx");
		textFieldSeats_.setColumns(10);

		textFieldPCSeats_ = new JTextField();
		textFieldPCSeats_
				.setToolTipText("<html>Sie können die Liste der <br> Räume über diesen<br>Filter begrenzen</html>");
		textFieldPCSeats_.setText("0");
		textFieldPCSeats_.addActionListener(new RoomTabCmbboxFilter());
		textFieldPCSeats_.setColumns(10);
		add(textFieldPCSeats_, "cell 5 1,growx");

		add(ViewManager.getInstance().getCoreLiveTickerPanel(),
				"cell 0 2,grow, aligny top");

		organisationTableScrollPane_ = new JScrollPane();
		organisationTableScrollPane_.setToolTipText("");
		add(organisationTableScrollPane_, "flowx,cell 2 2 4 1,grow");

		roommanagementTable_ = new JTable();
		roommanagementTable_
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		roommanagementTable_.setModel(ViewManager.getInstance()
				.getOrgaRoomTableModel());

		// register tablemodel on repository
		AppModel.getInstance().getRepositoryRoom()
				.register(ViewManager.getInstance().getOrgaRoomTableModel());

		// hide column where the room id is stored
		roommanagementTable_.getColumnModel().removeColumn(
				roommanagementTable_.getColumn("roomid"));

		roommanagementTable_.setShowVerticalLines(false);
		roommanagementTable_.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane_.setViewportView(roommanagementTable_);

		// Enable table sorting for the model
		rowSorter_ = new TableRowSorter<TableModel>();
		roommanagementTable_.setRowSorter(rowSorter_);
		rowSorter_.setModel(ViewManager.getInstance().getOrgaRoomTableModel());
		rowSorter_.sort();

		buttonPanel_ = new JPanel();
		buttonPanel_.setLayout(null);
		buttonPanel_.setMinimumSize(new Dimension(80, 10));
		buttonPanel_.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel_, "cell 6 2,grow");

		btnEdit_ = new JButton("hinzuf\u00FCgen");
		btnEdit_.setToolTipText("<html>Klicken Sie hier <br>um einen neuen Raum anzulegen</html>");
		btnEdit_.addActionListener(new RoomTabActionListener("add"));
		btnEdit_.setBounds(6, 11, 88, 23);
		buttonPanel_.add(btnEdit_);

		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten
				.setToolTipText("<html>Selektieren Sie einen Raum <br> und klicken Sie hier um dessen Eigenschaften zu ändern</html>");
		btnBearbeiten.addActionListener(new RoomTabActionListener("edit"));
		btnBearbeiten.setBounds(6, 45, 88, 23);
		buttonPanel_.add(btnBearbeiten);

		btnDelete_ = new JButton("l\u00F6schen");
		btnDelete_
				.setToolTipText("<html>Selektieren Sie einen Raum<br> um ihn zu löschen</html>");
		btnDelete_.addActionListener(new RoomTabActionListener("del"));
		btnDelete_.setBounds(6, 79, 88, 23);
		buttonPanel_.add(btnDelete_);

		btnRoomplanPrint_ = new JButton("Raumplan");
		btnRoomplanPrint_
				.setToolTipText("<html>Selektieren Sie einen Raum<br> und klicken Sie hier um einen Wochenplan <br>zur Belegung des Raumes zu erhalten</html>");
		btnRoomplanPrint_.addActionListener(new RoomTabActionListener("plan"));
		btnRoomplanPrint_.setBounds(6, 145, 88, 23);
		buttonPanel_.add(btnRoomplanPrint_);

		this.loadRooms();
	}

	/**
	 * loads all the {@link Room}s through the interface to the tablemodel
	 */
	public void loadRooms() {

		IntfCtrlRoom ctrlRoom = new CtrlRoom();

		// reload Table modell
		ViewManager.getInstance().getOrgaRoomTableModel()
				.addRooms(ctrlRoom.getAllRooms());
	}

	/**
	 * 
	 * @return the raumverwaltungTable
	 */
	public JTable getRoommanagementTable() {
		return roommanagementTable_;
	}

	/**
	 * @return the textFieldSeats
	 */
	public JTextField getTextFieldSeats() {
		return textFieldSeats_;
	}

	/**
	 * @return the textFieldPCSeats
	 */
	public JTextField getTextFieldPCSeats() {
		return textFieldPCSeats_;
	}

	/**
	 * @return the comboBoxLevel
	 */
	public JComboBox<String> getComboBoxLevel() {
		return comboBoxLevel_;
	}

	/**
	 * 
	 * @return the lblRoom
	 */
	public JLabel getLblRoom() {
		if (lblRoom_ == null) {
			lblRoom_ = new JLabel("Raum:");
		}
		return lblRoom_;
	}

	/**
	 * 
	 * @return the txtRoom
	 */
	public JTextField getTxtRoom() {
		if (txtRoom_ == null) {
			txtRoom_ = new JTextField();
			txtRoom_.setToolTipText("<html>Sie können die Liste der <br> Räume über diesen<br>Filter begrenzen</html>");
			txtRoom_.setColumns(10);
			txtRoom_.addActionListener(new RoomTabCmbboxFilter());
		}
		return txtRoom_;
	}

	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter_;
	}
}