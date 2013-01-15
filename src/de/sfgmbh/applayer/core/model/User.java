package de.sfgmbh.applayer.core.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

/**
 * Model for users
 * 
 * @author hannes
 *
 */
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
	private int newPasswordLength_;
	private boolean newPassword_;
	
	/**
	 * Create a new user object
	 */
	public User() {
		userId_ = -1;
		newPassword_ = false;
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
		this.login_ = this.cleanString(login_);
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
		this.fName_ = this.cleanString(fName_);
	}

	public String getlName_() {
		return lName_;
	}

	public void setlName_(String lName_) {
		this.lName_ = this.cleanString(lName_);
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
	 * @param password
	 */
	public void setPwHashAndSalt (String pw) {
		
		SecureRandom rand = new SecureRandom();
		
		this.salt_ = new BigInteger(32, rand).toString(32);
		
		String hashed = this.getSha256(this.salt_ + pw);
		
		if (hashed != null) {
			this.pass_ = hashed;
		}
		
		this.newPasswordLength_ = pw.length();
		this.newPassword_ = true;
	}
	
	/**
	 * Check if a plain text password string together with the users salt matches its hash
	 * @param password
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
	
	private String cleanString(String string) {
		String cleanString = string.trim();
		if (cleanString.startsWith("\"") || cleanString.startsWith("'")) {
			cleanString = cleanString.substring(1, cleanString.length());
		}
		if (cleanString.endsWith("\"") || cleanString.endsWith("'")) {
			cleanString = cleanString.substring(0, cleanString.length() - 1);
		}
		return cleanString;
	}
	
	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 */
	public boolean save() {
		if (AppModel.getInstance().getRepositoryUser().save(this)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Validates the user object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid user attributes
	 */
	public boolean validate() {
		boolean check = true;
		String message = "";
		Pattern validEmail = Pattern.compile("^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$");
		
		if (this.userId_ < -1 ) {
			message = message + "Der Benutzer hat keine gültige Benutzer ID<br/>";
			check = false;
		}
		if (this.login_.length() > 32 || this.login_.length() < 3) {
			message = message + "Die Benutzerkennung muss zwischen 3 und 32 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.mail_.length() > 64 ) {
			message = message + "Die E-Mail Adresse darf maximal 64 Zeichen lang sein!<br />";
			check = false;
		}
		if (!validEmail.matcher(this.mail_).matches()) {
			message = message + "Die E-Mail Adresse ist ungültig!<br />";
			check = false;
		}
		if (this.fName_.length() > 64 ) {
			message = message + "Der Vorname darf maximal 64 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.lName_.length() > 64 || this.lName_.length() < 1) {
			message = message + "Der Nachname zwischen 1 und 64 Zeichen lang sein!<br />";
			check = false;
		}
		if (!this.class_.equals("orga") && !this.class_.equals("lecturer") && !this.class_.equals("stud") ) {
			message = message + "Der Benutzer ist einer ungültigen Benutzerklasse zugeordnet!<br />";
			check = false;
		}
		if (this.class_.equals("lecturer") && this.chair_ == null) {
			message = message + "Einem Dozenten muss ein Lehrstuhl zugeordnet werden!<br />";
			check = false;
		}
		if (this.newPassword_ && this.newPasswordLength_ < 6) {
			message = message + "Ein neues Passwort muss mindestens 6 Zeichen lang sein!<br />";
			check = false;
		}
		
		if (check) {
			return true;
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException(message, "Fehler!");
			return false;
		}
	}

}
