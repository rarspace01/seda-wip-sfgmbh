package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfUser;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

/**
 * Table model to organize users
 * 
 * @author hannes
 * 
 */
public class UserTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header_ = { "Kennung", "E-Mail", "Benutzerklasse",
			"Lehrstuhl", "Letzer Login", "deaktiviert", "Hidden" };

	/**
	 * Creates an initial table model object
	 */
	public UserTabTable() {
		AppModel.getInstance().getRepositoryUser().register(this);
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
			filter.put("userclass", "<alle>");
			filter.put("chair", "<alle>");
			filter.put("user", "<alle>");
			filter.put("email", "<alle>");
		} else {
			String textMail = ViewManager.getInstance().getOrgaUserTab()
					.getTextFieldMail().getText();
			String textUserLogin = ViewManager.getInstance().getOrgaUserTab()
					.getTextFieldUserLogin().getText();
			filter.put("userclass", ViewManager.getInstance().getOrgaUserTab()
					.getComboBoxUserclass().getSelectedItem().toString());
			filter.put("chair", ViewManager.getInstance().getOrgaUserTab()
					.getComboBoxChair().getSelectedItem().toString());
			filter.put("user", textUserLogin);
			filter.put("email", textMail);

		}

		for (IntfUser user : AppModel.getInstance().getRepositoryUser()
				.getByFilter(filter)) {

			// Chair if user is lecturer
			String chair = null;
			if (user.getChair_() != null) {
				chair = user.getChair_().getChairName_();
			}

			// Get a date from the unix time stamp
			java.util.Date date = new java.util.Date(
					(long) user.getLastLogin_() * 1000);

			// Build the row...
			try {
				Object[] row = { 
						user.getLogin_(), 
						user.getMail_(),
						ViewHelper.getUserClass(user.getClass_()), 
						chair,
						ViewHelper.getGlobalDateFormat(date), 
						ViewHelper.getBoolean(user.isDisabled_()),
						user };
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

	/**
	 * disables edits on the table cells
	 * 
	 * @author denis
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void change() {
		this.change("update");

	}
}
