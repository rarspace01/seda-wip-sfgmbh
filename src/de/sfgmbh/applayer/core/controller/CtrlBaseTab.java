package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;

public class CtrlBaseTab {
	
	/**
	 * Login action
	 * @param login
	 * @param pw
	 * @return the user object if login credentials are correct (and the user is not disabled) and otherwise null
	 */
	public User login(String login, String pw) {
		
		DataHandlerUser dhu = new DataHandlerUser();
		User checkUser = dhu.getByLogin(login);
		
		if (checkUser != null) {
			if (checkUser.checkPw(pw) == true) {
				
				// Don't log in when user is disabled
				if (checkUser.isDisabled_()) {
					AppModel.getInstance().appExcaptions.setNewException("Ihr Benutzerkonto ist gesperrt.<br />Für genauere Informationen wenden Sie sich bitte an die Verwaltung.", "Fehler!");
					return null;
				}
				
				ServiceManager.getInstance().getSessionManager().setSession(checkUser);
				checkUser.setLastLogin_((long) System.currentTimeMillis()/1000);
				checkUser.save();
				return checkUser;
			}
		}
		
		AppModel.getInstance().appExcaptions.setNewException("Der eingegebene Benutzername oder das eingegebene Passwort ist inkorrekt! <p>Bitte versuche es noch einmal.</p>", "Fehler!");
		return null;
	}
	
	/**
	 * Logout action
	 */
	public void logout() {
		ServiceManager.getInstance().getSessionManager().clearSession();
	}

}
