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
			this.infoWindow = new InfoDialog("<strong> Fehlermeldung 1:</strong><br> Momentan kann keine PDF erzeugt werden.<br><br><strong>Fehlermeldung 2:</strong><br>Es wurden keine Daten ausgewählt.<br><br><strong>Fehlermeldung 3:</strong><br> Es wurde kein Dozent ausgewählt.<br><br><strong>Fehlermeldung 4:</strong><br> Die Funktionalität momentan nicht ausführbar. Wenden Sie sich an Ihren Systemadministrator.");
		}
		return this.infoWindow;
	}
}
