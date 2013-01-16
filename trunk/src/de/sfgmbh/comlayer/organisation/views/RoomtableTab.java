package de.sfgmbh.comlayer.organisation.views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.Room;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RoomtableTabBtnPdf;

public class RoomtableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable StundenplanTable;
	private int roomId_;
	
	public RoomtableTab() {
		initialize();
	}
	private void initialize() {
		setAutoscrolls(true);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 69, 830, 392);
		add(scrollPane);
		
		JPanel uniIconPanel = new JPanel();
		uniIconPanel.setBounds(792, 0, 69, 68);
		add(uniIconPanel);
		
		StundenplanTable = new JTable();
		
		scrollPane.setViewportView(StundenplanTable);
		
	
		StundenplanTable.setBackground(Color.WHITE);
		StundenplanTable.setModel(ViewManager.getInstance().getOrgaRoomtableTableModel());
		
		AppModel.getInstance().getRepositoryRoomAllocation().register(ViewManager.getInstance().getOrgaRoomTableModel());
		
		JButton btnPdfErzeugen = new JButton("PDF erzeugen");
		btnPdfErzeugen.addActionListener(new RoomtableTabBtnPdf("pdfCreate"));
		btnPdfErzeugen.setBounds(21, 17, 131, 23);
		add(btnPdfErzeugen);
		StundenplanTable.getColumnModel().getColumn(0).setResizable(false);
		StundenplanTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		StundenplanTable.getColumnModel().getColumn(0).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(0).setMaxWidth(105);
		StundenplanTable.getColumnModel().getColumn(1).setResizable(true);
		StundenplanTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(1).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(2).setResizable(true);
		StundenplanTable.getColumnModel().getColumn(2).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(2).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(3).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(4).setMaxWidth(145);
		StundenplanTable.getColumnModel().getColumn(5).setMinWidth(50);
		StundenplanTable.getColumnModel().getColumn(5).setMaxWidth(145);
		
		JLabel lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
	}
	
	public String getLectureOnTime(List<RoomAllocation> ral,int day, int time){
		String textualRepresentation="";
		
		for(int i=0;i<ral.size();i++){
			if(ral.get(i).getDay_()==day && ral.get(i).getTime_()==time){
				textualRepresentation=ral.get(i).getCourse_().getCourseName_()+" ("+ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_()+")";
			}
		}
		
		return textualRepresentation;
	}
	
	public void loadRoomTable(int roomId){
		
		//Room selectedRoom=AppModel.getInstance().getRepositoryRoom().getRoomById(roomId);
		
		//clear all rows
		
		for(int i=ViewManager.getInstance().getOrgaRoomtableTableModel().getRowCount()-1; i>=0;i--){
			ViewManager.getInstance().getOrgaRoomtableTableModel().removeRow(i);
		}
		
		HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
		
		tableFilter.put("roomid", ""+roomId);
		
		List<RoomAllocation> ral=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
		
		
		
		for(int i=0;i<ral.size();i++){
			System.out.println("AllocationDescr:");
			System.out.println(ral.get(i).getRoomAllocationId_());
			System.out.println(ral.get(i).getDay_());
			System.out.println(ral.get(i).getTime_());
			System.out.println(ral.get(i).getCourse_().getCourseDescription_());
			System.out.println(ral.get(i).getCourse_().getCourseAcronym_());
			System.out.println(ral.get(i).getCourse_().getCourseName_());
			
			//System.out.println(ral.get(i).get
		}
		
		
		
		for(int i=1;i<=7;i++){
			
			Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
			
			ViewManager.getInstance().getOrgaRoomtableTableModel().addRow(rowData);
			
			
			for(int j=1; j<=5; j++){
				
				ViewManager.getInstance().getOrgaRoomtableTableModel().setValueAt(getLectureOnTime(ral,j,i), i-1, j);
				
			}
		}
		
		
	}
	
	
}
