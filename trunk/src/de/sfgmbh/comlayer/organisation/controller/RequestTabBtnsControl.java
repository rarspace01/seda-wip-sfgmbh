package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.InfoDialog;

/**
 * Action listener for the buttons on the right in the request tab
 * 
 * @author hannes
 *
 */
public class RequestTabBtnsControl implements ActionListener {
	
	private String navAction;
	private CtrlRoomAllocation ctrlRoomAllocation;
	protected InfoDialog infoWindow;
	
	/**
	 * Create the action listener
	 */
	public RequestTabBtnsControl() {
		this.navAction = "default";
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
	}
	
	/**
	 * Create the action listener based on a action string
	 * @param action
	 */
	public RequestTabBtnsControl(String action) {
		this.navAction = action;
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Solve Button is pressed
		if (this.navAction.equals("solve")) {
			ViewManager.getInstance().getOrgaCounterproposalFrame().setVisible(true);
		}
		
		
		// Deny Button is pressed
		if (this.navAction.equals("accept")) {
			int row = ViewManager.getInstance().getOrgaRquestTab().getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaRquestTab().getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) ViewManager.getInstance().getOrgaRequestTableModel().getValueAt(row, 8);
					ctrlRoomAllocation.acceptRoomAllocation(selectedRa);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Error Button is pressed
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
