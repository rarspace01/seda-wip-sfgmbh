package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.base.views.InfoWindow;
import services.Bootstrap;

public class LVRaumanfrageWindowBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoWindow infoWindow;
	
	
	public LVRaumanfrageWindowBtns() {
		this.ctrlAction = "default";
	}
	public LVRaumanfrageWindowBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("abbr")){
			Bootstrap.serviceManager.getDozLVRaumanfrageMaske().setVisible(false);
		}
		
		// Los button is pressed
		if (this.ctrlAction.equals("los")){
			Bootstrap.serviceManager.getDozLVRaumanfrageMaske().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
