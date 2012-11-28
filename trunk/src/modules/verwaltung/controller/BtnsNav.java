package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;

import services.Bootstrap;


public class BtnsNav implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
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
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Nutzerverwaltung", null, Bootstrap.serviceManager.getVerwNutzer(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Lehrstuhlverwaltung Button is pressed
		if (this.navAction.equals("Lehrstuhlverw")) {
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, Bootstrap.serviceManager.getVerwLehrstuhl(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Raumverwaltung Button is pressed
		if (this.navAction.equals("Raumverw")) {
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Raumverwaltung", null, Bootstrap.serviceManager.getVerwRaum(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// LiveTicker Button is pressed
		if (this.navAction.equals("LiveTicker")) {
			this.getInfoWindow("LiveTicker Bearbeitung momentan noch nicht implementiert!").setVisible(true);
		}
		
		// Anfrageverwaltung Button is pressed
		if (this.navAction.equals("Anfrageverw")) {
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, Bootstrap.serviceManager.getVerwAnfrage(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}