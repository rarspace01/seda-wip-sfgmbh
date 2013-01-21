package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;

/**
 * Controller for chair management
 * 
 * @author anna
 *
 */
public class CtrlChair {

	/**
	 * Create or edit a chair if it is a valid chair
	 * @param chair
	 * @return true on success
	 */
	public boolean saveChair(Chair chair) {
		
		if (chair.validate()) {
			if (chair.save()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Delete a chair if possible
	 * @param chair
	 * @return true on success
	 */
	public boolean delete(Chair chair) {
		// Check if the users exists in the data base
		Chair delChair = AppModel.getInstance().getRepositoryChair().get(chair.getChairId_());
		
		if (delChair != null) {
			return AppModel.getInstance().getRepositoryChair().delete(delChair);
		}
		
		return false;
	}
}
