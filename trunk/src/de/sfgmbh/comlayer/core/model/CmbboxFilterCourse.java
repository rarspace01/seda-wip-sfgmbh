package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Course;

public class CmbboxFilterCourse extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	public CmbboxFilterCourse() {
		AppModel.getInstance().getRepositoryCourse().register(this);
		this.change();
	}

	@Override
	public void change() {
		this.addElement("<alle>");
		for (Course course : AppModel.getInstance().getRepositoryCourse().getAll()){
			this.addElement(course.getCourseAcronym_());
		}
	}
}
