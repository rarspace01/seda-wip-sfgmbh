package de.sfgmbh.applayer.organisation.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;

/**
 * Interface for the room controller
 * 
 * @author denis
 *
 */
public interface IntfCtrlRoom {

	/**
	 * Add a room to the model
	 * @param room
	 */
	public abstract void addRoom(IntfRoom room);

	/**
	 * Retrieve all rooms from the model
	 * @return
	 */
	public abstract List<IntfRoom> getAllRooms();

	/**
	 * Delete a room from the model
	 * @param room
	 */
	public abstract void delete(IntfRoom room);
	
	/**
	 * get a room by hsi id
	 * @param int
	 */
	public abstract IntfRoom get(int roomId);
	

}