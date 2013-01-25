package de.sfgmbh.datalayer.core.definitions;

import java.sql.ResultSet;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.datalayer.core.daos.DataHandlerChair;
/**
 * This is the interface for the {@link DataHandlerChair}
 * @author denis
 *
 */	
public interface IntfDataChair {

	/**
	 * 
	 * @return List of Chair objects which are present in the database
	 */
	public List<IntfChair> getAll();

	/**
	 * 
	 * @param chairId
	 *            - Chair id in the database
	 * @return {@link Chair} object
	 */
	public IntfChair get(int chairId);

	/**
	 * 
	 * @param chair
	 *            - the object which should be remove from the database
	 * @return {@link Boolean} hasDelteted
	 */
	public boolean delete(IntfChair chair);

	/**
	 * 
	 * @param chair
	 *            - object of type Chair to be saved in DB
	 * @return - int for error handling
	 */
	public boolean save(IntfChair chair);
	
	/**
	 * 
	 * @param resultSet - {@link ResultSet}
	 * @return {@link IntfChair} - generates an {@link Chair} Object
	 */
	public IntfChair makeChair(ResultSet resultSet);
	
	/**
	 * Get the chair of a user
	 * 
	 * @param userId
	 * @return a Chair if the submitted user (id) can be associated with one,
	 *         otherwise returns null
	 */
	public IntfChair getForUser(int userId);
	
	/**
	 * Get the chair based on its acronym
	 * 
	 * @param acronym
	 * @return a chair if the submitted acronym can be associated with one,
	 *         otherwise returns null
	 */
	public IntfChair getForAcronym(String acronym);
	
	
	
}
