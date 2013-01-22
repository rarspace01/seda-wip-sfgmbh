package de.sfgmbh.datalayer.core.definitions;

import java.sql.ResultSet;
import java.util.List;

import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.IntfChair;

public interface IntfDataChair {

	/**
	 * 
	 * @return List of Chair objects which are present in the database
	 */
	public List<IntfChair> getAll();

	/**
	 * 
	 * @param iChairId
	 *            - Chair id in the database
	 * @return Chair object
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
	
}
