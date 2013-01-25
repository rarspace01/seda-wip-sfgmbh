package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.definitions.IntfDataFilter;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Data handler for users in the data base
 * 
 * @author hannes
 * @author denis
 *
 */
public class DataHandlerUser implements IntfDataUser, IntfDataObservable, IntfDataFilter {
	
	private ArrayList<IntfDataObserver> observer_ = new ArrayList<IntfDataObserver>();
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataUser#getAll()
	 */
	@Override
	public List<User> getAll() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = 	"SELECT public.user.*, public.chair.* " +
								"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
									"ON public.chair.chairid = public.lecturer.chairid " +
									"ON public.user.userid = public.lecturer.userid ";

		try {

			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				listUser.add(this.makeUser(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return listUser;
	}
	
	/**
	 * Get all users which are lecturers
	 * @return a list of users which are lecturers
	 */
	public List<User> getAllLecturer() {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = 	"SELECT public.user.*, public.chair.* " +
								"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
									"ON public.chair.chairid = public.lecturer.chairid " +
									"ON public.user.userid = public.lecturer.userid " +
								"WHERE public.user.class = 'lecturer' ";

		try {

			ResultSet resultSet = dataManager.select(
					SqlStatement);

			while (resultSet.next()) {
				listUser.add(this.makeUser(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}

		return listUser;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataFilter#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<User> getByFilter(HashMap<String, String> filter) {
		
		DataManagerPostgreSql filterDm = null;
		DataManagerPostgreSql filterWithChairDm = null;
		
		List<User> listUser = new ArrayList<User>();
		
		// Convert some filter values
		if (filter.containsKey("userclass") && filter.get("userclass").equals("Dozenten")) {
			filter.put("userclass", "lecturer");
		}
		if (filter.containsKey("userclass") && filter.get("userclass").equals("Verwaltung")) {
			filter.put("userclass", "orga");
		}
		
		try {
			
			ResultSet rs = null;
			
			// Check for chair filter as we need another sql statement in that case
			if (filter.containsKey("chair") && filter.get("chair") != null && filter.get("chair") != "" && filter.get("chair") != "<alle>") {
				
				if (filterWithChairDm == null) { 
					filterWithChairDm = new DataManagerPostgreSql(); 
					filterWithChairDm.prepare(
							"SELECT public.user.*, public.chair.* " +
							"FROM public.user, public.chair, public.lecturer " +
							"WHERE public.chair.chairid = public.lecturer.chairid " +
							"AND public.user.userid = public.lecturer.userid " +
							"AND (public.user.lname LIKE ? OR public.user.fname LIKE ? OR public.user.login LIKE ? ) " +
							"AND (public.chair.chairname LIKE ? OR public.chair.chairacronym LIKE ? ) " +
							"AND public.user.class LIKE ? " +
							"AND public.user.mail LIKE ? ");
				}
				
				if (filter.containsKey("user") && filter.get("user") != null && filter.get("user") != "" && filter.get("user") != "<alle>") {
					filterWithChairDm.getPreparedStatement().setString(1, "%" + filter.get("user") + "%");
					filterWithChairDm.getPreparedStatement().setString(2, "%" + filter.get("user") + "%");
					filterWithChairDm.getPreparedStatement().setString(3, "%" + filter.get("user") + "%");
				} else {
					filterWithChairDm.getPreparedStatement().setString(1, "%");
					filterWithChairDm.getPreparedStatement().setString(2, "%");
					filterWithChairDm.getPreparedStatement().setString(3, "%");
				}
				if (filter.containsKey("chair") && filter.get("chair") != null && filter.get("chair") != "" && filter.get("chair") != "<alle>") {
					filterWithChairDm.getPreparedStatement().setString(4, "%" + filter.get("chair") + "%");
					filterWithChairDm.getPreparedStatement().setString(5, "%" + filter.get("chair") + "%");
				} else {
					filterWithChairDm.getPreparedStatement().setString(4, "%");
					filterWithChairDm.getPreparedStatement().setString(5, "%");
				}
				if ( filter.containsKey("userclass") && filter.get("userclass") != null && filter.get("userclass") != "" && filter.get("userclass") != "<alle>") {
					filterWithChairDm.getPreparedStatement().setString(6, "%" + filter.get("userclass") + "%");
				} else {
					filterWithChairDm.getPreparedStatement().setString(6, "%");
				}
				if (filter.containsKey("email") && filter.get("email") != null && filter.get("email") != "" && filter.get("email") != "<alle>") {
					filterWithChairDm.getPreparedStatement().setString(7, "%" + filter.get("email") + "%");
				} else {
					filterWithChairDm.getPreparedStatement().setString(7, "%");
				}

				rs = filterWithChairDm.selectPreparedStatement();
				
				
			} else {
			
				if (filterDm == null) { 
					filterDm = new DataManagerPostgreSql();
					filterDm.prepare(
							"SELECT public.user.*, public.chair.* " +
							"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
								"ON public.chair.chairid = public.lecturer.chairid " +
								"ON public.user.userid = public.lecturer.userid " +
							"WHERE (public.user.lname LIKE ? OR public.user.fname LIKE ? OR public.user.login LIKE ? ) " +
							"AND public.user.class LIKE ? " +
							"AND public.user.mail LIKE ? ");
				}
				
				if (filter.containsKey("user") && filter.get("user") != null && filter.get("user") != "" && filter.get("user") != "<alle>") {
					filterDm.getPreparedStatement().setString(1, "%" + filter.get("user") + "%");
					filterDm.getPreparedStatement().setString(2, "%" + filter.get("user") + "%");
					filterDm.getPreparedStatement().setString(3, "%" + filter.get("user") + "%");
				} else {
					filterDm.getPreparedStatement().setString(1, "%");
					filterDm.getPreparedStatement().setString(2, "%");
					filterDm.getPreparedStatement().setString(3, "%");
				}
				if ( filter.containsKey("userclass") && filter.get("userclass") != null && filter.get("userclass") != "" && filter.get("userclass") != "<alle>") {
					filterDm.getPreparedStatement().setString(4, "%" + filter.get("userclass") + "%");
				} else {
					filterDm.getPreparedStatement().setString(4, "%");
				}
				if (filter.containsKey("email") && filter.get("email") != null && filter.get("email") != "" && filter.get("email") != "<alle>") {
					filterDm.getPreparedStatement().setString(5, "%" + filter.get("email") + "%");
				} else {
					filterDm.getPreparedStatement().setString(5, "%");
				}
				
				rs = filterDm.selectPreparedStatement();
			}
			
			while (rs.next()) {
				listUser.add(this.makeUser(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerRoomAllocation-17:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerRoomAllocation-18:<br />" + e.toString()), "Fehler!");
		} finally {
			if (filterDm != null) {
				filterDm.dispose();
			}
			if (filterWithChairDm != null) {
				filterWithChairDm.dispose();
			}
		}
		
		return listUser;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataUser#get(int)
	 */
	@Override
	public IntfUser get(int id) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare("SELECT public.user.*, public.chair.* " +
														"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
															"ON public.chair.chairid = public.lecturer.chairid " +
															"ON public.user.userid = public.lecturer.userid " +
														"WHERE public.user.userid = ?");
			dataManager.getPreparedStatement().setInt(1, id);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerUser-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-16:<br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}
		
		return null;
	}
	
	/**
	 * Get a user by its login
	 * @param login
	 * @return a user
	 */
	public IntfUser getByLogin(String login) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		try {
			dataManager.prepare("SELECT public.user.*, public.chair.* " +
														"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
															"ON public.chair.chairid = public.lecturer.chairid " +
															"ON public.user.userid = public.lecturer.userid " +
														"WHERE public.user.login = ?");
			dataManager.getPreparedStatement().setString(1, login);
			ResultSet rs = dataManager.selectPreparedStatement();
			while (rs.next()) {
				return this.makeUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerUser-10:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-11:<br />" + e.toString()), "Fehler!");
		}finally{
			dataManager.dispose();
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataUser#search(java.lang.String)
	 */
	@Override
	public List<User> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataUser#delete(de.sfgmbh.applayer.core.definitions.IntfUser)
	 */
	@Override
	public boolean delete(IntfUser toBeDeletedUser) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		if (toBeDeletedUser != null) {
			boolean returnState = true;
			
			// Check if lecturer and delete the constrain first in that case
			if (toBeDeletedUser.getClass_().equals("lecturer")) {
				try {
					dataManager.prepare("DELETE FROM public.lecturer "
							+ "WHERE public.lecturer.userid = ?");
					dataManager.getPreparedStatement().setInt(1, toBeDeletedUser.getUserId_());
					returnState = dataManager.executePreparedStatement();
					
				} catch (SQLException e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler in der Klasse DataHandlerUser aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Klasse DataHandlerUser in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				}finally{
					dataManager.dispose();
				}
			}
			
			// Now proceed with the deletion in the user table if the first delete was a success
			if (returnState) {
				try {
					dataManager = new DataManagerPostgreSql();
					
					dataManager.prepare("DELETE FROM public.user "
							+ "WHERE public.user.userid = ?");
					dataManager.getPreparedStatement().setInt(1, toBeDeletedUser.getUserId_());
					returnState = dataManager.executePreparedStatement();
					this.update();
				} catch (SQLException e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler in der Klasse DataHandlerUser aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Klasse DataHandlerUser in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				}finally{
					dataManager.dispose();
				}
			}
			return returnState;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataUser#save(de.sfgmbh.applayer.core.definitions.IntfUser)
	 */
	@Override
	public boolean save(IntfUser user) {
		DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
		if (user.getUserId_() == -1) {
			boolean returnState = true;
				try {
					
					dataManager.prepare("INSERT INTO public.user"
							+ "(login, pass, salt, mail, class, fname, lname, lastlogin, disabled)"
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
					dataManager.getPreparedStatement().setString(1, user.getLogin_());
					dataManager.getPreparedStatement().setString(2, user.getPass_());
					dataManager.getPreparedStatement().setString(3, user.getSalt_());
					dataManager.getPreparedStatement().setString(4, user.getMail_());
					dataManager.getPreparedStatement().setString(5, user.getClass_());
					dataManager.getPreparedStatement().setString(6, user.getFirstName_());
					dataManager.getPreparedStatement().setString(7, user.getlName_());
					dataManager.getPreparedStatement().setLong(8, user.getLastLogin_());
					dataManager.getPreparedStatement().setBoolean(9, user.isDisabled_());
					dataManager.executePreparedStatement();
					if (user.getChair_() != null) {
						DataManagerPostgreSql dataManagerPrivate = new DataManagerPostgreSql();
						IntfUser newUser = this.getByLogin(user.getLogin_());
						dataManagerPrivate.prepare("INSERT INTO public.lecturer"
								+ "(userid, chairid)"
								+ "VALUES (?,?)");
						dataManagerPrivate.getPreparedStatement().setInt(1, newUser.getUserId_());
						dataManagerPrivate.getPreparedStatement().setInt(2, user.getChair_().getChairId_());
						returnState = dataManagerPrivate.executePreparedStatement();
						dataManagerPrivate.dispose();
					}
					this.update();
				} catch (SQLException e) {
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-05) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
					returnState =  false;
				} catch (Exception e) {
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-06) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
					returnState =  false;
				}finally{
					dataManager.dispose();
				}
				return returnState;
		} else {
			boolean returnState = true;
			try {
				dataManager.prepare("UPDATE public.user SET "
						+ "login = ?, pass = ?, salt = ?, mail = ?, class = ?, fname = ?, lname = ?, lastlogin = ?, disabled = ?"
						+ "WHERE userid = ?");
				dataManager.getPreparedStatement().setString(1, user.getLogin_());
				dataManager.getPreparedStatement().setString(2, user.getPass_());
				dataManager.getPreparedStatement().setString(3, user.getSalt_());
				dataManager.getPreparedStatement().setString(4, user.getMail_());
				dataManager.getPreparedStatement().setString(5, user.getClass_());
				dataManager.getPreparedStatement().setString(6, user.getFirstName_());
				dataManager.getPreparedStatement().setString(7, user.getlName_());
				dataManager.getPreparedStatement().setLong(8, user.getLastLogin_());
				dataManager.getPreparedStatement().setBoolean(9, user.isDisabled_());
				dataManager.getPreparedStatement().setInt(10, user.getUserId_());
				returnState = dataManager.executePreparedStatement();
				if (user.getChair_() != null) {
					
					// Frist check if the user already exists in the lecturer table
					dataManager.prepare("SELECT public.lecturer.* " +
								"FROM public.lecturer " +
								"WHERE public.lecturer.userid = ? ");
					dataManager.getPreparedStatement().setInt(1, user.getUserId_());
					ResultSet resultSet = dataManager.selectPreparedStatement();
					
					// Update if there is already an entry...
					if (resultSet.next()) {
						dataManager.prepare("UPDATE public.lecturer SET "
								+ "chairid = ? "
								+ "WHERE userid = ?");
						dataManager.getPreparedStatement().setInt(1, user.getChair_().getChairId_());
						dataManager.getPreparedStatement().setInt(2, user.getUserId_());
						returnState = dataManager.executePreparedStatement();
						
					// Create if not...
					} else {
						DataManagerPostgreSql dataManagerPrivate = new DataManagerPostgreSql();
						dataManagerPrivate.prepare("INSERT INTO public.lecturer"
								+ "(userid, chairid)"
								+ "VALUES (?,?)");
						dataManagerPrivate.getPreparedStatement().setInt(1, user.getUserId_());
						dataManagerPrivate.getPreparedStatement().setInt(2, user.getChair_().getChairId_());
						returnState = dataManagerPrivate.executePreparedStatement();
						dataManagerPrivate.dispose();
					}
				}
				this.update();
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				returnState = false;
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				returnState = false;
			}finally{
				dataManager.dispose();
			}
			return returnState;
			
		}
	}
	
	/**
	 * Forms a User object out of a given result set based on a variant
	 * @param resultSet
	 * @param variant
	 * @return a User object
	 */
	public User makeUser(ResultSet resultSet, String variant) {
		User returnUser = new User();
		
		try {
			returnUser.setUserId_(resultSet.getInt("userid"));
			returnUser.setLogin_(resultSet.getString("login"));
			returnUser.setPass_(resultSet.getString("pass"));
			returnUser.setSalt_(resultSet.getString("salt"));
			returnUser.setMail_(resultSet.getString("mail"));
			returnUser.setClass_(resultSet.getString("class"));
			returnUser.setFirstName_(resultSet.getString("fname"));
			returnUser.setlName_(resultSet.getString("lname"));
			returnUser.setLastLogin_(resultSet.getLong("lastlogin"));
			returnUser.setDisabled_(resultSet.getBoolean("disabled"));
			if (resultSet.getInt("chairid") > 0 && !variant.equals("nochair")) {
				returnUser.setChair_(DataModel.getInstance().getDataHandlerChair().makeChair(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-13) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-14) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnUser;
	}
	
	/**
	 * Forms a User object out of a given result set
	 * @param rs
	 * @return a User object
	 */
	public User makeUser(ResultSet rs) {
		return this.makeUser(rs, "default");
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
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerUser-12", "Fehler!");
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