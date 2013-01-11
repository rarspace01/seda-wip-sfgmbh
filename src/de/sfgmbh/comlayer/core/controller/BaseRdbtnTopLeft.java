package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;

public class BaseRdbtnTopLeft implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	protected CtrlBaseTab ctrlBaseTab;
	
	public BaseRdbtnTopLeft(){
		this.ctrlAction ="default";
	}
	
	public BaseRdbtnTopLeft(String action){
		this.ctrlAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//room-JRadioButton is pressed
		if (this.ctrlAction.equals("room")){
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(false);
			Bootstrap.serviceManager.getCoreBaseTab().contentPane.add(Bootstrap.serviceManager.getCoreBaseTab().roomPanel, "name_5256771068822");
			Bootstrap.serviceManager.getCoreBaseTab().roomPanel.setVisible(true);
		}
	}
}