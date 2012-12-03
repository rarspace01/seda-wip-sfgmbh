package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;





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
		if (Bootstrap.serviceManager.getCoreBaseTableModel().getRowCount() >= 3) {
			Bootstrap.serviceManager.getCoreBaseTableModel().removeRow(0);
			Bootstrap.serviceManager.getCoreBaseTableModel().removeRow(1);
		} else {
			this.getInfoWindow().setVisible(true);
		}
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("Die erste Filteraktion hat nur Demozwecken gedient (ohne tatsächlich auf die Filtereinstellung zu reagieren). Weitere Aktionen sind in der GUI Demo nicht möglich!");
		}
		return this.infoWindow;
	}
	
}
