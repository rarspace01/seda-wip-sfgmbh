package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;

public class CmbboxFilterLecturer extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	public CmbboxFilterLecturer() {
		AppModel.getInstance().repositoryUser.register(this);
		this.change();
	}

	@Override
	public void change() {
		this.addElement("<alle>");
		for (User user : AppModel.getInstance().repositoryUser.getAllLecturer()){
			this.addElement(user.getlName_());
		}
	}
}
