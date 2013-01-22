package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Action listener for the combobox on the professorship timetable
 * 
 * @author denis
 *
 */
public class ProfessorshipTimetableTabCmbbox implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ViewManager.getInstance().getChairTimetableTab().reloadPlan();
	}
	
}
