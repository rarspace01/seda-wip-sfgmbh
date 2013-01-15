package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		int test = ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().getSelectedRow();
		System.out.println(ViewManager.getInstance().getCoreBaseTableModel().getValueAt(test, 9).toString());
		//Go to the timetable tab
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(true);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Start", null, ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Stundenplan", null, ViewManager.getInstance().getPublicTimetableTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(false);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
	}
	
	

	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}

