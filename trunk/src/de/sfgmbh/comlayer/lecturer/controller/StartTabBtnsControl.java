package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.lecturer.views.CourseDialog;
import de.sfgmbh.comlayer.lecturer.views.RoomRequestDialog;
import de.sfgmbh.comlayer.lecturer.views.StartTab;

/**
 * Controller for the lecturer tab buttons on the right
 * 
 * @author hannes
 * @author christian
 * 
 */
public class StartTabBtnsControl implements ActionListener,
		IntfComDialogObserver {

	private String ctrlAction_;
	private IntfCourse delCourse_;
	private IntfRoomAllocation revokeAllocation_;
	private IntfCtrlStartTab cntrlStartTab_ = new CtrlStartTab();

	/**
	 * Create the action listener
	 */
	public StartTabBtnsControl() {
		this.ctrlAction_ = "default";
	}

	/**
	 * Create the action listener based on an action string Supported action
	 * strings are:<br>
	 * "add", "edit", "delete", "roomrequest", "publish" and "back"
	 * 
	 * @param action
	 */
	public StartTabBtnsControl(String action) {
		this.ctrlAction_ = action;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		StartTab startTab = ViewManager.getInstance().getLecturerStartTab();
		JTable topTable = ViewManager.getInstance().getLecturerStartTab()
				.getTableCourseTop();
		JTable bottomTable = ViewManager.getInstance().getLecturerStartTab()
				.getTableRoomAllocationBottom();
		TableModel modelTopTable = ViewManager.getInstance()
				.getLecturerStartTabTableTop();
		TableModel modelTableBottom = ViewManager.getInstance()
				.getLecturerStartTabTableBottom();
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();

		// Add button is pressed
		if (this.ctrlAction_.equals("add")) {
			CourseDialog newCourse = new CourseDialog();
			newCourse.setVisible(true);
		}

		// Edit button is pressed
		if (this.ctrlAction_.equals("edit")) {
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Veranstaltung wählen.",
						"Achtung!");
			} else {
				try {
					row = startTab.getRowSorterTop()
							.convertRowIndexToModel(row);
					IntfCourse editCourse = (IntfCourse) modelTopTable
							.getValueAt(row, 6);
					CourseDialog editCourseDialog = new CourseDialog("edit",
							editCourse);
					editCourseDialog.setVisible(true);
				} catch (Exception ex) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
											+ ex.toString(), "Fehler!");
				}
			}
		}

		// Delete button is pressed
		if (this.ctrlAction_.equals("delete")) {
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Veranstaltung wählen.",
						"Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				IntfCourse delCourse = (IntfCourse) modelTopTable.getValueAt(
						row, 6);
				QuestionDialog dialog = new QuestionDialog(
						"Wollen Sie die gewählte Veranstaltung wirklich löschen?",
						"Achtung!");
				this.delCourse_ = delCourse;
				dialog.register(this);
				dialog.setVisible(true);
				return;
			}
		}

		// Publish button is pressed
		if (this.ctrlAction_.equals("publish")) {
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Veranstaltung wählen.",
						"Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				IntfCourse publishCourse = (IntfCourse) modelTopTable
						.getValueAt(row, 6);
				if (!publishCourse.isLecturerEnabled_()) {
					publishCourse.setLecturerEnabled_(true);
					if (this.cntrlStartTab_.saveCourse(publishCourse)) {
						exceptionHandler.setNewException(
								"Veranstaltung freigegebn.", "Erfolg!",
								"success");
					}
				} else {
					exceptionHandler.setNewException(
							"Die Veranstaltung ist bereits freigeben!",
							"Achtung!", "info");
				}
			}
		}

		// Request room button is pressed
		if (this.ctrlAction_.equals("roomrequest")) {
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Veranstaltung wählen.",
						"Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				IntfCourse requestCourse = (IntfCourse) modelTopTable
						.getValueAt(row, 6);
				RoomRequestDialog roomRequestDialog = new RoomRequestDialog(
						requestCourse);
				roomRequestDialog.setVisible(true);
			}
		}

		// Revoke button is pressed
		if (this.ctrlAction_.equals("back")) {
			// Get the room allocation and use it
			int row = bottomTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Raumanfrage wählen.",
						"Achtung!");
			} else {
				row = startTab.getRowSorterBottom().convertRowIndexToModel(row);
				IntfRoomAllocation revokeAllocation = (IntfRoomAllocation) modelTableBottom
						.getValueAt(row, 7);
				if (revokeAllocation.getApproved_().equals("denied")) {
					exceptionHandler
							.setNewException(
									"Diese Raumbelegung ist bereits zurückgezogen bzw. abgelehnt.",
									"Achtung!", "info");
					return;
				}
				QuestionDialog dialog = new QuestionDialog(
						"Wollen Sie die gewählte Raumbelegung wirklich zurückziehen?",
						"Achtung!");
				this.revokeAllocation_ = revokeAllocation;
				dialog.register(this);
				dialog.setVisible(true);
				return;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver#answered(java
	 * .lang.String)
	 */
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.delCourse_ != null) {
				if (this.cntrlStartTab_.deleteCourse(this.delCourse_)) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Die Veranstaltung wurde erfolgreich gelöscht.",
									"Erfolg!", "success");
				}
			}
			if (this.revokeAllocation_ != null) {
				if (this.cntrlStartTab_
						.revokeRoomAllocation(this.revokeAllocation_)) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Die Raumbelegung wurde zurückgezogen.",
									"Erfolg!", "success");
				}
			}
		} else if (answer.equals("no")) {
			this.delCourse_ = null;
			this.revokeAllocation_ = null;
		}
	}

}
