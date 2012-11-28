package modules.base.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.Bootstrap;


public class BaseBtnAddToStundenplan implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getBase().startScreenPanel, null);
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Stundenplan", null, Bootstrap.serviceManager.getStundenplan(), null);
		Bootstrap.serviceManager.getBase().startScreenPanel.setVisible(false);
	}
}
