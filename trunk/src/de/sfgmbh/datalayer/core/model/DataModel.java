package de.sfgmbh.datalayer.core.model;

import java.util.ArrayList;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.datalayer.core.daos.DataHandlerChair;
import de.sfgmbh.datalayer.core.daos.DataHandlerCourse;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;

public class DataModel implements IntfAppObservable {
	
	private static DataModel uniqueInstance_ = new DataModel(); // declare on first access through JVM (thread-safe)
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	public DataExceptions dataExcaptions = new DataExceptions();
	public DataHandlerChair dataHandlerChair = new DataHandlerChair();
	public DataHandlerUser dataHandlerUser = new DataHandlerUser();
	public DataHandlerCourse dataHandlerCourse = new DataHandlerCourse();
	
	private DataModel() {} // class may only call itself via declaration
	
	/**
	 * Returns the singleton instance
	 * @return
	 */
	public static DataModel getInstance() {
		return uniqueInstance_;
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
			this.dataExcaptions.setNewException("Das Objekt implementiert nicht das Observer-Interface und kann daher nicht hinzugefügt werden!", "Fehler!");
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
