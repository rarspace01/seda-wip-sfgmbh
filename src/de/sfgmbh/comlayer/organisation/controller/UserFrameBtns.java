package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


public class UserFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public UserFrameBtns() {
		this.ctrlAction = "default";
	}
	public UserFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getOrgaUserFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			Bootstrap.serviceManager.getOrgaUserFrame().setVisible(false);
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Der Nutzer konnte nicht hinzugef�gt werden:<br>1. Es existiert bereits ein Nutzer mit dieser Nutzerkennung." +
					"<br>2. Es existiert bereits ein Nutzer mit dieser E-Mail-Adresse.<br>3. Das eingegebene Passwort ist ung�ltig.<br>" +
					"(Das Passwort muss mindestens 8 Zeichen umfassen, einen Gro�-und Kleinbuchstaben enthalten sowie eine Zahl und ein Sonderzeichen beinhalten)" +
					"<br>4. Sie haben keine Nutzerklasse gew�hlt.<br>5. Sie haben keinen Lehrstuhl gew�hlt.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
