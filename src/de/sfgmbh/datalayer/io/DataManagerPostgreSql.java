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
 */
public class DataManagerPostgreSql {
	private java.sql.Connection connection_;
	private Statement statement_;
	private PreparedStatement preparedStatement_;

	/**
	 * Create the data manager object (Non singleton instances of this object
	 * have to be allowed for some special cases. Remember to dispose
	 * connections from those with disposeNonSingleton().)
	 */
	public DataManagerPostgreSql() {
		DataManagerConfig dbconfig = new DataManagerConfig();
		try {
			Class.forName("org.postgresql.Driver");
			connection_ = DriverManager.getConnection("jdbc:postgresql://"
					+ dbconfig.getIp() + ":" + dbconfig.getPort() + "/"
					+ dbconfig.getDatabase(), dbconfig.getUsername(),
					dbconfig.getPassword());
			statement_ = connection_.createStatement();

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Bei der Verbindung mit dem Datenbank-Server ist ein Fehler aufgetreten.<br /><br />DataManagerPostgreSql-01:<br />"
									+ e.toString() + "<br /><br />UnivIS 2.0 kann ohne Datenbankverbindung nicht arbeiten. Bitte stellen Sie sicher, dass Sie sich zur Datenbank verbinden können (überprüfen Sie z. B. Ihre Internet-, Firewall oder VPN-Einstellungen) und versuchen Sie es erneut."),
							"Datenbank-Fehler!");
			System.exit(0);
		} catch (ClassNotFoundException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten.<br /><br />DataManagerPostgreSql-02:<br />" + e
									.toString()), "Fehler!");
		}
	}

	/**
	 * Dispatch the singleton data manger object
	 */
	public void dispose() {
		try {
			if (this.preparedStatement_ != null) {
				this.preparedStatement_.close();
			}
			if (this.statement_ != null) {
				this.statement_.close();
			}
			if (this.connection_ != null) {
				this.connection_.close();
			}
		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler in der Datenhaltung aufgetreten.<br /><br />DataManagerPostgreSql-032:<br />" + e
									.toString()), "Fehler!");
		}
	}

	/**
	 * Get the prepared statement object which is used by selectPstmt() or
	 * executePstmt() in this class to query the data base with prepared
	 * statements
	 * 
	 * @return the preparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return preparedStatement_;
	}

	/**
	 * Get the statement object for batch statements
	 * 
	 * @return pstmt - the preparedStatement
	 */
	public Statement getStatement() {
		return statement_;
	}

	/**
	 * Executes the given SQL statement, which returns a single ResultSet
	 * object.
	 * 
	 * @param SQLString
	 *            - To be executed
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet select(String SQLString) throws SQLException {

		ResultSet resultSet = null;

		try {

			resultSet = statement_.executeQuery(SQLString);

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataManagerPostgreSql-03) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		}

		return resultSet;
	}

	/**
	 * Executes the given SQL statement, which may be an INSERT, UPDATE, or
	 * DELETE statement or an SQL statement that returns nothing, such as an SQL
	 * DDL statement.
	 * 
	 * @param SQLString
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int execute(String SQLString) throws SQLException {

		int i = -1;

		try {

			i = statement_.executeUpdate(SQLString);

		} catch (SQLException e) {

			String dataBaseError = e.toString();
			String dataBaseErrorOutput = e.toString();

			if (dataBaseError.contains("FK_lecturerid")
					&& dataBaseError.contains("course")
					&& dataBaseError.contains("lecturer")
					&& dataBaseError.contains("userid")) {
				dataBaseErrorOutput = "Der Nutzer konnte nicht gelöscht werden, da ihm noch Veranstaltungen zugewiesen sind. Es können nur Benutzer aus dem System entfernt werden, die keine Veranstaltungen mehr haben.";
			}
			if (dataBaseError.contains("FK_chairid")
					&& dataBaseError.contains("lecturer")
					&& dataBaseError.contains("chair")
					&& dataBaseError.contains("chairid")) {
				dataBaseErrorOutput = "Ein Lehrstuhl kann nicht gelöscht werden, solange ihm noch Dozenten zugeordnet sind.";
			}
			if (dataBaseError.contains("FK_roomid")
					&& dataBaseError.contains("roomid")
					&& dataBaseError.contains("room")
					&& dataBaseError.contains("roomallocation")) {
				dataBaseErrorOutput = "Ein Raum kann nicht gelöscht werden, solange es noch Raumanfragen gibt, die ihm zugeordnet sind. Dies betrifft auch schon abgelehnte Raumanfragen - räumen Sie also ggf. die Raumanfragen in der Raumanfragenverwaltung auf.";
			}
			if (dataBaseError.contains("FK_courseid")
					&& dataBaseError.contains("course")
					&& dataBaseError.contains("courseid")
					&& dataBaseError.contains("roomallocation")) {
				dataBaseErrorOutput = "Eine Veranstaltung die noch Raumanfragen hat, kann nicht gelöscht werden. Widerrufen Sie alle Raumanfragen und bitten sie die Verwaltung, diese mit der Aufräumen-Funktion aus dem System zu entfernen.";
			}

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Diese Aktion kann nicht durchgeführt werden. Folgendes Problem besteht:<br /><br />" + dataBaseErrorOutput),
							"Datenbank-Fehler!");
		}

		return i;
	}

	/**
	 * Executes the given SQL statement, which may be an INSERT, UPDATE, or
	 * DELETE statement or an SQL statement that returns nothing, such as an SQL
	 * DDL statement.
	 * 
	 * @param SQLString
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 */
	public int silentexecute(String SQLString) throws SQLException {

		int i = -1;

		try {

			i = statement_.executeUpdate(SQLString);

		} catch (Exception e) {
			//
		}

		return i;
	}

	/**
	 * Prepare the data manager with a prepared statement that is about to be
	 * executed either by selectPstmt() or executePstmt()
	 * 
	 * @param prepareSqlString
	 * @return the prepared statement object
	 */
	public PreparedStatement prepare(String prepareSqlString) {

		try {
			this.preparedStatement_ = connection_
					.prepareStatement(prepareSqlString);
		} catch (Exception e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein unbekannter Fehler (DataManagerPostgreSql-05) in der Datenhaltung aufgetreten:<br /><br />" + e
									.toString()), "Fehler!");
		}

		return this.preparedStatement_;
	}

	/**
	 * Execute a select operation with the prepared statement object
	 * 
	 * @return a result set
	 * @throws SQLException
	 */
	public ResultSet selectPreparedStatement() throws SQLException {

		ResultSet resultSet = null;

		try {

			resultSet = this.getPreparedStatement().executeQuery();

		} catch (SQLException e) {

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Es ist ein SQL-Fehler (DataManagerPostgreSql-06) aufgetreten:<br /><br />" + e
									.toString()), "Datenbank-Fehler!");
		}

		return resultSet;
	}

	/**
	 * Execute a update or delete operation with the prepared statement object
	 * 
	 * @return true on success
	 * @throws SQLException
	 */
	public boolean executePreparedStatement() throws SQLException {

		try {

			this.getPreparedStatement().executeUpdate();

		} catch (SQLException e) {

			String dataBaseError = e.toString();
			String dataBaseErrorOutput = e.toString();

			if (dataBaseError.contains("FK_lecturerid")
					&& dataBaseError.contains("course")
					&& dataBaseError.contains("lecturer")
					&& dataBaseError.contains("userid")) {
				dataBaseErrorOutput = "Der Nutzer konnte nicht gelöscht werden, da ihm noch Veranstaltungen zugewiesen sind. Es können nur Benutzer aus dem System entfernt werden, die keine Veranstaltungen mehr haben.";
			}
			if (dataBaseError.contains("FK_chairid")
					&& dataBaseError.contains("lecturer")
					&& dataBaseError.contains("chair")
					&& dataBaseError.contains("chairid")) {
				dataBaseErrorOutput = "Ein Lehrstuhl kann nicht gelöscht werden, solange ihm noch Dozenten zugeordnet sind.";
			}
			if (dataBaseError.contains("FK_roomid")
					&& dataBaseError.contains("roomid")
					&& dataBaseError.contains("room")
					&& dataBaseError.contains("roomallocation")) {
				dataBaseErrorOutput = "Ein Raum kann nicht gelöscht werden, solange es noch Raumanfragen gibt, die ihm zugeordnet sind. Dies betrifft auch schon abgelehnte Raumanfragen - räumen Sie also ggf. die Raumanfragen in der Raumanfragenverwaltung auf.";
			}
			if (dataBaseError.contains("FK_courseid")
					&& dataBaseError.contains("course")
					&& dataBaseError.contains("courseid")
					&& dataBaseError.contains("roomallocation")) {
				dataBaseErrorOutput = "Eine Veranstaltung die noch Raumanfragen hat, kann nicht gelöscht werden. Widerrufen Sie alle Raumanfragen und bitten sie die Verwaltung, diese mit der Aufräumen-Funktion aus dem System zu entfernen.";
			}

			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Diese Aktion konnte nicht durchgeführt werden. Folgendes Problem ist aufgetreten:<br /><br />" + dataBaseErrorOutput),
							"Datenbank-Fehler!", "error");
			return false;
		}

		return true;
	}

}
