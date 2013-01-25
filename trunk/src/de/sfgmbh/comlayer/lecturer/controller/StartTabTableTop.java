package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Mouse action listener for the top table
 * 
 * @author christian
 * 
 */
public class StartTabTableTop extends MouseAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Set entries in the bottom table that match the entry selected in the
		// top table
		ViewManager.getInstance().getLecturerStartTabTableBottom()
				.change("select");
	}

}
