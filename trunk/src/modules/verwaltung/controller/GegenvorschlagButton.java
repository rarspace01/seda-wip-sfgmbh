package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.Bootstrap;

public class GegenvorschlagButton implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Bootstrap.serviceManager.getVerwGegenvorschlagMaske().setVisible(true);
		
	}
}
