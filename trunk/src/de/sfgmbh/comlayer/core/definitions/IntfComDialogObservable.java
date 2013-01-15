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
	public void register(Object observer);
	
	/**
	 * Update the registered observer (in this case there should be only one observer) with the answer
	 * @param answer
	 */
	public void update(String answer);
}
