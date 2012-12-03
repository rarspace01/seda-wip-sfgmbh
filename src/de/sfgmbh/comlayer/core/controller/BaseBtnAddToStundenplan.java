package comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import applayer.core.controller.Bootstrap;



public class BaseBtnAddToStundenplan implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Stundenplan", null, Bootstrap.serviceManager.getPublicTimetableTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
	}
}
