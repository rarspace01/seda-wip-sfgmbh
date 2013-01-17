package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;

/**
 * Model for chair combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterChair extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterChair() {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.change();
	}

	@Override
	public void change() {
		// Build and clean up the model on change - do not use
		// removeAllElements() as it can cause null pointer exceptions when an
		// observer model has null elements at any time
		int initalSize = this.getSize();
		this.addElement("<alle>");
		for (Chair chair : AppModel.getInstance().getRepositoryChair().getAll()){
			this.addElement(chair.getChairName_());
		}
		for (int i = 0; initalSize > i; i++) {
			this.removeElementAt(i+1);
		}
	}
}
