package de.sfgmbh.applayer.core.model;

import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;

/**
 * Model for room allocations
 * 
 * @author denis
 * @author hannes
 *
 */
public class RoomAllocation implements IntfRoomAllocation {

	private int roomAllocationId_;
	private IntfCourse course_;
	private IntfRoom room_;
	private String semester_;
	private int day_;
	private int time_;
	private String approved_;
	private String orgaMessage_;
	private String comment_;
	private List<IntfRoomAllocation> conflictingAllocations_;
	private boolean conflicting_;
	
	/**
	 * Create a default room allocation
	 */
	public RoomAllocation() {
		this.roomAllocationId_ = -1;
		this.conflicting_ = false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getRoomAllocationId_()
	 */
	@Override
	public int getRoomAllocationId_() {
		return roomAllocationId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setRoomAllocationId_(int)
	 */
	@Override
	public void setRoomAllocationId_(int roomAllocationId_) {
		this.roomAllocationId_ = roomAllocationId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getCourse_()
	 */
	@Override
	public IntfCourse getCourse_() {
		return course_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setCourse_(de.sfgmbh.applayer.core.definitions.IntfCourse)
	 */
	@Override
	public void setCourse_(IntfCourse course_) {
		this.course_ = course_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getRoom_()
	 */
	@Override
	public IntfRoom getRoom_() {
		return room_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setRoom_(de.sfgmbh.applayer.core.definitions.IntfRoom)
	 */
	@Override
	public void setRoom_(IntfRoom room_) {
		this.room_ = room_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getSemester_()
	 */
	@Override
	public String getSemester_() {
		return semester_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setSemester_(java.lang.String)
	 */
	@Override
	public void setSemester_(String semester_) {
		this.semester_ = semester_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getDay_()
	 */
	@Override
	public int getDay_() {
		return day_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setDay_(int)
	 */
	@Override
	public void setDay_(int day_) {
		this.day_ = day_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getTime_()
	 */
	@Override
	public int getTime_() {
		return time_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setTime_(int)
	 */
	@Override
	public void setTime_(int time_) {
		this.time_ = time_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getApproved_()
	 */
	@Override
	public String getApproved_() {
		return approved_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setApproved_(java.lang.String)
	 */
	@Override
	public void setApproved_(String approved_) {
		if (approved_.equals("accepted")) {
			this.approved_ = "accepted";
		} else if (approved_.equals("denied")) {
			this.approved_ = "denied";
		}  else if (approved_.equals("counter")) {
			this.approved_ = "counter";
		} else {
			this.approved_ = "waiting";
		}
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getOrgaMessage_()
	 */
	@Override
	public String getOrgaMessage_() {
		return orgaMessage_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setOrgaMessage_(java.lang.String)
	 */
	@Override
	public void setOrgaMessage_(String orgaMessage_) {
		if (orgaMessage_ != null) {
			if (orgaMessage_.length() > 10000) {
				orgaMessage_ = orgaMessage_.substring(0, 10000);
			}
		}
		this.orgaMessage_ = orgaMessage_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getComment_()
	 */
	@Override
	public String getComment_() {
		return comment_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setComment_(java.lang.String)
	 */
	@Override
	public void setComment_(String comment_) {
		this.comment_ = comment_;
	}
	
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#getConflictingAllocations_()
	 */
	@Override
	public List<IntfRoomAllocation> getConflictingAllocations_() {
		return conflictingAllocations_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setConflictingAllocations_()
	 */
	@Override
	public void setConflictingAllocations_() {
		if (this.conflictingAllocations_ == null) {
			List<IntfRoomAllocation> roomAllocationList = AppModel.getInstance().getRepositoryRoomAllocation().getConflictingAllocation(this);
			this.conflictingAllocations_ = roomAllocationList;
		}
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setForceConflictingAllocations_()
	 */
	@Override
	public void setForceConflictingAllocations_() {
		List<IntfRoomAllocation> roomAllocationList = AppModel.getInstance().getRepositoryRoomAllocation().getConflictingAllocation(this);
		this.conflictingAllocations_ = roomAllocationList;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#save()
	 */
	@Override
	public boolean save() {
		return AppModel.getInstance().getRepositoryRoomAllocation().save(this);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#isPublic()
	 */
	@Override
	public boolean isPublic() {
		
		if (this.course_.isLecturerEnabled_() && this.approved_.equals("accepted")) {
			return true;
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#isConflicting_()
	 */
	@Override
	public boolean isConflicting_() {
		return conflicting_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoomAllocation#setConflicting_(boolean)
	 */
	@Override
	public void setConflicting_(boolean conflicting_) {
		this.conflicting_ = conflicting_;
	}

}
