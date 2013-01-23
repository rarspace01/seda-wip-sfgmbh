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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.CtrlGenericTables;
import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.definitions.IntfCtrlGenericTables;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabBtn;
import de.sfgmbh.comlayer.lecturer.controller.ProfessorshipTimetableTabCmbbox;
/**
 * 
 * @author hannes
 * @author denis
 *
 */
public class ProfessorshipTimetableTab extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTable chairTimetableTable_;
	private JPanel panelProfessorshipPanel_;
	private JComboBox<String> comboBoxSemesterFilter_;
	private CmbboxFilterSemester comboBoxSemesterModel_= new CmbboxFilterSemester("select");
	private int maxEntries_=0;
	private JScrollPane scrollPane_;


	/**
	 * Create the panel.
	 */
	public ProfessorshipTimetableTab() {

		createContents();
	}
	private void createContents() {
		setAutoscrolls(true);
		setLayout(new MigLayout("", "[14.00,grow][][10px][112px][][][10.00,grow][right]", "[][][383px]"));
		
		JLabel lblWeekplanforTheChair = new JLabel("<html><h3>Wochenplan f\u00FCr den Lehrstuhl</h3></html>");
		lblWeekplanforTheChair.setFont(new Font("Arial", Font.PLAIN, 11));
		add(lblWeekplanforTheChair, "flowy,cell 1 0,growx,aligny center");
		
		JLabel lblUniIcon = new JLabel("");
		lblUniIcon.setVerticalAlignment(SwingConstants.TOP);
		lblUniIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblUniIcon, "cell 7 0,alignx right,aligny top");
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		
		JLabel lblSemester = new JLabel("Semester:");
		add(lblSemester, "flowx,cell 1 1,alignx left,aligny bottom");
		
		JButton btnPdfErzeugen = new JButton("PDF Erzeugen");
		btnPdfErzeugen.addActionListener(new ProfessorshipTimetableTabBtn("createpdf"));
		add(btnPdfErzeugen, "cell 3 1,growx,aligny bottom");
		
		panelProfessorshipPanel_=getPanel();
		
		add(panelProfessorshipPanel_, "cell 1 2 5 1,grow");
		
		JPanel uniIconPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) uniIconPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(uniIconPanel, "flowx,cell 5 0,alignx left,aligny top");
		
		comboBoxSemesterFilter_= new JComboBox<>();
		
		comboBoxSemesterFilter_.setModel(comboBoxSemesterModel_);
		comboBoxSemesterFilter_.addActionListener(new ProfessorshipTimetableTabCmbbox());
		comboBoxSemesterFilter_.setEditable(true);
		comboBoxSemesterFilter_.setAutoscrolls(true);
		
		add(comboBoxSemesterFilter_, "cell 1 1,alignx center,aligny bottom");
		
		reloadPlan();
	}
	/**
	 * 
	 * @return the panelProfessorshipPanel_
	 */
	public JPanel getPanel() {
		if (panelProfessorshipPanel_ == null) {
			panelProfessorshipPanel_ = new JPanel();
			panelProfessorshipPanel_.setLayout(new MigLayout("", "[830px:n:830px]", "[grow]"));
			
			scrollPane_ = new JScrollPane();
			scrollPane_.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			panelProfessorshipPanel_.add(scrollPane_, "cell 0 0,growx,aligny top");
			
			chairTimetableTable_ = new JTable();
			chairTimetableTable_.setPreferredScrollableViewportSize(new Dimension(750, 400));
			chairTimetableTable_.setBorder(null);
			
			
			
			scrollPane_.setViewportView(chairTimetableTable_);
			
	
			chairTimetableTable_.setBackground(Color.WHITE);
			chairTimetableTable_.setModel(ViewManager.getInstance().getLecturerChairimetableTabTable());
		
			
			//comboBoxSemesterFilter_
			
			chairTimetableTable_.getColumnModel().getColumn(0).setPreferredWidth(50);
			chairTimetableTable_.getColumnModel().getColumn(1).setPreferredWidth(70);
			chairTimetableTable_.getColumnModel().getColumn(2).setPreferredWidth(70);
			chairTimetableTable_.getColumnModel().getColumn(3).setPreferredWidth(70);
			chairTimetableTable_.getColumnModel().getColumn(4).setPreferredWidth(70);
			chairTimetableTable_.getColumnModel().getColumn(5).setPreferredWidth(70);
			
			chairTimetableTable_.setDefaultRenderer(Object.class, new LineWrapCellRenderer(maxEntries_));
			
		}
		return panelProfessorshipPanel_;
	}
	/**
	 * reloads the timetable for the whole chair
	 */
	public void reloadPlan(){
		
		IntfCtrlGenericTables genericTablesController=new CtrlGenericTables();
			
		HashMap<String,String> tableFilter = new HashMap<String,String> ();  //setting filter
		//get the selected semester
		tableFilter.put("semester", this.getComboBoxSemesterFilter().getSelectedItem().toString());
		//get the chairid of the logged in user
		if(SessionManager.getInstance().getSession() != null){
			tableFilter.put("chairid", ""+SessionManager.getInstance().getSession().getChair_().getChairId_());
		}
		// get the roomAllocations from the repository
		List<IntfRoomAllocation> roomAllocationList=AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(tableFilter);
		// reload Table based on roomAllocations
		genericTablesController.reloadTable(chairTimetableTable_, roomAllocationList,true);
		
		// Set the maximum size of the scroll pane (don't forget to add the table header!)
		scrollPane_.setMaximumSize(new Dimension(32767, ((int) chairTimetableTable_.getPreferredSize().getHeight() + 26)));
		this.updateUI();
	}
	/**
	 * 
	 * @return the panelProfessorshipPanel_
	 */
	public JPanel getPanel_() {
		return panelProfessorshipPanel_;
	}
	/**
	 * 
	 * @return the comboBoxSemesterFilter_
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		return comboBoxSemesterFilter_;
	}
	
}
