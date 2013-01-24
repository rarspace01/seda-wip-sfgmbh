
package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterAllocationStatus;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChairAcronym;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.RequestTabBtnsControl;
import de.sfgmbh.comlayer.organisation.controller.RequestTabCmbboxFilter;

/**
 * Tab for the room request management
 * 
 * @author anna
 * @author hannes
 *
 */
public class RequestTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable roomAllocationTable_;
	private JLabel lblLecturer_;
	private JLabel lblProfessorship_;
	private JLabel lblStatus_;
	private JLabel lblPCSeats_;
	private JComboBox<String> comboBoxLecturer_;
	private JComboBox<String> comboBoxChair_;
	private JScrollPane organisationTableScrollPane_;
	private JPanel buttonPanel_;
	private JButton btnFreigeben_;
	private JComboBox<String> comboBoxStatus_;
	private JComboBox<String> comboBoxSemester_;
	private JPanel uniIconPanel_;
	private JLabel lblUniIcon_;
	private JPanel filterTop_;
	private JPanel filterBottom_;
	private JLabel lblRoom_;
	private JTextField txtRoom_;
	private TableRowSorter<TableModel> rowSorter_;
	private JButton btnClean_;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ })
	public RequestTab() {
		
		initialize();
	}
	private void initialize() {
		setMinimumSize(new Dimension(100, 10));
		setMaximumSize(new Dimension(100, 32767));
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow][grow][grow][100px:100px:100px]", "[::40px,grow,center][][419.00,grow][]"));
		
		JLabel lblRaumverwaltung = new JLabel("Raumanfragen");
		lblRaumverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRaumverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblLecturer_ = new JLabel("Dozent:");
		add(lblLecturer_, "cell 2 0,aligny bottom");
		
		comboBoxLecturer_ = new JComboBox<String>();
		comboBoxLecturer_.addActionListener(new RequestTabCmbboxFilter());
		
		lblPCSeats_ = new JLabel("Semester:");
		add(lblPCSeats_, "cell 4 0,aligny bottom");
		add(getLblRoom(), "cell 5 0,aligny bottom");
		
		uniIconPanel_ = new JPanel();
		add(uniIconPanel_, "cell 6 0,alignx right,aligny top");	
		lblUniIcon_ = new JLabel();
		lblUniIcon_.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon_.setMaximumSize(new Dimension(50,50));
		uniIconPanel_.add(lblUniIcon_);
		
		comboBoxLecturer_.setModel(new CmbboxFilterLecturer(comboBoxLecturer_));
		comboBoxLecturer_.setEditable(true);
		comboBoxLecturer_.setAutoscrolls(true);
		add(comboBoxLecturer_, "cell 2 1,growx,aligny center");
		add(getFilterBottom(), "cell 3 1,alignx left,aligny center");
		add(getFilterTop(), "cell 3 0,alignx left,aligny bottom");
		
		comboBoxSemester_ = new JComboBox<String>();
		comboBoxSemester_.addActionListener(new RequestTabCmbboxFilter());
		comboBoxSemester_.setModel(new CmbboxFilterSemester());
		comboBoxSemester_.setEditable(true);
		comboBoxSemester_.setAutoscrolls(true);
		add(comboBoxSemester_, "cell 4 1,growx");
		add(getTxtRoom(), "cell 5 1,growx");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow");
		
		organisationTableScrollPane_ = new JScrollPane();
		add(organisationTableScrollPane_, "flowx,cell 2 2 4 1,grow");
		
		roomAllocationTable_ = new JTable();
		roomAllocationTable_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomAllocationTable_.setModel(ViewManager.getInstance().getOrgaRequestTableModel());
		roomAllocationTable_.setShowVerticalLines(false);
		roomAllocationTable_.setBackground(SystemColor.activeCaption);
		roomAllocationTable_.getColumnModel().removeColumn(roomAllocationTable_.getColumn("Hidden"));
		organisationTableScrollPane_.setViewportView(roomAllocationTable_);
		
		// Enable table sorting for the model
		rowSorter_ = new TableRowSorter<TableModel>();
		roomAllocationTable_.setRowSorter(rowSorter_);
		rowSorter_.setModel(ViewManager.getInstance().getOrgaRequestTableModel());
		List <RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(6, SortOrder.DESCENDING));
		rowSorter_.setSortKeys(sortKeys); 
		rowSorter_.sort();
		
		buttonPanel_ = new JPanel();
		buttonPanel_.setLayout(null);
		buttonPanel_.setMinimumSize(new Dimension(80, 10));
		buttonPanel_.setMaximumSize(new Dimension(120, 32767));
		add(buttonPanel_, "cell 6 2,grow");
		
		btnFreigeben_ = new JButton("freigeben");
		btnFreigeben_.setBounds(6, 11, 88, 23);
		btnFreigeben_.addActionListener(new RequestTabBtnsControl("accept"));
		buttonPanel_.add(btnFreigeben_);
		
		JButton btnAblehnen = new JButton("ablehnen");
		btnAblehnen.setBounds(6, 45, 88, 23);
		btnAblehnen.addActionListener(new RequestTabBtnsControl("deny"));
		buttonPanel_.add(btnAblehnen);
		
		JButton btnGegenvorschlag = new JButton("lösen");
		btnGegenvorschlag.setMinimumSize(new Dimension(90, 23));
		btnGegenvorschlag.setMaximumSize(new Dimension(90, 23));
		btnGegenvorschlag.setPreferredSize(new Dimension(50, 23));
		btnGegenvorschlag.addActionListener(new RequestTabBtnsControl("solve"));
		btnGegenvorschlag.setBounds(6, 79, 88, 23);
		buttonPanel_.add(btnGegenvorschlag);
		buttonPanel_.add(getBtnClean());
		
	}
	/**
	 * @return the comboBoxLecturer
	 */
	public JComboBox<String> getComboBoxLecturer() {
		return comboBoxLecturer_;
	}
	/**
	 * @return the comboBoxChair
	 */
	public JComboBox<String> getComboBoxChair() {
		return comboBoxChair_;
	}
	/**
	 * @return the comboBoxStatus
	 */
	public JComboBox<String> getComboBoxStatus() {
		return comboBoxStatus_;
	}
	/**
	 * @return the comboBoxSemester
	 */
	public JComboBox<String> getComboBoxSemester() {
		return comboBoxSemester_;
	}
	/**
	 * @return the roomAllocationTable
	 */
	public JTable getRoomAllocationTable() {
		return roomAllocationTable_;
	}
	public JPanel getFilterTop() {
		if (filterTop_ == null) {
			filterTop_ = new JPanel();
			filterTop_.setLayout(new MigLayout("insets 0", "[200px:200px:200px][100px:100px]", "[]"));
			
			lblProfessorship_ = new JLabel("Lehrstuhl:");
			filterTop_.add(lblProfessorship_, "cell 0 0");
			
			lblStatus_ = new JLabel("Freigabestatus:");
			filterTop_.add(lblStatus_, "cell 1 0");
		}
		return filterTop_;
	}
	public JPanel getFilterBottom() {
		if (filterBottom_ == null) {
			filterBottom_ = new JPanel();
			filterBottom_.setBorder(null);
			filterBottom_.setAlignmentX(0.0f);
			filterBottom_.setLayout(new MigLayout("insets 0", "[200px:200px:200px][120px:120px:120px]", "[]"));
			
			comboBoxChair_ = new JComboBox<String>();
			comboBoxChair_.setMaximumSize(new Dimension(200, 50));
			filterBottom_.add(comboBoxChair_, "cell 0 0,aligny center");
			comboBoxChair_.addActionListener(new RequestTabCmbboxFilter());
			comboBoxChair_.setModel(new CmbboxFilterChairAcronym(comboBoxChair_));
			comboBoxChair_.setEditable(true);
			comboBoxChair_.setAutoscrolls(true);
			
			comboBoxStatus_ = new JComboBox<String>();
			filterBottom_.add(comboBoxStatus_, "cell 1 0,aligny center");
			comboBoxStatus_.addActionListener(new RequestTabCmbboxFilter());
			comboBoxStatus_.setModel(new CmbboxFilterAllocationStatus());
			comboBoxStatus_.setEditable(true);
			comboBoxStatus_.setAutoscrolls(true);
		}
		return filterBottom_;
	}
	public JLabel getLblRoom() {
		if (lblRoom_ == null) {
			lblRoom_ = new JLabel("Raum:");
		}
		return lblRoom_;
	}
	public JTextField getTxtRoom() {
		if (txtRoom_ == null) {
			txtRoom_ = new JTextField();
			txtRoom_.setColumns(10);
			txtRoom_.addActionListener(new RequestTabCmbboxFilter());
		}
		return txtRoom_;
	}
	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter_;
	}
	/**
	 * @param rowSorter the rowSorter to set
	 */
	public void setRowSorter(TableRowSorter<TableModel> rowSorter) {
		this.rowSorter_ = rowSorter;
	}
	public JButton getBtnClean() {
		if (btnClean_ == null) {
			btnClean_ = new JButton("aufräumen");
			btnClean_.setBounds(5, 161, 89, 23);
			btnClean_.addActionListener(new RequestTabBtnsControl("clean"));
		}
		return btnClean_;
	}
}