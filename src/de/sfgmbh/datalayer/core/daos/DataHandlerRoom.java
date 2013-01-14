package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoom;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerRoom implements IntfDataRoom, IntfDataFilter, IntfDataObservable {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();

	@Override
	public List<Room> getAll() {

		List<Room> listRoom = new ArrayList<Room>();

		String SqlStatement = "SELECT * FROM public.room";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoom.add(this.makeRoom(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoom-06) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoom-07) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listRoom;
	}

	@Override
	public Room get(int id) {
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT * FROM public.room WHERE roomid = ?");
			DataManagerPostgreSql.getInstance().getPreparedStatement().setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeRoom(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoom-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoom-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}

	// Needs to changed to prepared statements!
	@Override
	public List<Room> search(String searchQry) {
		List<Room> listRoom = new ArrayList<Room>();
		
		String SqlStatement = "SELECT * FROM public.room WHERE roomnumber LIKE '%"
				+ searchQry + "%'";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoom.add(this.makeRoom(resultSet));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRoom;

	}

	@Override
	public List<Room> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Room room) {

		String SqlStatement = "DELETE FROM public.room " + "WHERE roomid = '"
				+ room.getRoomId_() + "';";

		try {

			DataManagerPostgreSql.getInstance().execute(SqlStatement);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void save(Room room) {

		String SqlStatement = "INSERT INTO public.room "
				+ "(roomnumber, buildingid, level, seats, pcseats, beamer, visualizer, overheads, chalkboards, whiteboards) "
				+ "VALUES ('" + room.getRoomNumber_() + "','"
				+ room.getBuildingId_() + "','"
				+ room.getLevel_() + "','" + room.getSeats_()
				+ "','" + room.getPcseats_() + "','"
				+ room.getBeamer_() + "','"
				+ room.getVisualizer_() + "','"
				+ room.getOverheads_() + "','"
				+ room.getChalkboards_() + "','"
				+ room.getWhiteboards_() + "');";

		try {

			DataManagerPostgreSql.getInstance().execute(SqlStatement);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String sqlStatement = "UPDATE public.room SET " + "roomnumber ='"
				+ room.getRoomNumber_() + "', " + "buildingid ='"
				+ room.getBuildingId_() + "', " + "level ='"
				+ room.getLevel_() + "', " + "seats ='"
				+ room.getSeats_() + "', " + "pcseats ='"
				+ room.getPcseats_() + "', " + "beamer ='"
				+ room.getBeamer_() + "', " + "visualizer ='"
				+ room.getVisualizer_() + "', " + "overheads ='"
				+ room.getOverheads_() + "', " + "chalkboards ='"
				+ room.getChalkboards_() + "', " + "whiteboards ='"
				+ room.getWhiteboards_() + "' " + "WHERE roomid='"
				+ room.getRoomId_() + "';";

		try {

			DataManagerPostgreSql.getInstance().execute(sqlStatement);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public List<Room> getByFilter(HashMap<String, String> filter) {
		List<Room> listRoom = new ArrayList<Room>();
		
		String SqlStatement = "SELECT * FROM public.room"; // placeholder until the statement below uses HashMap
		
		/**
		String SqlStatement = "SELECT * FROM public.room WHERE " + filter
				+ "='" + filterValue + "'";
			*/ // this needs to be adjusted to use the HashMap filter

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listRoom.add(this.makeRoom(resultSet));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRoom;
	}

	/**
	 * Forms a Room object out of a given result set
	 * @param ResultSet rs
	 * @return a Room object
	 */
	public Room makeRoom(ResultSet rs) {
		Room returnRoom = new Room();
		
		try {
			returnRoom.setRoomId_(rs.getInt("roomid"));
			returnRoom.setBuildingId_(rs.getInt("buildingid"));
			returnRoom.setBeamer_(rs.getInt("beamer"));
			returnRoom.setChalkboards_(rs.getInt("chalkboards"));
			returnRoom.setLevel_(rs.getString("level"));
			returnRoom.setOverheads_(rs.getInt("overheads"));
			returnRoom.setPcseats_(rs.getInt("pcseats"));
			returnRoom.setRoomNumber_(rs.getString("roomnumber"));
			returnRoom.setSeats_(rs.getInt("seats"));
			returnRoom.setVisualizer_(rs.getInt("visualizer"));
			returnRoom.setWhiteboards_(rs.getInt("whiteboards"));
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerRoom-02) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerRoom-03) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnRoom;
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
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerRoom-01", "Fehler!");
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
