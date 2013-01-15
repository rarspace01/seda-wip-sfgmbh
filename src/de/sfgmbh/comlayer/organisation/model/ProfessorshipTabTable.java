package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.*;

import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * 
 * @author AnnaTerra
 *
 */
public class ProfessorshipTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	
private String[] header = {"Lehrstuhlname", "Lehrstuhlk�rzel", "Inhaber",  "Fakult�t"};


public ProfessorshipTabTable() {
	AppModel.getInstance().getRepositoryChair().register(this);
	this.setColumnIdentifiers(header);
	this.change("init");
}

public void change(String variant) {
	HashMap<String, String> filter = new HashMap<String, String>();
	ViewHelper vh = new ViewHelper();
	
	this.setRowCount(0);
	
	if (variant.equals("init")) {
	} else {
		filter.put("chair", ViewManager.getInstance().getOrgaProfessorshipTab().getTextFieldProfessorshipname().getText());
	}
	
	for (Chair chair : AppModel.getInstance().getRepositoryChair().getAll()) {
		
		
		try {
			Object[] row = {
					chair.getChairName_(),
					chair.getAcronym_(),
					// chair.getChairOwner_(),
					chair.getFaculty_(),
					};
			this.addRow(row);

		} catch (Exception e) {
			AppModel.getInstance().getExceptionHandler().setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />" + e.toString(), "Fehler!");
		}
	}
}
@Override
public void change() {
	this.change("update");
	
}
}