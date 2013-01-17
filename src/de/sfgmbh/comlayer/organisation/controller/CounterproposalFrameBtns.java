package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
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
	
	
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog) {
		this.ctrlAction = "default";
		this.motherDialog = motherDialog;
	}
	public CounterproposalFrameBtns(CounterproposalDialog motherDialog, String action) {
		this.ctrlAction = action;
		this.motherDialog = motherDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Cancle button is pressed
		if (this.ctrlAction.equals("cancel")){
			this.motherDialog.dispose();
		}
		
		// Sent button is pressed
		if (this.ctrlAction.equals("send")){
			//
		}
		
		// Combobox action
		if (this.ctrlAction.equals("combo")) {
			RoomAllocation currentRoomAllocation = motherDialog.getProposalAllocation();
			Room room = AppModel.getInstance().getRepositoryRoom().getByNumber(motherDialog.getCmbboxRoom().getSelectedItem().toString());
			int time = motherDialog.getCmbboxTime().getSelectedIndex() + 1;
			int day  = motherDialog.getCmbboxDay().getSelectedIndex() + 1;
			
			if (room == null) {
				AppModel.getInstance().getExceptionHandler().setNewException("Dies ist kein gültiger Raum, bitte tragen Sie einen gültigen Raumnamen ein und versuchen Sie es erneut!", "Fehler!", "error");
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
