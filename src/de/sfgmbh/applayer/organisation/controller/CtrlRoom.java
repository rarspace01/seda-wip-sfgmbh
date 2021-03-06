package de.sfgmbh.applayer.organisation.controller;

import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoom;

/**
 * Controller for room management
 * 
 * @author denis
 * @author hannes
 * 
 */
public class CtrlRoom implements IntfCtrlRoom {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#addRoom(de.sfgmbh
	 * .applayer.core.model.Room)
	 */
	@Override
	public void addRoom(IntfRoom room) {

		AppModel.getInstance().getRepositoryRoom().save(room);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#getAllRooms()
	 */
	@Override
	public List<IntfRoom> getAllRooms() {
		return AppModel.getInstance().getRepositoryRoom().getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.organisation.controller.IntfCtrlRoom#delete(de.sfgmbh
	 * .applayer.core.model.Room)
	 */
	@Override
	public void delete(IntfRoom room) {
		AppModel.getInstance().getRepositoryRoom().delete(room);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoom#get(int)
	 */
	@Override
	public IntfRoom get(int roomId) {
		return AppModel.getInstance().getRepositoryRoom().getRoomById(roomId);
	}

}
