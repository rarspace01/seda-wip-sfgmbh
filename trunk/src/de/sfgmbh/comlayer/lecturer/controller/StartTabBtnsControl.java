package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
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
public class StartTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
	private String ctrlAction;
	private Course delCourse;
	private RoomAllocation revokeAllocation;
	private CtrlStartTab cntrlStartTab = new CtrlStartTab();
	
	/**
	 * Create the action listener
	 */
	public StartTabBtnsControl() {
		this.ctrlAction = "default";
	}
	/**
	 * Create the action listener based on an action string
	 * @param action
	 */
	public StartTabBtnsControl(String action) {
		this.ctrlAction = action;
	}
	
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
		if (this.ctrlAction.equals("add")){
			CourseDialog newCourse = new CourseDialog();
			newCourse.setVisible(true);
		}
		
		// Edit button is pressed
		if (this.ctrlAction.equals("edit")){
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Veranstaltung wählen.", "Achtung!");
			} else {
				try {
					row = startTab.getRowSorterTop().convertRowIndexToModel(row);
					Course editCourse = (Course) modelTopTable.getValueAt(row, 6);
					CourseDialog editCourseDialog = new CourseDialog("edit", editCourse);
					editCourseDialog.setVisible(true);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Delete button is pressed
		if (this.ctrlAction.equals("delete")){
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Veranstaltung wählen.", "Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				Course delCourse = (Course) modelTopTable.getValueAt(row, 6);
				QuestionDialog dialog = new QuestionDialog("Wollen Sie die gewählte Veranstaltung wirklich löschen?", "Achtung!");
				this.delCourse = delCourse;
				dialog.register(this);
				dialog.setVisible(true);
				return;
			}
		}
		
		// Publish button is pressed
		if (this.ctrlAction.equals("publish")){
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Veranstaltung wählen.", "Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				Course publishCourse = (Course) modelTopTable.getValueAt(row, 6);
				if (!publishCourse.isLecturerEnabled_()) {
					publishCourse.setLecturerEnabled_(true);
					if (this.cntrlStartTab.saveCourse(publishCourse)){
						exceptionHandler.setNewException("Veranstaltung freigegebn.", "Erfolg!", "success");
					}
				} else {
					exceptionHandler.setNewException("Die Veranstaltung ist bereits freigeben!", "Achtung!", "info");
				}
			}
		}
		
		// Request room button is pressed
		if (this.ctrlAction.equals("roomrequest")){
			// Get course and use it
			int row = topTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Veranstaltung wählen.", "Achtung!");
			} else {
				row = startTab.getRowSorterTop().convertRowIndexToModel(row);
				Course requestCourse = (Course) modelTopTable.getValueAt(row, 6);
				RoomRequestDialog roomRequestDialog = new RoomRequestDialog(requestCourse);
				roomRequestDialog.setVisible(true);
			}
		}
		
		// Revoke button is pressed
		if (this.ctrlAction.equals("back")){
			// Get the room allocation and use it
			int row = bottomTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Raumanfrage wählen.", "Achtung!");
			} else {
				row = startTab.getRowSorterBottom().convertRowIndexToModel(row);
				RoomAllocation revokeAllocation = (RoomAllocation) modelTableBottom.getValueAt(row, 7);
				if (revokeAllocation.getApproved_().equals("denied")) {
					exceptionHandler.setNewException("Diese Raumbelegung ist bereits zurückgezogen bzw. abgelehnt.", "Achtung!", "info");
					return;
				}
				QuestionDialog dialog = new QuestionDialog("Wollen Sie die gewählte Raumbelegung wirklich zurückziehen?", "Achtung!");
				this.revokeAllocation = revokeAllocation;
				dialog.register(this);
				dialog.setVisible(true);
				return;
			}
		}
		
	}
	
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.delCourse != null) {
				if (this.cntrlStartTab.deleteCourse(this.delCourse)) {
					AppModel.getInstance().getExceptionHandler().setNewException("Die Veranstaltung wurde erfolgreich gelöscht.", "Erfolg!", "success");
				}
			}
			if (this.revokeAllocation != null) {
				if (this.cntrlStartTab.revokeRoomAllocation(this.revokeAllocation)) {
					AppModel.getInstance().getExceptionHandler().setNewException("Die Raumbelegung wurde zurückgezogen.", "Erfolg!", "success");
				}
			}
		} else if (answer.equals("no")) {
			this.delCourse = null;
			this.revokeAllocation = null;
		}
	}
	
}
