package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Chair;
import de.sfgmbh.comlayer.core.controller.ViewManager;

// import de.sfgmbh.comlayer.core.controller.ViewHelper;

/**
 * 
 * @author anna
 * 
 */
public class ProfessorshipTabTable extends DefaultTableModel implements
		IntfAppObserver {

	private static final long serialVersionUID = 1L;

	private String[] header = { "Lehrstuhlname", "Lehrstuhlkürzel", "Inhaber",
			"Fakultät" };

	/**
	 * Creates an initial table model object
	 */
	public ProfessorshipTabTable() {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	/**
	 * Performs an action depending on the submitted variant to change and
	 * update the table model
	 * 
	 * @param variant
	 */
	public void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();

		this.setRowCount(0);

		if (variant.equals("init")) {

		} else {
			String textChair = ViewManager.getInstance()
					.getOrgaProfessorshipTab().getTextFieldProfessorshipname()
					.getText();
			filter.put("chair", textChair);
		}

		for (Chair chair : AppModel.getInstance().getRepositoryChair()
				.getByFilter(filter)) {

			try {
				Object[] row = { chair.getChairName_(), chair.getAcronym_(),
						chair.getChairOwner_(), chair.getFaculty_(), };
				this.addRow(row);

			} catch (Exception e) {
				AppModel.getInstance()
						.getExceptionHandler()
						.setNewException(
								"Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />"
										+ e.toString(), "Fehler!");
			}
		}
	}

	@Override
	public void change() {
		this.change("update");

	}
	
	/**
	 * disables edits on the table cells
	 * @author denis
	 */
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}