package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.Bootstrap;

public class DozentenTabBtnLPlan implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Lehrstuhlplan", null, Bootstrap.serviceManager.getDozLehrstuhlplanTab(), null);
		Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(Bootstrap.serviceManager.getBase().mainTabbedContainerPane.getTabCount()-1);
	}
}
