package de.sfgmbh.comlayer.lecturer.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class StartTabTableTop extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Bezeichnung", "Art", "Dozent", "SWS", "Erw. Teilnehmer", "öffentlich", "Hidden"};
	
	public StartTabTableTop() {
		AppModel.getInstance().repositoryCourse.register(this);
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
				filter.put("login", sessionUser.getLogin_());
			} else {
				filter.put("chair", sessionChair.getAcronym_());
				filter.put("lecturer", ViewManager.getInstance().getLecturerStartTab().getComboLecturer().getSelectedItem().toString());
			}
			
			for (Course course : AppModel.getInstance().repositoryCourse.getByFilter(filter)){
				try {
					Object[] row = {
							course.getCourseAcronym_(), 
							course.getCourseKind_(),
							course.getLecturer_().getlName_(), 
							course.getSws_(), 
							course.getExpectedAttendees_(), 
							vh.getBoolean(course.isLecturerEnabled_()),
							course
							};
					this.addRow(row);
	
				} catch (Exception e) {
					AppModel.getInstance().appExcaptions.setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler:<br />" + e.toString(), "Fehler!");
				}
				
			}
		}
	}
	
	@Override
	public void change() {
		this.change("update");
	}
}
