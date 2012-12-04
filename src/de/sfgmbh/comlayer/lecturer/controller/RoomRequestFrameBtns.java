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
			this.getInfoWindow("<strong> Fehlermeldungen 1:</strong><br> Ihre Raumanfrage mit Ihrem gew�nschten Raum konnte nicht gesendet werden:<br> Sie haben keinen <br> 1.Raum, 2. Tag, 3. keine Zeit <br> ausgew�hlt! <br> <strong> Fehlermeldungen 1:</strong> <br>Ihre Raumanfrage mit Ihren gew�nschten Kriterien konnte nicht gesendet werden: <br> Sie haben <br>1. keinen Tag, 2. keine Zeit, 3. kein Geb�ude, 4. keine gew�nschte Platzanzahl <br>ausgew�hlt.<br> 5. Die Anzahl der PC-Arbeitspl�tze entspricht nicht Ihren Vorgaben zur Platzanzahl").setVisible(true);
			//Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
