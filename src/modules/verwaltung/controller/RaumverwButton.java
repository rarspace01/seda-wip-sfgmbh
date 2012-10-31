package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;


public class RaumverwButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Verwaltung", null, Bootstrap.serviceManager.getVerwaltung(), null);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.getTabCount()-1);
	}
}