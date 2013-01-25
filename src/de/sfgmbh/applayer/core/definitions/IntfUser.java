package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.User;

/**
 * Interface for {@link User} object
 * 
 * @author denis
 * 
 */
public interface IntfUser {

	public abstract int getUserId_();

	public abstract void setUserId_(int userId_);

	public abstract String getLogin_();

	public abstract void setLogin_(String login_);

	public abstract String getPass_();

	public abstract void setPass_(String pass_);

	public abstract String getSalt_();

	public abstract void setSalt_(String salt_);

	public abstract String getMail_();

	public abstract void setMail_(String mail_);

	public abstract String getClass_();

	public abstract void setClass_(String class_);

	public abstract String getFirstName_();

	public abstract void setFirstName_(String fName_);

	public abstract String getlName_();

	public abstract void setlName_(String lName_);

	public abstract long getLastLogin_();

	public abstract void setLastLogin_(long lastLogin_);

	public abstract boolean isDisabled_();

	public abstract void setDisabled_(boolean disabled_);

	public abstract IntfChair getChair_();

	public abstract void setChair_(IntfChair chair_);

	/**
	 * Set hash for a plain text password string salted by a random string
	 * 
	 * @param password
	 */
	public abstract void setPasswordHashAndSalt(String password);

	/**
	 * Check if a plain text password string together with the users salt
	 * matches its hash
	 * 
	 * @param password
	 * @return true if the submitted plain text password is correct
	 */
	public abstract boolean checkPassword(String password);

	/**
	 * Save this user object in the DB (this will update a database entry if
	 * there is already one and create one if there is none)
	 */
	public abstract boolean save();

	/**
	 * Validates the user object, calls an info dialog when one check fails and
	 * returns true if all checks are passed
	 * 
	 * @return true if all check attributes are valid user attributes
	 */
	public abstract boolean validate();

}