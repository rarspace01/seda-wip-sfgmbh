package de.sfgmbh.datalayer.core.definitions;

import java.sql.ResultSet;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.Room;

/**
 * interface for handling the Room table in the database
 * 
 * @author denis
 * 
 */
public interface IntfDataRoom {

	/**
	 * gets all {@link Room}s from the database
	 * @return List of Room objects which are present in the database
	 */
	public List<IntfRoom> getAll();

	/**
	 * 
	 * @param roomId
	 *            - room id in the database
	 * @return {@link Room} object
	 */
	public IntfRoom get(int roomId);

	/**
	 * 
	 * @param room
	 *            - the object which should be remove from the database
	 * @return
	 */
	public void delete(IntfRoom room);

	/**
	 * 
	 * @param room
	 *            - object of type Room to be saved in DB
	 * @return - int for error handling
	 */
	public void save(IntfRoom room);
	
	/**
	 * creates Room objects from a given {@link ResultSet}
	 * @param resultSet
	 * @return {@link Room}
	 */
	public IntfRoom makeRoom(ResultSet resultSet);
	
}
