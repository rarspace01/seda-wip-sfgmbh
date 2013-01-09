package de.sfgmbh.init;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.InfoDialog;



public class Bootstrap {

	public static ServiceManager serviceManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// Call the Service Manager instance
		if (serviceManager == null){
			serviceManager = new ServiceManager();
		}
		
		// Start the GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
	
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Windows".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
				}
				
				// Register a mother info dialog
				InfoDialog infoDialog = new InfoDialog();
				AppModel.getInstance().appExcaptions.register(infoDialog);
				
				try {
					BaseTab frame = serviceManager.getCoreBaseTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
