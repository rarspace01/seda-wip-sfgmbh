package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;

/**
 * Model for chair acronym combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterChairAcronym extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String variant_;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterChairAcronym() {
		this.variant_ = "normal";
		AppModel.getInstance().getRepositoryChair().register(this);
		this.change();
	}
	
	/**
	 * Create the model object based on a submitted variant string
	 */
	public CmbboxFilterChairAcronym(String variant) {
		this.variant_ = variant;
		AppModel.getInstance().getRepositoryChair().register(this);
		this.change();
	}

	@Override
	public void change() {
		
		// Build and clean up the model on change - do not use
		// removeAllElements() as it can cause null pointer exceptions when an
		// observer model has null elements at any time
		int initalSize = this.getSize();
		if (!this.variant_.equals("blank")) {
			this.addElement("<alle>");
		} else {
			this.addElement("<keiner>");
		}
		for (Chair chair : AppModel.getInstance().getRepositoryChair().getAll()){
			this.addElement(chair.getAcronym_());
		}
		for (int i = 0; initalSize > i; i++) {
			this.removeElementAt(i+1);
		}
	}
}
