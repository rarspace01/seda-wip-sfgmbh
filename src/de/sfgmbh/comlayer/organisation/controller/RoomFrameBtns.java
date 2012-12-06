package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


public class RoomFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public RoomFrameBtns() {
		this.ctrlAction = "default";
	}
	public RoomFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(false);
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Der Raum konnte nicht hinzugefügt werden:<br>1. Die Raumnummer ist bereits im System vorhanden." +
					"<br>2. Einer der von Ihnen eingegebenen Werte ist syntaktisch inkorrekt.<br>3. Der von Ihnen eingetragene Raum existiert nicht im System. " +
					"Sollte er doch existieren, kontaktieren Sie bitte die Hausverwaltung.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
