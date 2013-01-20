package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;

public class ProfessorshipTimetableTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable lehrstuhlStundenplanTable_;
	private JPanel panelProfessorshipPanel_;
	private JComboBox<String> comboBoxSemesterFilter_;


	/**
	 * Create the panel.
	 */
	public ProfessorshipTimetableTab() {

		createContents();
	}
	private void createContents() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[359px][10px][112px][286px][101px][right]", "[61px][][383px]"));
		
		JLabel lblWochenplanFrDen = new JLabel("<html><h3>Wochenplan f\u00FCr den Lehrstuhl</h3></html>");
		lblWochenplanFrDen.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblWochenplanFrDen, "flowy,cell 0 0,growx,aligny center");
		
		JLabel lblUniIcon = new JLabel("");
		lblUniIcon.setVerticalAlignment(SwingConstants.TOP);
		lblUniIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblUniIcon, "cell 5 0,alignx right,aligny top");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		
		JLabel lblSemester = new JLabel("Semester:");
		add(lblSemester, "flowx,cell 0 1,alignx left,aligny bottom");
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn());
		add(btnPdfErzeugen, "cell 2 1,growx,aligny bottom");
		
		panelProfessorshipPanel_=getPanel();
		
		add(panelProfessorshipPanel_, "cell 0 2 5 1,grow");
		
		JPanel uniIconPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) uniIconPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(uniIconPanel, "flowx,cell 4 0,alignx left,aligny top");
		
		comboBoxSemesterFilter_= new JComboBox<>();
		
		comboBoxSemesterFilter_.setModel(new CmbboxFilterSemester());
		comboBoxSemesterFilter_.addKeyListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter_.addActionListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter_.setEditable(true);
		comboBoxSemesterFilter_.setAutoscrolls(true);
		
		add(comboBoxSemesterFilter_, "cell 0 1,alignx center,aligny bottom");
	}
	
	public JPanel getPanel() {
		if (panelProfessorshipPanel_ == null) {
			panelProfessorshipPanel_ = new JPanel();
			panelProfessorshipPanel_.setLayout(new MigLayout("", "[830px:n:830px]", "[grow]"));
			
			JScrollPane scrollPane = new JScrollPane();
			panelProfessorshipPanel_.add(scrollPane, "cell 0 0,growx,aligny top");
			
			lehrstuhlStundenplanTable_ = new JTable();
			lehrstuhlStundenplanTable_.setPreferredScrollableViewportSize(new Dimension(750, 400));
			lehrstuhlStundenplanTable_.setBorder(null);
			
			scrollPane.setViewportView(lehrstuhlStundenplanTable_);
			
	
			lehrstuhlStundenplanTable_.setBackground(Color.WHITE);
			lehrstuhlStundenplanTable_.setModel(ViewManager.getInstance().getLecturerChairimetableTabTable());
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(0).setResizable(true);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(0).setPreferredWidth(50);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(0).setMinWidth(50);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(0).setMaxWidth(105);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(1).setResizable(true);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(1).setPreferredWidth(50);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(1).setMinWidth(50);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(1).setMaxWidth(145);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(2).setResizable(true);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(2).setMinWidth(75);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(2).setMaxWidth(145);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(3).setPreferredWidth(80);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(3).setMinWidth(80);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(3).setMaxWidth(145);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(4).setPreferredWidth(70);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(4).setMinWidth(70);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(4).setMaxWidth(145);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(5).setMinWidth(70);
			lehrstuhlStundenplanTable_.getColumnModel().getColumn(5).setMaxWidth(145);
		}
		return panelProfessorshipPanel_;
	}
	
	public String getLectureOnTime(List<RoomAllocation> ral,int day, int time){
		String textualRepresentation="";
		
		for(int i=0;i<ral.size();i++){
			if(ral.get(i).getDay_()==day && ral.get(i).getTime_()==time){
				//textualRepresentation=ral.get(i).getCourse_().getCourseName_()+" ("+ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_()+")";
				textualRepresentation+=ral.get(i).getCourse_().getCourseAcronym_()+" - in - "+ral.get(i).getRoom_().getRoomNumber_()+"<br/>";
			}
		}
		
		return textualRepresentation;
	}
	
	public void reloadPlan(){
		
		
			
		ViewManager.getInstance().getLecturerChairimetableTabTable().setRowCount(0);
			
			HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
			
			tableFilter.put("semester", this.getComboBoxSemesterFilter().getSelectedItem().toString());
			
			System.out.println("Semesterfilter: "+this.getComboBoxSemesterFilter().getSelectedItem().toString());
			
			
			
			tableFilter.put("chairid", ""+SessionManager.getInstance().getSession().getChair_().getChairId_());
			
			List<RoomAllocation> ral=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
			
			for(int i=1;i<=7;i++){
				
				Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
				
				ViewManager.getInstance().getLecturerChairimetableTabTable().addRow(rowData);
				
				
				for(int j=1; j<=5; j++){
					
					ViewManager.getInstance().getLecturerChairimetableTabTable().setValueAt(getLectureOnTime(ral,j,i), i-1, j);
					
				}
			}
			
		
		
	}
	
	public JPanel getPanel_() {
		return panelProfessorshipPanel_;
	}
	
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter_;
	}
	
}
