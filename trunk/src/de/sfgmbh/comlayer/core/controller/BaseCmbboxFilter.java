package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Action listener for comboboxes on the start tab
 * 
 * @author User
 *
 */
public class BaseCmbboxFilter implements KeyListener, ActionListener {

	private String variant;
	
	/**
	 * Create the action listener based on a variant
	 * @param variant
	 */
	public BaseCmbboxFilter (String variant) {
		this.variant = variant;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (this.variant.equals("rooms")) {
			ViewManager.getInstance().getCoreRoomTableModel().change();
		}
		
		if (this.variant.equals("allocations")) {
			ViewManager.getInstance().getCoreBaseTableModel().change();
		}
	}
	
}
