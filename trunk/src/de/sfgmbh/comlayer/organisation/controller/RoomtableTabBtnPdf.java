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

	public RoomtableTabBtnPdf() {
		this.navAction_ = "default";
	}

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
				pdfController=new CtrlPdf(fc.getSelectedFile()
						.getAbsolutePath());
				// adding the panel as the content of the document
				pdfController.addContent(roomtitle + " - " + semester, ViewManager
						.getInstance().getOrgaRoomtableTab().getScrollPane_());
				pdfController.close();
			}
		}
	}
}
