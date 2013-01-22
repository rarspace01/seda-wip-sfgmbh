package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.User;

/**
 * Interface for handling the user table
 * @author denis
 *
 */
public interface IntfDataUser {

	/**
	 * 
	 * @return List of User objects which are present in the database
	 */
	public List<User> getAll();

	/**
	 * 
	 * @param userId
	 *            - User id in the database
	 * @return User object
	 */
	public User get(int userId);

	/**
	 * 
	 * @param searchQry - Search pattern
	 * @return User objects
	 */
	public List<User> search(String searchQry);
	
	/**
	 * 
	 * @param toBeDeletedUser
	 *            - the object which should be remove from the database
	 * @return true on success
	 */
	public boolean delete(User toBeDeletedUser);

	/**
	 * 
	 * @param toBeSavedUser
	 *            - object of type User to be saved in DB
	 * @return true on success
	 */
	public boolean save(User toBeSavedUser);
	
}
