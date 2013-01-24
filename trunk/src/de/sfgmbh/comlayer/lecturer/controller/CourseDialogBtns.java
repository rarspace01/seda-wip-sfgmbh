package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.lecturer.views.CourseDialog;

/**
 * Action listener for the course edit/create dialog
 * 
 * @author hannes
 * @author christian
 * 
 */
public class CourseDialogBtns implements ActionListener {

	private String ctrlAction;
	private CourseDialog motherDialog;
	private IntfCtrlStartTab cntrlStartTab = new CtrlStartTab();

	/**
	 * Create the action listener
	 * 
	 * @param motherDialog
	 */
	public CourseDialogBtns(CourseDialog motherDialog) {
		this.motherDialog = motherDialog;
		this.ctrlAction = "default";
	}

	/**
	 * Create the action listener based on an action string
	 * Supported action strings are "cancel" and "save"
	 * @param motherDialog
	 * @param action
	 */
	public CourseDialogBtns(CourseDialog motherDialog, String action) {
		this.motherDialog = motherDialog;
		this.ctrlAction = action;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();
		CmbboxFilterLecturer cmbboxModel = (CmbboxFilterLecturer) motherDialog
				.getCmbboxLecturer().getModel();

		// Cancel button is pressed
		if (this.ctrlAction.equals("cancel")) {
			this.motherDialog.dispose();
		}

		// Save button is pressed
		if (this.ctrlAction.equals("save")) {
			IntfCourse newCourse = motherDialog.getCourse();
			try {
				newCourse.setCourseAcronym_(motherDialog.getTxtAcronym()
						.getText());
				newCourse.setCourseDescription_(motherDialog
						.getEditDescription().getText());
				newCourse.setCourseKind_(motherDialog.getCmbboxKind()
						.getSelectedItem().toString());
				newCourse.setCourseName_(motherDialog.getTxtName().getText());
				newCourse.setExpectedAttendees_(Integer.parseInt(motherDialog
						.getTxtAttendees().getText()));
				newCourse.setSws_(Float.parseFloat(motherDialog.getTxtSws()
						.getText()));
				newCourse.setLecturerEnabled_(motherDialog.getChckbxPublic()
						.isSelected());
				// Get the lecturer object out of the model as a sql query on
				// the last name which should not be unique would not be a good
				// idea
				// Still this way users could see two same last names where they
				// only could find the 'right' one by trial and error...
				// But as per default the currently logged in lecturer is
				// selected in most of the cases this should be no big problem.
				newCourse.setLecturer_(cmbboxModel.getLecturerForModel().get(
						motherDialog.getCmbboxLecturer().getSelectedIndex()));
			} catch (Exception ex) {
				exceptionHandler
						.setNewException(
								"Ihre Eingabe war ungültig. Überpfüen Sie diese bitte " +
								"(stellen sie z. B. sicher,  dass Sie alle Felder ausgefüllt " +
								"haben und dass Sie, wo dies vorgesehen ist, nur Zahlen ohne " +
								"Buchstaben verwenden.",
								"Fehler!", "error");
				return;
			}

			if (this.cntrlStartTab.saveCourse(newCourse)) {
				exceptionHandler.setNewException(
						"Die Veranstaltung wurde gespeichert.", "Erfolg!",
						"success");
				this.motherDialog.dispose();
			}

		}
	}
}
