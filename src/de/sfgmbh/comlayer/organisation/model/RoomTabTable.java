package de.sfgmbh.comlayer.organisation.model;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.sfgmbh.applayer.core.definitions.IntfAppObserver;
import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.organisation.views.RoomTab;

/**
 * Table model for the room table
 * 
 * @author denis
 *
 */
public class RoomTabTable extends DefaultTableModel implements IntfAppObserver {

	private static final long serialVersionUID = 1L;
	private String[] preFillHeader = {"roomid","Raum", "Geb\u00E4ude", "Stock", "Pl\u00E4tze", "PC-Pl\u00E4tze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards"};
	
	
	public RoomTabTable() {
		this.setDataVector(null, preFillHeader);
	}
	
	public void addRoom(IntfRoom room) {
		
		Object[] rowVector= {room.getRoomId_(),room.getRoomNumber_(),"ERBA",room.getLevel_(),room.getSeats_(), room.getPcseats_(), room.getBeamer_(), room.getVisualizer_(), room.getOverheads_(), room.getChalkboards_(), room.getWhiteboards_()};
		
		this.addRow(rowVector);
	}

	public void addRooms(List<IntfRoom> allRooms) {
		
		for(int i=0; i<allRooms.size();i++){
			this.addRoom(allRooms.get(i));
		}
		
	}

	@Override
	public void change() {
		
		//delete all rows
		for(int i=this.getRowCount()-1;i>=0;i--){
			this.removeRow(i);
		}
		
		//prepare a filter
		HashMap<String, String> filter = new HashMap<String,String>();
		RoomTab roomTab = ViewManager.getInstance().getOrgaRoomTab();
		
		String seats, pcSeats;
		try {
			int intSeats = Integer.parseInt(roomTab.getTextFieldSeats().getText());
			int intPcSeats = Integer.parseInt(roomTab.getTextFieldPCSeats().getText());
			seats = String.valueOf(intSeats);
			pcSeats = String.valueOf(intPcSeats);
		} catch (Exception e) {
			AppModel.getInstance().getExceptionHandler().setNewException("Bitte stellen Sie sicher,  dass sie echte Zahlen bei den Filtern eingetragen haben", "Fehler!", "error");
			return;
		}
		
		filter.put("level", roomTab.getComboBoxLevel().getSelectedItem().toString());
		filter.put("seats", seats);
		filter.put("pcseats", pcSeats);
		filter.put("room", roomTab.getTxtRoom().getText());

		// get and add all rooms depending on a filter
		this.addRooms(AppModel.getInstance().getRepositoryRoom().getByFilter(filter));
		
	}
	
	/**
	 * disables edits on the table cells
	 * @author denis
	 */
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
	
}
