package de.sfgmbh.applayer.lecturer.controller;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;

/**
 * Controller for the lecturer's start tab
 * 
 * @author hannes
 * @author christian
 *
 */
public class CtrlStartTab implements IntfCtrlStartTab {

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#saveCourse(de.sfgmbh.applayer.core.model.Course)
	 */
	@Override
	public boolean saveCourse(IntfCourse course) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		// Check if course is valid
		if (course.validate()) {
			IntfUser loggedInUser = SessionManager.getInstance().getSession();
			// Check if the user for the course is a lecturer
			if (course.getLecturer_().getChair_() != null) {
				// Check if the logged in user is from the same chair as the user he tries to save the course for
				if (loggedInUser.getChair_().getChairId_() == course.getLecturer_().getChair_().getChairId_()) {
					if (course.save()) {
						return true;
					}
				} else {
					exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls bearbeiten oder erstellen!", "Fehler!", "error");
				}
			} else {
				exceptionHandler.setNewException("Lehrveranstaltungen können nur für Dozenten erstellt werden!", "Fehler!", "error");
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#deleteCourse(de.sfgmbh.applayer.core.model.Course)
	 */
	@Override
	public boolean deleteCourse(IntfCourse course) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		IntfUser loggedInUser = SessionManager.getInstance().getSession();
		// Check if the user for the course is a lecturer
		if (course.getLecturer_().getChair_() != null) {
			// Check if the logged in user is from the same chair as the user he tries to save the course for
			if (loggedInUser.getChair_().getChairId_() == course.getLecturer_().getChair_().getChairId_()) {
				return AppModel.getInstance().getRepositoryCourse().delete(course);
			} else {
				exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls löschen!", "Fehler!", "error");
			}
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten (die Veranstaltung konnte keinem Dozenten zugeordnet werden)!", "Fehler!", "error");
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#suggest(de.sfgmbh.applayer.core.model.RoomAllocation, java.util.HashMap)
	 */
	@Override
	public IntfRoomAllocation suggest(IntfRoomAllocation roomAllocation, HashMap<String, String> filter) {
		List<IntfRoomAllocation> currentAllocations = AppModel.getInstance().getRepositoryRoomAllocation().getAllOpen();
		List<IntfRoom> matchingRooms = AppModel.getInstance().getRepositoryRoom().getByFilter(filter);
		
		// Iterate over all matching rooms
		for (IntfRoom room : matchingRooms) {
			// For each room iterate over all days except Saturday and Sunday
			for (int day = 1; day < 6; day++) {
				// For each day iterate over all times except 18:00 to 20:00 and 20:00 to 22:00
				for (int time = 1; time < 6; time++) {
					// Finally iterate over all existing room allocations and when there is none for this time, create the suggestion room allocation here
					boolean isFree = true;
					for (IntfRoomAllocation existingRoomAllocation : currentAllocations) {
						if (existingRoomAllocation.getDay_() == day && 
								existingRoomAllocation.getTime_() == time && 
								existingRoomAllocation.getSemester_().equals(roomAllocation.getSemester_()) &&
								existingRoomAllocation.getRoom_().getRoomId_() == room.getRoomId_()) {
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#createRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean createRoomAllocation(IntfRoomAllocation roomAllocation) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		IntfUser loggedInUser = SessionManager.getInstance().getSession();
		
		// Ensure that the waiting status is set
		roomAllocation.setApproved_("waiting");
		
		// Ensure that a valid time/date is set
		if (roomAllocation.getTime_() < 1 || 
				roomAllocation.getTime_() > 7 ||
				roomAllocation.getDay_() < 1 || 
				roomAllocation.getDay_() > 7 ||
				roomAllocation.getSemester_() == null || 
				roomAllocation.getSemester_().equals("")){
			exceptionHandler.setNewException("Bitte wählen Sie ein gültiges Datum!", "Fehler!", "error");
			return false;
		}
			
		
		// Check if the user for the course is a lecturer
		if (roomAllocation.getCourse_().getLecturer_().getChair_() != null) {
			// Check if the logged in user is from the same chair as the user he tries to create an allocation for
			if (loggedInUser.getChair_().getChairId_() == roomAllocation.getCourse_().getLecturer_().getChair_().getChairId_()) {
				
				// Check if room allocation may be saved and do so if possible
				boolean allowSave = false;
				roomAllocation.setConflictingAllocations_();
				if (roomAllocation.getConflictingAllocations_().isEmpty()) {
					allowSave = true;
				}
				for (IntfRoomAllocation conflictRoomAllocations : roomAllocation.getConflictingAllocations_()){
					if (conflictRoomAllocations.getApproved_().equals("waiting") || conflictRoomAllocations.getApproved_().equals("denied")) {
						if (conflictRoomAllocations.getCourse_().getCourseId_() != roomAllocation.getCourse_().getCourseId_()) {
							allowSave = true;
						}
					}
				}
				if (allowSave) {
					return roomAllocation.save();
				} else {
					AppModel.getInstance().getExceptionHandler().setNewException("Eine andere Raumbelegung auf diesem Zeitslot ist bereits freigegeben oder als Gegenvorschlag eingetragen.", "Fehler!");
				}

			} else {
				exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls anlegen!", "Fehler!", "error");
			}
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten (die Veranstaltung konnte keinem Dozenten zugeordnet werden)!", "Fehler!", "error");
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#revokeRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean revokeRoomAllocation(IntfRoomAllocation roomAllocation) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		IntfUser loggedInUser = SessionManager.getInstance().getSession();
		
		// Set the status
		roomAllocation.setApproved_("denied");
		
		// Check if the user for the course is a lecturer
		if (roomAllocation.getCourse_().getLecturer_().getChair_() != null) {
			// Check if the logged in user is from the same chair as the user he tries to create an allocation for
			if (loggedInUser.getChair_().getChairId_() == roomAllocation.getCourse_().getLecturer_().getChair_().getChairId_()) {
				
				return roomAllocation.save();
	
			} else {
				exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls zurückziehen!", "Fehler!", "error");
			}
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten (die Veranstaltung konnte keinem Dozenten zugeordnet werden)!", "Fehler!", "error");
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.controller.IntfCtrlStartTab#counterRoomAllocation(de.sfgmbh.applayer.core.model.RoomAllocation, boolean)
	 */
	@Override
	public boolean counterRoomAllocation(IntfRoomAllocation roomAllocation, boolean accept) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		IntfUser loggedInUser = SessionManager.getInstance().getSession();
		
		// Check for current room location
		roomAllocation = AppModel.getInstance().getRepositoryRoomAllocation().get(roomAllocation.getRoomAllocationId_());
		
		// Set the correct status if allowed 
		if (accept && roomAllocation.getApproved_().equals("counter")) {
			roomAllocation.setApproved_("accepted");
		} else if (!accept && roomAllocation.getApproved_().equals("counter")) {
			roomAllocation.setApproved_("denied");
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten!", "Fehler!", "error");
			return false;
		}
		
		// Check if the user for the course is a lecturer
		if (roomAllocation.getCourse_().getLecturer_().getChair_() != null) {
			// Check if the logged in user is from the same chair as the user he tries to create an allocation for
			if (loggedInUser.getChair_().getChairId_() == roomAllocation.getCourse_().getLecturer_().getChair_().getChairId_()) {
				
				return roomAllocation.save();
	
			} else {
				exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls zurückziehen!", "Fehler!", "error");
			}
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten (die Veranstaltung konnte keinem Dozenten zugeordnet werden)!", "Fehler!", "error");
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab#hasBeenSeenAllocation(de.sfgmbh.applayer.core.definitions.IntfRoomAllocation)
	 */
	@Override
	public boolean hasBeenSeenAllocation(IntfRoomAllocation roomAllocation) {
		
		// Check for current room location
		roomAllocation = AppModel.getInstance().getRepositoryRoomAllocation().get(roomAllocation.getRoomAllocationId_());
		
		// Generate tag
		long currentTime = System.currentTimeMillis() / 1000L;
		String commentTag = "seen_at_" + (String.valueOf(currentTime));
		roomAllocation.setComment_(commentTag);
		
		return roomAllocation.save();
	}
}
