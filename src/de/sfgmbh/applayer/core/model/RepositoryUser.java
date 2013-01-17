package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

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
	public boolean save(User user) {
		if (DataModel.getInstance().getDataHandlerUser().save(user)) {
			return true;
		}
		return false;
	}

	@Override
	public void change() {
		this.update();
	}

	@Override
	public void update() {
		// Wait a little bit to avoid ConcurrentModificationException
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			for (Object o : this.observer_) {
				if (o instanceof IntfAppObserver) {
					((IntfAppObserver) o).change();
				}
			}
		} catch (Exception e) {
			// Be quiet and go on if there is still ConcurrentModificationException, it's not such a big deal if one update of an observer is missed
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
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: RepositoryUser-01", "Fehler!");
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
	
	/**
	 * Get a user by its id
	 * @param userId
	 * @return the user for the id or null if it doesn't exist
	 */
	public User get(int userId_) {
		return DataModel.getInstance().getDataHandlerUser().get(userId_);
	}

	/**
	 * Delete a user from the model if possible
	 * @param userToDelete
	 * @return true on success
	 */
	public boolean delete(User delUser) {
		return DataModel.getInstance().getDataHandlerUser().delete(delUser);
	}

}
