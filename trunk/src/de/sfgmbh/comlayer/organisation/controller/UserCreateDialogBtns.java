package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.organisation.controller.CtrlUser;


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
			ServiceManager.getInstance().getOrgaUserCreateDialog().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("save")){
			
			User newUser = new User();
			try {
				newUser.setLogin_(ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtLogin().getText());
				newUser.setMail_(ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtEmail().getText());
				if (ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtPasswort().getText().length() < 6) {
					AppModel.getInstance().appExcaptions.setNewException("Das Passwort muss mindestens 6 Zeichen lang sein!", "Fehler!" );
				} else {
					newUser.setPwHashAndSalt(ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtPasswort().getText());
				}
				newUser.setfName_(ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtFirstName().getText());
				newUser.setlName_(ServiceManager.getInstance().getOrgaUserCreateDialog().getTxtLastName().getText());
				newUser.setClass_(ServiceManager.getInstance().getOrgaUserCreateDialog().getComboBoxUserClass().getSelectedItem().toString());
				if (!ServiceManager.getInstance().getOrgaUserCreateDialog().getComboBoxLehrstuhl().getSelectedItem().toString().equals("<keiner>")) {
					Chair newChair = AppModel.getInstance().repositoryChair.getForAcronym(
							ServiceManager.getInstance().getOrgaUserCreateDialog().getComboBoxLehrstuhl().getSelectedItem().toString());
					if (newChair != null) {
						newUser.setChair_(newChair);
					} else {
						AppModel.getInstance().appExcaptions.setNewException("Der Lehrstuhl ist ungültig, geben Sie einen gültigen Lehrstuhl ein oder setzen Sie das Feld auf &lt;keiner&gt;, wenn sie keinen Lehrstuhl setzen möchten", "Fehler!" );
					}
				}
				if (ctrlUser.createUser(newUser)) {
					AppModel.getInstance().appExcaptions.setNewException("Der Benutzer wurde im System angelegt!", "Erfolg!" );
					ServiceManager.getInstance().getOrgaUserCreateDialog().setVisible(false);
				}
			} catch (Exception ex) {
				AppModel.getInstance().appExcaptions.setNewException("Es ist ein unerwartetes Problem aufgetreten.<br /><br />Fehler:<br />" + ex.toString(), "Fehler!" );
				ex.printStackTrace();
			}
		}
	}
}
