package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
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
			String pwd = new String(ViewManager.getInstance().getCoreBaseTab().getPwdPasswort().getPassword());
			String user = ViewManager.getInstance().getCoreBaseTab().getTxtBenutzername().getText();
			
			User checkUser = ctrlBaseTab.login(user, pwd);
			
			if (checkUser == null) {
				// currently here happens nothing
			} else if (checkUser.getClass_().equals("orga")) {
				this.callOrga();
				ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
			} else if (checkUser.getClass_().equals("lecturer")) {
				this.callLecturer();
				ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(false);
				ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(true);
			}
			
		} else if (this.version.equals("logout")){
			ctrlBaseTab.logout();
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
			ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(false);
			ViewManager.getInstance().getCoreBaseTab().getContentPane().add(ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), "name_5256771068822");
			ViewManager.getInstance().getCoreBaseTab().getPwdPasswort().setText("");
			ViewManager.getInstance().getCoreBaseTab().getTxtBenutzername().setText("");
			ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getPanelLogin().setVisible(true);
			ViewManager.getInstance().getCoreBaseTab().getPanelLogout().setVisible(false);
		}
	}
		
	public void callOrga() {
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(true);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Start", null, ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumanfrage-Management", null, ViewManager.getInstance().getOrgaRquestTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(1);
		ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(false);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Nutzerverwaltung", null, ViewManager.getInstance().getOrgaUserTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlverwaltung", null, ViewManager.getInstance().getOrgaProfessorshipTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Raumverwaltung", null, ViewManager.getInstance().getOrgaRoomTab(), null);
	}
	
	public void callLecturer() {
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setVisible(true);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().removeAll();
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Start", null, ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Dozenten Bereich", null, ViewManager.getInstance().getLecturerStartTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().setSelectedIndex(1);
		ViewManager.getInstance().getCoreBaseTab().getStartScreenPanel().setVisible(false);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Dozentenstundenplan", null, ViewManager.getInstance().getLecturerTimetableTab(), null);
		ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane().addTab("Lehrstuhlplan", null, ViewManager.getInstance().getLecturerProfessorshipTimetableTab(), null);
	}
	
	public InfoDialog getInfoWindow(String msg) {

		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}