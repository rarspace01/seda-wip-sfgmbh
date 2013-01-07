package de.sfgmbh.applayer.core.model;

public class Chair {

	private int chairId_;
	private String chairName_;
	private User chairOwner_;
	private String mainChair_;
	private String mainLevel_;
	private int buildingid_;
	public int getChairID_() {
		return chairId_;
	}
	public void setChairId_(int chairId_) {
		this.chairId_ = chairId_;
	}
	public String getChairName_() {
		return chairName_;
	}
	public void setChairName_(String chairName_) {
		this.chairName_ = chairName_;
	}
	public User getChairOwner_() {
		return chairOwner_;
	}
	public void setChairOwner_(User chairOwner_) {
		this.chairOwner_ = chairOwner_;
	}
	public String getMainChair_() {
		return mainChair_;
	}
	public void setMainChair_(String mainChair_) {
		this.mainChair_ = mainChair_;
	}
	public String getMainLevel_() {
		return mainLevel_;
	}
	public void setMainLevel_(String mainLevel_) {
		this.mainLevel_ = mainLevel_;
	}
	public int getBuildingid_() {
		return buildingid_;
	}
	public void setBuildingid_(int buildingid_) {
		this.buildingid_ = buildingid_;
	}
	
	
	
}
