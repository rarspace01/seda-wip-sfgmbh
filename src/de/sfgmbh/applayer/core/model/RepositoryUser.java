package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Repository for {@link User} objects in the application model
 * 
 * @author hannes
 * @author denis
 *
 */
public class RepositoryUser implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this user repository as observer in the data model
	 */
	public RepositoryUser() {
		DataModel.getInstance().getDataHandlerUser().register(this);
	}
	
	/**
	 * Return all users
	 * @return a list of all users
	 */
	public List<User> getAll() {
		return DataModel.getInstance().getDataHandlerUser().getAll();
	}
	
	/**
	 * Return all users of the class lecturer
	 * @return a list of all users of the class lecturer
	 */
	public List<User> getAllLecturer() {
		return DataModel.getInstance().getDataHandlerUser().getAllLecturer();
	}
	
	/**
	 * Return filtered users
	 * @return a list of filtered users
	 */
	public List<User> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerUser().getByFilter(filter);
	}
	
	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 * @return true on success
	 */
	public boolean save(IntfUser user) {
		if (DataModel.getInstance().getDataHandlerUser().save(user)) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	@Override
	public void change() {
		this.update();
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
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: RepositoryUser-01", "Fehler!");
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
	
	/**
	 * Get a user by its id
	 * @param userId
	 * @return the user for the id or null if it doesn't exist
	 */
	public IntfUser get(int userId_) {
		return DataModel.getInstance().getDataHandlerUser().get(userId_);
	}

	/**
	 * Delete a user from the model if possible
	 * @param userToDelete
	 * @return true on success
	 */
	public boolean delete(IntfUser delUser) {
		return DataModel.getInstance().getDataHandlerUser().delete(delUser);
	}

}
