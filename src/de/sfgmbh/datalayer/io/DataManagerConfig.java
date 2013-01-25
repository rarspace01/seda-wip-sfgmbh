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
	
	/**
	 * Create the configuration manager
	 */
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
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}else{
			createConfigFile();
		}
	}
	
	private boolean isValidProperties(Properties props){
		boolean isValid=false;
		if(props.getProperty("ip").matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")&&
				props.getProperty("port").matches("6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|5\\d{4}|[0-9]\\d{0,3}")&&
				props.getProperty("database").length()>0&&
				props.getProperty("username").length()>0&&
				props.getProperty("password").length()>0){
			isValid=true;
		}
		return isValid;
	}
	
	private void createConfigFile(){
		
		this.dbproperties_.setProperty("ip", "141.13.6.76");
		this.dbproperties_.setProperty("port", "5433");
		this.dbproperties_.setProperty("database", "WIP-SFGmbH");
		this.dbproperties_.setProperty("username", "WIP-SFGmbH");
		this.dbproperties_.setProperty("password", "n1qeiFhp");
		
		try {
			this.dbproperties_.store(new FileOutputStream(new File(fileName_)), "UNIVIS 2.0 config file");
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
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
