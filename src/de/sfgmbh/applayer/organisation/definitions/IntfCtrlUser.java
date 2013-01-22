package de.sfgmbh.applayer.organisation.definitions;

import de.sfgmbh.applayer.core.model.User;

public interface IntfCtrlUser {

	/**
	 * Create or edit a user if it is a valid user
	 * @param user
	 * @return true if the creation was successful
	 */
	public abstract boolean saveUser(User user);

	/**
	 * Delete a user if possible
	 * @param user
	 * @return true on success
	 */
	public abstract boolean delete(User user);

}