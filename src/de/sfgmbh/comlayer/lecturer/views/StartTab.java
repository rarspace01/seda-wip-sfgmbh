package de.sfgmbh.comlayer.lecturer.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.SessionManager;
import de.sfgmbh.applayer.core.model.AppModel;
import de.sfgmbh.applayer.core.model.RoomAllocation;
import de.sfgmbh.applayer.core.model.User;
import de.sfgmbh.applayer.lecturer.controller.CtrlStartTab;
import de.sfgmbh.comlayer.core.controller.ViewHelper;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.definitions.IntfComDialogObserver;
import de.sfgmbh.comlayer.core.model.CmbboxFilterAllocationStatus;
import de.sfgmbh.comlayer.core.model.CmbboxFilterCourse;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.core.views.QuestionDialog;
import de.sfgmbh.comlayer.lecturer.controller.StartTabBtnsControl;
import de.sfgmbh.comlayer.lecturer.controller.StartTabCmbboxFilter;
import de.sfgmbh.comlayer.lecturer.controller.StartTabTableTop;

public class StartTab extends JPanel implements IntfComDialogObserver {

	private static final long serialVersionUID = 1L;
	private JTable tableCourseTop;
	private JLabel lblLecturer;
	private JComboBox<String> comboLecturer;
	private JScrollPane lvOrganisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnRoomRequest;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox<String> comboBoxStatus;
	private JLabel lblStatus;
	private JPanel tablePanel;
	private JTable tableRoomAllocationBottom;
	private JComboBox<String> comboBoxLecturerBottom;
	private JComboBox<String> comboBoxCourse;
	private JComboBox<String> comboBoxSemesterBottom;
	private TableRowSorter<TableModel> rowSorterTop;
	private TableRowSorter<TableModel> rowSorterBottom;
	private RoomAllocation roomAllocation;
	private CtrlStartTab ctrlStartTab = new CtrlStartTab();

	/**
	 * Create the panel.
	 */
	public StartTab() {
		User currentUser = SessionManager.getInstance().getSession();
		ViewHelper vh = new ViewHelper();
		
		// When a logged in lecturer is here at fist check if he has counter proposals for which popups should be shown
		if (currentUser != null && currentUser.getClass_().equals("lecturer")) {
			HashMap<String,String> filter = new HashMap<String,String>();
			filter.put("login", currentUser.getLogin_());
			for (RoomAllocation ra : AppModel.getInstance().getRepositoryRoomAllocation().getByFilter(filter)) {
				if (ra.getApproved_().equals("counter")) {
					String orgaMsg = "";
					if (ra.getOrgaMessage_().length() > 1) {
						orgaMsg = "Folgende Nachricht wurde Ihnen von der Organisation dazu hinterlegt:<br /><span style='font-style:italic;'>" +
								ra.getOrgaMessage_() + "</span><br /><br />";
					}
					QuestionDialog dialog = new QuestionDialog("Zu Ihrer Veranstaltung <strong>" +
							ra.getCourse_().getCourseAcronym_() + " / " + ra.getCourse_().getCourseKind_() + " im " + ra.getSemester_() +
							"</strong> konnte der Termin nicht gewährt werden. Die Verwaltung schlägt ihnen stattdessen folgenden Termin vor:<br /><br /><strong>" +
							vh.getDay(ra.getDay_()) + "<br />" +
							vh.getTime(ra.getTime_()) + "<br />" +
							ra.getRoom_().getRoomNumber_() + "<br /></strong><br />" +
							orgaMsg +
							"Wollen Sie diesen Termin annehmen?<br /> " +
							"Wenn Sie sich noch nicht sicher sind, so verneinen Sie dies bitte. " +
							"Sie können dann einfach selbst wieder eine Raumanfrage stellen. Allerdings nur, wenn Ihnen bis dorthin niemand zuvor " +
							"kommt. Nehmen Sie den Termin jetzt an, so ist er sofort freigeben!", 
							"Gegenvorschlag!");
					this.roomAllocation = ra;
					dialog.register(this);
					dialog.setVisible(true);
				}
			}
		}
		
		
		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout("", "[140px:n:140px][][grow][grow][grow][grow][100px:100px:100px]", "[][385.00,grow]"));
		
		JLabel lblLehrveranstaltungen = new JLabel("Lehrveranstaltungen:");
		lblLehrveranstaltungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		JPanel uniIconPanel = new JPanel();
		add(lblLehrveranstaltungen, "cell 1 0,aligny bottom");
		add(uniIconPanel, "cell 6 0,alignx center");
		
		add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 1,grow");
		
		tablePanel = new JPanel();
		add(tablePanel, "cell 1 1 5 1,grow");
		tablePanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][150px:n:200px,grow][][][][100px:n:200px,grow][]"));
		
		lblLecturer = new JLabel("Dozent:");
		tablePanel.add(lblLecturer, "cell 0 0");
		
		comboLecturer = new JComboBox<String>();
		comboLecturer.addActionListener(new StartTabCmbboxFilter());
		tablePanel.add(comboLecturer, "cell 0 1,growx");
		comboLecturer.setModel(new CmbboxFilterLecturer(comboLecturer));
		comboLecturer.setEditable(true);
		comboLecturer.setAutoscrolls(true);
		
		lvOrganisationTableScrollPane = new JScrollPane();
		tablePanel.add(lvOrganisationTableScrollPane, "cell 0 2 5 1,grow");
		
		tableCourseTop = new JTable();
		tableCourseTop.addMouseListener(new StartTabTableTop());
		tableCourseTop.setModel(ViewManager.getInstance().getLecturerStartTabTableTop());
		tableCourseTop.getColumnModel().removeColumn(tableCourseTop.getColumn("Hidden"));
		tableCourseTop.setShowVerticalLines(false);
		tableCourseTop.setBackground(SystemColor.activeCaption);
		lvOrganisationTableScrollPane.setViewportView(tableCourseTop);
		
		// Enable table sorting for the top table model
		rowSorterTop = new TableRowSorter<TableModel>();
		tableCourseTop.setRowSorter(rowSorterTop);
		rowSorterTop.setModel(ViewManager.getInstance().getLecturerStartTabTableTop());
		rowSorterTop.sort();
		
		JLabel lblRaumzuordnungen = new JLabel("Raumzuordnungen:");
		lblRaumzuordnungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		tablePanel.add(lblRaumzuordnungen, "cell 0 3");
		
		JLabel lblLecturerBottom = new JLabel("Dozent:");
		tablePanel.add(lblLecturerBottom, "cell 0 4");
		
		JLabel lblCourse = new JLabel("Lehrveranstaltung:");
		tablePanel.add(lblCourse, "cell 1 4");
		
		JLabel labelSemester = new JLabel("Semester:");
		tablePanel.add(labelSemester, "cell 2 4");
		
		comboBoxLecturerBottom = new JComboBox<String>();
		comboBoxLecturerBottom.setModel(new CmbboxFilterLecturer(comboBoxLecturerBottom));
		comboBoxLecturerBottom.addActionListener(new StartTabCmbboxFilter());
		
		lblStatus = new JLabel("Ver\u00F6ffentlichungsstatus:");
		tablePanel.add(lblStatus, "cell 3 4");
		comboBoxLecturerBottom.setEditable(true);
		comboBoxLecturerBottom.setAutoscrolls(true);
		tablePanel.add(comboBoxLecturerBottom, "cell 0 5,growx");
		
		comboBoxCourse = new JComboBox<String>();
		comboBoxCourse.addActionListener(new StartTabCmbboxFilter());
		comboBoxCourse.setModel(new CmbboxFilterCourse(comboBoxCourse));
		comboBoxCourse.setEditable(true);
		comboBoxCourse.setAutoscrolls(true);
		tablePanel.add(comboBoxCourse, "cell 1 5,growx");
		
		comboBoxSemesterBottom = new JComboBox<String>();
		comboBoxSemesterBottom.addActionListener(new StartTabCmbboxFilter());
		comboBoxSemesterBottom.setModel(new CmbboxFilterSemester());
		comboBoxSemesterBottom.setEditable(true);
		comboBoxSemesterBottom.setAutoscrolls(true);
		tablePanel.add(comboBoxSemesterBottom, "cell 2 5,growx");
		
		comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.addActionListener(new StartTabCmbboxFilter());
		comboBoxStatus.setModel(new CmbboxFilterAllocationStatus());
		tablePanel.add(comboBoxStatus, "cell 3 5");
		comboBoxStatus.setEditable(true);
		comboBoxStatus.setAutoscrolls(true);
		
		JScrollPane raumanfragenScrollPane = new JScrollPane();
		tablePanel.add(raumanfragenScrollPane, "cell 0 6 5 1,grow");
		
		tableRoomAllocationBottom = new JTable();
		tableRoomAllocationBottom.setModel(ViewManager.getInstance().getLecturerStartTabTableBottom());
		tableRoomAllocationBottom.setShowVerticalLines(false);
		tableRoomAllocationBottom.getColumnModel().removeColumn(tableRoomAllocationBottom.getColumn("Hidden"));
		tableRoomAllocationBottom.setBackground(SystemColor.activeCaption);
		raumanfragenScrollPane.setViewportView(tableRoomAllocationBottom);
		
		// Enable table sorting for the bottom table model
		rowSorterBottom = new TableRowSorter<TableModel>();
		tableRoomAllocationBottom.setRowSorter(rowSorterBottom);
		rowSorterBottom.setModel(ViewManager.getInstance().getLecturerStartTabTableBottom());
		rowSorterBottom.sort();
				
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 1,grow");
		
		btnAdd = new JButton("hinzuf\u00FCgen");
		btnAdd.addActionListener(new StartTabBtnsControl("add"));
		btnAdd.setBounds(0, 54, 88, 23);
		buttonPanel.add(btnAdd);
		
		btnEdit = new JButton("bearbeiten");
		btnEdit.addActionListener(new StartTabBtnsControl("edit"));
		btnEdit.setBounds(0, 79, 88, 23);
		buttonPanel.add(btnEdit);
		
		btnDelete = new JButton("löschen");
		btnDelete.addActionListener(new StartTabBtnsControl("delete"));
		btnDelete.setBounds(0, 106, 88, 23);
		buttonPanel.add(btnDelete);
		
		JButton btnPublish = new JButton("freigeben");
		btnPublish.addActionListener(new StartTabBtnsControl("publish"));
		btnPublish.setBounds(0, 194, 88, 23);
		buttonPanel.add(btnPublish);
		
		//Ist hier die Schreibweise gem�� der java code conventions?mit RoomRequest?
		btnRoomRequest = new JButton("Anfrage");
		btnRoomRequest.addActionListener(new StartTabBtnsControl("roomrequest"));
		btnRoomRequest.setBounds(0, 160, 88, 23);
		buttonPanel.add(btnRoomRequest);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panel.setBounds(0, 248, 100, 1);
		buttonPanel.add(panel);
		
		JButton btnZurckziehen = new JButton("zurückziehen");
		btnZurckziehen.setToolTipText("<html> abgelehnte oder nicht mehr benötigte <br> Raumanfragen zurückziehen </html>");
		btnZurckziehen.addActionListener(new StartTabBtnsControl("back"));
		btnZurckziehen.setBounds(0, 260, 100, 23);
		buttonPanel.add(btnZurckziehen);
		
		JLabel lbluniIcon = new JLabel("");
		uniIconPanel.add(lbluniIcon);
		lbluniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
		lbluniIcon.setMaximumSize(new Dimension(50,50));
	}

	/**
	 * @return the comboLecturer
	 */
	public JComboBox<String> getComboLecturer() {
		return comboLecturer;
	}

	/**
	 * @return the comboBoxStatus
	 */
	public JComboBox<String> getComboBoxStatus() {
		return comboBoxStatus;
	}

	/**
	 * @return the comboBoxLecturerBottom
	 */
	public JComboBox<String> getComboBoxLecturerBottom() {
		return comboBoxLecturerBottom;
	}

	/**
	 * @return the comboBoxCourse
	 */
	public JComboBox<String> getComboBoxCourse() {
		return comboBoxCourse;
	}

	/**
	 * @return the comboBoxSemesterBottom
	 */
	public JComboBox<String> getComboBoxSemesterBottom() {
		return comboBoxSemesterBottom;
	}

	/**
	 * @return the tableCourseTop
	 */
	public JTable getTableCourseTop() {
		return tableCourseTop;
	}

	/**
	 * @return the tableRoomAllocationBottom
	 */
	public JTable getTableRoomAllocationBottom() {
		return tableRoomAllocationBottom;
	}

	/**
	 * @return the rowSorterTop
	 */
	public TableRowSorter<TableModel> getRowSorterTop() {
		return rowSorterTop;
	}

	/**
	 * @return the rowSorterBottom
	 */
	public TableRowSorter<TableModel> getRowSorterBottom() {
		return rowSorterBottom;
	}

	@Override
	public void answered(String answer) {
		if (answer.equals("yes")) {
			if (this.roomAllocation != null) {
				this.ctrlStartTab.counterRoomAllocation(roomAllocation, true);
				this.roomAllocation = null;
			}
		} else if (answer.equals("no")) {
			this.ctrlStartTab.counterRoomAllocation(roomAllocation, false);
			this.roomAllocation = null;
		}
	}
}
