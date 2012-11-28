package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;
import services.Bootstrap;

public class LVWindowBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoWindow infoWindow;
	
	
	public LVWindowBtns() {
		this.ctrlAction = "default";
	}
	public LVWindowBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("abbr")){
			Bootstrap.serviceManager.getDozLVMaske().setVisible(false);
		}
		
		// Speichern button is pressed
		if (this.ctrlAction.equals("speichern")){
			Bootstrap.serviceManager.getDozLVMaske().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
