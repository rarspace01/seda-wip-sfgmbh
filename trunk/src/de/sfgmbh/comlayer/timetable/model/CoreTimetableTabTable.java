package de.sfgmbh.comlayer.timetable.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * TableModel for the
 * 
 * @author denis
 * 
 */
public class CoreTimetableTabTable extends DefaultTableModel implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill_ = {

	};
	private String[] preFillHeader = { "Uhrzeit", "Montag", "Dienstag",
			"Mittwoch", "Donnerstag", "Freitag" };

	/**
	 * Create the model
	 */
	public CoreTimetableTabTable() {
		// setting the table header
		this.setDataVector(preFill_, preFillHeader);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
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

		// remove all rows
		this.setRowCount(0);

		// get all rooms from db
		ViewManager.getInstance().getCoreTimetableTab().reloadRoomTable();

	}
}
