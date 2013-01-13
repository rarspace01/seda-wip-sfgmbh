package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.User;

public class SessionManager {
	
	private static SessionManager uniqueInstance_ = new SessionManager();
	private boolean isLoggedIn;
	private User user;
	
	public SessionManager() {
		uniqueInstance_ = this;
		this.isLoggedIn = false;
		this.user = null;
	}
	
	public static SessionManager getInstance() {
		return uniqueInstance_;
	}

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
