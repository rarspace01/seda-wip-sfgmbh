package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;

public class RoomtableTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill = {
			
			
		};
	private String[] preFillHeader = {"Uhrzeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	
	
	public RoomtableTable() {
		this.setDataVector(preFill, preFillHeader);
	}
	
	/**
	 * disables edits on the table cells
	 * @author denis
	 */
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

	@Override
	public void change() {
		
		//delete all rows
		for(int i=this.getRowCount()-1;i>=0;i--){
			this.removeRow(i);
		}
		
		//get all rooms from db
		ViewManager.getInstance().getOrgaRoomtableTab().reloadRoomTable();
		
	}
}

