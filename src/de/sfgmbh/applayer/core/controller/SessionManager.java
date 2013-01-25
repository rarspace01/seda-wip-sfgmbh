package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.definitions.IntfSessionManager;
import de.sfgmbh.applayer.core.definitions.IntfUser;

/**
 * Session manager class which is used to store, retrieve and manage user
 * sessions
 * 
 * @author hannes
 * 
 */
public class SessionManager implements IntfSessionManager {

	private static IntfSessionManager uniqueInstance_ = new SessionManager();
	private boolean isLoggedIn_;
	private IntfUser user_;

	/**
	 * Create the session manager singleton
	 */
	private SessionManager() {
		this.isLoggedIn_ = false;
		this.user_ = null;
	}

	/**
	 * Get the session manager
	 * 
	 * @return the SessionManager
	 */
	public static IntfSessionManager getInstance() {
		if (uniqueInstance_ == null) {
			uniqueInstance_ = new SessionManager();
		}
		return uniqueInstance_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#dispose()
	 */
	@Override
	public void dispose() {
		isLoggedIn_ = false;
		user_ = null;
		uniqueInstance_ = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#getSession()
	 */
	@Override
	public IntfUser getSession() {
		return this.user_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.controller.IntfSessionManager#setSession(de.sfgmbh
	 * .applayer.core.model.User)
	 */
	@Override
	public void setSession(IntfUser user) {
		this.user_ = user;
		this.isLoggedIn_ = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#checkSession()
	 */
	@Override
	public boolean checkSession() {
		return this.isLoggedIn_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#clearSession()
	 */
	@Override
	public void clearSession() {
		this.user_ = null;
		this.isLoggedIn_ = false;
	}

}
