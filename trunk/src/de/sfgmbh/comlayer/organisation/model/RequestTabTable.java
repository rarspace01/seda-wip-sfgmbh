package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.*;

public class RequestTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"Benker", "SEDA", "Mo", "12.00 - 14.00", "R001", "WS 12/13", "wartend"},
			{"Sinz", "SEDA", "Di", "8.00 - 10.00", "R002", "WS 12/13", "freigegeben"},
		};
	private String[] preFillHeader = {"Dozent", "Lehrstuhl", "Tag", "Zeit", "Raum", "Semester", "Status"};
	
	
	public RequestTabTable() {
		this.setDataVector(preFill, preFillHeader);
	}
}
