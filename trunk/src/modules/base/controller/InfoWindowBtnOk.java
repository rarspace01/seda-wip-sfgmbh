package modules.base.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;

public class InfoWindowBtnOk implements ActionListener {
	
	protected InfoWindow infoWinwod;
	
	public InfoWindowBtnOk(InfoWindow transmittedInfoWindow) {
		this.infoWinwod = transmittedInfoWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		infoWinwod.setVisible(false);
	}
}