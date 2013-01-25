package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.AppException;

/**
 * Interfaces for the application model
 * 
 * @author hannes
 * @author denis
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
	public abstract IntfRepositoryChair getRepositoryChair();

	/**
	 * Get the user repository out of the main model
	 * @return the repositoryUser
	 */
	public abstract IntfRepositoryUser getRepositoryUser();

	/**
	 * Get the course repository out of the main model
	 * @return the repositoryCourse
	 */
	public abstract IntfRepositoryCourse getRepositoryCourse();

	/**
	 * Get the room repository out of the main model
	 * @return the repositoryRoom
	 */
	public abstract IntfRepositoryRoom getRepositoryRoom();

	/**
	 * Get the room allocation repository out of the main model
	 * @return the repositoryRoomAllocation
	 */
	public abstract IntfRepositoryRoomAllocation getRepositoryRoomAllocation();

}