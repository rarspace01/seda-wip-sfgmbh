package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerCourse implements IntfDataFilter, IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataManagerPostgreSql filterDm = null;

	public List<Course> getAll() {
		List<Course> listCourse = new ArrayList<Course>();
		Course returnCourse = null;

		String SqlStatement = "SELECT public.course.*, public.user.*, public.chair.* " +
								"FROM public.course, public.user, public.chair, public.lecturer " +
								"WHERE public.course.lecturerid = public.user.userid " +
								"AND public.course.lecturerid = public.lecturer.userid " +
								"AND public.chair.chairid = public.lecturer.chairid ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				returnCourse = this.makeCourse(resultSet);
				listCourse.add(returnCourse);
				returnCourse = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-02) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-03) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listCourse;
	}
	
	@Override
	public List<Course> getByFilter(HashMap<String, String> filter) {
		List<Course> listCourse = new ArrayList<Course>();
		
		try {
			if (filterDm == null) { 
				filterDm = new DataManagerPostgreSql(); 
					filterDm.prepare("SELECT public.course.*, public.user.*, public.chair.* " +
									"FROM public.course, public.user, public.chair, public.lecturer " +
									"WHERE public.course.lecturerid = public.user.userid " +
									"AND public.course.lecturerid = public.lecturer.userid " +
									"AND public.chair.chairid = public.lecturer.chairid " +
									"AND public.user.login LIKE ? " +
									"AND (public.chair.chairacronym LIKE ? OR public.chair.chairname LIKE ?) " +
									"AND (Public.user.fname LIKE ? OR public.user.lname LIKE ?) ");
			}
			if (filter.containsKey("login") && filter.get("login") != null && filter.get("login") != "" && filter.get("login") != "<alle>") {
				filterDm.getPreparedStatement().setString(1, filter.get("login"));
			} else {
				filterDm.getPreparedStatement().setString(1, "%");
			}
			if (filter.containsKey("chair") && filter.get("chair") != null && filter.get("chair") != "" && filter.get("chair") != "<alle>") {
				filterDm.getPreparedStatement().setString(2, "%" + filter.get("chair") + "%");
				filterDm.getPreparedStatement().setString(3, "%" + filter.get("chair") + "%");
			} else {
				filterDm.getPreparedStatement().setString(2, "%");
				filterDm.getPreparedStatement().setString(3, "%");
			}
			if (filter.containsKey("lecturer") && filter.get("lecturer") != null && filter.get("lecturer") != "" && filter.get("lecturer") != "<alle>") {
				filterDm.getPreparedStatement().setString(4, "%" + filter.get("lecturer") + "%");
				filterDm.getPreparedStatement().setString(5, "%" + filter.get("lecturer") + "%");
			} else {
				filterDm.getPreparedStatement().setString(4, "%");
				filterDm.getPreparedStatement().setString(5, "%");
			}
			
			ResultSet rs = filterDm.selectPstmt();
			while (rs.next()) {
				listCourse.add(makeCourse(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-09) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-10) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return listCourse;
	}
	
	public Course get(int id) {
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT public.course.*, public.user.*, public.chair.* " +
														"FROM public.course, public.user, public.chair, public.lecturer " +
														"WHERE public.course.lecturerid = public.user.userid " +
														"AND public.course.lecturerid = public.lecturer.userid " +
														"AND public.chair.chairid = public.lecturer.chairid " +
														"AND courseid = ? ");
			DataManagerPostgreSql.getInstance().getPreparedStatement().setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeCourse(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}
	
	/**
	 * Forms a course object out of a given result set
	 * @param ResultSet rs
	 * @return a course object
	 */
	public Course makeCourse(ResultSet rs) {
		Course returnCourse = new Course();
		
		try {
			returnCourse.setCourseId_(rs.getInt("courseid"));
			returnCourse.setLecturer_(
					DataModel.getInstance().getDataHandlerUser().makeUser(rs));
			returnCourse.setCourseAcronym_(rs.getString("courseacronym"));
			returnCourse.setCourseName_(rs.getString("coursename"));
			returnCourse.setSws_(rs.getFloat("sws"));
			returnCourse.setCourseKind_(rs.getString("coursekind"));
			returnCourse.setExpectedAttendees_(rs.getInt("expectedattendees"));
			returnCourse.setCourseDescription_(rs.getString("coursedescription"));
			returnCourse.setLecturerEnabled_(rs.getBoolean("lecturerenabled"));
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnCourse;
	}

	/**
	 * 
	 */
	@Override
	public void update() {
		
		// Create a private observer list to avoid ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<Object> currentObservers = (ArrayList<Object>) observer_.clone();
		
		for (Object o : currentObservers) {
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
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerCourse-01", "Fehler!");
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
