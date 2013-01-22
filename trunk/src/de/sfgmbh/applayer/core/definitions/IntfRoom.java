package de.sfgmbh.applayer.core.definitions;

import de.sfgmbh.applayer.core.model.Room;

/**
 * Interface for {@link Room} object
 * @author denis
 *
 */
public interface IntfRoom {

	public abstract int getRoomId_();

	public abstract void setRoomId_(int roomId_);

	public abstract String getRoomNumber_();

	public abstract void setRoomNumber_(String roomNumber_);

	public abstract int getBuildingId_();

	public abstract void setBuildingId_(int buildingId_);

	public abstract String getLevel_();

	public abstract void setLevel_(String level_);

	public abstract int getSeats_();

	public abstract void setSeats_(int seats_);

	public abstract int getPcseats_();

	public abstract void setPcseats_(int pcseats_);

	public abstract int getBeamer_();

	public abstract void setBeamer_(int beamer_);

	public abstract int getVisualizer_();

	public abstract void setVisualizer_(int visualizer_);

	public abstract int getOverheads_();

	public abstract void setOverheads_(int overheads_);

	public abstract int getChalkboards_();

	public abstract void setChalkboards_(int chalkboards_);

	public abstract int getWhiteboards_();

	public abstract void setWhiteboards_(int whiteboards_);

}