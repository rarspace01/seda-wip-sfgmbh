package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.User;

/**
 * Session manager class which is used to store, retrieve and manage user sessions
 * 
 * @author hannes
 *
 */
public class SessionManager {
	
	private static SessionManager uniqueInstance_ = new SessionManager();
	private boolean isLoggedIn;
	private User user;
	
	/**
	 * Create the session manager singleton
	 */
	public SessionManager() {
		uniqueInstance_ = this;
		this.isLoggedIn = false;
		this.user = null;
	}
	
	/**
	 * Get the session manager
	 * @return the SessionManager
	 */
	public static SessionManager getInstance() {
		return uniqueInstance_;
	}

	/**
	 * Dispose the current session manager
	 */
	public void dispose() {
		uniqueInstance_ = null;
	}
	
	/**
	 * Get the currently logged in user
	 * @return the logged in use
	 */
	public User getSession() {
		return this.user;
	}
	
	/**
	 * Start a user session
	 * @param user
	 */
	public void setSession(User user) {
		this.user = user;
		this.isLoggedIn = true;
	}
	
	/**
	 * Check if there currently is a session
	 * @return true or false
	 */
	public boolean checkSession() {
		return this.isLoggedIn;
	}
	
	/**
	 * Reset the current session
	 */
	public void clearSession() {
		this.user = null;
		this.isLoggedIn = false;
	}

}
