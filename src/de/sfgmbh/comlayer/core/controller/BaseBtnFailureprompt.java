package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.comlayer.core.views.InfoDialog;

public class BaseBtnFailureprompt implements ActionListener {

	private String ctrlAction;
	protected InfoDialog infoWindow;
	
	
	public BaseBtnFailureprompt() {
		this.ctrlAction = "default";
	}
	public BaseBtnFailureprompt(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
