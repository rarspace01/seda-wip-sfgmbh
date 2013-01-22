package de.sfgmbh.comlayer.core.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.sfgmbh.comlayer.core.views.BaseTab;

/**
 * Mouse action listener for the base tab table
 * 
 * @author hannes
 *
 */
public class BaseTable extends MouseAdapter {
	/* Not used as it doesn't work in every case - when reactivating handle with care
	private BaseTab baseTab;
	private String variant;
	*/
	
	/**
	 * Create the action listener dependent on the base tab based on a variant string
	 * @param baseTab
	 * @param variant
	 */
	public BaseTable(BaseTab baseTab, String variant) {
		/* Not used as it doesn't work in every case - when reactivating handle with care
		this.baseTab = baseTab;
		this.variant = variant;
		*/
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// Check if something is selected, if yes enable buttons
		
		/* Not used as it doesn't work in every case - when reactivating handle with care
		if (this.variant.equals("allocation")) {
			if (baseTab.getRoomTable().getSelectedRow() > -1) {
				baseTab.getBtnRoomplan().setEnabled(true);
			}
		}
		
		if (this.variant.equals("room")) {
			if (baseTab.getOrganisationTable().getSelectedRow() > -1) {
				baseTab.getBtnTimetable().setEnabled(true);
			}
		}
		*/
	}
	
}
