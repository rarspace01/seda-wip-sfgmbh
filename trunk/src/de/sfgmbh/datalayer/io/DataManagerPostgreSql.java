package de.sfgmbh.datalayer.io;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


/**
 * 
 * @author denis
 * Die Klasse DataManager dient zum Verwalten der Verbindungen mit der Datenbank
 */
public class DataManagerPostgreSql {
        private static DataManagerPostgreSql uniqueInstance = null;
        
        private static long lLastAccessed=0;
        
        private java.sql.Connection conn;
        private Statement stmt;
        
        private DataManagerPostgreSql() {
                try {
                        Class.forName("com.mysql.jdbc.Driver"); 
                        conn = DriverManager.getConnection("jdbc:psotgresql://"+"127.0.0.1"+"/"+"dbname"+"?" +
                                   "user="+"username"+"&password="+"pwd"+"");
                        stmt = conn.createStatement();
                        
                } catch (SQLException e) {
                        
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                uniqueInstance = this;
        }

        public static DataManagerPostgreSql getInstance()
        {
                        if(uniqueInstance== null||Calendar.getInstance().getTimeInMillis()>(lLastAccessed+(999*1000)))
                        {
                    	uniqueInstance=null;
                        new DataManagerPostgreSql();
                        }
                        lLastAccessed=Calendar.getInstance().getTimeInMillis();
                return uniqueInstance;  
        }
        
        public void dispose()
        {
                uniqueInstance=null;
        }
        
        //Methode für normale Select Operationen
        public ResultSet select(String SQLString) throws SQLException
        {
                ResultSet rs=null;
                
                try {
                        
                        rs=stmt.executeQuery(SQLString);

                } catch (SQLException e) {
                	e.printStackTrace();
                }
                return rs;
        }

        //Methode für alles außer select Operationen
        public int execute(String SQLString) throws SQLException
        {
                int i=-1;
                
                try {
                        
                        i=stmt.executeUpdate(SQLString);

                } catch (SQLException e) {
                	e.printStackTrace();
                }
                return i;
        }
        
    public Connection getConnection() {
        return conn;
    }

        
}
