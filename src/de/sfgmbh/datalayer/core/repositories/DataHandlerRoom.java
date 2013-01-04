package de.sfgmbh.datalayer.core.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoom;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerRoom implements IntfDataRoom,IntfDataFilter {

	@Override
	public List<Room> getAll() {

		List<Room> listRoom = new ArrayList<Room>();
		Room returnRoom = null;
		
		String SqlStatement = "SELECT * FROM public.room";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {

				returnRoom = new Room(resultSet.getInt("roomid"),
						resultSet.getInt("buildingid"));

				returnRoom.setBeamer_(resultSet.getInt("beamer"));
				returnRoom.setChalkboards_(resultSet.getInt("chalkboards"));
				returnRoom.setLevel_(resultSet.getString("level"));
				returnRoom.setOverheads_(resultSet.getInt("overheads"));
				returnRoom.setPcseats_(resultSet.getInt("pcseats"));
				returnRoom.setRoomNumber_(resultSet.getString("roomnumber"));
				returnRoom.setSeats_(resultSet.getInt("seats"));
				returnRoom.setVisualizer_(resultSet.getInt("visualizer"));
				returnRoom.setWhiteboards_(resultSet.getInt("whiteboards"));
				
				listRoom.add(returnRoom);
				returnRoom=null;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRoom;
	}

	@Override
	public Room get(int roomId) {

		Room returnRoom = null;

		String SqlStatement = "SELECT * FROM public.room WHERE roomid='"
				+ roomId + "'";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			resultSet.next();

			returnRoom = new Room(resultSet.getInt("roomid"),
					resultSet.getInt("buildingid"));

			returnRoom.setBeamer_(resultSet.getInt("beamer"));
			returnRoom.setChalkboards_(resultSet.getInt("chalkboards"));
			returnRoom.setLevel_(resultSet.getString("level"));
			returnRoom.setOverheads_(resultSet.getInt("overheads"));
			returnRoom.setPcseats_(resultSet.getInt("pcseats"));
			returnRoom.setRoomNumber_(resultSet.getString("roomnumber"));
			returnRoom.setSeats_(resultSet.getInt("seats"));
			returnRoom.setVisualizer_(resultSet.getInt("visualizer"));
			returnRoom.setWhiteboards_(resultSet.getInt("whiteboards"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnRoom;
	}

	@Override
	public List<Room> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Room toBeDeletedRoom) {
		// TODO Auto-generated method stub
	}

	@Override
	public void save(Room toBeSavedRoom) {

		String SqlStatement = "INSERT INTO public.room "
				+ "(roomnumber, buildingid, level, seats, pcseats, beamer, visualizer, overheads, chalkboards, whiteboards) "
				+ "VALUES ('" + toBeSavedRoom.getRoomNumber_() + "','"
				+ toBeSavedRoom.getBuildingId_() + "','"
				+ toBeSavedRoom.getLevel_() + "','" + toBeSavedRoom.getSeats_()
				+ "','" + toBeSavedRoom.getPcseats_() + "','"
				+ toBeSavedRoom.getBeamer_() + "','"
				+ toBeSavedRoom.getVisualizer_() + "','"
				+ toBeSavedRoom.getOverheads_() + "','"
				+ toBeSavedRoom.getChalkboards_() + "','"
				+ toBeSavedRoom.getWhiteboards_() + "');";

		try {

			DataManagerPostgreSql.getInstance().execute(SqlStatement);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Room toBeUpdatedRoom) {

		String SqlStatement = "UPDATE public.room SET " + "roomnumber ='"
				+ toBeUpdatedRoom.getRoomNumber_() + "', " + "buildingid ='"
				+ toBeUpdatedRoom.getBuildingId_() + "', " + "level ='"
				+ toBeUpdatedRoom.getLevel_() + "', " + "seats ='"
				+ toBeUpdatedRoom.getSeats_() + "', " + "pcseats ='"
				+ toBeUpdatedRoom.getPcseats_() + "', " + "beamer ='"
				+ toBeUpdatedRoom.getBeamer_() + "', " + "visualizer ='"
				+ toBeUpdatedRoom.getVisualizer_() + "', " + "overheads ='"
				+ toBeUpdatedRoom.getOverheads_() + "', " + "chalkboards ='"
				+ toBeUpdatedRoom.getChalkboards_() + "', " + "whiteboards ='"
				+ toBeUpdatedRoom.getWhiteboards_() + "' " + "WHERE roomid='"
				+ toBeUpdatedRoom.getRoomId_() + "';";

		try {

			DataManagerPostgreSql.getInstance().execute(SqlStatement);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Room> getByFilter(String filterName, String filterValue) {
		List<Room> listRoom = new ArrayList<Room>();
		Room returnRoom = null;
		
		String SqlStatement = "SELECT * FROM public.room WHERE "+filterName+"='"+filterValue+"'";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {

				returnRoom = new Room(resultSet.getInt("roomid"),
						resultSet.getInt("buildingid"));

				returnRoom.setBeamer_(resultSet.getInt("beamer"));
				returnRoom.setChalkboards_(resultSet.getInt("chalkboards"));
				returnRoom.setLevel_(resultSet.getString("level"));
				returnRoom.setOverheads_(resultSet.getInt("overheads"));
				returnRoom.setPcseats_(resultSet.getInt("pcseats"));
				returnRoom.setRoomNumber_(resultSet.getString("roomnumber"));
				returnRoom.setSeats_(resultSet.getInt("seats"));
				returnRoom.setVisualizer_(resultSet.getInt("visualizer"));
				returnRoom.setWhiteboards_(resultSet.getInt("whiteboards"));
				
				listRoom.add(returnRoom);
				returnRoom=null;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRoom;
	}



}
