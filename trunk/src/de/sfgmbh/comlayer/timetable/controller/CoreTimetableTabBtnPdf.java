package de.sfgmbh.comlayer.timetable.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;
import de.sfgmbh.datalayer.io.DataManagerPDF;
import de.sfgmbh.datalayer.io.IntfDataManagerPDF;



public class CoreTimetableTabBtnPdf implements ActionListener {
	
	private String navAction;
	protected InfoDialog infoWindow;
	
	
	public CoreTimetableTabBtnPdf() {
		this.navAction = "default";
	}
	public CoreTimetableTabBtnPdf(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Pdf Button is pressed
		if (this.navAction.equals("pdfCreate")) {
			
			String sFilename = "";
			JFileChooser fc = new JFileChooser();
			
			//setting *.pdf filter for save dialog
			FileFilters filter = new FileFilters();
			filter.addExtension("pdf");
			filter.setDescription("PDF - Portable Document Format");
			fc.setFileFilter(filter);
			
			fc.setSelectedFile(new File(sFilename));
			if (fc.showSaveDialog(fc) == JFileChooser.APPROVE_OPTION) {
			// if the user hasn't typed .pdf, we'll do it for him
				if (!fc.getSelectedFile().getPath().toLowerCase().endsWith(
						".pdf"))
					{
						fc.setSelectedFile(new File(fc.getSelectedFile() + ".pdf"));
					}
				String semester = ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString();
				IntfDataManagerPDF dmpdf=new DataManagerPDF(fc.getSelectedFile().getAbsolutePath());
				dmpdf.addContent("Vorlesungsplan - Semester: "+semester,ViewManager.getInstance().getCoreTimetableTab().getScrollPane_());
				dmpdf.close();
			}
			
		}
		if (this.navAction.equals("reset")) {
			ViewManager.getInstance().getCoreTimetableTab().resetPlan();
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
