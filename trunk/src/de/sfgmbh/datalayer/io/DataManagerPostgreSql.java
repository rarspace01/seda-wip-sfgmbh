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
	private PreparedStatement pstmt;

	public DataManagerPostgreSql() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" +
			"141.13.6.76:5433" + "/" + "WIP-SFGmbH" + "?" + "user=" +
			"WIP-SFGmbH" + "&password=" + "n1qeiFhp" + "");
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Bei der Verbindung mit dem Datenbank-Server ist ein Fehler aufgetreten.<br /><br />DataManagerPostgreSql-01:<br />" + e.toString() + 
					"<br /><br />UniVis 2.0 kann ohne Datenbankverbindung nicht arbeiten. Bitte stellen Sie sicher, dass Sie sich zur Datenbank verbinden können (überprüfen Sie z. B. Ihre Internet-, Firewall oder VPN-Einstellungen) und versuchen Sie es erneut."), "Datenbank-Fehler!");
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten.<br /><br />DataManagerPostgreSql-02:<br />" + e.toString()), "Fehler!");
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
	 * @return the preparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return pstmt;
	}

	/**
	 * Executes the given SQL statement, which returns a single ResultSet object. 
	 * @param SQLString - To be executed
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet select(String SQLString) throws SQLException {
		
		ResultSet rs = null;

		try {
			
			long a, b, diff;
			a = (long) System.currentTimeMillis();
			rs = stmt.executeQuery(SQLString);
			b = (long) System.currentTimeMillis();
			diff = b - a;
			System.out.println(diff + "ms for SQL select: ["+SQLString+"]");

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-03) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
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
		
		int i = -1;

		try {
			
			long a, b, diff;
			a = (long) System.currentTimeMillis();
			i = stmt.executeUpdate(SQLString);
			b = (long) System.currentTimeMillis();
			diff = b - a;
			System.out.println(diff + "ms for SQL execute: ["+SQLString+"]");

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-04) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
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
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataManagerPostgreSql-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return this.pstmt;
		
	}
	
	// Execute prepared statement
	public ResultSet selectPstmt()  throws SQLException {
		
		ResultSet rs = null;
		
		try {
			long a, b, diff;
			a = (long) System.currentTimeMillis();
			rs = this.getPreparedStatement().executeQuery();
			b = (long) System.currentTimeMillis();
			diff = b - a;
			System.out.println(diff + "ms for SQL PS select: [" + this.getPreparedStatement().toString() +"]");
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-06) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		}
		
		return rs;
	}
	
	public int executePstmt()  throws SQLException {
		
		int i = -1;
		
		try {
			long a, b, diff;
			a = (long) System.currentTimeMillis();
			i = this.getPreparedStatement().executeUpdate();
			b = (long) System.currentTimeMillis();
			diff = b - a;
			System.out.println(diff + "ms for SQL PS execute: [" + this.getPreparedStatement().toString() +"]");
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
		}
		
		return i;
	}

}
