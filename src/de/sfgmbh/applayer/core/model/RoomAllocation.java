package de.sfgmbh.applayer.core.model;

import java.util.List;

import de.sfgmbh.datalayer.core.definitions.IntfDataRetrievable;

public class RoomAllocation implements IntfDataRetrievable{

	private int roomAllocationId_;
	private Course course_;
	private Room room_;
	private String semester_;
	private int day_;
	private int time_;
	private String approved_;
	private String orgaMessage_;
	private String comment_;
	private List<RoomAllocation> conflictingAllocations_;
	
	public RoomAllocation() {
		this.roomAllocationId_ = -1;
	}
	
	/**
	 * @return the roomAllocationId_
	 */
	public int getRoomAllocationId_() {
		return roomAllocationId_;
	}

	/**
	 * @param roomAllocationId_ the roomAllocationId_ to set
	 */
	public void setRoomAllocationId_(int roomAllocationId_) {
		this.roomAllocationId_ = roomAllocationId_;
	}

	/**
	 * @return the course_
	 */
	public Course getCourse_() {
		return course_;
	}

	/**
	 * @param course_ the course_ to set
	 */
	public void setCourse_(Course course_) {
		this.course_ = course_;
	}

	/**
	 * @return the room_
	 */
	public Room getRoom_() {
		return room_;
	}

	/**
	 * @param room_ the room_ to set
	 */
	public void setRoom_(Room room_) {
		this.room_ = room_;
	}

	/**
	 * @return the semester_
	 */
	public String getSemester_() {
		return semester_;
	}

	/**
	 * @param semester_ the semester_ to set
	 */
	public void setSemester_(String semester_) {
		this.semester_ = semester_;
	}

	/**
	 * @return the day_
	 */
	public int getDay_() {
		return day_;
	}

	/**
	 * @param day_ the day_ to set
	 */
	public void setDay_(int day_) {
		this.day_ = day_;
	}

	/**
	 * @return the time_
	 */
	public int getTime_() {
		return time_;
	}

	/**
	 * @param time_ the time_ to set
	 */
	public void setTime_(int time_) {
		this.time_ = time_;
	}

	/**
	 * @return the approved_
	 */
	public String getApproved_() {
		return approved_;
	}

	/**
	 * @param approved_ the approved_ to set
	 */
	public void setApproved_(String approved_) {
		if (approved_.equals("accepted"))
			this.approved_ = "accepted";
		else if (approved_.equals("denied")) {
			this.approved_ = "denied";
		} else {
			this.approved_ = "waiting";
		}
	}

	/**
	 * @return the orgaMessage_
	 */
	public String getOrgaMessage_() {
		return orgaMessage_;
	}

	/**
	 * @param orgaMessage_ the orgaMessage_ to set
	 */
	public void setOrgaMessage_(String orgaMessage_) {
		this.orgaMessage_ = orgaMessage_;
	}

	/**
	 * @return the comment_
	 */
	public String getComment_() {
		return comment_;
	}

	/**
	 * @param comment_ the comment_ to set
	 */
	public void setComment_(String comment_) {
		this.comment_ = comment_;
	}
	
	
	/**
	 * @return the conflictingAllocations_
	 */
	public List<RoomAllocation> getConflictingAllocations_() {
		return conflictingAllocations_;
	}

	/**
	 * @param conflictingAllocations_ the conflictingAllocations_ to set
	 */
	public void setConflictingAllocations_() {
		if (this.conflictingAllocations_ == null) {
			List<RoomAllocation> ral = AppModel.getInstance().repositoryRoomAllocation.getConflictingAllocation(this);
			this.conflictingAllocations_ = ral;
		}
	}
	
	/**
	 * Save this room allocation in the DB
	 */
	public void save() {
		AppModel.getInstance().repositoryRoomAllocation.save(this);
	}

	/**
	 * Check if this allocation is published (approved by the organ staff and published by the lecturer)
	 * @return true if this allocation is published
	 */
	public boolean isPublic() {
		
		if (this.course_.isLecturerEnabled_() && this.approved_.equals("accepted")) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
