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
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.applayer.core.controller.ServiceManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterAllocationStatus;
import de.sfgmbh.comlayer.core.model.CmbboxFilterCourse;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.StartTabBtnsControl;
import de.sfgmbh.comlayer.lecturer.controller.StartTabCmbboxFilter;
import de.sfgmbh.comlayer.lecturer.controller.StartTabTableTop;

public class StartTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableCourseTop;
	private JLabel lblLecturer;
	private JComboBox<String> comboLecturer;
	private JPanel sidePanel;
	private JPanel topSidePanel;
	private JScrollPane lvOrganisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnRoomRequest;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox<String> comboBoxStatus;
	private JLabel lblStatus;
//	private JButton btnLivetickerEdit;
	private JPanel tablePanel;
	private JTable roomrequestTable;
	private JTextPane txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf;
	private JButton btnFailureprompt;
	private JComboBox<String> comboBoxLecturerBottom;
	private JComboBox<String> comboBoxCourse;
	private JComboBox<String> comboBoxSemesterBottom;

	/**
	 * Create the panel.
	 */
	public StartTab() {
		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout("", "[140px:n:140px][][grow][grow][grow][grow][100px:100px:100px]", "[][385.00,grow]"));
		
		JLabel lblLehrveranstaltungen = new JLabel("Lehrveranstaltungen:");
		lblLehrveranstaltungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		JPanel uniIconPanel = new JPanel();
		add(lblLehrveranstaltungen, "cell 1 0,aligny bottom");
		add(uniIconPanel, "cell 6 0,alignx center");
		
		
		sidePanel = new JPanel();
		sidePanel.setMinimumSize(new Dimension(140, 10));
		sidePanel.setLayout(null);
		sidePanel.setMaximumSize(new Dimension(140, 32767));
		sidePanel.setBorder(null);
		add(sidePanel, "cell 0 1,grow");
		
		topSidePanel = new JPanel();
		topSidePanel.setLayout(null);
		topSidePanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		topSidePanel.setBounds(0, 0, 140, 424);
		sidePanel.add(topSidePanel);
		
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf = new JTextPane();
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setBackground(UIManager.getColor("Button.background"));
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setContentType("text/plain\r\n");
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. \r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.");
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setBounds(10, 11, 120, 402);
		topSidePanel.add(txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf);
		
		tablePanel = new JPanel();
		add(tablePanel, "cell 1 1 5 1,grow");
		tablePanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][150px:n:200px,grow][][][][100px:n:200px,grow][]"));
		
		lblLecturer = new JLabel("Dozent:");
		tablePanel.add(lblLecturer, "cell 0 0");
		
		comboLecturer = new JComboBox<String>();
		comboLecturer.addActionListener(new StartTabCmbboxFilter());
		tablePanel.add(comboLecturer, "cell 0 1,growx");
		comboLecturer.setModel(new CmbboxFilterLecturer());
		comboLecturer.setEditable(true);
		comboLecturer.setAutoscrolls(true);
		
		lvOrganisationTableScrollPane = new JScrollPane();
		tablePanel.add(lvOrganisationTableScrollPane, "cell 0 2 5 1,grow");
		
		tableCourseTop = new JTable();
		tableCourseTop.addMouseListener(new StartTabTableTop());
		tableCourseTop.setModel(ServiceManager.getInstance().getLecturerStartTabTableTop());
		tableCourseTop.getColumnModel().removeColumn(tableCourseTop.getColumn("Hidden"));
		tableCourseTop.setShowVerticalLines(false);
		tableCourseTop.setBackground(SystemColor.activeCaption);
		lvOrganisationTableScrollPane.setViewportView(tableCourseTop);
		
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
		comboBoxLecturerBottom.setModel(new CmbboxFilterLecturer());
		comboBoxLecturerBottom.addActionListener(new StartTabCmbboxFilter());
		
		lblStatus = new JLabel("Ver\u00F6ffentlichungsstatus:");
		tablePanel.add(lblStatus, "cell 3 4");
		comboBoxLecturerBottom.setEditable(true);
		comboBoxLecturerBottom.setAutoscrolls(true);
		tablePanel.add(comboBoxLecturerBottom, "cell 0 5,growx");
		
		comboBoxCourse = new JComboBox<String>();
		comboBoxCourse.addActionListener(new StartTabCmbboxFilter());
		comboBoxCourse.setModel(new CmbboxFilterCourse());
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
		
		roomrequestTable = new JTable();
		roomrequestTable.setModel(ServiceManager.getInstance().getLecturerStartTabTableBottom());
		roomrequestTable.setShowVerticalLines(false);
		roomrequestTable.getColumnModel().removeColumn(roomrequestTable.getColumn("Hidden"));
		roomrequestTable.setBackground(SystemColor.activeCaption);
		raumanfragenScrollPane.setViewportView(roomrequestTable);
		
		btnFailureprompt = new JButton("Ausloggen");
		btnFailureprompt.addActionListener(new StartTabBtnsControl("Fehlermeldung"));
		tablePanel.add(btnFailureprompt, "cell 0 7");
				
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
		btnZurckziehen.addActionListener(new StartTabBtnsControl("back"));
		btnZurckziehen.setBounds(0, 260, 100, 23);
		buttonPanel.add(btnZurckziehen);
		
		JLabel lbluniIcon = new JLabel("");
		uniIconPanel.add(lbluniIcon);
		lbluniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
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
}
