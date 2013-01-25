package de.sfgmbh.applayer.core.controller;

import de.sfgmbh.applayer.core.definitions.IntfCtrlBaseTab;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;

/**
 * BaseTab Controller
 * 
 * @author hannes
 *
 */
public class CtrlBaseTab implements IntfCtrlBaseTab {
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlBaseTab#login(java.lang.String, java.lang.String)
	 */
	@Override
	public IntfUser login(String login, String pw) {
		
		IntfDataUser dataHandlerUser = new DataHandlerUser();
		IntfUser checkUser = dataHandlerUser.getByLogin(login);
		
		if (checkUser != null) {
			if (checkUser.checkPassword(pw) == true) {
				
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlBaseTab#logout()
	 */
	@Override
	public void logout() {
		SessionManager.getInstance().clearSession();
	}

}
