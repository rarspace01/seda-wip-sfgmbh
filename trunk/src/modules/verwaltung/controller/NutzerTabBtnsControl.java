package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;
import services.Bootstrap;

public class NutzerTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
	public NutzerTabBtnsControl() {
		this.navAction = "default";
	}
	public NutzerTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Benutzer hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getVerwNutzerMaske().setVisible(true);
		}
		
		// Benutzer bearbeiten Button is pressed
		if (this.navAction.equals("bearbeiten")) {
			Bootstrap.serviceManager.getVerwNutzerMaske().setVisible(true);
		}
		
		// Benutzer löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("Benutzer wird gelöscht!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
