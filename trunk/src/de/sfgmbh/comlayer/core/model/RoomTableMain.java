package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;

/**
 * Model of the room list table
 * 
 * @author mario
 * @author hannes
 *
 */
public class RoomTableMain extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Raum", "Gebäude", "Stock.", "Plätze", "PC-Plätze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards", "Hidden"};
	
	/**
	 * Create the room table model
	 */
	public RoomTableMain() {
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	private void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("room", "<alle>");
			filter.put("seats", "<alle>");
			filter.put("level", "<alle>");
		} else {
			
			// Ensure seats is a number
			String seats;
			try {
				int intSeats = Integer.parseInt(baseTab.getComboBoxSeatsFilter().getSelectedItem().toString());
				seats = String.valueOf(intSeats);
			} catch (Exception e) {
				AppModel.getInstance().getExceptionHandler().setNewException("Bitte stellen Sie sicher,  dass Sie eine Zahlen bei dem Sitze-Filtern eingetragen haben.", "Fehler!", "error");
				baseTab.getComboBoxSeatsFilter().setSelectedItem("0");
				return;
			}
			filter.put("room", baseTab.getComboBoxRoomnumberFilter().getSelectedItem().toString());
			filter.put("seats", seats);
			filter.put("level", baseTab.getComboBoxLevelFilter().getSelectedItem().toString());
		}
		
		for (IntfRoom room : AppModel.getInstance().getRepositoryRoom().getByFilter(filter)){
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
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
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
