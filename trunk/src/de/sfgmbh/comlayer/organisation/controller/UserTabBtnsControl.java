package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;



public class UserTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public UserTabBtnsControl() {
		this.navAction = "default";
	}
	public UserTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Benutzer hinzuf�gen Button is pressed
		if (this.navAction.equals("hinz")) {
			ViewManager.getInstance().getOrgaUserCreateDialog().setVisible(true);
		}
		
		// Benutzer bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			ViewManager.getInstance().getOrgaUserEditFrame().setVisible(true);
			//this.getInfoWindow("<b>Fehlermeldung:</b><br> Sie haben keinen Nutzer selektiert!<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie" +
			//		" sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Benutzer l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Sie haben keinen Nutzer selektiert!").setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher k�nnen keine Nutzer angezeigt werden.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
