package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.comlayer.core.controller.ViewManager;

public class RoomTableMain extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Raum", "Gebäude", "Stock.", "Plätze", "PC-Plätze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards", "Hidden"};
	
	public RoomTableMain() {
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	public void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("room", "<alle>");
			filter.put("seats", "<alle>");
			filter.put("level", "<alle>");
		} else {
			filter.put("room", ViewManager.getInstance().getCoreBaseTab().getComboBoxRoomnumberFilter().getSelectedItem().toString());
			filter.put("seats", ViewManager.getInstance().getCoreBaseTab().getComboBoxSeatsFilter().getSelectedItem().toString());
			filter.put("level", ViewManager.getInstance().getCoreBaseTab().getComboBoxLevelFilter().getSelectedItem().toString());
		}
		
		for (Room room : AppModel.getInstance().getRepositoryRoom().getByFilter(filter)){
			//if (room.isPublic()) {
				try {
					Object[] row = {
							room.getRoomNumber_(), 
							room.getBuildingId_(),
							room.getLevel_(),
							room.getSeats_(),
							room.getPcseats_(),
							room.getBeamer_(),
							room.getVisualizer_(),
							room.getOverheads_(),
							room.getChalkboards_(),
							room.getWhiteboards_(),
							room,
							};
					this.addRow(row);
	
				} catch (Exception e) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />" + e.toString(), "Fehler!");
				}
			//}
		}
	}
	
	@Override
	public void change() {
		this.change("update");
	}
	
	/**
	 * disables edits on the table cells
	 * 
	 * @author denis
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
