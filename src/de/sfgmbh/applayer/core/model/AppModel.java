package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

public class AppModel implements IntfAppObservable {
	
	private static AppModel uniqueInstance_ = new AppModel(); // declare on first access through JVM (thread-safe)
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private AppException exceptionHandler_ = new AppException();
	private RepositoryChair repositoryChair_ = new RepositoryChair();
	private RepositoryUser repositoryUser_ = new RepositoryUser();
	private RepositoryCourse repositoryCourse_ = new RepositoryCourse();
	private RepositoryRoom repositoryRoom_ = new RepositoryRoom();
	private RepositoryRoomAllocation repositoryRoomAllocation_ = new RepositoryRoomAllocation();
	
	private AppModel() {} // class may only call itself via declaration
	
	/**
	 * @return the exceptionHandler
	 */
	public AppException getExceptionHandler() {
		return exceptionHandler_;
	}

	/**
	 * @return the repositoryChair
	 */
	public RepositoryChair getRepositoryChair() {
		return repositoryChair_;
	}

	/**
	 * @return the repositoryUser
	 */
	public RepositoryUser getRepositoryUser() {
		return repositoryUser_;
	}

	/**
	 * @return the repositoryCourse
	 */
	public RepositoryCourse getRepositoryCourse() {
		return repositoryCourse_;
	}

	/**
	 * @return the repositoryRoom
	 */
	public RepositoryRoom getRepositoryRoom() {
		return repositoryRoom_;
	}

	/**
	 * @return the repositoryRoomAllocation
	 */
	public RepositoryRoomAllocation getRepositoryRoomAllocation() {
		return repositoryRoomAllocation_;
	}

	/**
	 * Returns the singleton instance
	 * @return
	 */
	public static AppModel getInstance() {
		return uniqueInstance_;
	}
	
	/**
	 * 
	 */
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfAppObserver) {
				((IntfAppObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			this.exceptionHandler_.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!", "Fehler!");
		}
		
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

}
