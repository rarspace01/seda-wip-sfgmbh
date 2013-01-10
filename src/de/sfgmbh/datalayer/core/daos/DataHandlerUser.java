package de.sfgmbh.datalayer.core.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.definitions.IntfDataObservable;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerUser implements IntfDataUser, IntfDataObservable {

	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	@Override
	public List<User> getAll() {
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = "SELECT * FROM public.user";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listUser.add(this.makeUser(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listUser;
	}
	
	public List<User> getAllLecturer() {
		List<User> listUser = new ArrayList<User>();

		String SqlStatement = "SELECT * FROM public.user WHERE class = 'lecturer' ";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {
				listUser.add(this.makeUser(resultSet));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listUser;
	}

	@Override
	public User get(int id) {
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT * FROM public.user WHERE userid = ?");
			DataManagerPostgreSql.getInstance().pstmt.setInt(1, id);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler aufgetreten.<br /><br />Fehler DataHandlerUser-15:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-16:<br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}
	
	public User getByLogin(String login) {
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT * FROM public.user WHERE login = ?");
			DataManagerPostgreSql.getInstance().pstmt.setString(1, login);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {
				return this.makeUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler aufgetreten:<br /><br />Fehler DataHandlerUser-10:<br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten:<br /><br />Fehler DataHandlerUser-11:<br />" + e.toString()), "Fehler!");
		}
		
		return null;
	}

	@Override
	public List<User> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User toBeDeletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(User user) {
		
		if (user.getUserId_() == -1) {
				try {
					
					DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
					
					dm.prepare("INSERT INTO public.user"
							+ "(login, pass, salt, mail, class, fname, lname, lastlogin)"
							+ "VALUES (?,?,?,?,?,?,?,?)");
					dm.pstmt.setString(1, user.getLogin_());
					dm.pstmt.setString(2, user.getPass_());
					dm.pstmt.setString(3, user.getSalt_());
					dm.pstmt.setString(4, user.getMail_());
					dm.pstmt.setString(5, user.getClass_());
					dm.pstmt.setString(6, user.getfName_());
					dm.pstmt.setString(7, user.getlName_());
					dm.pstmt.setLong(8, user.getLastLogin_());
					dm.executePstmt();
					
				} catch (SQLException e) {
					e.printStackTrace();
					DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-05) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					e.printStackTrace();
					DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-06) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
				}
		} else {
			try {
				DataManagerPostgreSql dm=DataManagerPostgreSql.getInstance();
				dm.prepare("UPDATE public.user SET "
						+ "login = ?, pass = ?, salt = ?, mail = ?, class = ?, fname = ?, lname = ?, lastlogin = ?"
						+ "WHERE userid = ?");
				dm.pstmt.setString(1, user.getLogin_());
				dm.pstmt.setString(2, user.getPass_());
				dm.pstmt.setString(3, user.getSalt_());
				dm.pstmt.setString(4, user.getMail_());
				dm.pstmt.setString(5, user.getClass_());
				dm.pstmt.setString(6, user.getfName_());
				dm.pstmt.setString(7, user.getlName_());
				dm.pstmt.setLong(8, user.getLastLogin_());
				dm.pstmt.setInt(9, user.getUserId_());
				dm.executePstmt();
				
			} catch (SQLException e) {
				e.printStackTrace();
				DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
			}
		}
			
	}
	
	/**
	 * Forms a User object out of a given result set
	 * @param ResultSet rs
	 * @return a User object
	 */
	private User makeUser(ResultSet rs) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-13) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-14) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return returnUser;
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
			DataModel.getInstance().dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!<br />Fehler: DataHandlerUser-12", "Fehler!");
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
