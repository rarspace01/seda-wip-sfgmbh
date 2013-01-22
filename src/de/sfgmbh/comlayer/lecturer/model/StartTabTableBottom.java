package de.sfgmbh.comlayer.lecturer.model;

import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Room allocation table model for the bottom table in the lecturer's start tab
 * 
 * @author hannes
 * @author christian
 *
 */
public class StartTabTableBottom extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Bezeichnung", "Dozent", "Zeit", "Tag", "Semester", "Raum", "Status", "Hidden"};
	
	/**
	 * Creates a default model object and performs a default (initial) change action
	 */
	public StartTabTableBottom() {
		AppModel.getInstance().getRepositoryRoomAllocation().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}
	
	/**
	 * Update the model<br>
	 * Depending on the variant and certain values which are currently present in the corresponding view components there are certain filters set.
	 * Based on these filters data is retrieved and the model is (re-)built.
	 * 
	 * @param variant
	 */
	public void change(String variant) {
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
					
					// Update the model of related combo boxes
					// As here happens all model updated related to the table and the combo box classes are reused for many tables this happens here
					ActionListener a1 = ViewManager.getInstance().getLecturerStartTab().getComboBoxCourse().getActionListeners()[0];
					ActionListener a2 = ViewManager.getInstance().getLecturerStartTab().getComboBoxLecturerBottom().getActionListeners()[0];
					ViewManager.getInstance().getLecturerStartTab().getComboBoxCourse().removeActionListener(a1);
					ViewManager.getInstance().getLecturerStartTab().getComboBoxLecturerBottom().removeActionListener(a2);
					ViewManager.getInstance().getLecturerStartTab().getComboBoxCourse().setSelectedItem(selectedCourse.getCourseAcronym_());
					ViewManager.getInstance().getLecturerStartTab().getComboBoxLecturerBottom().setSelectedItem(selectedCourse.getLecturer_().getlName_());
					ViewManager.getInstance().getLecturerStartTab().getComboBoxCourse().addActionListener(a1);
					ViewManager.getInstance().getLecturerStartTab().getComboBoxLecturerBottom().addActionListener(a2);
					
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
							ViewHelper.getTime(ra.getTime_()), 
							ViewHelper.getDay(ra.getDay_()), 
							ra.getSemester_(), 
							ra.getRoom_().getRoomNumber_(),
							ViewHelper.getAllocationStatus(ra.getApproved_()),
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
