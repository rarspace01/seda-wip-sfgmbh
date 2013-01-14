package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;



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
					this.getInfoWindow("<strong>Fehlermeldung:</strong><br> Es konnte keine �bersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgew�hlt!").setVisible(true);	
		}
		//Test if hidden objects can be accessed (this my be removed)
		int test = ServiceManager.getInstance().getCoreBaseTab().getOrganisationTable().getSelectedRow();
		System.out.println(ServiceManager.getInstance().getCoreBaseTableModel().getValueAt(test, 9).toString());
		//Go to the timetable tab
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.removeAll();
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, ServiceManager.getInstance().getCoreBaseTab().startScreenPanel, null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Stundenplan", null, ServiceManager.getInstance().getPublicTimetableTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().startScreenPanel.setVisible(false);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
	}
	
	

	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}

