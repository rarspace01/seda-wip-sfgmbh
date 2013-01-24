package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for chair combo boxes
 * 
 * @author hannes
 * @author anna
 *
 */
public class CmbboxFilterChair extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox;
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterChair(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.dependentComboBox = dependentComboBox;
		this.build();
	}

	/**
	 * Create the model object
	 */
	public CmbboxFilterChair() {
		this.build();
	}

	private void build() {
		
		this.addElement("<alle>");
		for (IntfChair chair : AppModel.getInstance().getRepositoryChair().getAll()){
			this.addElement(chair.getChairName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterChair newModel = new CmbboxFilterChair(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryChair().unregister(this);
	}

}
