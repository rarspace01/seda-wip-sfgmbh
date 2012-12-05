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
			this.infoWindow = new InfoDialog("<strong> Fehlermeldung:</strong><br>1. Momentan kann keine PDF erzeugt werden.<br>2. Es wurden keine Daten ausgewählt.<br>3. Es wurde kein Dozent ausgewählt.<br>4. Die Funktionalität momentan nicht ausführbar. Wenden Sie sich an Ihren Systemadministrator");
		}
		return this.infoWindow;
	}
}
