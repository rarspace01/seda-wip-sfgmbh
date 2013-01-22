package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlUser;

/**
 * Controller for user management
 * 
 * @author hannes
 *
 */
public class CtrlUser implements IntfCtrlUser {
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlUser#saveUser(de.sfgmbh.applayer.core.model.User)
	 */
	@Override
	public boolean saveUser(User user) {
		
		if (user.validate()) {
			if (user.save()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlUser#delete(de.sfgmbh.applayer.core.model.User)
	 */
	@Override
	public boolean delete(User user) {
		// Check if the users exists in the data base
		User delUser = AppModel.getInstance().getRepositoryUser().get(user.getUserId_());
		
		if (delUser != null) {
			return AppModel.getInstance().getRepositoryUser().delete(delUser);
		}
		
		return false;
	}

}
