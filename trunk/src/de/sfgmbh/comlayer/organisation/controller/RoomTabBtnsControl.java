package comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;

import applayer.core.controller.Bootstrap;


public class RoomTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public RoomTabBtnsControl() {
		this.navAction = "default";
	}
	public RoomTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Raum hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true);
		}
		
		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true);
		}
		
		// Raum löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("Raum wird gelöscht!").setVisible(true);
		}
		
		// Raumplan Button is pressed
		if (this.navAction.equals("Raumplan")) {
			this.getInfoWindow("Raumplan noch nicht implementiert!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
