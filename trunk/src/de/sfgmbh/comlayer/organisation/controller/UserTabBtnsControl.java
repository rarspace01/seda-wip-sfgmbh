package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class UserTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public UserTabBtnsControl() {
		this.navAction = "default";
	}
	public UserTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Benutzer hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaUserFrame().setVisible(true);
		}
		
		// Benutzer bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			//Bootstrap.serviceManager.getOrgaUserFrame().setVisible(true);
			this.getInfoWindow("<b>Fehlermeldung:</b><br> Sie haben keinen Nutzer selektiert!<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie" +
					" sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Benutzer löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Sie haben keinen Nutzer selektiert!").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher können keine Nutzer angezeigt werden.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
