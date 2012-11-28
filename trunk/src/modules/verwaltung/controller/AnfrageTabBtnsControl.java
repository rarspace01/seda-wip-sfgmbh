package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;
import services.Bootstrap;

public class AnfrageTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
	public AnfrageTabBtnsControl() {
		this.navAction = "default";
	}
	public AnfrageTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Konfliktlösen Button is pressed
		if (this.navAction.equals("Konflikt")) {
			Bootstrap.serviceManager.getVerwGegenvorschlagMaske().setVisible(true);
		}
		
		// Ablehnen Button is pressed
		if (this.navAction.equals("ablehnen")) {
			this.getInfoWindow("Anfrage wird abgelehnt!").setVisible(true);
		}
		
		// Freigeben Button is pressed
		if (this.navAction.equals("freigeben")) {
			this.getInfoWindow("Anfrage wird freigegeben!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
