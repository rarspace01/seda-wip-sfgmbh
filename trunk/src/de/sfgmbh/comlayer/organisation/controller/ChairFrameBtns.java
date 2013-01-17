package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;


public class ChairFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public ChairFrameBtns() {
		this.ctrlAction = "default";
	}
	public ChairFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			ViewManager.getInstance().getOrgaChairFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			ViewManager.getInstance().getOrgaChairFrame().setVisible(false);
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Ihr Lehrstuhl konnte nicht hinzugef�gt werden:<br>1. Sie haben keinen Lehrstuhlnamen eingegeben.<br>2. Der Lehrstuhl existiert bereits." +
					"<br>3. Sie haben keinen Lehrstuhlinhaber eingegeben.<br>4. Der Lehrstuhlinhaber existiert bereits.<br>5. Sie haben kein Hauptgeb�ude eingegeben.<br>6. Sie haben keinen Stock eingegeben.").setVisible(true);		
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
