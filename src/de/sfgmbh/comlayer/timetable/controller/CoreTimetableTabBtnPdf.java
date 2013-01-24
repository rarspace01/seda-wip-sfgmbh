package de.sfgmbh.comlayer.timetable.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.CtrlPdf;
import de.sfgmbh.applayer.core.definitions.IntfCtrlPdf;
import de.sfgmbh.comlayer.core.controller.FileFilters;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

/**
 * Action listener class for the PDF output
 * 
 * @author denis
 * 
 */
public class CoreTimetableTabBtnPdf implements ActionListener {

	private String navAction_;
	protected InfoDialog infoWindow;

	/**
	 * constructor
	 */
	public CoreTimetableTabBtnPdf() {
		this.navAction_ = "default";
	}

	/**
	 * constructor
	 * 
	 * @param action
	 *            - String whcih declares the action
	 */
	public CoreTimetableTabBtnPdf(String action) {
		this.navAction_ = action;
	}

	/**
	 * actionPerformed on button klick
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		IntfCtrlPdf pdfController;
		// Pdf Button is pressed
		if (this.navAction_.equals("pdfCreate")) {

			String sFilename = "";
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
				String semester = ViewManager.getInstance().getCoreBaseTab()
						.getComboBoxSemesterFilter().getSelectedItem()
						.toString();

				// setPDF
				pdfController = new CtrlPdf(fileChooser.getSelectedFile()
						.getAbsolutePath());
				// add the specific content
				pdfController.addContent("Vorlesungsplan - Semester: "
						+ semester, ViewManager.getInstance()
						.getCoreTimetableTab().getScrollPane_());
				// close document
				pdfController.close();
			}

		}
		if (this.navAction_.equals("reset")) {
			ViewManager.getInstance().getCoreTimetableTab().resetPlan();
		}
	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
