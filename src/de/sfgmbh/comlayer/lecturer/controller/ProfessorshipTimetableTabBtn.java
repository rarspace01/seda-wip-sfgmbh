package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.CtrlPdf;
import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.comlayer.core.controller.FileFilters;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

/**
 * action listener class for the char timetables
 * 
 * @author denis
 * 
 */
public class ProfessorshipTimetableTabBtn implements ActionListener {

	private String navAction_;
	protected InfoDialog infoWindow;

	/**
	 * Create the action listener
	 */
	public ProfessorshipTimetableTabBtn() {
		this.navAction_ = "default";
	}

	/**
	 * Create the action listener based on an action string Supported action
	 * strings are "createpdf"
	 * 
	 * @param action
	 */
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
			JFileChooser fileChooser = new JFileChooser();

			// setting *.pdf filter for save dialog
			FileFilters filter = new FileFilters();
			filter.addExtension("pdf");
			filter.setDescription("PDF - Portable Document Format");
			fileChooser.setFileFilter(filter);

			fileChooser.setSelectedFile(new File(sFilename));
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				// if the user hasn't typed .pdf, we'll do it for him
				if (!fileChooser.getSelectedFile().getPath().toLowerCase()
						.endsWith(".pdf")) {
					fileChooser.setSelectedFile(new File(fileChooser
							.getSelectedFile() + ".pdf"));
				}
				// setting the chairtitle and semster as the header for the
				// PDF-document
				chairTitle = SessionManager.getInstance().getSession()
						.getChair_().getAcronym();
				semester = ViewManager.getInstance().getOrgaRoomtableTab()
						.getComboBoxSemesterFilter().getSelectedItem()
						.toString();

				// set the PDF
				pdfController = new CtrlPdf(fileChooser.getSelectedFile()
						.getAbsolutePath());
				// adding the content to the pdf
				pdfController.addContent("Lehrstuhl: " + chairTitle
						+ " - Semester: " + semester, ViewManager.getInstance()
						.getChairTimetableTab().getPanel_());
				// close the PDF file
				pdfController.close();
			}

		}

	}
}
