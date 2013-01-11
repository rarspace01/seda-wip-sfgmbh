package de.sfgmbh.datalayer.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.sfgmbh.datalayer.core.model.DataModel;

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
	public PreparedStatement pstmt;

	public DataManagerPostgreSql() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" +
			"141.13.6.76:5433" + "/" + "WIP-SFGmbH" + "?" + "user=" +
			"WIP-SFGmbH" + "&password=" + "n1qeiFhp" + "");
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Bei der Verbindung mit dem Datenbank-Server ist ein Fehler aufgetreten.<br /><br />DataManagerPostgreSql-01:<br />" + e.toString()), "Datenbank-Fehler!");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten.<br /><br />DataManagerPostgreSql-02:<br />" + e.toString()), "Fehler!");
		}
		// For testing purpose deactivated - if nothing bad happens it can stay this way; if not remember to set the constructor back to private
		// uniqueInstance_ = this;
	}

	public static DataManagerPostgreSql getInstance() {
		if (uniqueInstance_ == null) {
			uniqueInstance_ = new DataManagerPostgreSql();
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
		
		System.out.println("SQL select: ["+SQLString+"]");
		
		ResultSet rs = null;

		try {

			rs = stmt.executeQuery(SQLString);

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
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
		
		System.out.println("SQL execute: ["+SQLString+"]");
		
		int i = -1;

		try {

			i = stmt.executeUpdate(SQLString);

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		}
		return i;
	}

	public Connection getConnection() {
		return conn;
	}
	
	// Prepare statement
	public PreparedStatement prepare(String SqlString) {
		
		try {
			this.pstmt = conn.prepareStatement(SqlString);
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein unbekannter Fehler (DataManagerPostgreSql-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return this.pstmt;
		
	}
	
	// Execute prepared statement
	public ResultSet selectPstmt()  throws SQLException {
		
		ResultSet rs = null;
		
		try {
			rs = this.pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-06) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		}
		
		return rs;
	}
	
	public int executePstmt()  throws SQLException {
		
		int i = -1;
		
		try {
			i = this.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().dataExcaptions.setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		}
		
		return i;
	}

}
