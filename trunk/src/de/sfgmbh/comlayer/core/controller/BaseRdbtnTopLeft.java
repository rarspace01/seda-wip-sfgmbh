package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

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
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().removeAll();
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().repaint();
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblRoomnumber(), "cell 0 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblBuildingid(), "cell 1 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblLevel(), "cell 2 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxRoomnumberFilter(), "cell 0 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxBuildingidFilter(), "cell 1 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxLevelFilter(), "cell 2 1,aligny bottom");
		}
		
		//course-JRadioButton is pressed
		if (this.ctrlAction.equals("course")){
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainTableScrollPane().setViewportView(ViewManager.getInstance().getCoreBaseTab().getOrganisationTable());
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainTableScrollPane().setViewportView(ViewManager.getInstance().getCoreBaseTab().getOrganisationTable());	
		}
	}
}