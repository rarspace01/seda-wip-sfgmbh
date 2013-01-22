package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.organisation.model.RequestTabTable;
import de.sfgmbh.comlayer.organisation.views.CounterproposalDialog;
import de.sfgmbh.comlayer.organisation.views.RequestTab;

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
	private RoomAllocation solveApprovedAllocation;
	private boolean cleanRoomAllocations;
	
	/**
	 * Create the action listener
	 */
	public RequestTabBtnsControl() {
		this.navAction = "default";
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
		this.cleanRoomAllocations = false;
	}
	
	/**
	 * Create the action listener based on a action string
	 * @param action
	 */
	public RequestTabBtnsControl(String action) {
		this.navAction = action;
		this.ctrlRoomAllocation = new CtrlRoomAllocation();
		this.cleanRoomAllocations = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance().getExceptionHandler();
		RequestTab requestTab = ViewManager.getInstance().getOrgaRquestTab();
		RequestTabTable requestTableModel = ViewManager.getInstance().getOrgaRequestTableModel();
		
		// Solve Button is pressed
		if (this.navAction.equals("solve")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) requestTableModel.getValueAt(row, 8);
					
					if (selectedRa.getApproved_().equals("denied")) {
						exceptionHandler.setNewException("Zu bereits abgelehnten Raumbelegungen gibt es kein Verfahren für Lösungen bzw. Gegenvorschläge. Hier muss der Dozent eine neue Raumbelegung erstellen.", "Fehler!");
						return;
					}
					
					if (selectedRa.getApproved_().equals("accepted")) {
						QuestionDialog dialog = new QuestionDialog("Sie sind im Begriff für eine bereits freigegebene Raumbelegung einen Gegenvorschlag zu erstellen. Dies ist möglich, kommt allerdings einer Verschiebung gleich. Die Raumbelegung wird dann nicht mehr öffentlich angezeigt werden, bis der Dozent den neuen Termin freigibt. <br /><br /> Wollen Sie das wirklich?", "Achtung!");
						this.solveApprovedAllocation = selectedRa;
						dialog.register(this);
						dialog.setVisible(true);
						return;
					}
					
					CounterproposalDialog counterproposalDialog = new CounterproposalDialog(selectedRa);
					counterproposalDialog.setVisible(true);
				} catch (Exception ex) {
					exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		
		// Accept Button is pressed
		if (this.navAction.equals("accept")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) requestTableModel.getValueAt(row, 8);
					ctrlRoomAllocation.acceptRoomAllocation(selectedRa);
				} catch (Exception ex) {
					exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		
		// Deny Button is pressed
		if (this.navAction.equals("deny")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Spalte auswählen.", "Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					RoomAllocation selectedRa = (RoomAllocation) requestTableModel.getValueAt(row, 8);
					
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
					exceptionHandler.setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Clean Button is pressed
		if (this.navAction.equals("clean")) {
			QuestionDialog dialog = new QuestionDialog("Mit dieser Aktion löschen Sie alle abgelehnten Raumbelegungen dauerhaft aus der Datenbank. Wollen Sie dies wirklich?", "Achtung!");
			this.cleanRoomAllocations = true;
			dialog.register(this);
			dialog.setVisible(true);
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
			if (this.solveApprovedAllocation != null) {
				CounterproposalDialog counterproposalDialog = new CounterproposalDialog(this.solveApprovedAllocation);
				counterproposalDialog.setVisible(true);
			}
			if (this.cleanRoomAllocations) {
				ctrlRoomAllocation.cleanRoomAllocations();
			}
		} else if (answer.equals("no")) {
			this.revokeAllocation = null;
			this.solveApprovedAllocation = null;
			this.cleanRoomAllocations = false;
		}
	}
}

