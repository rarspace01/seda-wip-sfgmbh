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
	public int getLectureId_() {
		return lectureId_;
	}
	public void setLectureId_(int lectureId_) {
		this.lectureId_ = lectureId_;
	}
	public User getLecturer_() {
		return lecturer_;
	}
	public void setLecturer_(User lecturer_) {
		this.lecturer_ = lecturer_;
	}
	public String getLectureDescr_() {
		return lectureDescr_;
	}
	public void setLectureDescr_(String lectureDescr_) {
		this.lectureDescr_ = lectureDescr_;
	}
	public String getLectureAcronym_() {
		return lectureAcronym_;
	}
	public void setLectureAcronym_(String lectureAcronym_) {
		this.lectureAcronym_ = lectureAcronym_;
	}
	public String getSemester_() {
		return semester_;
	}
	public void setSemester_(String semester_) {
		this.semester_ = semester_;
	}
	public int getSws_() {
		return sws_;
	}
	public void setSws_(int sws_) {
		this.sws_ = sws_;
	}
	public boolean isVisible_() {
		return isVisible_;
	}
	public void setVisible_(boolean isVisible_) {
		this.isVisible_ = isVisible_;
	}
	public int getPredictedStudents_() {
		return predictedStudents_;
	}
	public void setPredictedStudents_(int predictedStudents_) {
		this.predictedStudents_ = predictedStudents_;
	}
	
	
	
}
