package modules.base.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modules.base.views.InfoWindow;

public class BaseRdbtnTopLeft implements ActionListener {
	
	protected InfoWindow infoWindow;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.getInfoWindow().setVisible(true);
	}
	
	public InfoWindow getInfoWindow() {
		if (this.infoWindow == null) {
			this.infoWindow = new InfoWindow("Jetzt muss sich die Table ändern. Dieses Feature ist noch nicht weiter implementiert!");
		}
		return this.infoWindow;
	}
}