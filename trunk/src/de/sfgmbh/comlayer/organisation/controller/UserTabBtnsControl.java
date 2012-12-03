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
		
		// Benutzer hinzuf�gen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaUserFrame().setVisible(true);
		}
		
		// Benutzer bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			Bootstrap.serviceManager.getOrgaUserFrame().setVisible(true);
		}
		
		// Benutzer l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("Benutzer wird gel�scht!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
