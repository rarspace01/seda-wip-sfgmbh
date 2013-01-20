package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;

public class TimetableTabTable extends DefaultTableModel implements IntfAppObserver  {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			
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
