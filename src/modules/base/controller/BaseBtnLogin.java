package modules.base.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;
import services.Bootstrap;


public class BaseBtnLogin implements ActionListener {
	
	protected InfoWindow infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String pwd = new String(Bootstrap.serviceManager.getBase().getPwdPasswort().getPassword());
		String user = Bootstrap.serviceManager.getBase().getTxtBenutzername().getText();
		
		if (pwd.equals("Doz") && user.equals("Doz")) {
			
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getBase().startScreenPanel, null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Dozenten Bereich", null, Bootstrap.serviceManager.getDozenten(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(1);
			Bootstrap.serviceManager.getBase().startScreenPanel.setVisible(false);
			
		} else if (pwd.equals("Verw") && user.equals("Verw")) {
			
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getBase().startScreenPanel, null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Studenten Profil", null, Bootstrap.serviceManager.getStudProfil(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(1);
			Bootstrap.serviceManager.getBase().startScreenPanel.setVisible(false);
			
		} else if (pwd.equals("Stud") && user.equals("Stud")) {
			
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setVisible(true);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.removeAll();
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Start", null, Bootstrap.serviceManager.getBase().startScreenPanel, null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.addTab("Raumanfrage-Management", null, Bootstrap.serviceManager.getVerwAnfrage(), null);
			Bootstrap.serviceManager.getBase().mainTabbedContainerPane.setSelectedIndex(1);
			Bootstrap.serviceManager.getBase().startScreenPanel.setVisible(false);
			
		} else {
			this.getInfoWindow().setVisible(true);	
		}
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("Der eingegebene Benutzername oder das eingegebene Passwort ist inkorrekt! Bitte versuche es nochmal.");
		}
		return this.infoWindow;
	}
}