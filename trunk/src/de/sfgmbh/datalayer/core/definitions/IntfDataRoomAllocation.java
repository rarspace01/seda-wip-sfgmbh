package de.sfgmbh.datalayer.core.definitions;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoomAllocation;
/**
 * This is the interface for the {@link DataHandlerRoomAllocation}
 * 
 * @author denis
 *
 */
public interface IntfDataRoomAllocation {

	/**
	 * Get all room allocations
	 * @return a list with all room allocations
	 */
	public abstract List<IntfRoomAllocation> getAll();

	/**
	 * Get all room allocations which are not already denied 
	 * @return a list with all open room allocations
	 */
	public abstract List<IntfRoomAllocation> getAllOpen();

	public abstract List<IntfRoomAllocation> getByFilter(
			HashMap<String, String> filter);

	/**
	 * Checks for conflicting room allocations for one given room allocation
	 * @param roomAllocation
	 * @return a list of all conflicting room allocations
	 */
	public abstract List<IntfRoomAllocation> getConflictingAllocation(
			IntfRoomAllocation roomAllocation);

	/**
	 * Get a list of all conflicting dates. <br>
	 * That means courses in the same room, at the same time in the same semester <br>
	 * @return an ArrayList of HashMaps for each conflicting date. <br>
	 * 		The HashMap has the following entries: <br>
	 * 		"roomid" - the affected ID of the room <br>
	 * 		"time" - the Integer of the affected time <br>
	 * 		"day" - the Integer of the affected day <br>
	 * 		"semester" - the String of the affected Semester
	 */
	public abstract List<HashMap<String, Object>> getConflictingDates();

	/**
	 * For a given list of room allocations set their conflicting state appropriate
	 * @param roomAllocations
	 * @return a list of room allocations with the correct conflicting state
	 */
	public abstract List<IntfRoomAllocation> setConflicts(
			List<IntfRoomAllocation> roomAllocations);

	/**
	 * Get a room allocation by its id
	 * @param roomAllocationId
	 * @return a room allocation by its id
	 */
	public abstract IntfRoomAllocation get(int roomAllocationId);

	/**
	 * Save a room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public abstract boolean save(IntfRoomAllocation roomAllocation);

	/**
	 * Delete all denied allocations
	 * @return true on success
	 */
	public abstract boolean clean();


}