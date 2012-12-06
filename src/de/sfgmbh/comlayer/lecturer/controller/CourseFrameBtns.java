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
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Die Speicherung Ihrer eingegeben Daten wurde abgebrochen:<br> " +
					"1. Sie haben keinen Lehrstuhl ausgewählt <br> 2. Sie haben keinen Dozenten ausgewählt. <br> 3. Ihre Bezeichnung erhält unerlaubte Zeichen. <br>" +
					"4. Ihre Beschreibung erhält nicht genug Zeichen, sie muss mindestens 300 Zeichen erhalten. <br>5. Sie haben kein Semester, <br>" +
					"6. keine erwartete Teilnehmerzahl ausgewählt. <br>7. Die Lehrveranstaltung existiert bereits. <br> Überprüfen Sie die Angaben oder ändern Sie " +
					"die gewünschte Lehrveranstaltung über die Selektion in der Liste und die Funktion bearbeiten.").setVisible(true); 
			// Bootstrap.serviceManager.getLecturerCourseFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
