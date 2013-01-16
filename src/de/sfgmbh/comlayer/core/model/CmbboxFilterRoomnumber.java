package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;

/**
 * Model for roomnumber combo boxes
 * 
 * @author hannes
 * @author mario
 *
 */
public class CmbboxFilterRoomnumber extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String variant;
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterRoomnumber() {
		this.variant = "default";
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.change();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param variant
	 */
	public CmbboxFilterRoomnumber(String variant) {
		this.variant = variant;
		this.change();
		
	}

	@Override
	public void change() {
		
		// Build and clean up the model on change - do not use
		// removeAllElements() as it can cause null pointer exceptions when an
		// observer model has null elements at any time
		int initalSize = this.getSize();
		if (this.variant.equals("default")) {
			this.addElement("<alle>");
		} else if (this.variant.equals("select")) {
			this.addElement("");
		}
		for (Room room : AppModel.getInstance().getRepositoryRoom().getAll()){
			this.addElement(room.getRoomNumber_());
		}
		for (int i = 0; initalSize > i; i++) {
			this.removeElementAt(i+1);
		}
	}
}
