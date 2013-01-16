package de.sfgmbh.applayer.organisation.controller;

import java.util.List;

import de.sfgmbh.applayer.core.model.AppModel;
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
	
}
