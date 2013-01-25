package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Action listener for the combobox on the timetable
 * 
 * @author christian
 * @author denis
 *
 */
public class TimetableTabCmbbox implements ActionListener {
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ViewManager.getInstance().getLecturerTimetableTab().reloadPlan();
	}
	
}
