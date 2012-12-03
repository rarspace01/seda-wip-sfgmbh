package comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;


public class ProfessorshipTimetableTabCmbbox implements ActionListener {
	
	protected InfoDialog infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("Jetzt sollte der Stundenplan je Dozent angepasst werden!");
		}
		return this.infoWindow;
	}
}
