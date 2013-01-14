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

public class DataHandlerRoomAllocation implements IntfDataObservable, IntfDataFilter {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataManagerPostgreSql filterDm = null;
	private DataManagerPostgreSql conflictingAllocationDm = null;
	
	public List<RoomAllocation> getAll() {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();

		String SqlStatement = "SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
								"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
								"WHERE public.roomallocation.courseid = public.course.courseid " +
								"AND public.course.lecturerid = public.user.userid " +
								"AND public.course.lecturerid = public.lecturer.userid " +
								"AND public.chair.chairid = public.lecturer.chairid " +
								"AND public.roomallocation.roomid = public.room.roomid ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(resultSet, "normal"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listRoomAllocation;
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
						"AND public.user.login LIKE ? ");
				
			}
			if (filter.containsKey("lecturer") && filter.get("lecturer") != null && filter.get("lecturer") != "" && filter.get("lecturer") != "<alle>") {
				filterDm.pstmt.setString(1, "%" + filter.get("lecturer") + "%");
				filterDm.pstmt.setString(2, "%" + filter.get("lecturer") + "%");
			} else {
				filterDm.pstmt.setString(1, "%");
				filterDm.pstmt.setString(2, "%");
			}
			if (filter.containsKey("chair") && filter.get("chair") != null && filter.get("chair") != "" && filter.get("chair") != "<alle>") {
				filterDm.pstmt.setString(3, "%" + filter.get("chair") + "%");
				filterDm.pstmt.setString(4, "%" + filter.get("chair") + "%");
			} else {
				filterDm.pstmt.setString(3, "%");
				filterDm.pstmt.setString(4, "%");
			}
			if (filter.containsKey("course") && filter.get("course") != null && filter.get("course") != "" && filter.get("course") != "<alle>") {
				filterDm.pstmt.setString(5, "%" + filter.get("course") + "%");
				filterDm.pstmt.setString(6, "%" + filter.get("course") + "%");
			} else {
				filterDm.pstmt.setString(5, "%");
				filterDm.pstmt.setString(6, "%");
			}
			if (filter.containsKey("semester") && filter.get("semester") != null && filter.get("semester") != "" && filter.get("semester") != "<alle>") {
				filterDm.pstmt.setString(7, "%" + filter.get("semester") + "%");
			} else {
				filterDm.pstmt.setString(7, "%");
			}
			if (filter.containsKey("status") && filter.get("status") != null && filter.get("status") != "" && filter.get("status") != "<alle>") {
				filterDm.pstmt.setString(8, "%" + filter.get("status") + "%");
			} else {
				filterDm.pstmt.setString(8, "%");
			}
			if (filter.containsKey("room") && filter.get("room") != null && filter.get("room") != "" && filter.get("room") != "<alle>") {
				filterDm.pstmt.setString(9, "%" + filter.get("room") + "%");
			} else {
				filterDm.pstmt.setString(9, "%");
			}
			if (filter.containsKey("login") && filter.get("login") != null && filter.get("login") != "" && filter.get("login") != "<alle>") {
				filterDm.pstmt.setString(10, filter.get("login"));
			} else {
				filterDm.pstmt.setString(10, "%");
			}
			
			ResultSet rs = filterDm.selectPstmt();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "normal"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-17:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-18:<br />" + e.toString()), "Fehler!");
		}
		return listRoomAllocation;
	}
	
	/**
	 * Checks for conflicting room allocations
	 * @param ra
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
						"ANd public.roomallocation.roomallocationid <> ? ");
			}
			conflictingAllocationDm.pstmt.setInt(1, ra.getRoom_().getRoomId_());
			conflictingAllocationDm.pstmt.setInt(2, ra.getDay_());
			conflictingAllocationDm.pstmt.setInt(3, ra.getTime_());
			conflictingAllocationDm.pstmt.setString(4, ra.getSemester_());
			conflictingAllocationDm.pstmt.setInt(5, ra.getRoomAllocationId_());
			
			ResultSet rs = conflictingAllocationDm.selectPstmt();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "conflictingChildObject"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-16:<br />" + e.toString()), "Fehler!");
		}
		return listRoomAllocation;
	}
	
	/**
	 * Get a room allocation by its id
	 * @param id
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
														"AND roomallocationid = ?");
			DataManagerPostgreSql.getInstance().pstmt.setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeRoomAllocation(rs, "normal");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-20:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-21:<br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}
	
	/**
	* Save a room allocation
	* @param ra
	*/
	public void save(RoomAllocation ra) {
		if (ra.getRoomAllocationId_() == -1) {
			try {
				
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				
				dm.prepare("INSERT INTO public.roomallocation"
						+ "(courseid, roomid, semester, day, time, approved, orgamessage, comment)"
						+ "VALUES (?,?,?,?,?,?,?,?)");
				dm.pstmt.setInt(1, ra.getCourse_().getCourseId_());
				dm.pstmt.setInt(2, ra.getRoom_().getRoomId_());
				dm.pstmt.setString(3, ra.getSemester_());
				dm.pstmt.setInt(4, ra.getDay_());
				dm.pstmt.setInt(5, ra.getTime_());
				dm.pstmt.setString(6, ra.getApproved_());
				dm.pstmt.setString(7, ra.getOrgaMessage_());
				dm.pstmt.setString(8, ra.getComment_());
				dm.executePstmt();
				this.update();
				return;
				
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Fehler!");
			}
		} else {
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				dm.prepare("UPDATE public.roomallocation SET "
						+ "courseid = ?, roomid = ?, semester = ?, day = ?, time = ?, approved = ?, orgamessage = ?, comment = ? "
						+ "WHERE roomallocationid = ?");
				dm.pstmt.setInt(1, ra.getCourse_().getCourseId_());
				dm.pstmt.setInt(2, ra.getRoom_().getRoomId_());
				dm.pstmt.setString(3, ra.getSemester_());
				dm.pstmt.setInt(4, ra.getDay_());
				dm.pstmt.setInt(5, ra.getTime_());
				dm.pstmt.setString(6, ra.getApproved_());
				dm.pstmt.setString(7, ra.getOrgaMessage_());
				dm.pstmt.setString(8, ra.getComment_());
				dm.pstmt.setInt(9, ra.getRoomAllocationId_());
				dm.executePstmt();
				this.update();
				return;
				
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-23:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-24:<br />" + e.toString()), "Fehler!");
			}
		}
	}
	
	/**
	 * Forms a RoomAllocation object out of a given result set
	 * @param ResultSet rs
	 * @return a RoomAllocation object
	 */
	private RoomAllocation makeRoomAllocation(ResultSet rs, String variant) {
		RoomAllocation returnRoomAllocation = new RoomAllocation();
		
		try {
			returnRoomAllocation = new RoomAllocation();

			returnRoomAllocation.setRoomAllocationId_(rs.getInt("roomallocationid"));
			returnRoomAllocation.setCourse_(
					DataModel.getInstance().dataHandlerCourse.makeCourse(rs));
			returnRoomAllocation.setRoom_(
					DataModel.getInstance().dataHandlerRoom.makeRoom(rs));
			returnRoomAllocation.setSemester_(rs.getString("semester"));
			returnRoomAllocation.setDay_(rs.getInt("day"));
			returnRoomAllocation.setTime_(rs.getInt("time"));
			returnRoomAllocation.setApproved_(rs.getString("approved"));
			returnRoomAllocation.setOrgaMessage_(rs.getString("orgamessage"));
			returnRoomAllocation.setComment_(rs.getString("comment"));
			// Avoid loops
			if (variant.equals("conflictingChildObject")) {
				// Currently do nothing
			} else {
				returnRoomAllocation.setConflictingAllocations_();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExceptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-04) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
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
			DataModel.getInstance().dataExceptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerRoomAllocation-12", "Fehler!");
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
