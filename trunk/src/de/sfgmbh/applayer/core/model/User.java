package de.sfgmbh.applayer.core.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.definitions.IntfUser;

/**
 * Model for users
 * 
 * @author hannes
 *
 */
public class User implements IntfUser {

	private int userId_;
	private String login_;
	private String pass_;
	private String salt_;
	private String mail_;
	private String class_;
	private String firstName_;
	private String lastName_;
	private long lastLogin_;
	private boolean disabled_;
	private IntfChair chair_;
	private int newPasswordLength_;
	private boolean newPassword_;
	
	/**
	 * Create a default user
	 */
	public User() {
		userId_ = -1;
		newPassword_ = false;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getUserId_()
	 */
	@Override
	public int getUserId_() {
		return userId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setUserId_(int)
	 */
	@Override
	public void setUserId_(int userId_) {
		this.userId_ = userId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getLogin_()
	 */
	@Override
	public String getLogin_() {
		return login_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setLogin_(java.lang.String)
	 */
	@Override
	public void setLogin_(String login_) {
		this.login_ = this.cleanString(login_);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getPass_()
	 */
	@Override
	public String getPass_() {
		return pass_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setPass_(java.lang.String)
	 */
	@Override
	public void setPass_(String pass_) {
		this.pass_ = pass_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getSalt_()
	 */
	@Override
	public String getSalt_() {
		return salt_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setSalt_(java.lang.String)
	 */
	@Override
	public void setSalt_(String salt_) {
		this.salt_ = salt_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getMail_()
	 */
	@Override
	public String getMail_() {
		return mail_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setMail_(java.lang.String)
	 */
	@Override
	public void setMail_(String mail_) {
		this.mail_ = mail_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getClass_()
	 */
	@Override
	public String getClass_() {
		return class_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setClass_(java.lang.String)
	 */
	@Override
	public void setClass_(String class_) {
		if (class_.equals("Verwaltung") || class_.equals("orga")) {
			this.class_ = "orga";
		} else if (class_.equals("Dozenten") || class_.equals("Dozent") || class_.equals("lecturer")) {
			this.class_ = "lecturer";
		} else if (class_.equals("Studenten") || class_.equals("Student") || class_.equals("stud")) {
			this.class_ = "stud";
		}
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getfName_()
	 */
	@Override
	public String getFirstName_() {
		return firstName_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setfName_(java.lang.String)
	 */
	@Override
	public void setFirstName_(String fName_) {
		this.firstName_ = this.cleanString(fName_);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getlName_()
	 */
	@Override
	public String getlName_() {
		return lastName_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setlName_(java.lang.String)
	 */
	@Override
	public void setlName_(String lName_) {
		this.lastName_ = this.cleanString(lName_);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getLastLogin_()
	 */
	@Override
	public long getLastLogin_() {
		return lastLogin_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setLastLogin_(long)
	 */
	@Override
	public void setLastLogin_(long lastLogin_) {
		this.lastLogin_ = lastLogin_;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#isDisabled_()
	 */
	@Override
	public boolean isDisabled_() {
		return disabled_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setDisabled_(boolean)
	 */
	@Override
	public void setDisabled_(boolean disabled_) {
		this.disabled_ = disabled_;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#getChair_()
	 */
	@Override
	public IntfChair getChair_() {
		return chair_;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setChair_(de.sfgmbh.applayer.core.definitions.IntfChair)
	 */
	@Override
	public void setChair_(IntfChair chair_) {
		this.chair_ = chair_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#setPwHashAndSalt(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#checkPw(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#save()
	 */
	@Override
	public boolean save() {
		if (AppModel.getInstance().getRepositoryUser().save(this)) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfUser#validate()
	 */
	@Override
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
		if (this.firstName_.length() > 64 ) {
			message = message + "Der Vorname darf maximal 64 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.lastName_.length() > 64 || this.lastName_.length() < 1) {
			message = message + "Der Nachname muss zwischen 1 und 64 Zeichen lang sein!<br />";
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
		if (this.class_.equals("orga") && this.chair_ != null) {
			message = message + "Ein Verwaltungsmitglied kann keinen Lehrstuhl haben!<br />";
			check = false;
		}
		if (this.newPassword_ && this.newPasswordLength_ < 8) {
			message = message + "Ein neues Passwort muss mindestens 8 Zeichen lang sein!<br />";
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
