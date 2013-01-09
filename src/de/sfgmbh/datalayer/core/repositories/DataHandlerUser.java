package de.sfgmbh.datalayer.core.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Data Handler for User Table
 * @author denis
 *
 */
public class DataHandlerUser implements IntfDataUser{

	@Override
	public List<User> getAll() {
		List<User> listUser = new ArrayList<User>();
		User returnUser = null;

		String SqlStatement = "SELECT * FROM public.user";

		try {

			ResultSet resultSet = DataManagerPostgreSql.getInstance().select(
					SqlStatement);

			while (resultSet.next()) {

				returnUser = new User();

				returnUser.setUserId_(resultSet.getInt("userid"));
				returnUser.setLogin_(resultSet.getString("login"));
				returnUser.setPass_(resultSet.getString("pass"));
				returnUser.setSalt_(resultSet.getString("salt"));
				returnUser.setMail_(resultSet.getString("mail"));
				returnUser.setClass_(resultSet.getString("class"));
				returnUser.setfName_(resultSet.getString("fname"));
				returnUser.setlName_(resultSet.getString("lname"));
				returnUser.setLastLogin_(resultSet.getLong("lastlogin"));

				listUser.add(returnUser);
				returnUser = null;

			}

		} catch (SQLException e) {
			e.printStackTrace();
			AppModel.getInstance().appExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-01) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			AppModel.getInstance().appExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-02) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}

		return listUser;
	}

	@Override
	public User get(int UserId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getByLogin(String login) {
	
		User returnUser = new User();
		
		try {
			DataManagerPostgreSql.getInstance().prepare("SELECT * FROM public.user WHERE login = ?");
			DataManagerPostgreSql.getInstance().pstmt.setString(1, login);
			ResultSet rs = DataManagerPostgreSql.getInstance().selectPstmt();
			while (rs.next()) {

				returnUser.setUserId_(rs.getInt("userid"));
				returnUser.setLogin_(rs.getString("login"));
				returnUser.setPass_(rs.getString("pass"));
				returnUser.setSalt_(rs.getString("salt"));
				returnUser.setMail_(rs.getString("mail"));
				returnUser.setClass_(rs.getString("class"));
				returnUser.setfName_(rs.getString("fname"));
				returnUser.setlName_(rs.getString("lname"));
				returnUser.setLastLogin_(rs.getLong("lastlogin"));

				return returnUser;

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			AppModel.getInstance().appExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		} catch (Exception e) {
			e.printStackTrace();
			AppModel.getInstance().appExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-04) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
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
					AppModel.getInstance().appExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-05) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
				} catch (Exception e) {
					e.printStackTrace();
					AppModel.getInstance().appExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-06) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
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
				AppModel.getInstance().appExcaptions.setNewException(("Es ist ein SQL-Fehler (DataHandlerUser-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
			} catch (Exception e) {
				e.printStackTrace();
				AppModel.getInstance().appExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataHandlerUser-08) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
			}
		}
			
	}


}
