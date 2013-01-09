package de.sfgmbh.datalayer.core.definitions;

public interface IntfDataObserver {
	
	/**
	 * Triggered by update if there is a change in the observable object
	 */
	public void change();

}
