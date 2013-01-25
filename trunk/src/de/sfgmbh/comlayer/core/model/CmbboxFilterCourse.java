package de.sfgmbh.comlayer.core.model;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.model.AppModel;

/**
 * Model for course combo boxes
 * 
 * @author hannes
 * @author mario
 *
 */
public class CmbboxFilterCourse extends DefaultComboBoxModel<String> implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox_;
	
	/**
	 * Create the model object
	 * @param dependentComboBox
	 */
	public CmbboxFilterCourse(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryCourse().register(this);
		this.dependentComboBox_ = dependentComboBox;
		this.build();
	}

	private void build() {
		
		this.addElement("<alle>");
		for (IntfCourse course : AppModel.getInstance().getRepositoryCourse().getAll()){
			this.addElement(course.getCourseAcronym_());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterCourse newModel = new CmbboxFilterCourse(this.dependentComboBox_);
		this.dependentComboBox_.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryCourse().unregister(this);
	}
}
