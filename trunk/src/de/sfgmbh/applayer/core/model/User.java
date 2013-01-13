package de.sfgmbh.applayer.core.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

public class User {

	private int userId_;
	private String login_;
	private String pass_;
	private String salt_;
	private String mail_;
	private String class_;
	private String fName_;
	private String lName_;
	private long lastLogin_;
	private boolean disabled_;
	private Chair chair_;
	
	public User() {
		userId_=-1;
	}

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
		if (class_.equals("Verwaltung") || class_.equals("orga")) {
			this.class_ = "orga";
		} else if (class_.equals("Dozenten") || class_.equals("Dozent") || class_.equals("lecturer")) {
			this.class_ = "lecturer";
		} else if (class_.equals("Studenten") || class_.equals("Student") || class_.equals("stud")) {
			this.class_ = "stud";
		}
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
	
	public boolean isDisabled_() {
		return disabled_;
	}

	public void setDisabled_(boolean disabled_) {
		this.disabled_ = disabled_;
	}
	
	public Chair getChair_() {
		return chair_;
	}
	
	public void setChair_(Chair chair_) {
		this.chair_ = chair_;
	}

	/**
	 * Set hash for a plain text password string salted by a random string
	 * @param pw
	 */
	public void setPwHashAndSalt (String pw) {
		
		SecureRandom rand = new SecureRandom();
		
		this.salt_ = new BigInteger(32, rand).toString(32);
		
		String hashed = this.getSha256(this.salt_ + pw);
		
		if (hashed != null) {
			this.pass_ = hashed;
		}
	}
	
	/**
	 * Check if a plain text password string together with the users salt matches its hash
	 * @param pw
	 * @return true if the submitted plain text password is correct
	 */
	public boolean checkPw (String pw) {
		
		String checkPhrase = this.salt_ + pw;
		String checksum = this.getSha256(checkPhrase);
		
		if (checksum.equals(this.pass_)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Get the SHA-256 hash for any string
	 * @param string
	 * @return the SHA-256 hash value for the submitted string
	 */
	private String getSha256 (String string) {
		
		MessageDigest md ;
		
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(string.getBytes());
			
		    StringBuffer sb = new StringBuffer();
		    for(byte b : hash) {
		    	sb.append(String.format("%02x", b));
		    }

		    return sb.toString();
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 */
	public boolean save() {
		if (AppModel.getInstance().repositoryUser.save(this)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Validates the user object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid user attributes
	 */
	public boolean validate() {
		Pattern validEmail = Pattern.compile("^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$");
		
		if (this.userId_ < -1 ) {
			AppModel.getInstance().appExcaptions.setNewException("Das ist keine gülite Benutzer ID", "Fehler!");
			return false;
		}
		if (this.login_.length() > 32 && this.login_.length() < 3) {
			AppModel.getInstance().appExcaptions.setNewException("Die Benutzerkennung muss zwischen 3 und 6 Zeichen lang sein!", "Fehler!");
			return false;
		}
		if (this.mail_.length() > 64 ) {
			AppModel.getInstance().appExcaptions.setNewException("Die E-Mail Adresse darf maximal 64 Zeichen lang sein!", "Fehler!");
			return false;
		}
		if (!validEmail.matcher(this.mail_).matches()) {
			AppModel.getInstance().appExcaptions.setNewException("Die E-Mail Adresse ist ungültig!", "Fehler!");
			return false;
		}
		if (this.fName_.length() > 64 ) {
			AppModel.getInstance().appExcaptions.setNewException("Der Vorname darf maximal 64 Zeichen lang sein!", "Fehler!");
			return false;
		}
		if (this.lName_.length() > 64 ) {
			AppModel.getInstance().appExcaptions.setNewException("Der Nachname darf maximal 64 Zeichen lang sein!", "Fehler!");
			return false;
		}
		if (!this.class_.equals("orga") && !this.class_.equals("lecturer") && !this.class_.equals("stud") ) {
			AppModel.getInstance().appExcaptions.setNewException("Der Benutzer ist einer ungültigen Benutzerklasse zugeordnet!", "Fehler!");
			return false;
		}
		if (this.class_.equals("lecturer") && this.chair_ == null) {
			AppModel.getInstance().appExcaptions.setNewException("Einem Dozenten muss ein Lehrstuhl zugeordnet werden!", "Fehler!");
			return false;
		}
		
		return true;
	}

}
