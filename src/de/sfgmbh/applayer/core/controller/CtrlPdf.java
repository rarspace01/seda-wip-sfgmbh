package de.sfgmbh.applayer.core.controller;

import javax.swing.JComponent;

import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.datalayer.io.DataManagerPdf;
import de.sfgmbh.datalayer.io.IntfDataManagerPdf;

/**
 * controller class for the PDF handling
 * 
 * @author denis
 * 
 */
public class CtrlPdf implements IntfCtrlPdf {

	private IntfDataManagerPdf pdfManager_;
	private String filename_;

	public CtrlPdf(String filename) {
		this.filename_ = filename;

		pdfManager_ = new DataManagerPdf(this.filename_);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.sfgmbh.applayer.core.controller.IntfCtrlPdf#addContent(java.lang.String
	 * , javax.swing.JComponent)
	 */
	@Override
	public void addContent(String paragraphtitle, JComponent component) {
		if (filename_.length() > 0) {
			pdfManager_.addContent(paragraphtitle, component);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlPdf#close()
	 */
	@Override
	public void close() {
		pdfManager_.close();
		pdfManager_ = null;
	}

}
