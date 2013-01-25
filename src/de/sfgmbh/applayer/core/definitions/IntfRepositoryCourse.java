package de.sfgmbh.applayer.core.definitions;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.Course;

/**
 * Interface for the course repository
 * @author denis
 *
 */
public interface IntfRepositoryCourse {

	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public abstract List<Course> getAll();

	/**
	 * Get course objects based on a filter
	 * @param filter
	 * @return course objects based on a filter
	 */
	public abstract List<Course> getByFilter(HashMap<String, String> filter);

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	public abstract void change();

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	public abstract void update();

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	public abstract void register(IntfAppObserver observer);

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	public abstract void unregister(IntfAppObserver observer);

	/**
	 * Save a course in the DB
	 * @param course
	 * @return true on success
	 */
	public abstract boolean save(IntfCourse course);

	/**
	 * Delete a course from the DB
	 * @param course
	 * @return true on success
	 */
	public abstract boolean delete(IntfCourse course);

}