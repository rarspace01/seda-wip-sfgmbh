package de.sfgmbh.comlayer.core.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Table model for the very first table users see after program start with all
 * courses, their times and rooms (RoomAllocation objects)
 * 
 * @author mario
 * 
 */
public class BaseTableMain extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header_ = { "Bez.", "Name", "Art", "Dozent", "Tag",
			"Uhrzeit", "Raum", "Sem.", "SWS", "Hidden" };

	/**
	 * Creates an initial table model object
	 */
	public BaseTableMain() {
		AppModel.getInstance().getRepositoryRoomAllocation().register(this);
		this.setColumnIdentifiers(header_);
		this.change("init");
	}

	/**
	 * Performs an action depending on the submitted variant to change and
	 * update the table model
	 * 
	 * @param variant
	 */
	public void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();

		this.setRowCount(0);

		if (variant.equals("init")) {
			filter.put("chair", "<alle>");
			filter.put("course", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
		} else {
			filter.put("chair", ViewManager.getInstance().getCoreBaseTab()
					.getComboBoxChairFilter().getSelectedItem().toString());
			filter.put("course", ViewManager.getInstance().getCoreBaseTab()
					.getComboBoxOrgaFilter().getSelectedItem().toString());
			filter.put("lecturer", ViewManager.getInstance().getCoreBaseTab()
					.getComboBoxLecturerFilter().getSelectedItem().toString());
			filter.put("semester", ViewManager.getInstance().getCoreBaseTab()
					.getComboBoxSemesterFilter().getSelectedItem().toString());
		}

		for (IntfRoomAllocation roomAllocation : AppModel.getInstance()
				.getRepositoryRoomAllocation().getByFilter(filter)) {
			if (roomAllocation.isPublic()) {
				try {
					Object[] row = {
							roomAllocation.getCourse_().getCourseAcronym_(),
							roomAllocation.getCourse_().getCourseName_(),
							roomAllocation.getCourse_().getCourseKind_(),
							roomAllocation.getCourse_().getLecturer_()
									.getlName_(),
							ViewHelper.getDay(roomAllocation.getDay_()),
							ViewHelper.getTime(roomAllocation.getTime_()),
							roomAllocation.getRoom_().getRoomNumber_(),
							roomAllocation.getSemester_(),
							roomAllocation.getCourse_().getSws_(),
							roomAllocation };
					this.addRow(row);

				} catch (Exception e) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />"
											+ e.toString(), "Fehler!");
				}
			}
		}
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
		this.change("update");
	}
}
