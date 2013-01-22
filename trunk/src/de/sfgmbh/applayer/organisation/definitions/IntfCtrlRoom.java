package de.sfgmbh.applayer.organisation.definitions;

import java.util.List;

import de.sfgmbh.applayer.core.model.Room;

public interface IntfCtrlRoom {

	public abstract void addRoom(Room room);

	public abstract List<Room> getAllRooms();

	public abstract void delete(Room room);

}