package de.sfgmbh.datalayer.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * class for getting db settings
 * @author denis
 *
 */
public class DataManagerConfig {

	private Properties dbproperties_=new Properties();
	
	private String fileName_="db.cfg";
	
	public DataManagerConfig() {
		loadSettings();
	}
	
	private void loadSettings(){
		getPropertiesFromFile();
		if(!isValidProperties(this.dbproperties_)){
			createConfigFile();
		}
	}
	
	private void getPropertiesFromFile(){
		File f = new File(fileName_);
		if(f.exists()) {
			try {
				BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fileName_));
				dbproperties_.load(stream);
				stream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			createConfigFile();
		}
	}
	
	private boolean isValidProperties(Properties props){
		boolean isValid=false;
		if(props.getProperty("ip").matches("")&&
				props.getProperty("port").matches("[0-9]")&&
				props.getProperty("database").matches("")&&
				props.getProperty("username").matches("")&&
				props.getProperty("password").matches("")){
			isValid=true;
		}
		return isValid;
	}
	
	private void createConfigFile(){
		
		System.out.println("created default db config");
		
		this.dbproperties_.setProperty("ip", "141.13.6.76");
		this.dbproperties_.setProperty("port", "5433");
		this.dbproperties_.setProperty("database", "WIP-SFGmbH");
		this.dbproperties_.setProperty("username", "WIP-SFGmbH");
		this.dbproperties_.setProperty("password", "n1qeiFhp");
		
		try {
			this.dbproperties_.store(new FileOutputStream(new File(fileName_)), "UNIVIS 2.0 config file");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the ip_
	 */
	public String getIp() {
		if(dbproperties_.getProperty("ip") == null){
			loadSettings();
		}
		return dbproperties_.getProperty("ip");
	}
	/**
	 * @return the port_
	 */
	public int getPort() {
		int port=-1;
		if(dbproperties_.getProperty("port") == null){
			loadSettings();
		}
		port=Integer.parseInt(dbproperties_.getProperty("port"));
		return port;
	}
	/**
	 * @return the username_
	 */
	public String getUsername() {
		if(dbproperties_.getProperty("username") == null){
			loadSettings();
		}
		return dbproperties_.getProperty("username");
	}
	/**
	 * @return the password_
	 */
	public String getPassword() {
		if(dbproperties_.getProperty("password") == null){
			loadSettings();
		}
		return dbproperties_.getProperty("password");
	}
	/**
	 * @return the database_
	 */
	public String getDatabase() {
		if(dbproperties_.getProperty("database") == null){
			loadSettings();
		}
		return dbproperties_.getProperty("database");
	}

	
	
}
