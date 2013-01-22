package de.sfgmbh.datalayer.core.definitions;

public interface IntfDataObservable {

	/**
	 * Notifies all observer about a change
	 */
	public void update();
	
	/**
	 * Registers an observer
	 * @param observer - {@link IntfDataObserver}
	 */
	public void register(IntfDataObserver observer);
	
	/**
	 * Unregisters an observer 
	 * @param observer - {@link IntfDataObserver}
	 */
	public void unregister(IntfDataObserver observer);
}
