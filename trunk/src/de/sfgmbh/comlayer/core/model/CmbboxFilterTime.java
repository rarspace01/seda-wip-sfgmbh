package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for time combo boxes
 * 
 * @author mario
 * @author hannes
 *
 */
public class CmbboxFilterTime extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	private String variant;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterTime() {
		this.variant = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param variant
	 */
	public CmbboxFilterTime(String variant) {
		this.variant = variant;
		this.build();
		
	}

	public void build() {
		String[] elements = new String[] {"<alle>", "8:00 - 10:00", "10:00 - 12:00", "12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00"};
		if (this.variant.equals("select")) {
			elements = new String[] {"8:00 - 10:00", "10:00 - 12:00", "12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00"};
		}
		
		this.removeAllElements();
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
