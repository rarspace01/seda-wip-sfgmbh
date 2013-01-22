package de.sfgmbh.comlayer.core.definitions;

/**
 * Interface for dialogs which should be observable
 * 
 * @author hannes
 *
 */
public interface IntfComDialogObservable {
	
	/**
	 * Registers an observer in the dialog
	 * @param observer
	 */
	public void register(IntfComDialogObserver observer);
	
	/**
	 * Registers an observer in the dialog
	 * @param observer
	 */
	public void unregister(IntfComDialogObserver observer);
	
	/**
	 * Update the registered 
	 * @param answer
	 */
	public void update(String answer);
	
}
