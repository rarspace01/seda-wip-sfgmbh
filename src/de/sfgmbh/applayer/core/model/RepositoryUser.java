package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
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
		DataModel.getInstance().dataHandlerUser.register(this);
	}
	
	/**
	 * Return all users
	 * @return a list of all users
	 */
	public List<User> getAll() {
		return DataModel.getInstance().dataHandlerUser.getAll();
	}
	
	/**
	 * Return all users of the class lecturer
	 * @return a list of all users of the class lecturer
	 */
	public List<User> getAllLecturer() {
		return DataModel.getInstance().dataHandlerUser.getAllLecturer();
	}

	@Override
	public void change() {
		this.update();
	}

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
			AppModel.getInstance().appExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: RepositoryUser-01", "Fehler!");
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
