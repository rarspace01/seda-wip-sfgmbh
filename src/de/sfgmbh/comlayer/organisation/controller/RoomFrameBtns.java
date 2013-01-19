package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.organisation.controller.CtrlRoom;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

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
			ViewManager.getInstance().getOrgaRoomFrame().setVisible(false);
		}

		// Speichern button is pressed
		if (this.ctrlAction.equals("save")) {
			
			//TODO check for fileds
			if(ViewManager.getInstance().getOrgaRoomFrame()
					.getTxtLevel().getText().length()==0){
				AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie ein Stockwerk an.", "Achtung!");
			}else if(ViewManager.getInstance().getOrgaRoomFrame()
					.getTxtRoomNumber().getText().length()==0){
				AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Raumnummer an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtSeats().getText().length()==0 || ViewManager.getInstance()
							.getOrgaRoomFrame().getTxtSeats().getText().matches("[^0-9]")){
						AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Sitzplanzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtPcSeats().getText().length()==0 || ViewManager.getInstance()
							.getOrgaRoomFrame().getTxtPcSeats().getText().matches("[^0-9]")){
						AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Pc Sitzplanzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtBeamer().getText().length()==0 || ViewManager.getInstance()
							.getOrgaRoomFrame().getTxtBeamer().getText().matches("[^0-9]")){
						AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Beamer Anzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtVisualizer().getText().length()==0 || ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtVisualizer().getText().matches("[^0-9]")){
				AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Visualizeranzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtOverheads().getText().length()==0 || ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtOverheads().getText().matches("[^0-9]")){
				AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Overheadanzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtChalkboards().getText().length()==0 || ViewManager.getInstance()
							.getOrgaRoomFrame().getTxtChalkboards().getText().matches("[^0-9]")){
						AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie eine Tafelanzahl an.", "Achtung!");
			}else if(ViewManager.getInstance()
					.getOrgaRoomFrame().getTxtWhiteboards().getText().length()==0 || ViewManager.getInstance()
							.getOrgaRoomFrame().getTxtWhiteboards().getText().matches("[^0-9]")){
						AppModel.getInstance().getExceptionHandler().setNewException("Bitte geben Sie ein Whiteboardanzahl an.", "Achtung!");
			}else{
			
				ViewManager.getInstance().getOrgaRoomFrame().setVisible(false);
	
				// read Values, Create Room object, store to DB
				Room toBeSavedRoom = new Room(1); // Building ID=1 for Erba
	
				//check if room has already a id, if so store it
				if(ViewManager.getInstance()
						.getOrgaRoomFrame().getTxtroomid().getText().length()>0){
					AppModel.getInstance().getExceptionHandler().setNewException("");
				}
				
				toBeSavedRoom.setRoomNumber_(ViewManager.getInstance()
						.getOrgaRoomFrame().getTxtRoomNumber().getText());
				toBeSavedRoom.setLevel_(ViewManager.getInstance().getOrgaRoomFrame()
						.getTxtLevel().getText());
	
				toBeSavedRoom.setSeats_(Integer.parseInt(ViewManager.getInstance()
						.getOrgaRoomFrame().getTxtSeats().getText()));
				toBeSavedRoom.setPcseats_(Integer.parseInt(ViewManager.getInstance()
						.getOrgaRoomFrame().getTxtPcSeats().getText()));
	
				toBeSavedRoom.setBeamer_(Integer.parseInt(ViewManager.getInstance()
						.getOrgaRoomFrame().getTxtBeamer().getText()));
				toBeSavedRoom.setChalkboards_(Integer
						.parseInt(ViewManager.getInstance().getOrgaRoomFrame()
								.getTxtChalkboards().getText()));
				toBeSavedRoom.setOverheads_(Integer
						.parseInt(ViewManager.getInstance().getOrgaRoomFrame()
								.getTxtOverheads().getText()));
				toBeSavedRoom.setVisualizer_(Integer
						.parseInt(ViewManager.getInstance().getOrgaRoomFrame()
								.getTxtVisualizer().getText()));
				toBeSavedRoom.setWhiteboards_(Integer
						.parseInt(ViewManager.getInstance().getOrgaRoomFrame()
								.getTxtWhiteboards().getText()));
	
				this.ctrlRoom.addRoom(toBeSavedRoom);
			}
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
