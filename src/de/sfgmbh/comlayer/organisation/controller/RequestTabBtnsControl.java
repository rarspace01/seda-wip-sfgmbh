package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
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
public class RequestTabBtnsControl implements ActionListener,
		IntfComDialogObserver {

	private String navAction_;
	private IntfCtrlRoomAllocation ctrlRoomAllocation_;
	private IntfRoomAllocation revokeAllocation_;
	private IntfRoomAllocation solveApprovedAllocation_;
	private boolean cleanRoomAllocations_;

	/**
	 * Create the action listener
	 */
	public RequestTabBtnsControl() {
		this.navAction_ = "default";
		this.ctrlRoomAllocation_ = new CtrlRoomAllocation();
		this.cleanRoomAllocations_ = false;
	}

	/**
	 * Create the action listener based on a action string
	 * 
	 * @param action
	 */
	public RequestTabBtnsControl(String action) {
		this.navAction_ = action;
		this.ctrlRoomAllocation_ = new CtrlRoomAllocation();
		this.cleanRoomAllocations_ = false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();
		RequestTab requestTab = ViewManager.getInstance().getOrgaRquestTab();
		RequestTabTable requestTableModel = ViewManager.getInstance()
				.getOrgaRequestTableModel();

		// Solve Button is pressed
		if (this.navAction_.equals("solve")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Spalte auswählen.",
						"Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					IntfRoomAllocation selectedRa = (IntfRoomAllocation) requestTableModel
							.getValueAt(row, 8);

					if (selectedRa.getApproved_().equals("denied")) {
						exceptionHandler
								.setNewException(
										"Zu bereits abgelehnten Raumbelegungen gibt es kein Verfahren für Lösungen bzw. Gegenvorschläge. Hier muss der Dozent eine neue Raumbelegung erstellen.",
										"Fehler!");
						return;
					}

					if (selectedRa.getApproved_().equals("accepted")) {
						QuestionDialog dialog = new QuestionDialog(
								"Sie sind im Begriff für eine bereits freigegebene Raumbelegung einen Gegenvorschlag zu erstellen. Dies ist möglich, kommt allerdings einer Verschiebung gleich. Die Raumbelegung wird dann nicht mehr öffentlich angezeigt werden, bis der Dozent den neuen Termin freigibt. <br /><br /> Wollen Sie das wirklich?",
								"Achtung!");
						this.solveApprovedAllocation_ = selectedRa;
						dialog.register(this);
						dialog.setVisible(true);
						return;
					}

					CounterproposalDialog counterproposalDialog = new CounterproposalDialog(
							selectedRa);
					counterproposalDialog.setVisible(true);
				} catch (Exception ex) {
					exceptionHandler.setNewException(
							"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
									+ ex.toString(), "Fehler!");
				}
			}
		}

		// Accept Button is pressed
		if (this.navAction_.equals("accept")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Spalte auswählen.",
						"Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					IntfRoomAllocation selectedRa = (IntfRoomAllocation) requestTableModel
							.getValueAt(row, 8);
					ctrlRoomAllocation_.acceptRoomAllocation(selectedRa);
				} catch (Exception ex) {
					exceptionHandler.setNewException(
							"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
									+ ex.toString(), "Fehler!");
				}
			}
		}

		// Deny Button is pressed
		if (this.navAction_.equals("deny")) {
			int row = requestTab.getRoomAllocationTable().getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Spalte auswählen.",
						"Achtung!");
			} else {
				try {
					row = requestTab.getRowSorter().convertRowIndexToModel(row);
					IntfRoomAllocation selectedRa = (IntfRoomAllocation) requestTableModel
							.getValueAt(row, 8);

					// Check if room allocation is already accepted and warn in
					// that case
					if (selectedRa.getApproved_().equals("accepted")) {
						this.revokeAllocation_ = selectedRa;
						QuestionDialog dialog = new QuestionDialog(
								"Diese Raumbelegung ist bereits freigeben. <br />Sind Sie sicher, dass Sie die Freigabe zurückziehen wollen? Falls Sie diesen Schritt durchführen, vergewissern Sie sich bitte, dass Sie den Dozenten informieren!<br /><br />Raumbelegung wirklich zurückziehen?",
								"Achtung!");
						dialog.register(this);
						dialog.setVisible(true);
					} else if (selectedRa.getApproved_().equals("counter")) {
						this.revokeAllocation_ = selectedRa;
						QuestionDialog dialog = new QuestionDialog(
								"Diese Raumbelegung ist als Gegenvorschlag eingetragen. Wollen Sie diesen wirklich zurückziehen?",
								"Achtung!");
						dialog.register(this);
						dialog.setVisible(true);
					} else {
						this.denyAllocation(selectedRa);
					}
				} catch (Exception ex) {
					exceptionHandler.setNewException(
							"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
									+ ex.toString(), "Fehler!");
				}
			}
		}

		// Clean Button is pressed
		if (this.navAction_.equals("clean")) {
			QuestionDialog dialog = new QuestionDialog(
					"Mit dieser Aktion löschen Sie alle abgelehnten Raumbelegungen dauerhaft aus der Datenbank. Wollen Sie dies wirklich?",
					"Achtung!");
			this.cleanRoomAllocations_ = true;
			dialog.register(this);
			dialog.setVisible(true);
		}
	}

	private void denyAllocation(IntfRoomAllocation roomAllocation) {
		if (roomAllocation == null) {
			AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Die Raumbelegung konnte nicht abgelehnt werden",
							"Fehler!");
			return;
		}
		ctrlRoomAllocation_.denyRoomAllocation(roomAllocation);
	}

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver#answered(java.lang.String)
	 */
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.revokeAllocation_ != null) {
				this.denyAllocation(this.revokeAllocation_);
				this.revokeAllocation_ = null;
			}
			if (this.solveApprovedAllocation_ != null) {
				CounterproposalDialog counterproposalDialog = new CounterproposalDialog(
						this.solveApprovedAllocation_);
				counterproposalDialog.setVisible(true);
			}
			if (this.cleanRoomAllocations_) {
				ctrlRoomAllocation_.cleanRoomAllocations();
			}
		} else if (answer.equals("no")) {
			this.revokeAllocation_ = null;
			this.solveApprovedAllocation_ = null;
			this.cleanRoomAllocations_ = false;
		}
	}
}
