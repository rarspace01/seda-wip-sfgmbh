package de.sfgmbh.init;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.datalayer.core.model.DataModel;
import de.sfgmbh.datalayer.io.DataManagerPostgreSql;

/**
 * Runs the program
 * 
 * @author hannes
 * @author denis
 * 
 */
public class Bootstrap {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// option for resseting DB on startup
		for (int i = 0; i < args.length; i++) {
			if (args[i].contains("resetdb")) {
				new ResetDb().factoryReset();
			}
		}

		// Start the GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				// set look and feel
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Windows".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					// If Nimbus is not available, you can set the GUI to
					// another look and feel.
				}

				// Register a mother info dialog
				InfoDialog infoDialog = new InfoDialog();
				AppModel.getInstance().getExceptionHandler()
						.register(infoDialog);

				// check DB connection
				if (hasDbConnection()) {
					try {
						BaseTab frame = ViewManager.getInstance()
								.getCoreBaseTab();
						frame.setVisible(true);
					} catch (Exception e) {

					}
				}
			}
		});

	}

	private static boolean hasDbConnection() {
		boolean hasDbConnection = false;
		try {
			DataManagerPostgreSql dataManager = new DataManagerPostgreSql();
			dataManager.select("SELECT 1;");
			hasDbConnection = true;
			dataManager.dispose();
		} catch (SQLException e) {
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Keine Verbindung zur Datenbank möglich:<br /><br />" + e
									.toString()), "Verbindungs-Fehler!");

		} catch (NullPointerException e) {
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Keine Verbindung zur Datenbank möglich:<br /><br />" + e
									.toString()), "Verbindungs-Fehler!");

		} catch (Exception e) {
			DataModel
					.getInstance()
					.getExceptionsHandler()
					.setNewException(
							("Keine Verbindung zur Datenbank möglich:<br /><br />" + e
									.toString()), "Verbindungs-Fehler!");

		}
		return hasDbConnection;
	}

}
