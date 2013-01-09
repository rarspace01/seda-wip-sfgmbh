package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.Course;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerCourse implements IntfDataFilter, IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();

	public List<Course> getAll() {
		List<Course> listChair = new ArrayList<Course>();
		Course returnCourse = null;

		String SqlStatement = "SELECT * FROM public.course";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				returnCourse = this.makeCourse(resultSet);
				listChair.add(returnCourse);
				returnCourse = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-02) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-03) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listChair;
	}

	public Course get(int id) {
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT * FROM public.course WHERE courseid = ?");
			DataManagerPostgreSql.getInstance().pstmt.setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeCourse(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}
	
	@Override
	public List<Course> getByFilter(String filterName, String filterValue) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Forms a course object out of a given result set
	 * @param ResultSet rs
	 * @return a course object
	 */
	private Course makeCourse(ResultSet rs) {
		Course returnCourse = new Course();
		
		try {
			returnCourse.setCourseId_(rs.getInt("courseid"));
			returnCourse.setLecturer_(
					DataModel.getInstance().dataHandlerUser.get(rs.getInt("lecturerid")));
			returnCourse.setCourseAcronym_(rs.getString("courseacronym"));
			returnCourse.setCourseName_(rs.getString("coursename"));
			returnCourse.setSws_(rs.getFloat("sws"));
			returnCourse.setCourseKind_(rs.getString("coursekind"));
			returnCourse.setExpectedAttendees_(rs.getInt("expectedattendees"));
			returnCourse.setCourseDescription_(rs.getString("coursedescription"));
			returnCourse.setLecturerEnabled_(rs.getBoolean("lecturerenabled"));
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerCourse-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerCourse-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnCourse;
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
			DataModel.getInstance().dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerCourse-01", "Fehler!");
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
