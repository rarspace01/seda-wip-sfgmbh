package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlUser;
import de.sfgmbh.comlayer.organisation.views.UserCreateDialog;

/**
 * Action listener for the create and edit user dialog
 * 
 * @author hannes
 *
 */
public class UserCreateDialogBtns implements ActionListener {
	
	private String ctrlAction_;
	private IntfCtrlUser ctrlUser_ = new CtrlUser();
	private UserCreateDialog motherDialog_;
	
	
	/**
	 * Create the action listener
	 * @param motherDialog
	 */
	public UserCreateDialogBtns(UserCreateDialog motherDialog) {
		this.ctrlAction_ = "default";
		this.motherDialog_ = motherDialog;
	}
	
	/**
	 * Create the action listener for e specific submitted action
	 * @param motherDialog
	 * @param action
	 */
	public UserCreateDialogBtns(UserCreateDialog motherDialog, String action) {
		this.ctrlAction_ = action;
		this.motherDialog_ = motherDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Cancel button is pressed
		if (this.ctrlAction_.equals("cancle")){
			this.motherDialog_.dispose();
		}
		
		// Save button is pressed
		if (this.ctrlAction_.equals("save")){
			
			IntfUser createUser = null;
			boolean create;
			
			// When there is no editUser this is the create frame
			if (this.motherDialog_.getEditUser() == null) {
				create = true;
				createUser = new User();
			} else {
				create = false;
				createUser = this.motherDialog_.getEditUser();
			}
			
			try {
				createUser.setLogin_(this.motherDialog_.getTxtLogin().getText());
				createUser.setMail_(this.motherDialog_.getTxtEmail().getText());
				createUser.setfName_(this.motherDialog_.getTxtFirstName().getText());
				createUser.setlName_(this.motherDialog_.getTxtLastName().getText());
				
				// Only overwrite the class if a user gets created
				// Already existent user currently cannot get a class change.
				// Though it would be possible it could get a complicated so it probably will not make it in v. 1.0
				if (create) {
					createUser.setClass_(this.motherDialog_.getComboBoxUserClass().getSelectedItem().toString());
				}
				
				// Handle the chair of a user
				if (!this.motherDialog_.getComboBoxLehrstuhl().getSelectedItem().toString().equals("<keiner>")) {
					IntfChair newChair = AppModel.getInstance().getRepositoryChair().getForAcronym(
							this.motherDialog_.getComboBoxLehrstuhl().getSelectedItem().toString());
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
					createUser.setPwHashAndSalt(this.motherDialog_.getTxtPasswort().getText());
				} else if (this.motherDialog_.getTxtPasswort().getText().length() > 0) {
					createUser.setPwHashAndSalt(this.motherDialog_.getTxtPasswort().getText());
				}
				
				// Handle disabling of users (only for old users)
				if (!create) {
					createUser.setDisabled_(this.motherDialog_.getChckbxUserDisabled().isSelected());
				} 
				
				// Try to save the edited or new user
				if (ctrlUser_.saveUser(createUser)) {
					AppModel.getInstance().getExceptionHandler().setNewException("Der Benutzer wurde gespeichert!", "Erfolg!", "success" );
					this.motherDialog_.setVisible(false);
				}
			} catch (Exception ex) {
				AppModel.getInstance().getExceptionHandler().setNewException("Es ist ein unerwartetes Problem aufgetreten.<br /><br />Fehler:<br />" + ex.toString(), "Fehler!" );
				ex.printStackTrace();
			}
		} else {
			
		}
		
	}
}
