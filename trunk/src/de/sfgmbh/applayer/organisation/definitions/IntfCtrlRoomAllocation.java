package de.sfgmbh.applayer.organisation.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * Room allocation Interface
 * @author hannes
 *
 */
public interface IntfCtrlRoomAllocation {

	/**
	 * Accept a room allocation if possible
	 * @param roomAllocation
	 * @return true on successful accept
	 */
	public abstract boolean acceptRoomAllocation(IntfRoomAllocation ra);

	/**
	 * Get all room allocations
	 * 
	 * @return a list of all RoomAlloction objects
	 * @deprecated As no further filtering is done here the method should be used in RepositoryRoomAllocation directly instead.
	 */
	public abstract List<IntfRoomAllocation> getAllRoomAllocations();

	/**
	 * Deny a room allocation if possible
	 * @param roomAllocation
	 */
	public abstract boolean denyRoomAllocation(IntfRoomAllocation roomAllocation);

	/**
	 * Suggest a time slot for a room allocation. Currently set time slot is, expect the semester, ignored. Expected attendees of the course are taken into account.
	 * @param roomAllocation
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public abstract IntfRoomAllocation suggest(IntfRoomAllocation roomAllocation);

	/**
	 * Create a counter proposal room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean createCounterProposal(IntfRoomAllocation roomAllocation);

	/**
	 * Delete all denied allocations
	 * @return true on success
	 */
	public abstract boolean cleanRoomAllocations();
	
	/**
	 * retrieves the room allocations from a given List and day/time
	 * @param roomAllocations
	 * @param day - @see {@link ViewHelper}
	 * @param time - @see {@link ViewHelper}
	 * @return textualRepresentation - Textual HTML Representation as a {@link String} 
	 * @author denis
	 * @param showRoomName  - shows the roomname on output
	 */
	public String getLectureOnTime(List<IntfRoomAllocation> roomAllocations,int day, int time, boolean showRoomName, boolean markDuplicates);

}