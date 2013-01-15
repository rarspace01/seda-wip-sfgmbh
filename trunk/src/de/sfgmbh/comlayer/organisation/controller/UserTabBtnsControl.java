package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.core.views.QuestionDialog;



public class UserTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
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
		
		// Add button is pressed
		if (this.navAction.equals("hinz")) {
			ViewManager.getInstance().getOrgaUserCreateDialog().setVisible(true);
		}
		
		// Edit button is pressed
		if (this.navAction.equals("edit")) {
			ViewManager.getInstance().getOrgaUserEditFrame().setVisible(true);
			//this.getInfoWindow("<b>Fehlermeldung:</b><br> Sie haben keinen Nutzer selektiert!<br> Wenn Sie keine Fehlermeldung erhalten gelangen Sie" +
			//		" sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Delete button is pressed
		if (this.navAction.equals("loschen")) {
			QuestionDialog dialog = new QuestionDialog("Wollen Sie den gewählten benutzer wirklich löschen?", "Achtung!");
			dialog.setVisible(true);
			dialog.register(this);
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
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			//
		} else if (answer.equals("no")) {
			//
		}
	}
}
