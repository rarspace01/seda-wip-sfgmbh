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
	protected String version;
	
	public BaseBtnLogin() {
		this.ctrlBaseTab = new CtrlBaseTab();
	}
	
	public BaseBtnLogin(String version) {
		this.version = version;
		this.ctrlBaseTab = new CtrlBaseTab();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (this.version == null || this.version.equals("login")) {
			String pwd = new String(Bootstrap.serviceManager.getCoreBaseTab().getPwdPasswort().getPassword());
			String user = Bootstrap.serviceManager.getCoreBaseTab().getTxtBenutzername().getText();
			
			User checkUser = ctrlBaseTab.login(user, pwd);
			
			if (checkUser == null) {
				// currently here happens nothing
			} else if (checkUser.getClass_().equals("orga")) {
				this.callOrga();
				Bootstrap.serviceManager.getCoreBaseTab().getPanelLogin().setVisible(false);
				Bootstrap.serviceManager.getCoreBaseTab().getPanelLogout().setVisible(true);
			} else if (checkUser.getClass_().equals("lecturer")) {
				this.callLecturer();
				Bootstrap.serviceManager.getCoreBaseTab().getPanelLogin().setVisible(false);
				Bootstrap.serviceManager.getCoreBaseTab().getPanelLogout().setVisible(true);
			}
			
		} else if (this.version.equals("logout")){
			ctrlBaseTab.logout();
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(false);
			Bootstrap.serviceManager.getCoreBaseTab().contentPane.add(Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, "name_5256771068822");
			Bootstrap.serviceManager.getCoreBaseTab().getPwdPasswort().setText("");
			Bootstrap.serviceManager.getCoreBaseTab().getTxtBenutzername().setText("");
			Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().getPanelLogin().setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().getPanelLogout().setVisible(false);
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