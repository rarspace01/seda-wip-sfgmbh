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
 * DataHandler for chairs
 * 
 * @author denis
 * @author anna
 *
 */
public class DataHandlerChair implements IntfDataChair, IntfDataFilter,
		IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#getAll()
	 */
	@Override
	public List<IntfChair> getAll() {
		List<IntfChair> listChair = new ArrayList<IntfChair>();

		String SqlStatement = 
				"SELECT public.user.*, public.chair.* " + 
				"FROM  public.chair LEFT JOIN public.user " +
				"ON public.chair.chairowner =  public.user.userid ";
		
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			
			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				listChair.add(this.makeChair(resultSet));
			}

			
			
		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return listChair;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#get(int)
	 */
	@Override
	public IntfChair get(int chairId) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare(
					"SELECT public.user.*, public.chair.* " + 
					"FROM  public.chair LEFT JOIN public.user " +
					"ON public.chair.chairowner =  public.user.userid " +
					"WHERE public.chair.chairid = ?");
			dataManager.getPreparedStatement()
					.setInt(1, chairId);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerUser-15:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-16:<br />" + e
									.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#getForUser(int)
	 */
	@Override
	public IntfChair getForUser(int userId) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare(
							"SELECT public.user.*, public.chair.* " +
							"FROM public.lecturer, public.chair LEFT JOIN public.user " +
							"ON public.chair.chairowner =  public.user.userid " +
							"WHERE public.lecturer.chairid = public.chair.chairid " +
							"AND public.lecturer.userid = ? ");
			dataManager.getPreparedStatement()
					.setInt(1, userId);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerChair-06:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerChair-07:<br />" + e
									.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#getForAcronym(java.lang.String)
	 */
	@Override
	public IntfChair getForAcronym(String acronym) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare(
					"SELECT public.user.*, public.chair.* " + 
					"FROM public.chair  LEFT JOIN public.user " +
					"ON public.chair.chairowner =  public.user.userid " +
					"WHERE public.chair.chairacronym = ? ");
			dataManager.getPreparedStatement()
					.setString(1, acronym);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeChair(rs);
			}

		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerChair-08:<br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerChair-09:<br />" + e
									.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#delete(de.sfgmbh.applayer.core.definitions.IntfChair)
	 */
	@Override
	public boolean delete(IntfChair toBeDeletedChair) {
		
		if (toBeDeletedChair != null) {
			boolean returnState = true;

			// Now delete the chair
			if (returnState) {
				try {
					DataManagerPostgreSql dataManager = new DataManagerPostgreSql();

					dataManager.prepare("DELETE FROM public.chair "
							+ "WHERE public.chair.chairid = ?");
					dataManager.getPreparedStatement().setInt(1,
							toBeDeletedChair.getChairId_());
					returnState = dataManager.executePreparedStatement();
					dataManager.dispose();
					this.update();
				} catch (SQLException e) {
					returnState = false;
					
					DataModel
							.getInstance()
							.getExceptionsHandler()
							.setNewException(
									("Es ist ein SQL-Fehler in der Klasse DataHandlerUser aufgetreten:<br /><br />" + e
											.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					returnState = false;
					
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

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#save(de.sfgmbh.applayer.core.definitions.IntfChair)
	 */
	@Override
	public boolean save(IntfChair chair) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		if (chair.getChairId_() == -1) {
			boolean returnState = true;
				try {
					
					dataManager.prepare("INSERT INTO public.chair"
							+ "(chairname, chairowner, buildingid, chairlevel, faculty, chairacronym)"
							+ "VALUES (?,?,?,?,?,?)");
					dataManager.getPreparedStatement().setString(1, chair.getChairName());
					if (chair.getChairOwner() != null) {
						dataManager.getPreparedStatement().setInt(2, chair.getChairOwner().getUserId_());
					} else {
						dataManager.getPreparedStatement().setNull(2, java.sql.Types.INTEGER);
					}
					dataManager.getPreparedStatement().setInt(3, chair.getBuildingId());
					dataManager.getPreparedStatement().setString(4, chair.getChairLevel());
					dataManager.getPreparedStatement().setString(5, chair.getFaculty());
					dataManager.getPreparedStatement().setString(6, chair.getAcronym());
					dataManager.executePreparedStatement();
					this.update();
				} catch (SQLException e) {
					
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
					returnState =  false;
				} catch (Exception e) {
					
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
					returnState =  false;
				}

				dataManager.dispose();
				return returnState;
		} else {
			boolean returnState = true;
			try {
				dataManager.prepare("UPDATE public.chair SET "
						+ "chairname = ?, chairowner = ?, buildingid = ?, chairlevel = ?, faculty = ?, chairacronym = ? "
						+ "WHERE chairid = ?");
				dataManager.getPreparedStatement().setString(1, chair.getChairName());
				if (chair.getChairOwner() != null) {
					dataManager.getPreparedStatement().setInt(2, chair.getChairOwner().getUserId_());
				} else {
					dataManager.getPreparedStatement().setNull(2, java.sql.Types.INTEGER);
				}
				dataManager.getPreparedStatement().setInt(3, chair.getBuildingId());
				dataManager.getPreparedStatement().setString(4, chair.getChairLevel());
				dataManager.getPreparedStatement().setString(5, chair.getFaculty());
				dataManager.getPreparedStatement().setString(6, chair.getAcronym());
				dataManager.getPreparedStatement().setInt(7, chair.getChairId_());
				returnState = dataManager.executePreparedStatement();
				this.update();
			} catch (SQLException e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				returnState = false;
			} catch (Exception e) {
				
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				returnState = false;
			}
			
			dataManager.dispose();
			return returnState;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataFilter#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<IntfChair> getByFilter(HashMap<String, String> filter) {

		DataManagerPostgreSql filterDm = null;
		List<IntfChair> listChair = new ArrayList<IntfChair>();

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

			rs = filterDm.selectPreparedStatement();
			while (rs.next()) {
				listChair.add(makeChair(rs));
			}

			filterDm.dispose();
		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return listChair;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataChair#makeChair(java.sql.ResultSet)
	 */
	@Override
	public IntfChair makeChair(ResultSet resultSet) {

		IntfChair returnChair = new Chair();

		try {
			returnChair.setChairId(resultSet.getInt("chairid"));
			returnChair.setChairName(resultSet.getString("chairname"));
			if (resultSet.getInt("chairowner") > 0) {
				returnChair.setChairOwner(DataModel.getInstance().getDataHandlerUser().makeUser(resultSet, "nochair"));
			}
			returnChair.setBuildingId(resultSet.getInt("buildingid"));
			returnChair.setChairLevel(resultSet.getString("chairlevel"));
			returnChair.setFaculty(resultSet.getString("faculty"));
			returnChair.setAcronym(resultSet.getString("chairacronym"));
		} catch (SQLException e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataHandlerChair-04) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataHandlerChair-05) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return returnChair;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObservable#update()
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

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObservable#register(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
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

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObservable#unregister(de.sfgmbh.datalayer.core.definitions.IntfDataObserver)
	 */
	@Override
	public void unregister(IntfDataObserver observer) {
		observer_.remove(observer);
	}

}
