package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryRoom;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Repository for {@link Room} objects in the application model
 * 
 * @author denis
 *
 */
public class RepositoryRoom implements IntfAppObservable, IntfDataObserver, IntfRepositoryRoom {
	
	private ArrayList<IntfAppObserver> observer_ = new ArrayList<IntfAppObserver>();
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<IntfRoom> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerRoom().getByFilter(filter);
	}	
	
	/**
	 * Register this room repository as observer in the data model
	 */
	public RepositoryRoom() {
		DataModel.getInstance().getDataHandlerRoom().register(this);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#getAll()
	 */
	@Override
	public List<IntfRoom> getAll() {
		return DataModel.getInstance().getDataHandlerRoom().getAll();
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#getRoomById(int)
	 */
	@Override
	public IntfRoom getRoomById(int roomId) {
		return DataModel.getInstance().getDataHandlerRoom().get(roomId);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#save(de.sfgmbh.applayer.core.definitions.IntfRoom)
	 */
	@Override
	public void save(IntfRoom room){
		DataModel.getInstance().getDataHandlerRoom().save(room);
		this.change();
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#delete(de.sfgmbh.applayer.core.definitions.IntfRoom)
	 */
	@Override
	public void delete(IntfRoom room){
		DataModel.getInstance().getDataHandlerRoom().delete(room);
		this.change();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#change()
	 */
	@Override
	public void change() {
		this.update();
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#update()
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#register(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void register(IntfAppObserver observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			AppModel.getInstance().getExceptionHandler().setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryChair-01", "Fehler!");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#unregister(de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoom#getByNumber(java.lang.String)
	 */
	@Override
	public IntfRoom getByNumber(String roomNumber_) {
		IntfRoom room;
		HashMap<String, String> filter=new HashMap<>();
		filter.put("room", roomNumber_);
		//we only need the first room
		room=DataModel.getInstance().getDataHandlerRoom().getByFilter(filter).get(0);
		return room;
	}


}
