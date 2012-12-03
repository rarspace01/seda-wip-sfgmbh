package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;

import de.sfgmbh.applayer.core.controller.Bootstrap;




public class BtnsNav implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public BtnsNav() {
		this.navAction = "default";
	}
	public BtnsNav(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Nutzerverwaltungs Button is pressed
		if (this.navAction.equals("Nutzerverw")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Nutzerverwaltung", null, Bootstrap.serviceManager.getOrgaUserTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Lehrstuhlverwaltung Button is pressed
		if (this.navAction.equals("Lehrstuhlverw")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, Bootstrap.serviceManager.getOrgaProfessorshipTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Raumverwaltung Button is pressed
		if (this.navAction.equals("Raumverw")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumverwaltung", null, Bootstrap.serviceManager.getOrgaRoomTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// LiveTicker Button is pressed
		if (this.navAction.equals("liveticker")) {
			this.getInfoWindow("LiveTicker Bearbeitung momentan noch nicht implementiert!").setVisible(true);
		}
		
		// Anfrageverwaltung Button is pressed
		if (this.navAction.equals("Anfrageverw")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, Bootstrap.serviceManager.getOrgaRquestTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}