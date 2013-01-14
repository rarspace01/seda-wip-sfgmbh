package de.sfgmbh.applayer.organisation.controller;

import java.util.List;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;

public class CtrlRoom {

	public void addRoom(Room room){
		
		AppModel.getInstance().getRepositoryRoom().save(room);
		
	}
	
	public List<Room> getAllRooms(){
		return AppModel.getInstance().getRepositoryRoom().getAll();
	}
	
	
}
