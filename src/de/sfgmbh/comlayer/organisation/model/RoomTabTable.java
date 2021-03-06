package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.organisation.views.RoomTab;

/**
 * Table model for the room table
 * 
 * @author denis
 * 
 */
public class RoomTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] preFillHeader_ = { "roomid", "Raum", "Geb\u00E4ude",
			"Stock", "Pl\u00E4tze", "PC-Pl\u00E4tze", "Beamer", "Visualizer",
			"Overheads", "Tafeln", "Whiteboards" };

	/**
	 * Create the model
	 */
	public RoomTabTable() {
		this.setDataVector(null, preFillHeader_);
	}

	/**
	 * Add a room to the model
	 * 
	 * @param room
	 */
	public void addRoom(IntfRoom room) {

		Object[] rowVector = { room.getRoomId_(), room.getRoomNumber_(),
				"ERBA", room.getLevel_(), room.getSeats_(), room.getPcseats_(),
				room.getBeamer_(), room.getVisualizer_(), room.getOverheads_(),
				room.getChalkboards_(), room.getWhiteboards_() };

		this.addRow(rowVector);
	}

	/**
	 * Add a list of rooms to the model
	 * 
	 * @param allRooms
	 */
	public void addRooms(List<IntfRoom> allRooms) {

		for (int i = 0; i < allRooms.size(); i++) {
			this.addRoom(allRooms.get(i));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {

		// delete all rows
		this.setRowCount(0);

		// prepare a filter
		HashMap<String, String> filter = new HashMap<String, String>();
		RoomTab roomTab = ViewManager.getInstance().getOrgaRoomTab();
		// setting the filters
		String seats, pcSeats;
		try {
			int intSeats = Integer.parseInt(roomTab.getTextFieldSeats()
					.getText());
			int intPcSeats = Integer.parseInt(roomTab.getTextFieldPCSeats()
					.getText());
			seats = String.valueOf(intSeats);
			pcSeats = String.valueOf(intPcSeats);
		} catch (Exception e) {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Bitte stellen Sie sicher,  dass sie echte Zahlen bei den Filtern eingetragen haben",
							"Fehler!", "error");
			return;
		}
		// push filter into hashmap
		filter.put("level", roomTab.getComboBoxLevel().getSelectedItem()
				.toString());
		filter.put("seats", seats);
		filter.put("pcseats", pcSeats);
		filter.put("room", roomTab.getTxtRoom().getText());

		// get Rooms & push them into the table
		this.addRooms(AppModel.getInstance().getRepositoryRoom()
				.getByFilter(filter));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
