package de.sfgmbh.comlayer.lecturer.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Room allocation table model for the top table in the lecturer's start tab
 * 
 * @author hannes
 * @author christian
 *
 */
public class StartTabTableTop extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header_ = {"Bezeichnung", "Art", "Dozent", "SWS", "Erw. Teilnehmer", "öffentlich", "Hidden"};
	
	/**
	 * Creates a default model object and performs a default (initial) change action
	 */
	public StartTabTableTop() {
		AppModel.getInstance().getRepositoryCourse().register(this);
		this.setColumnIdentifiers(header_);
		this.change("init");
	}

	private void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();
		IntfUser sessionUser = SessionManager.getInstance().getSession();
		
		if (sessionUser.getChair_() != null) {
		
			IntfChair sessionChair = sessionUser.getChair_();
			
			this.setRowCount(0);
			
			if (variant.equals("init")) {
				filter.put("chair", sessionChair.getAcronym());
				filter.put("login", sessionUser.getLogin_());
			} else {
				filter.put("chair", sessionChair.getAcronym());
				filter.put("lecturer", ViewManager.getInstance().getLecturerStartTab().getComboLecturer().getSelectedItem().toString());
			}
			
			for (IntfCourse course : AppModel.getInstance().getRepositoryCourse().getByFilter(filter)){
				try {
					Object[] row = {
							course.getCourseAcronym_(), 
							course.getCourseKind_(),
							course.getLecturer_().getlName_(), 
							course.getSws_(), 
							course.getExpectedAttendees_(), 
							ViewHelper.getBoolean(course.isLecturerEnabled_()),
							course
							};
					this.addRow(row);
	
				} catch (Exception e) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler:<br />" + e.toString(), "Fehler!");
				}
				
			}
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
