package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.datalayer.core.definitions.IntfDataChair;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerChair implements IntfDataChair, IntfDataFilter,
		IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataManagerPostgreSql filterDm = null;

	@Override
	public List<Chair> getAll() {
		List<Chair> listChair = new ArrayList<Chair>();

		String SqlStatement = "SELECT * FROM public.chair";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listChair.add(this.makeChair(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerChair-02) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerChair-03) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return listChair;
	}

	@Override
	public Chair get(int id) {
		try {
			DataManagerPostgreSql.getInstance().prepare(
					"SELECT public.chair.* " + "FROM  public.chair "
							+ "WHERE public.chair.chairid = ?");
			DataManagerPostgreSql.getInstance().getPreparedStatement()
					.setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerUser-15:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-16:<br />" + e
									.toString()), "Fehler!");
		}

		return null;
	}

	/**
	 * Get the chair of a user
	 * 
	 * @param userId
	 * @return a Chair if the submitted user (id) can be associated with one,
	 *         otherwise returns null
	 */
	public Chair getForUser(int userId) {
		try {
			DataManagerPostgreSql
					.getInstance()
					.prepare(
							"SELECT public.chair.* "
									+ "FROM public.chair, public.lecturer "
									+ "WHERE public.lecturer.chairid = public.chair.chairid "
									+ "AND public.lecturer.userid = ? ");
			DataManagerPostgreSql.getInstance().getPreparedStatement()
					.setInt(1, userId);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerChair-06:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerChair-07:<br />" + e
									.toString()), "Fehler!");
		}

		return null;
	}

	/**
	 * Get the chair based on its acronym
	 * 
	 * @param acronym
	 * @return a chair if the submitted acronym can be associated with one,
	 *         otherwise returns null
	 */
	public Chair getForAcronym(String acronym) {
		try {
			DataManagerPostgreSql.getInstance().prepare(
					"SELECT public.chair.* " + "FROM public.chair "
							+ "WHERE public.chair.chairacronym = ? ");
			DataManagerPostgreSql.getInstance().getPreparedStatement()
					.setString(1, acronym);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerChair-08:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerChair-09:<br />" + e
									.toString()), "Fehler!");
		}

		return null;
	}

	@Override
	public List<Chair> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chair> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Chair toBeDeletedChair) {
		if (toBeDeletedChair != null) {
			boolean returnState = true;

			// Now delete the chair
			if (returnState) {
				try {
					DataManagerPostgreSql dm = DataManagerPostgreSql
							.getInstance();

					dm.prepare("DELETE FROM public.chair "
							+ "WHERE public.chair.chairid = ?");
					dm.getPreparedStatement().setInt(1,
							toBeDeletedChair.getChairId_());
					returnState = dm.executePstmt();
					this.update();
				} catch (SQLException e) {
					returnState = false;
					e.printStackTrace();
					DataModel
							.getInstance()
							.getExceptionsHandler()
							.setNewException(
									("Es ist ein SQL-Fehler in der Klasse DataHandlerUser aufgetreten:<br /><br />" + e
											.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					returnState = false;
					e.printStackTrace();
					DataModel
							.getInstance()
							.getExceptionsHandler()
							.setNewException(
									("Es ist ein unbekannter Fehler in der Klasse DataHandlerUser in der Datenhaltung aufgetreten:<br /><br />" + e
											.toString()), "Fehler!");
				}
			}
			return returnState;
		}
		return false;
	}

	@Override
	public void save(Chair Chair) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Chair> getByFilter(HashMap<String, String> filter) {
		List<Chair> listChair = new ArrayList<Chair>();
		// TODO Auto-generated method stub
		try {

			ResultSet rs = null;

			if (filterDm == null) {
				filterDm = new DataManagerPostgreSql();
				filterDm.prepare("SELECT public.user.*, public.chair.* "
						+ "FROM public.chair LEFT JOIN public.user "
						+ "ON public.chair.chairowner =  public.user.userid "
						+ "WHERE (public.chair.chairname LIKE ?  OR public.chair.chairacronym LIKE ? ) ");
			}
			if (filter.containsKey("chair") && filter.get("chair") != null
					&& filter.get("chair") != ""
					&& filter.get("chair") != "<alle>") {
				filterDm.getPreparedStatement().setString(1,
						"%" + filter.get("chair") + "%");
				filterDm.getPreparedStatement().setString(2,
						"%" + filter.get("chair") + "%");
			} else {
				filterDm.getPreparedStatement().setString(1, "%");
				filterDm.getPreparedStatement().setString(2, "%");
			}

			rs = filterDm.selectPstmt();
			while (rs.next()) {
				listChair.add(makeChair(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return listChair;
	}

	/**
	 * Forms a Chair object out of a given result set
	 * 
	 * @param ResultSet
	 *            rs
	 * @return a Chair object
	 */
	public Chair makeChair(ResultSet rs) {
		Chair returnChair = new Chair();

		try {
			returnChair.setChairId_(rs.getInt("chairid"));
			returnChair.setChairName_(rs.getString("chairname"));

			// returnChair.setChairOwner_(DataModel.getInstance().getDataHandlerUser().get(rs.getInt("chairowner")));
			returnChair.setBuildingId_(rs.getInt("buildingid"));
			returnChair.setChairLevel_(rs.getString("chairlevel"));
			returnChair.setFaculty_(rs.getString("faculty"));
			returnChair.setAcronym_(rs.getString("chairacronym"));
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerChair-04) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerChair-05) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return returnChair;
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
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerChair-01",
							"Fehler!");
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
