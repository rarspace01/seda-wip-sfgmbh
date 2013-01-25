package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.CtrlPdf;
import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.comlayer.core.controller.FileFilters;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * action listener for the lecturer time table
 * 
 * @author christian
 * @author denis
 * 
 */
public class TimetableTabBtn implements ActionListener {

	private String navAction_;

	/**
	 * Create the action listener
	 */
	public TimetableTabBtn() {
		this.navAction_ = "default";
	}

	/**
	 * Create the action listener based on a action string
	 * Supported action strings are:<br>
	 * "createpdf"
	 * @param action
	 */
	public TimetableTabBtn(String action) {
		this.navAction_ = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.navAction_.equals("createpdf")) {
			IntfCtrlPdf pdfController;
			String sFilename = "";
			String lecturerTitle = "";
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
					fileChooser.setSelectedFile(new File(fileChooser.getSelectedFile() + ".pdf"));
				}
				lecturerTitle = ViewManager.getInstance()
						.getLecturerTimetableTab().getcomboBoxLecturer_()
						.getSelectedItem().toString();
				semester = ViewManager.getInstance().getLecturerTimetableTab()
						.getComboBoxSemesterFilter().getSelectedItem()
						.toString();
				
				pdfController=new CtrlPdf(fileChooser.getSelectedFile()
						.getAbsolutePath());
				// adding the content
				pdfController.addContent(lecturerTitle + " - " + semester, ViewManager
						.getInstance().getLecturerTimetableTab().getPanel_());
				//close the PDF file
				pdfController.close();
			}

		}
	}
}
