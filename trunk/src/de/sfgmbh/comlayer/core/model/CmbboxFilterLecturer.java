package de.sfgmbh.comlayer.core.model;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;

/**
 * Model for lecturer combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterLecturer extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox_;
	private String variant_;
	private ArrayList<User> lecturerForModel_ = new ArrayList<User>();
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.variant_ = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param dependentCombobox
	 * @param variant
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox, String variant) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.variant_ = variant;
		this.build();
	}

	/**
	 * Create the model object
	 */
	public CmbboxFilterLecturer() {
		this.variant_ = "default";
		this.build();
	}

	private void build() {
		
		if (this.variant_.equals("select")) {
			// currently nothing to add here
		} else if (this.variant_.equals("create")) {
			this.addElement("<keiner>");
			lecturerForModel_.add(0, null);
		} else {
			this.addElement("<alle>");
			lecturerForModel_.add(0, null);
		}
		
		for (User user : AppModel.getInstance().getRepositoryUser().getAllLecturer()){
			this.addElement(user.getlName_());
			int desiredIndex = this.getSize()-1;
			lecturerForModel_.add(desiredIndex, user);
		}

		// Set the selected lecturer to the currently logged in lecturer if
		// there is a lecturer logged in - otherwise set it to "<alle>"
		IntfUser currentUser = SessionManager.getInstance().getSession();
		if (currentUser != null) {
			if (currentUser.getClass_().equals("lecturer")) {
				this.setSelectedItem(currentUser.getlName_());
			} 
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterLecturer newModel = new CmbboxFilterLecturer(this.dependentComboBox_);
		this.dependentComboBox_.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryUser().unregister(this);
	}

	/**
	 * Get an array of lecturers which corresponds to the model (same indexes as model positions)
	 * @return the lecturerForModel
	 */
	public ArrayList<User> getLecturerForModel() {
		return lecturerForModel_;
	}
}
