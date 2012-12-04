package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


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
			this.getInfoWindow("<b>Der Konflikt konnte nicht gel�st werden:</b><br><br>1. Sie haben keinen Dozenten ausgew�hlt.<br>2. Sie haben keine Lehrveranstaltung ausgew�hlt.<br>3. Sie haben keinen Raum ausgew�hlt.<br>4. Sie haben keine Zeit ausgew�hlt.<br>5. Ihre Nachricht an den Dozenten ist zu lang.<br>6. Ihre Nachricht an den Dozenten ist zu kurz.").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
