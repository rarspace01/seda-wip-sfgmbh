package de.sfgmbh.datalayer.io;

import javax.swing.JComponent;
import javax.swing.JTable;

public interface IntfDataManagerPDF {

	public abstract void addContent(String paragraphtitle, JComponent component);
	
	public abstract void close();

}