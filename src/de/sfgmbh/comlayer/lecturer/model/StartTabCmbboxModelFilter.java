package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.DefaultComboBoxModel;

public class StartTabCmbboxModelFilter extends DefaultComboBoxModel<String> {

	private static final long serialVersionUID = 1L;
	
	public StartTabCmbboxModelFilter() {
		super(new String[] {"<alle>"});
	}
	public StartTabCmbboxModelFilter(String variant) {
		if (variant.equals("Doz")) {
			
			String[] elements = new String[] {"<alle>", "Sinz", "Ferstel", "Wolf", "Krieger"};
			
			for (String element : elements) {
				this.addElement(element);
			}
			
		} else if (variant.equals("Lehrstuhl")) {
			
			String[] elements = new String[] {"<alle>", "Lehrstuhl Eins", "Lehrstuhl Zwei", "SEDA", "IISM", "ISDL"};
			
			for (String element : elements) {
				this.addElement(element);
			}
			
		} else if (variant.equals("Sem")) {
			
			String[] elements = new String[] {"<alle>", "WS 12 / 13", "SS 13", "WS 13 /14"};
			
			for (String element : elements) {
				this.addElement(element);
			}
			
		} else if (variant.equals("Veranst")) {
			
			String[] elements = new String[] {"<alle>", "WI Projekt", "Standards und Netzwerke"};
			
			for (String element : elements) {
				this.addElement(element);
			}		
		} else {
			String[] elements = new String[] {"<alle>"};
			
			for (String element : elements) {
				this.addElement(element);
			}
		}
	}
}
