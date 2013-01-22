package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.organisation.controller.CtrlChair;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlChair;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.organisation.views.ChairCreateDialog;

/**
 * Action listener for the chair edit/create dialog
 * 
 * @author hannes
 * @author anna
 * 
 */
public class ChairCreateDialogBtns implements ActionListener {

	private String ctrlAction;
	private ChairCreateDialog motherDialog;
	private IntfCtrlChair ctrlChair = new CtrlChair();

	/**
	 * Create the action listener
	 * 
	 * @param motherDialog
	 */
	public ChairCreateDialogBtns(ChairCreateDialog motherDialog) {
		this.motherDialog = motherDialog;
		this.ctrlAction = "default";
	}

	/**
	 * Create the action listener based on an action string
	 * 
	 * @param motherDialog
	 * @param action
	 */
	public ChairCreateDialogBtns(ChairCreateDialog motherDialog, String action) {
		this.motherDialog = motherDialog;
		this.ctrlAction = action;
	}

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
			Chair newChair = motherDialog.getChair();
			try {
				newChair.setAcronym_(motherDialog.getTxtAcronym()
						.getText());
				newChair.setChairName_(motherDialog
						.getTxtName().getText());
				newChair.setChairLevel_(motherDialog.getTxtLevel().getText());
				newChair.setFaculty_(motherDialog.getTxtFaculity().getText());
				// Get the lecturer object out of the model as a sql query on
				// the last name which should not be unique would not be a good
				// idea
				// Still this way users could see two same last names where they
				// only could find the 'right' one by trial and error...
				// But as per default the currently logged in lecturer is
				// selected in most of the cases this should be no big problem.
				newChair.setChairOwner_(cmbboxModel.getLecturerForModel().get(
						motherDialog.getCmbboxLecturer().getSelectedIndex()));
			} catch (Exception ex) {
				exceptionHandler
						.setNewException(
								"Ihre Eingabe war ungültig. Überpfüen Sie diese bitte " +
								"(stellen sie z. B. sicher,  dass Sie alle notwendigen Felder ausgefüllt " +
								"haben und dass Sie, wo dies vorgesehen ist, nur Zahlen ohne " +
								"Buchstaben verwenden.",
								"Fehler!", "error");
				return;
			}

			if (this.ctrlChair.saveChair(newChair)) {
				exceptionHandler.setNewException(
						"Der Lehrstuhl wurde gespeichert.", "Erfolg!",
						"success");
				this.motherDialog.dispose();
			}

		}
	}
}
