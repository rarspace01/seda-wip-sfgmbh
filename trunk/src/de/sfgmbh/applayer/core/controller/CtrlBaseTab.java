package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.repositories.DataHandlerUser;

public class CtrlBaseTab {
	
	// Login action
	public User login(String login, String pw) {
		
		DataHandlerUser dhu = new DataHandlerUser();
		User checkUser = dhu.getByLogin(login);
		
		if (checkUser != null) {
			if (checkUser.checkPw(pw) == true) {
				ServiceManager.getInstance().getSessionManager().setSession(checkUser);
				checkUser.setLastLogin_((long) System.currentTimeMillis()/1000);
				checkUser.save();
				return checkUser;
			}
		}
		
		return null;
	}
	
	// Logout action
	public void logout() {
		ServiceManager.getInstance().getSessionManager().clearSession();
	}

}
