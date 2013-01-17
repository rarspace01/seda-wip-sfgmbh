package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Data handler for room allocations in the data base
 * 
 * @author hannes
 * @author denis
 *
 */
public class DataHandlerRoomAllocation implements IntfDataObservable, IntfDataFilter {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataManagerPostgreSql filterDm = null;
	private DataManagerPostgreSql conflictingAllocationDm = null;
	
	/**
	 * Get all room allocations
	 * @return a list with all room allocations
	 */
	public List<RoomAllocation> getAll() {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();

		String SqlStatement = "SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
								"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
								"WHERE public.roomallocation.courseid = public.course.courseid " +
								"AND public.course.lecturerid = public.user.userid " +
								"AND public.course.lecturerid = public.lecturer.userid " +
								"AND public.chair.chairid = public.lecturer.chairid " +
								"AND public.roomallocation.roomid = public.room.roomid " +
								"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC"
								;

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(resultSet, "normal"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return this.setConflicts(listRoomAllocation);
	}
	
	@Override
	public List<RoomAllocation> getByFilter(HashMap<String, String> filter) {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();
		
		// Translate some filter values when needed
		if (filter.containsKey("status") && filter.get("status").equals("wartend")) {
			filter.put("status", "waiting");
		}
		if (filter.containsKey("status") && filter.get("status").equals("freigegeben")) {
			filter.put("status", "accepted");
		}
		if (filter.containsKey("status") && filter.get("status").equals("abgelehnt")) {
			filter.put("status", "denied");
		}
		
		
		try {
			if (filterDm == null) { 
				filterDm = new DataManagerPostgreSql(); 
				filterDm.prepare(
						"SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
						"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
						"WHERE public.roomallocation.courseid = public.course.courseid " +
						"AND public.course.lecturerid = public.user.userid " +
						"AND public.course.lecturerid = public.lecturer.userid " +
						"AND public.chair.chairid = public.lecturer.chairid " +
						"AND public.roomallocation.roomid = public.room.roomid " +
						"AND (public.user.lname LIKE ? OR public.user.fname LIKE ? ) " +
						"AND (public.chair.chairname LIKE ? OR public.chair.chairacronym LIKE ? ) " +
						"AND (public.course.courseacronym LIKE ? OR public.course.coursename LIKE ? ) " +
						"AND public.roomallocation.semester LIKE ? " +
						"AND public.roomallocation.approved LIKE ? " +
						"AND public.room.roomnumber LIKE ? " +
						"AND public.user.login LIKE ? " +
						"AND public.room.roomid BETWEEN ? AND ? " +
						"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC");
				
			}
			if (filter.containsKey("lecturer") && filter.get("lecturer") != null && filter.get("lecturer") != "" && filter.get("lecturer") != "<alle>") {
				filterDm.getPreparedStatement().setString(1, "%" + filter.get("lecturer") + "%");
				filterDm.getPreparedStatement().setString(2, "%" + filter.get("lecturer") + "%");
			} else {
				filterDm.getPreparedStatement().setString(1, "%");
				filterDm.getPreparedStatement().setString(2, "%");
			}
			if (filter.containsKey("chair") && filter.get("chair") != null && filter.get("chair") != "" && filter.get("chair") != "<alle>") {
				filterDm.getPreparedStatement().setString(3, "%" + filter.get("chair") + "%");
				filterDm.getPreparedStatement().setString(4, "%" + filter.get("chair") + "%");
			} else {
				filterDm.getPreparedStatement().setString(3, "%");
				filterDm.getPreparedStatement().setString(4, "%");
			}
			if (filter.containsKey("course") && filter.get("course") != null && filter.get("course") != "" && filter.get("course") != "<alle>") {
				filterDm.getPreparedStatement().setString(5, "%" + filter.get("course") + "%");
				filterDm.getPreparedStatement().setString(6, "%" + filter.get("course") + "%");
			} else {
				filterDm.getPreparedStatement().setString(5, "%");
				filterDm.getPreparedStatement().setString(6, "%");
			}
			if (filter.containsKey("semester") && filter.get("semester") != null && filter.get("semester") != "" && filter.get("semester") != "<alle>") {
				filterDm.getPreparedStatement().setString(7, "%" + filter.get("semester") + "%");
			} else {
				filterDm.getPreparedStatement().setString(7, "%");
			}
			if (filter.containsKey("status") && filter.get("status") != null && filter.get("status") != "" && filter.get("status") != "<alle>") {
				filterDm.getPreparedStatement().setString(8, "%" + filter.get("status") + "%");
			} else {
				filterDm.getPreparedStatement().setString(8, "%");
			}
			if (filter.containsKey("room") && filter.get("room") != null && filter.get("room") != "" && filter.get("room") != "<alle>") {
				filterDm.getPreparedStatement().setString(9, "%" + filter.get("room") + "%");
			} else {
				filterDm.getPreparedStatement().setString(9, "%");
			}
			if (filter.containsKey("login") && filter.get("login") != null && filter.get("login") != "" && filter.get("login") != "<alle>") {
				filterDm.getPreparedStatement().setString(10, filter.get("login"));
			} else {
				filterDm.getPreparedStatement().setString(10, "%");
			}
			if (filter.containsKey("roomid") && filter.get("roomid") != null && filter.get("roomid") != "" && filter.get("roomid") != "<alle>") {
				filterDm.getPreparedStatement().setInt(11, Integer.parseInt(filter.get("roomid")));
				filterDm.getPreparedStatement().setInt(12, Integer.parseInt(filter.get("roomid")));
			} else {
				filterDm.getPreparedStatement().setInt(11, 0);
				filterDm.getPreparedStatement().setInt(12, 2147483647);
			}
			
			ResultSet rs = filterDm.selectPstmt();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "normal"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-17:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-18:<br />" + e.toString()), "Fehler!");
		}
		
		return this.setConflicts(listRoomAllocation);
	}
	
	/**
	 * Checks for conflicting room allocations for one given room allocation
	 * @param roomAllocation
	 * @return a list of all conflicting room allocations
	 */
	public List<RoomAllocation> getConflictingAllocation(RoomAllocation ra) {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();
		try {
			if (conflictingAllocationDm == null) { 
				conflictingAllocationDm = new DataManagerPostgreSql(); 
				conflictingAllocationDm.prepare(
						"SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
						"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
						"WHERE public.roomallocation.courseid = public.course.courseid " +
						"AND public.course.lecturerid = public.user.userid " +
						"AND public.course.lecturerid = public.lecturer.userid " +
						"AND public.chair.chairid = public.lecturer.chairid " +
						"AND public.roomallocation.roomid = public.room.roomid " +
						"AND public.roomallocation.roomid = ? " +
						"AND public.roomallocation.day = ? " +
						"AND public.roomallocation.time = ? " +
						"AND public.roomallocation.semester LIKE ? " +
						"AND public.roomallocation.approved NOT LIKE 'denied' " +
						"ANd public.roomallocation.roomallocationid <> ? " +
						"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC"
						);
			}
			conflictingAllocationDm.getPreparedStatement().setInt(1, ra.getRoom_().getRoomId_());
			conflictingAllocationDm.getPreparedStatement().setInt(2, ra.getDay_());
			conflictingAllocationDm.getPreparedStatement().setInt(3, ra.getTime_());
			conflictingAllocationDm.getPreparedStatement().setString(4, ra.getSemester_());
			conflictingAllocationDm.getPreparedStatement().setInt(5, ra.getRoomAllocationId_());
			
			ResultSet rs = conflictingAllocationDm.selectPstmt();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "conflictingChildObject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-16:<br />" + e.toString()), "Fehler!");
		}
		return this.setConflicts(listRoomAllocation);
	}
	
	/**
	 * Get a list of all conflicting dates. <br>
	 * That means courses in the same room, at the same time in the same semester <br>
	 * @return an ArrayList of HashMaps for each conflicting date. <br>
	 * 		The HashMap has the following entries: <br>
	 * 		"roomid" - the affected ID of the room <br>
	 * 		"time" - the Integer of the affected time <br>
	 * 		"day" - the Integer of the affected day <br>
	 * 		"semester" - the String of the affected Semester
	 */
	public List<HashMap<String, Object>> getConflictingDates() {
		List<HashMap<String, Object>> conflictingDates = new ArrayList<HashMap<String, Object>>();

		String SqlStatement = "SELECT COUNT(*), public.roomallocation.roomid, public.roomallocation.time, public.roomallocation.day, public.roomallocation.semester " +
								"FROM public.roomallocation " +
								"WHERE public.roomallocation.approved NOT LIKE 'denied' " +
								"GROUP BY public.roomallocation.roomid, public.roomallocation.time, public.roomallocation.day, public.roomallocation.semester " +
								"HAVING COUNT(*) > 1 ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				HashMap<String, Object> date = new HashMap<String, Object>();
				date.put("roomid", resultSet.getInt("roomid"));
				date.put("time", resultSet.getInt("time"));
				date.put("day", resultSet.getInt("day"));
				date.put("semester", resultSet.getString("semester"));
				conflictingDates.add(date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return conflictingDates;
	}
	
	/**
	 * For a given list of room allocations set their conflicting state appropriate
	 * @param roomAllocations
	 * @return a list of room allocations with the correct conflicting state
	 */
	public List<RoomAllocation> setConflicts(List<RoomAllocation> roomAllocations) {
		List<HashMap<String, Object>> conflictingDates = this.getConflictingDates();
		List<RoomAllocation> setAllocations = new ArrayList<RoomAllocation>();
		
		// Iterate over all room allocations and check for each allocation all conflicting dates
		for (RoomAllocation roomAllocation : roomAllocations) {
			for (HashMap<String, Object> conflict : conflictingDates) {
				if (roomAllocation.getRoom_().getRoomId_() == (int) conflict.get("roomid") && 
						roomAllocation.getDay_() == (int) conflict.get("day") &&
						roomAllocation.getTime_() == (int) conflict.get("time") &&
						roomAllocation.getSemester_().equals((String) conflict.get("semester"))) {
					roomAllocation.setConflicting_(true);
				}
			}
			setAllocations.add(roomAllocation);
		}
		
		return setAllocations;
	}
	
	/**
	 * Get a room allocation by its id
	 * @param roomAllocationId
	 * @return a room allocation by its id
	 */
	public RoomAllocation get(int id) {
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
														"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
														"WHERE public.roomallocation.courseid = public.course.courseid " +
														"AND public.course.lecturerid = public.user.userid " +
														"AND public.course.lecturerid = public.lecturer.userid " +
														"AND public.chair.chairid = public.lecturer.chairid " +
														"AND public.roomallocation.roomid = public.room.roomid " +
														"AND roomallocationid = ? " +
														"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC");
			DataManagerPostgreSql.getInstance().getPreparedStatement().setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				RoomAllocation roomAllocation = this.makeRoomAllocation(rs, "normal");
				for (HashMap<String, Object> conflict : this.getConflictingDates()) {
					if (roomAllocation.getRoom_().getRoomId_() == (int) conflict.get("roomid") && 
							roomAllocation.getDay_() == (int) conflict.get("day") &&
							roomAllocation.getTime_() == (int) conflict.get("time") &&
							roomAllocation.getSemester_().equals((String) conflict.get("semester"))) {
						roomAllocation.setConflicting_(true);
					}
				}
				return roomAllocation;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-20:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-21:<br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}
	
	/**
	* Save a room allocation
	* @param roomAllocation
	* @return true on success
	*/
	public boolean save(RoomAllocation ra) {
		boolean returnStatus = false;
		if (ra.getRoomAllocationId_() == -1) {
			try {
				
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				
				dm.prepare("INSERT INTO public.roomallocation"
						+ "(courseid, roomid, semester, day, time, approved, orgamessage, comment)"
						+ "VALUES (?,?,?,?,?,?,?,?)");
				dm.getPreparedStatement().setInt(1, ra.getCourse_().getCourseId_());
				dm.getPreparedStatement().setInt(2, ra.getRoom_().getRoomId_());
				dm.getPreparedStatement().setString(3, ra.getSemester_());
				dm.getPreparedStatement().setInt(4, ra.getDay_());
				dm.getPreparedStatement().setInt(5, ra.getTime_());
				dm.getPreparedStatement().setString(6, ra.getApproved_());
				dm.getPreparedStatement().setString(7, ra.getOrgaMessage_());
				dm.getPreparedStatement().setString(8, ra.getComment_());
				returnStatus = dm.executePstmt();
				this.update();
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Fehler!");
			}
		} else {
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				dm.prepare("UPDATE public.roomallocation SET "
						+ "courseid = ?, roomid = ?, semester = ?, day = ?, time = ?, approved = ?, orgamessage = ?, comment = ? "
						+ "WHERE roomallocationid = ?");
				dm.getPreparedStatement().setInt(1, ra.getCourse_().getCourseId_());
				dm.getPreparedStatement().setInt(2, ra.getRoom_().getRoomId_());
				dm.getPreparedStatement().setString(3, ra.getSemester_());
				dm.getPreparedStatement().setInt(4, ra.getDay_());
				dm.getPreparedStatement().setInt(5, ra.getTime_());
				dm.getPreparedStatement().setString(6, ra.getApproved_());
				dm.getPreparedStatement().setString(7, ra.getOrgaMessage_());
				dm.getPreparedStatement().setString(8, ra.getComment_());
				dm.getPreparedStatement().setInt(9, ra.getRoomAllocationId_());
				returnStatus = dm.executePstmt();
				this.update();
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-23:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-24:<br />" + e.toString()), "Fehler!");
			}
		}
		return returnStatus;
	}
	
	/**
	 * Forms a RoomAllocation object out of a given result set
	 * @param resultSet
	 * @return a RoomAllocation object
	 */
	private RoomAllocation makeRoomAllocation(ResultSet rs, String variant) {
		RoomAllocation returnRoomAllocation = new RoomAllocation();
		
		try {
			returnRoomAllocation = new RoomAllocation();

			returnRoomAllocation.setRoomAllocationId_(rs.getInt("roomallocationid"));
			returnRoomAllocation.setCourse_(
					DataModel.getInstance().getDataHandlerCourse().makeCourse(rs));
			returnRoomAllocation.setRoom_(
					DataModel.getInstance().getDataHandlerRoom().makeRoom(rs));
			returnRoomAllocation.setSemester_(rs.getString("semester"));
			returnRoomAllocation.setDay_(rs.getInt("day"));
			returnRoomAllocation.setTime_(rs.getInt("time"));
			returnRoomAllocation.setApproved_(rs.getString("approved"));
			returnRoomAllocation.setOrgaMessage_(rs.getString("orgamessage"));
			returnRoomAllocation.setComment_(rs.getString("comment"));
			/* Do net set conflicting allocations here as it causes a lot of SQL queries when building lists - use setConflicts(List<RoomAllocation> roomAllocations) instead
			// Avoid loops
			if (variant.equals("conflictingChildObject")) {
				// Currently do nothing
			} else {
				returnRoomAllocation.setConflictingAllocations_();
			}
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-04) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnRoomAllocation;
	}

	/**
	 * 
	 */
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfDataObserver) {
				((IntfDataObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerRoomAllocation-12", "Fehler!");
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

}
