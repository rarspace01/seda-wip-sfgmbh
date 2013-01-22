package de.sfgmbh.datalayer.io;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Class is used for the PostgreSQL DB connection
 * 
 * @author denis
 * @author hannes
 * 
 */
public class DataManagerPostgreSql {
	private static DataManagerPostgreSql uniqueInstance_ = null;

	private java.sql.Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	/**
	 * Create the data manager object
	 */
	private DataManagerPostgreSql() {
		DataManagerConfig dbconfig=new DataManagerConfig();
		System.out.println("[D-Layer] Creating Connection to: ["+dbconfig.getIp()+":"+dbconfig.getPort()+"]");
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" +
			dbconfig.getIp()+":"+dbconfig.getPort()+ "/" + dbconfig.getDatabase(), 
			dbconfig.getUsername(),dbconfig.getPassword());
			stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Bei der Verbindung mit dem Datenbank-Server ist ein Fehler aufgetreten.<br /><br />DataManagerPostgreSql-01:<br />" + e.toString() + 
					"<br /><br />UnivIS 2.0 kann ohne Datenbankverbindung nicht arbeiten. Bitte stellen Sie sicher, dass Sie sich zur Datenbank verbinden können (überprüfen Sie z. B. Ihre Internet-, Firewall oder VPN-Einstellungen) und versuchen Sie es erneut."), "Datenbank-Fehler!");
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten.<br /><br />DataManagerPostgreSql-02:<br />" + e.toString()), "Fehler!");
		}
		
		
		
	}

	/**
	 * Get a singleton data manger object
	 * @return {@link DataManagerPostgreSql}
	 */
	public static DataManagerPostgreSql getInstance() {
		if (uniqueInstance_ == null) {
			uniqueInstance_ = new DataManagerPostgreSql();
		}
		return uniqueInstance_;
	}

	/**
	 * Dispatch the singleton data manger object
	 */
	public void dispose() {
		try {
			if(this.pstmt!=null){
			this.pstmt.close();
			}
			if(this.stmt!=null){
			this.stmt.close();
			}
			if(this.conn!=null){
			this.conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uniqueInstance_ = null;
	}
	
	/**
	 * Get the prepared statement object which is used by selectPstmt() or executePstmt() in this class to query the data base with prepared statements
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
	
	/**
	 * Prepare the data manager with a prepared statement that is about to be executed either by selectPstmt() or executePstmt()
	 * @param prepareSqlString
	 * @return the prepared statement object
	 * 
	 */
	public PreparedStatement prepare(String prepareSqlString) {
		
		try {
			this.pstmt = conn.prepareStatement(prepareSqlString);
		} catch (Exception e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein unbekannter Fehler (DataManagerPostgreSql-05) in der Datenhaltung aufgetreten:<br /><br />" + e.toString()), "Fehler!");
		}
		
		return this.pstmt;
	}
	
	/**
	 * Execute a select operation with the prepared statement object
	 * @return a result set
	 * @throws SQLException
	 */
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
	
	/**
	 * Execute a update or delete operation with the prepared statement object
	 * @return true on success
	 * @throws SQLException
	 */
	public boolean executePstmt()  throws SQLException {
		
		try {
			long a, b, diff;
			a = (long) System.currentTimeMillis();
			this.getPreparedStatement().executeUpdate();
			b = (long) System.currentTimeMillis();
			diff = b - a;
			System.out.println(diff + "ms for SQL PS execute: [" + this.getPreparedStatement().toString() +"]");
		} catch (SQLException e) {
			e.printStackTrace();
			DataModel.getInstance().getExceptionsHandler().setNewException(("Es ist ein SQL-Fehler (DataManagerPostgreSql-07) aufgetreten:<br /><br />" + e.toString()), "Datenbank-Fehler!");
			return false;
		}
		
		return true;
	}

}
