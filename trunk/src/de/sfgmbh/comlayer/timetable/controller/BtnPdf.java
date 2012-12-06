package de.sfgmbh.comlayer.timetable.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;



public class BtnPdf implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public BtnPdf() {
		this.navAction = "default";
	}
	public BtnPdf(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Pdf Button is pressed
		if (this.navAction.equals("pdfCreate")) {
			this.getInfoWindow("<b>Fehlermeldung 1:</b><br> Momentan kann keine PDF erzeugt werden.<b><br><br>Fehlermeldung 2:</b><br> Es wurden keine Daten ausgewählt.<br><br><b>Fehlermeldung 3:</b><br> Es wurde kein Dozent ausgewählt.<br><br><b>Fehlermeldung 4:</b><br> Die Funktionalität ist momentan nicht ausführbar. Wenden Sie sich bitte an Ihren Systemadministrator!").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
