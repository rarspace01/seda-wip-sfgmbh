package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;
import services.Bootstrap;

public class RaumTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
	public RaumTabBtnsControl() {
		this.navAction = "default";
	}
	public RaumTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Raum hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getVerwRaumMaske().setVisible(true);
		}
		
		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("bearbeiten")) {
			Bootstrap.serviceManager.getVerwRaumMaske().setVisible(true);
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
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
