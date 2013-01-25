package de.sfgmbh.applayer.organisation.definitions;

import de.sfgmbh.applayer.core.definitions.IntfUser;

/**
 * Interface for the user controller
 * 
 * @author hannes
 * 
 */
public interface IntfCtrlUser {

	/**
	 * Create or edit a user if it is a valid user
	 * 
	 * @param user
	 * @return true if the creation was successful
	 */
	public abstract boolean saveUser(IntfUser user);

	/**
	 * Delete a user if possible
	 * 
	 * @param user
	 * @return true on success
	 */
	public abstract boolean delete(IntfUser user);

}