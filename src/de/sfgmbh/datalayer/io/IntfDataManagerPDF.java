package de.sfgmbh.datalayer.io;

import javax.swing.JComponent;
/**
 * This is the interface for the {@link DataManagerPDF}
 * @author denis
 *
 */
public interface IntfDataManagerPDF {

	public abstract void addContent(String paragraphtitle, JComponent component);
	
	public abstract void close();

}