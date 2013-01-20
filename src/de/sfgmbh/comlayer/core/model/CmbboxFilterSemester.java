package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

/**
 * Model for semester combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterSemester extends DefaultComboBoxModel<String>{

	private static final long serialVersionUID = 1L;
	private String variant;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterSemester() {
		this.variant = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param variant
	 */
	public CmbboxFilterSemester(String variant) {
		this.variant = variant;
		this.build();
		
	}

	public void build() {
		
		String[] elements;
		if (this.variant.equals("select")) {
			elements = new String[] {"WS 12/13", "SS 13", "WS 13/14", "SS 14"};
		} else {
			elements = new String[] {"<alle>", "WS 12/13", "SS 13", "WS 13/14", "SS 14"};
		}
		
		for (String element : elements) {
			this.addElement(element);
		}
	}
}
