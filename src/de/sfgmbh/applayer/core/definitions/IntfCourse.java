package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.Course;

/**
 * Interface for the {@link Course} object
 * @author hannes
 *
 */
public interface IntfCourse {

	/**
	 * @return the courseId_
	 */
	public abstract int getCourseId_();

	/**
	 * @param courseId_ the courseId_ to set
	 */
	public abstract void setCourseId_(int courseId_);

	/**
	 * @return the lecturer_
	 */
	public abstract IntfUser getLecturer_();

	/**
	 * @param lecturer_ the lecturer_ to set
	 */
	public abstract void setLecturer_(IntfUser lecturer_);

	/**
	 * @return the courseAcronym_
	 */
	public abstract String getCourseAcronym_();

	/**
	 * @param courseAcronym_ the courseAcronym_ to set
	 */
	public abstract void setCourseAcronym_(String courseAcronym_);

	/**
	 * @return the courseName_
	 */
	public abstract String getCourseName_();

	/**
	 * @param courseName_ the courseName_ to set
	 */
	public abstract void setCourseName_(String courseName_);

	/**
	 * @return the sws_
	 */
	public abstract Float getSws_();

	/**
	 * @param sws_ the sws_ to set
	 */
	public abstract void setSws_(Float sws_);

	/**
	 * @return the courseKind_
	 */
	public abstract String getCourseKind_();

	/**
	 * Set the kind of a course.<br>
	 * Valid course kinds currently are:<br>
	 * "Vorlesung", "Übung" and "Tutorium" where "Vorlesung" is the default
	 * @param courseKind_ the courseKind_ to set
	 */
	public abstract void setCourseKind_(String courseKind_);

	/**
	 * @return the courseDescription_
	 */
	public abstract String getCourseDescription_();

	/**
	 * @param courseDescription_ the courseDescription_ to set
	 */
	public abstract void setCourseDescription_(String courseDescription_);

	/**
	 * @return the expectedAttendees_
	 */
	public abstract int getExpectedAttendees_();

	/**
	 * @param expectedAttendees_ the expectedAttendees_ to set
	 */
	public abstract void setExpectedAttendees_(int expectedAttendees_);

	/**
	 * @return the lecturerEnabled_
	 */
	public abstract boolean isLecturerEnabled_();

	/**
	 * @param lecturerEnabled_ the lecturerEnabled_ to set
	 */
	public abstract void setLecturerEnabled_(boolean lecturerEnabled_);

	/**
	 * Validates the course object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid
	 */
	public abstract boolean validate();

	/**
	 * Save this course object in the DB
	 */
	public abstract boolean save();

}