package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

public class RepositoryChair implements IntfAppObservable, IntfDataObserver {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this chair repository as observer in the data model
	 */
	public RepositoryChair() {
		DataModel.getInstance().getDataHandlerChair().register(this);
	}
	
	/**
	 * Return all chairs
	 * @return a list of all chairs
	 */
	public List<Chair> getAll() {
		return DataModel.getInstance().getDataHandlerChair().getAll();
	}
	
	/**
	 * Get the chair based on its acronym
	 * @param acronym
	 * @return a chair if the submitted acronym can be associated with one, otherwise returns null
	 */
	public Chair getForAcronym(String acronym) {
		return DataModel.getInstance().getDataHandlerChair().getForAcronym(acronym);
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
