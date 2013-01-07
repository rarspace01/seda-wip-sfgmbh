package de.sfgmbh.datalayer.core.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoom;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerRoom implements IntfDataRoom, IntfDataFilter {

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
				returnRoom = null;

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
		List<Room> listRoom = new ArrayList<Room>();
		Room returnRoom = null;

		String SqlStatement = "SELECT * FROM public.room WHERE roomnumber LIKE '%"
				+ searchQry + "%'";

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
				returnRoom = null;

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
	public List<Room> getByFilter(String filterName, String filterValue) {
		List<Room> listRoom = new ArrayList<Room>();
		Room returnRoom = null;

		String SqlStatement = "SELECT * FROM public.room WHERE " + filterName
				+ "='" + filterValue + "'";

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
				returnRoom = null;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listRoom;
	}

}
