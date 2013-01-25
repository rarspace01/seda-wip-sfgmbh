package de.sfgmbh.applayer.core.definitions;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for the room repository
 * @author denis
 *
 */
public interface IntfRepositoryRoom {

	/**
	 * Get rooms all rooms which match a HashMap filter
	 * @param filter
	 * @return a list of rooms
	 */
	public abstract List<IntfRoom> getByFilter(HashMap<String, String> filter);

	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public abstract List<IntfRoom> getAll();

	/**
	 * Get a unique room by its id
	 * @param roomId
	 * @return a room
	 */
	public abstract IntfRoom getRoomById(int roomId);

	/**
	 * Save a room
	 * @param room
	 */
	public abstract void save(IntfRoom room);

	/**
	 * Delte a room
	 * @param room
	 */
	public abstract void delete(IntfRoom room);

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
	 * Get a room by its number
	 * @param roomNumber
	 * @return a room object
	 */
	public abstract IntfRoom getByNumber(String roomNumber_);

}