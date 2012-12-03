package comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;

import applayer.core.controller.Bootstrap;


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
			this.getInfoWindow("Anfrage wird abgelehnt!").setVisible(true);
		}
		
		// Freigeben Button is pressed
		if (this.navAction.equals("publish")) {
			this.getInfoWindow("Anfrage wird freigegeben!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
