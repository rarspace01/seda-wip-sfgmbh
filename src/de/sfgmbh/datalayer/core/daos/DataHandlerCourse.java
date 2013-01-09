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

				returnCourse = new Course();

				returnCourse.setCourseId_(resultSet.getInt("courseid"));
				returnCourse.setLecturer_(
						DataModel.getInstance().dataHandlerUser.get(resultSet.getInt("lecturerid")));
				returnCourse.setCourseAcronym_(resultSet.getString("courseacronym"));
				returnCourse.setCourseName_(resultSet.getString("coursename"));
				returnCourse.setSws_(resultSet.getFloat("sws"));
				returnCourse.setCourseKind_(resultSet.getString("coursekind"));
				returnCourse.setExpectedAttendees_(resultSet.getInt("expectedattendees"));
				returnCourse.setCourseDescription_(resultSet.getString("coursedescription"));
				returnCourse.setLecturerEnabled_(resultSet.getBoolean("lecturerenabled"));
				
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

	@Override
	public List<Course> getByFilter(String filterName, String filterValue) {
		// TODO Auto-generated method stub
		return null;
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
