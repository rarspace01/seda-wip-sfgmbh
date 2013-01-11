package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class RequestTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Dozent", "Lehrstuhl", "Tag", "Zeit", "Raum", "Semester", "Status", "Hidden"};
	
	public RequestTabTable() {
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
			filter.put("status", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
			filter.put("room", "<alle>");
		} else {
			filter.put("chair", ServiceManager.getInstance().getOrgaRquestTab().getComboBoxChair().getSelectedItem().toString());
			filter.put("status", ServiceManager.getInstance().getOrgaRquestTab().getComboBoxStatus().getSelectedItem().toString());
			filter.put("lecturer", ServiceManager.getInstance().getOrgaRquestTab().getComboBoxLecturer().getSelectedItem().toString());
			filter.put("semester", ServiceManager.getInstance().getOrgaRquestTab().getComboBoxSemester().getSelectedItem().toString());
			filter.put("room", ServiceManager.getInstance().getOrgaRquestTab().getTxtRoom().getText());
		}
		
		for (RoomAllocation ra : AppModel.getInstance().repositoryRoomAllocation.getByFilter(filter)){
			
			try {
				Object[] row = {
						ra.getCourse_().getLecturer_().getlName_(),
						ra.getCourse_().getLecturer_().getChair().getAcronym_(),
						vh.getDay(ra.getDay_()),
						vh.getTime(ra.getTime_()),
						ra.getRoom_().getRoomNumber_(),
						ra.getSemester_(),
						vh.getAllocationStatus(ra.getApproved_()),
						ra
						};
				this.addRow(row);

			} catch (Exception e) {
				AppModel.getInstance().appExcaptions.setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler RequestTabTable-01:<br />" + e.toString(), "Fehler!");
			}
		}
	}

	@Override
	public void change() {
		this.change("update");
	}
}
