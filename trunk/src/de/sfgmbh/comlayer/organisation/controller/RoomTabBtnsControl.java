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
		
		// Raum hinzuf�gen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true);
		}
		
		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			//Bootstrap.serviceManager.getOrgaRoomFrame().setVisible(true)
			this.getInfoWindow("<b>Fehlermeldung:</b><br> Sie haben keinen Raum selektiert!<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie" +
					" sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Raum l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Der Raum konnte nicht gel�scht werden:<br>Sie haben keinen Raum selektiert.").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher k�nnen keine R�ume angezeigt werden.").setVisible(true);
		}
		
		// Raumplan Button is pressed
		if (this.navAction.equals("Raumplan")) {
			
			this.getInfoWindow("<strong>Fehlermeldung</strong><br> Es konnte keine �bersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgew�hlt!").setVisible(true);	
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
