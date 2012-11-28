package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modules.base.views.InfoWindow;


public class DozentenTabCmbboxFilter implements KeyListener, ActionListener {
	
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
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("Filterfunktionen sind hier noch nicht implementiert");
		}
		return this.infoWindow;
	}
	
}
