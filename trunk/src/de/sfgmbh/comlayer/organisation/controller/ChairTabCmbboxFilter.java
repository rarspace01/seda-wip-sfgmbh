package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * 
 * @author hannes
 * @author mario
 *
 */
public class ChairTabCmbboxFilter implements KeyListener, ActionListener {
	
	
	
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
		ViewManager.getInstance().getOrgaChairTableModel().change();
	} 
	
	/*public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("Filterfunktionen sind hier noch nicht implementiert");
		}
		return this.infoWindow;
	}
	*/
}
