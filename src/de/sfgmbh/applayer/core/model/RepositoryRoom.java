package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class RepositoryRoom implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	public List<Room> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerRoom().getByFilter(filter);
	}	
	
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
	
	public IntfRoom getRoomById(int roomId) {
		return DataModel.getInstance().getDataHandlerRoom().get(roomId);
	}
	
	public void save(IntfRoom room){
		DataModel.getInstance().getDataHandlerRoom().save(room);
		this.change();
	}
	
	public void delete(IntfRoom room){
		DataModel.getInstance().getDataHandlerRoom().delete(room);
		this.change();
	}
	
	@Override
	public void change() {
		this.update();
	}

	@Override
	public void update() {
		
		// Create a private observer list to avoid ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfAppObserver> currentObservers = (ArrayList<IntfAppObserver>) observer_.clone();
				
		for (IntfAppObserver observer : currentObservers) {
			if (observer instanceof IntfAppObserver) {
				observer.change();
			}
		}
	}
	
	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(IntfAppObserver observer) {
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
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

	/**
	 * Get a room by its number
	 * @param roomNumber
	 * @return a room object
	 */
	public IntfRoom getByNumber(String roomNumber_) {
		IntfRoom room;
		HashMap<String, String> filter=new HashMap<>();
		filter.put("roomnumber", roomNumber_);
		//we only need the first room
		room=DataModel.getInstance().getDataHandlerRoom().getByFilter(filter).get(0);
		return room;
	}


}
