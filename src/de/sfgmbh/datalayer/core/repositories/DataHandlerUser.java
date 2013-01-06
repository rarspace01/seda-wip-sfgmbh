package de.sfgmbh.datalayer.core.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.definitions.IntfDataUser;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

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
	public void save(User toBeSavedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User toBeUpdatedUser) {
		// TODO Auto-generated method stub
		
	}

}
