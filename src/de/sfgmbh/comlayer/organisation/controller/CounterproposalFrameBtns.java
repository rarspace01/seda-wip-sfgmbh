package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;

import de.sfgmbh.applayer.core.controller.Bootstrap;

public class CounterproposalFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public CounterproposalFrameBtns() {
		this.ctrlAction = "default";
	}
	public CounterproposalFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getOrgaCounterproposalFrame().setVisible(false);
		}
		
		// Senden button is pressed
		if (this.ctrlAction.equals("send")){
			Bootstrap.serviceManager.getOrgaCounterproposalFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
