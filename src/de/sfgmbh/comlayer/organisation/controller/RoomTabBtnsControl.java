package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

public class RoomTabBtnsControl implements ActionListener {

	private String navAction;
	protected InfoDialog infoWindow;

	public RoomTabBtnsControl() {
		this.navAction = "default";
	}

	public RoomTabBtnsControl(String action) {
		this.navAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Raum hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
		}

		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {

			if(ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow()>=0){
			
				//get selected Room from DB	
				int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow();
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
	
				//load values from room object to gui
				ViewManager.getInstance().getOrgaRoomFrame().getTxtroomid().setText(""+selectedRoom.getRoomId_());
				
				ViewManager.getInstance().getOrgaRoomFrame().getTxtRoomNumber().setText(selectedRoom.getRoomNumber_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtLevel().setText(selectedRoom.getLevel_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtSeats().setText(""+selectedRoom.getSeats_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtPcSeats().setText(""+selectedRoom.getPcseats_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtBeamer().setText(""+selectedRoom.getBeamer_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtChalkboards().setText(""+selectedRoom.getChalkboards_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtOverheads().setText(""+selectedRoom.getOverheads_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtVisualizer().setText(""+selectedRoom.getVisualizer_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtWhiteboards().setText(""+selectedRoom.getWhiteboards_());
				
				
				//set Room Edit Frame visible
				ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
			
			}
			
		}

		// Raum l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			
				if(ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow()>=0){
				
				//get selected Room from DB	
				int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow();
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
				
				AppModel.getInstance().getRepositoryRoom().delete(selectedRoom);
				
				/*
				this.getInfoWindow(
						"<b>Fehlermeldung:</b><br>Der Raum konnte nicht gel�scht werden:<br>Sie haben keinen Raum selektiert.")
						.setVisible(true);*/
				}
		}

		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow(
					"<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher k�nnen keine R�ume angezeigt werden.")
					.setVisible(true);
		}

		// Raumplan Button is pressed
		if (this.navAction.equals("Raumplan")) {

			this.getInfoWindow(
					"<strong>Fehlermeldung</strong><br> Es konnte keine Übersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgew�hlt!")
					.setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
					.setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
					.addTab("Raumplan", null,
							ViewManager.getInstance().getOrgaRoomtableTab(),
							null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
					.setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
							.getTabCount() - 1);

		}

	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
