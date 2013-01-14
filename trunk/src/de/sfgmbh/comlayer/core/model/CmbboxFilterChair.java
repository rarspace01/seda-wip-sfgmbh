package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;

public class CmbboxFilterChair extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	public CmbboxFilterChair() {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.change();
	}

	@Override
	public void change() {
		this.addElement("<alle>");
		for (Chair chair : AppModel.getInstance().getRepositoryChair().getAll()){
			this.addElement(chair.getChairName_());
		}
	}
}
