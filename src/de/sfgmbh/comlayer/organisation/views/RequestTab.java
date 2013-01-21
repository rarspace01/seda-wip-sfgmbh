
package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterAllocationStatus;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChair;
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
	private JTable roomAllocationTable;
	private JLabel lblLecturer;
	private JLabel lblProfessorship;
	private JLabel lblStatus;
	private JLabel lblPCSeats;
	private JComboBox<String> comboBoxLecturer;
	private JComboBox<String> comboBoxChair;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnFreigeben;
	private JComboBox<String> comboBoxStatus;
	private JComboBox<String> comboBoxSemester;
	private JPanel uniIconPanel;
	private JLabel lblUniIcon;
	private JPanel filterTop;
	private JPanel filterBottom;
	private JLabel lblRoom;
	private JTextField txtRoom;
	private TableRowSorter<TableModel> rowSorter;

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
		setLayout(new MigLayout("", "[140px:140px:140px][10px:10px:10px][grow][grow][grow][grow][100px:100px:100px]", "[][][419.00,grow][]"));
		
		JLabel lblRaumverwaltung = new JLabel("Raumanfragen");
		lblRaumverwaltung.setFont(new Font("SansSerif", Font.BOLD, 13));
		add(lblRaumverwaltung, "cell 0 0,alignx center,aligny bottom");
		
		lblLecturer = new JLabel("Dozent:");
		add(lblLecturer, "cell 2 0,aligny bottom");
		
		comboBoxLecturer = new JComboBox<String>();
		comboBoxLecturer.addActionListener(new RequestTabCmbboxFilter());
		
		lblPCSeats = new JLabel("Semester:");
		add(lblPCSeats, "cell 4 0,aligny bottom");
		add(getLblRoom(), "cell 5 0,aligny bottom");
		
		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx center,growy");	
		lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		comboBoxLecturer.setModel(new CmbboxFilterLecturer(comboBoxLecturer));
		comboBoxLecturer.setEditable(true);
		comboBoxLecturer.setAutoscrolls(true);
		add(comboBoxLecturer, "cell 2 1,growx,aligny center");
		add(getFilterBottom(), "cell 3 1,alignx left,aligny center");
		
		comboBoxSemester = new JComboBox<String>();
		comboBoxSemester.addActionListener(new RequestTabCmbboxFilter());
		comboBoxSemester.setModel(new CmbboxFilterSemester());
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		add(comboBoxSemester, "cell 4 1,growx");
		add(getTxtRoom(), "cell 5 1,growx");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2,grow");
		
		organisationTableScrollPane = new JScrollPane();
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		roomAllocationTable = new JTable();
		roomAllocationTable.setModel(ViewManager.getInstance().getOrgaRequestTableModel());
		roomAllocationTable.setShowVerticalLines(false);
		roomAllocationTable.setBackground(SystemColor.activeCaption);
		roomAllocationTable.getColumnModel().removeColumn(roomAllocationTable.getColumn("Hidden"));
		organisationTableScrollPane.setViewportView(roomAllocationTable);
		
		// Enable table sorting for the model
		rowSorter = new TableRowSorter<TableModel>();
		roomAllocationTable.setRowSorter(rowSorter);
		rowSorter.setModel(ViewManager.getInstance().getOrgaRequestTableModel());
		rowSorter.sort();
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(120, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnFreigeben = new JButton("freigeben");
		btnFreigeben.setBounds(6, 11, 88, 23);
		btnFreigeben.addActionListener(new RequestTabBtnsControl("accept"));
		buttonPanel.add(btnFreigeben);
		
		JButton btnAblehnen = new JButton("ablehnen");
		btnAblehnen.setBounds(6, 45, 88, 23);
		btnAblehnen.addActionListener(new RequestTabBtnsControl("deny"));
		buttonPanel.add(btnAblehnen);
		
		JButton btnGegenvorschlag = new JButton("lösen");
		btnGegenvorschlag.setMinimumSize(new Dimension(90, 23));
		btnGegenvorschlag.setMaximumSize(new Dimension(90, 23));
		btnGegenvorschlag.setPreferredSize(new Dimension(50, 23));
		btnGegenvorschlag.addActionListener(new RequestTabBtnsControl("solve"));
		btnGegenvorschlag.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnGegenvorschlag);
		
	}
	/**
	 * @return the comboBoxLecturer
	 */
	public JComboBox<String> getComboBoxLecturer() {
		return comboBoxLecturer;
	}
	/**
	 * @return the comboBoxChair
	 */
	public JComboBox<String> getComboBoxChair() {
		return comboBoxChair;
	}
	/**
	 * @return the comboBoxStatus
	 */
	public JComboBox<String> getComboBoxStatus() {
		return comboBoxStatus;
	}
	/**
	 * @return the comboBoxSemester
	 */
	public JComboBox<String> getComboBoxSemester() {
		return comboBoxSemester;
	}
	/**
	 * @return the roomAllocationTable
	 */
	public JTable getRoomAllocationTable() {
		return roomAllocationTable;
	}
	public JPanel getFilterTop() {
		if (filterTop == null) {
			filterTop = new JPanel();
			filterTop.setLayout(new MigLayout("insets 0", "[200px:200px:200px][100px:100px]", "[]"));
			
			lblProfessorship = new JLabel("Lehrstuhl:");
			filterTop.add(lblProfessorship, "cell 0 0");
			
			lblStatus = new JLabel("Freigabestatus:");
			filterTop.add(lblStatus, "cell 1 0");
		}
		return filterTop;
	}
	public JPanel getFilterBottom() {
		if (filterBottom == null) {
			filterBottom = new JPanel();
			filterBottom.setBorder(null);
			filterBottom.setAlignmentX(0.0f);
			filterBottom.setLayout(new MigLayout("insets 0", "[200px:200px:200px][100px:100px:100px]", ""));
			
			comboBoxChair = new JComboBox<String>();
			comboBoxChair.setMaximumSize(new Dimension(200, 50));
			filterBottom.add(comboBoxChair, "cell 0 0,aligny center");
			comboBoxChair.addActionListener(new RequestTabCmbboxFilter());
			comboBoxChair.setModel(new CmbboxFilterChair(comboBoxChair));
			comboBoxChair.setEditable(true);
			comboBoxChair.setAutoscrolls(true);
			
			comboBoxStatus = new JComboBox<String>();
			filterBottom.add(comboBoxStatus, "cell 1 0,aligny center");
			comboBoxStatus.addActionListener(new RequestTabCmbboxFilter());
			comboBoxStatus.setModel(new CmbboxFilterAllocationStatus());
			comboBoxStatus.setEditable(true);
			comboBoxStatus.setAutoscrolls(true);
		}
		return filterBottom;
	}
	public JLabel getLblRoom() {
		if (lblRoom == null) {
			lblRoom = new JLabel("Raum:");
		}
		return lblRoom;
	}
	public JTextField getTxtRoom() {
		if (txtRoom == null) {
			txtRoom = new JTextField();
			txtRoom.setColumns(10);
			txtRoom.addActionListener(new RequestTabCmbboxFilter());
		}
		return txtRoom;
	}
	/**
	 * @return the rowSorter
	 */
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter;
	}
	/**
	 * @param rowSorter the rowSorter to set
	 */
	public void setRowSorter(TableRowSorter<TableModel> rowSorter) {
		this.rowSorter = rowSorter;
	}
}