package de.sfgmbh.init;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * class for reseting the database based on the included sql file
 * @author denis
 *
 */
public class ResetDb {
	// List with Strings
	List<String> sqlStatements=new ArrayList<String>();
	
	/**
	 * Construct the class
	 */
	public ResetDb() {
	}
	
	/***
	 * starts the "factory" reset
	 */
	public void factoryReset(){
		runSqlScript();
		System.out.println("resetted Database to initial values");
		
	}

	// defines the explicit method for running the sql File
	private void runSqlScript() {
		
		String fileLine="";
		String puffer="";
		
		// use a buffered Stream for the resource - we can't access the sql file directly
		BufferedInputStream bufferedStream;
		bufferedStream=new BufferedInputStream(this.getClass().getResourceAsStream("/de/sfgmbh/init/reset.sql"));
		// transform the  
		InputStreamReader inputstream=new InputStreamReader(bufferedStream);
		BufferedReader bufferedReader  = new BufferedReader(inputstream);
		
		try {
			while((fileLine=bufferedReader.readLine()) != null){
				if(fileLine.length()>0&&(!fileLine.contains("--"))){
					if(fileLine.contains(";")){
						puffer+=fileLine;
						//System.out.println("["+puffer+"]");
						sqlStatements.add(puffer);
						puffer="";
					}else{
						puffer+=fileLine;
					}
				
				}
			}
		} catch (IOException e) {
			//
		}
		try {
			DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
			
			for(int i=0;i<sqlStatements.size();i++){
				if(sqlStatements.get(i)!=null){
					dataManager.silentexecute(sqlStatements.get(i));
				}
			}
			
			dataManager.getStatement().executeBatch();
			
			dataManager.dispose();
		} catch (BatchUpdateException e) {
			//ignore errors on resetting
			//
		} catch (SQLException e) {
			//
		}	
			
		
	}
	
}
