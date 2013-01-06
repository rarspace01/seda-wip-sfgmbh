package de.sfgmbh.applayer.core.model;

import java.util.HashMap;

import de.sfgmbh.datalayer.core.definitions.IntfDataRetrievable;

public class User implements IntfDataRetrievable {

	private int userId_;
	private String login_;
	private String pass_;
	private String salt_;
	private String mail_;
	private String class_;
	private String fName_;
	private String lName_;
	private long lastLogin_;

	public int getUserId_() {
		return userId_;
	}

	public void setUserId_(int userId_) {
		this.userId_ = userId_;
	}

	public String getLogin_() {
		return login_;
	}

	public void setLogin_(String login_) {
		this.login_ = login_;
	}

	public String getPass_() {
		return pass_;
	}

	public void setPass_(String pass_) {
		this.pass_ = pass_;
	}

	public String getSalt_() {
		return salt_;
	}

	public void setSalt_(String salt_) {
		this.salt_ = salt_;
	}

	public String getMail_() {
		return mail_;
	}

	public void setMail_(String mail_) {
		this.mail_ = mail_;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getfName_() {
		return fName_;
	}

	public void setfName_(String fName_) {
		this.fName_ = fName_;
	}

	public String getlName_() {
		return lName_;
	}

	public void setlName_(String lName_) {
		this.lName_ = lName_;
	}

	public long getLastLogin_() {
		return lastLogin_;
	}

	public void setLastLogin_(long lastLogin_) {
		this.lastLogin_ = lastLogin_;
	}

	public User(HashMap<String, Object> dataExchange) {
		this.setData(dataExchange);
	}

	public User() {

	}

	public void setData(HashMap<String, Object> dataExchange) {
		if (dataExchange.containsKey("userid")
				&& (int) dataExchange.get("userid") != 0) {
			userId_ = (int) dataExchange.get("userid");
		}

		if (dataExchange.containsKey("login")
				&& (String) dataExchange.get("login") != null
				&& (String) dataExchange.get("login") != "") {
			login_ = (String) dataExchange.get("login");
		}

		if (dataExchange.containsKey("pass")
				&& (String) dataExchange.get("pass") != null
				&& (String) dataExchange.get("pass") != "") {
			pass_ = (String) dataExchange.get("pass");
		}

		if (dataExchange.containsKey("salt")
				&& (String) dataExchange.get("salt") != null
				&& (String) dataExchange.get("salt") != "") {
			salt_ = (String) dataExchange.get("salt");
		}

		if (dataExchange.containsKey("class")
				&& (String) dataExchange.get("class") != null
				&& (String) dataExchange.get("class") != "") {
			class_ = (String) dataExchange.get("class");
		}

		if (dataExchange.containsKey("mail")
				&& (String) dataExchange.get("mail") != null
				&& (String) dataExchange.get("mail") != "") {
			mail_ = (String) dataExchange.get("mail");
		}

		if (dataExchange.containsKey("fname")
				&& (String) dataExchange.get("fname") != null
				&& (String) dataExchange.get("fname") != "") {
			fName_ = (String) dataExchange.get("fname");
		}

		if (dataExchange.containsKey("lname")
				&& (String) dataExchange.get("lname") != null
				&& (String) dataExchange.get("lname") != "") {
			lName_ = (String) dataExchange.get("lname");
		}

		if (dataExchange.containsKey("lastlogin")
				&& (int) dataExchange.get("lastlogin") != 0) {
			lastLogin_ = (int) dataExchange.get("lastlogin");
		}
	}

	public HashMap<String, Object> getData() {

		HashMap<String, Object> returnData = new HashMap<String, Object>();

		returnData.put("userid", this.userId_);
		returnData.put("login", this.login_);
		returnData.put("pass", this.pass_);
		returnData.put("salt", this.salt_);
		returnData.put("mail", this.mail_);
		returnData.put("class", this.class_);
		returnData.put("fname", this.fName_);
		returnData.put("lname", this.lName_);
		returnData.put("lastlogin", this.lastLogin_);

		return returnData;
	}

}
