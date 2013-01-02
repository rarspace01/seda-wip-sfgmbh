package de.sfgmbh.datalayer.core.definitions;

/**
 * Interface für Model in der k-Schicht,<br/>
 * um die Daten nach einem<br/>
 * aktualisieren() im Observer Pattern zu laden<br/>
 * @author denis
 *
 */
public interface IntfDataRetrievable {

	public Object getData();
	
}
