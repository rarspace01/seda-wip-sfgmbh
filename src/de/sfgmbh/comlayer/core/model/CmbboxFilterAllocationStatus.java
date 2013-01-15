package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

/**
 * Model for allocation status combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterAllocationStatus extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterAllocationStatus() {
		this.change();
	}

	@Override
	public void change() {
		String[] elements = new String[] {"<alle>", "wartend", "abgelehnt", "freigegeben"};
		

		for (String element : elements) {
			this.addElement(element);
		}
	}
}
