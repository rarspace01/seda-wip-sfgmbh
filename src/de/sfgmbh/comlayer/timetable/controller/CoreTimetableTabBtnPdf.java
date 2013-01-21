package de.sfgmbh.comlayer.timetable.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;
import de.sfgmbh.datalayer.io.DataManagerPDF;



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
			
			//get save path
			
			JFileChooser fc = new JFileChooser();
			FileFilters filter = new FileFilters();
			filter.addExtension("pdf");
			filter.setDescription("PDF - Portable Document Format");
			fc.setFileFilter(filter);

			String sDateiname = "";
			fc.setSelectedFile(new File(sDateiname));

			if (fc.showSaveDialog(fc) != 0) {
			} else {
					//sofern der user nicht pdf als endung ausgew�hlt hat machen wir es
				if (!fc.getSelectedFile().getPath().toLowerCase().endsWith(
						".pdf"))
					{
						fc.setSelectedFile(new File(fc.getSelectedFile() + ".pdf"));
					}
			
			}
			
			if(!fc.getSelectedFile().getName().contains(".")){
				System.out.println("no file selected");
				AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
				exceptionHandler.setNewException("Keine Datei Ausgewählt");
			}else{
			
			System.out.println("Selected Path: ["+fc.getSelectedFile().getAbsolutePath()+"]");		
			
			//finished pathget
			
			//getRoomTItle
			String semester = ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString();
			
			DataManagerPDF dmpdf=new DataManagerPDF(fc.getSelectedFile().getAbsolutePath());
			
			dmpdf.addContent("Vorlesungsplan - "+semester,ViewManager.getInstance().getCoreTimetableTab().getScrollPane_());
			
			dmpdf.close();
			
			System.out.println("Created PDF");
			
			}
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
