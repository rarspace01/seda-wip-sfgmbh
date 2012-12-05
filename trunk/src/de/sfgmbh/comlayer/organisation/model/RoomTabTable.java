package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.*;

public class RoomTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"R001", "Erba1", "EG", "55", "55", "1", "0", "0", "0", "2"},
			{"R002", "Feki1", "1", "120", "0", "2", "1", "0", "4", "0"},
		};
	private String[] preFillHeader = {"Raum", "Geb\u00E4ude", "Stock", "Pl\u00E4tze", "PC-Pl\u00E4tze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards"};
	
	
	public RoomTabTable() {
		this.setDataVector(preFill, preFillHeader);
	}
}
