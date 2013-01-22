package de.sfgmbh.comlayer.core.model;

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
import de.sfgmbh.applayer.core.model.Chair;

/**
 * Model for chair combo boxes
 * 
 * @author hannes
 *
 */
public class CmbboxFilterChair extends DefaultComboBoxModel<String> implements IntfAppObserver,IntfAppObservable,KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> dependentComboBox;
	private ArrayList<Object> observer_= new ArrayList<Object>();
	
	/**
	 * Create the model object
	 * @param dependentCombobox
	 */
	public CmbboxFilterChair(JComboBox<String> dependentComboBox) {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.dependentComboBox = dependentComboBox;
		this.build();
	}

	public CmbboxFilterChair() {
		this.build();
	}

	private void build() {
		
		this.addElement("<alle>");
		for (Chair chair : AppModel.getInstance().getRepositoryChair().getAll()){
			this.addElement(chair.getChairName_());
		}
	}

	@Override
	public void change() {
		
		// Build a new model (which will be up to date automatically) and set it
		CmbboxFilterChair newModel = new CmbboxFilterChair(this.dependentComboBox);
		this.dependentComboBox.setModel(newModel);
		
		// Unregister this model as it is no longer used and would cause unwanted additional queries
		AppModel.getInstance().getRepositoryChair().unregister(this);
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
