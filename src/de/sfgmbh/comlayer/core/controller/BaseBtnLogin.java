package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.views.InfoDialog;



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
			String pwd = new String(ServiceManager.getInstance().getCoreBaseTab().getPwdPasswort().getPassword());
			String user = ServiceManager.getInstance().getCoreBaseTab().getTxtBenutzername().getText();
			
			User checkUser = ctrlBaseTab.login(user, pwd);
			
			if (checkUser == null) {
				// currently here happens nothing
			} else if (checkUser.getClass_().equals("orga")) {
				this.callOrga();
				ServiceManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ServiceManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
			} else if (checkUser.getClass_().equals("lecturer")) {
				this.callLecturer();
				ServiceManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ServiceManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
			}
			
		} else if (this.version.equals("logout")){
			ctrlBaseTab.logout();
			ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.removeAll();
			ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setVisible(false);
			ServiceManager.getInstance().getCoreBaseTab().contentPane.add(ServiceManager.getInstance().getCoreBaseTab().startScreenPanel, "name_5256771068822");
			ServiceManager.getInstance().getCoreBaseTab().getPwdPasswort().setText("");
			ServiceManager.getInstance().getCoreBaseTab().getTxtBenutzername().setText("");
			ServiceManager.getInstance().getCoreBaseTab().startScreenPanel.setVisible(true);
			ServiceManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(true);
			ServiceManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(false);
		}
	}
		
	public void callOrga() {
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.removeAll();
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, ServiceManager.getInstance().getCoreBaseTab().startScreenPanel, null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, ServiceManager.getInstance().getOrgaRquestTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
		ServiceManager.getInstance().getCoreBaseTab().startScreenPanel.setVisible(false);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Nutzerverwaltung", null, ServiceManager.getInstance().getOrgaUserTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Lehrstuhlverwaltung", null, ServiceManager.getInstance().getOrgaProfessorshipTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Raumverwaltung", null, ServiceManager.getInstance().getOrgaRoomTab(), null);
	}
	
	public void callLecturer() {
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.removeAll();
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, ServiceManager.getInstance().getCoreBaseTab().startScreenPanel, null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Dozenten Bereich", null, ServiceManager.getInstance().getLecturerStartTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
		ServiceManager.getInstance().getCoreBaseTab().startScreenPanel.setVisible(false);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Dozentenstundenplan", null, ServiceManager.getInstance().getLecturerTimetableTab(), null);
		ServiceManager.getInstance().getCoreBaseTab().mainTabbedContainerPane.addTab("Lehrstuhlplan", null, ServiceManager.getInstance().getLecturerProfessorshipTimetableTab(), null);
	}
	
	public InfoDialog getInfoWindow(String msg) {

		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}