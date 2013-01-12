package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;

public class CtrlRoom {

	public void addRoom(Room room){
		
		AppModel.getInstance().repositoryRoom.save(room);
		
	}
	
}
