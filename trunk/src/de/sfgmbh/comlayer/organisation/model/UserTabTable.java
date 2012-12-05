package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.*;

public class UserTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"ba001", "test1@uni-bamberg.de", "Dozent", "SEDA", "01.01.1999"},
			{"ba002", "test2@uni-bamberg.de", "Verwaltung", null, "01.01.1999"},
		};
	private String[] preFillHeader = {"Kennung", "E-Mail", "Benutzerklasse", "Lehrstuhl", "Letzer Login"};
	
	
	public UserTabTable() {
		this.setDataVector(preFill, preFillHeader);
	}
}
