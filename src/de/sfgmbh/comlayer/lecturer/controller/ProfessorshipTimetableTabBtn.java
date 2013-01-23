package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;
import de.sfgmbh.datalayer.io.DataManagerPDF;
import de.sfgmbh.datalayer.io.IntfDataManagerPDF;

/**
 * action listener class for the char timetables
 * 
 * @author denis
 * 
 */
public class ProfessorshipTimetableTabBtn implements ActionListener {

	private String navAction;
	protected InfoDialog infoWindow;

	public ProfessorshipTimetableTabBtn() {
		this.navAction = "default";
	}

	public ProfessorshipTimetableTabBtn(String action) {
		this.navAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.navAction.equals("createpdf")) {

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
				IntfDataManagerPDF dmpdf = new DataManagerPDF(fc.getSelectedFile()
						.getAbsolutePath());
				// adding the content to the pdf
				dmpdf.addContent("Lehrstuhl: " + chairTitle + " - Semester: "
						+ semester, ViewManager.getInstance()
						.getChairTimetableTab().getPanel_());
				dmpdf.close();
			}

		}

	}
}
