package de.sfgmbh.applayer.lecturer.definitions;

import java.util.HashMap;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;

/**
 * Interface for the start tab controller
 * 
 * @author hannes
 * @author christian
 * 
 */
public interface IntfCtrlStartTab {

	/**
	 * Create or edit a course if it is a valid course
	 * 
	 * @param course
	 * @return true if the creation was successful
	 */
	public abstract boolean saveCourse(IntfCourse course);

	/**
	 * Delete a course
	 * 
	 * @param course
	 * @return true on success
	 */
	public abstract boolean deleteCourse(IntfCourse course);

	/**
	 * Suggest a time slot for a room allocation and a set of filters (seat
	 * preferences). Currently set time slot is, expect the semester, ignored.
	 * 
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public abstract IntfRoomAllocation suggest(
			IntfRoomAllocation roomAllocation, HashMap<String, String> filter);

	/**
	 * Create a new room allocation
	 * 
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean createRoomAllocation(
			IntfRoomAllocation roomAllocation);

	/**
	 * Revoke a new room allocation
	 * 
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean revokeRoomAllocation(
			IntfRoomAllocation roomAllocation);

	/**
	 * Saves a room allocation with an indicator comment that the lecturer has
	 * seen its status
	 * 
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean hasBeenSeenAllocation(
			IntfRoomAllocation roomAllocation);

	/**
	 * Accept or deny a counter proposal where the boolean value decied which it
	 * should be
	 * 
	 * @param roomAllocation
	 * @param accept
	 * @return true on success
	 */
	public abstract boolean counterRoomAllocation(
			IntfRoomAllocation roomAllocation, boolean accept);

}