package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;

public class CmbboxFilterBuildingid extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	public CmbboxFilterBuildingid() {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.change();
	}

	@Override
	public void change() {
		this.addElement("<alle>");
		for (User user : AppModel.getInstance().getRepositoryUser().getAllLecturer()){
			this.addElement(user.getlName_());
		}
	}
}
