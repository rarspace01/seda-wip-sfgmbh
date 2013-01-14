package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
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
			ServiceManager.getInstance().getOrgaRoomFrame().setVisible(true);
		}

		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {

			//get selected Room from DB			
			Room selectedRoom=AppModel.getInstance().repositoryRoom.getRoomById(ServiceManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow());

			//load values from room object to gui
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtroomid().setText(""+selectedRoom.getRoomId_());
			
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtRoomNumber().setText(selectedRoom.getRoomNumber_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtLevel().setText(selectedRoom.getLevel_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtSeats().setText(""+selectedRoom.getSeats_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtPcSeats().setText(""+selectedRoom.getPcseats_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtBeamer().setText(""+selectedRoom.getBeamer_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtChalkboards().setText(""+selectedRoom.getChalkboards_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtOverheads().setText(""+selectedRoom.getOverheads_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtVisualizer().setText(""+selectedRoom.getVisualizer_());
			ServiceManager.getInstance().getOrgaRoomFrame().getTxtWhiteboards().setText(""+selectedRoom.getWhiteboards_());
			
			
			//set Room Edit Frame visible
			ServiceManager.getInstance().getOrgaRoomFrame().setVisible(true);
			
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
			ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane
					.setVisible(true);
			ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane
					.addTab("Raumplan", null,
							ServiceManager.getInstance().getOrgaRoomtableTab(),
							null);
			ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane
					.setSelectedIndex(ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane
							.getTabCount() - 1);

		}

	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
