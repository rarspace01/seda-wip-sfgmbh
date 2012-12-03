package comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;

import applayer.core.controller.Bootstrap;



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
		
		// Studenplan button is pressed
		if (this.navAction.equals("timetable")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, Bootstrap.serviceManager.getLecturerTimetableTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Lehrstuhlplan button is pressed
		if (this.navAction.equals("professorshiptimetable")) {
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("professorshiptimetable", null, Bootstrap.serviceManager.getLecturerProfessorshipTimetableTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// LiveTicker button is pressed
		if (this.navAction.equals("liveticker")) {
			this.getInfoWindow("LiveTicker Bearbeitung momentan noch nicht implementiert!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
