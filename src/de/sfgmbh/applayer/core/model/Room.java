package de.sfgmbh.applayer.core.model;

import de.sfgmbh.applayer.core.definitions.IntfRoom;

/**
 * Room model class
 * 
 * @author denis
 * 
 */
public class Room implements IntfRoom {

	private int roomId_;
	private String roomNumber_;
	private int buildingId_;
	private String level_;
	private int seats_;
	private int pcSeats_;
	private int beamer_;
	private int visualizer_;
	private int overheads_;
	private int chalkboards_;
	private int whiteboards_;

	/**
	 * Create a default room
	 */
	public Room() {
		this.roomId_ = -1;
		roomNumber_ = "";
		level_ = "";
		seats_ = 0;
		pcSeats_ = 0;
		beamer_ = 0;
		visualizer_ = 0;
		overheads_ = 0;
		chalkboards_ = 0;
		whiteboards_ = 0;
	}
	
	/**
	 * Create a room with specific values
	 * @param roomId_
	 * @param roomNumber_
	 * @param buildingId_
	 * @param level_
	 * @param seats_
	 * @param pcSeats_
	 * @param beamer_
	 * @param visualizer_
	 * @param overheads_
	 * @param chalkboards_
	 * @param whiteboards_
	 */
	public Room(int roomId_, String roomNumber_, int buildingId_,
			String level_, int seats_, int pcSeats_, int beamer_,
			int visualizer_, int overheads_, int chalkboards_, int whiteboards_) {
		super();
		this.roomId_ = roomId_;
		this.roomNumber_ = roomNumber_;
		this.buildingId_ = buildingId_;
		this.level_ = level_;
		this.seats_ = seats_;
		this.pcSeats_ = pcSeats_;
		this.beamer_ = beamer_;
		this.visualizer_ = visualizer_;
		this.overheads_ = overheads_;
		this.chalkboards_ = chalkboards_;
		this.whiteboards_ = whiteboards_;
	}



	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getRoomId_()
	 */
	@Override
	public int getRoomId_() {
		return roomId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setRoomId_(int)
	 */
	@Override
	public void setRoomId_(int roomId_) {
		this.roomId_ = roomId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getRoomNumber_()
	 */
	@Override
	public String getRoomNumber_() {
		return roomNumber_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setRoomNumber_(java.lang.String)
	 */
	@Override
	public void setRoomNumber_(String roomNumber_) {
		this.roomNumber_ = roomNumber_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getBuildingId_()
	 */
	@Override
	public int getBuildingId_() {
		return buildingId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setBuildingId_(int)
	 */
	@Override
	public void setBuildingId_(int buildingId_) {
		this.buildingId_ = buildingId_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getLevel_()
	 */
	@Override
	public String getLevel_() {
		return level_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setLevel_(java.lang.String)
	 */
	@Override
	public void setLevel_(String level_) {
		this.level_ = level_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getSeats_()
	 */
	@Override
	public int getSeats_() {
		return seats_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setSeats_(int)
	 */
	@Override
	public void setSeats_(int seats_) {
		this.seats_ = seats_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getPcseats_()
	 */
	@Override
	public int getPcseats_() {
		return pcSeats_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setPcseats_(int)
	 */
	@Override
	public void setPcseats_(int pcseats_) {
		this.pcSeats_ = pcseats_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getBeamer_()
	 */
	@Override
	public int getBeamer_() {
		return beamer_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setBeamer_(int)
	 */
	@Override
	public void setBeamer_(int beamer_) {
		this.beamer_ = beamer_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getVisualizer_()
	 */
	@Override
	public int getVisualizer_() {
		return visualizer_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setVisualizer_(int)
	 */
	@Override
	public void setVisualizer_(int visualizer_) {
		this.visualizer_ = visualizer_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getOverheads_()
	 */
	@Override
	public int getOverheads_() {
		return overheads_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setOverheads_(int)
	 */
	@Override
	public void setOverheads_(int overheads_) {
		this.overheads_ = overheads_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getChalkboards_()
	 */
	@Override
	public int getChalkboards_() {
		return chalkboards_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setChalkboards_(int)
	 */
	@Override
	public void setChalkboards_(int chalkboards_) {
		this.chalkboards_ = chalkboards_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#getWhiteboards_()
	 */
	@Override
	public int getWhiteboards_() {
		return whiteboards_;
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRoom#setWhiteboards_(int)
	 */
	@Override
	public void setWhiteboards_(int whiteboards_) {
		this.whiteboards_ = whiteboards_;
	}

}
