package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.organisation.views.RequestTab;

/**
 * Table model for the room allocation table for the organization
 * 
 * @author hannes
 *
 */
public class RequestTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Dozent", "Veranstaltung", "Tag", "Zeit", "Raum", "Semester", "Status", "Konflikt", "Hidden"};
	
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
		HashMap<String, String> filter = new HashMap<String, String>();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("chair", "<alle>");
			filter.put("status", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
			filter.put("room", "<alle>");
		} else {
			RequestTab requestTab = ViewManager.getInstance().getOrgaRquestTab();
			
			filter.put("chair", requestTab.getComboBoxChair().getSelectedItem().toString());
			filter.put("status", requestTab.getComboBoxStatus().getSelectedItem().toString());
			filter.put("lecturer", requestTab.getComboBoxLecturer().getSelectedItem().toString());
			filter.put("semester", requestTab.getComboBoxSemester().getSelectedItem().toString());
			filter.put("room", requestTab.getTxtRoom().getText());
		}
		
		// Time tracking for debugging - remove in final release
		long a, b, diff;
		a = (long) System.currentTimeMillis();
		
		for (IntfRoomAllocation ra : AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter)){
			
			// Set some speical Strings
			String conflict = "-";
			if (ra.isConflicting_()) {
				conflict = "JA!";
			}
			String status;
			status = ViewHelper.getAllocationStatus(ra.getApproved_());
			if (status.equals("wartend")) {
				// UTF-8 Special invisible character to force ordering
				// Character code: U+200D
				status = "‍‍‍‍wartend";
			}
			
			try {
				Object[] row = {
						ra.getCourse_().getLecturer_().getlName_(),
						ra.getCourse_().getCourseAcronym_(),
						ViewHelper.getDay(ra.getDay_()),
						ViewHelper.getTime(ra.getTime_()),
						ra.getRoom_().getRoomNumber_(),
						ra.getSemester_(),
						status,
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
