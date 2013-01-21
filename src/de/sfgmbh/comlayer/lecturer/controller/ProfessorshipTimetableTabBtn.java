package de.sfgmbh.comlayer.lecturer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.organisation.views.FileFilters;
import de.sfgmbh.datalayer.io.DataManagerPDF;


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
			//ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
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
				String chairTitle = SessionManager.getInstance().getSession().getChair_().getAcronym_();
				String semester = ViewManager.getInstance().getChairTimetableTab().getComboBoxSemesterFilter().getSelectedItem().toString();
				
				DataManagerPDF dmpdf=new DataManagerPDF(fc.getSelectedFile().getAbsolutePath());
				
				dmpdf.addContent("Lehrstuhl: "+chairTitle +" - Semester: "+semester,ViewManager.getInstance().getChairTimetableTab().getPanel_());
				
				dmpdf.close();
				
				System.out.println("Created PDF");
			
			}
			
		}
		
		//this.getInfoWindow().setVisible(true);
	}
	
	public InfoDialog getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoDialog("<strong> Fehlermeldung 1:</strong><br> Momentan kann keine PDF erzeugt werden.<br><br><strong>Fehlermeldung 2:</strong><br>Es wurden keine Daten ausgew�hlt.<br><br><strong>Fehlermeldung 3:</strong><br> Es wurde kein Dozent ausgew�hlt.<br><br><strong>Fehlermeldung 4:</strong><br> Die Funktionalit�t momentan nicht ausf�hrbar. Wenden Sie sich an Ihren Systemadministrator.");
		}
		return this.infoWindow;
	}
}
