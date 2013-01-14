package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class RoomTableMain extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Raum", "Gebäude", "Stock.", "Plätze", "PC-Plätze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards", "Hidden"};
	
	public RoomTableMain() {
		AppModel.getInstance().repositoryRoomAllocation.register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	public void change(String variant) {
		ViewHelper vh = new ViewHelper();
		HashMap<String, String> filter = new HashMap<String, String>();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("chair", "<alle>");
			filter.put("course", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
		} else {
			filter.put("chair", ViewManager.getInstance().getCoreBaseTab().getComboBoxChariFilter().getSelectedItem().toString());
			filter.put("course", ViewManager.getInstance().getCoreBaseTab().getComboBoxOrgaFilter().getSelectedItem().toString());
			filter.put("lecturer", ViewManager.getInstance().getCoreBaseTab().getComboBoxLecturerFilter().getSelectedItem().toString());
			filter.put("semester", ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString());
		}
		
		for (RoomAllocation ra : AppModel.getInstance().repositoryRoomAllocation.getByFilter(filter)){
			if (ra.isPublic()) {
				try {
					Object[] row = {
							"WE5/03.004", 
							"Erba", 
							"3", 
							"55", 
							vh.getDay(ra.getDay_()),
							"2",
							"0", 
							"0", 
							"0",
							"1",
							ra
							};
					this.addRow(row);
	
				} catch (Exception e) {
					AppModel.getInstance().appExcaptions.setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />" + e.toString(), "Fehler!");
				}
			}
		}
	}
	
	@Override
	public void change() {
		this.change("update");
	}
}
