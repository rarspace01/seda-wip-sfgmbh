package de.sfgmbh.applayer.core.model;


public class Room{

	private int roomId_;
	private String roomNumber_;
	private int buildingId_;
	private String level_;
	private int seats_;
	private int pcseats_;
	private int beamer_;
	private int visualizer_;
	private int overheads_;
	private int chalkboards_;
	private int whiteboards_;
	
	public Room(){
		this.roomId_ = -1;
	}
	public Room(int buildingId) {

		buildingId_=buildingId;
		
		roomNumber_="";
		level_="";
		seats_=0;
		pcseats_=0;
		beamer_=0;
		visualizer_=0;
		overheads_=0;
		chalkboards_=0;
		whiteboards_=0;
		
	}
	public Room(int roomID, int buildingId) {

		roomId_=roomID;
		buildingId_=buildingId;
		
		roomNumber_="";
		level_="";
		seats_=0;
		pcseats_=0;
		beamer_=0;
		visualizer_=0;
		overheads_=0;
		chalkboards_=0;
		whiteboards_=0;
		
	}
	
	public int getRoomId_() {
		return roomId_;
	}


	public void setRoomId_(int roomId_) {
		this.roomId_ = roomId_;
	}


	public String getRoomNumber_() {
		return roomNumber_;
	}


	public void setRoomNumber_(String roomNumber_) {
		this.roomNumber_ = roomNumber_;
	}


	public int getBuildingId_() {
		return buildingId_;
	}


	public void setBuildingId_(int buildingId_) {
		this.buildingId_ = buildingId_;
	}


	public String getLevel_() {
		return level_;
	}


	public void setLevel_(String level_) {
		this.level_ = level_;
	}


	public int getSeats_() {
		return seats_;
	}


	public void setSeats_(int seats_) {
		this.seats_ = seats_;
	}


	public int getPcseats_() {
		return pcseats_;
	}


	public void setPcseats_(int pcseats_) {
		this.pcseats_ = pcseats_;
	}


	public int getBeamer_() {
		return beamer_;
	}


	public void setBeamer_(int beamer_) {
		this.beamer_ = beamer_;
	}


	public int getVisualizer_() {
		return visualizer_;
	}


	public void setVisualizer_(int visualizer_) {
		this.visualizer_ = visualizer_;
	}


	public int getOverheads_() {
		return overheads_;
	}


	public void setOverheads_(int overheads_) {
		this.overheads_ = overheads_;
	}


	public int getChalkboards_() {
		return chalkboards_;
	}


	public void setChalkboards_(int chalkboards_) {
		this.chalkboards_ = chalkboards_;
	}


	public int getWhiteboards_() {
		return whiteboards_;
	}


	public void setWhiteboards_(int whiteboards_) {
		this.whiteboards_ = whiteboards_;
	}
	
}
