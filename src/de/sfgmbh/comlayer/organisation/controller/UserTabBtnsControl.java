package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlUser;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.organisation.views.UserCreateDialog;


/**
 * Action listener for the user organization control buttons on the right
 * 
 * @author hannes
 *
 */
public class UserTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
	private String navAction_;
	private boolean deleteUser_ = false;
	private IntfUser userMarkedForDeletion_;
	private IntfCtrlUser ctrlUser_ = new CtrlUser();
	
	
	/**
	 * Create the action listener
	 */
	public UserTabBtnsControl() {
		this.navAction_ = "default";
	}
	
	/**
	 * Create the action listener based on a submitted action string
	 * @param action
	 */
	public UserTabBtnsControl(String action) {
		this.navAction_ = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Add button is pressed
		if (this.navAction_.equals("add")) {
			UserCreateDialog newUserDialog = new UserCreateDialog();
			newUserDialog.setVisible(true);
		}
		
		// Edit button is pressed
		if (this.navAction_.equals("edit")) {
			// Get the user
			int row = ViewManager.getInstance().getOrgaUserTab().getUserOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Benutzer auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaUserTab().getRowSorter().convertRowIndexToModel(row);
					IntfUser editUser = (IntfUser) ViewManager.getInstance().getOrgaUserTableModel().getValueAt(row, 6);
					UserCreateDialog editDialog = new UserCreateDialog(editUser);
					editDialog.setVisible(true);
					editDialog.setTitle("Benutzerinfo bearbeiten");
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Delete button is pressed
		if (this.navAction_.equals("delete")) {
			// Set a user delete variable to be sure the user really pressed the delete button later
			deleteUser_ = true;
			
			// Get the user
			int row = ViewManager.getInstance().getOrgaUserTab().getUserOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Benutzer auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaUserTab().getRowSorter().convertRowIndexToModel(row);
					this.userMarkedForDeletion_ = (IntfUser) ViewManager.getInstance().getOrgaUserTableModel().getValueAt(row, 6);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
				
				QuestionDialog dialog = new QuestionDialog("Wollen Sie den gewählten benutzer wirklich löschen?", "Achtung!");
				dialog.register(this);
				dialog.setVisible(true);
			}
		}
	}
	
	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			this.deleteUser(this.userMarkedForDeletion_);
		} else if (answer.equals("no")) {
			this.deleteUser_ = false;
		}
	}
	
	/**
	 * Delete a user
	 * @param user
	 * @return true if the deletion was successful
	 */
	public void deleteUser(IntfUser user) {
		
		if (this.deleteUser_) {
			this.deleteUser_ = false;
			if (ctrlUser_.delete(user)) {
				AppModel.getInstance().getExceptionHandler().setNewException("Der Benutzer wurde erfolgreich gelöscht!", "Erfolg!", "success");
			} else {
				
			}
		}
	}
}
