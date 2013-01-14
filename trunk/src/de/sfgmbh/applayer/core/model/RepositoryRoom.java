package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class RepositoryRoom implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this room repository as observer in the data model
	 */
	public RepositoryRoom() {
		DataModel.getInstance().getDataHandlerRoom().register(this);
	}
	
	/**
	 * Return all courses
	 * @return a list of all courses
	 */
	public List<Room> getAll() {
		return DataModel.getInstance().getDataHandlerRoom().getAll();
	}
	
	public Room getRoomById(int roomId) {
		return DataModel.getInstance().getDataHandlerRoom().get(roomId);
	}
	
	public void save(Room room){
		DataModel.getInstance().getDataHandlerRoom().save(room);
		this.change();
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
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryChair-01", "Fehler!");
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
