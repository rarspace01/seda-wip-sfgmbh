package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.DefaultTableModel;

public class ProfessorshipTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			{"SEDA", "Sinz", "WIAI"},
			{"KTR", "Krieger", "WIAI"},
		};
	private String[] preFillHeader = {"Lehrstuhlname", "Inhaber", "Fakult\u00E4t"};
	
	
	public ProfessorshipTabTable() {
		this.setDataVector(preFill, preFillHeader);
	}
}
