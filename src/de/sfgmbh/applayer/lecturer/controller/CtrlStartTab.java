package de.sfgmbh.applayer.lecturer.controller;

import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.core.model.User;

/**
 * Controller for the lecturer's start tab
 * 
 * @author hannes
 * @author christian
 *
 */
public class CtrlStartTab {

	/**
	 * Create or edit a course if it is a valid course
	 * @param course
	 * @return true if the creation was successful
	 */
	public boolean saveCourse(Course course) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		// Check if course is valid
		if (course.validate()) {
			User loggedInUser = SessionManager.getInstance().getSession();
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
	
	/**
	 * Delete a course
	 * @param course
	 * @return true on success
	 */
	public boolean deleteCourse(Course course) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		
		User loggedInUser = SessionManager.getInstance().getSession();
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
	
	/**
	 * Suggest a time slot for a room allocation and a set of filters (seat preferences). Currently set time slot is, expect the semester, ignored.
	 * @return a room allocation with a free time slot, null if there are none
	 */
	public RoomAllocation suggest(RoomAllocation roomAllocation, HashMap<String, String> filter) {
		List<RoomAllocation> currentAllocations = AppModel.getInstance().getRepositoryRoomAllocation().getAllOpen();
		List<Room> matchingRooms = AppModel.getInstance().getRepositoryRoom().getByFilter(filter);
		
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
	
	/**
	 * Create a new room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public boolean createRoomAllocation(RoomAllocation roomAllocation) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		User loggedInUser = SessionManager.getInstance().getSession();
		
		// Ensure that the waiting status is set
		roomAllocation.setApproved_("waiting");
		
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
				for (RoomAllocation conflictRa : roomAllocation.getConflictingAllocations_()){
					if (conflictRa.getApproved_().equals("waiting") || conflictRa.getApproved_().equals("denied")) {
						if (conflictRa.getCourse_().getCourseId_() != roomAllocation.getCourse_().getCourseId_()) {
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
				exceptionHandler.setNewException("Sie können nur Lehrveranstaltungen für Dozenten Ihres Lehrstuhls löschen!", "Fehler!", "error");
			}
		} else {
			exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten (die Veranstaltung konnte keinem Dozenten zugeordnet werden)!", "Fehler!", "error");
		}
		
		return false;
	}
	
	/**
	 * Revoke a new room allocation
	 * @param roomAllocation
	 * @return true on success
	 */
	public boolean revokeRoomAllocation(RoomAllocation roomAllocation) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		User loggedInUser = SessionManager.getInstance().getSession();
		
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
	
	/**
	 * Accept or deny a counter proposal where the boolean value decied which it should be
	 * @param roomAllocation
	 * @param accapt
	 * @return true on success
	 */
	public boolean counterRoomAllocation(RoomAllocation roomAllocation, boolean accept) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		User loggedInUser = SessionManager.getInstance().getSession();
		
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
}
