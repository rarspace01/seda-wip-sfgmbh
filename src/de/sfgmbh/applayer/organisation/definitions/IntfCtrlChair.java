package de.sfgmbh.applayer.organisation.definitions;

import de.sfgmbh.applayer.core.model.IntfChair;

public interface IntfCtrlChair {

	/**
	 * Create or edit a chair if it is a valid chair
	 * @param chair
	 * @return true on success
	 */
	public abstract boolean saveChair(IntfChair chair);

	/**
	 * Delete a chair if possible
	 * @param chair
	 * @return true on success
	 */
	public abstract boolean delete(IntfChair chair);

}