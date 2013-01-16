package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;

/**
 * Action listener for the buttons on the right in the request tab
 * 
 * @author hannes
 *
 */
public class RequestTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
	private String navAction;
	private CtrlRoomAllocation ctrlRoomAllocation;
	private RoomAllocation revokeAllocation;
	
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
			int row = ViewManager.getInstance().getOrgaRquestTab().getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaRquestTab().getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) ViewManager.getInstance().getOrgaRequestTableModel().getValueAt(row, 8);
					
					CounterproposalDialog counterproposalDialog = new CounterproposalDialog(selectedRa);
					counterproposalDialog.setVisible(true);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		
		// Accept Button is pressed
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
		
		
		// Deny Button is pressed
		if (this.navAction.equals("deny")) {
			int row = ViewManager.getInstance().getOrgaRquestTab().getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaRquestTab().getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) ViewManager.getInstance().getOrgaRequestTableModel().getValueAt(row, 8);
					
					// Check if room allocation is already accepted and warn in that case
					if (selectedRa.getApproved_().equals("accepted")) {
						this.revokeAllocation = selectedRa;
						QuestionDialog dialog = new QuestionDialog("Diese Raumbelegung ist bereits freigeben. <br />Sind Sie sicher, dass Sie die Freigabe zurückziehen wollen? Falls Sie diesen Schritt durchführen, vergewissern Sie sich bitte, dass Sie den Dozenten informieren!<br /><br />Raumbelegung wirklich zurückziehen?", "Achtung!");
						dialog.register(this);
						dialog.setVisible(true);
					} else if (selectedRa.getApproved_().equals("counter")) {
						this.revokeAllocation = selectedRa;
						QuestionDialog dialog = new QuestionDialog("Diese Raumbelegung ist als Gegenvorschlag eingetragen. Wollen Sie diesen wirklich zurückziehen?", "Achtung!");
						dialog.register(this);
						dialog.setVisible(true);
					} else {
						this.denyAllocation(selectedRa);
					}
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
	}
	
	private void denyAllocation (RoomAllocation ra) {
		if (ra == null) {
			AppModel.getInstance().getExceptionHandler().setNewException("Die Raumbelegung konnte nicht abgelehnt werden", "Fehler!");
			return;
		}
		ctrlRoomAllocation.denyRoomAllocation(ra);
	}
	
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.revokeAllocation != null) {
				this.denyAllocation(this.revokeAllocation);
				this.revokeAllocation = null;
			}
		} else if (answer.equals("no")) {
			this.revokeAllocation = null;
		}
	}
}

