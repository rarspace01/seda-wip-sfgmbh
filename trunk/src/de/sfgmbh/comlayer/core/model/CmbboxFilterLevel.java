package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;

/**
 * Model for room level combo boxes
 * 
 * @author hannes
 * @author mario
 *
 */
public class CmbboxFilterLevel extends DefaultComboBoxModel<String> implements IntfAppObserver{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox;
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterLevel(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.dependentComboBox = dependentComboBox;
		this.build();
	}

	private void build() {
		
		this.addElement("<alle>");
		for (Room room : AppModel.getInstance().getRepositoryRoom().getAll()){
			// Check if already exists as we do not want multi-sets
			if (this.getIndexOf(room.getLevel_()) < 0) {
				this.addElement(room.getLevel_());
			}
		}
	}
	
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterLevel newModel = new CmbboxFilterLevel(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryRoom().unregister(this);
	}
}
