package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;


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
			Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(false);
			this.getInfoWindow("<b>Ihr Lehrstuhl konnte nicht hinzugefügt werden:</b><br><br>1. Sie haben keinen Lehrstuhlnamen eingegeben.<br>2. Der Lehrstuhl existiert bereits.<br>3. Sie haben keinen Lehrstuhlinhaber eingegeben.<br>4. Der Lehrstuhlinhaber existiert bereits.<br>5. Sie haben kein Hauptgebäude eingegeben.<br>6. Sie haben keinen Stock eingegeben.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
