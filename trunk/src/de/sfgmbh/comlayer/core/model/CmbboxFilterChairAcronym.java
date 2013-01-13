package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;

public class CmbboxFilterChairAcronym extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String variant_;
	
	public CmbboxFilterChairAcronym() {
		this.variant_ = "normal";
		AppModel.getInstance().repositoryChair.register(this);
		this.change();
	}
	
	public CmbboxFilterChairAcronym(String variant) {
		this.variant_ = variant;
		AppModel.getInstance().repositoryChair.register(this);
		this.change();
	}

	@Override
	public void change() {
		if (!this.variant_.equals("blank")) {
			this.addElement("<alle>");
		} else {
			this.addElement("<keiner>");
		}
		for (Chair chair : AppModel.getInstance().repositoryChair.getAll()){
			this.addElement(chair.getAcronym_());
		}
	}
}
