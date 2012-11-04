package modules.coregui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;


public class AddToStundenplanButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreGUI().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Stundenplan", null, Bootstrap.serviceManager.getStundenplan(), null);
		Bootstrap.serviceManager.getCoreGUI().startScreenPanel.setVisible(false);
	}
}
