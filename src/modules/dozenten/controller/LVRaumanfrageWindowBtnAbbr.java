package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.Bootstrap;

public class LVRaumanfrageWindowBtnAbbr implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Bootstrap.serviceManager.getDozLVRaumanfrageMaske().setVisible(false);
		
	}
}
