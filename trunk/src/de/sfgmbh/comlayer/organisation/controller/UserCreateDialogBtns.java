package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;
import de.sfgmbh.comlayer.organisation.views.UserCreateDialog;

/**
 * Action listener for the create and edit user dialog
 * 
 * @author hannes
 *
 */
public class UserCreateDialogBtns implements ActionListener {
	
	private String ctrlAction;
	private CtrlUser ctrlUser = new CtrlUser();
	private UserCreateDialog motherDialog;
	
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public UserCreateDialogBtns(UserCreateDialog motherDialog) {
		this.ctrlAction = "default";
		this.motherDialog = motherDialog;
	}
	
	/**
	 * Create the action listener for e specific submitted action
	 * @param motherDialog
	 * @param action
	 */
	public UserCreateDialogBtns(UserCreateDialog motherDialog, String action) {
		this.ctrlAction = action;
		this.motherDialog = motherDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Cancel button is pressed
		if (this.ctrlAction.equals("cancle")){
			this.motherDialog.dispose();
		}
		
		// Save button is pressed
		if (this.ctrlAction.equals("save")){
			
			User createUser = null;
			boolean create;
			
			// When there is no editUser this is the create frame
			if (this.motherDialog.getEditUser() == null) {
				create = true;
				createUser = new User();
			} else {
				create = false;
				createUser = this.motherDialog.getEditUser();
			}
			
			try {
				createUser.setLogin_(this.motherDialog.getTxtLogin().getText());
				createUser.setMail_(this.motherDialog.getTxtEmail().getText());
				createUser.setfName_(this.motherDialog.getTxtFirstName().getText());
				createUser.setlName_(this.motherDialog.getTxtLastName().getText());
				createUser.setClass_(this.motherDialog.getComboBoxUserClass().getSelectedItem().toString());
				
				// Handle the chair of a user
				if (!this.motherDialog.getComboBoxLehrstuhl().getSelectedItem().toString().equals("<keiner>")) {
					Chair newChair = AppModel.getInstance().getRepositoryChair().getForAcronym(
							this.motherDialog.getComboBoxLehrstuhl().getSelectedItem().toString());
					if (newChair != null) {
						createUser.setChair_(newChair);
					} else {
						AppModel.getInstance()
								.getExceptionHandler()
								.setNewException(
										"Der Lehrstuhl ist ungültig, geben Sie einen gültigen Lehrstuhl ein oder setzen Sie das Feld auf &lt;keiner&gt;, wenn sie keinen Lehrstuhl setzen möchten",
										"Fehler!");
					}
				}
				
				// Handle the password (this depends if it is a new or an old user)
				if (create) {
					createUser.setPwHashAndSalt(this.motherDialog.getTxtPasswort().getText());
				} else if (this.motherDialog.getTxtPasswort().getText().length() > 0) {
					createUser.setPwHashAndSalt(this.motherDialog.getTxtPasswort().getText());
				}
				
				// Handle disabling of users (only for old users)
				if (!create) {
					createUser.setDisabled_(this.motherDialog.getChckbxUserDisabled().isSelected());
				} 
				
				// Try to save the edited or new user
				if (ctrlUser.saveUser(createUser)) {
					AppModel.getInstance().getExceptionHandler().setNewException("Der Benutzer wurde gespeichert!", "Erfolg!" );
					this.motherDialog.setVisible(false);
				}
			} catch (Exception ex) {
				AppModel.getInstance().getExceptionHandler().setNewException("Es ist ein unerwartetes Problem aufgetreten.<br /><br />Fehler:<br />" + ex.toString(), "Fehler!" );
				ex.printStackTrace();
			}
		} else {
			
		}
		
	}
}
