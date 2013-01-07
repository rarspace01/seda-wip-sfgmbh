package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.User;

public class SessionManager {
	
	private boolean isLoggedIn;
	private User user;
	
	public SessionManager() {
		this.isLoggedIn = false;
		this.user = null;
	}
	
	public User getSession() {
		return this.user;
	}
	
	public void setSession(User user) {
		this.user = user;
		this.isLoggedIn = true;
	}
	
	public boolean checkSession() {
		return this.isLoggedIn;
	}
	
	public void clearSession() {
		this.user = null;
		this.isLoggedIn = false;
	}

}
