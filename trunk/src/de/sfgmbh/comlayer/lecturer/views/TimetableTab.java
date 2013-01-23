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
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.organisation.controller.CtrlRoomAllocation;
import de.sfgmbh.applayer.organisation.definitions.IntfCtrlRoomAllocation;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.TimetableTabBtn;
import de.sfgmbh.comlayer.lecturer.controller.TimetableTabCmbbox;

public class TimetableTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable lecturerTimetableTable_;
	private JPanel panel_;
	private JLabel lblSemester_;
	private JComboBox<String> comboBoxSemesterFilter_;
	private CmbboxFilterSemester comboBoxSemesterModel_= new CmbboxFilterSemester("select");
	private JComboBox<String> comboBoxLecturer_;
	private CmbboxFilterLecturer comboBoxLecturerModel_;
	private JLabel lblWeekplanLecturer;
	
	

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
		
		comboBoxLecturer_.addActionListener(new TimetableTabCmbbox());
		setLayout(new MigLayout("", "[5.00,grow][][][][][][-1.00,grow][right]", "[][][364px]"));
		
		lblWeekplanLecturer = new JLabel("<html><h3>Wochenplan für Dozenten</h3></html>");
		add(lblWeekplanLecturer, "cell 1 0");
		
		JPanel uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 7 0,alignx right,aligny top");
		
		JLabel lblUniIcon = new JLabel("");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		comboBoxLecturerModel_= new CmbboxFilterLecturer(comboBoxLecturer_, "select");
		comboBoxLecturer_.setModel(comboBoxLecturerModel_);
		add(comboBoxLecturer_, "cell 1 1,growx,aligny bottom");
		
		lblSemester_ = new JLabel("Semester:");
		add(lblSemester_, "flowx,cell 3 1,alignx left,aligny bottom");
		
		comboBoxSemesterFilter_= new JComboBox<>();
		
		comboBoxSemesterFilter_.setModel(comboBoxSemesterModel_);
		comboBoxSemesterFilter_.addActionListener(new TimetableTabCmbbox());
		comboBoxSemesterFilter_.setEditable(true);
		comboBoxSemesterFilter_.setAutoscrolls(true);
		
		add(comboBoxSemesterFilter_, "cell 3 1,alignx right,aligny bottom");
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new TimetableTabBtn("createpdf"));
		add(btnPdfErzeugen, "cell 4 1,alignx left,aligny bottom");
		add(getPanel(), "cell 1 2 5 1,grow");
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
	
	public void reloadPlan(){
		
		IntfCtrlRoomAllocation roomAllocationController=new CtrlRoomAllocation();
		
		if(!this.getcomboBoxLecturer_().getSelectedItem().toString().toLowerCase().contains("alle")){
			
		ViewManager.getInstance().getLecturerTimetableTabTable().setRowCount(0);
			
			HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
			
			tableFilter.put("semester", this.getComboBoxSemesterFilter().getSelectedItem().toString());
			
			tableFilter.put("login", this.comboBoxLecturerModel_.getLecturerForModel().get(this.getcomboBoxLecturer_().getSelectedIndex()).getLogin_());
			
			List<IntfRoomAllocation> roomAllocations=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
			
			for(int i=1;i<=7;i++){
				
				Object[] rowData= {ViewHelper.getTime(i)+" Uhr", "","", "", "", ""};
				
				ViewManager.getInstance().getLecturerTimetableTabTable().addRow(rowData);
				
				
				for(int j=1; j<=5; j++){
					
					ViewManager.getInstance().getLecturerTimetableTabTable().setValueAt(roomAllocationController.getLectureOnTime(roomAllocations,j,i), i-1, j);
					
				}
			}
			
		}
		
	}
	
	public JPanel getPanel_() {
		return panel_;
	}
	
	
}

