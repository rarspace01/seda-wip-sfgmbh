package modules.base.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modules.base.views.InfoWindow;

import services.Bootstrap;


public class BaseCmbboxFilter implements KeyListener, ActionListener {
	
	protected InfoWindow infoWindow;
	
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
		} else {
			this.getInfoWindow().setVisible(true);
		}
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("Die erste Filteraktion hat nur Demozwecken gedient (ohne tatsächlich auf die Filtereinstellung zu reagieren). Weitere Aktionen sind in der GUI Demo nicht möglich!");
		}
		return this.infoWindow;
	}
	
}
