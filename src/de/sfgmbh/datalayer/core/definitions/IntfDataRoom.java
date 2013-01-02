package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.Room;

/**
 * class for handling the Room table in the database
 * 
 * @author denis
 * 
 */
public interface IntfDataRoom {

	/**
	 * 
	 * @return List of Room objects which are present in the database
	 */
	public List<Room> getAll();

	/**
	 * 
	 * @param iRoomId
	 *            - room id in the database
	 * @return Room object
	 */
	public Room get(int iRoomId);

	/**
	 * 
	 * @param searchQry - Search pattern
	 * @return Room objects
	 */
	public List<Room> search(String searchQry);

	/**
	 * 
	 * @param filterQry - filter according to pattern.
	 * <br/>Patter example: "{@code building=ERBA&level=2.1}"
	 * <br/>"{@code building=FEKI&level=2&pc_min=10}"  
	 *            
	 * @return Room objects
	 */
	public List<Room> filter(String filterQry);
	
	/**
	 * 
	 * @param toBeDeletedRoom
	 *            - the object which should be remove from the database
	 * @return
	 */
	public int delete(Room toBeDeletedRoom);

	/**
	 * 
	 * @param toBeSavedRoom
	 *            - object of type Room to be saved in DB
	 * @return - int for error handling
	 */
	public int save(Room toBeSavedRoom);

	
	
}
