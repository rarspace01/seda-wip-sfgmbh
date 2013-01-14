package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class RepositoryCourse implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this course repository as observer in the data model
	 */
	public RepositoryCourse() {
		DataModel.getInstance().dataHandlerCourse.register(this);
	}
	
	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public List<Course> getAll() {
		return DataModel.getInstance().dataHandlerCourse.getAll();
	}
	
	/**
	 * Get course objects based on a filter
	 * @param filter
	 * @return course objects based on a filter
	 */
	public List<Course> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().dataHandlerCourse.getByFilter(filter);
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
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryChair-01", "Fehler!");
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
