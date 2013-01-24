package de.sfgmbh.applayer.core.definitions;

/**
 * Interface for the {@link Chair} object
 * 
 * @author anna
 * @author hannes
 *
 */
public interface IntfChair {

	/**
	 * @return the chairId_
	 */
	public abstract int getChairId_();

	/**
	 * @param chairId the chairId_ to set
	 */
	public abstract void setChairId(int chairId);

	/**
	 * @return the chairName_
	 */
	public abstract String getChairName();

	/**
	 * @param chairName the chairName_ to set
	 */
	public abstract void setChairName(String chairName);

	/**
	 * @return the chairOwner_
	 */
	public abstract IntfUser getChairOwner();

	/**
	 * @param chairOwner the chairOwner_ to set
	 */
	public abstract void setChairOwner(IntfUser chairOwner);

	/**
	 * @return the buildingId_
	 */
	public abstract int getBuildingId();

	/**
	 * @param buildingId the buildingId_ to set
	 */
	public abstract void setBuildingId(int buildingId);

	/**
	 * @return the chairLevel_
	 */
	public abstract String getChairLevel();

	/**
	 * @param chairLevel the chairLevel_ to set
	 */
	public abstract void setChairLevel(String chairLevel);

	/**
	 * @return the faculty_
	 */
	public abstract String getFaculty();

	/**
	 * Set the faculty - currently only "WI" is supported
	 * @param faculty_ the faculty_ to set
	 */
	public abstract void setFaculty(String faculty_);

	/**
	 * @return the acronym_
	 */
	public abstract String getAcronym();

	/**
	 * @param acronym_ the acronym_ to set
	 */
	public abstract void setAcronym(String acronym_);

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