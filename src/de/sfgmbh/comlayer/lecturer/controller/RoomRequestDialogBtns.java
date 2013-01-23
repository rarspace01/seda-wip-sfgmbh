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
	
	private String ctrlAction;
	private RoomRequestDialog motherDialog;
	private IntfCtrlStartTab ctrlStartTab;
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public RoomRequestDialogBtns(RoomRequestDialog motherDialog) {
		this.ctrlAction = "default";
		this.ctrlStartTab = new CtrlStartTab();
		this.motherDialog = motherDialog;
	}
	
	/**
	 * Create the action listener for a special action
	 * @param motherDialog
	 * @param action
	 */
	public RoomRequestDialogBtns(RoomRequestDialog motherDialog, String action) {
		this.ctrlAction = action;
		this.ctrlStartTab = new CtrlStartTab();
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
			IntfRoomAllocation currentAllocation = motherDialog.getProposalAllocation();
			
			if (ctrlStartTab.createRoomAllocation(currentAllocation)) {
				exceptionHandler.setNewException("Ihre Raumanfrage wurde erfolgreich eingetragen.<br />" +
						"In der unteren Tabelle können Sie ihren Freigabestatus sehen. Sobald sie durch die Verwaltung " +
						"freigegeben wrude und Sie die dazugehörige Veranstaltung veröffentlicht haben, ist sie für Studenten " +
						"sichtbar.", "Erfolg!", "success");
				motherDialog.dispose();
			} 
		}
		
		// New suggestion button is pressed
		if (this.ctrlAction.equals("newSuggestion")){
			HashMap<String,String> filter = new HashMap<String,String>();
			int seats, pcSeats, beamer, overheads, whiteboards, vizualizer, chalkboards;
			try {
				seats = Integer.parseInt(motherDialog.getTxtSeats().getText());
				pcSeats = Integer.parseInt(motherDialog.getTxtPcs().getText());
				beamer = Integer.parseInt(motherDialog.getTxtBeamer().getText());
				overheads = Integer.parseInt(motherDialog.getTxtOverhead().getText());
				whiteboards = Integer.parseInt(motherDialog.getTxtWhiteboard().getText());
				vizualizer = Integer.parseInt(motherDialog.getTxtVisual().getText());
				chalkboards = Integer.parseInt(motherDialog.getTxtBoard().getText());
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
			motherDialog.setSuggestRoomAllocation(motherDialog.getProposalAllocation(), filter);
		}
		
		// Combobox action
		if (this.ctrlAction.equals("combo")) {
			IntfRoomAllocation currentRoomAllocation = motherDialog.getProposalAllocation();
			IntfRoom room = AppModel.getInstance().getRepositoryRoom().getByNumber(motherDialog.getCmbboxRoom().getSelectedItem().toString());
			int time = motherDialog.getCmbboxTime().getSelectedIndex() + 1;
			int day  = motherDialog.getCmbboxDay().getSelectedIndex() + 1;
			String semester = motherDialog.getCmbboxSemester().getSelectedItem().toString();
			
			if (room == null) {
				exceptionHandler.setNewException("Dies ist kein gültiger Raum, bitte tragen Sie einen gültigen Raumnamen ein und versuchen Sie es erneut!", "Fehler!", "error");
				motherDialog.getCmbboxRoom().setSelectedItem(currentRoomAllocation.getRoom_().getRoomNumber_());
				return;
			} else {
				IntfRoomAllocation newRoomAllocation = currentRoomAllocation;
				newRoomAllocation.setRoom_(room);
				newRoomAllocation.setTime_(time);
				newRoomAllocation.setDay_(day);
				newRoomAllocation.setSemester_(semester);
				motherDialog.setRoomAllocation(newRoomAllocation);
			}
		}
	}
}
