package de.sfgmbh.applayer.core.model;


/**
 * Chair model class
 * 
 * @author hannes
 * @author anna
 *
 */
public class Chair implements IntfChair {

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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getChairId_()
	 */
	@Override
	public int getChairId_() {
		return chairId_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setChairId_(int)
	 */
	@Override
	public void setChairId_(int chairId_) {
		this.chairId_ = chairId_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getChairName_()
	 */
	@Override
	public String getChairName_() {
		return chairName_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setChairName_(java.lang.String)
	 */
	@Override
	public void setChairName_(String chairName_) {
		this.chairName_ = chairName_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getChairOwner_()
	 */
	@Override
	public User getChairOwner_() {
		return chairOwner_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setChairOwner_(de.sfgmbh.applayer.core.model.User)
	 */
	@Override
	public void setChairOwner_(User chairOwner_) {
		this.chairOwner_ = chairOwner_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getBuildingId_()
	 */
	@Override
	public int getBuildingId_() {
		return buildingId_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setBuildingId_(int)
	 */
	@Override
	public void setBuildingId_(int buildingId_) {
		this.buildingId_ = buildingId_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getChairLevel_()
	 */
	@Override
	public String getChairLevel_() {
		return chairLevel_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setChairLevel_(java.lang.String)
	 */
	@Override
	public void setChairLevel_(String chairLevel_) {
		this.chairLevel_ = chairLevel_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getFaculty_()
	 */
	@Override
	public String getFaculty_() {
		return faculty_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setFaculty_(java.lang.String)
	 */
	@Override
	public void setFaculty_(String faculty_) {
		if (faculty_.equals("WI")) {
			this.faculty_ = faculty_;
		} else {
			this.faculty_ = "WI";
		}
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#getAcronym_()
	 */
	@Override
	public String getAcronym_() {
		return acronym_;
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#setAcronym_(java.lang.String)
	 */
	@Override
	public void setAcronym_(String acronym_) {
		this.acronym_ = acronym_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#validate()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfChair#save()
	 */
	@Override
	public boolean save() {
		return AppModel.getInstance().getRepositoryChair().save(this);
	}
	
	
	
	
}
