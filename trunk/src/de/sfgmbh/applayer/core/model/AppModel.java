package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppModel;
import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

public class AppModel implements IntfAppObservable, IntfAppModel {
	
	private static IntfAppModel uniqueInstance_ = new AppModel(); // declare on first access through JVM (thread-safe)
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private AppException exceptionHandler_ = new AppException();
	private RepositoryChair repositoryChair_ = new RepositoryChair();
	private RepositoryUser repositoryUser_ = new RepositoryUser();
	private RepositoryCourse repositoryCourse_ = new RepositoryCourse();
	private RepositoryRoom repositoryRoom_ = new RepositoryRoom();
	private RepositoryRoomAllocation repositoryRoomAllocation_ = new RepositoryRoomAllocation();
	
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
	public RepositoryChair getRepositoryChair() {
		return repositoryChair_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryUser()
	 */
	@Override
	public RepositoryUser getRepositoryUser() {
		return repositoryUser_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryCourse()
	 */
	@Override
	public RepositoryCourse getRepositoryCourse() {
		return repositoryCourse_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryRoom()
	 */
	@Override
	public RepositoryRoom getRepositoryRoom() {
		return repositoryRoom_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfAppModel#getRepositoryRoomAllocation()
	 */
	@Override
	public RepositoryRoomAllocation getRepositoryRoomAllocation() {
		return repositoryRoomAllocation_;
	}

	/**
	 * Returns the singleton instance
	 * @return
	 */
	public static IntfAppModel getInstance() {
		return uniqueInstance_;
	}
	
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfAppObserver) {
				((IntfAppObserver) o).change();
			}
		}
	}
	

	@Override
	public void register(Object observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			this.exceptionHandler_.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!", "Fehler!");
		}
		
	}
	
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

}
