package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;


public class DozentenTabBtnZuruckz implements ActionListener {
	
	protected InfoWindow infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("Hier folgt die Anwendungslogik um einen Antrag zurückzuziehen!");
		}
		return this.infoWindow;
	}
}
