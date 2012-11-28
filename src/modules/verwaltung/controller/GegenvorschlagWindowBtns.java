package modules.verwaltung.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;
import services.Bootstrap;

public class GegenvorschlagWindowBtns implements ActionListener {
	
	private String ctrlAction;
	protected InfoWindow infoWindow;
	
	
	public GegenvorschlagWindowBtns() {
		this.ctrlAction = "default";
	}
	public GegenvorschlagWindowBtns(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Abbrechen button is pressed
		if (this.ctrlAction.equals("abbr")){
			Bootstrap.serviceManager.getVerwGegenvorschlagMaske().setVisible(false);
		}
		
		// Senden button is pressed
		if (this.ctrlAction.equals("senden")){
			Bootstrap.serviceManager.getVerwGegenvorschlagMaske().setVisible(false);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
