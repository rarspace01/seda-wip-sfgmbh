package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
/**
 * 
 * @author hannes
 *
 */
public class UserTabCmbboxFilter implements KeyListener, ActionListener {

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
		ViewManager.getInstance().getOrgaUserTableModel().change();
	}
	
}
