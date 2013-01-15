package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.core.views.QuestionDialog;


/**
 * Action listener for the user organization control buttons on the right
 * 
 * @author User
 *
 */
public class UserTabBtnsControl implements ActionListener, IntfComDialogObserver {
	
	private String navAction;
	private boolean deleteUser = false;
	private User userMarkedForDeletion;
	private CtrlUser ctrlUser = new CtrlUser();
	protected InfoDialog infoWindow;
	
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
			ViewManager.getInstance().getOrgaUserCreateDialog().setVisible(true);
		}
		
		// Edit button is pressed
		if (this.navAction.equals("edit")) {
			ViewManager.getInstance().getOrgaUserEditFrame().setVisible(true);
		}
		
		// Delete button is pressed
		if (this.navAction.equals("delete")) {
			// Set a user delete variable to be sure the user really pressed the delete button later
			deleteUser = true;
			
			// Get the user
			int row = ViewManager.getInstance().getOrgaUserTab().getUserOrgaTable().getSelectedRow();
			if (row == -1) {
				AppModel.getInstance().getExceptionHandler().setNewException("Sie müssen zunächst einen Benutzer auswählen.", "Achtung!");
			}
			try {
				this.userMarkedForDeletion = (User) ViewManager.getInstance().getOrgaUserTableModel().getValueAt(row, 6);
			} catch (Exception ex) {
				AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
			}
			
			QuestionDialog dialog = new QuestionDialog("Wollen Sie den gewählten benutzer wirklich löschen?", "Achtung!");
			dialog.register(this);
			dialog.setVisible(true);
		}
		
		// Fehlermeldung Button is pressed
		if (this.navAction.equals("error")) {
			this.getInfoWindow("<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher k�nnen keine Nutzer angezeigt werden.").setVisible(true);
		}
	}
	
	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
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
