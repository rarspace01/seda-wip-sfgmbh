package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.comlayer.core.controller.ViewHelper;

public class UserTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] header = {"Kennung", "E-Mail", "Benutzerklasse", "Lehrstuhl", "Letzer Login", "deaktiviert", "Hidden"};
	
	
	public UserTabTable() {
		AppModel.getInstance().repositoryUser.register(this);
		this.setColumnIdentifiers(header);
		this.change("init");
	}

	public void change(String variant) {
		HashMap<String, String> filter = new HashMap<String, String>();
		ViewHelper vh = new ViewHelper();
		
		this.setRowCount(0);
		
		if (variant.equals("init")) {
			filter.put("userclass", "<alle>");
			filter.put("chair", "<alle>");
			filter.put("user", "<alle>");
			filter.put("email", "<alle>");
		} else {
			String textMail = ServiceManager.getInstance().getOrgaUserTab().getTextFieldMail().getText();
			String textUserLogin = ServiceManager.getInstance().getOrgaUserTab().getTextFieldUserLogin().getText();
			filter.put("userclass", ServiceManager.getInstance().getOrgaUserTab().getComboBoxUserclass().getSelectedItem().toString());
			filter.put("chair", ServiceManager.getInstance().getOrgaUserTab().getComboBoxChair().getSelectedItem().toString());
			filter.put("user", textUserLogin);
			filter.put("email", textMail);
			
		}
		
		for (User user : AppModel.getInstance().repositoryUser.getByFilter(filter)){
			
			// Chair if user is lecturer
			String chair = null;
			if (user.getChair_() != null) {
				chair = user.getChair_().getChairName_();
			}
			
			// Get a date from the unix time stamp
			java.util.Date date = new java.util.Date((long) user.getLastLogin_() * 1000);
			
			// Build the row...
			try {
				Object[] row = {
						user.getLogin_(),
						user.getMail_(),
						vh.getUserClass(user.getClass_()),
						chair,
						date.toString(),
						vh.getBoolean(user.isDisabled_()),
						user
						};
				this.addRow(row);

			} catch (Exception e) {
				AppModel.getInstance().appExcaptions.setNewException("Ein unbekannter Fehler ist aufgetreten! <br /><br />Fehler BaseTableMain-01:<br />" + e.toString(), "Fehler!");
			}
		}
	}
	
	@Override
	public void change() {
		this.change("update");
		
	}
}
