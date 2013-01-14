package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;


public class ProfessorshipFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public ProfessorshipFrameBtns() {
		this.ctrlAction = "default";
	}
	public ProfessorshipFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			ServiceManager.getInstance().getOrgaProfessorshipFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			ServiceManager.getInstance().getOrgaProfessorshipFrame().setVisible(false);
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
