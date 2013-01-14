package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;


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
			ServiceManager.getInstance().getLecturerCourseFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Die Speicherung Ihrer eingegeben Daten wurde abgebrochen:<br> " +
					"1. Sie haben keinen Lehrstuhl ausgew�hlt <br> 2. Sie haben keinen Dozenten ausgew�hlt. <br> 3. Ihre Bezeichnung erh�lt unerlaubte Zeichen. <br>" +
					"4. Ihre Beschreibung erh�lt nicht genug Zeichen, sie muss mindestens 300 Zeichen erhalten. <br>5. Sie haben kein Semester ausgew�hlt. <br>" +
					"6. keine erwartete Teilnehmerzahl ausgew�hlt. <br>7. Die Lehrveranstaltung existiert bereits. <br> �berpr�fen Sie die Angaben oder �ndern Sie " +
					"die gew�nschte Lehrveranstaltung �ber die Selektion in der Liste und die Funktion bearbeiten.").setVisible(true); 
			// ServiceManager.getInstance().getLecturerCourseFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
