package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;
import services.Bootstrap;

public class DozentenTabBtnsControl implements ActionListener {
	
	private String ctrlAction;
	protected InfoWindow infoWindow;
	
	
	public DozentenTabBtnsControl() {
		this.ctrlAction = "default";
	}
	public DozentenTabBtnsControl(String action) {
		this.ctrlAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// Hinzufügen button is pressed
		if (this.ctrlAction.equals("hinzu")){
			Bootstrap.serviceManager.getDozLVMaske().setVisible(true);
		}
		
		// Bearbeiten button is pressed
		if (this.ctrlAction.equals("bearbeiten")){
			this.getInfoWindow("Hier erscheint die Bearbieten-Maske!").setVisible(true);
		}
		
		// Veröffentlichen button is pressed
		if (this.ctrlAction.equals("freigeben")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Belegung öffentlich zu schalten!").setVisible(true);
		}
		
		// Raumanfrage button is pressed
		if (this.ctrlAction.equals("raumanfrage")){
			Bootstrap.serviceManager.getDozLVRaumanfrageMaske().setVisible(true);
		}
		
		// Zurückziehen button is pressed
		if (this.ctrlAction.equals("zuruck")){
			this.getInfoWindow("Hier folgt die Anwendungslogik um einen Antrag zurückzuziehen!").setVisible(true);
		}
		
	}
	
	// Manage InfoWindow instance
	public InfoWindow getInfoWindow(String msg) {
		this.infoWindow = new InfoWindow(msg);
		return this.infoWindow;
	}
}
