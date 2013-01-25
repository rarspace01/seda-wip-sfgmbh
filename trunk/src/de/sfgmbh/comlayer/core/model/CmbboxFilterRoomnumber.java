package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for roomnumber combo boxes
 * 
 * @author mario
 * 
 */
public class CmbboxFilterRoomnumber extends DefaultComboBoxModel<String>
		implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String variant_;
	private JComboBox<String> dependentComboBox_;

	/**
	 * Create the model object
	 * 
	 * @param dependentComboBox
	 */
	public CmbboxFilterRoomnumber(JComboBox<String> dependentComboBox) {
		this.variant_ = "default";
		AppModel.getInstance().getRepositoryRoom().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.build();
	}

	/**
	 * Create the model object based on a variant
	 * 
	 * @param dependentComboBox
	 * @param variant
	 */
	public CmbboxFilterRoomnumber(JComboBox<String> dependentComboBox,
			String variant) {
		this.variant_ = variant;
		this.dependentComboBox_ = dependentComboBox;
		this.build();

	}

	private void build() {

		if (this.variant_.equals("default")) {
			this.addElement("<alle>");
		} else if (this.variant_.equals("select")) {
			this.addElement("");
		}
		for (IntfRoom room : AppModel.getInstance().getRepositoryRoom()
				.getAll()) {
			this.addElement(room.getRoomNumber_());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {

		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterRoomnumber newModel = new CmbboxFilterRoomnumber(
				this.dependentComboBox_);
		this.dependentComboBox_.setModel(newModel);

		// Unregister this model as it is no longer used and would cause
		// unwanted additional queries
		AppModel.getInstance().getRepositoryRoom().unregister(this);
	}
}
