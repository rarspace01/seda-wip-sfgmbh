package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.DefaultTableModel;

public class RoomtableTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"08:00-10:00 Uhr", "","", "SEDA-WIP-B in WP3/01.004", "", "SEDA-DMS-B in WP3/01.004"},
			{"10:00-12:00 Uhr", "", "SEDA-WIP-B in WP3/01.004", "", "", "ISDL-ISS1-M in WP3/01.004"},
			{"12:00-14:00 Uhr", "", "", "ISDL-ISS1-M �bung in WP3/01.004", "",""},
			{"14:00-16:00 Uhr", "SEDA-EbIS-1 in WP3/04.004", "","", "", ""},
			{"16:00-18:00 Uhr", "","", "", "", ""},
			{"18:00-20:00 Uhr", "", "", "", "SEDA-EbIS-1 �bung in WP3/01.004", ""},
			
			{null, null, null, null, null, null, null, null, null},
		};
	private String[] preFillHeader = {"Uhrzeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	
	
	public RoomtableTable() {
		this.setDataVector(preFill, preFillHeader);
	}
}

