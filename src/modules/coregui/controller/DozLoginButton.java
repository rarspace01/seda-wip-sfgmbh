package modules.coregui.controller;

import gui.CoreGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DozLoginButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CoreGUI.serviceManager.getCoreGUI().mainTabbedContainerPane.setVisible(true);
		CoreGUI.serviceManager.getCoreGUI().mainTabbedContainerPane.removeAll();
		CoreGUI.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Start", null, CoreGUI.serviceManager.getCoreGUI().startScreenPanel, null);
		CoreGUI.serviceManager.getCoreGUI().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, CoreGUI.serviceManager.getDozenten(), null);
		CoreGUI.serviceManager.getCoreGUI().mainTabbedContainerPane.setSelectedIndex(1);
		CoreGUI.serviceManager.getCoreGUI().startScreenPanel.setVisible(false);
	}
}