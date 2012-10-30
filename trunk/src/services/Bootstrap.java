package services;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import modules.coregui.views.CoreGUI;

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
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
				}
				
				try {
					CoreGUI frame = serviceManager.getCoreGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
