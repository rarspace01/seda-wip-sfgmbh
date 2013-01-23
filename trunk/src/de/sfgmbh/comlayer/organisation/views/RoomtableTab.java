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

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RoomtableCmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RoomtableTabBtnPdf;

public class RoomtableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable stundenplanTable;
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
		setLayout(new MigLayout("", "[20px:20px,grow][131px][50px][129px][461px][20px:20px,grow][right]", "[68px][392px]"));
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		scrollPane_ = new JScrollPane();
		add(scrollPane_, "cell 1 1 4 1,grow");
		
		stundenplanTable = new JTable();
		
		scrollPane_.setViewportView(stundenplanTable);
		
	
		stundenplanTable.setBackground(Color.WHITE);
		stundenplanTable.setModel(ViewManager.getInstance().getOrgaRoomtableTableModel());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getOrgaRoomTableModel());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new RoomtableTabBtnPdf("pdfCreate"));
		add(btnPdfErzeugen, "cell 1 0,growx,aligny center");
		stundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		stundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		stundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(0).setMaxWidth(105);
		stundenplanTable.getColumnModel().getColumn(1).setResizable(true);
		stundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		stundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(1).setMaxWidth(145);
		stundenplanTable.getColumnModel().getColumn(2).setResizable(true);
		stundenplanTable.getColumnModel().getColumn(2).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(2).setMaxWidth(145);
		stundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		stundenplanTable.getColumnModel().getColumn(3).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(3).setMaxWidth(145);
		stundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		stundenplanTable.getColumnModel().getColumn(4).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(4).setMaxWidth(145);
		stundenplanTable.getColumnModel().getColumn(5).setMinWidth(50);
		stundenplanTable.getColumnModel().getColumn(5).setMaxWidth(145);
		
		lblSemester = new JLabel("Semester:");
		
		comboBoxSemesterFilter = new JComboBox<String>();
		comboBoxSemesterFilter.setModel(comboBoxSemesterModel_);
		comboBoxSemesterFilter.addActionListener(new RoomtableCmbboxFilter());
		comboBoxSemesterFilter.setEditable(true);
		comboBoxSemesterFilter.setAutoscrolls(true);
		
		add(lblSemester, "cell 3 0,alignx left,aligny center");
		add(comboBoxSemesterFilter, "cell 3 0,alignx right,aligny center");
	}
	
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter;
	}

	public void reloadRoomTable(){
		loadRoomTable(this.roomId_);
	}

	public void loadRoomTable(int roomId){
		
		loadRoomTable(roomId, this.getComboBoxSemesterFilter().getSelectedItem().toString());
	}
	
	public void loadRoomTable(int roomId, String semester){
		
		this.roomId_=roomId;
		IntfCtrlGenericTables genericTablesController=new CtrlGenericTables();
		
		//setting the filters
		HashMap<String,String> tableFilter = new HashMap<String,String> ();
		tableFilter.put("roomid", ""+this.roomId_);
		tableFilter.put("semester", semester);
		//get Room allocations based on roomid + semester
		List<IntfRoomAllocation> roomAllocationList=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
		//calling the controller for reloading the gui
		genericTablesController.reloadTable(getStundenplanTable(), roomAllocationList,false);
		
	}
	
	public JTable getStundenplanTable() {
		return stundenplanTable;
	}
	
	public JScrollPane getScrollPane_() {
		return scrollPane_;
	}
	
	public int getRoomId_() {
		return roomId_;
	}
	
}
