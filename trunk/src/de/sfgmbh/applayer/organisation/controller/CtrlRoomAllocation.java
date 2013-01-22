package de.sfgmbh.applayer.organisation.controller;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;

/**
 * Controller for the room allocation handling of the organization
 * 
 * @author hannes
 * @author denis
 *
 */
public class CtrlRoomAllocation implements IntfCtrlRoomAllocation {
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#acceptRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean acceptRoomAllocation(RoomAllocation ra) {
		// Get the currently up to date room allocation
		RoomAllocation currentRa = AppModel.getInstance().getRepositoryRoomAllocation().get(ra.getRoomAllocationId_());
		
		// Denied allocations will not get accepted
		if (currentRa.getApproved_().equals("denied")) {
			AppModel.getInstance().getExceptionHandler().setNewException("Eine bereits abgelehnte Raumbelegung kann nicht freigegeben werden. Bitten Sie den Dozenten eine erneute Anfrage zu stellen.", "Fehler!");
			return false;
		}
		
		// Check if it is possible to set it to accepted and do so if yes
		if (!currentRa.getApproved_().equals("accepted")){
			if (!currentRa.isConflicting_()){
				currentRa.setApproved_("accepted");
				return currentRa.save();
			} else {
				boolean allowSave = false;
				currentRa.setConflictingAllocations_();
				for (RoomAllocation conflictRa : currentRa.getConflictingAllocations_()){
					if (conflictRa.getApproved_().equals("waiting") || conflictRa.getApproved_().equals("denied")) {
						allowSave = true;
					}
				}
				if (allowSave) {
					currentRa.setApproved_("accepted");
					return currentRa.save();
				} else {
					AppModel.getInstance().getExceptionHandler().setNewException("Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben oder als Gegenvorschlag eingetragen.", "Fehler!");
				}
			}
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Diese Raumbelegung ist bereits freigegeben!", "Fehler!");
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#getAllRoomAllocations()
	 */
	@Override
	public List<RoomAllocation> getAllRoomAllocations(){
		return AppModel.getInstance().getRepositoryRoomAllocation().getAll();
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#denyRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#suggest(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public RoomAllocation suggest(RoomAllocation roomAllocation) {
		List<RoomAllocation> currentAllocations = AppModel.getInstance().getRepositoryRoomAllocation().getAllOpen();
		HashMap<String, String> roomFilter = new HashMap<String, String>();
		roomFilter.put("seats", String.valueOf(roomAllocation.getCourse_().getExpectedAttendees_()));
		List<Room> matchingRooms = AppModel.getInstance().getRepositoryRoom().getByFilter(roomFilter);
		
		// Iterate over all matching rooms
		for (IntfRoom room : matchingRooms) {
			// For each room iterate over all days except Saturday and Sunday
			for (int day = 1; day < 6; day++) {
				// For each day iterate over all times except 18:00 to 20:00 and 20:00 to 22:00
				for (int time = 1; time < 6; time++) {
					// Finally iterate over all existing room allocations and when there is none for this time, create the suggestion room allocation here
					boolean isFree = true;
					for (RoomAllocation existingRoomAllocation : currentAllocations) {
						if (existingRoomAllocation.getDay_() == day && 
								existingRoomAllocation.getTime_() == time && 
								existingRoomAllocation.getSemester_().equals(roomAllocation.getSemester_()) &&
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#createCounterProposal(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean createCounterProposal(RoomAllocation roomAllocation) {
		if (roomAllocation.getApproved_().equals("denied")) {
			return false;
		}
		
		roomAllocation.setApproved_("counter");
		
		// Check if it should be possible to set this room allocation as counter and do so if yes
		if (!roomAllocation.isConflicting_()){
			return roomAllocation.save();
		} else {
			boolean allowSave = false;
			roomAllocation.setConflictingAllocations_();
			for (RoomAllocation conflictRa : roomAllocation.getConflictingAllocations_()){
				if (conflictRa.getApproved_().equals("waiting") || conflictRa.getApproved_().equals("denied")) {
					allowSave = true;
				}
			}
			if (roomAllocation.getConflictingAllocations_().isEmpty()) {
				allowSave = true;
			}
			if (allowSave) {
				return roomAllocation.save();
			} else {
				AppModel.getInstance().getExceptionHandler().setNewException("Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben oder als Gegenvorschlag eingetragen.", "Fehler!");
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#cleanRoomAllocations()
	 */
	@Override
	public boolean cleanRoomAllocations() {
		return AppModel.getInstance().getRepositoryRoomAllocation().clean();
	}
	
}
