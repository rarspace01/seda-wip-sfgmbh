package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;

public class BaseBtnFailureprompt implements ActionListener {

	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public BaseBtnFailureprompt() {
		this.ctrlAction = "default";
	}
	public BaseBtnFailureprompt(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		// Fehlermeldung button is pressed
		if (this.ctrlAction.equals("error")){
			this.getInfoWindow("<strong> Es konnte keine Verbindung zur Datenbank aufgebaut werden. Bitte wenden Sie sich an Ihren Systemadministrator.</strong>").setVisible(true);
		}
		
	}
	// Manage InfoWindow instance
		public InfoDialog getInfoWindow(String msg) {
			this.infoWindow = new InfoDialog(msg);
			return this.infoWindow;
		}
		public InfoDialog getInfoWindow(String msg, String title) {
			this.infoWindow = new InfoDialog(msg, title);
			return this.infoWindow;
		}
}
