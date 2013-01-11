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
	
	public List<RoomAllocation> getAll() {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();

		String SqlStatement = "SELECT * FROM public.roomallocation";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listRoomAllocation;
	}
	
	@Override
	public List<RoomAllocation> getByFilter(HashMap<String, String> filter) {
		List<RoomAllocation> listRoomAllocation = new ArrayList<RoomAllocation>();
		
		try {
			if (filterDm == null) { 
				filterDm = new DataManagerPostgreSql(); 
				filterDm.prepare(
						"SELECT public.roomallocation.* " +
						"FROM public.roomallocation, public.room, public.course, public.user, public.chair, public.lecturer " +
						"WHERE public.roomallocation.courseid = public.course.courseid " +
						"AND public.course.lecturerid = public.user.userid " +
						"AND public.course.lecturerid = public.lecturer.userid " +
						"AND public.chair.chairid = public.lecturer.chairid " +
						"AND public.roomallocation.roomid = public.room.roomid " +
						"AND (public.user.lname LIKE ? OR public.user.fname LIKE ? ) " +
						"AND (public.chair.chairname LIKE ? OR public.chair.chairacronym LIKE ? ) " +
						"AND (public.course.courseacronym LIKE ? OR public.course.coursename LIKE ? ) " +
						"AND public.roomallocation.semester LIKE ? ");
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
			
			ResultSet rs = filterDm.selectPstmt();
			while (rs.next()) {
				listRoomAllocation.add(this.makeRoomAllocation(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-16:<br />" + e.toString()), "Fehler!");
		}
		return listRoomAllocation;
	}
	
	/**
	 * Forms a RoomAllocation object out of a given result set
	 * @param ResultSet rs
	 * @return a RoomAllocation object
	 */
	private RoomAllocation makeRoomAllocation(ResultSet rs) {
		RoomAllocation returnRoomAllocation = new RoomAllocation();
		
		try {
			returnRoomAllocation = new RoomAllocation();

			returnRoomAllocation.setRoomAllocationId_(rs.getInt("roomallocationid"));
			returnRoomAllocation.setCourse_(
					DataModel.getInstance().dataHandlerCourse.get(rs.getInt("courseid")));
			returnRoomAllocation.setRoom_(
					DataModel.getInstance().dataHandlerRoom.get(rs.getInt("roomid")));
			returnRoomAllocation.setSemester_(rs.getString("semester"));
			returnRoomAllocation.setDay_(rs.getInt("day"));
			returnRoomAllocation.setTime_(rs.getInt("time"));
			returnRoomAllocation.setApproved_(rs.getString("approved"));
			returnRoomAllocation.setOrgaMessage_(rs.getString("orgamessage"));
			returnRoomAllocation.setComment_(rs.getString("comment"));
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerRoomAllocation-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoomAllocation-04) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
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
			DataModel.getInstance().dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerRoomAllocation-12", "Fehler!");
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
