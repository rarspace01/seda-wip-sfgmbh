package de.sfgmbh.comlayer.core.definitions;

/**
 * Interface for dialog observer
 * 
 * @author hannes
 *
 */
public interface IntfComDialogObserver {

	/**
	 * Indicates the the user clicked a button in the dialog and return an answer in form of a string
	 * @param answer
	 */
	public void answered(String answer);
}
