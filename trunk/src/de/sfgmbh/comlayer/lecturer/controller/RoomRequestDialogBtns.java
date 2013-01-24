package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.lecturer.views.RoomRequestDialog;

/**
 * Action listener for the room request dialog
 * 
 * @author hannes
 *
 */
public class RoomRequestDialogBtns implements ActionListener {
	
	private String ctrlAction_;
	private RoomRequestDialog motherDialog_;
	private IntfCtrlStartTab ctrlStartTab_;
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public RoomRequestDialogBtns(RoomRequestDialog motherDialog) {
		this.ctrlAction_ = "default";
		this.ctrlStartTab_ = new CtrlStartTab();
		this.motherDialog_ = motherDialog;
	}
	
	/**
	 * Create the action listener for a special action
	 * Supported action strings are:<br>
	 * "cancel", "send", "combo" and "newSuggestion"
	 * @param motherDialog
	 * @param action
	 */
	public RoomRequestDialogBtns(RoomRequestDialog motherDialog, String action) {
		this.ctrlAction_ = action;
		this.ctrlStartTab_ = new CtrlStartTab();
		this.motherDialog_ = motherDialog;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		// Cancle button is pressed
		if (this.ctrlAction_.equals("cancel")){
			this.motherDialog_.dispose();
		}
		
		// Sent button is pressed
		if (this.ctrlAction_.equals("send")){
			IntfRoomAllocation currentAllocation = motherDialog_.getProposalAllocation();
			
			if (ctrlStartTab_.createRoomAllocation(currentAllocation)) {
				exceptionHandler.setNewException("Ihre Raumanfrage wurde erfolgreich eingetragen.<br />" +
						"In der unteren Tabelle können Sie ihren Freigabestatus sehen. Sobald sie durch die Verwaltung " +
						"freigegeben wrude und Sie die dazugehörige Veranstaltung veröffentlicht haben, ist sie für Studenten " +
						"sichtbar.", "Erfolg!", "success");
				motherDialog_.dispose();
			} 
		}
		
		// New suggestion button is pressed
		if (this.ctrlAction_.equals("newSuggestion")){
			HashMap<String,String> filter = new HashMap<String,String>();
			int seats, pcSeats, beamer, overheads, whiteboards, vizualizer, chalkboards;
			try {
				seats = Integer.parseInt(motherDialog_.getTxtSeats().getText());
				pcSeats = Integer.parseInt(motherDialog_.getTxtPcs().getText());
				beamer = Integer.parseInt(motherDialog_.getTxtBeamer().getText());
				overheads = Integer.parseInt(motherDialog_.getTxtOverhead().getText());
				whiteboards = Integer.parseInt(motherDialog_.getTxtWhiteboard().getText());
				vizualizer = Integer.parseInt(motherDialog_.getTxtVisual().getText());
				chalkboards = Integer.parseInt(motherDialog_.getTxtBoard().getText());
			} catch (Exception ex) {
				exceptionHandler.setNewException("Bitte stellen Sie sicher, dass Sie alle Felder für einen Vorschlag mit einer gültigen Zahl gefüllt haben. Sollte Ihnen ein Wert egal sein, so lassen Sie ihn bitte einfach auf 0 stehen.", "Fehler!", "error");
				return;
			}
			filter.put("seats", String.valueOf(seats));
			filter.put("pcseats", String.valueOf(pcSeats));
			filter.put("beamer", String.valueOf(beamer));
			filter.put("overheads", String.valueOf(overheads));
			filter.put("whiteboards", String.valueOf(whiteboards));
			filter.put("visualizer", String.valueOf(vizualizer));
			filter.put("chalkboards", String.valueOf(chalkboards));
			motherDialog_.setSuggestRoomAllocation(motherDialog_.getProposalAllocation(), filter);
		}
		
		// Combobox action
		if (this.ctrlAction_.equals("combo")) {
			IntfRoomAllocation currentRoomAllocation = motherDialog_.getProposalAllocation();
			IntfRoom room = AppModel.getInstance().getRepositoryRoom().getByNumber(motherDialog_.getCmbboxRoom().getSelectedItem().toString());
			int time = motherDialog_.getCmbboxTime().getSelectedIndex() + 1;
			int day  = motherDialog_.getCmbboxDay().getSelectedIndex() + 1;
			String semester = motherDialog_.getCmbboxSemester().getSelectedItem().toString();
			
			if (room == null) {
				exceptionHandler.setNewException("Dies ist kein gültiger Raum, bitte tragen Sie einen gültigen Raumnamen ein und versuchen Sie es erneut!", "Fehler!", "error");
				motherDialog_.getCmbboxRoom().setSelectedItem(currentRoomAllocation.getRoom_().getRoomNumber_());
				return;
			} else {
				IntfRoomAllocation newRoomAllocation = currentRoomAllocation;
				newRoomAllocation.setRoom_(room);
				newRoomAllocation.setTime_(time);
				newRoomAllocation.setDay_(day);
				newRoomAllocation.setSemester_(semester);
				motherDialog_.setRoomAllocation(newRoomAllocation);
			}
		}
	}
}
