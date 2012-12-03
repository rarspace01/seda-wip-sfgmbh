package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.*;

public class StartTabTableBottom extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"SEDA-WIP-B", "Benker", "WS1213", "Do.", "12:00 - 14:00", "WP3/02.001", "abgelehnt"},
			{"SEDA-WIP-B", "Benker", "WS1213", "Mo.", "14:00 - 16:00", "WP3/04.002", "wartend"},
		};
	private String[] preFillHeader = {"Bezeichnung", "Dozent", "Semester", "Tag", "Zeit", "Raum", "status"};
	
	
	public StartTabTableBottom() {
		this.setDataVector(preFill, preFillHeader);
	}
}
