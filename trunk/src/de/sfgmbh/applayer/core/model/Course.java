package de.sfgmbh.applayer.core.model;


public class Course {

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
	 * Set the kind of a course.<br>
	 * Valid course kinds currently are:<br>
	 * "Vorlesung", "Übung" and "Tutorium" where "Vorlesung" is the default
	 * @param courseKind_ the courseKind_ to set
	 */
	public void setCourseKind_(String courseKind_) {
		if (courseKind_.equals("Vorlesung") || courseKind_.equals("Übung") || courseKind_.equals("Tutorium")) {
			this.courseKind_ = courseKind_;
		} else {
			this.courseKind_ = "Vorlesung";
		}
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

	/**
	 * Validates the course object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid
	 */
	public boolean validate() {
		boolean check = true;
		String message = "";
		
		if (this.courseId_ < -1 ) {
			message = message + "Die Veranstaltung hat keine gültige ID!<br/>";
			check = false;
		}
		if (this.courseAcronym_.length() > 16 || this.courseAcronym_.length() < 2) {
			message = message + "Die Kennung einer Veranstaltung muss zwischen 2 und 16 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.courseDescription_.length() > 256 ) {
			message = message + "Die Beschreibung einer Veranstaltung darf 256 Zeichen nicht überschreiten!<br />";
			check = false;
		}
		if (this.courseName_.length() > 64 || this.courseName_.length() < 1) {
			message = message + "Der ausführliche Name einer Veranstaltung muss zwischen 1 und 64 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.expectedAttendees_ < 0 ) {
			message = message + "Die erwartete Teilnehmerzahl darf nicht negativ sein!<br />";
			check = false;
		}
		if (this.sws_ < 0 || this.sws_ > 100) {
			message = message + "Die SWS müssen ziwsch 0 und 100 liegen!<br />";
			check = false;
		}
		
		if (check) {
			return true;
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException(message, "Fehler!");
			return false;
		}
	}
	
	/**
	 * Save this course object in the DB
	 */
	public boolean save() {
		if (AppModel.getInstance().getRepositoryCourse().save(this)) {
			return true;
		}
		return false;
	}
	
}
