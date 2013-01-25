package de.sfgmbh.applayer.core.definitions;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for the chair repository
 * @author anna
 *
 */
public interface IntfRepositoryChair {

	/**
	 * Return all chairs
	 * @return a list of all chairs
	 */
	public abstract List<IntfChair> getAll();

	/**
	 * 
	 * @param filter
	 * @return a filtered list of chairs
	 */
	public abstract List<IntfChair> getByFilter(HashMap<String, String> filter);

	/**
	 * Get the chair based on its acronym
	 * @param acronym
	 * @return a chair if the submitted acronym can be associated with one, otherwise returns null
	 */
	public abstract IntfChair getForAcronym(String acronym);

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
	 * Get a chair by its id
	 * @param chairId
	 * @return the user for the id or null if it doesn't exist
	 */
	public abstract IntfChair get(int chairId);

	/**
	 * Delete a Chair from the model if possible
	 * @param delChair
	 * @return true on success
	 */
	public abstract boolean delete(IntfChair delChair);

	/**
	 * Save a chair in the DB
	 * @return true on success
	 */
	public abstract boolean save(IntfChair chair);

}