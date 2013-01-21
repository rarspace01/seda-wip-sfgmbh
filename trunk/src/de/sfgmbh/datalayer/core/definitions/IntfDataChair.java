package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.Chair;

public interface IntfDataChair {

	/**
	 * 
	 * @return List of Chair objects which are present in the database
	 */
	public List<Chair> getAll();

	/**
	 * 
	 * @param iChairId
	 *            - Chair id in the database
	 * @return Chair object
	 */
	public Chair get(int ChairId);

	/**
	 * 
	 * @param searchQry - Search pattern
	 * @return Chair objects
	 */
	public List<Chair> search(String searchQry);

	/**
	 * 
	 * @param filterQry - filter according to pattern.
	 * <br/>Patter example: "{@code building=ERBA&level=2.1}"
	 * <br/>"{@code building=FEKI&level=2&pc_min=10}"  
	 *            
	 * @return Chair objects
	 */
	public List<Chair> filter(String filterQry);
	
	/**
	 * 
	 * @param Chair
	 *            - the object which should be remove from the database
	 * @return
	 */
	public boolean delete(Chair Chair);

	/**
	 * 
	 * @param Chair
	 *            - object of type Chair to be saved in DB
	 * @return - int for error handling
	 */
	public boolean save(Chair Chair);
	
}
