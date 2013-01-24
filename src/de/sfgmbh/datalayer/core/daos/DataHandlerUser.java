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
	
	@Override
	public List<User> getAll() {
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = 	"SELECT public.user.*, public.chair.* " +
								"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
									"ON public.chair.chairid = public.lecturer.chairid " +
									"ON public.user.userid = public.lecturer.userid ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
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
			DataManagerPostgreSql.getInstance().dispose();
		}

		return listUser;
	}
	
	/**
	 * Get all users which are lecturers
	 * @return a list of users which are lecturers
	 */
	public List<User> getAllLecturer() {
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = 	"SELECT public.user.*, public.chair.* " +
								"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
									"ON public.chair.chairid = public.lecturer.chairid " +
									"ON public.user.userid = public.lecturer.userid " +
								"WHERE public.user.class = 'lecturer' ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
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
			DataManagerPostgreSql.getInstance().dispose();
		}

		return listUser;
	}

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
					filterWithChairDm = DataManagerPostgreSql.getInstance(); 
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

				rs = filterWithChairDm.selectPstmt();
				
			} else {
			
				if (filterDm == null) { 
					filterDm = DataManagerPostgreSql.getInstance(); 
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
				
				rs = filterDm.selectPstmt();
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
		}finally{
			DataManagerPostgreSql.getInstance().dispose();
		}
		return listUser;
	}
	
	@Override
	public IntfUser get(int id) {
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT public.user.*, public.chair.* " +
														"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
															"ON public.chair.chairid = public.lecturer.chairid " +
															"ON public.user.userid = public.lecturer.userid " +
														"WHERE public.user.userid = ?");
			DataManagerPostgreSql.getInstance().getPreparedStatement().setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
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
			DataManagerPostgreSql.getInstance().dispose();
		}
		
		return null;
	}
	
	/**
	 * Get a user by its login
	 * @param login
	 * @return a user
	 */
	public IntfUser getByLogin(String login) {
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT public.user.*, public.chair.* " +
														"FROM public.user LEFT JOIN public.lecturer INNER JOIN public.chair " +
															"ON public.chair.chairid = public.lecturer.chairid " +
															"ON public.user.userid = public.lecturer.userid " +
														"WHERE public.user.login = ?");
			DataManagerPostgreSql.getInstance().getPreparedStatement().setString(1, login);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
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
			DataManagerPostgreSql.getInstance().dispose();
		}
		
		return null;
	}

	@Override
	public List<User> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(IntfUser toBeDeletedUser) {
		if (toBeDeletedUser != null) {
			boolean returnState = true;
			
			// Check if lecturer and delete the constrain first in that case
			if (toBeDeletedUser.getClass_().equals("lecturer")) {
				try {
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("DELETE FROM public.lecturer "
							+ "WHERE public.lecturer.userid = ?");
					dm.getPreparedStatement().setInt(1, toBeDeletedUser.getUserId_());
					returnState = dm.executePstmt();
					
				} catch (SQLException e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler in der Klasse DataHandlerUser aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					returnState = false;
					e.printStackTrace();
					DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Klasse DataHandlerUser in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				}finally{
					DataManagerPostgreSql.getInstance().dispose();
				}
			}
			
			// Now proceed with the deletion in the user table if the first delete was a success
			if (returnState) {
				try {
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("DELETE FROM public.user "
							+ "WHERE public.user.userid = ?");
					dm.getPreparedStatement().setInt(1, toBeDeletedUser.getUserId_());
					returnState = dm.executePstmt();
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
					DataManagerPostgreSql.getInstance().dispose();
				}
			}
			return returnState;
		}
		return false;
	}

	/**
	 * Saves a new user in the DB if the user doesn't exist already and updates an existing user in the DB otherwise
	 * @param user
	 * @return ture on success
	 */
	@Override
	public boolean save(IntfUser user) {
		
		if (user.getUserId_() == -1) {
			boolean returnState = true;
				try {
					
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("INSERT INTO public.user"
							+ "(login, pass, salt, mail, class, fname, lname, lastlogin, disabled)"
							+ "VALUES (?,?,?,?,?,?,?,?,?)");
					dm.getPreparedStatement().setString(1, user.getLogin_());
					dm.getPreparedStatement().setString(2, user.getPass_());
					dm.getPreparedStatement().setString(3, user.getSalt_());
					dm.getPreparedStatement().setString(4, user.getMail_());
					dm.getPreparedStatement().setString(5, user.getClass_());
					dm.getPreparedStatement().setString(6, user.getfName_());
					dm.getPreparedStatement().setString(7, user.getlName_());
					dm.getPreparedStatement().setLong(8, user.getLastLogin_());
					dm.getPreparedStatement().setBoolean(9, user.isDisabled_());
					dm.executePstmt();
					if (user.getChair_() != null) {
						DataManagerPostgreSql dmPrivate = new DataManagerPostgreSql();
						IntfUser newUser = this.getByLogin(user.getLogin_());
						dmPrivate.prepare("INSERT INTO public.lecturer"
								+ "(userid, chairid)"
								+ "VALUES (?,?)");
						dmPrivate.getPreparedStatement().setInt(1, newUser.getUserId_());
						dmPrivate.getPreparedStatement().setInt(2, user.getChair_().getChairId_());
						returnState = dmPrivate.executePstmt();
						dmPrivate.disposeNonSingleton();
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
					DataManagerPostgreSql.getInstance().dispose();
				}
				return returnState;
		} else {
			boolean returnState = true;
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				dm.prepare("UPDATE public.user SET "
						+ "login = ?, pass = ?, salt = ?, mail = ?, class = ?, fname = ?, lname = ?, lastlogin = ?, disabled = ?"
						+ "WHERE userid = ?");
				dm.getPreparedStatement().setString(1, user.getLogin_());
				dm.getPreparedStatement().setString(2, user.getPass_());
				dm.getPreparedStatement().setString(3, user.getSalt_());
				dm.getPreparedStatement().setString(4, user.getMail_());
				dm.getPreparedStatement().setString(5, user.getClass_());
				dm.getPreparedStatement().setString(6, user.getfName_());
				dm.getPreparedStatement().setString(7, user.getlName_());
				dm.getPreparedStatement().setLong(8, user.getLastLogin_());
				dm.getPreparedStatement().setBoolean(9, user.isDisabled_());
				dm.getPreparedStatement().setInt(10, user.getUserId_());
				returnState = dm.executePstmt();
				if (user.getChair_() != null) {
					
					// Frist check if the user already exists in the lecturer table
					dm.prepare("SELECT public.lecturer.* " +
								"FROM public.lecturer " +
								"WHERE public.lecturer.userid = ? ");
					dm.getPreparedStatement().setInt(1, user.getUserId_());
					ResultSet resultSet = dm.selectPstmt();
					
					// Update if there is already an entry...
					if (resultSet.next()) {
						dm.prepare("UPDATE public.lecturer SET "
								+ "chairid = ? "
								+ "WHERE userid = ?");
						dm.getPreparedStatement().setInt(1, user.getChair_().getChairId_());
						dm.getPreparedStatement().setInt(2, user.getUserId_());
						returnState = dm.executePstmt();
						
					// Create if not...
					} else {
						DataManagerPostgreSql dmPrivate = new DataManagerPostgreSql();
						dmPrivate.prepare("INSERT INTO public.lecturer"
								+ "(userid, chairid)"
								+ "VALUES (?,?)");
						dmPrivate.getPreparedStatement().setInt(1, user.getUserId_());
						dmPrivate.getPreparedStatement().setInt(2, user.getChair_().getChairId_());
						returnState = dmPrivate.executePstmt();
						dmPrivate.disposeNonSingleton();
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
				DataManagerPostgreSql.getInstance().dispose();
			}
			return returnState;
			
		}
	}
	
	/**
	 * Forms a User object out of a given result set based on a variant
	 * @param rs
	 * @param variant
	 * @return a User object
	 */
	public User makeUser(ResultSet rs, String variant) {
		User returnUser = new User();
		
		try {
			returnUser.setUserId_(rs.getInt("userid"));
			returnUser.setLogin_(rs.getString("login"));
			returnUser.setPass_(rs.getString("pass"));
			returnUser.setSalt_(rs.getString("salt"));
			returnUser.setMail_(rs.getString("mail"));
			returnUser.setClass_(rs.getString("class"));
			returnUser.setfName_(rs.getString("fname"));
			returnUser.setlName_(rs.getString("lname"));
			returnUser.setLastLogin_(rs.getLong("lastlogin"));
			returnUser.setDisabled_(rs.getBoolean("disabled"));
			if (rs.getInt("chairid") > 0 && !variant.equals("nochair")) {
				returnUser.setChair_(DataModel.getInstance().getDataHandlerChair().makeChair(rs));
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
			DataModel.getInstance().getExceptionsHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: DataHandlerUser-12", "Fehler!");
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
