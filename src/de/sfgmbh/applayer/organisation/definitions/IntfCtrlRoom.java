package de.sfgmbh.applayer.organisation.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;

public interface IntfCtrlRoom {

	public abstract void addRoom(IntfRoom room);

	public abstract List<IntfRoom> getAllRooms();

	public abstract void delete(IntfRoom room);

}