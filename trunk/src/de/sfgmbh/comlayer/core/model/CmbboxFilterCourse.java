package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for course combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterCourse extends DefaultComboBoxModel<String> implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox_;
	private boolean isRestricted_;

	/**
	 * Create the model object
	 * 
	 * @param dependentComboBox
	 */
	public CmbboxFilterCourse(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryCourse().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.isRestricted_ = false;
		this.build();
	}

	/**
	 * Create the model object and check if it should display a restricted view
	 * of only courses from lecturer of the same chair
	 * 
	 * @param dependentComboBox
	 * @param isRestricted
	 */
	public CmbboxFilterCourse(JComboBox<String> dependentComboBox,
			boolean isRestricted) {
		AppModel.getInstance().getRepositoryCourse().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.isRestricted_ = isRestricted;
		this.build();
	}

	private void build() {
		IntfUser currentUser = SessionManager.getInstance().getSession();

		// Check and get for a restricted view (only courses from the same
		// chair)
		if (this.isRestricted_ && currentUser != null
				&& currentUser.getChair_() != null) {
			HashMap<String, String> filter = new HashMap<String, String>();
			filter.put("chair", currentUser.getChair_().getChairName());

			this.addElement("<alle>");
			for (IntfCourse course : AppModel.getInstance()
					.getRepositoryCourse().getByFilter(filter)) {
				this.addElement(course.getCourseAcronym_());
			}

			// Else retrieve all courses
		} else {
			this.addElement("<alle>");
			for (IntfCourse course : AppModel.getInstance()
					.getRepositoryCourse().getAll()) {
				this.addElement(course.getCourseAcronym_());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {

		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterCourse newModel = new CmbboxFilterCourse(
				this.dependentComboBox_);
		this.dependentComboBox_.setModel(newModel);

		// Unregister this model as it is no longer used and would cause
		// unwanted additional queries
		AppModel.getInstance().getRepositoryCourse().unregister(this);
	}
}
