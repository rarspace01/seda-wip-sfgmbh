package de.sfgmbh.applayer.lecturer.definitions;

import java.util.HashMap;

import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.RoomAllocation;

public interface IntfCtrlStartTab {

	/**
	 * Create or edit a course if it is a valid course
	 * @param course
	 * @return true if the creation was successful
	 */
	public abstract boolean saveCourse(Course course);

	/**
	 * Delete a course
	 * @param course
	 * @return true on success
	 */
	public abstract boolean deleteCourse(Course course);

	/**
	 * Suggest a time slot for a room allocation and a set of filters (seat preferences). Currently set time slot is, expect the semester, ignored.
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public abstract RoomAllocation suggest(RoomAllocation roomAllocation,
			HashMap<String, String> filter);

	/**
	 * Create a new room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean createRoomAllocation(RoomAllocation roomAllocation);

	/**
	 * Revoke a new room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean revokeRoomAllocation(RoomAllocation roomAllocation);

	/**
	 * Accept or deny a counter proposal where the boolean value decied which it should be
	 * @param roomAllocation
	 * @param accapt
	 * @return true on success
	 */
	public abstract boolean counterRoomAllocation(
			RoomAllocation roomAllocation, boolean accept);

}