package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfCourse;
import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.datalayer.core.definitions.IntfDataCourse;
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
public class DataHandlerCourse implements IntfDataFilter, IntfDataObservable, IntfDataCourse {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#getAll()
	 */
	@Override
	public List<Course> getAll() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		
		List<Course> listCourse = new ArrayList<Course>();
		Course returnCourse = null;

		String SqlStatement = "SELECT public.course.*, public.user.*, public.chair.* " +
								"FROM public.course, public.user, public.chair, public.lecturer " +
								"WHERE public.course.lecturerid = public.user.userid " +
								"AND public.course.lecturerid = public.lecturer.userid " +
								"AND public.chair.chairid = public.lecturer.chairid ";

		try {

			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				returnCourse = this.makeCourse(resultSet);
				listCourse.add(returnCourse);
				returnCourse = null;
			}

		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-02) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-03) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		dataManager.dispose();
		return listCourse;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<Course> getByFilter(HashMap<String, String> filter) {
		DataManagerPostgreSql filterDm = null;
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
			
			ResultSet rs = filterDm.selectPreparedStatement();
			while (rs.next()) {
				listCourse.add(makeCourse(rs));
			}
			
			filterDm.dispose();
		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-09) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-10) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			filterDm.dispose();
		}
		
		return listCourse;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#get(int)
	 */
	@Override
	public IntfCourse get(int id) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		
		try {
			dataManager.prepare("SELECT public.course.*, public.user.*, public.chair.* " +
														"FROM public.course, public.user, public.chair, public.lecturer " +
														"WHERE public.course.lecturerid = public.user.userid " +
														"AND public.course.lecturerid = public.lecturer.userid " +
														"AND public.chair.chairid = public.lecturer.chairid " +
														"AND courseid = ? ");
			dataManager.getPreparedStatement().setInt(1, id);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeCourse(rs);
			}
			
		} catch (SQLException e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#makeCourse(java.sql.ResultSet)
	 */
	@Override
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
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnCourse;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#update()
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
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#register(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void register(IntfDataObserver observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerCourse-01", "Fehler!");
		}
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#unregister(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#save(de.sfgmbh.applayer.core.model.Course)
	 */
	@Override
	public boolean save(IntfCourse course) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		
		if (course.getCourseId_() == -1) {
			boolean returnState = true;
				try {
					
					dataManager.prepare("INSERT INTO public.course"
							+ "(lecturerid, courseacronym, coursename, sws, coursekind, coursedescription, expectedattendees, lecturerenabled)"
							+ "VALUES (?,?,?,?,?,?,?,?)");
					dataManager.getPreparedStatement().setInt(1, course.getLecturer_().getUserId_());
					dataManager.getPreparedStatement().setString(2, course.getCourseAcronym_());
					dataManager.getPreparedStatement().setString(3, course.getCourseName_());
					dataManager.getPreparedStatement().setFloat(4, course.getSws_());
					dataManager.getPreparedStatement().setString(5, course.getCourseKind_());
					dataManager.getPreparedStatement().setString(6, course.getCourseDescription_());
					dataManager.getPreparedStatement().setInt(7, course.getExpectedAttendees_());
					dataManager.getPreparedStatement().setBoolean(8, course.isLecturerEnabled_());
					dataManager.executePreparedStatement();
					this.update();
				} catch (SQLException e) {
					
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
					returnState =  false;
				} catch (Exception e) {
					
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
					returnState =  false;
				}finally{
					dataManager.dispose();
				}
				return returnState;
		} else {
			boolean returnState = true;
			try {

				dataManager.prepare("UPDATE public.course SET "
						+ "lecturerid = ?, courseacronym = ?, coursename = ?, sws = ?, coursekind = ?, coursedescription = ?, expectedattendees = ?, lecturerenabled = ? "
						+ "WHERE courseid = ?");
				dataManager.getPreparedStatement().setInt(1, course.getLecturer_().getUserId_());
				dataManager.getPreparedStatement().setString(2, course.getCourseAcronym_());
				dataManager.getPreparedStatement().setString(3, course.getCourseName_());
				dataManager.getPreparedStatement().setFloat(4, course.getSws_());
				dataManager.getPreparedStatement().setString(5, course.getCourseKind_());
				dataManager.getPreparedStatement().setString(6, course.getCourseDescription_());
				dataManager.getPreparedStatement().setInt(7, course.getExpectedAttendees_());
				dataManager.getPreparedStatement().setBoolean(8, course.isLecturerEnabled_());
				dataManager.getPreparedStatement().setInt(9, course.getCourseId_());
				returnState = dataManager.executePreparedStatement();
				this.update();
			} catch (SQLException e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				returnState = false;
			} catch (Exception e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				returnState = false;
			}finally{
				dataManager.dispose();
			}
			
			dataManager.dispose();
			return returnState;
			
		}
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.daos.IntfDataCourse#delete(de.sfgmbh.applayer.core.model.Course)
	 */
	@Override
	public boolean delete(IntfCourse course) {
		
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		if (course != null) {
			boolean returnState = true;
			
			try {
				
				dataManager.prepare("DELETE FROM public.course "
						+ "WHERE public.course.courseid = ?");
				dataManager.getPreparedStatement().setInt(1, course.getCourseId_());
				returnState = dataManager.executePreparedStatement();
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
		
		dataManager.dispose();
		return false;
	}

}
