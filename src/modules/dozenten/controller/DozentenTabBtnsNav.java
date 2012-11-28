package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;

import services.Bootstrap;

public class DozentenTabBtnsNav implements ActionListener {
	
	private String navAction;
	protected InfoWindow infoWindow;
	
	
	public DozentenTabBtnsNav() {
		this.navAction = "default";
	}
	public DozentenTabBtnsNav(String action) {
		this.navAction = action;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Studenplan button is pressed
		if (this.navAction.equals("Stundenplan")) {
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, Bootstrap.serviceManager.getDozStundenplanTab(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// Lehrstuhlplan button is pressed
		if (this.navAction.equals("Lehrstuhlplan")) {
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Lehrstuhlplan", null, Bootstrap.serviceManager.getDozLehrstuhlplanTab(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
		}
		
		// LiveTicker button is pressed
		if (this.navAction.equals("LiveTicker")) {
			this.getInfoWindow("LiveTicker Bearbeitung momentan noch nicht implementiert!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
