package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.DefaultTableModel;

/**
 * Table model for the professorship timetable tab table
 * @author denis
 *
 */
public class ProfessorshipTimetableTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
			private Object[][] preFill_ = {
				};
			private String[] preFillHeader_ = {"Uhrzeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	
	
	/**
	 * Create the model
	 */
	public ProfessorshipTimetableTabTable() {
		this.setDataVector(preFill_, preFillHeader_);
	}
	
	/**
	 * disables edits on the table cells
	 * 
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
