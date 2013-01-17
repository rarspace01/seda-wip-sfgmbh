package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.datalayer.io.DataManagerPDF;



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
			
			//getRoomTItle
			String roomtitle = AppModel.getInstance().getRepositoryRoom().getRoomById(ViewManager.getInstance().getOrgaRoomtableTab().getRoomId_()).getRoomNumber_();
			
			DataManagerPDF dmpdf=new DataManagerPDF("C:/Share/test_"+ViewManager.getInstance().getOrgaRoomtableTab().getRoomId_()+".pdf");
			
			dmpdf.addContent(roomtitle,ViewManager.getInstance().getOrgaRoomtableTab().getScrollPane_());
			
			dmpdf.close();
			
			System.out.println("Created PDF");
			
			//getPane
			
			//
			
//			this.getInfoWindow("<b>Fehlermeldung 1:</b><br> Momentan kann keine PDF erzeugt werden.<b><br><br>Fehlermeldung 2:</b><br> Es wurden keine Daten ausgew�hlt.<br><br><b>Fehlermeldung 3:</b><br> Es wurde kein Dozent ausgew�hlt.<br><br><b>Fehlermeldung 4:</b><br> Die Funktionalit�t momentan nicht ausf�hrbar. Wenden Sie sich bitte an Ihren Systemadministrator!").setVisible(true);
			
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}
