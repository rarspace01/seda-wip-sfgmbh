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
	 * 
	 * @return List of Room objects which are present in the database
	 */
	public List<IntfRoom> getAll();

	/**
	 * return a {@link Room} Object from the given roomId
	 * 
	 * @param roomId
	 *            - room id in the database
	 * @return {@link Room}
	 */
	public IntfRoom get(int roomId);

	/**
	 * deletes a given object from the database
	 * 
	 * @param room
	 *            - the object which should be remove from the database
	 */
	public void delete(IntfRoom room);

	/**
	 * saves an given Room Object to the database
	 * 
	 * @param room
	 *            - object of type Room to be saved in DB
	 */
	public void save(IntfRoom room);

	/**
	 * Forms a {@link Room} object out of a given {@link ResultSet}
	 * 
	 * @param resultSet
	 *            - {@link ResultSet}
	 * 
	 * @return a {@link Room} object
	 */
	public IntfRoom makeRoom(ResultSet resultSet);

}
