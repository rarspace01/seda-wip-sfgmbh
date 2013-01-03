package tests;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.repositories.DataHandlerRoom;

public class DataLayerTests {

	public static void main(String[] args) {
	
		DataLayerTests dlt=new DataLayerTests();
		
		dlt.testRoomSave();
		
		dlt.testUpdate();
		
		
	}
	
	void testRoomSave(){
		
		Room r1=new Room(-1);
		
		r1.setRoomNumber_("WE5/0.05");
		r1.setLevel_("EG");
		r1.setSeats_(30);
		r1.setBeamer_(1);
		
		DataHandlerRoom dhr=new DataHandlerRoom();
		
		dhr.save(r1);
		
	}
	
	void testUpdate(){
	
		DataHandlerRoom dhr=new DataHandlerRoom();
		Room r1=dhr.get(1);
		
		r1.setBeamer_(r1.getBeamer_()+1);
		
		dhr.update(r1);
		
	}
	
}
