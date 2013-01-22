package de.sfgmbh.applayer.organisation.definitions;

import de.sfgmbh.applayer.core.model.Chair;

public interface IntfCtrlChair {

	/**
	 * Create or edit a chair if it is a valid chair
	 * @param chair
	 * @return true on success
	 */
	public abstract boolean saveChair(Chair chair);

	/**
	 * Delete a chair if possible
	 * @param chair
	 * @return true on success
	 */
	public abstract boolean delete(Chair chair);

}