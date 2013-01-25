package de.sfgmbh.comlayer.lecturer.views;

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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.definitions.IntfRoomAllocation;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.applayer.lecturer.definitions.IntfCtrlStartTab;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.model.CmbboxFilterAllocationStatus;
import de.sfgmbh.comlayer.core.model.CmbboxFilterCourse;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.StartTabBtnsControl;
import de.sfgmbh.comlayer.lecturer.controller.StartTabCmbboxFilter;
import de.sfgmbh.comlayer.lecturer.controller.StartTabTableTop;

/**
 * Start tab panel
 * 
 * @author anna
 * @author mario
 * @author christian
 * 
 */
public class StartTab extends JPanel implements IntfComDialogObserver {

	private static final long serialVersionUID = 1L;
	private JTable tableCourseTop_;
	private JLabel lblLecturer_;
	private JComboBox<String> comboLecturer_;
	private JScrollPane lvOrganisationTableScrollPane_;
	private JPanel buttonPanel_;
	private JButton btnAdd_;
	private JButton btnRoomRequest_;
	private JButton btnEdit_;
	private JButton btnDelete_;
	private JComboBox<String> comboBoxStatus_;
	private JLabel lblStatus_;
	private JPanel tablePanel_;
	private JTable tableRoomAllocationBottom_;
	private JComboBox<String> comboBoxLecturerBottom_;
	private JComboBox<String> comboBoxCourse_;
	private JComboBox<String> comboBoxSemesterBottom_;
	private TableRowSorter<TableModel> rowSorterTop_;
	private TableRowSorter<TableModel> rowSorterBottom_;
	private IntfRoomAllocation roomAllocation_;
	private IntfCtrlStartTab ctrlStartTab_ = new CtrlStartTab();

	/**
	 * Create the panel.
	 */
	public StartTab() {
		initialize();
	}

	private void initialize() {

		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout(
				"",
				"[140px:n:140px,grow][][grow][grow][grow][grow][100px:100px:100px,right]",
				"[30.00][][][grow][grow]"));

		JLabel lblLehrveranstaltungen = new JLabel("Lehrveranstaltungen:");
		lblLehrveranstaltungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblLehrveranstaltungen, "cell 1 0,alignx right,aligny bottom");

		add(ViewManager.getInstance().getCoreLiveTickerPanel(),
				"cell 0 3, grow, aligny top");

		lblLecturer_ = new JLabel("Dozent:");
		add(lblLecturer_, "cell 1 1,alignx left,aligny bottom");

		tablePanel_ = new JPanel();
		add(tablePanel_, "cell 1 3 5 1,grow");
		tablePanel_.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[][-43.00][150px:n:200px,grow][][][][100px:n:200px,grow][]"));

		lvOrganisationTableScrollPane_ = new JScrollPane();
		tablePanel_.add(lvOrganisationTableScrollPane_,
				"cell 0 2 5 1,aligny top, grow");

		tableCourseTop_ = new JTable();
		tableCourseTop_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourseTop_.addMouseListener(new StartTabTableTop());
		tableCourseTop_.setModel(ViewManager.getInstance()
				.getLecturerStartTabTableTop());
		tableCourseTop_.getColumnModel().removeColumn(
				tableCourseTop_.getColumn("Hidden"));
		tableCourseTop_.setShowVerticalLines(false);
		tableCourseTop_.setBackground(SystemColor.activeCaption);
		lvOrganisationTableScrollPane_.setViewportView(tableCourseTop_);

		// Enable table sorting for the top table model
		rowSorterTop_ = new TableRowSorter<TableModel>();
		tableCourseTop_.setRowSorter(rowSorterTop_);
		rowSorterTop_.setModel(ViewManager.getInstance()
				.getLecturerStartTabTableTop());
		rowSorterTop_.sort();

		JLabel lblRoomallocations = new JLabel("Raumzuordnungen:");
		lblRoomallocations.setFont(new Font("Tahoma", Font.BOLD, 12));
		tablePanel_.add(lblRoomallocations, "cell 0 3");

		JLabel lblLecturerBottom = new JLabel("Dozent:");
		tablePanel_.add(lblLecturerBottom, "cell 0 4");

		JLabel lblCourse = new JLabel("Lehrveranstaltung:");
		tablePanel_.add(lblCourse, "cell 1 4");

		JLabel labelSemester = new JLabel("Semester:");
		tablePanel_.add(labelSemester, "cell 2 4");

		comboBoxLecturerBottom_ = new JComboBox<String>();
		comboBoxLecturerBottom_
				.setToolTipText("<html>Sie können die Liste der <br> Raumzuordnungen über <br> diese Filter begrenzen</html>");
		comboBoxLecturerBottom_.setModel(new CmbboxFilterLecturer(
				comboBoxLecturerBottom_, "default", true));
		comboBoxLecturerBottom_.addActionListener(new StartTabCmbboxFilter());

		lblStatus_ = new JLabel("Ver\u00F6ffentlichungsstatus:");
		tablePanel_.add(lblStatus_, "cell 3 4");
		comboBoxLecturerBottom_.setEditable(true);
		comboBoxLecturerBottom_.setAutoscrolls(true);
		tablePanel_.add(comboBoxLecturerBottom_, "cell 0 5,growx");

		comboBoxCourse_ = new JComboBox<String>();
		comboBoxCourse_
				.setToolTipText("<html>Sie können die Liste der <br> Raumzuordnungen über <br> diese Filter begrenzen</html>");
		comboBoxCourse_.addActionListener(new StartTabCmbboxFilter());
		comboBoxCourse_.setModel(new CmbboxFilterCourse(comboBoxCourse_, true));
		comboBoxCourse_.setEditable(true);
		comboBoxCourse_.setAutoscrolls(true);
		tablePanel_.add(comboBoxCourse_, "cell 1 5,growx");

		comboBoxSemesterBottom_ = new JComboBox<String>();
		comboBoxSemesterBottom_
				.setToolTipText("<html>Sie können die Liste der <br> Raumzuordnungen über <br> diese Filter begrenzen</html>");
		comboBoxSemesterBottom_.addActionListener(new StartTabCmbboxFilter());
		comboBoxSemesterBottom_.setModel(new CmbboxFilterSemester());
		comboBoxSemesterBottom_.setEditable(true);
		comboBoxSemesterBottom_.setAutoscrolls(true);
		tablePanel_.add(comboBoxSemesterBottom_, "cell 2 5,growx");

		comboBoxStatus_ = new JComboBox<String>();
		comboBoxStatus_
				.setToolTipText("<html>Sie können die Liste der <br> Raumzuordnungen über <br> diese Filter begrenzen</html>");
		comboBoxStatus_.addActionListener(new StartTabCmbboxFilter());
		comboBoxStatus_.setModel(new CmbboxFilterAllocationStatus());
		tablePanel_.add(comboBoxStatus_, "cell 3 5");
		comboBoxStatus_.setEditable(true);
		comboBoxStatus_.setAutoscrolls(true);

		JScrollPane raumanfragenScrollPane = new JScrollPane();
		tablePanel_.add(raumanfragenScrollPane, "cell 0 6 5 1,grow");

		tableRoomAllocationBottom_ = new JTable();
		tableRoomAllocationBottom_
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRoomAllocationBottom_.setModel(ViewManager.getInstance()
				.getLecturerStartTabTableBottom());
		tableRoomAllocationBottom_.setShowVerticalLines(false);
		tableRoomAllocationBottom_.getColumnModel().removeColumn(
				tableRoomAllocationBottom_.getColumn("Hidden"));
		tableRoomAllocationBottom_.setBackground(SystemColor.activeCaption);
		raumanfragenScrollPane.setViewportView(tableRoomAllocationBottom_);

		// Enable table sorting for the bottom table model
		rowSorterBottom_ = new TableRowSorter<TableModel>();
		tableRoomAllocationBottom_.setRowSorter(rowSorterBottom_);
		rowSorterBottom_.setModel(ViewManager.getInstance()
				.getLecturerStartTabTableBottom());
		rowSorterBottom_.sort();

		buttonPanel_ = new JPanel();
		buttonPanel_.setLayout(null);
		buttonPanel_.setMinimumSize(new Dimension(80, 10));
		buttonPanel_.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel_, "cell 6 3,grow");

		btnAdd_ = new JButton("hinzuf\u00FCgen");
		btnAdd_.setToolTipText("<html>Hier klicken um eine <br>Lehrveranstaltung zu erstellen</html>");
		btnAdd_.addActionListener(new StartTabBtnsControl("add"));
		btnAdd_.setBounds(0, 54, 88, 23);
		buttonPanel_.add(btnAdd_);

		btnEdit_ = new JButton("bearbeiten");
		btnEdit_.setToolTipText("<html>Selektieren Sie eine Lehrveranstaltung<br> um sie zu bearbeiten</html>");
		btnEdit_.addActionListener(new StartTabBtnsControl("edit"));
		btnEdit_.setBounds(0, 79, 88, 23);
		buttonPanel_.add(btnEdit_);

		btnDelete_ = new JButton("löschen");
		btnDelete_
				.setToolTipText("<html>Selektieren Sie eine Lehrveranstaltung<br> um sie zu löschen</html>");
		btnDelete_.addActionListener(new StartTabBtnsControl("delete"));
		btnDelete_.setBounds(0, 106, 88, 23);
		buttonPanel_.add(btnDelete_);

		JButton btnPublish = new JButton("freigeben");
		btnPublish
				.setToolTipText("<html>Selektieren Sie eine Lehrveranstaltung<br> um sie frei zu geben</html>");
		btnPublish.addActionListener(new StartTabBtnsControl("publish"));
		btnPublish.setBounds(0, 194, 88, 23);
		buttonPanel_.add(btnPublish);

		btnRoomRequest_ = new JButton("Anfrage");
		btnRoomRequest_
				.setToolTipText("<html> Für erstellte Lehrveranstaltungen<br> können Sie eine Raumanfrage<br> an die Verwaltung senden</html>");
		btnRoomRequest_
				.addActionListener(new StartTabBtnsControl("roomrequest"));
		btnRoomRequest_.setBounds(0, 160, 88, 23);
		buttonPanel_.add(btnRoomRequest_);

		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panel.setBounds(-20, 232, 110, 1);
		buttonPanel_.add(panel);

		JButton btnRecall = new JButton("widerrufen");
		btnRecall
				.setToolTipText("<html> abgelehnte oder nicht mehr benötigte <br> Raumanfragen widerrufen </html>");
		btnRecall.addActionListener(new StartTabBtnsControl("back"));
		btnRecall.setBounds(0, 260, 88, 23);
		buttonPanel_.add(btnRecall);

		JLabel lblUniIcon = new JLabel("");
		lblUniIcon
				.setIcon(new ImageIcon(
						BaseTab.class
								.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50, 50));
		add(lblUniIcon, "cell 6 0 1 2,alignx right,aligny top");

		comboLecturer_ = new JComboBox<String>();
		add(comboLecturer_, "cell 1 2,alignx left,aligny bottom");
		comboLecturer_
				.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
		comboLecturer_.addActionListener(new StartTabCmbboxFilter());
		comboLecturer_.setModel(new CmbboxFilterLecturer(comboLecturer_,
				"default", true));
		comboLecturer_.setEditable(true);
		comboLecturer_.setAutoscrolls(true);
	}

	/**
	 * @return the comboLecturer
	 */
	public JComboBox<String> getComboLecturer() {
		return comboLecturer_;
	}

	/**
	 * @return the comboBoxStatus
	 */
	public JComboBox<String> getComboBoxStatus() {
		return comboBoxStatus_;
	}

	/**
	 * @return the comboBoxLecturerBottom
	 */
	public JComboBox<String> getComboBoxLecturerBottom() {
		return comboBoxLecturerBottom_;
	}

	/**
	 * @return the comboBoxCourse
	 */
	public JComboBox<String> getComboBoxCourse() {
		return comboBoxCourse_;
	}

	/**
	 * @return the comboBoxSemesterBottom
	 */
	public JComboBox<String> getComboBoxSemesterBottom() {
		return comboBoxSemesterBottom_;
	}

	/**
	 * @return the tableCourseTop
	 */
	public JTable getTableCourseTop() {
		return tableCourseTop_;
	}

	/**
	 * @return the tableRoomAllocationBottom
	 */
	public JTable getTableRoomAllocationBottom() {
		return tableRoomAllocationBottom_;
	}

	/**
	 * @return the rowSorterTop
	 */
	public TableRowSorter<TableModel> getRowSorterTop() {
		return rowSorterTop_;
	}

	/**
	 * @return the rowSorterBottom
	 */
	public TableRowSorter<TableModel> getRowSorterBottom() {
		return rowSorterBottom_;
	}

	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.roomAllocation_ != null) {
				this.ctrlStartTab_.counterRoomAllocation(roomAllocation_, true);
				this.roomAllocation_ = null;
			}
		} else if (answer.equals("no")) {
			this.ctrlStartTab_.counterRoomAllocation(roomAllocation_, false);
			this.roomAllocation_ = null;
		}
	}

	/**
	 * @return the roomAllocation
	 */
	public IntfRoomAllocation getRoomAllocation() {
		return roomAllocation_;
	}

	/**
	 * @param roomAllocation
	 *            the roomAllocation to set
	 */
	public void setRoomAllocation(IntfRoomAllocation roomAllocation) {
		this.roomAllocation_ = roomAllocation;
	}
}
