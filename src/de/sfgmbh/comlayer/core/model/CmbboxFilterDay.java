package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for day combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterDay extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;
	private String variant_;

	/**
	 * Create the model object
	 */
	public CmbboxFilterDay() {
		this.variant_ = "default";
		this.build();
	}

	/**
	 * Create the model object based on a variant
	 * 
	 * @param variant
	 */
	public CmbboxFilterDay(String variant) {
		this.variant_ = variant;
		this.build();

	}

	private void build() {
		String[] elements = new String[] { "<alle>", "Montag", "Dienstag",
				"Mittwoch", "Donnerstag", "Freitag" };
		if (this.variant_.equals("select")) {
			elements = new String[] { "Montag", "Dienstag", "Mittwoch",
					"Donnerstag", "Freitag" };
		}

		this.removeAllElements();
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
