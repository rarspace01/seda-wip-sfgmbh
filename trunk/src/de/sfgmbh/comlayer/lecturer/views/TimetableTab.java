package de.sfgmbh.comlayer.lecturer.views;

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
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabCmbbox;
import de.sfgmbh.comlayer.lecturer.controller.TimetableTabBtn;

public class TimetableTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable lecturerTimetableTable_;
	private JPanel panel_;
	private JLabel lblSemester_;
	private JComboBox<String> comboBoxSemesterFilter_;
	private JComboBox<String> comboBoxLecturer_;
	private CmbboxFilterLecturer comboBoxLecturerModel_;
	
	

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ })
	public TimetableTab() {

		createContents();
	}
	private void createContents() {
		setAutoscrolls(true);
		
		comboBoxLecturer_ = new JComboBox<>();
		
		comboBoxLecturer_.addActionListener(new ProfessorshipTimetableTabCmbbox());
		setLayout(new MigLayout("", "[124px][23px][112px][501px][94px][right]", "[42px][21px][364px]"));
		comboBoxLecturerModel_= new CmbboxFilterLecturer(comboBoxLecturer_);
		comboBoxLecturer_.setModel(comboBoxLecturerModel_);
		add(comboBoxLecturer_, "cell 0 0,growx,aligny bottom");
		
		lblSemester_ = new JLabel("Semester:");
		add(lblSemester_, "flowx,cell 2 0,alignx left,aligny bottom");
		
		comboBoxSemesterFilter_= new JComboBox<>();
		
		comboBoxSemesterFilter_.setModel(new CmbboxFilterSemester());
		comboBoxSemesterFilter_.addKeyListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter_.addActionListener(new BaseCmbboxFilter());
		comboBoxSemesterFilter_.setEditable(true);
		comboBoxSemesterFilter_.setAutoscrolls(true);
		
		add(comboBoxSemesterFilter_, "cell 2 0,alignx right,aligny bottom");
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new TimetableTabBtn("createpdf"));
		add(btnPdfErzeugen, "cell 3 0,alignx left,aligny bottom");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 5 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel("");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		add(getPanel(), "cell 0 2 5 1,grow");
	}
	
	
	public JPanel getPanel() {
		if (panel_ == null) {
			panel_ = new JPanel();
			panel_.setLayout(new MigLayout("", "[830px:n:830px,grow]", "[]"));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_.add(scrollPane, "cell 0 0,grow");
			
			lecturerTimetableTable_ = new JTable();
			
			scrollPane.setViewportView(lecturerTimetableTable_);
			
	
			lecturerTimetableTable_.setBackground(Color.WHITE);
			lecturerTimetableTable_.setModel(ViewManager.getInstance().getLecturerTimetableTabTable());
			lecturerTimetableTable_.getColumnModel().getColumn(0).setResizable(false);
			lecturerTimetableTable_.getColumnModel().getColumn(0).setPreferredWidth(50);
			lecturerTimetableTable_.getColumnModel().getColumn(0).setMinWidth(50);
			lecturerTimetableTable_.getColumnModel().getColumn(0).setMaxWidth(105);
			lecturerTimetableTable_.getColumnModel().getColumn(1).setResizable(false);
			lecturerTimetableTable_.getColumnModel().getColumn(1).setPreferredWidth(50);
			lecturerTimetableTable_.getColumnModel().getColumn(1).setMinWidth(50);
			lecturerTimetableTable_.getColumnModel().getColumn(1).setMaxWidth(145);
			lecturerTimetableTable_.getColumnModel().getColumn(2).setResizable(false);
			lecturerTimetableTable_.getColumnModel().getColumn(2).setMinWidth(75);
			lecturerTimetableTable_.getColumnModel().getColumn(2).setMaxWidth(145);
			lecturerTimetableTable_.getColumnModel().getColumn(3).setPreferredWidth(80);
			lecturerTimetableTable_.getColumnModel().getColumn(3).setMinWidth(80);
			lecturerTimetableTable_.getColumnModel().getColumn(3).setMaxWidth(145);
			lecturerTimetableTable_.getColumnModel().getColumn(4).setPreferredWidth(70);
			lecturerTimetableTable_.getColumnModel().getColumn(4).setMinWidth(70);
			lecturerTimetableTable_.getColumnModel().getColumn(4).setMaxWidth(145);
			lecturerTimetableTable_.getColumnModel().getColumn(5).setMinWidth(70);
			lecturerTimetableTable_.getColumnModel().getColumn(5).setMaxWidth(145);
			
			reloadPlan();
			
		}
		return panel_;
	}
	
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter_;
	}
	
	public JComboBox<String> getcomboBoxLecturer_() {
		return comboBoxLecturer_;
	}
	
	public String getLectureOnTime(List<RoomAllocation> ral,int day, int time){
		String textualRepresentation="";
		
		for(int i=0;i<ral.size();i++){
			if(ral.get(i).getDay_()==day && ral.get(i).getTime_()==time){
				//textualRepresentation=ral.get(i).getCourse_().getCourseName_()+" ("+ral.get(i).getCourse_().getLecturer_().getChair_().getAcronym_()+" - "+ral.get(i).getCourse_().getCourseAcronym_()+")";
				textualRepresentation=ral.get(i).getCourse_().getCourseAcronym_()+" - in - "+ral.get(i).getRoom_().getRoomNumber_();
			}
		}
		
		return textualRepresentation;
	}
	
	public void reloadPlan(){
		
		if(this.getcomboBoxLecturer_().getSelectedItem().toString().toLowerCase().contains("alle")){
			
		}else{
			
		ViewManager.getInstance().getLecturerTimetableTabTable().setRowCount(0);
			
			HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
			
			tableFilter.put("semester", this.getComboBoxSemesterFilter().getSelectedItem().toString());
			
			System.out.println("Semesterfilter: "+this.getComboBoxSemesterFilter().getSelectedItem().toString());
			
			tableFilter.put("login", this.comboBoxLecturerModel_.getLecturerForModel().get(this.getcomboBoxLecturer_().getSelectedIndex()).getLogin_());
			
			List<RoomAllocation> ral=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
			
			for(int i=1;i<=7;i++){
				
				Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
				
				ViewManager.getInstance().getLecturerTimetableTabTable().addRow(rowData);
				
				
				for(int j=1; j<=5; j++){
					
					ViewManager.getInstance().getLecturerTimetableTabTable().setValueAt(getLectureOnTime(ral,j,i), i-1, j);
					
				}
			}
			
		}
		
	}
	
	public JPanel getPanel_() {
		return panel_;
	}
	
	
}

