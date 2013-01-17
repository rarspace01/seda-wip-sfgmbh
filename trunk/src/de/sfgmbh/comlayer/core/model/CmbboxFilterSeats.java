package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for allocation status combo boxes
 * 
 * @author mario
 *
 */
public class CmbboxFilterSeats extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterSeats() {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.change();
	}

	@Override
	public void change() {
		String[] elements = new String[] {"<alle>", ">= 10", ">= 30", ">= 50", ">= 100"};
		
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
