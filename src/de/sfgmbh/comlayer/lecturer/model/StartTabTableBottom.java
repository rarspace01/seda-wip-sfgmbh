package de.sfgmbh.comlayer.lecturer.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class StartTabTableBottom extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Bezeichnung", "Dozent", "Zeit", "Tag", "Semester", "Raum", "Status", "Hidden"};
	
	public StartTabTableBottom() {
		AppModel.getInstance().getRepositoryRoomAllocation().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	public void change(String variant) {
		ViewHelper vh = new ViewHelper();
		HashMap<String, String> filter = new HashMap<String, String>();
		User sessionUser = SessionManager.getInstance().getSession();
		
		if (sessionUser.getChair_() != null) {
		
			Chair sessionChair = sessionUser.getChair_();
		
			this.setRowCount(0);
			
			if (variant.equals("init")) {
				filter.put("chair", sessionChair.getAcronym_());
				filter.put("status", "<alle>");
				filter.put("login", sessionUser.getLogin_());
				filter.put("semester", "<alle>");
				filter.put("course", "<alle>");
			} else if (variant.equals("select")) {
				int row = ViewManager.getInstance().getLecturerStartTab().getTableCourseTop().getSelectedRow();
				if (row != -1) {
					Course selectedCourse = null;
					try {
						selectedCourse = (Course) ViewManager.getInstance().getLecturerStartTabTableTop().getValueAt(row, 6);
					} catch (Exception ex) {
						AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
					}
					filter.put("chair", sessionChair.getAcronym_());
					filter.put("login", selectedCourse.getLecturer_().getLogin_());
					filter.put("course", selectedCourse.getCourseAcronym_());
				}
			} else {
				filter.put("chair", sessionChair.getAcronym_());
				filter.put("status", ViewManager.getInstance().getLecturerStartTab().getComboBoxStatus().getSelectedItem().toString());
				filter.put("lecturer", ViewManager.getInstance().getLecturerStartTab().getComboBoxLecturerBottom().getSelectedItem().toString());
				filter.put("semester", ViewManager.getInstance().getLecturerStartTab().getComboBoxSemesterBottom().getSelectedItem().toString());
				filter.put("course", ViewManager.getInstance().getLecturerStartTab().getComboBoxCourse().getSelectedItem().toString());
			}
			
			for (RoomAllocation ra : AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter)){
				try {
					Object[] row = {
							ra.getCourse_().getCourseAcronym_(), 
							ra.getCourse_().getLecturer_().getlName_(), 
							vh.getTime(ra.getTime_()), 
							vh.getDay(ra.getDay_()), 
							ra.getSemester_(), 
							ra.getRoom_().getRoomNumber_(),
							vh.getAllocationStatus(ra.getApproved_()),
							ra
							};
					this.addRow(row);
	
				} catch (Exception e) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler:<br />" + e.toString(), "Fehler!");
				}
			}
		}
	}
	
	@Override
	public void change() {
		this.change("update");
	}
}
