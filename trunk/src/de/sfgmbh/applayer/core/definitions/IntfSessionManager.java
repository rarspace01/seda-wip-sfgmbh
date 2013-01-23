package de.sfgmbh.applayer.core.definitions;

/**
 * Interface for the session manager
 * @author hannes
 *
 */
public interface IntfSessionManager {

	/**
	 * Dispose the current session manager
	 */
	public abstract void dispose();

	/**
	 * Get the currently logged in user
	 * @return the logged in use
	 */
	public abstract IntfUser getSession();

	/**
	 * Start a user session
	 * @param user
	 */
	public abstract void setSession(IntfUser user);

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