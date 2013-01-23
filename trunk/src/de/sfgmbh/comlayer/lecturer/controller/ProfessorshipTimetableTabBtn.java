package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.CtrlPdf;
import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;

/**
 * action listener class for the char timetables
 * 
 * @author denis
 * 
 */
public class ProfessorshipTimetableTabBtn implements ActionListener {

	private String navAction_;
	protected InfoDialog infoWindow;

	public ProfessorshipTimetableTabBtn() {
		this.navAction_ = "default";
	}

	public ProfessorshipTimetableTabBtn(String action) {
		this.navAction_ = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.navAction_.equals("createpdf")) {

			IntfCtrlPdf pdfController;
			
			String sFilename = "";
			String chairTitle = "";
			String semester = "";
			JFileChooser fc = new JFileChooser();

			// setting *.pdf filter for save dialog
			FileFilters filter = new FileFilters();
			filter.addExtension("pdf");
			filter.setDescription("PDF - Portable Document Format");
			fc.setFileFilter(filter);

			fc.setSelectedFile(new File(sFilename));
			if (fc.showSaveDialog(fc) == JFileChooser.APPROVE_OPTION) {
				// if the user hasn't typed .pdf, we'll do it for him
				if (!fc.getSelectedFile().getPath().toLowerCase()
						.endsWith(".pdf")) {
					fc.setSelectedFile(new File(fc.getSelectedFile() + ".pdf"));
				}
				// setting the chairtitle and semster as the header for the
				// PDF-document
				chairTitle = SessionManager.getInstance().getSession()
						.getChair_().getAcronym_();
				semester = ViewManager.getInstance().getOrgaRoomtableTab()
						.getComboBoxSemesterFilter().getSelectedItem()
						.toString();
				
				//set the PDF
				pdfController=new CtrlPdf(fc.getSelectedFile()
						.getAbsolutePath());
				// adding the content to the pdf
				pdfController.addContent("Lehrstuhl: " + chairTitle + " - Semester: "
						+ semester, ViewManager.getInstance()
						.getChairTimetableTab().getPanel_());
				//close the PDF file
				pdfController.close();
			}

		}

	}
}
