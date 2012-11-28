package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;


public class LehrstuhlverwButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, Bootstrap.serviceManager.getVerwLehrstuhl(), null);
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
	}
}