package de.sfgmbh.datalayer.core.definitions;

/**
 * This is the interface for the {@link IntfDataObserver}
 * 
 * @author hannes
 * 
 */
public interface IntfDataObserver {

	/**
	 * Triggered by update if there is a change in the observable object
	 */
	public void change();

}
