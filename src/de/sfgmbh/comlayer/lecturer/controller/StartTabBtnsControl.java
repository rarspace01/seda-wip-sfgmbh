package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


public class StartTabBtnsControl implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public StartTabBtnsControl() {
		this.ctrlAction = "default";
	}
	public StartTabBtnsControl(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Hinzufügen button is pressed
		if (this.ctrlAction.equals("add")){
			Bootstrap.serviceManager.getLecturerCourseFrame().setVisible(true);
		}
		
		// Bearbeiten button is pressed
		if (this.ctrlAction.equals("edit")){
			this.getInfoWindow("Hier erscheint die Bearbieten-Maske!").setVisible(true);
		}
		
		// Veröffentlichen button is pressed
		if (this.ctrlAction.equals("publish")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Belegung öffentlich zu schalten!").setVisible(true);
		}
		
		// Raumanfrage button is pressed
		if (this.ctrlAction.equals("roomrequest")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(true);
		}
		
		// Zurückziehen button is pressed
		if (this.ctrlAction.equals("back")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Antrag zurückzuziehen!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
