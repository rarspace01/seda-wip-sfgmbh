package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;
import de.sfgmbh.comlayer.core.controller.ViewManager;


public class UserCreateDialogBtns implements ActionListener {
	
	private String ctrlAction;
	private CtrlUser ctrlUser = new CtrlUser();
	
	
	
	public UserCreateDialogBtns() {
		this.ctrlAction = "default";
	}
	public UserCreateDialogBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("cancle")){
			ViewManager.getInstance().getOrgaUserCreateDialog().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			
			User newUser = new User();
			try {
				newUser.setLogin_(ViewManager.getInstance().getOrgaUserCreateDialog().getTxtLogin().getText());
				newUser.setMail_(ViewManager.getInstance().getOrgaUserCreateDialog().getTxtEmail().getText());
				if (ViewManager.getInstance().getOrgaUserCreateDialog().getTxtPasswort().getText().length() < 6) {
					AppModel.getInstance().getExceptionHandler().setNewException("Das Passwort muss mindestens 6 Zeichen lang sein!", "Fehler!" );
				} else {
					newUser.setPwHashAndSalt(ViewManager.getInstance().getOrgaUserCreateDialog().getTxtPasswort().getText());
				}
				newUser.setfName_(ViewManager.getInstance().getOrgaUserCreateDialog().getTxtFirstName().getText());
				newUser.setlName_(ViewManager.getInstance().getOrgaUserCreateDialog().getTxtLastName().getText());
				newUser.setClass_(ViewManager.getInstance().getOrgaUserCreateDialog().getComboBoxUserClass().getSelectedItem().toString());
				if (!ViewManager.getInstance().getOrgaUserCreateDialog().getComboBoxLehrstuhl().getSelectedItem().toString().equals("<keiner>")) {
					Chair newChair = AppModel.getInstance().getRepositoryChair().getForAcronym(
							ViewManager.getInstance().getOrgaUserCreateDialog().getComboBoxLehrstuhl().getSelectedItem().toString());
					if (newChair != null) {
						newUser.setChair_(newChair);
					} else {
						AppModel.getInstance().getExceptionHandler().setNewException("Der Lehrstuhl ist ungültig, geben Sie einen gültigen Lehrstuhl ein oder setzen Sie das Feld auf &lt;keiner&gt;, wenn sie keinen Lehrstuhl setzen möchten", "Fehler!" );
					}
				}
				if (ctrlUser.createUser(newUser)) {
					AppModel.getInstance().getExceptionHandler().setNewException("Der Benutzer wurde im System angelegt!", "Erfolg!" );
					ViewManager.getInstance().getOrgaUserCreateDialog().setVisible(false);
				}
			} catch (Exception ex) {
				AppModel.getInstance().getExceptionHandler().setNewException("Es ist ein unerwartetes Problem aufgetreten.<br /><br />Fehler:<br />" + ex.toString(), "Fehler!" );
				ex.printStackTrace();
			}
		}
	}
}
