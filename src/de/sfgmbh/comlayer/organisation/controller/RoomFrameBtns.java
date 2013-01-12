package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.organisation.controller.CtrlRoom;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;

/**
 * action listener for Room edit Frame
 * 
 * @author denis
 * 
 */
public class RoomFrameBtns implements ActionListener {

	private String ctrlAction;
	protected InfoDialog infoWindow;
	protected CtrlRoom ctrlRoom;

	public RoomFrameBtns() {
		this.ctrlAction = "default";
		if (this.ctrlRoom == null) {
			this.ctrlRoom = new CtrlRoom();
		}
	}

	public RoomFrameBtns(String action) {
		this.ctrlAction = action;
		if (this.ctrlRoom == null) {
			this.ctrlRoom = new CtrlRoom();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")) {
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(false);
		}

		// Speichern button is pressed
		if (this.ctrlAction.equals("save")) {
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(false);

			// read Values, Create Room object, store to DB
			Room toBeSavedRoom = new Room(1); // Building ID=1 for Erba

			toBeSavedRoom.setRoomNumber_(Bootstrap.serviceManager
					.getOrgaRoomFrame().getTxtRoomNumber().getText());
			toBeSavedRoom.setLevel_(Bootstrap.serviceManager.getOrgaRoomFrame()
					.getTxtLevel().getText());

			toBeSavedRoom.setSeats_(Integer.parseInt(Bootstrap.serviceManager
					.getOrgaRoomFrame().getTxtSeats().getText()));
			toBeSavedRoom.setPcseats_(Integer.parseInt(Bootstrap.serviceManager
					.getOrgaRoomFrame().getTxtPcSeats().getText()));

			toBeSavedRoom.setBeamer_(Integer.parseInt(Bootstrap.serviceManager
					.getOrgaRoomFrame().getTxtBeamer().getText()));
			toBeSavedRoom.setChalkboards_(Integer
					.parseInt(Bootstrap.serviceManager.getOrgaRoomFrame()
							.getTxtChalkboards().getText()));
			toBeSavedRoom.setOverheads_(Integer
					.parseInt(Bootstrap.serviceManager.getOrgaRoomFrame()
							.getTxtOverheads().getText()));
			toBeSavedRoom.setVisualizer_(Integer
					.parseInt(Bootstrap.serviceManager.getOrgaRoomFrame()
							.getTxtVisualizer().getText()));
			toBeSavedRoom.setWhiteboards_(Integer
					.parseInt(Bootstrap.serviceManager.getOrgaRoomFrame()
							.getTxtVisualizer().getText()));

			this.ctrlRoom.addRoom(toBeSavedRoom);
			
			/*
			 * this.getInfoWindow(
			 * "<b>Fehlermeldung:</b><br>Der Raum konnte nicht hinzugef�gt werden:<br>1. Die Raumnummer ist bereits im System vorhanden."
			 * +
			 * "<br>2. Einer der von Ihnen eingegebenen Werte ist syntaktisch inkorrekt.<br>3. Der von Ihnen eingetragene Raum existiert nicht im System. "
			 * +
			 * "Sollte er doch existieren, kontaktieren Sie bitte die Hausverwaltung."
			 * ).setVisible(true);
			 */
		}
	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
