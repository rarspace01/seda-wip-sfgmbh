package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.RepositoryChair;
import de.sfgmbh.applayer.core.model.RepositoryCourse;
import de.sfgmbh.applayer.core.model.RepositoryRoom;
import de.sfgmbh.applayer.core.model.RepositoryRoomAllocation;
import de.sfgmbh.applayer.core.model.RepositoryUser;

/**
 * Interfaces for the application model
 * 
 * @author hannes
 *
 */
public interface IntfAppModel {

	/**
	 * Get a class which handles how errors, special events or special information should be handled and is capable to store user-friendly messages 
	 * @return the exceptionHandler
	 */
	public abstract AppException getExceptionHandler();

	/**
	 * Get the chair repository out of the main model
	 * @return the repositoryChair
	 */
	public abstract RepositoryChair getRepositoryChair();

	/**
	 * Get the user repository out of the main model
	 * @return the repositoryUser
	 */
	public abstract RepositoryUser getRepositoryUser();

	/**
	 * Get the course repository out of the main model
	 * @return the repositoryCourse
	 */
	public abstract RepositoryCourse getRepositoryCourse();

	/**
	 * Get the room repository out of the main model
	 * @return the repositoryRoom
	 */
	public abstract RepositoryRoom getRepositoryRoom();

	/**
	 * Get the room allocation repository out of the main model
	 * @return the repositoryRoomAllocation
	 */
	public abstract RepositoryRoomAllocation getRepositoryRoomAllocation();

}