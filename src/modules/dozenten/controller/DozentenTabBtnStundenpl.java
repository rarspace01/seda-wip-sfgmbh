package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;

public class DozentenTabBtnStundenpl implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, Bootstrap.serviceManager.getDozStundenplanTab(), null);
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
	}
}
