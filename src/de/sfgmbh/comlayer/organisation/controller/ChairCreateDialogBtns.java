package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
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

	private String ctrlAction_;
	private ChairCreateDialog motherDialog_;
	private IntfCtrlChair ctrlChair_ = new CtrlChair();

	/**
	 * Create the action listener
	 * 
	 * @param motherDialog
	 */
	public ChairCreateDialogBtns(ChairCreateDialog motherDialog) {
		this.motherDialog_ = motherDialog;
		this.ctrlAction_ = "default";
	}

	/**
	 * Create the action listener based on an action string
	 * 
	 * @param motherDialog
	 * @param action
	 */
	public ChairCreateDialogBtns(ChairCreateDialog motherDialog, String action) {
		this.motherDialog_ = motherDialog;
		this.ctrlAction_ = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();
		CmbboxFilterLecturer cmbboxModel = (CmbboxFilterLecturer) motherDialog_
				.getCmbboxLecturer().getModel();

		// Cancel button is pressed
		if (this.ctrlAction_.equals("cancel")) {
			this.motherDialog_.dispose();
		}

		// Save button is pressed
		if (this.ctrlAction_.equals("save")) {
			IntfChair newChair = motherDialog_.getChair();
			try {
				newChair.setAcronym_(motherDialog_.getTxtAcronym()
						.getText());
				newChair.setChairName_(motherDialog_
						.getTxtName().getText());
				newChair.setChairLevel_(motherDialog_.getTxtLevel().getText());
				newChair.setFaculty_(motherDialog_.getTxtFaculity().getText());
				// Get the lecturer object out of the model as a sql query on
				// the last name which should not be unique would not be a good
				// idea
				// Still this way users could see two same last names where they
				// only could find the 'right' one by trial and error...
				// But as per default the currently logged in lecturer is
				// selected in most of the cases this should be no big problem.
				newChair.setChairOwner_(cmbboxModel.getLecturerForModel().get(
						motherDialog_.getCmbboxLecturer().getSelectedIndex()));
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

			if (this.ctrlChair_.saveChair(newChair)) {
				exceptionHandler.setNewException(
						"Der Lehrstuhl wurde gespeichert.", "Erfolg!",
						"success");
				this.motherDialog_.dispose();
			}

		}
	}
}
