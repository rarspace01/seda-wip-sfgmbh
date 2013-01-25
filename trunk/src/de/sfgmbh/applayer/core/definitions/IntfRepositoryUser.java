package de.sfgmbh.applayer.core.definitions;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.User;
/**
 * Interface for the user repository
 * @author denis
 *
 */
public interface IntfRepositoryUser {

	/**
	 * Return all users
	 * @return a list of all users
	 */
	public abstract List<User> getAll();

	/**
	 * Return all users of the class lecturer
	 * @return a list of all users of the class lecturer
	 */
	public abstract List<User> getAllLecturer();

	/**
	 * Return filtered users
	 * @return a list of filtered users
	 */
	public abstract List<User> getByFilter(HashMap<String, String> filter);

	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 * @return true on success
	 */
	public abstract boolean save(IntfUser user);

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
	 * Get a user by its id
	 * @param userId
	 * @return the user for the id or null if it doesn't exist
	 */
	public abstract IntfUser get(int userId_);

	/**
	 * Delete a user from the model if possible
	 * @param userToDelete
	 * @return true on success
	 */
	public abstract boolean delete(IntfUser delUser);

}