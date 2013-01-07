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
	 * @param iUserId
	 *            - User id in the database
	 * @return User object
	 */
	public User get(int UserId);

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
	 * @return
	 */
	public void delete(User toBeDeletedUser);

	/**
	 * 
	 * @param toBeSavedUser
	 *            - object of type User to be saved in DB
	 * @return - int for error handling
	 */
	public void save(User toBeSavedUser);
	
}
