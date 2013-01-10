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
			Bootstrap.serviceManager.getLecturerCourseEditFrame().setVisible(true);
			//this.getInfoWindow("<strong>Fehlermeldung:</strong> <br>Sie haben keine Lehveranstaltung selektiert! <br> " +
			//		"Wenn Sie keine Fehlermeldung erhalten, gelangen sofort zu einer Bearbeiten-Maske wie beim Hinzufügen von Lehrveranstaltung.").setVisible(true);
		}
		
		// Löschen button is pressed
		if (this.ctrlAction.equals("delete")){
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Sie haben keine Lehrveranstaltung selektiert!").setVisible(true);
		}
		
		// Veröffentlichen button is pressed
		if (this.ctrlAction.equals("publish")){
			this.getInfoWindow("<strong>Fehlermeldung:</strong> <br>Sie haben keine Lehrveranstaltung selektiert!").setVisible(true);
		}
		
		// Raumanfrage button is pressed
		if (this.ctrlAction.equals("roomrequest")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(true);
		}
		
		// Zurückziehen button is pressed
		if (this.ctrlAction.equals("back")){
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Sie haben keine Lehrveranstaltung selektiert!").setVisible(true); // Titel: "blaa"
		}
		
		// Fehlermeldung button is pressed
		if (this.ctrlAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Es konnte keine Verbindung zur Datenbank aufgebaut werden. Daher können keine Lehrveranstaltungen und/oder Raumanfragen angezeigt werden.").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
	public InfoDialog getInfoWindow(String msg, String title) {
		this.infoWindow = new InfoDialog(msg, title);
		return this.infoWindow;
	}
}
