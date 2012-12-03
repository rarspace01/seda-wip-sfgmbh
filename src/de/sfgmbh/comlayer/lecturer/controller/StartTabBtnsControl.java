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

		// Hinzuf�gen button is pressed
		if (this.ctrlAction.equals("add")){
			Bootstrap.serviceManager.getLecturerCourseFrame().setVisible(true);
		}
		
		// Bearbeiten button is pressed
		if (this.ctrlAction.equals("edit")){
			this.getInfoWindow("Hier erscheint die Bearbieten-Maske!").setVisible(true);
		}
		
		// Ver�ffentlichen button is pressed
		if (this.ctrlAction.equals("publish")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Belegung �ffentlich zu schalten!").setVisible(true);
		}
		
		// Raumanfrage button is pressed
		if (this.ctrlAction.equals("roomrequest")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(true);
		}
		
		// Zur�ckziehen button is pressed
		if (this.ctrlAction.equals("back")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Antrag zur�ckzuziehen!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
