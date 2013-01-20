package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;

/**
 * BaseTab Controller
 * 
 * @author hannes
 *
 */
public class CtrlBaseTab {
	
	/**
	 * Login function to log a user in and eventually personalize the application for him
	 * 
	 * @param login
	 * @param password
	 * @return the user object if login credentials are correct (and the user is not disabled) and otherwise null
	 */
	public User login(String login, String pw) {
		
		DataHandlerUser dhu = new DataHandlerUser();
		User checkUser = dhu.getByLogin(login);
		
		if (checkUser != null) {
			if (checkUser.checkPw(pw) == true) {
				
				// Don't log in when user is disabled
				if (checkUser.isDisabled_()) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ihr Benutzerkonto ist gesperrt.<br />Für genauere Informationen wenden Sie sich bitte an die Verwaltung.", "Fehler!");
					return null;
				}
				
				SessionManager.getInstance().setSession(checkUser);
				checkUser.setLastLogin_((long) System.currentTimeMillis()/1000);
				checkUser.save();
				
				return checkUser;
			}
		}
		
		AppModel.getInstance().getExceptionHandler().setNewException("Der eingegebene Benutzername oder das eingegebene Passwort ist inkorrekt! <p>Bitte versuche es noch einmal.</p>", "Fehler!");
		return null;
	}
	
	/**
	 * Logout action
	 */
	public void logout() {
		SessionManager.getInstance().clearSession();
	}

}
