package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

public class CmbboxFilterAllocationStatus extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
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
