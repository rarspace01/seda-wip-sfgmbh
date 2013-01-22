package de.sfgmbh.comlayer.core.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

/**
 * Model for allocation status combo boxes
 * 
 * @author mario
 *
 */
public class CmbboxFilterSeats extends DefaultComboBoxModel<String> implements IntfAppObservable,KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private ArrayList<Object> observer_= new ArrayList<Object>();
	
	/**
	 * Create the model object
	 */
	public CmbboxFilterSeats() {
		this.build();
	}

	private void build() {
		String[] elements = new String[] {"<alle>", ">= 10", ">= 30", ">= 50", ">= 100"};
		
		for (String element : elements) {
			this.addElement(element);
		}
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
