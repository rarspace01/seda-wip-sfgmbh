package de.sfgmbh.applayer.organisation.controller;

import java.util.List;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoom;

public class CtrlRoom implements IntfCtrlRoom {

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#addRoom(de.sfgmbh.applayer.core.model.Room)
	 */
	@Override
	public void addRoom(Room room){
		
		AppModel.getInstance().getRepositoryRoom().save(room);
		
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#getAllRooms()
	 */
	@Override
	public List<Room> getAllRooms(){
		return AppModel.getInstance().getRepositoryRoom().getAll();
	}
	
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#delete(de.sfgmbh.applayer.core.model.Room)
	 */
	@Override
	public void delete(Room room){
		//TODO implement
	}
	
}
