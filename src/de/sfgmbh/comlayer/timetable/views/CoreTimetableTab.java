package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.timetable.controller.CoreTimetableTabBtnPdf;

/**
 * Tab for the Students to let them view their colelcted Tiemtable
 * @author anna - guiparts
 * @author denis - methods
 *
 */
public class CoreTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable timetableTable;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester;
	private List<IntfRoomAllocation> roomAllocList_=new ArrayList<IntfRoomAllocation>();	
	private JLabel lblvaluesemester;
	private JButton btnTimetableReset;
	
	public CoreTimetableTab() {
		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[20px:20px,grow][131px][50px][92.00px][461px][20px:20px,grow][right]", "[68px][grow,shrink 0]"));
		
		btnTimetableReset = new JButton("Stundenplan zurücksetzen");
		btnTimetableReset.addActionListener(new CoreTimetableTabBtnPdf("reset"));
		add(btnTimetableReset, "cell 4 0");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		scrollPane_ = new JScrollPane();
		scrollPane_.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_.setBorder(null);
		add(scrollPane_, "cell 1 1 4 1,growx,aligny top");
		
		timetableTable = new JTable();
		
		scrollPane_.setViewportView(timetableTable);
		
	
		timetableTable.setBackground(Color.WHITE);
		timetableTable.setModel(ViewManager.getInstance().getCoreTimetableTabTable());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getCoreTimetableTabTable());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new CoreTimetableTabBtnPdf("pdfCreate"));
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
		
		add(lblSemester, "flowx,cell 3 0,alignx left,aligny center");
		
		lblvaluesemester = new JLabel("#valueSemester#");
		add(lblvaluesemester, "cell 3 0");
	
		reloadRoomTable();
	}
	/**
	 * reloads the timetable corresponding to the selection on the basetab
	 * @author denis
	 */
	public void reloadRoomTable(){
		//controller for table methods
		IntfCtrlGenericTables genericTablesController=new CtrlGenericTables();
		//set the Semester label
		this.lblvaluesemester.setText(ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString());
		//get the room allocations from the temprary storage
		List<IntfRoomAllocation> roomAllocationList=this.roomAllocList_;
		//call the reload method
		genericTablesController.reloadTable(getStundenplanTable(), roomAllocationList,true,true);
		
		// Set the maximum size of the scroll pane (don't forget to add the table header!)
		scrollPane_.setMaximumSize(new Dimension(32767, ((int) timetableTable.getPreferredSize().getHeight() + 26)));
		this.updateUI();
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
	
	/**
	 * resets the collectedAllocations
	 * @author denis
	 */
	public void resetPlan(){
		roomAllocList_.clear();
		reloadRoomTable();
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
	 * @return scrollPane_
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
