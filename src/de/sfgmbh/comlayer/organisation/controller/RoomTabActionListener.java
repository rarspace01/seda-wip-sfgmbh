package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.organisation.views.RoomFrame;
import de.sfgmbh.comlayer.organisation.views.RoomTab;

/**
 * Action listener for the room organization control buttons
 * 
 * @author denis
 *
 */
public class RoomTabActionListener implements ActionListener, IntfComDialogObserver {

	private String navAction;

	public RoomTabActionListener() {
		this.navAction = "default";
	}

	public RoomTabActionListener(String action) {
		this.navAction = action;
	}

	/**
	 * Standard {@link ActionListener}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int getTableId;
		
		RoomFrame roomFrame = ViewManager.getInstance().getOrgaRoomFrame();
		RoomTab roomTab = ViewManager.getInstance().getOrgaRoomTab();

		//initializing values
		roomFrame.reset();
		
		// Add Button is pressed
		if (this.navAction.equals("add")) {
			roomFrame.setVisible(true);
		}

		// Edit Button is pressed
		if (this.navAction.equals("edit")) {

			// check if a room was selected
			if(roomTab.getRoommanagementTable().getSelectedRow()>=0){
			
				// get the row id from the selected Room from DB	
				getTableId=roomTab.getRoommanagementTable().getSelectedRow();
				
				getTableId = roomTab.getRowSorter().convertRowIndexToModel(getTableId);
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				IntfRoom selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
	
				// load values from room object to gui
				roomFrame.getTxtroomid().setText(""+selectedRoom.getRoomId_());
				
				roomFrame.getTxtRoomNumber().setText(selectedRoom.getRoomNumber_());
				roomFrame.getTxtLevel().setText(selectedRoom.getLevel_());
				roomFrame.getTxtSeats().setText(""+selectedRoom.getSeats_());
				roomFrame.getTxtPcSeats().setText(""+selectedRoom.getPcseats_());
				roomFrame.getTxtBeamer().setText(""+selectedRoom.getBeamer_());
				roomFrame.getTxtChalkboards().setText(""+selectedRoom.getChalkboards_());
				roomFrame.getTxtOverheads().setText(""+selectedRoom.getOverheads_());
				roomFrame.getTxtVisualizer().setText(""+selectedRoom.getVisualizer_());
				roomFrame.getTxtWhiteboards().setText(""+selectedRoom.getWhiteboards_());
				
				
				// set Room Edit Frame visible
				roomFrame.setVisible(true);
			
			}else{
				AppModel.getInstance()
				.getExceptionHandler()
				.setNewException(
						"Bitte wählen sie einen Raum aus",
						"Fehler!");
			}
			
		}

		// Delete Button is pressed
		if (this.navAction.equals("del")) {
				// check if a room was selected
				if(roomTab.getRoommanagementTable().getSelectedRow()>=0){
					//open dialog and ask the user to confirm deletion
					QuestionDialog dialog = new QuestionDialog("Wollen Sie den gewählten Raum wirklich löschen?", "Achtung!");
					dialog.register(this);
					dialog.setVisible(true);
				
				}else{
					AppModel.getInstance()
					.getExceptionHandler()
					.setNewException(
							"Bitte wählen sie einen Raum aus",
							"Fehler!");
				}
		}

		// Plan Button is pressed
		if (this.navAction.equals("plan")) {
			
			System.out.println("Roomplan klicked");
			
			if(roomTab.getRoommanagementTable().getSelectedRow()>=0){
				
				//get selected Room from DB	
				getTableId=roomTab.getRoommanagementTable().getSelectedRow();
				getTableId = roomTab.getRowSorter().convertRowIndexToModel(getTableId);
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
						.setVisible(true);
				ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
						.addTab("Raumplan", null,
								ViewManager.getInstance().getOrgaRoomtableTab(),
								null);
				ViewManager.getInstance().getOrgaRoomtableTab().loadRoomTable(getId);
				ViewManager.getInstance().getCoreBaseTab().switchToNextTab();
			}else{
				AppModel.getInstance()
				.getExceptionHandler()
				.setNewException(
						"Bitte wählen sie einen Raum aus",
						"Fehler!");
			}
			
		}

	}

	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {

			int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRoommanagementTable().getSelectedRow();
			getTableId = ViewManager.getInstance().getOrgaRoomTab().getRowSorter().convertRowIndexToModel(getTableId);
			int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
			
			IntfRoom selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
			
			AppModel.getInstance().getRepositoryRoom().delete(selectedRoom);
			
		} else if (answer.equals("no")) {
			//do nothing
		}
		
	}
}
