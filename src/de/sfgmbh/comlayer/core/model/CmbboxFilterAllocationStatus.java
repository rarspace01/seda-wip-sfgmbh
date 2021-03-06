package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for allocation status combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterAllocationStatus extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the model object
	 */
	public CmbboxFilterAllocationStatus() {
		this.build();
	}

	private void build() {
		String[] elements = new String[] { "<alle>", "wartend", "abgelehnt",
				"freigegeben", "Gegenvorschlag" };

		for (String element : elements) {
			this.addElement(element);
		}
	}
}
