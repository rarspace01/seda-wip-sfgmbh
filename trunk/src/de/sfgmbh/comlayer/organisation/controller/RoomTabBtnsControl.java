package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			//Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true)
			this.getInfoWindow("<b>Fehlermeldung:</b><br> Sie haben keinen Raum selektiert!<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie" +
					" sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Raum löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Der Raum konnte nicht gelöscht werden:<br>Sie haben keinen Raum selektiert.").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher können keine Räume angezeigt werden.").setVisible(true);
		}
		
		// Raumplan Button is pressed
		if (this.navAction.equals("Raumplan")) {
			
			this.getInfoWindow("<strong>Fehlermeldung</strong><br> Es konnte keine Übersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgewählt!").setVisible(true);	
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumplan", null, Bootstrap.serviceManager.getOrgaRoomtableTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);

		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
