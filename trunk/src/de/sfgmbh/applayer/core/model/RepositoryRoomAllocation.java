package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryRoomAllocation;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Repository for {@link RoomAllocation} objects in the application model
 * 
 * @author hannes
 * @author denis
 * 
 */
public class RepositoryRoomAllocation implements IntfAppObservable,
		IntfDataObserver, IntfRepositoryRoomAllocation {

	private ArrayList<Object> observer_ = new ArrayList<Object>();

	/**
	 * Register this room allocation repository as observer in the data model
	 */
	public RepositoryRoomAllocation() {
		DataModel.getInstance().getDataHandlerRoomAllocation().register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#getAll()
	 */
	@Override
	public List<IntfRoomAllocation> getAll() {
		return DataModel.getInstance().getDataHandlerRoomAllocation().getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#getAllOpen()
	 */
	@Override
	public List<IntfRoomAllocation> getAllOpen() {
		return DataModel.getInstance().getDataHandlerRoomAllocation()
				.getAllOpen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#getByFilter
	 * (java.util.HashMap)
	 */
	@Override
	public List<IntfRoomAllocation> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerRoomAllocation()
				.getByFilter(filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#
	 * getConflictingAllocation
	 * (de.sfgmbh.applayer.core.definitions.IntfRoomAllocation)
	 */
	@Override
	public List<IntfRoomAllocation> getConflictingAllocation(
			IntfRoomAllocation roomAllocation) {
		return DataModel.getInstance().getDataHandlerRoomAllocation()
				.getConflictingAllocation(roomAllocation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#get(int)
	 */
	@Override
	public IntfRoomAllocation get(int id) {
		return DataModel.getInstance().getDataHandlerRoomAllocation().get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#save(de.sfgmbh
	 * .applayer.core.definitions.IntfRoomAllocation)
	 */
	@Override
	public boolean save(IntfRoomAllocation roomAllocation) {
		return DataModel.getInstance().getDataHandlerRoomAllocation()
				.save(roomAllocation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	@Override
	public void change() {
		this.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
	 */
	@Override
	public void update() {

		// Create a private observer list to avoid
		// ConcurrentModificationException
		@SuppressWarnings("unchecked")
		ArrayList<IntfAppObserver> currentObservers = (ArrayList<IntfAppObserver>) observer_
				.clone();

		for (IntfAppObserver observer : currentObservers) {
			if (observer instanceof IntfAppObserver) {
				observer.change();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.definitions.IntfAppObservable#register(de.sfgmbh
	 * .applayer.core.definitions.IntfAppObserver)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#register(de
	 * .sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void register(IntfAppObserver observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!<br />Fehler: RepositoryRoomAllocation-01",
							"Fehler!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.definitions.IntfAppObservable#unregister(de.sfgmbh
	 * .applayer.core.definitions.IntfAppObserver)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#unregister
	 * (de.sfgmbh.applayer.core.definitions.IntfAppObserver)
	 */
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryRoomAllocation#clean()
	 */
	@Override
	public boolean clean() {
		return DataModel.getInstance().getDataHandlerRoomAllocation().clean();
	}

}
