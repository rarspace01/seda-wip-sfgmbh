package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.*;

public class StartTabTableTop extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"SEDA-WIP-B", "Benker", "WS1213", "4", "30", "2", "nein", "nein"},
		};
	private String[] preFillHeader = {"Bezeichnung", "Dozent", "Semester", "SWS", "Erw. Teilnehmer", "Termine", "Raumanfrage freigegeben", "\u00D6ffentlich"};
	
	
	public StartTabTableTop() {
		this.setDataVector(preFill, preFillHeader);
	}
}
