package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.lecturer.views.CourseDialog;
import de.sfgmbh.comlayer.lecturer.views.StartTab;

/**
 * Controller for the lecturer tab buttons on the right
 * 
 * @author hannes
 * @author christian
 *
 */
public class StartTabBtnsControl implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public StartTabBtnsControl() {
		this.ctrlAction = "default";
	}
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

		}
		
		// Publish button is pressed
		if (this.ctrlAction.equals("publish")){
			
		}
		
		// Request room button is pressed
		if (this.ctrlAction.equals("roomrequest")){
			ViewManager.getInstance().getLecturerRoomRequestFrame().setVisible(true);
		}
		
		// Revoke button is pressed
		if (this.ctrlAction.equals("back")){
			
		}
		
	}
}
