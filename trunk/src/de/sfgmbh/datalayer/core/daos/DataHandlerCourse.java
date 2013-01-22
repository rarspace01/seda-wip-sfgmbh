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

/**
 * DataHandler for the courses
 * 
 * @author denis
 * @author hannes
 *
 */
public class DataHandlerCourse implements IntfDataFilter, IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();

	/**
	 * Get all courses
	 * @return a list of all courses
	 */
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}

		return listCourse;
	}
	
	@Override
	public List<Course> getByFilter(HashMap<String, String> filter) {
		DataManagerPostgreSql filterDm = null;
		List<Course> listCourse = new ArrayList<Course>();
		
		try {
			if (filterDm == null) { 
				filterDm = DataManagerPostgreSql.getInstance(); 
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}
		
		return listCourse;
	}
	
	/**
	 * Get a course by its ID
	 * @param id
	 * @return a course
	 */
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
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
		ArrayList<IntfDataObserver> currentObservers = (ArrayList<IntfDataObserver>) observer_.clone();
		
		for (IntfDataObserver observer : currentObservers) {
			if (observer instanceof IntfDataObserver) {
				observer.change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(IntfDataObserver observer) {
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
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

	/**
	 * Save a course in the DB (this creates a course if its ID is -1 and otherwise updates an existing one)
	 * @param course
	 * @return true on success
	 */
	public boolean save(Course course) {
		
		if (course.getCourseId_() == -1) {
			boolean returnState = true;
				try {
					
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("INSERT INTO public.course"
							+ "(lecturerid, courseacronym, coursename, sws, coursekind, coursedescription, expectedattendees, lecturerenabled)"
							+ "VALUES (?,?,?,?,?,?,?,?)");
					dm.getPreparedStatement().setInt(1, course.getLecturer_().getUserId_());
					dm.getPreparedStatement().setString(2, course.getCourseAcronym_());
					dm.getPreparedStatement().setString(3, course.getCourseName_());
					dm.getPreparedStatement().setFloat(4, course.getSws_());
					dm.getPreparedStatement().setString(5, course.getCourseKind_());
					dm.getPreparedStatement().setString(6, course.getCourseDescription_());
					dm.getPreparedStatement().setInt(7, course.getExpectedAttendees_());
					dm.getPreparedStatement().setBoolean(8, course.isLecturerEnabled_());
					dm.executePstmt();
					this.update();
				} catch (SQLException e) {
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
					returnState =  false;
				} catch (Exception e) {
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
					returnState =  false;
				}finally{
					DataManagerPostgreSql.getInstance().dispose();
				}
				return returnState;
		} else {
			boolean returnState = true;
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				dm.prepare("UPDATE public.course SET "
						+ "lecturerid = ?, courseacronym = ?, coursename = ?, sws = ?, coursekind = ?, coursedescription = ?, expectedattendees = ?, lecturerenabled = ? "
						+ "WHERE courseid = ?");
				dm.getPreparedStatement().setInt(1, course.getLecturer_().getUserId_());
				dm.getPreparedStatement().setString(2, course.getCourseAcronym_());
				dm.getPreparedStatement().setString(3, course.getCourseName_());
				dm.getPreparedStatement().setFloat(4, course.getSws_());
				dm.getPreparedStatement().setString(5, course.getCourseKind_());
				dm.getPreparedStatement().setString(6, course.getCourseDescription_());
				dm.getPreparedStatement().setInt(7, course.getExpectedAttendees_());
				dm.getPreparedStatement().setBoolean(8, course.isLecturerEnabled_());
				dm.getPreparedStatement().setInt(9, course.getCourseId_());
				returnState = dm.executePstmt();
				this.update();
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				returnState = false;
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				returnState = false;
			}finally{
				DataManagerPostgreSql.getInstance().dispose();
			}
			return returnState;
			
		}
	}

	/**
	 * Delete a course from the DB
	 * @param course
	 * @return true on success
	 */
	public boolean delete(Course course) {
		if (course != null) {
			boolean returnState = true;
			
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				
				dm.prepare("DELETE FROM public.course "
						+ "WHERE public.course.courseid = ?");
				dm.getPreparedStatement().setInt(1, course.getCourseId_());
				returnState = dm.executePstmt();
				this.update();
			} catch (SQLException e) {
				returnState = false;
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				returnState = false;
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
			}finally{
				DataManagerPostgreSql.getInstance().dispose();
			}
			return returnState;
		}
		return false;
	}

}
