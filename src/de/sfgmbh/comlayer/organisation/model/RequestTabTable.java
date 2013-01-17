package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * Table model for the room allocation table for the organization
 * 
 * @author hannes
 *
 */
public class RequestTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Dozent", "Lehrstuhl", "Tag", "Zeit", "Raum", "Semester", "Status","Konflikt", "Hidden"};
	
	/**
	 * Creates an initial table model object
	 */
	public RequestTabTable() {
		AppModel.getInstance().getRepositoryRoomAllocation().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}
	
	/**
	 * Performs an action depending on the submitted variant to change and update the table model
	 * @param variant
	 */
	public void change(String variant) {
		ViewHelper vh = new ViewHelper();
		HashMap<String, String> filter = new HashMap<String, String>();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("chair", "<alle>");
			filter.put("status", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
			filter.put("room", "<alle>");
		} else {
			filter.put("chair", ViewManager.getInstance().getOrgaRquestTab().getComboBoxChair().getSelectedItem().toString());
			filter.put("status", ViewManager.getInstance().getOrgaRquestTab().getComboBoxStatus().getSelectedItem().toString());
			filter.put("lecturer", ViewManager.getInstance().getOrgaRquestTab().getComboBoxLecturer().getSelectedItem().toString());
			filter.put("semester", ViewManager.getInstance().getOrgaRquestTab().getComboBoxSemester().getSelectedItem().toString());
			filter.put("room", ViewManager.getInstance().getOrgaRquestTab().getTxtRoom().getText());
		}
		
		// Time tracking for debugging - remove in final release
		long a, b, diff;
		a = (long) System.currentTimeMillis();
		
		for (RoomAllocation ra : AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter)){
			
			String conflict = "-";
			if (ra.isConflicting_()) {
				conflict = "JA!!";
			}
			
			try {
				Object[] row = {
						ra.getCourse_().getLecturer_().getlName_(),
						ra.getCourse_().getLecturer_().getChair_().getAcronym_(),
						vh.getDay(ra.getDay_()),
						vh.getTime(ra.getTime_()),
						ra.getRoom_().getRoomNumber_(),
						ra.getSemester_(),
						vh.getAllocationStatus(ra.getApproved_()),
						conflict,
						ra
						};
				this.addRow(row);
				
			} catch (Exception e) {
				AppModel.getInstance().getExceptionHandler().setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler RequestTabTable-01:<br />" + e.toString(), "Fehler!");
			}
		}
		b = (long) System.currentTimeMillis();
		diff = b - a;
		System.out.println(diff + "ms for RequestTabTable build process");
	}

	/**
	 * disables edits on the table cells
	 * @author denis
	 */
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
	
	@Override
	public void change() {
		this.change("update");
	}
}
