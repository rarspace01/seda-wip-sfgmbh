package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;


public class TimetableTabBtn implements ActionListener {
	
	protected InfoDialog infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("<b>Fehlermeldung1:</b><br> Momentan kann keine PDF erzeugt werden.<b><br><br>Fehlermeldung:2</b><br> Es wurden keine Daten ausgewählt.<br><br><b>Fehlermeldung3:</b><br> Es wurde kein Dozent ausgewählt.<br><br><b>Fehlermeldung4:</b><br> Die Funktionalität momentan nicht ausführbar. Wenden Sie sich bitte an Ihren Systemadministrator!");
		}
		return this.infoWindow;
	}
}
