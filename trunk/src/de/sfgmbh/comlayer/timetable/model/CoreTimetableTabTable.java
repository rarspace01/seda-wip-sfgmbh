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

	public CoreTimetableTabTable() {
		// setting the table header
		this.setDataVector(preFill_, preFillHeader);
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

		// remove all rows
		this.setRowCount(0);

		// get all rooms from db
		ViewManager.getInstance().getCoreTimetableTab().reloadRoomTable();

	}
}
