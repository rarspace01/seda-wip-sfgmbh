package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class BaseBtnAddToStundenplan implements ActionListener {
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public BaseBtnAddToStundenplan() {
		this.navAction = "default";
	}
	public BaseBtnAddToStundenplan(String action) {
		this.navAction = action;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//Add to Stundenplan button is pressed
		if (this.navAction.equals("plus")) {
					this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Es konnte keine Übersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgewählt!").setVisible(true);	
		}
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Stundenplan", null, Bootstrap.serviceManager.getPublicTimetableTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
	}
	
	

	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}

