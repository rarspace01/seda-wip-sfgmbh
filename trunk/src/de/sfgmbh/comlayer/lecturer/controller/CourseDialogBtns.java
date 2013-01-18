package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.lecturer.views.CourseDialog;

/**
 * Action listener for the course edit/create dialog
 * 
 * @author hannes
 * @author christian
 *
 */
public class CourseDialogBtns implements ActionListener {
	
	private String ctrlAction;
	private CourseDialog motherDialog;
	protected InfoDialog infoWindow;
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public CourseDialogBtns(CourseDialog motherDialog) {
		this.motherDialog = motherDialog;
		this.ctrlAction = "default";
	}
	
	/**
	 * Create the action listener based on an action string
	 * @param motherDialog
	 * @param action
	 */
	public CourseDialogBtns(CourseDialog motherDialog, String action) {
		this.motherDialog = motherDialog;
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Cancel button is pressed
		if (this.ctrlAction.equals("cancel")){
			this.motherDialog.dispose();
		}
		
		// Save button is pressed
		if (this.ctrlAction.equals("save")){
			
		}
	}
}
