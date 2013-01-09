package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;

public class AppModel implements IntfAppObservable {
	
	private static AppModel uniqueInstance_ = new AppModel(); // declare on first access through JVM (thread-safe)
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	public AppExceptions appExcaptions = new AppExceptions();
	public RepositoryChair repositoryChair = new RepositoryChair();
	public RepositoryUser repositoryUser = new RepositoryUser();
	public RepositoryCourse repositoryCourse = new RepositoryCourse();
	public RepositoryRoom repositoryRoom = new RepositoryRoom();
	
	private AppModel() {} // class may only call itself via declaration
	
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
			this.appExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!", "Fehler!");
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
