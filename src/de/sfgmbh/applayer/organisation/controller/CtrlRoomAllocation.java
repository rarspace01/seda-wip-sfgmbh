package de.sfgmbh.applayer.organisation.controller;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;

/**
 * Controller for the room allocation handling of the organization
 * 
 * @author hannes
 * @author denis
 *
 */
public class CtrlRoomAllocation {
	
	/**
	 * Accept a room allocation if possible
	 * @param roomAllocation
	 */
	public boolean acceptRoomAllocation(RoomAllocation ra) {
		RoomAllocation currentRa = AppModel.getInstance().getRepositoryRoomAllocation().get(ra.getRoomAllocationId_());
		if (!currentRa.getApproved_().equals("accepted")){
			if (currentRa.getConflictingAllocations_().isEmpty()){
				currentRa.setApproved_("accepted");
				return currentRa.save();
			} else {
				boolean allowSave = false;
				for (RoomAllocation conflictRa : currentRa.getConflictingAllocations_()){
					if (conflictRa.getApproved_().equals("waiting") || conflictRa.getApproved_().equals("denied")) {
						allowSave = true;
					}
				}
				if (allowSave) {
					currentRa.setApproved_("accepted");
					return currentRa.save();
				} else {
					AppModel.getInstance().getExceptionHandler().setNewException("Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben.", "Fehler!");
				}
			}
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Diese Raumbelegung ist bereits freigegeben!", "Fehler!");
		}
		return false;
	}

	/**
	 * Get all room allocations
	 * 
	 * @return a list of all RoomAlloction objects
	 * @deprecated As no further filtering is done here the method should be used in RepositoryRoomAllocation directly instead.
	 */
	public List<RoomAllocation> getAllRoomAllocations(){
		return AppModel.getInstance().getRepositoryRoomAllocation().getAll();
	}
	
	/**
	 * Deny a room allocation if possible
	 * @param roomAllocation
	 */
	public boolean denyRoomAllocation(RoomAllocation ra) {
		RoomAllocation currentRa = AppModel.getInstance().getRepositoryRoomAllocation().get(ra.getRoomAllocationId_());
		if (currentRa.getApproved_().equals("accepted") || currentRa.getApproved_().equals("waiting") || currentRa.getApproved_().equals("counter")){
			currentRa.setApproved_("denied");
			return currentRa.save();
		} else if (currentRa.getApproved_().equals("denied")) {
			AppModel.getInstance().getExceptionHandler().setNewException("Diese Raumbelegung ist bereits abgelehnt!", "Fehler!");
		}
		return false;
	}

	/**
	 * Suggest a time slot for a room allocation. Currently set time slot is, expect the semester, ignored. Expected attendees of the course are taken into account.
	 * @param roomAllocation
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public RoomAllocation suggest(RoomAllocation roomAllocation) {
		List<RoomAllocation> currentAllocations = AppModel.getInstance().getRepositoryRoomAllocation().getAll();
		HashMap<String, String> roomFilter = new HashMap<String, String>();
		roomFilter.put("seats", String.valueOf(roomAllocation.getCourse_().getExpectedAttendees_()));
		List<Room> matchingRooms = AppModel.getInstance().getRepositoryRoom().getByFilter(roomFilter);
		
		// Iterate over all matching rooms
		for (Room room : matchingRooms) {
			// For each room iterate over all days except Saturday and Sunday
			for (int day = 1; day < 6; day++) {
				// For each day iterate over all times except 18:00 to 20:00 and 20:00 to 22:00
				for (int time = 1; time < 6; time++) {
					// Finally iterate over all existing room allocations and when there is none for this time, create the suggestion room allocation here
					boolean isFree = true;
					for (RoomAllocation existingRoomAllocation : currentAllocations) {
						if (existingRoomAllocation.getDay_() == day && 
								existingRoomAllocation.getTime_() == time && 
								existingRoomAllocation.getSemester_() == roomAllocation.getSemester_() &&
								existingRoomAllocation.getRoom_().getRoomId_() == room.getRoomId_()) {
							isFree = false;
						}
					}
					if (isFree) {
						RoomAllocation suggestAllocation = new RoomAllocation();
						suggestAllocation = roomAllocation;
						suggestAllocation.setRoom_(room);
						suggestAllocation.setDay_(day);
						suggestAllocation.setTime_(time);
						return suggestAllocation;
					}
				}
			}
		}
		return null;
	}
	
}
