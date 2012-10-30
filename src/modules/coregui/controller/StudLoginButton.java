package modules.coregui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;

public class StudLoginButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreGUI().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Studenten Profil", null, Bootstrap.serviceManager.getStudProfil(), null);
		Bootstrap.serviceManager.getCoreGUI().mainTabbedContainerPane.setSelectedIndex(1);
		Bootstrap.serviceManager.getCoreGUI().startScreenPanel.setVisible(false);
	}
}
