package modules.dozenten.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;


public class DozentenTabBtnTicker implements ActionListener {
	
	protected InfoWindow infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("LiveTicker Bearbeitung momentan noch nicht implementiert!");
		}
		return this.infoWindow;
	}
}
