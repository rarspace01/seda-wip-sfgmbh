package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;





public class BtnsNav implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public BtnsNav() {
		this.navAction = "default";
	}
	public BtnsNav(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Nutzerverwaltungs Button is pressed
		if (this.navAction.equals("Nutzerverw")) {
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Nutzerverwaltung", null, ViewManager.getInstance().getOrgaUserTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
		
		// Lehrstuhlverwaltung Button is pressed
		if (this.navAction.equals("Lehrstuhlverw")) {
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlverwaltung", null, ViewManager.getInstance().getOrgaChairTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
		
		// Raumverwaltung Button is pressed
		if (this.navAction.equals("Raumverw")) {
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumverwaltung", null, ViewManager.getInstance().getOrgaRoomTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
		
		// LiveTicker Button is pressed
		if (this.navAction.equals("liveticker")) {
			this.getInfoWindow("Live-Ticker-Bearbeitung ist momentan noch nicht implementiert!").setVisible(true);
		}
		
		// Anfrageverwaltung Button is pressed
		if (this.navAction.equals("Anfrageverw")) {
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumanfrage-Management", null, ViewManager.getInstance().getOrgaRquestTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}