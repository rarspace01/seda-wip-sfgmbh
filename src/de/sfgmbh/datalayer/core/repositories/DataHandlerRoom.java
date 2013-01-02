package de.sfgmbh.datalayer.core.repositories;

import java.util.List;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.definitions.IntfDataRoom;

public class DataHandlerRoom implements IntfDataRoom{

	@Override
	public List<Room> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room get(int iRoomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> search(String searchQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> filter(String filterQry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Room toBeDeletedRoom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Room toBeSavedRoom) {
		// TODO Auto-generated method stub
		
		String sSQL="INSERT INTO ";
		
		//toBeSavedRoom.
		
		return 0;
	}

}
