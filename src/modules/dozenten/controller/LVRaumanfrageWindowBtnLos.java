package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import services.Bootstrap;

public class LVRaumanfrageWindowBtnLos implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Bootstrap.serviceManager.getDozLVRaumanfrageMaske().setVisible(false);
		
	}
}
