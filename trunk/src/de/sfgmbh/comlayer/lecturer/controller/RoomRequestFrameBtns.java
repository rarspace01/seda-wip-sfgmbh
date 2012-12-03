package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class RoomRequestFrameBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public RoomRequestFrameBtns() {
		this.ctrlAction = "default";
	}
	public RoomRequestFrameBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(false);
		}
		
		// Los button is pressed
		if (this.ctrlAction.equals("go")){
			Bootstrap.serviceManager.getLecturerRoomRequestFrame().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
