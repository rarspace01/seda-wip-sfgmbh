package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.Building;

/**
 * class for handling the Building table in the database
 * 
 * @author denis
 * 
 */
public interface IntfDataBuilding {

	/**
	 * 
	 * @return List of Building objects which are present in the database
	 */
	public List<Building> getAll();

	/**
	 * 
	 * @param iBuildingId
	 *            - room id in the database
	 * @return Building object
	 */
	public Building get(int iBuildingId);

	/**
	 * 
	 * @param searchQry - Search pattern
	 * @return Room objects
	 */
	public List<Building> search(String searchQry);

	/**
	 * 
	 * @param filterQry - filter according to pattern.
	 * <br/>Patter example: "{@code building=ERBA&level=2.1}"
	 * <br/>"{@code building=FEKI&level=2&pc_min=10}"  
	 *            
	 * @return Building objects
	 */
	public List<Building> filter(String filterQry);
	
	/**
	 * 
	 * @param toBeDeletedBuilding
	 *            - the object which should be remove from the database
	 * @return
	 */
	public int delete(Building toBeDeletedBuilding);

	/**
	 * 
	 * @param toBeSavedBuilding
	 *            - object of type Room to be saved in DB
	 * @return - int for error handling
	 */
	public int save(Building toBeSavedBuilding);

}
