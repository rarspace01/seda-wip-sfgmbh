package de.sfgmbh.comlayer.lecturer.controller;

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


public class TimetableTabBtn implements ActionListener {

	private String navAction;
	protected InfoDialog infoWindow;
	
	public TimetableTabBtn() {
		this.navAction = "default";
	}

	public TimetableTabBtn(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (this.navAction.equals("createpdf")) {

				System.out.println("got pdf trigger");
				
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
				String lecturerTitle = ViewManager.getInstance().getLecturerTimetableTab().getcomboBoxLecturer_().getSelectedItem().toString();
				String semester = ViewManager.getInstance().getLecturerTimetableTab().getComboBoxSemesterFilter().getSelectedItem().toString();
				
				DataManagerPDF dmpdf=new DataManagerPDF(fc.getSelectedFile().getAbsolutePath());
				
				dmpdf.addContent(lecturerTitle +" - "+semester,ViewManager.getInstance().getLecturerTimetableTab().getPanel_());
				
				dmpdf.close();
				
				System.out.println("Created PDF");
				
			}
		
		}
		//this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("<b>Fehlermeldung1:</b><br> Momentan kann keine PDF erzeugt werden.<b><br><br>Fehlermeldung:2</b><br> Es wurden keine Daten ausgew�hlt.<br><br><b>Fehlermeldung3:</b><br> Es wurde kein Dozent ausgew�hlt.<br><br><b>Fehlermeldung4:</b><br> Die Funktionalit�t momentan nicht ausf�hrbar. Wenden Sie sich bitte an Ihren Systemadministrator!");
		}
		return this.infoWindow;
	}
}
