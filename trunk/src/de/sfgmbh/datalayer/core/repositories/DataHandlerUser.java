package de.sfgmbh.datalayer.core.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

public class DataHandlerUser implements IntfDataUser{
	
	private DataManagerPostgreSql dm = DataManagerPostgreSql.getInstance();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			dm.prepare("SELECT * FROM public.user WHERE login = ?");
			dm.pstmt.setString(1, login);
			ResultSet rs = dm.selectPstmt();
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
		
		if (user.getUserId_() == 0 || user.getUserId_() == -1) {
				try {
					
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
					dm.pstmt.setInt(8, (int) user.getLastLogin_());
					dm.executePstmt();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} else {
			try {
				
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
				dm.pstmt.setInt(8, (int) user.getLastLogin_());
				dm.pstmt.setInt(9, (int) user.getUserId_());
				dm.executePstmt();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}

	@Override
	public void update(User toBeUpdatedUser) {
		// TODO Auto-generated method stub
		
	}

}
