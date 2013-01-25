package de.sfgmbh.applayer.core.model;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfUser;

/**
 * Class for courses
 * 
 * @author christian
 * @author hannes
 * 
 */
public class Course implements IntfCourse {

	private int courseId_;
	private IntfUser lecturer_;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getCourseId_()
	 */
	@Override
	public int getCourseId_() {
		return courseId_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#setCourseId_(int)
	 */
	@Override
	public void setCourseId_(int courseId_) {
		this.courseId_ = courseId_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getLecturer_()
	 */
	@Override
	public IntfUser getLecturer_() {
		return lecturer_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setLecturer_(de.sfgmbh.applayer
	 * .core.model.User)
	 */
	@Override
	public void setLecturer_(IntfUser lecturer_) {
		this.lecturer_ = lecturer_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getCourseAcronym_()
	 */
	@Override
	public String getCourseAcronym_() {
		return courseAcronym_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setCourseAcronym_(java.lang.
	 * String)
	 */
	@Override
	public void setCourseAcronym_(String courseAcronym_) {
		this.courseAcronym_ = courseAcronym_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getCourseName_()
	 */
	@Override
	public String getCourseName_() {
		return courseName_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setCourseName_(java.lang.String)
	 */
	@Override
	public void setCourseName_(String courseName_) {
		this.courseName_ = courseName_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getSws_()
	 */
	@Override
	public Float getSws_() {
		return sws_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#setSws_(java.lang.Float)
	 */
	@Override
	public void setSws_(Float sws_) {
		this.sws_ = sws_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getCourseKind_()
	 */
	@Override
	public String getCourseKind_() {
		return courseKind_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setCourseKind_(java.lang.String)
	 */
	@Override
	public void setCourseKind_(String courseKind_) {
		if (courseKind_.equals("Vorlesung") || courseKind_.equals("Übung")
				|| courseKind_.equals("Tutorium")) {
			this.courseKind_ = courseKind_;
		} else {
			this.courseKind_ = "Vorlesung";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getCourseDescription_()
	 */
	@Override
	public String getCourseDescription_() {
		return courseDescription_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setCourseDescription_(java.lang
	 * .String)
	 */
	@Override
	public void setCourseDescription_(String courseDescription_) {
		this.courseDescription_ = courseDescription_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#getExpectedAttendees_()
	 */
	@Override
	public int getExpectedAttendees_() {
		return expectedAttendees_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#setExpectedAttendees_(int)
	 */
	@Override
	public void setExpectedAttendees_(int expectedAttendees_) {
		this.expectedAttendees_ = expectedAttendees_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#isLecturerEnabled_()
	 */
	@Override
	public boolean isLecturerEnabled_() {
		return lecturerEnabled_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfCourse#setLecturerEnabled_(boolean)
	 */
	@Override
	public void setLecturerEnabled_(boolean lecturerEnabled_) {
		this.lecturerEnabled_ = lecturerEnabled_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#validate()
	 */
	@Override
	public boolean validate() {
		boolean check = true;
		String message = "";

		if (this.courseId_ < -1) {
			message = message + "Die Veranstaltung hat keine gültige ID!<br/>";
			check = false;
		}
		if (this.courseAcronym_.length() > 16
				|| this.courseAcronym_.length() < 2) {
			message = message
					+ "Die Kennung einer Veranstaltung muss zwischen 2 und 16 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.courseDescription_.length() > 256) {
			message = message
					+ "Die Beschreibung einer Veranstaltung darf 256 Zeichen nicht überschreiten!<br />";
			check = false;
		}
		if (this.courseName_.length() > 256 || this.courseName_.length() < 1) {
			message = message
					+ "Der ausführliche Name einer Veranstaltung muss zwischen 1 und 256 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.expectedAttendees_ < 0) {
			message = message
					+ "Die erwartete Teilnehmerzahl darf nicht negativ sein!<br />";
			check = false;
		}
		if (this.sws_ < 0 || this.sws_ > 100) {
			message = message + "Die SWS müssen ziwsch 0 und 100 liegen!<br />";
			check = false;
		}

		if (check) {
			return true;
		} else {
			AppModel.getInstance().getExceptionHandler()
					.setNewException(message, "Fehler!");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfCourse#save()
	 */
	@Override
	public boolean save() {
		if (AppModel.getInstance().getRepositoryCourse().save(this)) {
			return true;
		}
		return false;
	}

}
