package de.sfgmbh.datalayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.datalayer.core.daos.DataHandlerChair;
import de.sfgmbh.datalayer.core.daos.DataHandlerCourse;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoom;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoomAllocation;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;

public class DataModel implements IntfAppObservable {

	private static DataModel uniqueInstance_; 
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	private DataExceptions exceptionsHandler_ = new DataExceptions();
	private DataHandlerChair dataHandlerChair_ = new DataHandlerChair();
	private DataHandlerUser dataHandlerUser_ = new DataHandlerUser();
	private DataHandlerCourse dataHandlerCourse_ = new DataHandlerCourse();
	private DataHandlerRoom dataHandlerRoom_ = new DataHandlerRoom();
	private DataHandlerRoomAllocation dataHandlerRoomAllocation_ = new DataHandlerRoomAllocation();

	private DataModel() {
	}

	/**
	 * Returns the singleton instance
	 * 
	 * @return
	 */
	public static DataModel getInstance() {
		if(uniqueInstance_==null){
			uniqueInstance_=new DataModel();
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
	public DataHandlerCourse getDataHandlerCourse() {
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

	/**
	 * 
	 */
	@Override
	public void update() {
		for (Object o : observer_) {
			if (o instanceof IntfDataObserver) {
				((IntfDataObserver) o).change();
			}
		}
	}

	/**
	 * 
	 * @param observer
	 */
	@Override
	public void register(Object observer) {
		if (observer instanceof IntfDataObserver) {
			observer_.add(observer);
		} else {
			this.exceptionsHandler_
					.setNewException(
							"Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugef�gt werden!",
							"Fehler!");
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
