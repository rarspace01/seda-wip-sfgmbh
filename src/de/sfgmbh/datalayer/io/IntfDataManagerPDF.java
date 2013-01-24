package de.sfgmbh.datalayer.io;

import javax.swing.JComponent;
/**
 * This is the interface for the {@link DataManagerPdf}
 * @author denis
 *
 */
public interface IntfDataManagerPdf {

	public abstract void addContent(String paragraphtitle, JComponent component);
	
	public abstract void close();

}