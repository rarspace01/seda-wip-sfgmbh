package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.organisation.views.RequestTab;

/**
 * Table model for the room allocation table for the organization
 * 
 * @author hannes
 * 
 */
public class RequestTabTable extends DefaultTableModel implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header_ = { "Dozent", "Veranstaltung", "Tag", "Zeit",
			"Raum", "Semester", "Status", "Konflikt", "Hidden" };

	/**
	 * Creates an initial table model object
	 */
	public RequestTabTable() {
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
			filter.put("status", "<alle>");
			filter.put("lecturer", "<alle>");
			filter.put("semester", "<alle>");
			filter.put("room", "<alle>");
		} else {
			RequestTab requestTab = ViewManager.getInstance()
					.getOrgaRquestTab();

			filter.put("chair", requestTab.getComboBoxChair().getSelectedItem()
					.toString());
			filter.put("status", requestTab.getComboBoxStatus()
					.getSelectedItem().toString());
			filter.put("lecturer", requestTab.getComboBoxLecturer()
					.getSelectedItem().toString());
			filter.put("semester", requestTab.getComboBoxSemester()
					.getSelectedItem().toString());
			filter.put("room", requestTab.getTxtRoom().getText());
		}

		for (IntfRoomAllocation roomAllocation : AppModel.getInstance()
				.getRepositoryRoomAllocation().getByFilter(filter)) {

			// Set some speical Strings
			String conflict = "-";
			if (roomAllocation.isConflicting_()) {
				conflict = "JA!";
			}
			String status;
			status = ViewHelper.getAllocationStatus(roomAllocation
					.getApproved_());
			if (status.equals("wartend")) {
				// UTF-8 Special invisible character to force ordering
				// Character code: U+200D
				status = "wartend";
			}

			try {
				Object[] row = {
						roomAllocation.getCourse_().getLecturer_().getlName_(),
						roomAllocation.getCourse_().getCourseAcronym_(),
						ViewHelper.getDay(roomAllocation.getDay_()),
						ViewHelper.getTime(roomAllocation.getTime_()),
						roomAllocation.getRoom_().getRoomNumber_(),
						roomAllocation.getSemester_(), status, conflict,
						roomAllocation };
				this.addRow(row);

			} catch (Exception e) {
				AppModel.getInstance()
						.getExceptionHandler()
						.setNewException(
								"Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler RequestTabTable-01:<br />"
										+ e.toString(), "Fehler!");
			}
		}
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
		this.change("update");
	}
}
