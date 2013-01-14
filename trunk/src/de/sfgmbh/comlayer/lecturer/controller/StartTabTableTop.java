package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.sfgmbh.comlayer.core.controller.ViewManager;

public class StartTabTableTop extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		ViewManager.getInstance().getLecturerStartTabTableBottom().change("select");
	}
	
}
