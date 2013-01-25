package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoomAllocation;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Data handler for room allocations in the data base
 * 
 * @author hannes
 * @author denis
 *
 */
public class DataHandlerRoomAllocation implements IntfDataObservable, IntfDataFilter, IntfDataRoomAllocation {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#getAll()
	 */
	@Override
	public List<IntfRoomAllocation> getAll() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<IntfRoomAllocation> listRoomAllocation = new ArrayList<IntfRoomAllocation>();

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

			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(resultSet, "normal"));
			}

		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return this.setConflicts(listRoomAllocation);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#getAllOpen()
	 */
	@Override
	public List<IntfRoomAllocation> getAllOpen() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<IntfRoomAllocation> listRoomAllocation = new ArrayList<IntfRoomAllocation>();

		String SqlStatement = "SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
								"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
								"WHERE public.roomallocation.courseid = public.course.courseid " +
								"AND public.course.lecturerid = public.user.userid " +
								"AND public.course.lecturerid = public.lecturer.userid " +
								"AND public.chair.chairid = public.lecturer.chairid " +
								"AND public.roomallocation.roomid = public.room.roomid " +
								"AND public.roomallocation.approved NOT LIKE 'denied' " +
								"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC"
								;

		try {

			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(resultSet, "normal"));
			}

		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return this.setConflicts(listRoomAllocation);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<IntfRoomAllocation> getByFilter(HashMap<String, String> filter) {
		DataManagerPostgreSql filterDm = null;
		// DataManagerPostgreSql conflictingAllocationDm = null;
		List<IntfRoomAllocation> listRoomAllocation = new ArrayList<IntfRoomAllocation>();
		
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
		if (filter.containsKey("status") && filter.get("status").equals("Gegenvorschlag")) {
			filter.put("status", "counter");
		}
		
		
		try {
			if (filterDm == null) { 
				filterDm = new DataManagerPostgreSql();
				if(filter.containsKey("courseenabled")){
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
									"AND public.chair.chairid BETWEEN ? AND ? " +
									"AND public.roomallocation.day BETWEEN ? AND ? " +
									"AND public.course.lecturerenabled = ? " +
							"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC");
				}else{
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
									"AND public.chair.chairid BETWEEN ? AND ? " +
									"AND public.roomallocation.day BETWEEN ? AND ? " +
							"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC");
				}
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
			if (filter.containsKey("chairid") && filter.get("chairid") != null && filter.get("chairid") != "" && filter.get("chairid") != "<alle>") {
				filterDm.getPreparedStatement().setInt(13, Integer.parseInt(filter.get("chairid")));
				filterDm.getPreparedStatement().setInt(14, Integer.parseInt(filter.get("chairid")));
			} else {
				filterDm.getPreparedStatement().setInt(13, 0);
				filterDm.getPreparedStatement().setInt(14, 2147483647);
			}
			if (filter.containsKey("day") && filter.get("day") != null && filter.get("day") != "" && filter.get("day") != "<alle>") {
				filterDm.getPreparedStatement().setInt(15, Integer.parseInt(filter.get("day")));
				filterDm.getPreparedStatement().setInt(16, Integer.parseInt(filter.get("day")));
			} else {
				filterDm.getPreparedStatement().setInt(15, 0);
				filterDm.getPreparedStatement().setInt(16, 2147483647);
			}
			if (filter.containsKey("courseenabled") && filter.get("courseenabled") != null && filter.get("courseenabled") != "" && filter.get("courseenabled") != "<alle>") {
				filterDm.getPreparedStatement().setBoolean(17, true);
			}
			
			ResultSet rs = filterDm.selectPreparedStatement();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "normal"));
			}
			
		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-17:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-18:<br />" + e.toString()), "Fehler!");
		}finally{
			filterDm.dispose();
		}
		
		return this.setConflicts(listRoomAllocation);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#getConflictingAllocation(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public List<IntfRoomAllocation> getConflictingAllocation(IntfRoomAllocation ra) {
		// DataManagerPostgreSql filterDm = null;
		DataManagerPostgreSql conflictingAllocationDm = null;
		List<IntfRoomAllocation> listRoomAllocation = new ArrayList<IntfRoomAllocation>();
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
			
			ResultSet rs = conflictingAllocationDm.selectPreparedStatement();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs, "conflictingChildObject"));
			}
		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-16:<br />" + e.toString()), "Fehler!");
		}finally{
			conflictingAllocationDm.dispose();
		}
		return this.setConflicts(listRoomAllocation);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#getConflictingDates()
	 */
	@Override
	public List<HashMap<String, Object>> getConflictingDates() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<HashMap<String, Object>> conflictingDates = new ArrayList<HashMap<String, Object>>();

		String SqlStatement = "SELECT COUNT(*), public.roomallocation.roomid, public.roomallocation.time, public.roomallocation.day, public.roomallocation.semester " +
								"FROM public.roomallocation " +
								"WHERE public.roomallocation.approved NOT LIKE 'denied' " +
								"GROUP BY public.roomallocation.roomid, public.roomallocation.time, public.roomallocation.day, public.roomallocation.semester " +
								"HAVING COUNT(*) > 1 ";

		try {

			ResultSet resultSet = dataManager.select(
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
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return conflictingDates;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#setConflicts(java.util.List)
	 */
	@Override
	public List<IntfRoomAllocation> setConflicts(List<IntfRoomAllocation> listRoomAllocation) {
		List<HashMap<String, Object>> conflictingDates = this.getConflictingDates();
		List<IntfRoomAllocation> setAllocations = new ArrayList<IntfRoomAllocation>();
		
		// Iterate over all room allocations and check for each allocation all conflicting dates
		for (IntfRoomAllocation roomAllocation : listRoomAllocation) {
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#get(int)
	 */
	@Override
	public IntfRoomAllocation get(int id) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare("SELECT public.roomallocation.*, public.course.*, public.user.*, public.room.*, public.chair.* " +
														"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
														"WHERE public.roomallocation.courseid = public.course.courseid " +
														"AND public.course.lecturerid = public.user.userid " +
														"AND public.course.lecturerid = public.lecturer.userid " +
														"AND public.chair.chairid = public.lecturer.chairid " +
														"AND public.roomallocation.roomid = public.room.roomid " +
														"AND roomallocationid = ? " +
														"ORDER BY public.roomallocation.day ASC, public.roomallocation.time ASC");
			dataManager.getPreparedStatement().setInt(1, id);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				IntfRoomAllocation roomAllocation = this.makeRoomAllocation(rs, "normal");
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
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-20:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-21:<br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#save(de.sfgmbh.applayer.core.model.RoomAllocation)
	 */
	@Override
	public boolean save(IntfRoomAllocation ra) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		boolean returnStatus = false;
		if (ra.getRoomAllocationId_() == -1) {
			try {
				
				dataManager.prepare("INSERT INTO public.roomallocation"
						+ "(courseid, roomid, semester, day, time, approved, orgamessage, comment)"
						+ "VALUES (?,?,?,?,?,?,?,?)");
				dataManager.getPreparedStatement().setInt(1, ra.getCourse_().getCourseId_());
				dataManager.getPreparedStatement().setInt(2, ra.getRoom_().getRoomId_());
				dataManager.getPreparedStatement().setString(3, ra.getSemester_());
				dataManager.getPreparedStatement().setInt(4, ra.getDay_());
				dataManager.getPreparedStatement().setInt(5, ra.getTime_());
				dataManager.getPreparedStatement().setString(6, ra.getApproved_());
				dataManager.getPreparedStatement().setString(7, ra.getOrgaMessage_());
				dataManager.getPreparedStatement().setString(8, ra.getComment_());
				returnStatus = dataManager.executePreparedStatement();
				this.update();
			} catch (SQLException e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-22:<br />" + e.toString()), "Fehler!");
			}finally{
				dataManager.dispose();
			}
		} else {
			try {

				dataManager.prepare("UPDATE public.roomallocation SET "
						+ "courseid = ?, roomid = ?, semester = ?, day = ?, time = ?, approved = ?, orgamessage = ?, comment = ? "
						+ "WHERE roomallocationid = ?");
				dataManager.getPreparedStatement().setInt(1, ra.getCourse_().getCourseId_());
				dataManager.getPreparedStatement().setInt(2, ra.getRoom_().getRoomId_());
				dataManager.getPreparedStatement().setString(3, ra.getSemester_());
				dataManager.getPreparedStatement().setInt(4, ra.getDay_());
				dataManager.getPreparedStatement().setInt(5, ra.getTime_());
				dataManager.getPreparedStatement().setString(6, ra.getApproved_());
				dataManager.getPreparedStatement().setString(7, ra.getOrgaMessage_());
				dataManager.getPreparedStatement().setString(8, ra.getComment_());
				dataManager.getPreparedStatement().setInt(9, ra.getRoomAllocationId_());
				returnStatus = dataManager.executePreparedStatement();
				this.update();
			} catch (SQLException e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-23:<br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-24:<br />" + e.toString()), "Fehler!");
			}finally{
				dataManager.dispose();
			}
		}
		return returnStatus;
	}
	
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
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-04) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnRoomAllocation;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#update()
	 */
	@Override
	public void update() {
		
		// Create a private observer list to avoid ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfDataObserver> currentObservers = (ArrayList<IntfDataObserver>) observer_.clone();
		
		for (IntfDataObserver observer : currentObservers) {
			if (observer instanceof IntfDataObserver) {
				observer.change();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#register(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void register(IntfDataObserver observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerRoomAllocation-12", "Fehler!");
		}
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#unregister(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataRoomAllocation#clean()
	 */
	@Override
	public boolean clean() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		boolean returnState = false;
		try {
			dataManager.prepare("DELETE FROM  public.roomallocation " +
						"WHERE public.roomallocation.approved LIKE 'denied' ");
			dataManager.executePreparedStatement();
			returnState = true;
			this.update();
		} catch (SQLException e) {
			returnState = false;
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			returnState = false;
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}
		return returnState;
	}


}
