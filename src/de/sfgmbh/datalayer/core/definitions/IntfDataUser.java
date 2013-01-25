package de.sfgmbh.datalayer.core.definitions;

import java.util.List;

import javax.security.auth.login.LoginContext;

import de.sfgmbh.applayer.core.definitions.IntfUser;
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
	public IntfUser get(int userId);
	
	/**
	 * get {@link User} by Loin
	 * @param login
	 * @return user - {@link User}
	 */
	public IntfUser getByLogin(String login);

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
	public boolean delete(IntfUser toBeDeletedUser);

	/**
	 * Saves a new user in the DB if the user doesn't exist already and updates an existing user in the DB otherwise
	 * @param user
	 * @return ture on success
	 */
	public boolean save(IntfUser toBeSavedUser);
	
}
