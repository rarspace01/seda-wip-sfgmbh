package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;

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
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true);
		}

		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {

			//get selected Room from DB			
			Room selectedRoom=AppModel.getInstance().repositoryRoom.getRoomById(Bootstrap.serviceManager.getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow());

			//load values from room object to gui
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtroomid().setText(""+selectedRoom.getRoomId_());
			
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtRoomNumber().setText(selectedRoom.getRoomNumber_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtLevel().setText(selectedRoom.getLevel_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtSeats().setText(""+selectedRoom.getSeats_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtPcSeats().setText(""+selectedRoom.getPcseats_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtBeamer().setText(""+selectedRoom.getBeamer_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtChalkboards().setText(""+selectedRoom.getChalkboards_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtOverheads().setText(""+selectedRoom.getOverheads_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtVisualizer().setText(""+selectedRoom.getVisualizer_());
			Bootstrap.serviceManager.getOrgaRoomFrame().getTxtWhiteboards().setText(""+selectedRoom.getWhiteboards_());
			
			
			//set Room Edit Frame visible
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true);
			
		}

		// Raum l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow(
					"<b>Fehlermeldung:</b><br>Der Raum konnte nicht gel�scht werden:<br>Sie haben keinen Raum selektiert.")
					.setVisible(true);
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
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane
					.setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane
					.addTab("Raumplan", null,
							Bootstrap.serviceManager.getOrgaRoomtableTab(),
							null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane
					.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane
							.getTabCount() - 1);

		}

	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
