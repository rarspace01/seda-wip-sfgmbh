package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



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
			//Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true)
			this.getInfoWindow("<b>Alternativen:</b><br><br>1. Sie haben keinen Raum selektiert!<br>2. Sie gelangen sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Raum löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Der Raum konnte nicht gelöscht werden:</b><br><br>Sie haben keinen Raum selektiert.").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("Es besteht keine Verbindung zur Datenbank. Daher können keine Räume angezeigt werden.").setVisible(true);
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
