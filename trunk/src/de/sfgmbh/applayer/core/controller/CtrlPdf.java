package de.sfgmbh.applayer.core.controller;

import javax.swing.JComponent;

import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.datalayer.io.DataManagerPDF;
import de.sfgmbh.datalayer.io.IntfDataManagerPDF;

/**
 * controller class for the PDF handling
 * @author denis
 *
 */
public class CtrlPdf implements IntfCtrlPdf {

	IntfDataManagerPDF pdfManager;
	String filename_;
	
	
	public CtrlPdf(String filename) {
		this.filename_=filename;
		
		pdfManager=new DataManagerPDF(this.filename_);
		
	}
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlPdf#addContent(java.lang.String, javax.swing.JComponent)
	 */
	@Override
	public void addContent(String paragraphtitle, JComponent component){
		if(filename_.length()>0){
			pdfManager.addContent(paragraphtitle, component);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlPdf#close()
	 */
	@Override
	public void close(){
		pdfManager.close();
		pdfManager=null;
	}

	
}
