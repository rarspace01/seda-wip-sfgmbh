package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppModel;
import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryChair;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryCourse;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryRoom;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryRoomAllocation;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryUser;

/**
 * This is the main application layer model class
 * 
 * @author hannes
 * @author denis
 *
 */
public class AppModel implements IntfAppObservable, IntfAppModel {
	
	private static IntfAppModel uniqueInstance_ = new AppModel(); // declare on first access through JVM (thread-safe)
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private AppException exceptionHandler_ = new AppException();
	private IntfRepositoryChair repositoryChair_ = new RepositoryChair();
	private IntfRepositoryUser repositoryUser_ = new RepositoryUser();
	private IntfRepositoryCourse repositoryCourse_ = new RepositoryCourse();
	private IntfRepositoryRoom repositoryRoom_ = new RepositoryRoom();
	private IntfRepositoryRoomAllocation repositoryRoomAllocation_ = new RepositoryRoomAllocation();
	
	private AppModel() {} // class may only call itself via declaration
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getExceptionHandler()
	 */
	@Override
	public AppException getExceptionHandler() {
		return exceptionHandler_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryChair()
	 */
	@Override
	public IntfRepositoryChair getRepositoryChair() {
		return repositoryChair_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryUser()
	 */
	@Override
	public IntfRepositoryUser getRepositoryUser() {
		return repositoryUser_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryCourse()
	 */
	@Override
	public IntfRepositoryCourse getRepositoryCourse() {
		return repositoryCourse_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryRoom()
	 */
	@Override
	public IntfRepositoryRoom getRepositoryRoom() {
		return repositoryRoom_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryRoomAllocation()
	 */
	@Override
	public IntfRepositoryRoomAllocation getRepositoryRoomAllocation() {
		return repositoryRoomAllocation_;
	}

	/**
	 * Returns the singleton instance
	 * @return uniqueInstance_ - Instance of the singelton
	 */
	public static IntfAppModel getInstance() {
		return uniqueInstance_;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	@Override
	public void update() {
		// Create a private observer list to avoid ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfAppObserver> currentObservers = (ArrayList<IntfAppObserver>) observer_.clone();
				
		for (IntfAppObserver observer : currentObservers) {
			if (observer instanceof IntfAppObserver) {
				observer.change();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void register(IntfAppObserver observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			this.exceptionHandler_.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!", "Fehler!");
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

}
