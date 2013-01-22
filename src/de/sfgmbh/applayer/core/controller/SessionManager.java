package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.definitions.IntfSessionManager;
import de.sfgmbh.applayer.core.model.User;

/**
 * Session manager class which is used to store, retrieve and manage user sessions
 * 
 * @author hannes
 *
 */
public class SessionManager implements IntfSessionManager {
	
	private static IntfSessionManager uniqueInstance_ = new SessionManager();
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
	public static IntfSessionManager getInstance() {
		return uniqueInstance_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#dispose()
	 */
	@Override
	public void dispose() {
		uniqueInstance_ = null;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#getSession()
	 */
	@Override
	public User getSession() {
		return this.user;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#setSession(de.sfgmbh.applayer.core.model.User)
	 */
	@Override
	public void setSession(User user) {
		this.user = user;
		this.isLoggedIn = true;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#checkSession()
	 */
	@Override
	public boolean checkSession() {
		return this.isLoggedIn;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfSessionManager#clearSession()
	 */
	@Override
	public void clearSession() {
		this.user = null;
		this.isLoggedIn = false;
	}

}
