package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;

public class StundenplanButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, Bootstrap.serviceManager.getDozStundenplanTab(), null);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.getTabCount()-1);
	}
}
