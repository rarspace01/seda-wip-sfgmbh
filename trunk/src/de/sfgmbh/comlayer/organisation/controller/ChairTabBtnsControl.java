package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.organisation.controller.CtrlChair;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.organisation.views.ChairCreateDialog;
// import de.sfgmbh.comlayer.core.views.InfoDialog;


/**
 * Action Listener for the user organization control buttons on the right
 * 
 * @author anna
 * @author hannes
 *
 */
public class ChairTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
	private String navAction;
	private boolean deleteChair = false;
	private Chair chairMarkedForDeletion;
	private CtrlChair ctrlChair = new CtrlChair();
	
	/**
	 * Create the action listener based on a submitted action string
	 */
	public ChairTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Add button is pressed
		if (this.navAction.equals("add")) {
			ChairCreateDialog newChairDialog = new ChairCreateDialog();
			newChairDialog.setVisible(true);
		}
		
		// Edit button is pressed
		if (this.navAction.equals("edit")) {
			// Get the chair
			int row = ViewManager.getInstance().getOrgaChairTab().getChairOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Lehrstuhl auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaChairTab().getRowSorter().convertRowIndexToModel(row);
					Chair editChair = (Chair) ViewManager.getInstance().getOrgaChairTableModel().getValueAt(row, 4);
					ChairCreateDialog editDialog = new ChairCreateDialog("edit", editChair);
					editDialog.setVisible(true);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Delete button is pressed
		if (this.navAction.equals("delete")) {
			//set a chair delete variable to be sure the user really pressed the delete button later
			deleteChair = true;
			
			//Get the chair
			int row = ViewManager.getInstance().getOrgaChairTab().getChairOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Lehrstuhl auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaChairTab().getRowSorter().convertRowIndexToModel(row);
					this.chairMarkedForDeletion = (Chair) ViewManager.getInstance().getOrgaChairTableModel().getValueAt(row, 4);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
				
				QuestionDialog dialog = new QuestionDialog("Wollen Sie den gewählten Lehrstuhl wirklich löschen?", "Achtung!");
				dialog.register(this);
				dialog.setVisible(true);
		
			}
		}
	}	
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			this.deleteChair(this.chairMarkedForDeletion);
		} else if (answer.equals("no")) {
			this.deleteChair = false;
			this.chairMarkedForDeletion = null;
		}
	}
	
	/**
	 * Delete a chair
	 * @param chair
	 * @return true if the deletion was successful
	 */
	public void deleteChair(Chair chair) {
		
		if (this.deleteChair) {
			this.deleteChair = false;
			if (ctrlChair.delete(chair)) {
				this.chairMarkedForDeletion = null;
				AppModel.getInstance().getExceptionHandler().setNewException("Der Lehrstuhl wurde erfolgreich gelöscht!", "Erfolg!", "success");
			} 
		}
	}
}
