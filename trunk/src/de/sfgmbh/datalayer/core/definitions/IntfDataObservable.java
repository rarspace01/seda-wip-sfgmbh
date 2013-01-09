package de.sfgmbh.datalayer.core.definitions;

public interface IntfDataObservable {

	/**
	 * Notifies all observer about a change
	 */
	public void update();
	
	/**
	 * Registers an observer
	 * @param listner
	 */
	public void register(Object observer);
	
	/**
	 * Unregisters an observer 
	 * @param listner
	 */
	public void unregister(Object observer);
}
