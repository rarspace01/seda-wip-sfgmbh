package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.sfgmbh.applayer.core.controller.ServiceManager;

public class StartTabTableTop extends MouseAdapter {
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		ServiceManager.getInstance().getLecturerStartTabTableBottom().change("select");
	}
	
}
