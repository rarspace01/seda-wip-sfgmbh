package de.sfgmbh.datalayer.core.definitions;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.model.Course;
/**
 * This is the interface for the {@link DataCourse}
 * 
 * @author denis
 * @author hannes
 *
 */
public interface IntfDataCourse {

	/**
	 * Get all courses
	 * @return a list of all courses
	 */
	public abstract List<Course> getAll();

	public abstract List<Course> getByFilter(HashMap<String, String> filter);

	/**
	 * Get a course by its ID
	 * @param id
	 * @return a course
	 */
	public abstract IntfCourse get(int id);

	/**
	 * Forms a course object out of a given result set
	 * @param resultSet - {@link ResultSet}
	 * @return a course object
	 */
	public abstract IntfCourse makeCourse(ResultSet resultSet);

	/**
	 * 
	 */
	public abstract void update();

	/**
	 * 
	 * @param observer
	 */
	public abstract void register(IntfDataObserver observer);

	/**
	 * 
	 * @param observer
	 */
	public abstract void unregister(IntfDataObserver observer);

	/**
	 * Save a course in the DB (this creates a course if its ID is -1 and otherwise updates an existing one)
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