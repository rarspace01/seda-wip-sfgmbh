package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class RepositoryRoomAllocation implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this room allocation repository as observer in the data model
	 */
	public RepositoryRoomAllocation() {
		DataModel.getInstance().getDataHandlerRoomAllocation().register(this);
	}
	
	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public List<RoomAllocation> getAll() {
		return DataModel.getInstance().getDataHandlerRoomAllocation().getAll();
	}
	
	/**
	 * Return filtered courses
	 * @return a list of filtered courses
	 */
	public List<RoomAllocation> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerRoomAllocation().getByFilter(filter);
	}

	/**
	 * Checks for conflicting room allocations
	 * @param ra
	 * @return a list of all conflicting room allocations
	 */
	public List<RoomAllocation> getConflictingAllocation(RoomAllocation ra) {
		return DataModel.getInstance().getDataHandlerRoomAllocation().getConflictingAllocation(ra);
	}
	
	/**
	 * Get a room allocation by its id
	 * @param id
	 * @return a room allocation by its id
	 */
	public RoomAllocation get(int id) {
		return DataModel.getInstance().getDataHandlerRoomAllocation().get(id);
	}
	
	/**
	 * Save this user object in the DB (this will update a database entry if there is already one and create one if there is none)
	 * @return true on success
	 */
	public boolean save(RoomAllocation ra) {
		return DataModel.getInstance().getDataHandlerRoomAllocation().save(ra);
	}

	@Override
	public void change() {
		this.update();
	}

	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfAppObserver) {
				((IntfAppObserver) o).change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryRoomAllocation-01", "Fehler!");
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void unregister(Object observer) {
		observer_.remove(observer);
	}

}
