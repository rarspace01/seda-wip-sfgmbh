package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


public class CourseFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public CourseFrameBtns() {
		this.ctrlAction = "default";
	}
	public CourseFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getLecturerCourseFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Die Speicherung Ihrer eingegeben Daten wurde abgebrochen!<br> Sie haben keinen 1. Lehrstuhl, 2. Dozenten ausgewählt. 3. Ihre Bezeichnung erhält unerlaubte Zeichen. 4. Ihre Beschreibung erhält nicht genug Zeichen, sie muss mindestens 300 Zeichen erhalten. 5. Sie haben kein Semester, 6. keine erwartete Teilnehmerzahl ausgewählt. 7. Die Lehrveranstaltung existiert bereits; Überprüfen Sie die Angaben oder ändern Sie dei gewünschte Lehrveranstaltung über die Selektion in der Liste und die Funktion bearbeiten.").setVisible(true); // Titel: "Fehler beim Speichern erfolgt"
			// Bootstrap.serviceManager.getLecturerCourseFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
