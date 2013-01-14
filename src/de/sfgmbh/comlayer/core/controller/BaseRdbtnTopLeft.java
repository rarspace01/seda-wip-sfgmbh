package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;

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
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainTableScrollPane().setViewportView(ViewManager.getInstance().getCoreBaseTab().getRoomTable());
		}
		
		//course-JRadioButton is pressed
		if (this.ctrlAction.equals("course")){
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainTableScrollPane().setViewportView(ViewManager.getInstance().getCoreBaseTab().getOrganisationTable());	
		}
	}
}