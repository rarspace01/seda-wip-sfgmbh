package de.sfgmbh.applayer.core.definitions;

public interface IntfAppObservable {

	/**
	 * Notifies all observer about a change
	 */
	public void update();
	
	/**
	 * Registers an observer
	 * @param listner
	 */
	public void register(IntfAppObserver observer);
	
	/**
	 * Unregisters an observer 
	 * @param listner
	 */
	public void unregister(IntfAppObserver observer);
}
