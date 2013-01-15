package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;




public class StartTabBtnsNav implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public StartTabBtnsNav() {
		this.navAction = "default";
	}
	public StartTabBtnsNav(String action) {
		this.navAction = action;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Stundenplan button is pressed
		if (this.navAction.equals("timetable")) {
			this.getInfoWindow("<strong>Fehlermeldung 1:</strong><br> Es konnte kein Dozentenstundenplan erstellt werden, da kein Dozent dem Lehrstuhl zugeordnet ist.<br><br>" +
					"<strong>Fehlermeldung 2:</strong><br>Es konnte kein Dozentenstundenplan erstellt werden, da noch keine Lehrveranstaltungen exisiteren.").setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Dozentenstundenplan", null, ViewManager.getInstance().getLecturerTimetableTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
		
		// Lehrstuhlplan button is pressed
		if (this.navAction.equals("professorshiptimetable")) {
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br>Es konnte kein Lehrstuhlplan erstellt werden, da noch keine Lehrveranstaltungen exisiteren.").setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlplan", null, ViewManager.getInstance().getLecturerProfessorshipTimetableTab(), null);
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().getTabCount()-1);
		}
		
		// LiveTicker button is pressed
		if (this.navAction.equals("liveticker")) {
			this.getInfoWindow("<strong>Fehlermeldung:</strong><br>" +
					"LiveTicker Bearbeitung momentan noch nicht implementiert!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
