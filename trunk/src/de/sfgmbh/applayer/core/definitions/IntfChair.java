package de.sfgmbh.applayer.core.definitions;


public interface IntfChair {

	/**
	 * @return the chairId_
	 */
	public abstract int getChairId_();

	/**
	 * @param chairId_ the chairId_ to set
	 */
	public abstract void setChairId_(int chairId_);

	/**
	 * @return the chairName_
	 */
	public abstract String getChairName_();

	/**
	 * @param chairName_ the chairName_ to set
	 */
	public abstract void setChairName_(String chairName_);

	/**
	 * @return the chairOwner_
	 */
	public abstract IntfUser getChairOwner_();

	/**
	 * @param chairOwner_ the chairOwner_ to set
	 */
	public abstract void setChairOwner_(IntfUser chairOwner_);

	/**
	 * @return the buildingId_
	 */
	public abstract int getBuildingId_();

	/**
	 * @param buildingId_ the buildingId_ to set
	 */
	public abstract void setBuildingId_(int buildingId_);

	/**
	 * @return the chairLevel_
	 */
	public abstract String getChairLevel_();

	/**
	 * @param chairLevel_ the chairLevel_ to set
	 */
	public abstract void setChairLevel_(String chairLevel_);

	/**
	 * @return the faculty_
	 */
	public abstract String getFaculty_();

	/**
	 * Set the faculty - currently only "WI" is supported
	 * @param faculty_ the faculty_ to set
	 */
	public abstract void setFaculty_(String faculty_);

	/**
	 * @return the acronym_
	 */
	public abstract String getAcronym_();

	/**
	 * @param acronym_ the acronym_ to set
	 */
	public abstract void setAcronym_(String acronym_);

	/**
	 * Validates the chair object, calls an info dialog when one check fails and returns true if all checks are passed
	 * @return true if all check attributes are valid
	 */
	public abstract boolean validate();

	/**
	 * Save this chair in the DB
	 * @return true on success
	 */
	public abstract boolean save();

}