package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
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
	
	private String navAction;
	private boolean deleteUser = false;
	private User userMarkedForDeletion;
	private IntfCtrlUser ctrlUser = new CtrlUser();
	
	
	/**
	 * Create the action listener
	 */
	public UserTabBtnsControl() {
		this.navAction = "default";
	}
	
	/**
	 * Create the action listener based on a submitted action string
	 * @param action
	 */
	public UserTabBtnsControl(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Add button is pressed
		if (this.navAction.equals("add")) {
			UserCreateDialog newUserDialog = new UserCreateDialog();
			newUserDialog.setVisible(true);
		}
		
		// Edit button is pressed
		if (this.navAction.equals("edit")) {
			// Get the user
			int row = ViewManager.getInstance().getOrgaUserTab().getUserOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Benutzer auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaUserTab().getRowSorter().convertRowIndexToModel(row);
					User editUser = (User) ViewManager.getInstance().getOrgaUserTableModel().getValueAt(row, 6);
					UserCreateDialog editDialog = new UserCreateDialog(editUser);
					editDialog.setVisible(true);
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
		
		// Delete button is pressed
		if (this.navAction.equals("delete")) {
			// Set a user delete variable to be sure the user really pressed the delete button later
			deleteUser = true;
			
			// Get the user
			int row = ViewManager.getInstance().getOrgaUserTab().getUserOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Benutzer auswählen.", "Achtung!");
			} else {
				try {
					row = ViewManager.getInstance().getOrgaUserTab().getRowSorter().convertRowIndexToModel(row);
					this.userMarkedForDeletion = (User) ViewManager.getInstance().getOrgaUserTableModel().getValueAt(row, 6);
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
			this.deleteUser(this.userMarkedForDeletion);
		} else if (answer.equals("no")) {
			this.deleteUser = false;
		}
	}
	
	/**
	 * Delete a user
	 * @param user
	 * @return true if the deletion was successful
	 */
	public void deleteUser(User user) {
		
		if (this.deleteUser) {
			this.deleteUser = false;
			if (ctrlUser.delete(user)) {
				AppModel.getInstance().getExceptionHandler().setNewException("Der Benutzer wurde erfolgreich gelöscht!", "Erfolg!", "success");
			} else {
				
			}
		}
	}
}
