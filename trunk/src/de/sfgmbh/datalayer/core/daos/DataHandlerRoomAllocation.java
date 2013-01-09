package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerRoomAllocation implements IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
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
			returnRoomAllocation.setApproved_(rs.getBoolean("approved"));
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
			DataModel.getInstance().dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerRoomAllocation-12", "Fehler!");
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
