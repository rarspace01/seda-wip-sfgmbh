package de.sfgmbh.comlayer.core.model;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
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
public class CmbboxFilterRoomnumber extends DefaultComboBoxModel<String> implements IntfAppObserver,IntfAppObservable,KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private String variant;
	private JComboBox<String> dependentComboBox;
	private ArrayList<Object> observer_=new ArrayList<Object>();
	
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
		for (Room room : AppModel.getInstance().getRepositoryRoom().getAll()){
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
