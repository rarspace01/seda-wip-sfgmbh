package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class BaseTableMain extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Sem.", "Art", "Bez.", "Name", "Dozent", "Tag", "Uhrzeit", "Raum", "SWS", "Hidden"};
	
	public BaseTableMain() {
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
			filter.put("chair", ServiceManager.getInstance().getCoreBaseTab().getComboBoxChariFilter().getSelectedItem().toString());
			filter.put("course", ServiceManager.getInstance().getCoreBaseTab().getComboBoxOrgaFilter().getSelectedItem().toString());
			filter.put("lecturer", ServiceManager.getInstance().getCoreBaseTab().getComboBoxLecturerFilter().getSelectedItem().toString());
			filter.put("semester", ServiceManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString());
		}
		
		for (RoomAllocation ra : AppModel.getInstance().repositoryRoomAllocation.getByFilter(filter)){
			if (ra.isPublic()) {
				try {
					Object[] row = {
							ra.getSemester_(), 
							ra.getCourse_().getCourseKind_(), 
							ra.getCourse_().getCourseAcronym_(), 
							ra.getCourse_().getCourseName_(), 
							ra.getCourse_().getLecturer_().getlName_(), 
							vh.getDay(ra.getDay_()),
							vh.getTime(ra.getTime_()), 
							ra.getRoom_().getRoomNumber_(), 
							ra.getCourse_().getSws_(),
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
