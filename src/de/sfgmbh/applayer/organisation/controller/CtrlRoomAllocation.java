package de.sfgmbh.applayer.organisation.controller;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;

public class CtrlRoomAllocation {
	
	/**
	 * Accept a room allocation if possible
	 * @param ra
	 */
	public void acceptRoomAllocation(RoomAllocation ra) {
		RoomAllocation currentRa = AppModel.getInstance().getRepositoryRoomAllocation().get(ra.getRoomAllocationId_());
		if (!currentRa.getApproved_().equals("accepted")){
			if (currentRa.getConflictingAllocations_().isEmpty()){
				currentRa.setApproved_("accepted");
				currentRa.save();
			} else {
				boolean allowSave = false;
				for (RoomAllocation conflictRa : currentRa.getConflictingAllocations_()){
					if (conflictRa.getApproved_().equals("waiting") || conflictRa.getApproved_().equals("denied")) {
						allowSave = true;
					}
				}
				if (allowSave) {
					currentRa.setApproved_("accepted");
					currentRa.save();
				} else {
					AppModel.getInstance().getExceptionHandler().setNewException("Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben.", "Fehler!");
				}
			}
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Diese Raumbelegung ist bereits freigegeben!", "Fehler!");
		}
		return;
	}

}
