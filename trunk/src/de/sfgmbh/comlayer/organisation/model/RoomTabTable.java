package de.sfgmbh.comlayer.organisation.model;

import java.util.List;

import javax.swing.table.*;

import de.sfgmbh.applayer.core.model.Room;

public class RoomTabTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
//	private Object[][] preFill = {
//			{"R001", "Erba1", "EG", "55", "55", "1", "0", "0", "0", "2"},
//			{"R002", "Feki1", "1", "120", "0", "2", "1", "0", "4", "0"},
//		};
	private String[] preFillHeader = {"roomid","Raum", "Geb\u00E4ude", "Stock", "Pl\u00E4tze", "PC-Pl\u00E4tze", "Beamer", "Visualizer", "Overheads", "Tafeln", "Whiteboards"};
	
	
	public RoomTabTable() {
		this.setDataVector(null, preFillHeader);
	}
	
	public void addRoom(Room room) {
		
		Object[] rowVector= {room.getRoomId_(),room.getRoomNumber_(),room.getBuildingId_(),room.getLevel_(),room.getSeats_(), room.getPcseats_(), room.getBeamer_(), room.getVisualizer_(), room.getOverheads_(), room.getChalkboards_(), room.getWhiteboards_()};
		
		this.addRow(rowVector);
	}

	public void addRooms(List<Room> allRooms) {
		
		for(int i=0; i<allRooms.size();i++){
			this.addRoom(allRooms.get(i));
		}
		
	}
	
}
