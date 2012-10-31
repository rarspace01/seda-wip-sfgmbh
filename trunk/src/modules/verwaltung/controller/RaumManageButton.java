package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.Bootstrap;

public class RaumManageButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Bootstrap.serviceManager.getVerwRaumMaske().setVisible(true);
		
	}
}
