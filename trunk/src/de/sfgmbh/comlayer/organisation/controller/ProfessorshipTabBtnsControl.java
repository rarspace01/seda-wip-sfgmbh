package comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;

import applayer.core.controller.Bootstrap;


public class ProfessorshipTabBtnsControl implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public ProfessorshipTabBtnsControl() {
		this.navAction = "default";
	}
	public ProfessorshipTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Lehrstuhl hinzuf�gen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(true);
		}
		
		// Lehrstuhl bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(true);
		}
		
		// Lehrstuhl l�schen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("Lehrstuhl wird gel�scht!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
