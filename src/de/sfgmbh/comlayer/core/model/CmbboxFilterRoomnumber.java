package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;

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
	private JComboBox<String> dependentComboBox;
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterRoomnumber(JComboBox<String> dependentComboBox) {
		this.variant = "default";
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.dependentComboBox = dependentComboBox;
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param variant
	 * @param dependentCombobox
	 */
	public CmbboxFilterRoomnumber(JComboBox<String> dependentComboBox, String variant) {
		this.variant = variant;
		this.dependentComboBox = dependentComboBox;
		this.build();
		
	}

	private void build() {
		
		if (this.variant.equals("default")) {
			this.addElement("<alle>");
		} else if (this.variant.equals("select")) {
			this.addElement("");
		}
		for (IntfRoom room : AppModel.getInstance().getRepositoryRoom().getAll()){
			this.addElement(room.getRoomNumber_());
		}
	}
	
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterRoomnumber newModel = new CmbboxFilterRoomnumber(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryRoom().unregister(this);
	}
}
