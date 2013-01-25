package de.sfgmbh.comlayer.organisation.model;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.comlayer.core.controller.ViewManager;
/**
 * Table model for the roomtable tab table
 * 
 * @author denis
 *
 */
public class RoomtableTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private Object[][] preFill_ = {
			
			
		};
	private String[] preFillHeader_ = {"Uhrzeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"};
	
	/**
	 * Create the model
	 */
	public RoomtableTable() {
		this.setDataVector(preFill_, preFillHeader_);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

	/*
	 * (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.definitions.IntfAppObserver#change()
	 */
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

