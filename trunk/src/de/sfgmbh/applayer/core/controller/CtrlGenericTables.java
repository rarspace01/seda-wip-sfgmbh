package de.sfgmbh.applayer.core.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;

public class CtrlGenericTables implements IntfCtrlGenericTables {

	/* (non-Javadoc)
	 * @see de.sfgmbh.applayer.core.controller.IntfCtrlGenericTables#countBreaklines(java.lang.String)
	 */
	@Override
	public int countBreaklines (String inputString) {
		   String string = inputString;
		    Pattern p = Pattern.compile("<br/>");
		    Matcher m = p.matcher(string);
		    int count = 0;
		    while (m.find()){
		    	count +=1;
		    }
		    return count;
	}
	
	public void resizeRows(JTable table){
		//calc max heigth per row and set it
		int maxHeight=1;
		int tmpHeight=0;
		for(int i=0;i<6;i++){
			maxHeight=1;
			for(int j=1; j<=5; j++){
				tmpHeight=this.countBreaklines(table.getModel().getValueAt(i, j).toString());
				if(tmpHeight>maxHeight){
					maxHeight=tmpHeight;
				}
			}
			table.setRowHeight(i, maxHeight*16);
		}
	}
	
	public void reloadTable(JTable table, List<IntfRoomAllocation> roomAllocationList){
		IntfCtrlRoomAllocation roomAllocationController=new CtrlRoomAllocation();
		// clear all rows
		if(table.getModel() instanceof DefaultTableModel){
		((DefaultTableModel) table.getModel()).setRowCount(0);
				// add data to the table
				for(int i=1;i<=6;i++){
					Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""}; // inital data values
					((DefaultTableModel) table.getModel()).addRow(rowData);
					for(int j=1; j<=5; j++){
						((DefaultTableModel) table.getModel()).setValueAt(roomAllocationController.getLectureOnTime(roomAllocationList,j,i), i-1, j);
					}
				}
		}
		resizeRows(table);
	}
	
	
}
