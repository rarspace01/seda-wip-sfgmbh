package de.sfgmbh.applayer.core.definitions;

/**
 * Interfaces for objects from the app layer which should be observable
 * 
 * @author hannes
 *
 */
public interface IntfAppObservable {

	/**
	 * Notifies all observer about a change
	 */
	public void update();
	
	/**
	 * Registers an observer
	 * @param observer - {@link IntfAppObserver}
	 */
	public void register(IntfAppObserver observer);
	
	/**
	 * Unregisters an observer 
	 * @param observer - {@link IntfAppObserver}
	 */
	public void unregister(IntfAppObserver observer);
}
