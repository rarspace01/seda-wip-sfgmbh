package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.views.BaseTab;

/**
 * Action listener for the buttons on the base tab
 * 
 * @author mario
 * @author hannes
 *
 */
public class BaseBtns implements ActionListener {
	private String navAction;
	
	/**
	 * Create the action listener
	 */
	public BaseBtns() {
		this.navAction = "default";
	}
	
	/**
	 * Create the action listener based on an action string
	 * @param action
	 */
	public BaseBtns(String action) {
		this.navAction = action;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();
		
	
		// Add to timetable button is pressed
		if (this.navAction.equals("timetable")){
			JTable roomAllocationTable = baseTab.getOrganisationTable();
			TableModel roomAllocationTableModel = roomAllocationTable.getModel();
			
			// Get room allocation(s)
			int rows[] = roomAllocationTable.getSelectedRows();
			if (rows.length <= 0) {
				exceptionHandler.setNewException("Sie müssen zunächst eine Veranstaltung wählen.", "Achtung!");
			} else {
				try {
					List<RoomAllocation> returnList = new ArrayList<RoomAllocation>();
					for (int row : rows) {
						row = baseTab.getRowSorterAllocation().convertRowIndexToModel(row);
						RoomAllocation selectedAllocation = (RoomAllocation) roomAllocationTableModel.getValueAt(row, 9);
						returnList.add(selectedAllocation);
					}
					
					/* HIER MUSS DER STUNDENPLAN GERUFEN WERDEN BZW UPGEDATET WERDEN */
					
					// Evtl.:
					// ViewManger.getInstance().getCoreTimetableTab().setAllocation(returnList);
					// ?? 
					// Dann würde es evlt Sinn machen dass Timetable Tab nach Core zu refactoren
					// Evtl macht es auch Sinn das Tab neu zu instanziieren - also da könnte man
					// ja das gleiche verwenden und nur im ViewManger eine extra Variable und Get-
					// Funktion anlegen. Sonst würde es ja das Tab überschreiben wenn User einge-
					// loggt sind. Musst halt mal schauen...
					
					
				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
			return;
		}
		
		// Get roomtable button is pressed
		if (this.navAction.equals("roomtable")){
			JTable roomTable = baseTab.getRoomTable();
			TableModel roomTableModel = roomTable.getModel();
			
			
			// Get room allocation(s)
			int row = roomTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException("Sie müssen zunächst einen Raum wählen.", "Achtung!");
			} else {
				try {
					row = baseTab.getRowSorterRoom().convertRowIndexToModel(row);
					Room room = (Room) roomTableModel.getValueAt(row, 10);

					/* HIER MUSS DER RAUMPLAN GERUFEN WERDEN BZW UPGEDATET WERDEN */
					
					// Siehe Kommentar von oben
					// Wenn du die ID brauchst aber hier halt room.getRoomId_()
					

				} catch (Exception ex) {
					AppModel.getInstance().getExceptionHandler().setNewException("Ein unerwarteter Fehler ist aufgetreten.<br /><br >" + ex.toString(), "Fehler!");
				}
			}
		}
	}
}

