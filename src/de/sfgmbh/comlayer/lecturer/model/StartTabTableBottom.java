package de.sfgmbh.comlayer.lecturer.model;

import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Room allocation table model for the bottom table in the lecturer's start tab
 * 
 * @author hannes
 * @author christian
 * 
 */
public class StartTabTableBottom extends DefaultTableModel implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header_ = { "Bezeichnung", "Dozent", "Zeit", "Tag",
			"Semester", "Raum", "Status", "Hidden" };

	/**
	 * Creates a default model object and performs a default (initial) change
	 * action
	 */
	public StartTabTableBottom() {
		AppModel.getInstance().getRepositoryRoomAllocation().register(this);
		this.setColumnIdentifiers(header_);
		this.change("init");
	}

	/**
	 * Update the model<br>
	 * Depending on the variant and certain values which are currently present
	 * in the corresponding view components there are certain filters set. Based
	 * on these filters data is retrieved and the model is (re-)built.<br>
	 * 
	 * Supported variant strings are:<br>
	 * "init" and "select", all other strings fall back to the default change()
	 * behavior
	 * 
	 * @param variant
	 */
	public void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();
		IntfUser sessionUser = SessionManager.getInstance().getSession();

		if (sessionUser.getChair_() != null) {

			IntfChair sessionChair = sessionUser.getChair_();

			this.setRowCount(0);

			if (variant.equals("init")) {
				filter.put("chair", sessionChair.getAcronym());
				filter.put("status", "<alle>");
				filter.put("login", sessionUser.getLogin_());
				filter.put("semester", "<alle>");
				filter.put("course", "<alle>");

			} else if (variant.equals("select")) {
				int row = ViewManager.getInstance().getLecturerStartTab()
						.getTableCourseTop().getSelectedRow();
				if (row != -1) {
					IntfCourse selectedCourse = null;
					try {
						selectedCourse = (IntfCourse) ViewManager.getInstance()
								.getLecturerStartTabTableTop()
								.getValueAt(row, 6);
					} catch (Exception ex) {
						AppModel.getInstance()
								.getExceptionHandler()
								.setNewException(
										"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
												+ ex.toString(), "Fehler!");
					}
					filter.put("chair", sessionChair.getAcronym());
					filter.put("login", selectedCourse.getLecturer_()
							.getLogin_());
					filter.put("course", selectedCourse.getCourseAcronym_());

					// Update the model of related combo boxes
					// As here happens all model updated related to the table
					// and the combo box classes are reused for many tables this
					// happens here
					ActionListener a1 = ViewManager.getInstance()
							.getLecturerStartTab().getComboBoxCourse()
							.getActionListeners()[0];
					ActionListener a2 = ViewManager.getInstance()
							.getLecturerStartTab().getComboBoxLecturerBottom()
							.getActionListeners()[0];
					ViewManager.getInstance().getLecturerStartTab()
							.getComboBoxCourse().removeActionListener(a1);
					ViewManager.getInstance().getLecturerStartTab()
							.getComboBoxLecturerBottom()
							.removeActionListener(a2);
					ViewManager
							.getInstance()
							.getLecturerStartTab()
							.getComboBoxCourse()
							.setSelectedItem(selectedCourse.getCourseAcronym_());
					ViewManager
							.getInstance()
							.getLecturerStartTab()
							.getComboBoxLecturerBottom()
							.setSelectedItem(
									selectedCourse.getLecturer_().getlName_());
					ViewManager.getInstance().getLecturerStartTab()
							.getComboBoxCourse().addActionListener(a1);
					ViewManager.getInstance().getLecturerStartTab()
							.getComboBoxLecturerBottom().addActionListener(a2);

				}
			} else {
				filter.put("chair", sessionChair.getAcronym());
				filter.put("status", ViewManager.getInstance()
						.getLecturerStartTab().getComboBoxStatus()
						.getSelectedItem().toString());
				filter.put("lecturer", ViewManager.getInstance()
						.getLecturerStartTab().getComboBoxLecturerBottom()
						.getSelectedItem().toString());
				filter.put("semester", ViewManager.getInstance()
						.getLecturerStartTab().getComboBoxSemesterBottom()
						.getSelectedItem().toString());
				filter.put("course", ViewManager.getInstance()
						.getLecturerStartTab().getComboBoxCourse()
						.getSelectedItem().toString());
			}

			for (IntfRoomAllocation roomAllocation : AppModel.getInstance()
					.getRepositoryRoomAllocation().getByFilter(filter)) {
				try {
					Object[] row = {
							roomAllocation.getCourse_().getCourseAcronym_(),
							roomAllocation.getCourse_().getLecturer_()
									.getlName_(),
							ViewHelper.getTime(roomAllocation.getTime_()),
							ViewHelper.getDay(roomAllocation.getDay_()),
							roomAllocation.getSemester_(),
							roomAllocation.getRoom_().getRoomNumber_(),
							ViewHelper.getAllocationStatus(roomAllocation
									.getApproved_()), roomAllocation };
					this.addRow(row);

				} catch (Exception e) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler:<br />"
											+ e.toString(), "Fehler!");
				}
			}
		}
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

	/**
	 * disables edits on the table cells
	 * 
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
