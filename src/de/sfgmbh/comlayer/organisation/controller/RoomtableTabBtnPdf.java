package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;
import de.sfgmbh.datalayer.io.DataManagerPDF;

/**
 * action listener for room management tab
 * 
 * @author denis
 * 
 */
public class RoomtableTabBtnPdf implements ActionListener {

	private String navAction;
	protected InfoDialog infoWindow;

	public RoomtableTabBtnPdf() {
		this.navAction = "default";
	}

	public RoomtableTabBtnPdf(String action) {
		this.navAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Pdf Button is pressed
		if (this.navAction.equals("pdfCreate")) {

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
				// setting roomtitle and semseeter for the header of the PDF
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
				DataManagerPDF dmpdf = new DataManagerPDF(fc.getSelectedFile()
						.getAbsolutePath());
				// adding the pnael as the content of the document
				dmpdf.addContent(roomtitle + " - " + semester, ViewManager
						.getInstance().getOrgaRoomtableTab().getScrollPane_());
				dmpdf.close();
			}
		}
	}
}
