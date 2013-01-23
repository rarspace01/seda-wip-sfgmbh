package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Repository for the {@link Course} objects in the model
 * 
 * @author hannes
 *
 */
public class RepositoryCourse implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this course repository as observer in the data model
	 */
	public RepositoryCourse() {
		DataModel.getInstance().getDataHandlerCourse().register(this);
	}
	
	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public List<Course> getAll() {
		return DataModel.getInstance().getDataHandlerCourse().getAll();
	}
	
	/**
	 * Get course objects based on a filter
	 * @param filter
	 * @return course objects based on a filter
	 */
	public List<Course> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerCourse().getByFilter(filter);
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
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryChair-01", "Fehler!");
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
	 * Save a course in the DB
	 * @param course
	 * @return true on success
	 */
	public boolean save(IntfCourse course) {
		return DataModel.getInstance().getDataHandlerCourse().save(course);
	}

	/**
	 * Delete a course from the DB
	 * @param course
	 * @return true on success
	 */
	public boolean delete(IntfCourse course) {
		return DataModel.getInstance().getDataHandlerCourse().delete(course);
	}

}
