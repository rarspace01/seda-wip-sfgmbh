package de.sfgmbh.comlayer.lecturer.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Table model for the chair timetable table
 * 
 * @author denis
 * 
 */
public class ChairTimetableTabTable extends DefaultTableModel implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill_ = {};
	private String[] preFillHeader_ = { "Uhrzeit", "Montag", "Dienstag",
			"Mittwoch", "Donnerstag", "Freitag" };

	/**
	 * Create the model
	 */
	public ChairTimetableTabTable() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {

		ViewManager.getInstance().getChairTimetableTab().reloadPlan();

	}

}
