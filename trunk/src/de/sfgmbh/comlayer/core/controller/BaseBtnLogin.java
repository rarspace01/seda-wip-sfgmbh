package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class BaseBtnLogin implements ActionListener {
	
	protected InfoDialog infoWindow;
	protected CtrlBaseTab ctrlBaseTab;
	
	public BaseBtnLogin() {
		this.ctrlBaseTab = new CtrlBaseTab();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String pwd = new String(Bootstrap.serviceManager.getCoreBaseTab().getPwdPasswort().getPassword());
		String user = Bootstrap.serviceManager.getCoreBaseTab().getTxtBenutzername().getText();
		
		if (pwd.equals("Doz") && user.equals("Doz")) {
			
			this.callLecturer();
			
		} else if (pwd.equals("Verw") && user.equals("Verw")) {
			
			this.callOrga();
			
		} else if (pwd.equals("Stud") && user.equals("Stud")) {
			
			this.getInfoWindow("Das Studentenprofil ist ein Wunschkriterium, das vermutlich nicht in der ersten Version implementiert wird.").setVisible(true);
		
		} else {
			
			User checkUser = ctrlBaseTab.login(user, pwd);
			
			if (checkUser == null) {
				this.getInfoWindow("<strong> Fehlermeldung: </strong><br>Der eingegebene Benutzername oder das eingegebene Passwort ist inkorrekt! <p>Bitte versuche es noch einmal.</p>").setVisible(true);
			} else if (checkUser.getClass_().equals("orga")) {
				this.callOrga();
			} else if (checkUser.getClass_().equals("lecturer")) {
				this.callLecturer();
			}
				
		}
	}
		
	public void callOrga() {
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, Bootstrap.serviceManager.getOrgaRquestTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
		Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Nutzerverwaltung", null, Bootstrap.serviceManager.getOrgaUserTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, Bootstrap.serviceManager.getOrgaProfessorshipTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumverwaltung", null, Bootstrap.serviceManager.getOrgaRoomTab(), null);
	}
	
	public void callLecturer() {
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Dozenten Bereich", null, Bootstrap.serviceManager.getLecturerStartTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
		Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, Bootstrap.serviceManager.getLecturerTimetableTab(), null);
		Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Lehrstuhlplan", null, Bootstrap.serviceManager.getLecturerProfessorshipTimetableTab(), null);
	}
	
	public InfoDialog getInfoWindow(String msg) {

		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}