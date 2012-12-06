package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



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
		
		// Lehrstuhl hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(true);
		}
		
		// Lehrstuhl bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {
			//Bootstrap.serviceManager.getOrgaProfessorshipFrame().setVisible(true);
			this.getInfoWindow("<b>Alternativen:</b><br><br>1. Sie haben keinen Lehrstuhl selektiert!<br>2. Sie gelangen sofort zu einer Bearbeiten-Maske, welche allerdings noch nicht implementiert ist.").setVisible(true);
		}
		
		// Lehrstuhl löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			this.getInfoWindow("<b>Der Lehrstuhl konnte nicht gelöscht werden:</b><br><br>Sie haben keinen Lehrstuhl selektiert.").setVisible(true);
		}
		
		// Fehlermeldung button is pressed
				if (this.navAction.equals("Fehlermeldung")){
					this.getInfoWindow("<strong> Es besteht keine Verbindung zur Datenbank, deswegen können die Lehrstühle nicht geladen werden</strong>").setVisible(true);
				}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
