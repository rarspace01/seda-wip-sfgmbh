package de.sfgmbh.applayer.organisation.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.RoomAllocation;

public interface IntfCtrlRoomAllocation {

	/**
	 * Accept a room allocation if possible
	 * @param roomAllocation
	 * @return true on successful accept
	 */
	public abstract boolean acceptRoomAllocation(RoomAllocation ra);

	/**
	 * Get all room allocations
	 * 
	 * @return a list of all RoomAlloction objects
	 * @deprecated As no further filtering is done here the method should be used in RepositoryRoomAllocation directly instead.
	 */
	public abstract List<RoomAllocation> getAllRoomAllocations();

	/**
	 * Deny a room allocation if possible
	 * @param roomAllocation
	 */
	public abstract boolean denyRoomAllocation(RoomAllocation ra);

	/**
	 * Suggest a time slot for a room allocation. Currently set time slot is, expect the semester, ignored. Expected attendees of the course are taken into account.
	 * @param roomAllocation
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public abstract RoomAllocation suggest(RoomAllocation roomAllocation);

	/**
	 * Create a counter proposal room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean createCounterProposal(RoomAllocation roomAllocation);

	/**
	 * Delete all denied allocations
	 * @return true on success
	 */
	public abstract boolean cleanRoomAllocations();

}