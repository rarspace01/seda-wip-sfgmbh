package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Action listener for comboboxes on the start tab
 * 
 * @author mario
 *
 */
public class BaseCmbboxFilter implements KeyListener, ActionListener {

	private String variant_;
	
	/**
	 * Create the action listener based on a variant<br>
	 * Supported variants are:<br>
	 * "rooms" - for the room table<br>
	 * "allocations" - for the allocation table
	 * @param variant
	 */
	public BaseCmbboxFilter (String variant) {
		this.variant_ = variant;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		//
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (this.variant_.equals("rooms")) {
			ViewManager.getInstance().getCoreRoomTableModel().change();
		}
		
		if (this.variant_.equals("allocations")) {
			ViewManager.getInstance().getCoreBaseTableModel().change();
		}
	}
	
}
