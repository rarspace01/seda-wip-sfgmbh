package de.sfgmbh.applayer.organisation.controller;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#
	 * acceptRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean acceptRoomAllocation(IntfRoomAllocation roomAllocations) {
		// Get the currently up to date room allocation
		IntfRoomAllocation currentRoomAllocations = AppModel.getInstance()
				.getRepositoryRoomAllocation()
				.get(roomAllocations.getRoomAllocationId_());

		// Denied allocations will not get accepted
		if (currentRoomAllocations.getApproved_().equals("denied")) {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Eine bereits abgelehnte Raumbelegung kann nicht freigegeben werden. Bitten Sie den Dozenten eine erneute Anfrage zu stellen.",
							"Fehler!");
			return false;
		}

		// Check if it is possible to set it to accepted and do so if yes
		if (!currentRoomAllocations.getApproved_().equals("accepted")) {
			if (!currentRoomAllocations.isConflicting_()) {
				currentRoomAllocations.setApproved_("accepted");
				return currentRoomAllocations.save();
			} else {
				boolean allowSave = false;
				currentRoomAllocations.setConflictingAllocations_();
				for (IntfRoomAllocation conflictRoomAllocations : currentRoomAllocations
						.getConflictingAllocations_()) {
					if (conflictRoomAllocations.getApproved_()
							.equals("waiting")
							|| conflictRoomAllocations.getApproved_().equals(
									"denied")) {
						allowSave = true;
					}
				}
				if (allowSave) {
					currentRoomAllocations.setApproved_("accepted");
					long currentTime = System.currentTimeMillis() / 1000L;
					String comment = "accepted_at_"
							+ String.valueOf(currentTime);
					currentRoomAllocations.setComment_(comment);
					return currentRoomAllocations.save();
				} else {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben oder als Gegenvorschlag eingetragen.",
									"Fehler!");
				}
			}
		} else {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Diese Raumbelegung ist bereits freigegeben!",
							"Fehler!");
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#
	 * getAllRoomAllocations()
	 */
	@Override
	public List<IntfRoomAllocation> getAllRoomAllocations() {
		return AppModel.getInstance().getRepositoryRoomAllocation().getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#
	 * denyRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean denyRoomAllocation(IntfRoomAllocation roomAllocation) {
		IntfRoomAllocation currentRoomAllocation = AppModel.getInstance()
				.getRepositoryRoomAllocation()
				.get(roomAllocation.getRoomAllocationId_());
		if (currentRoomAllocation.getApproved_().equals("accepted")
				|| currentRoomAllocation.getApproved_().equals("waiting")
				|| currentRoomAllocation.getApproved_().equals("counter")) {
			currentRoomAllocation.setApproved_("denied");
			long currentTime = System.currentTimeMillis() / 1000L;
			String comment = "denied_at_" + String.valueOf(currentTime);
			currentRoomAllocation.setComment_(comment);
			return currentRoomAllocation.save();
		} else if (currentRoomAllocation.getApproved_().equals("denied")) {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Diese Raumbelegung ist bereits abgelehnt!",
							"Fehler!");
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#suggest
	 * (de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public IntfRoomAllocation suggest(IntfRoomAllocation roomAllocation) {
		List<IntfRoomAllocation> currentAllocations = AppModel.getInstance()
				.getRepositoryRoomAllocation().getAllOpen();
		HashMap<String, String> roomFilter = new HashMap<String, String>();
		roomFilter.put("seats", String.valueOf(roomAllocation.getCourse_()
				.getExpectedAttendees_()));
		List<IntfRoom> matchingRooms = AppModel.getInstance()
				.getRepositoryRoom().getByFilter(roomFilter);

		// Iterate over all matching rooms
		for (IntfRoom room : matchingRooms) {
			// For each room iterate over all days except Saturday and Sunday
			for (int day = 1; day < 6; day++) {
				// For each day iterate over all times except 18:00 to 20:00 and
				// 20:00 to 22:00
				for (int time = 1; time < 6; time++) {
					// Finally iterate over all existing room allocations and
					// when there is none for this time, create the suggestion
					// room allocation here
					boolean isFree = true;
					for (IntfRoomAllocation existingRoomAllocation : currentAllocations) {
						if (existingRoomAllocation.getDay_() == day
								&& existingRoomAllocation.getTime_() == time
								&& existingRoomAllocation.getSemester_()
										.equals(roomAllocation.getSemester_())
								&& existingRoomAllocation.getRoom_()
										.getRoomId_() == room.getRoomId_()) {
							isFree = false;
						}
					}
					if (isFree) {
						IntfRoomAllocation suggestAllocation = new RoomAllocation();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#
	 * createCounterProposal(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean createCounterProposal(IntfRoomAllocation roomAllocation) {
		if (roomAllocation.getApproved_().equals("denied")) {
			return false;
		}

		roomAllocation.setApproved_("counter");
		long currentTime = System.currentTimeMillis() / 1000L;
		String comment = "counter_at_" + String.valueOf(currentTime);
		roomAllocation.setComment_(comment);

		// Check if it should be possible to set this room allocation as counter
		// and do so if yes
		if (!roomAllocation.isConflicting_()) {
			return roomAllocation.save();
		} else {
			boolean allowSave = false;
			roomAllocation.setConflictingAllocations_();
			for (IntfRoomAllocation conflictRoomAllocations : roomAllocation
					.getConflictingAllocations_()) {
				if (conflictRoomAllocations.getApproved_().equals("waiting")
						|| conflictRoomAllocations.getApproved_().equals(
								"denied")) {
					allowSave = true;
				}
			}
			if (roomAllocation.getConflictingAllocations_().isEmpty()) {
				allowSave = true;
			}
			if (allowSave) {
				return roomAllocation.save();
			} else {
				AppModel.getInstance()
						.getExceptionHandler()
						.setNewException(
								"Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben oder als Gegenvorschlag eingetragen.",
								"Fehler!");
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.controller.IntfCtrlRoomAllocation#
	 * cleanRoomAllocations()
	 */
	@Override
	public boolean cleanRoomAllocations() {
		return AppModel.getInstance().getRepositoryRoomAllocation().clean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation#
	 * getLectureOnTime(java.util.List, int, int, boolean, boolean)
	 */
	@Override
	public String getLectureOnTime(List<IntfRoomAllocation> roomAllocations,
			int day, int time, boolean showRoomName, boolean markDuplicates) {
		String textStart = "<html>";
		String textualRepresentation = "";
		// check for room allocations on given time
		for (int i = 0; i < roomAllocations.size(); i++) {
			if (roomAllocations.get(i).getDay_() == day
					&& roomAllocations.get(i).getTime_() == time) {
				if (showRoomName) {
					textualRepresentation += roomAllocations.get(i)
							.getCourse_().getCourseAcronym_()
							+ " - "
							+ roomAllocations.get(i).getRoom_()
									.getRoomNumber_() + "<br/>";
				} else {
					textualRepresentation += roomAllocations.get(i)
							.getCourse_().getCourseAcronym_()
							+ "<br/>";
				}

			}
		}
		if (new CtrlGenericTables().countBreaklines(textualRepresentation) > 1
				&& markDuplicates) {
			textualRepresentation = "<font color='red'>"
					+ textualRepresentation + "</font>";
		}
		return textStart + textualRepresentation + "</html>";
	}

}
