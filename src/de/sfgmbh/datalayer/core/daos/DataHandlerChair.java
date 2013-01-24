package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.datalayer.core.definitions.IntfDataChair;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;
/**
 * 
 * @author denis
 *
 */
public class DataHandlerChair implements IntfDataChair, IntfDataFilter,
		IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();

	@Override
	public List<IntfChair> getAll() {
		List<IntfChair> listChair = new ArrayList<IntfChair>();

		String SqlStatement = 
				"SELECT public.user.*, public.chair.* " + 
				"FROM  public.chair LEFT JOIN public.user " +
				"ON public.chair.chairowner =  public.user.userid ";

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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}

		return listChair;
	}

	@Override
	public IntfChair get(int chairId) {
		try {
			DataManagerPostgreSql.getInstance().prepare(
					"SELECT public.user.*, public.chair.* " + 
					"FROM  public.chair LEFT JOIN public.user " +
					"ON public.chair.chairowner =  public.user.userid " +
					"WHERE public.chair.chairid = ?");
			DataManagerPostgreSql.getInstance().getPreparedStatement()
					.setInt(1, chairId);
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
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
	public IntfChair getForUser(int userId) {
		try {
			DataManagerPostgreSql
					.getInstance()
					.prepare(
							"SELECT public.user.*, public.chair.* " +
							"FROM public.lecturer, public.chair LEFT JOIN public.user " +
							"ON public.chair.chairowner =  public.user.userid " +
							"WHERE public.lecturer.chairid = public.chair.chairid " +
							"AND public.lecturer.userid = ? ");
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
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
	public IntfChair getForAcronym(String acronym) {
		try {
			DataManagerPostgreSql.getInstance().prepare(
					"SELECT public.user.*, public.chair.* " + 
					"FROM public.chair  LEFT JOIN public.user " +
					"ON public.chair.chairowner =  public.user.userid " +
					"WHERE public.chair.chairacronym = ? ");
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}

		return null;
	}

	@Override
	public boolean delete(IntfChair toBeDeletedChair) {
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
				}finally{
					DataManagerPostgreSql.getInstance().dispose();
				}
			}
			return returnState;
		}
		return false;
	}

	@Override
	public boolean save(IntfChair chair) {
		if (chair.getChairId_() == -1) {
			boolean returnState = true;
				try {
					
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("INSERT INTO public.chair"
							+ "(chairname, chairowner, buildingid, chairlevel, faculty, chairacronym)"
							+ "VALUES (?,?,?,?,?,?)");
					dm.getPreparedStatement().setString(1, chair.getChairName_());
					if (chair.getChairOwner_() != null) {
						dm.getPreparedStatement().setInt(2, chair.getChairOwner_().getUserId_());
					} else {
						dm.getPreparedStatement().setNull(2, java.sql.Types.INTEGER);
					}
					dm.getPreparedStatement().setInt(3, chair.getBuildingId_());
					dm.getPreparedStatement().setString(4, chair.getChairLevel_());
					dm.getPreparedStatement().setString(5, chair.getFaculty_());
					dm.getPreparedStatement().setString(6, chair.getAcronym_());
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
				dm.prepare("UPDATE public.chair SET "
						+ "chairname = ?, chairowner = ?, buildingid = ?, chairlevel = ?, faculty = ?, chairacronym = ? "
						+ "WHERE chairid = ?");
				dm.getPreparedStatement().setString(1, chair.getChairName_());
				if (chair.getChairOwner_() != null) {
					dm.getPreparedStatement().setInt(2, chair.getChairOwner_().getUserId_());
				} else {
					dm.getPreparedStatement().setNull(2, java.sql.Types.INTEGER);
				}
				dm.getPreparedStatement().setInt(3, chair.getBuildingId_());
				dm.getPreparedStatement().setString(4, chair.getChairLevel_());
				dm.getPreparedStatement().setString(5, chair.getFaculty_());
				dm.getPreparedStatement().setString(6, chair.getAcronym_());
				dm.getPreparedStatement().setInt(7, chair.getChairId_());
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

	@Override
	public List<IntfChair> getByFilter(HashMap<String, String> filter) {
		DataManagerPostgreSql filterDm = null;
		List<IntfChair> listChair = new ArrayList<IntfChair>();
		// TODO Auto-generated method stub
		try {

			ResultSet rs = null;

			if (filterDm == null) {
				filterDm = DataManagerPostgreSql.getInstance();
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}

		return listChair;
	}

	/**
	 * Forms a Chair object out of a given result set
	 * 
	 * @param resultSet - {@link ResultSet} for creatign the CHair Objects
	 *            rs
	 * @return  - a Chair object
	 */
	public IntfChair makeChair(ResultSet resultSet) {
		IntfChair returnChair = new Chair();

		try {
			returnChair.setChairId_(resultSet.getInt("chairid"));
			returnChair.setChairName_(resultSet.getString("chairname"));
			if (resultSet.getInt("chairowner") > 0) {
				returnChair.setChairOwner_(DataModel.getInstance().getDataHandlerUser().makeUser(resultSet, "nochair"));
			}
			returnChair.setBuildingId_(resultSet.getInt("buildingid"));
			returnChair.setChairLevel_(resultSet.getString("chairlevel"));
			returnChair.setFaculty_(resultSet.getString("faculty"));
			returnChair.setAcronym_(resultSet.getString("chairacronym"));
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
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

}
