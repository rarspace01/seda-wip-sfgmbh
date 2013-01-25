package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Action listener for the counter proposal dialog
 * 
 * @author hannes
 * 
 */
public class CounterproposalFrameBtns implements ActionListener {

	private String ctrlAction_;
	private CounterproposalDialog motherDialog_;
	private IntfCtrlRoomAllocation ctrlRoomAllocation_;

	/**
	 * Create the action listener
	 * 
	 * @param motherDialog
	 */
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog) {
		this.ctrlAction_ = "default";
		this.ctrlRoomAllocation_ = new CtrlRoomAllocation();
		this.motherDialog_ = motherDialog;
	}

	/**
	 * Create the action listener for a special action
	 * 
	 * @param motherDialog
	 * @param action
	 */
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog,
			String action) {
		this.ctrlAction_ = action;
		this.ctrlRoomAllocation_ = new CtrlRoomAllocation();
		this.motherDialog_ = motherDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();

		// Cancle button is pressed
		if (this.ctrlAction_.equals("cancel")) {
			this.motherDialog_.dispose();
		}

		// Sent button is pressed
		if (this.ctrlAction_.equals("send")) {
			IntfRoomAllocation currentAllocation = motherDialog_
					.getProposalAllocation();

			String orgaMessage = motherDialog_.getEditorPane().getText();
			if (orgaMessage.length() > 10000) {
				exceptionHandler
						.setNewException(
								"Sie können einen Text von nur maximal 10 000 Zeichen verfassen. Bitte kürzen Sie Ihre Nachricht.",
								"Fehler!", "error");
				return;
			}

			currentAllocation.setOrgaMessage_(orgaMessage);

			if (ctrlRoomAllocation_.createCounterProposal(currentAllocation)) {
				exceptionHandler.setNewException(
						"Gegenvorschlag erfolgreich eingetragen!", "Erfolg!",
						"success");
				motherDialog_.dispose();
			}
		}

		// Combobox action
		if (this.ctrlAction_.equals("combo")) {
			IntfRoomAllocation currentRoomAllocation = motherDialog_
					.getProposalAllocation();
			IntfRoom room = AppModel
					.getInstance()
					.getRepositoryRoom()
					.getByNumber(
							motherDialog_.getCmbboxRoom().getSelectedItem()
									.toString());
			int time = motherDialog_.getCmbboxTime().getSelectedIndex() + 1;
			int day = motherDialog_.getCmbboxDay().getSelectedIndex() + 1;

			if (room == null) {
				exceptionHandler
						.setNewException(
								"Dies ist kein gültiger Raum, bitte tragen Sie einen gültigen Raumnamen ein und versuchen Sie es erneut!",
								"Fehler!", "error");
				motherDialog_.getCmbboxRoom().setSelectedItem(
						currentRoomAllocation.getRoom_().getRoomNumber_());
				return;
			} else {
				IntfRoomAllocation newRoomAllocation = currentRoomAllocation;
				newRoomAllocation.setRoom_(room);
				newRoomAllocation.setTime_(time);
				newRoomAllocation.setDay_(day);
				motherDialog_.setRoomAllocation(newRoomAllocation);
			}
		}
	}
}
