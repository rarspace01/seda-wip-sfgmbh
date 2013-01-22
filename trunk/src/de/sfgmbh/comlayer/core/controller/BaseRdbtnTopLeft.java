package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfCtrlBaseTab;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
/**
 * 
 * @author hannes
 * @author mario
 *
 */
/**
 * Radio button action listener on the base tab
 * 
 * @author mario
 *
 */
public class BaseRdbtnTopLeft implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	protected IntfCtrlBaseTab ctrlBaseTab;
	
	/**
	 * Create the action listener 
	 */
	public BaseRdbtnTopLeft(){
		this.ctrlAction ="default";
	}
	/**
	 * sets the action for the different selection for the rdbtns  (room or course list)
	 * @param action
	 */
	/**
	 * Create the action listener based on an action string
	 */
	public BaseRdbtnTopLeft(String action){
		this.ctrlAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();

		//room-JRadioButton is pressed
		if (this.ctrlAction.equals("room")){
			baseTab.getRoomTable().setVisible(true);
			baseTab.getOrganisationTable().setVisible(false);
			baseTab.getMainTableScrollPane().setViewportView(baseTab.getRoomTable());
			baseTab.getRoomTable().setVisible(true);
			baseTab.getMainFilterPanel().setVisible(false);
			baseTab.getMainFilterPanel().removeAll();
			baseTab.getMainFilterPanel().add(baseTab.getLblRoomnumber(), "cell 0 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getLblRoomplaces(), "cell 1 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getLblLevel(), "cell 2 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxRoomnumberFilter(), "cell 0 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxSeatsFilter(), "cell 1 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxLevelFilter(), "cell 2 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().setVisible(true);
			baseTab.getBtnRoomplan().setVisible(true);
			baseTab.getBtnTimetable().setVisible(false);
		}
		
		//course-JRadioButton is pressed
		if (this.ctrlAction.equals("course")){
			baseTab.getOrganisationTable().setVisible(true);
			baseTab.getRoomTable().setVisible(false);
			baseTab.getMainTableScrollPane().setViewportView(baseTab.getOrganisationTable());
			baseTab.getOrganisationTable().setVisible(true);
			baseTab.getMainFilterPanel().setVisible(false);
			baseTab.getMainFilterPanel().removeAll();
			baseTab.getMainFilterPanel().add(baseTab.getLblLehrveranstaltung(), "cell 0 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getLblLehrstuhl(), "cell 1 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getLblDozent(), "cell 2 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getLblSemester(), "cell 3 0,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxOrgaFilter(), "cell 0 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxChairFilter(), "cell 1 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxLecturerFilter(), "cell 2 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().add(baseTab.getComboBoxSemesterFilter(), "cell 3 1,aligny bottom, grow");
			baseTab.getMainFilterPanel().setVisible(true);
			baseTab.getBtnRoomplan().setVisible(false);
			baseTab.getBtnTimetable().setVisible(true);
		}
	}
}