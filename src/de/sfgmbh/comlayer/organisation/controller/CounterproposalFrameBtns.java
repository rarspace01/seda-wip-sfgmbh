package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Action listener for the counter proposal dialog
 * 
 * @author hannes
 *
 */
public class CounterproposalFrameBtns implements ActionListener {
	
	private String ctrlAction;
	private CounterproposalDialog motherDialog;
	private CtrlRoomAllocation ctrlRoomAllocation;
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog) {
		this.ctrlAction = "default";
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
		this.motherDialog = motherDialog;
	}
	
	/**
	 * Create the action listener for a special action
	 * @param motherDialog
	 * @param action
	 */
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog, String action) {
		this.ctrlAction = action;
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
		this.motherDialog = motherDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		// Cancle button is pressed
		if (this.ctrlAction.equals("cancel")){
			this.motherDialog.dispose();
		}
		
		// Sent button is pressed
		if (this.ctrlAction.equals("send")){
			RoomAllocation currentAllocation = motherDialog.getProposalAllocation();
			
			String orgaMessage = motherDialog.getEditorPane().getText();
			if (orgaMessage.length() > 10000) {
				exceptionHandler.setNewException("Sie können einen Text von nur maximal 10 000 Zeichen verfassen. Bitte kürzen Sie Ihre Nachricht.", "Fehler!", "error");
				return;
			}
			
			currentAllocation.setOrgaMessage_(orgaMessage);
			
			if (ctrlRoomAllocation.createCounterProposal(currentAllocation)) {
				exceptionHandler.setNewException("Gegenvorschlag erfolgreich eingetragen!", "Erfolg!", "success");
				motherDialog.dispose();
			} 
		}
		
		// Combobox action
		if (this.ctrlAction.equals("combo")) {
			RoomAllocation currentRoomAllocation = motherDialog.getProposalAllocation();
			Room room = AppModel.getInstance().getRepositoryRoom().getByNumber(motherDialog.getCmbboxRoom().getSelectedItem().toString());
			int time = motherDialog.getCmbboxTime().getSelectedIndex() + 1;
			int day  = motherDialog.getCmbboxDay().getSelectedIndex() + 1;
			
			if (room == null) {
				exceptionHandler.setNewException("Dies ist kein gültiger Raum, bitte tragen Sie einen gültigen Raumnamen ein und versuchen Sie es erneut!", "Fehler!", "error");
				motherDialog.getCmbboxRoom().setSelectedItem(currentRoomAllocation.getRoom_().getRoomNumber_());
				return;
			} else {
				RoomAllocation newRoomAllocation = currentRoomAllocation;
				newRoomAllocation.setRoom_(room);
				newRoomAllocation.setTime_(time);
				newRoomAllocation.setDay_(day);
				motherDialog.setRoomAllocation(newRoomAllocation);
			}
		}
	}
}
