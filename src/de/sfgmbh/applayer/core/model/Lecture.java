package de.sfgmbh.applayer.core.model;

public class Lecture {

	private int lectureId_;
	private User lecturer_;
	private String lectureDescr_;
	private String lectureAcronym_;
	private String semester_;
	private int sws_;
	private boolean isVisible_;
	private int predictedStudents_;
	
	/**
	 * 
	 * @return the lecturer ID
	 */
	public int getLectureId_() {
		return lectureId_;
	}
	
	/**
	 * 
	 * @param the lectureId_ to set
	 */
	public void setLectureId_(int lectureId_) {
		this.lectureId_ = lectureId_;
	}
	
	/**
	 * 
	 * @return the lecturer
	 */
	public User getLecturer_() {
		return lecturer_;
	}
	
	/**
	 * 
	 * @param the lecturer_ to set
	 */
	public void setLecturer_(User lecturer_) {
		this.lecturer_ = lecturer_;
	}
	
	/**
	 * 
	 * @return the lecture Description
	 */
	public String getLectureDescr_() {
		return lectureDescr_;
	}
	
	/**
	 * 
	 * @param the lectureDescr_ to set
	 */
	public void setLectureDescr_(String lectureDescr_) {
		this.lectureDescr_ = lectureDescr_;
	}
	
	/**
	 * 
	 * @return the lecturer Acronym
	 */
	public String getLectureAcronym_() {
		return lectureAcronym_;
	}
	
	/**
	 * 
	 * @param the lectureAcronym_ to set
	 */
	public void setLectureAcronym_(String lectureAcronym_) {
		this.lectureAcronym_ = lectureAcronym_;
	}
	
	/**
	 * 
	 * @return the semester
	 */
	public String getSemester_() {
		return semester_;
	}
	
	/**
	 * 
	 * @param the semester_ to set
	 */
	public void setSemester_(String semester_) {
		this.semester_ = semester_;
	}
	
	/**
	 * 
	 * @return the SWS (Semesterwochenstunden=hours per week per semester)
	 */
	public int getSws_() {
		return sws_;
	}
	
	/**
	 * 
	 * @param the sws_ to set
	 */
	public void setSws_(int sws_) {
		this.sws_ = sws_;
	}
	
	/**
	 * 
	 * @return the visibility
	 */
	public boolean isVisible_() {
		return isVisible_;
	}
	
	/**
	 * 
	 * @param the isVisible_ to set
	 */
	public void setVisible_(boolean isVisible_) {
		this.isVisible_ = isVisible_;
	}
	
	/**
	 * 
	 * @return the predicted students
	 */
	public int getPredictedStudents_() {
		return predictedStudents_;
	}
	
	/**
	 * 
	 * @param the predictedStudents_ to set
	 */
	public void setPredictedStudents_(int predictedStudents_) {
		this.predictedStudents_ = predictedStudents_;
	}
	
	
	
}
