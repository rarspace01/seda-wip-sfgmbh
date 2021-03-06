package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for course kind combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterKind extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;
	private String variant_;

	/**
	 * Create the model object
	 */
	public CmbboxFilterKind() {
		this.variant_ = "default";
		this.build();
	}

	/**
	 * Create the model object based on a variant
	 * 
	 * @param variant
	 */
	public CmbboxFilterKind(String variant) {
		this.variant_ = variant;
		this.build();

	}

	private void build() {
		String[] elements = new String[] { "<alle>", "Vorlesung", "Übung",
				"Tutorium" };
		if (this.variant_.equals("select")) {
			elements = new String[] { "Vorlesung", "Übung", "Tutorium" };
		}

		this.removeAllElements();
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
