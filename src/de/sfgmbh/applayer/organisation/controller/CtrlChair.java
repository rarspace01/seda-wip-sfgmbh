package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlChair;

/**
 * Controller for chair management
 * 
 * @author anna
 * 
 */
public class CtrlChair implements IntfCtrlChair {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlChair#saveChair(de
	 * .sfgmbh.applayer.core.model.Chair)
	 */
	@Override
	public boolean saveChair(IntfChair chair) {

		if (chair.validate()) {
			if (chair.save()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlChair#delete(de.sfgmbh
	 * .applayer.core.model.Chair)
	 */
	@Override
	public boolean delete(IntfChair chair) {
		// Check if the users exists in the data base
		IntfChair delChair = AppModel.getInstance().getRepositoryChair()
				.get(chair.getChairId_());

		if (delChair != null) {
			return AppModel.getInstance().getRepositoryChair().delete(delChair);
		}

		return false;
	}
}
