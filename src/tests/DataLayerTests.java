package tests;

import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.datalayer.core.repositories.DataHandlerRoom;

public class DataLayerTests {

	public static void main(String[] args) {
	
		DataLayerTests dlt=new DataLayerTests();
		
	}
	
	void testRoomSave(){
		
		Room r1=new Room();
		
		r1.setRoomId_(1);
		r1.setRoomNumber_("");
		r1.setSeats_(30);
		r1.setBeamer_(1);
		
		DataHandlerRoom dhr=new DataHandlerRoom();
		
		dhr.save(r1);
		
	}
	
}
