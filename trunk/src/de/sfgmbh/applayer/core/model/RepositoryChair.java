package de.sfgmbh.applayer.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.sfgmbh.applayer.core.definitions.IntfAppObservable;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.definitions.IntfRepositoryChair;
import de.sfgmbh.datalayer.core.definitions.IntfDataObserver;
import de.sfgmbh.datalayer.core.model.DataModel;

/**
 * Repository for {@link Chair} objects in the application model
 * 
 * @author denis
 * @author hannes
 *
 */
public class RepositoryChair implements IntfAppObservable, IntfDataObserver, IntfRepositoryChair {
	
	private ArrayList<Object> observer_ = new ArrayList<Object>();
	
	/**
	 * Register this chair repository as observer in the data model
	 */
	public RepositoryChair() {
		DataModel.getInstance().getDataHandlerChair().register(this);
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#getAll()
	 */
	@Override
	public List<IntfChair> getAll() {
		return DataModel.getInstance().getDataHandlerChair().getAll();
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#getByFilter(java.util.HashMap)
	 */
	@Override
	public List<IntfChair> getByFilter(HashMap<String, String> filter) {
		return DataModel.getInstance().getDataHandlerChair().getByFilter(filter);
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#getForAcronym(java.lang.String)
	 */
	@Override
	public IntfChair getForAcronym(String acronym) {
		return DataModel.getInstance().getDataHandlerChair().getForAcronym(acronym);
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.datalayer.core.definitions.IntfDataObserver#change()
	 */
	@Override
	public void change() {
		this.update();
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObservable#update()
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
	@Override
	public void unregister(IntfAppObserver observer) {
		observer_.remove(observer);
	}
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#get(int)
	 */
	@Override
	public IntfChair get(int chairId_) {
		return DataModel.getInstance().getDataHandlerChair().get(chairId_);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#delete(de.sfgmbh.applayer.core.definitions.IntfChair)
	 */
	@Override
	public boolean delete(IntfChair delChair) {
		return DataModel.getInstance().getDataHandlerChair().delete(delChair);
	}

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.model.IntfRepositoryChair#save(de.sfgmbh.applayer.core.definitions.IntfChair)
	 */
	@Override
	public boolean save(IntfChair chair) {
		return DataModel.getInstance().getDataHandlerChair().save(chair);
	}

}
