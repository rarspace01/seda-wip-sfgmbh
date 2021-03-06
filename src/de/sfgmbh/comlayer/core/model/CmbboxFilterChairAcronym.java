package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for chair acronym combo boxes
 * 
 * @author anna
 * 
 */
public class CmbboxFilterChairAcronym extends DefaultComboBoxModel<String>
		implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String variant_;
	private JComboBox<String> dependentComboBox_;

	/**
	 * Create the model object
	 * 
	 * @param dependentCombobox
	 */
	public CmbboxFilterChairAcronym(JComboBox<String> dependentComboBox) {
		this.variant_ = "normal";
		AppModel.getInstance().getRepositoryChair().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.build();
	}

	/**
	 * Create the model object based on a submitted variant string
	 */
	public CmbboxFilterChairAcronym(JComboBox<String> dependentComboBox,
			String variant) {
		this.variant_ = variant;
		AppModel.getInstance().getRepositoryChair().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.build();
	}

	private void build() {

		if (!this.variant_.equals("blank")) {
			this.addElement("<alle>");
		} else {
			this.addElement("<keiner>");
		}
		for (IntfChair chair : AppModel.getInstance().getRepositoryChair()
				.getAll()) {
			this.addElement(chair.getAcronym());
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
		CmbboxFilterChairAcronym newModel = new CmbboxFilterChairAcronym(
				this.dependentComboBox_);
		this.dependentComboBox_.setModel(newModel);

		// Unregister this model as it is no longer used and would cause
		// unwanted additional queries
		AppModel.getInstance().getRepositoryChair().unregister(this);
	}
}
