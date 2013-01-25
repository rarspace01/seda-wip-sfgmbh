package de.sfgmbh.datalayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.daos.DataHandlerChair;
import de.sfgmbh.datalayer.core.daos.DataHandlerCourse;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoom;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoomAllocation;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;
import de.sfgmbh.datalayer.core.definitions.IntfDataCourse;

/**
 * The main class to manage the data handler in which interested objects that
 * implement the IntfAppOberserver interfaces may register and get updated on
 * any relevant changes from the corresponding data handler.
 * 
 * @author hannes
 * 
 */
public class DataModel implements IntfAppObservable {

	private static DataModel uniqueInstance_;
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataExceptions exceptionsHandler_ = new DataExceptions();
	private DataHandlerChair dataHandlerChair_ = new DataHandlerChair();
	private DataHandlerUser dataHandlerUser_ = new DataHandlerUser();
	private IntfDataCourse dataHandlerCourse_ = new DataHandlerCourse();
	private DataHandlerRoom dataHandlerRoom_ = new DataHandlerRoom();
	private DataHandlerRoomAllocation dataHandlerRoomAllocation_ = new DataHandlerRoomAllocation();

	private DataModel() {
	}

	/**
	 * Returns the singleton instance
	 * 
	 * @return {@link DataModel}
	 */
	public static DataModel getInstance() {
		if (uniqueInstance_ == null) {
			uniqueInstance_ = new DataModel();
		}
		return uniqueInstance_;
	}

	/**
	 * @return the exceptionsHandler
	 */
	public DataExceptions getExceptionsHandler() {
		return exceptionsHandler_;
	}

	/**
	 * @return the dataHandlerChair
	 */
	public DataHandlerChair getDataHandlerChair() {
		return dataHandlerChair_;
	}

	/**
	 * @return the dataHandlerUser
	 */
	public DataHandlerUser getDataHandlerUser() {
		return dataHandlerUser_;
	}

	/**
	 * @return the dataHandlerCourse
	 */
	public IntfDataCourse getDataHandlerCourse() {
		return dataHandlerCourse_;
	}

	/**
	 * @return the dataHandlerRoom
	 */
	public DataHandlerRoom getDataHandlerRoom() {
		return dataHandlerRoom_;
	}

	/**
	 * @return the dataHandlerRoomAllocation
	 */
	public DataHandlerRoomAllocation getDataHandlerRoomAllocation() {
		return dataHandlerRoomAllocation_;
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
	@Override
	public void register(IntfAppObserver observer) {
		if (observer instanceof IntfAppObserver) {
			observer_.add(observer);
		} else {
			this.exceptionsHandler_
					.setNewException(
							"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!",
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
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}

}
