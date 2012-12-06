package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class RequestTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public RequestTabBtnsControl() {
		this.navAction = "default";
	}
	public RequestTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Konfliktlösen Button is pressed
		if (this.navAction.equals("Konflikt")) {
			Bootstrap.serviceManager.getOrgaCounterproposalFrame().setVisible(true);
		}
		
		// Ablehnen Button is pressed
		if (this.navAction.equals("ablehnen")) {
			this.getInfoWindow("<b>Die Lehrveranstaltung konnte nicht freigegeben werden:</b><br><br>Sie haben keine Lehrveranstaltung selektiert!").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<b> Es konnte keine Datenverbindung hergestellt werden. Somit können keine Anfragen angezeigt werden.</b>").setVisible(true);
		}
		
		// Freigeben Button is pressed
		if (this.navAction.equals("publish")) {
			this.getInfoWindow("<b>Die Lehrveranstaltung konnte nicht freigegeben werden:</b><br><br>Sie haben keine Lehrveranstaltung selektiert!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
		
	}
}
