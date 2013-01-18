package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class BaseCmbboxFilter implements KeyListener, ActionListener {

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
		ViewManager.getInstance().getCoreBaseTableModel().change();
		ViewManager.getInstance().getCoreRoomTableModel().change();
		ViewManager.getInstance().getOrgaRoomtableTableModel().change();
	}
	
}
