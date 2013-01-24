package de.sfgmbh.comlayer.organisation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RoomtableCmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RoomtableTabBtnPdf;

public class RoomtableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable timetableTable;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester;
	private JComboBox<String> comboBoxSemesterFilter;
	private CmbboxFilterSemester comboBoxSemesterModel_= new CmbboxFilterSemester("select");

	
	public RoomtableTab() {
		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[20px:20px,grow][131px][50px][129px][461px][20px:20px,grow][right]", "[68px][392px][grow]"));
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		scrollPane_ = new JScrollPane();
		scrollPane_.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane_, "cell 1 1 4 1,grow");
		
		timetableTable = new JTable();
		
		scrollPane_.setViewportView(timetableTable);
		
	
		timetableTable.setBackground(Color.WHITE);
		timetableTable.setModel(ViewManager.getInstance().getOrgaRoomtableTableModel());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getOrgaRoomTableModel());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new RoomtableTabBtnPdf("pdfCreate"));
		add(btnPdfErzeugen, "cell 1 0,growx,aligny center");
		timetableTable.getColumnModel().getColumn(0).setResizable(false);
		timetableTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		timetableTable.getColumnModel().getColumn(0).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(0).setMaxWidth(105);
		timetableTable.getColumnModel().getColumn(1).setResizable(true);
		timetableTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		timetableTable.getColumnModel().getColumn(1).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(1).setMaxWidth(145);
		timetableTable.getColumnModel().getColumn(2).setResizable(true);
		timetableTable.getColumnModel().getColumn(2).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(2).setMaxWidth(145);
		timetableTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		timetableTable.getColumnModel().getColumn(3).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(3).setMaxWidth(145);
		timetableTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		timetableTable.getColumnModel().getColumn(4).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(4).setMaxWidth(145);
		timetableTable.getColumnModel().getColumn(5).setMinWidth(50);
		timetableTable.getColumnModel().getColumn(5).setMaxWidth(145);
		
		lblSemester = new JLabel("Semester:");
		
		comboBoxSemesterFilter = new JComboBox<String>();
		comboBoxSemesterFilter.setModel(comboBoxSemesterModel_);
		comboBoxSemesterFilter.addActionListener(new RoomtableCmbboxFilter());
		comboBoxSemesterFilter.setEditable(true);
		comboBoxSemesterFilter.setAutoscrolls(true);
		
		add(lblSemester, "cell 3 0,alignx left,aligny center");
		add(comboBoxSemesterFilter, "cell 3 0,alignx right,aligny center");
	}
	/**
	 * 
	 * @return the comboBoxSemesterFilter
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter;
	}
	/**
	 * reloads the timetable for the selected room
	 */
	public void reloadRoomTable(){
		loadRoomTable(this.roomId_);
	}
	/**
	 * 
	 * @param roomId
	 */
	public void loadRoomTable(int roomId){
		
		loadRoomTable(roomId, this.getComboBoxSemesterFilter().getSelectedItem().toString());
	}
	/**
	 * loads the table depending on the roomId and semester
	 * @param roomId
	 * @param semester
	 */
	public void loadRoomTable(int roomId, String semester){
		
		this.roomId_=roomId;
		IntfCtrlGenericTables genericTablesController=new CtrlGenericTables();
		
		// setting the filters
		HashMap<String,String> tableFilter = new HashMap<String,String> ();
		// only show verified classes
		tableFilter.put("status", "accepted");
		tableFilter.put("courseenabled", "true");
		// setting roomid&semester
		tableFilter.put("roomid", ""+this.roomId_);
		tableFilter.put("semester", semester);
		// get Room allocations based on roomid + semester
		List<IntfRoomAllocation> roomAllocationList=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
		//calling the controller for reloading the gui
		genericTablesController.reloadTable(getStundenplanTable(), roomAllocationList,false,true);
		
		// Set the maximum size of the scroll pane (don't forget to add the table header!)
		scrollPane_.setMaximumSize(new Dimension(32767, ((int) timetableTable.getPreferredSize().getHeight() + 26)));
		this.updateUI();
	}
	/**
	 * 
	 * @return the timetableTable
	 */
	public JTable getStundenplanTable() {
		return timetableTable;
	}
	/**
	 * 
	 * @return the scrollPane_
	 */
	public JScrollPane getScrollPane_() {
		return scrollPane_;
	}
	/**
	 * 
	 * @return the roomId_
	 */
	public int getRoomId_() {
		return roomId_;
	}
	
}
