package de.sfgmbh.datalayer.io;

import javax.swing.JComponent;
/**
 * This is the interface for the {@link DataManagerPdf}
 * @author denis
 *
 */
public interface IntfDataManagerPdf {

	/**
	 * Add content in from of a title and Swing components the the pdf
	 * @param paragraphtitle
	 * @param component
	 */
	public abstract void addContent(String paragraphtitle, JComponent component);
	
	/**
	 * Close it
	 */
	public abstract void close();

}