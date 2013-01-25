package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfChair;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Table model for the chair table
 * 
 * @author anna
 * 
 */
public class ChairTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;

	private String[] header_ = { "Lehrstuhlname", "Lehrstuhlkürzel", "Inhaber",
			"Fakultät", "Hidden" };

	/**
	 * Creates an initial table model object
	 */
	public ChairTabTable() {
		AppModel.getInstance().getRepositoryChair().register(this);
		this.setColumnIdentifiers(header_);
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
			String textChair = ViewManager.getInstance().getOrgaChairTab()
					.getTextFieldChairname().getText();
			filter.put("chair", textChair);
		}

		for (IntfChair chair : AppModel.getInstance().getRepositoryChair()
				.getByFilter(filter)) {

			String owner = "";
			if (chair.getChairOwner() != null) {
				owner = chair.getChairOwner().getlName_();
			}
			try {
				Object[] row = { chair.getChairName(), chair.getAcronym(),
						owner, chair.getFaculty(), chair };
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
	@Override
	public void change() {
		this.change("update");

	}

	/**
	 * disables edits on the table cells
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}