package comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import comlayer.core.views.InfoDialog;

public class InfoDialogBtnOk implements ActionListener {
	
	protected InfoDialog infoWinwod;
	
	public InfoDialogBtnOk(InfoDialog transmittedInfoWindow) {
		this.infoWinwod = transmittedInfoWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		infoWinwod.setVisible(false);
	}
}