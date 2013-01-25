package de.sfgmbh.applayer.core.definitions;

/**
 * Interface for all objects which want to observer an oberservable app layer
 * object
 * 
 * @author hannes
 * 
 */
public interface IntfAppObserver {

	/**
	 * Triggered by update if there is a change in the observable object
	 */
	public void change();

}
