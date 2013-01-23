package de.sfgmbh.comlayer.core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import de.sfgmbh.applayer.core.definitions.IntfRoom;
import de.sfgmbh.applayer.core.model.AppException;
import de.sfgmbh.applayer.core.model.AppModel;
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
	private String navAction_;

	/**
	 * Create the action listener
	 */
	public BaseBtns() {
		this.navAction_ = "default";
	}

	/**
	 * Create the action listener based on an action string
	 * 
	 * @param action
	 */
	public BaseBtns(String action) {
		this.navAction_ = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BaseTab baseTab = ViewManager.getInstance().getCoreBaseTab();
		AppException exceptionHandler = AppModel.getInstance()
				.getExceptionHandler();

		// Add to timetable button is pressed
		if (this.navAction_.equals("timetable")) {
			JTable roomAllocationTable = baseTab.getOrganisationTable();
			TableModel roomAllocationTableModel = roomAllocationTable
					.getModel();

			// Get room allocation(s)
			int rows[] = roomAllocationTable.getSelectedRows();
			if (rows.length <= 0) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst eine Veranstaltung wählen.",
						"Achtung!");
			} else {
				try {
					List<RoomAllocation> returnList = new ArrayList<RoomAllocation>();
					for (int row : rows) {
						row = baseTab.getRowSorterAllocation()
								.convertRowIndexToModel(row);
						RoomAllocation selectedAllocation = (RoomAllocation) roomAllocationTableModel
								.getValueAt(row, 9);
						returnList.add(selectedAllocation);
					}

					ViewManager.getInstance().getCoreBaseTab()
							.getMainTabbedContainerPane().setVisible(true);
					if (ViewManager.getInstance().getCoreBaseTab()
							.getMainTabbedContainerPane().getTabCount() == 0) {
						ViewManager
								.getInstance()
								.getCoreBaseTab()
								.getMainTabbedContainerPane()
								.addTab("Start",
										null,
										ViewManager.getInstance()
												.getCoreBaseTab()
												.getStartScreenPanel(), null);
					}

					ViewManager.getInstance().getCoreTimetableTab()
							.addAllocation(returnList);
					ViewManager
							.getInstance()
							.getCoreBaseTab()
							.getMainTabbedContainerPane()
							.addTab("Vorlesungsplan",
									null,
									ViewManager.getInstance()
											.getCoreTimetableTab(), null);
					ViewManager.getInstance().getCoreTimetableTab()
							.setVisible(true);
					ViewManager.getInstance().getCoreBaseTab()
							.switchToNextTab();

				} catch (Exception ex) {
					ex.printStackTrace();
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
											+ ex.toString(), "Fehler!");
				}
			}
			return;
		}

		// Get roomtable button is pressed
		if (this.navAction_.equals("roomtable")) {
			JTable roomTable = baseTab.getRoomTable();
			TableModel roomTableModel = roomTable.getModel();

			// Get room allocation(s)
			int row = roomTable.getSelectedRow();
			if (row == -1) {
				exceptionHandler.setNewException(
						"Sie müssen zunächst einen Raum wählen.", "Achtung!");
			} else {
				try {
					row = baseTab.getRowSorterRoom()
							.convertRowIndexToModel(row);
					IntfRoom room = (IntfRoom) roomTableModel.getValueAt(row,
							10);

					ViewManager.getInstance().getCoreBaseTab()
							.getMainTabbedContainerPane().setVisible(true);
					if (ViewManager.getInstance().getCoreBaseTab()
							.getMainTabbedContainerPane().getTabCount() == 0) {
						ViewManager
								.getInstance()
								.getCoreBaseTab()
								.getMainTabbedContainerPane()
								.addTab("Start",
										null,
										ViewManager.getInstance()
												.getCoreBaseTab()
												.getStartScreenPanel(), null);
					}
					int getId = room.getRoomId_();

					ViewManager.getInstance().getCoreBaseTab()
							.getMainTabbedContainerPane().setVisible(true);

					ViewManager
							.getInstance()
							.getCoreBaseTab()
							.getMainTabbedContainerPane()
							.addTab("Raumplan",
									null,
									ViewManager.getInstance()
											.getOrgaRoomtableTab(), null);

					ViewManager.getInstance().getOrgaRoomtableTab()
							.loadRoomTable(getId);
					ViewManager.getInstance().getCoreBaseTab()
							.switchToNextTab();

				} catch (Exception ex) {
					AppModel.getInstance()
							.getExceptionHandler()
							.setNewException(
									"Ein unerwarteter Fehler ist aufgetreten.<br /><br >"
											+ ex.toString(), "Fehler!");
				}
			}
		}
	}
}
