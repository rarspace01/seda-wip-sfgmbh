package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoom;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Class for retrieving the Rooms from the database and saving them to the
 * database.
 * 
 * @author denis
 * 
 */
public class DataHandlerRoom implements IntfDataRoom, IntfDataFilter,
		IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();

	/**
	 * retrieves all rooms from the database
	 */
	@Override
	public List<IntfRoom> getAll() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<IntfRoom> listRooms = new ArrayList<IntfRoom>();

		String sqlStatement = "SELECT * FROM public.room";

		try {

			ResultSet resultSet = dataManager.select(sqlStatement);
			// iterate trough all objects, add them to the listRooms
			while (resultSet.next()) {
				listRooms.add(this.makeRoom(resultSet));
			}

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerRoom-06) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerRoom-07) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		} finally {
			// close the connection when we retrieved the data
			dataManager.dispose();
		}

		return listRooms;
	}

	/**
	 * return a {@link Room} Object from the given roomId
	 */
	@Override
	public IntfRoom get(int roomId) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		IntfRoom returnRoom = null;

		try {
			dataManager.prepare("SELECT * FROM public.room WHERE roomid = ?");
			dataManager.getPreparedStatement().setInt(1, roomId);
			ResultSet resultSet = dataManager.selectPreparedStatement();
			// take the first result and save it to the room object
			while (resultSet.next()) {
				returnRoom = this.makeRoom(resultSet);
			}
			// exception handling
		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerRoom-04) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerRoom-05) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		} finally {
			// close the connection when we retrieved the data
			dataManager.dispose();
		}

		return returnRoom;
	}

	/**
	 * deletes a {@link Room} Object in the database
	 */
	@Override
	public void delete(IntfRoom room) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		String SqlStatement = "DELETE FROM public.room " + "WHERE roomid = '"
				+ room.getRoomId_() + "';";

		try {

			dataManager.execute(SqlStatement);

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerRoom-06) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} finally {
			// close the connection when we retrieved the data
			dataManager.dispose();
		}

	}

	/**
	 * saves an {@link Room} object in the database
	 */
	@Override
	public void save(IntfRoom room) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();

		/*
		 * check if room has already an valid id incase of <0 its a new Room and
		 * we use an INSERT otherwise we use an UPDATE
		 */
		if (room.getRoomId_() < 0) {

			try {
				// build the prepared statement
				dataManager
						.prepare("INSERT INTO public.room "
								+ "(roomnumber, buildingid, level, seats, pcseats, beamer, visualizer, overheads, chalkboards, whiteboards) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?);");
				dataManager.getPreparedStatement().setString(1,
						room.getRoomNumber_());
				dataManager.getPreparedStatement().setInt(2,
						room.getBuildingId_());
				dataManager.getPreparedStatement().setString(3,
						room.getLevel_());
				dataManager.getPreparedStatement().setInt(4, room.getSeats_());
				dataManager.getPreparedStatement()
						.setInt(5, room.getPcseats_());
				dataManager.getPreparedStatement().setInt(6, room.getBeamer_());
				dataManager.getPreparedStatement().setInt(7,
						room.getVisualizer_());
				dataManager.getPreparedStatement().setInt(8,
						room.getOverheads_());
				dataManager.getPreparedStatement().setInt(9,
						room.getChalkboards_());
				dataManager.getPreparedStatement().setInt(10,
						room.getWhiteboards_());
				// execute the prepared statement
				dataManager.executePreparedStatement();

			} catch (SQLException e) {

				DataModel
						.getInstance()
						.getExceptionsHandler()
						.setNewException(
								("Es ist ein SQL-Fehler (DataHandlerRoom-07a) aufgetreten:<br /><br />" + e
										.toString()), "Datenbank-Fehler!");
			} finally {
				dataManager.dispose();
			}
		} else {

			try {

				// build the prepared statement
				dataManager
						.prepare("UPDATE public.room SET roomnumber = ?,"
								+ " buildingid = ?, level = ?, seats = ?, pcseats = ?,"
								+ " beamer = ?, visualizer = ?, overheads = ?, chalkboards = ?,"
								+ " whiteboards = ? WHERE roomid= ?;");
				dataManager.getPreparedStatement().setString(1,
						room.getRoomNumber_());
				dataManager.getPreparedStatement().setInt(2,
						room.getBuildingId_());
				dataManager.getPreparedStatement().setString(3,
						room.getLevel_());
				dataManager.getPreparedStatement().setInt(4, room.getSeats_());
				dataManager.getPreparedStatement()
						.setInt(5, room.getPcseats_());
				dataManager.getPreparedStatement().setInt(6, room.getBeamer_());
				dataManager.getPreparedStatement().setInt(7,
						room.getVisualizer_());
				dataManager.getPreparedStatement().setInt(8,
						room.getOverheads_());
				dataManager.getPreparedStatement().setInt(9,
						room.getChalkboards_());
				dataManager.getPreparedStatement().setInt(10,
						room.getWhiteboards_());
				dataManager.getPreparedStatement()
						.setInt(11, room.getRoomId_());
				// execute the statement
				dataManager.executePreparedStatement();

			} catch (SQLException e) {

				DataModel
						.getInstance()
						.getExceptionsHandler()
						.setNewException(
								("Es ist ein SQL-Fehler (DataHandlerRoom-07b) aufgetreten:<br /><br />" + e
										.toString()), "Datenbank-Fehler!");
			} finally {
				dataManager.dispose();
			}
		}

	}

	/**
	 * retrieves an {@link List} of {@link Room} objects based on a given
	 * {@link HashMap} filter
	 */
	@Override
	public List<IntfRoom> getByFilter(HashMap<String, String> filter) {
		DataManagerPostgreSql filterDataManager = null;
		List<IntfRoom> listRoom = new ArrayList<IntfRoom>();

		try {
			if (filterDataManager == null) {
				filterDataManager = new DataManagerPostgreSql();
				filterDataManager.prepare("SELECT public.room.* "
						+ "FROM public.room "
						+ "WHERE public.room.roomnumber LIKE ? "
						+ "AND public.room.seats >= ? "
						+ "AND public.room.level LIKE ? "
						+ "AND public.room.pcseats >= ? "
						+ "AND public.room.beamer >= ? "
						+ "AND public.room.visualizer >= ? "
						+ "AND public.room.overheads >= ? "
						+ "AND public.room.chalkboards >= ? "
						+ "AND public.room.whiteboards >= ? ");

			}
			/*
			 * retireving the filters from the hashmap and settig the values fro
			 * the preparedstatement we check if <alle> ist set and replace it
			 * to specifiy a according wildcard
			 */
			if (filter.containsKey("room") && filter.get("room") != null
					&& filter.get("room") != ""
					&& filter.get("room") != "<alle>") {
				filterDataManager.getPreparedStatement().setString(1,
						"%" + filter.get("room") + "%");
			} else {
				filterDataManager.getPreparedStatement().setString(1, "%");
			}
			if (filter.containsKey("seats") && filter.get("seats") != null
					&& filter.get("seats") != ""
					&& filter.get("seats") != "<alle>") {
				filterDataManager.getPreparedStatement()
						.setInt(2,
								Integer.parseInt(filter.get("seats").replace(
										">= ", "")));
			} else {
				filterDataManager.getPreparedStatement().setInt(2, 0);
			}
			if (filter.containsKey("level") && filter.get("level") != null
					&& filter.get("level") != ""
					&& filter.get("level") != "<alle>") {
				filterDataManager.getPreparedStatement().setString(3,
						"%" + filter.get("level") + "%");
			} else {
				filterDataManager.getPreparedStatement().setString(3, "%");
			}
			if (filter.containsKey("pcseats") && filter.get("pcseats") != null
					&& filter.get("pcseats") != ""
					&& filter.get("pcseats") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						4,
						Integer.parseInt(filter.get("pcseats").replace(">= ",
								"")));
			} else {
				filterDataManager.getPreparedStatement().setInt(4, 0);
			}
			if (filter.containsKey("beamer") && filter.get("beamer") != null
					&& filter.get("beamer") != ""
					&& filter.get("beamer") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						5,
						Integer.parseInt(filter.get("beamer")
								.replace(">= ", "")));
			} else {
				filterDataManager.getPreparedStatement().setInt(5, 0);
			}
			if (filter.containsKey("visualizer")
					&& filter.get("visualizer") != null
					&& filter.get("visualizer") != ""
					&& filter.get("visualizer") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						6,
						Integer.parseInt(filter.get("visualizer").replace(
								">= ", "")));
			} else {
				filterDataManager.getPreparedStatement().setInt(6, 0);
			}
			if (filter.containsKey("overheads")
					&& filter.get("overheads") != null
					&& filter.get("overheads") != ""
					&& filter.get("overheads") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						7,
						Integer.parseInt(filter.get("overheads").replace(">= ",
								"")));
			} else {
				filterDataManager.getPreparedStatement().setInt(7, 0);
			}
			if (filter.containsKey("chalkboards")
					&& filter.get("chalkboards") != null
					&& filter.get("chalkboards") != ""
					&& filter.get("chalkboards") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						8,
						Integer.parseInt(filter.get("chalkboards").replace(
								">= ", "")));
			} else {
				filterDataManager.getPreparedStatement().setInt(8, 0);
			}
			if (filter.containsKey("whiteboards")
					&& filter.get("whiteboards") != null
					&& filter.get("whiteboards") != ""
					&& filter.get("whiteboards") != "<alle>") {
				filterDataManager.getPreparedStatement().setInt(
						9,
						Integer.parseInt(filter.get("whiteboards").replace(
								">= ", "")));
			} else {
				filterDataManager.getPreparedStatement().setInt(9, 0);
			}

			ResultSet resultSet = filterDataManager.selectPreparedStatement();
			while (resultSet.next()) {
				listRoom.add(this.makeRoom(resultSet));
			}

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoom-17:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoom-18:<br />" + e
									.toString()), "Fehler!");
		} finally {
			filterDataManager.dispose();
		}

		return listRoom;
	}

	/**
	 * Forms a {@link Room} object out of a given {@link ResultSet}
	 * 
	 * @param resultSet
	 *            - {@link ResultSet}
	 * 
	 * @return a {@link Room} object
	 */
	public Room makeRoom(ResultSet resultSet) {
		Room returnRoom = new Room();

		try {
			// setting the values on the temporary Room object
			returnRoom.setRoomId_(resultSet.getInt("roomid"));
			returnRoom.setBuildingId_(resultSet.getInt("buildingid"));
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

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerRoom-02) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerRoom-03) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return returnRoom;
	}

	/**
	 * notifys all {@link IntfDataObserver} Observers on a data change
	 */
	@Override
	public void update() {
		// Create a private observer list to avoid
		// ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfDataObserver> currentObservers = (ArrayList<IntfDataObserver>) observer_
				.clone();
		// call the change() method on all Observers
		for (IntfDataObserver observer : currentObservers) {
			if (observer instanceof IntfDataObserver) {
				observer.change();
			}
		}
	}

	/**
	 * registers an {@link IntfDataObserver} Observer at the
	 * {@link DataHandlerRoom}
	 * 
	 * @param observer
	 */
	@Override
	public void register(IntfDataObserver observer) {
		// checking for implementation
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerRoom-01",
							"Fehler!");
		}
	}

	/**
	 * unregisters an {@link IntfDataObserver} Observer at the
	 * {@link DataHandlerRoom}
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

}