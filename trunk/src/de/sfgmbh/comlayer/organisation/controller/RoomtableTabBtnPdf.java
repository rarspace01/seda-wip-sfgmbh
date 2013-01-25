package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.CtrlPdf;
import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.FileFilters;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

/**
 * pdf action listener for room management tab
 * 
 * @author denis
 * 
 */
public class RoomtableTabBtnPdf implements ActionListener {

	private String navAction_;
	protected InfoDialog infoWindow;

	/**
	 * Create the action listener
	 */
	public RoomtableTabBtnPdf() {
		this.navAction_ = "default";
	}

	/**
	 * Create the action listener based on an action string<br>
	 * Supported action strings are:<br>
	 * "pdfCreate"
	 * @param action
	 */
	public RoomtableTabBtnPdf(String action) {
		this.navAction_ = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		IntfCtrlPdf pdfController;
		
		// Pdf Button is pressed
		if (this.navAction_.equals("pdfCreate")) {

			String sFilename = "";
			String roomtitle = "";
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
				// setting roomtitle and semeseter for the header of the PDF
				// document
				roomtitle = AppModel
						.getInstance()
						.getRepositoryRoom()
						.getRoomById(
								ViewManager.getInstance().getOrgaRoomtableTab()
										.getRoomId_()).getRoomNumber_();
				semester = ViewManager.getInstance().getOrgaRoomtableTab()
						.getComboBoxSemesterFilter().getSelectedItem()
						.toString();

				// create the PDF
				pdfController=new CtrlPdf(fileChooser.getSelectedFile()
						.getAbsolutePath());
				// adding the panel as the content of the document
				pdfController.addContent(roomtitle + " - " + semester, ViewManager
						.getInstance().getOrgaRoomtableTab().getScrollPane_());
				pdfController.close();
			}
		}
	}
}
