package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.User;

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

}
