package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
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
	private JComboBox<String> dependentComboBox;
	private String variant;
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox = dependentComboBox;
		this.variant = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param dependentCombobox
	 * @param variant
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox, String variant) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox = dependentComboBox;
		this.variant = variant;
		this.build();
	}

	private void build() {
		
		if (!this.variant.equals("select")) {
			this.addElement("<alle>");
		}
		for (User user : AppModel.getInstance().getRepositoryUser().getAllLecturer()){
			this.addElement(user.getlName_());
		}

		// Set the selected lecturer to the currently logged in lecturer if
		// there is a lecturer logged in - otherwise set it to "<alle>"
		User currentUser = SessionManager.getInstance().getSession();
		if (currentUser != null) {
			if (currentUser.getClass_().equals("lecturer")) {
				this.setSelectedItem(currentUser.getlName_());
			} else {
				this.setSelectedItem("<alle>");
			}
		} else {
			this.setSelectedItem("<alle>");
		}
	}
	
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterLecturer newModel = new CmbboxFilterLecturer(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
	}
}
