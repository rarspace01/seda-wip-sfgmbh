package tests;

import java.util.List;

import de.sfgmbh.applayer.core.controller.CtrlBaseTab;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.datalayer.core.daos.DataHandlerRoom;
import de.sfgmbh.datalayer.core.daos.DataHandlerUser;

public class DataLayerTests {

	public static void main(String[] args) {
	
		DataLayerTests dlt=new DataLayerTests();
		CtrlBaseTab cbt = new CtrlBaseTab();
		
		// dlt.generateNewUser();
		dlt.getUserLogin();
		
		User test = cbt.login("Test", "Test");
		System.out.println(test.getPass_());
		
	}
	
	public void getUserLogin() {
		String login = "Test";
		DataHandlerUser dhu = new DataHandlerUser();
		User checkUser = dhu.getByLogin(login);
		
		System.out.println(checkUser.getPass_());
	}
	
	public void generateNewUser() {
		User newUser = new User();
		
		newUser.setPwHashAndSalt("Test");
		newUser.setLogin_("Test");
		newUser.setClass_("orga");
		
		DataHandlerUser dhu = new DataHandlerUser();
		dhu.save(newUser);
		
		System.out.println(newUser.checkPw("Test"));
		
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
	
	void testRoomUpdate(){
	
		DataHandlerRoom dhr=new DataHandlerRoom();
		Room r1=dhr.get(1);
		
		r1.setBeamer_(r1.getBeamer_()+1);
		
		dhr.save(r1);
		
	}
	
	void testRoomRetrievAll(){
		DataHandlerRoom dhr=new DataHandlerRoom();
		
		List<Room> roomlist=dhr.getAll();
		
		for(int i=0; i<roomlist.size();i++){
			System.out.println("Room in DB: ["+roomlist.get(i).getRoomId_()+"]["+roomlist.get(i).getRoomNumber_()+"]");
		}
		
	}
	
}
