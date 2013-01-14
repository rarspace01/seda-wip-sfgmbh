package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;



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
			ViewManager.getInstance().getLecturerRoomRequestFrame().setVisible(false);
		}
		
		// Los button is pressed; normalerweise sollte das Fenster geschlossen werden, im aktuellen Prototypen Status kommt eine Fehlermeldung
		if (this.ctrlAction.equals("go")){
			this.getInfoWindow("<strong>Fehlermeldung1:</strong><br>Ihre Raumanfrage mit Ihrem gew�nschten Raum konnte nicht gesendet werden:<br> 1. Sie haben keinen Raum ausgew�hlt. " +
					"<br> 2. Sie haben keinen Tag ausgew�hlt. <br> 3. Sie haben keine Zeit ausgew�hlt. <br>  <br><strong>Fehlermeldung2:</strong><br>" +
					" Ihre Raumanfrage mit Ihren gew�nschten Kriterien konnte nicht gesendet werden: <br> 1. Sie haben keinen Tag ausgew�hlt. <br> 2. Sie haben keine Zeit ausgew�hlt.<br>" +
					" 3. Sie haben kein Geb�ude ausgew�hlt. <br> 4. Sie haben keine gew�nschte Platzanzahl ausgew�hlt.<br> 5. Die Anzahl der PC-Arbeitspl�tze entspricht nicht Ihren Vorgaben zur Platzanzahl.").setVisible(true);
			//ServiceManager.getInstance().getLecturerRoomRequestFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
