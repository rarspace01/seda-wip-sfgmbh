package comlayer.core.model;

import javax.swing.table.*;

public class BaseTableMain extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"WS1213", "\u00DC", "SEDA-WIP-B", "WI-Projekt", "Benker", "Mo", "10 - 12", "WP3/03.002", "2"},
			{"WS1314", "VL", "ISDL-ISS1-M", "Standards und Netzwerke", "Weitzel", "Mi", "18 - 20", "WP3/05.004", "4"},
			{null, null, null, null, null, null, null, null, null},
		};
	private String[] preFillHeader = {"Sem.", "Art", "Bez.", "Name", "Dozent", "Tag", "Uhrzeit", "Raum", "SWS"};
	
	
	public BaseTableMain() {
		this.setDataVector(preFill, preFillHeader);
	}
}
