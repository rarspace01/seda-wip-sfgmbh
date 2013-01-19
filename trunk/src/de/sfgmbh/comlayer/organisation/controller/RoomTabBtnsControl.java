package de.sfgmbh.comlayer.organisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.views.InfoDialog;
import de.sfgmbh.comlayer.core.views.QuestionDialog;

public class RoomTabBtnsControl implements ActionListener, IntfComDialogObserver {

	private String navAction;
	protected InfoDialog infoWindow;

	public RoomTabBtnsControl() {
		this.navAction = "default";
	}

	public RoomTabBtnsControl(String action) {
		this.navAction = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//initializing values
		ViewManager.getInstance().getOrgaRoomFrame().getTxtroomid().setText("-1");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtRoomNumber().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtLevel().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtSeats().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtPcSeats().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtBeamer().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtChalkboards().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtOverheads().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtVisualizer().setText("");
		ViewManager.getInstance().getOrgaRoomFrame().getTxtWhiteboards().setText("");
		
		// Raum hinzufügen Button is pressed
		if (this.navAction.equals("hinz")) {
			ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
		}

		// Raum bearbeiten Button is pressed
		if (this.navAction.equals("edit")) {

			if(ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow()>=0){
			
				//get selected Room from DB	
				int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow();
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
	
				//load values from room object to gui
				ViewManager.getInstance().getOrgaRoomFrame().getTxtroomid().setText(""+selectedRoom.getRoomId_());
				
				ViewManager.getInstance().getOrgaRoomFrame().getTxtRoomNumber().setText(selectedRoom.getRoomNumber_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtLevel().setText(selectedRoom.getLevel_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtSeats().setText(""+selectedRoom.getSeats_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtPcSeats().setText(""+selectedRoom.getPcseats_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtBeamer().setText(""+selectedRoom.getBeamer_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtChalkboards().setText(""+selectedRoom.getChalkboards_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtOverheads().setText(""+selectedRoom.getOverheads_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtVisualizer().setText(""+selectedRoom.getVisualizer_());
				ViewManager.getInstance().getOrgaRoomFrame().getTxtWhiteboards().setText(""+selectedRoom.getWhiteboards_());
				
				
				//set Room Edit Frame visible
				ViewManager.getInstance().getOrgaRoomFrame().setVisible(true);
			
			}else{
				AppModel.getInstance()
				.getExceptionHandler()
				.setNewException(
						"Bitte wählen sie einen Raum aus",
						"Fehler!");
			}
			
		}

		// Raum löschen Button is pressed
		if (this.navAction.equals("loschen")) {
			
				if(ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow()>=0){
				
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

		// Fehlermeldung Button is pressed
		if (this.navAction.equals("Fehlermeldung")) {
			this.getInfoWindow(
					"<b>Fehlermeldung:</b><br>Es besteht keine Verbindung zur Datenbank. Daher können keine R�ume angezeigt werden.")
					.setVisible(true);
		}

		// Raumplan Button is pressed
		if (this.navAction.equals("Raumplan")) {
			
			System.out.println("Roomplan klicked");
			
			if(ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow()>=0){
				
				//get selected Room from DB	
				int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow();
				int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
				
				
				
				
//				this.getInfoWindow(
//						"<strong>Fehlermeldung</strong><br> Es konnte keine Übersicht Ihrer Veranstaltungen angezeigt werden. Sie haben keine Lehrveranstaltung ausgew�hlt!")
//						.setVisible(true);
				ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
						.setVisible(true);
				ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
						.addTab("Raumplan", null,
								ViewManager.getInstance().getOrgaRoomtableTab(),
								null);
				ViewManager.getInstance().getOrgaRoomtableTab().loadRoomTable(getId);
				ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
						.setSelectedIndex(ViewManager.getInstance().getCoreBaseTab().getMainTabbedContainerPane()
								.getTabCount() - 1);
			}
			
		}

	}

	// Manage InfoWindow instance
	public InfoDialog getInfoWindow(String msg) {
		this.infoWindow = new InfoDialog(msg);
		return this.infoWindow;
	}

	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {

			int getTableId=ViewManager.getInstance().getOrgaRoomTab().getRaumverwaltungTable().getSelectedRow();
			int getId=Integer.parseInt(ViewManager.getInstance().getOrgaRoomTableModel().getValueAt(getTableId, 0).toString());
			
			Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(getId);
			
			AppModel.getInstance().getRepositoryRoom().delete(selectedRoom);
			
		} else if (answer.equals("no")) {
			//do nothing
		}
		
	}
}
