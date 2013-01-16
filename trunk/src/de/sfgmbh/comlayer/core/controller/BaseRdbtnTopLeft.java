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
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().removeAll();
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblRoomnumber(), "cell 0 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblRoomplaces(), "cell 1 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblLevel(), "cell 2 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxRoomnumberFilter(), "cell 0 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxRoomplacesFilter(), "cell 1 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxLevelFilter(), "cell 2 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().setVisible(true);
		}
		
		//course-JRadioButton is pressed
		if (this.ctrlAction.equals("course")){
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getRoomTable().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainTableScrollPane().setViewportView(ViewManager.getInstance().getCoreBaseTab().getOrganisationTable());
			ViewManager.getInstance().getCoreBaseTab().getOrganisationTable().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().removeAll();
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblLehrveranstaltung(), "cell 0 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblLehrstuhl(), "cell 1 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblDozent(), "cell 2 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getLblSemester(), "cell 3 0,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxOrgaFilter(), "cell 0 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxChairFilter(), "cell 1 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxLecturerFilter(), "cell 2 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().add(ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter(), "cell 3 1,aligny bottom");
			ViewManager.getInstance().getCoreBaseTab().getMainFilterPanel().setVisible(true);
		}
	}
}