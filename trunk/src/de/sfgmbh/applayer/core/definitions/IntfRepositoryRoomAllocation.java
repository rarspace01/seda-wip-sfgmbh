package de.sfgmbh.applayer.core.definitions;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for the room alloaction repository
 * @author hannes
 *
 */
public interface IntfRepositoryRoomAllocation {

	/**
	 * Return all room allocations
	 * @return a list of all room allocations
	 */
	public abstract List<IntfRoomAllocation> getAll();

	/**
	 * Get all room allocations which are not already denied 
	 * @return a list with all open room allocations
	 */
	public abstract List<IntfRoomAllocation> getAllOpen();

	/**
	 * Return filtered courses
	 * @return a list of filtered courses
	 */
	public abstract List<IntfRoomAllocation> getByFilter(
			HashMap<String, String> filter);

	/**
	 * Checks for conflicting room allocations
	 * @param roomAllocation
	 * @return a list of all conflicting room allocations
	 */
	public abstract List<IntfRoomAllocation> getConflictingAllocation(
			IntfRoomAllocation roomAllocation);

	/**
	 * Get a room allocation by its id
	 * @param id
	 * @return a room allocation by its id
	 */
	public abstract IntfRoomAllocation get(int id);

	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 * @return true on success
	 */
	public abstract boolean save(IntfRoomAllocation roomAllocation);

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	public abstract void change();

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	public abstract void update();

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	public abstract void register(IntfAppObserver observer);

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	public abstract void unregister(IntfAppObserver observer);

	/**
	 * Delete all denied allocations
	 * @return true on success
	 */
	public abstract boolean clean();

}