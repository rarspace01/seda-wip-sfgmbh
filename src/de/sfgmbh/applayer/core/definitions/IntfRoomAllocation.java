package de.sfgmbh.applayer.core.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.RoomAllocation;

/**
 * Interface for {@link RoomAllocation} object
 * @author hannes
 *
 */
public interface IntfRoomAllocation {

	/**
	 * @return the roomAllocationId_
	 */
	public abstract int getRoomAllocationId_();

	/**
	 * @param roomAllocationId_ the roomAllocationId_ to set
	 */
	public abstract void setRoomAllocationId_(int roomAllocationId_);

	/**
	 * @return the course_
	 */
	public abstract IntfCourse getCourse_();

	/**
	 * @param course_ the course_ to set
	 */
	public abstract void setCourse_(IntfCourse course_);

	/**
	 * @return the room_
	 */
	public abstract IntfRoom getRoom_();

	/**
	 * @param room_ the room_ to set
	 */
	public abstract void setRoom_(IntfRoom room_);

	/**
	 * @return the semester_
	 */
	public abstract String getSemester_();

	/**
	 * @param semester_ the semester_ to set
	 */
	public abstract void setSemester_(String semester_);

	/**
	 * @return the day_
	 */
	public abstract int getDay_();

	/**
	 * @param day_ the day_ to set
	 */
	public abstract void setDay_(int day_);

	/**
	 * @return the time_
	 */
	public abstract int getTime_();

	/**
	 * @param time_ the time_ to set
	 */
	public abstract void setTime_(int time_);

	/**
	 * @return the approved_
	 */
	public abstract String getApproved_();

	/**
	 * Set the approval status of the room allocation Valid status strings are:<br><br>
	 * 
	 * "accepted" - The room allocation is approved by the organization<br>
	 * "denied" - The room allocation is denied by the organization<br>
	 * "waiting" - The room allocation is not yet reviewed by the organization<br>
	 * "counter" - The room allocation marks a counter proposal by the organization,<br>
	 * this should be changed to "accepted" or "denied" automatically as soon as<br>
	 * the lecturer accepts or denies the proposal. Until then time slot for this room<br>
	 * allocation may not be taken by other allocations.<br><br>
	 * 
	 * "waiting" is the default status which will be set if no other valid status was
	 * submitted.
	 * 
	 * @param status
	 */
	public abstract void setApproved_(String approved_);

	/**
	 * @return the orgaMessage_
	 */
	public abstract String getOrgaMessage_();

	/**
	 * Set the organization message. The message will be cut after 10 000 characters.
	 * @param orgaMessage_ the orgaMessage_ to set
	 */
	public abstract void setOrgaMessage_(String orgaMessage_);

	/**
	 * @return the comment_
	 */
	public abstract String getComment_();

	/**
	 * @param comment_ the comment_ to set
	 */
	public abstract void setComment_(String comment_);

	/**
	 * @return the conflictingAllocations_
	 */
	public abstract List<RoomAllocation> getConflictingAllocations_();

	/**
	 * Set conflicting room allocations when this room allocation doesn't already have any conflicting allocations
	 * @param conflictingAllocations_ the conflictingAllocations_ to set
	 */
	public abstract void setConflictingAllocations_();

	/**
	 * Set conflicting room allocations regardless if there already are any set
	 * @param conflictingAllocations_ the conflictingAllocations_ to set
	 */
	public abstract void setForceConflictingAllocations_();

	/**
	 * Save this room allocation in the DB
	 * @return true on success
	 */
	public abstract boolean save();

	/**
	 * Check if this allocation is published (approved by the organ staff and published by the lecturer)
	 * @return true if this allocation is published
	 */
	public abstract boolean isPublic();

	/**
	 * @return the conflicting_
	 */
	public abstract boolean isConflicting_();

	/**
	 * @param conflicting_ the conflicting_ to set
	 */
	public abstract void setConflicting_(boolean conflicting_);

}