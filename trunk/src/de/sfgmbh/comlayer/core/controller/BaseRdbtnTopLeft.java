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
			Bootstrap.serviceManager.getCoreBaseTab().getRoomTable().setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().getOrganisationTable().setVisible(false);
			Bootstrap.serviceManager.getCoreBaseTab().getMainTableScrollPane().setViewportView(Bootstrap.serviceManager.getCoreBaseTab().getRoomTable());
		}
		
		//course-JRadioButton is pressed
		if (this.ctrlAction.equals("course")){
			Bootstrap.serviceManager.getCoreBaseTab().getOrganisationTable().setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().getRoomTable().setVisible(false);
			Bootstrap.serviceManager.getCoreBaseTab().getMainTableScrollPane().setViewportView(Bootstrap.serviceManager.getCoreBaseTab().getOrganisationTable());	
		}
	}
}