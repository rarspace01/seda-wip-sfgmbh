package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;

/**
 * Action listener for the okay button in the info dialog
 * 
 * @author hannes
 *
 */
public class InfoDialogBtnOk implements ActionListener {
	
	protected InfoDialog infoWinwod;
	
	/**
	 * Create the action listener
	 * @param transmittedInfoWindow
	 */
	public InfoDialogBtnOk(InfoDialog transmittedInfoWindow) {
		this.infoWinwod = transmittedInfoWindow;
	}
	
	/**
	 * Close on action
	 */
	public void actionPerformed(ActionEvent e) {
		infoWinwod.dispose();
	}
}