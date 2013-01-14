package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

public class RequestTabBtnsControl implements ActionListener {
	
	private String navAction;
	private CtrlRoomAllocation ctrlRoomAllocation;
	protected InfoDialog infoWindow;
	
	
	public RequestTabBtnsControl() {
		this.navAction = "default";
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
	}
	public RequestTabBtnsControl(String action) {
		this.navAction = action;
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Konfliktl�sen Button is pressed
		if (this.navAction.equals("solve")) {
			ViewManager.getInstance().getOrgaCounterproposalFrame().setVisible(true);
		}
		
		
		// Ablehnen Button is pressed
		if (this.navAction.equals("accept")) {
			int row = ViewManager.getInstance().getOrgaRquestTab().getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().appExcaptions.setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			}
			try {
				RoomAllocation selectedRa = (RoomAllocation) ViewManager.getInstance().getOrgaRequestTableModel().getValueAt(row, 8);
				ctrlRoomAllocation.acceptRoomAllocation(selectedRa);
			} catch (Exception ex) {
				AppModel.getInstance().appExcaptions.setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
			}
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("error")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br> Es konnte keine Datenverbindung hergestellt werden. Somit k�nnen keine Anfragen angezeigt werden.</b>").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
		
	}
}
