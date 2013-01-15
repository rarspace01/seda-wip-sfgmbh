package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

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
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterLecturer() {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.change();
	}

	@Override
	public void change() {
		
		// Build and clean up the model on change - do not use
		// removeAllElements() as it can cause null pointer exceptions when an
		// observer model has null elements at any time
		int initalSize = this.getSize();
		this.addElement("<alle>");
		for (User user : AppModel.getInstance().getRepositoryUser().getAllLecturer()){
			this.addElement(user.getlName_());
		}
		for (int i = 0; initalSize > i; i++) {
			try {
				this.removeElementAt(i+1);
			} catch (Exception e) {
				// Be quiet and just go on, that's no big deal!
			}
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
}
