package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.User;

public interface IntfCtrlBaseTab {

	/**
	 * Login function to log a user in and eventually personalize the application for him
	 * 
	 * @param login
	 * @param password
	 * @return the user object if login credentials are correct (and the user is not disabled) and otherwise null
	 */
	public abstract User login(String login, String pw);

	/**
	 * Logout action
	 */
	public abstract void logout();

}