package de.sfgmbh.comlayer.core.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;

/**
 * Model for lecturer combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterLecturer extends DefaultComboBoxModel<String> implements IntfAppObserver,IntfAppObservable,KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox;
	private String variant;
	private ArrayList<User> lecturerForModel = new ArrayList<User>();
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox = dependentComboBox;
		this.variant = "default";
		this.build();
	}
	
	/**
	 * Create the model object based on a variant
	 * @param dependentCombobox
	 * @param variant
	 */
	public CmbboxFilterLecturer(JComboBox<String> dependentComboBox, String variant) {
		AppModel.getInstance().getRepositoryUser().register(this);
		this.dependentComboBox = dependentComboBox;
		this.variant = variant;
		this.build();
	}

	public CmbboxFilterLecturer() {
		this.variant = "default";
		this.build();
	}

	private void build() {
		
		if (this.variant.equals("select")) {
			// currently nothing to add here
		} else if (this.variant.equals("create")) {
			this.addElement("<keiner>");
			lecturerForModel.add(0, null);
		} else {
			this.addElement("<alle>");
			lecturerForModel.add(0, null);
		}
		
		for (User user : AppModel.getInstance().getRepositoryUser().getAllLecturer()){
			this.addElement(user.getlName_());
			int desiredIndex = this.getSize()-1;
			lecturerForModel.add(desiredIndex, user);
		}

		// Set the selected lecturer to the currently logged in lecturer if
		// there is a lecturer logged in - otherwise set it to "<alle>"
		User currentUser = SessionManager.getInstance().getSession();
		if (currentUser != null) {
			if (currentUser.getClass_().equals("lecturer")) {
				this.setSelectedItem(currentUser.getlName_());
			} 
		}
	}
	
	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterLecturer newModel = new CmbboxFilterLecturer(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryUser().unregister(this);
	}

	/**
	 * @return the lecturerForModel
	 */
	public ArrayList<User> getLecturerForModel() {
		return lecturerForModel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		
		// Create a private observer list to avoid ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<Object> currentObservers = (ArrayList<Object>) observer_.clone();
		
		for (Object o : (currentObservers)) {
			if (o instanceof IntfAppObserver) {
				((IntfAppObserver) o).change();
			}
		}
		
	}

	@Override
	public void register(Object observer) {
		observer_.add(observer);
	}

	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
