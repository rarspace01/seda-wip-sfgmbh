package comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import comlayer.core.views.InfoDialog;


public class CmbboxFilter implements KeyListener, ActionListener {
	
	protected InfoDialog infoWindow;
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		this.getInfoWindow().setVisible(true);
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
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("Filterfunktionen sind hier noch nicht implementiert");
		}
		return this.infoWindow;
	}
	
}
