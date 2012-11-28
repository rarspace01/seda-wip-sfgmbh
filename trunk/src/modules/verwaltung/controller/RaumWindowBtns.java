package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;
import services.Bootstrap;

public class RaumWindowBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoWindow infoWindow;
	
	
	public RaumWindowBtns() {
		this.ctrlAction = "default";
	}
	public RaumWindowBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("abbr")){
			Bootstrap.serviceManager.getVerwRaumMaske().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("speichern")){
			Bootstrap.serviceManager.getVerwRaumMaske().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
