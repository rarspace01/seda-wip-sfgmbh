package de.sfgmbh.datalayer.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class is used for the PostgreSQL DB connection
 * 
 * @author denishamann
 *         
 * @version 0.2
 * 
 */
public class DataManagerPostgreSql {
	private static DataManagerPostgreSql uniqueInstance_ = null;

	private java.sql.Connection conn;
	private Statement stmt;

	private DataManagerPostgreSql() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" +
			"141.13.6.76:5433" + "/" + "WIP-SFGmbH" + "?" + "user=" +
			"WIP-SFGmbH" + "&password=" + "n1qeiFhp" + "");
			stmt = conn.createStatement();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uniqueInstance_ = this;
	}

	public static DataManagerPostgreSql getInstance() {
		if (uniqueInstance_ == null) {
			new DataManagerPostgreSql();
		}
		return uniqueInstance_;
	}

	public void dispose() {
		uniqueInstance_ = null;
	}

	/**
	 * Executes the given SQL statement, which returns a single ResultSet object. 
	 * @param SQLString - To be executed
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet select(String SQLString) throws SQLException {
		
		System.out.println("SQL Qry: ["+SQLString+"]");
		
		ResultSet rs = null;

		try {

			rs = stmt.executeQuery(SQLString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *  Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement or an SQL statement that returns nothing, such as an SQL DDL statement.
	 * @param SQLString
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int execute(String SQLString) throws SQLException {
		
		System.out.println("SQL Qry: ["+SQLString+"]");
		
		int i = -1;

		try {

			i = stmt.executeUpdate(SQLString);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Connection getConnection() {
		return conn;
	}

}
