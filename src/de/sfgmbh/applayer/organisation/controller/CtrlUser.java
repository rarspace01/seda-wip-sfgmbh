package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;

/**
 * Controller for user management
 * 
 * @author hannes
 *
 */
public class CtrlUser {
	
	/**
	 * Create a user if it is a valid user
	 * @param user
	 * @return true if the creation was successful
	 */
	public boolean createUser(User user) {
		
		if (user.validate()) {
			if (user.save()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Delete a user if possible
	 * @param user
	 * @return true on success
	 */
	public boolean delete(User user) {
		// Check if the users exists in the data base
		User delUser = AppModel.getInstance().getRepositoryUser().get(user.getUserId_());
		
		if (delUser != null) {
			return AppModel.getInstance().getRepositoryUser().delete(delUser);
		}
		
		return false;
	}

}
