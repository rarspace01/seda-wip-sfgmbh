package modules.coregui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import services.Bootstrap;


public class VeranstaltungsFilter implements KeyListener, ActionListener {
	
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
		if (Bootstrap.serviceManager.getVTableModel().getRowCount() >= 3) {
			Bootstrap.serviceManager.getVTableModel().removeRow(0);
			Bootstrap.serviceManager.getVTableModel().removeRow(1);
		}
	}
	
}
