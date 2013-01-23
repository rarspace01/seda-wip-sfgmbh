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
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
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
	public String getLectureOnTime(List<RoomAllocation> ral,int day, int time){
		String textualRepresentation="";
		
		for(int i=0;i<ral.size();i++){
			if(ral.get(i).getDay_()==day && ral.get(i).getTime_()==time){
				//textualRepresentation=ral.get(i).getCourse_().getCourseName_()+" ("+ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_()+")";
				textualRepresentation=ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_();
			}
		}
		
		return textualRepresentation;
	}

	public void reloadRoomTable(){
		loadRoomTable(this.roomId_);
	}

	public void loadRoomTable(int roomId){
		
		loadRoomTable(roomId, this.getComboBoxSemesterFilter().getSelectedItem().toString());
	}
	
	public void loadRoomTable(int roomId, String semester){
		
		this.roomId_=roomId;
		
		//Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(roomId);
		
		//clear all rows
		
		ViewManager.getInstance().getOrgaRoomtableTableModel().setRowCount(0);
		
		HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
		
		tableFilter.put("roomid", ""+this.roomId_);
		tableFilter.put("semester", semester);
		
		List<RoomAllocation> ral=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
		
		for(int i=1;i<=7;i++){
			
			Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
			
			ViewManager.getInstance().getOrgaRoomtableTableModel().addRow(rowData);
			
			
			for(int j=1; j<=5; j++){
				
				ViewManager.getInstance().getOrgaRoomtableTableModel().setValueAt(getLectureOnTime(ral,j,i), i-1, j);
				
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
	
}
