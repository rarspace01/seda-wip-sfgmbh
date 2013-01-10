package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;





public class BaseCmbboxFilter implements KeyListener, ActionListener {
	
	protected InfoDialog infoWindow;
	
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
		ServiceManager.getInstance().getCoreBaseTableModel().change();
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("Die erste Filteraktion hat nur Demozwecken gedient (ohne tats�chlich auf die Filtereinstellung zu reagieren). Weitere Aktionen sind in der GUI Demo nicht m�glich!");
		}
		return this.infoWindow;
	}
	
}
