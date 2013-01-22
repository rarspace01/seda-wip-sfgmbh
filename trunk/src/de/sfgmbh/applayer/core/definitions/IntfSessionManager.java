package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.User;

public interface IntfSessionManager {

	/**
	 * Dispose the current session manager
	 */
	public abstract void dispose();

	/**
	 * Get the currently logged in user
	 * @return the logged in use
	 */
	public abstract User getSession();

	/**
	 * Start a user session
	 * @param user
	 */
	public abstract void setSession(User user);

	/**
	 * Check if there currently is a session
	 * @return true or false
	 */
	public abstract boolean checkSession();

	/**
	 * Reset the current session
	 */
	public abstract void clearSession();

}