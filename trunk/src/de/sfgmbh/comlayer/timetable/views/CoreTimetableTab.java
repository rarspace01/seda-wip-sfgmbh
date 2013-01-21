package de.sfgmbh.comlayer.timetable.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.timetable.controller.CoreTimetableTabBtnPdf;

public class CoreTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable stundenplanTable;
	private int roomId_;
	private JScrollPane scrollPane_;
	private JLabel lblSemester;
	private List<RoomAllocation> roomAllocList_=new ArrayList<RoomAllocation>();	
	private JLabel lblvaluesemester;
	private JButton btnStundenplanZurcksetzen;
	
	public CoreTimetableTab() {
		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[131px][50px][92.00px][461px][69px][right]", "[68px][392px]"));
		
		btnStundenplanZurcksetzen = new JButton("Stundenplan zurücksetzen");
		btnStundenplanZurcksetzen.addActionListener(new CoreTimetableTabBtnPdf("reset"));
		add(btnStundenplanZurcksetzen, "cell 3 0");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 5 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		scrollPane_ = new JScrollPane();
		add(scrollPane_, "cell 0 1 5 1,grow");
		
		stundenplanTable = new JTable();
		
		scrollPane_.setViewportView(stundenplanTable);
		
	
		stundenplanTable.setBackground(Color.WHITE);
		stundenplanTable.setModel(ViewManager.getInstance().getCoreTimetableTabTable());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getCoreTimetableTabTable());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new CoreTimetableTabBtnPdf("pdfCreate"));
		add(btnPdfErzeugen, "cell 0 0,growx,aligny center");
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
		
		add(lblSemester, "flowx,cell 2 0,alignx left,aligny center");
		
		lblvaluesemester = new JLabel("#valueSemester#");
		add(lblvaluesemester, "cell 2 0");
		
		//reloadRoomTable();
	}
	
	public String getLectureOnTime(List<RoomAllocation> ral,int day, int time){
		String textualRepresentation="<html>";
		
		int tempEntries=0;
		
		for(int i=0;i<ral.size();i++){
			if(ral.get(i).getDay_()==day && ral.get(i).getTime_()==time){
				System.out.println("matched");
				//textualRepresentation=ral.get(i).getCourse_().getCourseName_()+" ("+ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_()+")";
				textualRepresentation+=ral.get(i).getCourse_().getCourseAcronym_()+" - in - "+ral.get(i).getRoom_().getRoomNumber_()+"<br/>";
				tempEntries++;
			}
		}
		return textualRepresentation+"</html>";
	}

	public void reloadRoomTable(){
		
		this.lblvaluesemester.setText(ViewManager.getInstance().getCoreBaseTab().getComboBoxSemesterFilter().getSelectedItem().toString());
		
		//Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(roomId);
		
		//clear all rows
		
		ViewManager.getInstance().getCoreTimetableTabTable().setRowCount(0);
		
		List<RoomAllocation> ral=this.roomAllocList_;
		
		for(int i=1;i<=7;i++){
			
			Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
			
			ViewManager.getInstance().getCoreTimetableTabTable().addRow(rowData);
			
			for(int j=1; j<=5; j++){
				
				ViewManager.getInstance().getCoreTimetableTabTable().setValueAt(getLectureOnTime(ral,j,i), i-1, j);
				
			}
		}
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
	
	public void addAllocation(List<RoomAllocation> roomallocations) {
		System.out.println("[Com-Layer] Allocs recieved: "+roomallocations.size());
		
		for(int i=0;i<roomallocations.size();i++){
			this.roomAllocList_.add(roomallocations.get(i));
		}
		reloadRoomTable();
	}
	
	public void resetPlan(){
		roomAllocList_.clear();
		reloadRoomTable();
	}
	
}
