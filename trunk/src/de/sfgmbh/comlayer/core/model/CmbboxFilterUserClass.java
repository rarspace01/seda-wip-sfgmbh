package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for user class combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterUserClass extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;
	private String variant_;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterUserClass() {
		this.variant_ = "normal";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant string
	 */
	public CmbboxFilterUserClass(String variant) {
		this.variant_ = variant;
		this.build();
	}

	private void build() {
		
		String[] elements;
		
		if (!this.variant_.equals("blank")) {
			elements = new String[] {"<alle>", "Dozenten", "Verwaltung"};
		} else {
			elements = new String[] {"Dozenten", "Verwaltung"};
		}
		
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
