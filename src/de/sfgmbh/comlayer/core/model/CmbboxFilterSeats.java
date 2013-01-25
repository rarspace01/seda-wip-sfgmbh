package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for allocation status combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterSeats extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the model object
	 */
	public CmbboxFilterSeats() {
		this.build();
	}

	private void build() {
		String[] elements = new String[] { "0", "10", "30", "50", "100" };

		for (String element : elements) {
			this.addElement(element);
		}
	}
}
