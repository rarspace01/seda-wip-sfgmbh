package de.sfgmbh.applayer.core.model;


/**
 * Chair model class
 * 
 * @author hannes
 * @author anna
 *
 */
public class Chair {

	private int chairId_;
	private String chairName_;
	private User chairOwner_;
	private int buildingId_;
	private String chairLevel_;
	private String faculty_;
	private String acronym_;
	
	/**
	 * Create a default chair object
	 */
	public Chair() {
		this.chairId_ = -1;
		this.buildingId_ = -1;
	}
	
	/**
	 * @return the chairId_
	 */
	public int getChairId_() {
		return chairId_;
	}
	/**
	 * @param chairId_ the chairId_ to set
	 */
	public void setChairId_(int chairId_) {
		this.chairId_ = chairId_;
	}
	/**
	 * @return the chairName_
	 */
	public String getChairName_() {
		return chairName_;
	}
	/**
	 * @param chairName_ the chairName_ to set
	 */
	public void setChairName_(String chairName_) {
		this.chairName_ = chairName_;
	}
	/**
	 * @return the chairOwner_
	 */
	public User getChairOwner_() {
		return chairOwner_;
	}
	/**
	 * @param chairOwner_ the chairOwner_ to set
	 */
	public void setChairOwner_(User chairOwner_) {
		this.chairOwner_ = chairOwner_;
	}
	/**
	 * @return the buildingId_
	 */
	public int getBuildingId_() {
		return buildingId_;
	}
	/**
	 * @param buildingId_ the buildingId_ to set
	 */
	public void setBuildingId_(int buildingId_) {
		this.buildingId_ = buildingId_;
	}
	/**
	 * @return the chairLevel_
	 */
	public String getChairLevel_() {
		return chairLevel_;
	}
	/**
	 * @param chairLevel_ the chairLevel_ to set
	 */
	public void setChairLevel_(String chairLevel_) {
		this.chairLevel_ = chairLevel_;
	}
	/**
	 * @return the faculty_
	 */
	public String getFaculty_() {
		return faculty_;
	}
	/**
	 * Set the faculty - currently only "WI" is supported
	 * @param faculty_ the faculty_ to set
	 */
	public void setFaculty_(String faculty_) {
		if (faculty_.equals("WI")) {
			this.faculty_ = faculty_;
		} else {
			this.faculty_ = "WI";
		}
	}
	/**
	 * @return the acronym_
	 */
	public String getAcronym_() {
		return acronym_;
	}
	/**
	 * @param acronym_ the acronym_ to set
	 */
	public void setAcronym_(String acronym_) {
		this.acronym_ = acronym_;
	}

	/**
	 * Validates the chair object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid
	 */
	public boolean validate() {
		boolean check = true;
		String message = "";
		
		if (this.chairId_ < -1 ) {
			message = message + "Der Lehrstuhl hat keine gültige ID!<br/>";
			check = false;
		}
		if (this.acronym_.length() > 32 || this.acronym_.length() < 2) {
			message = message + "Die Kurzbezeichnung muss zwischen 2 und 32 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.chairName_.length() > 128 || this.chairName_.length() < 2) {
			message = message + "Der Name muss zwischen 2 und 128 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.chairLevel_.length() > 12 || this.chairLevel_.length() < 0) {
			message = message + "Das Stockwerk muss zwischen 0 und 12 Zeichen lang sein!<br />";
			check = false;
		}
		if (this.chairOwner_ != null) {
			if (this.chairOwner_.getChair_().getChairId_() != this.chairId_) {
				message = message + "Der Inhaber muss zunächst diesem Lehrstuhl zugeordnet werden. (Ein neuer Lehrstuhl muss daher zunächst ohne Inhaber erstellt werden.)<br />";
				check = false;
			}
		}
		if (this.faculty_.length() > 32 || this.faculty_.length() < 2) {
			message = message + "Die Fakultät muss zwischen 2 und 32 Zeichen lang sein!<br />";
			check = false;
		}
		
		if (check) {
			return true;
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException(message, "Fehler!");
			return false;
		}
	}
	
	/**
	 * Save this chair in the DB
	 * @return true on success
	 */
	public boolean save() {
		return AppModel.getInstance().getRepositoryChair().save(this);
	}
	
	
	
	
}
