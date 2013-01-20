package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;

public class TimetableTabTable extends DefaultTableModel implements IntfAppObserver  {

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
	
	
	public TimetableTabTable() {
		this.setDataVector(preFill, preFillHeader);
	}
	
	/**
	 * disables edits on the table cells
	 * 
	 * @author denis
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void change() {

		ViewManager.getInstance().getLecturerTimetableTab().reloadPlan();
		
	}
}
