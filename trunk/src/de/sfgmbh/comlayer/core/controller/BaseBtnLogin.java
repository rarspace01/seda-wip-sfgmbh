package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.init.Bootstrap;



public class BaseBtnLogin implements ActionListener {
	
	protected InfoDialog infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String pwd = new String(Bootstrap.serviceManager.getCoreBaseTab().getPwdPasswort().getPassword());
		String user = Bootstrap.serviceManager.getCoreBaseTab().getTxtBenutzername().getText();
		
		if (pwd.equals("Doz") && user.equals("Doz")) {
			
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Dozenten Bereich", null, Bootstrap.serviceManager.getLecturerStartTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
			Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
			
		} else if (pwd.equals("Verw") && user.equals("Verw")) {
			
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel, null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, Bootstrap.serviceManager.getOrgaRquestTab(), null);
			Bootstrap.serviceManager.getCoreBaseTab().mainTabbedContainerPane.setSelectedIndex(1);
			Bootstrap.serviceManager.getCoreBaseTab().startScreenPanel.setVisible(false);
			
		} else if (pwd.equals("Stud") && user.equals("Stud")) {
			
			this.getInfoWindow("Das Studentenprofil ist ein Wunschkriterium, das vermutlich nicht in der ersten Version implementiert wird.").setVisible(true);
		
			
		} else {
			this.getInfoWindow("Der eingegebene Benutzername oder das eingegebene Passwort ist inkorrekt! Bitte versuche es nochmal.").setVisible(true);	
		}
	}
	
	public InfoDialog getInfoWindow(String msg) {

		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}
}