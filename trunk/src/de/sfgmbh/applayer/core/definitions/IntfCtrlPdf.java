package de.sfgmbh.applayer.core.definitions;

import javax.swing.JComponent;

public interface IntfCtrlPdf {

	/**
	 * Adds content to the current PDF file
	 * @param paragraphtitle
	 * @param component
	 */
	public abstract void addContent(String paragraphtitle, JComponent component);
	
	/**
	 * closes the current PDF file
	 */
	public abstract void close();

}