package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;
import services.Bootstrap;

public class LehrstuhlTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
	public LehrstuhlTabBtnsControl() {
		this.navAction = "default";
	}
	public LehrstuhlTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Lehrstuhl hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getVerwLehrstuhlMaske().setVisible(true);
		}
		
		// Lehrstuhl bearbeiten Button is pressed
		if (this.navAction.equals("bearbeiten")) {
			Bootstrap.serviceManager.getVerwLehrstuhlMaske().setVisible(true);
		}
		
		// Lehrstuhl löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("Lehrstuhl wird gelöscht!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
