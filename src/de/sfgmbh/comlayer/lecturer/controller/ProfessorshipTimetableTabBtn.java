package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;


public class ProfessorshipTimetableTabBtn implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	public ProfessorshipTimetableTabBtn() {
		this.navAction = "default";
	}

	public ProfessorshipTimetableTabBtn(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (this.navAction.equals("createpdf")) {
			//ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
			System.out.println("got pdf trigger");
		}
		
		//this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("<strong> Fehlermeldung 1:</strong><br> Momentan kann keine PDF erzeugt werden.<br><br><strong>Fehlermeldung 2:</strong><br>Es wurden keine Daten ausgew�hlt.<br><br><strong>Fehlermeldung 3:</strong><br> Es wurde kein Dozent ausgew�hlt.<br><br><strong>Fehlermeldung 4:</strong><br> Die Funktionalit�t momentan nicht ausf�hrbar. Wenden Sie sich an Ihren Systemadministrator.");
		}
		return this.infoWindow;
	}
}
