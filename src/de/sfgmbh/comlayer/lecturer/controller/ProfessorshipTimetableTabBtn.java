package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;


public class ProfessorshipTimetableTabBtn implements ActionListener {
	
	protected InfoDialog infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("<strong> Fehlermeldung:</strong><br>Leider sind keine Lehrveranstaltung für den Lehrstuhl eingetragen. <p> Oder hier öffnet sich ein Tab mit dem Lehrstuhlstundenplan.");
		}
		return this.infoWindow;
	}
}
