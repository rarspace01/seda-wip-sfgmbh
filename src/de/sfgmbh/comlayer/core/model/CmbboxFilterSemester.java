package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

/**
 * Model for semester combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterSemester extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterSemester() {
		this.change();
	}

	@Override
	public void change() {
		String[] elements = new String[] {"<alle>", "WS 12/13", "SS 13", "WS 13/14", "SS 14"};
		
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
