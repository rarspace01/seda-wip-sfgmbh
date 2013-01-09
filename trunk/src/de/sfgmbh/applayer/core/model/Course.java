package de.sfgmbh.applayer.core.model;

import de.sfgmbh.datalayer.core.definitions.IntfDataRetrievable;

public class Course implements IntfDataRetrievable{

	private int courseId_;
	private User lecturer_;
	private String courseAcronym_;
	private String courseName_;
	private Float sws_;
	private String courseKind_;
	private String courseDescription_;
	private int expectedAttendees_;
	private boolean lecturerEnabled_;
	
	public Course() {
		courseId_ = -1;
	}
	
	/**
	 * @return the courseId_
	 */
	public int getCourseId_() {
		return courseId_;
	}

	/**
	 * @param courseId_ the courseId_ to set
	 */
	public void setCourseId_(int courseId_) {
		this.courseId_ = courseId_;
	}

	/**
	 * @return the lecturer_
	 */
	public User getLecturer_() {
		return lecturer_;
	}

	/**
	 * @param lecturer_ the lecturer_ to set
	 */
	public void setLecturer_(User lecturer_) {
		this.lecturer_ = lecturer_;
	}

	/**
	 * @return the courseAcronym_
	 */
	public String getCourseAcronym_() {
		return courseAcronym_;
	}

	/**
	 * @param courseAcronym_ the courseAcronym_ to set
	 */
	public void setCourseAcronym_(String courseAcronym_) {
		this.courseAcronym_ = courseAcronym_;
	}

	/**
	 * @return the courseName_
	 */
	public String getCourseName_() {
		return courseName_;
	}

	/**
	 * @param courseName_ the courseName_ to set
	 */
	public void setCourseName_(String courseName_) {
		this.courseName_ = courseName_;
	}

	/**
	 * @return the sws_
	 */
	public Float getSws_() {
		return sws_;
	}

	/**
	 * @param sws_ the sws_ to set
	 */
	public void setSws_(Float sws_) {
		this.sws_ = sws_;
	}

	/**
	 * @return the courseKind_
	 */
	public String getCourseKind_() {
		return courseKind_;
	}

	/**
	 * @param courseKind_ the courseKind_ to set
	 */
	public void setCourseKind_(String courseKind_) {
		this.courseKind_ = courseKind_;
	}

	/**
	 * @return the courseDescription_
	 */
	public String getCourseDescription_() {
		return courseDescription_;
	}

	/**
	 * @param courseDescription_ the courseDescription_ to set
	 */
	public void setCourseDescription_(String courseDescription_) {
		this.courseDescription_ = courseDescription_;
	}

	/**
	 * @return the expectedAttendees_
	 */
	public int getExpectedAttendees_() {
		return expectedAttendees_;
	}

	/**
	 * @param expectedAttendees_ the expectedAttendees_ to set
	 */
	public void setExpectedAttendees_(int expectedAttendees_) {
		this.expectedAttendees_ = expectedAttendees_;
	}

	/**
	 * @return the lecturerEnabled_
	 */
	public boolean isLecturerEnabled_() {
		return lecturerEnabled_;
	}

	/**
	 * @param lecturerEnabled_ the lecturerEnabled_ to set
	 */
	public void setLecturerEnabled_(boolean lecturerEnabled_) {
		this.lecturerEnabled_ = lecturerEnabled_;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
