package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class RoomRequestFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public RoomRequestFrameBtns() {
		this.ctrlAction = "default";
	}
	public RoomRequestFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(false);
		}
		
		// Los button is pressed; normalerweise sollte das Fenster geschlossen werden, im aktuellen Prototypen Status kommt eine Fehlermeldung
		if (this.ctrlAction.equals("go")){
			this.getInfoWindow("<strong>Fehlermeldung1:</strong><br>Ihre Raumanfrage mit Ihrem gewünschten Raum konnte nicht gesendet werden:<br> 1. Sie haben keinen Raum ausgewählt. " +
					"<br> 2. Sie haben keinen Tag ausgewählt. <br> 3. Sie haben keine Zeit ausgewählt. <br>  <br><strong>Fehlermeldung2:</strong><br>" +
					" Ihre Raumanfrage mit Ihren gewünschten Kriterien konnte nicht gesendet werden: <br> 1. Sie haben keinen Tag ausgewählt. <br> 2. Sie haben keine Zeit ausgewählt.<br>" +
					" 3. Sie haben kein Gebäude ausgewählt. <br> 4. Sie haben keine gewünschte Platzanzahl ausgewählt.<br> 5. Die Anzahl der PC-Arbeitsplätze entspricht nicht Ihren Vorgaben zur Platzanzahl.").setVisible(true);
			//Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
