package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.timetable.controller.CoreTimetableTabBtnPdf;

/**
 * @author anna
 * @author denis - view methods
 *
 */
public class CoreTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable stundenplanTable;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester;
	private List<IntfRoomAllocation> roomAllocList_=new ArrayList<IntfRoomAllocation>();	
	private JLabel lblvaluesemester;
	private JButton btnStundenplanZurcksetzen;
	
	public CoreTimetableTab() {
		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[20px:20px,grow][131px][50px][92.00px][461px][20px:20px,grow][right]", "[68px][392px]"));
		
		btnStundenplanZurcksetzen = new JButton("Stundenplan zurücksetzen");
		btnStundenplanZurcksetzen.addActionListener(new CoreTimetableTabBtnPdf("reset"));
		add(btnStundenplanZurcksetzen, "cell 4 0");
		
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
		stundenplanTable.setModel(ViewManager.getInstance().getCoreTimetableTabTable());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getCoreTimetableTabTable());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new CoreTimetableTabBtnPdf("pdfCreate"));
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
		
		add(lblSemester, "flowx,cell 3 0,alignx left,aligny center");
		
		lblvaluesemester = new JLabel("#valueSemester#");
		add(lblvaluesemester, "cell 3 0");
		
		reloadRoomTable();
	}
	
	public void reloadRoomTable(){
		//controller for table methods
		IntfCtrlGenericTables genericTablesController=new CtrlGenericTables();
		//set teh Semester label
		this.lblvaluesemester.setText(ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString());
		//get the room allocations from the temprary storage
		List<IntfRoomAllocation> roomAllocationList=this.roomAllocList_;
		//call the reload method
		genericTablesController.reloadTable(getStundenplanTable(), roomAllocationList,true);
		
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
	
	/**
	 * adds an room allocation to he table, stores it locally
	 * @author denis
	 * @param roomallocations - {@link RoomAllocation}
	 */
	public void addAllocation(List<RoomAllocation> roomallocations) {
		
		for(int i=0;i<roomallocations.size();i++){
			if(!roomAllocList_.contains(roomallocations.get(i))){
			this.roomAllocList_.add(roomallocations.get(i));
			}
		}
		reloadRoomTable();
	}
	
	public void resetPlan(){
		roomAllocList_.clear();
		reloadRoomTable();
	}
	
}
