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
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.lecturer.controller.StartTabBtnsControl;
import de.sfgmbh.comlayer.lecturer.controller.StartTabCmbboxFilter;
import de.sfgmbh.comlayer.lecturer.model.StartTabCmbboxModelFilter;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableBottom;
import de.sfgmbh.comlayer.lecturer.model.StartTabTableTop;

public class StartTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable lvOrganisationTable;
	private JLabel lblProfessorship;
	private JLabel lblLecturer;
	private JLabel lblSemester;
	private JComboBox<String> comboProfessorship;
	private JComboBox<String> comboLecturer;
	private JComboBox<String> comboBoxSemester;
	private JPanel sidePanel;
	private JPanel topSidePanel;
	private JScrollPane lvOrganisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnRoomRequest;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox<String> comboBox;
	private JLabel lblStatus;
//	private JButton btnLivetickerEdit;
	private JPanel tablePanel;
	private JTable roomrequestTable;
	private JTextPane txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf;
	private JButton btnFailureprompt;

	/**
	 * Create the panel.
	 */
	public StartTab() {
		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout("", "[140px:n:140px][][grow][grow][grow][grow][100px:n:100px,grow]", "[][grow]"));
		
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
		topSidePanel.setBounds(0, 0, 130, 390);
		sidePanel.add(topSidePanel);
		
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf = new JTextPane();
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setBackground(UIManager.getColor("Button.background"));
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setContentType("text/plain\r\n");
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen. \r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.");
		txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf.setBounds(10, 0, 110, 379);
		topSidePanel.add(txtpnBajksbfwebfskbjfsbksbksdbkgdbfkgbdkrgbekrbgf);
		
		tablePanel = new JPanel();
		add(tablePanel, "cell 1 1 5 1,grow");
		tablePanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][150px:n:200px,grow][][][][100px:n:200px,grow][]"));
		
		lblProfessorship = new JLabel("Lehrstuhl:");
		tablePanel.add(lblProfessorship, "cell 0 0");
		
		lblLecturer = new JLabel("Dozent:");
		tablePanel.add(lblLecturer, "cell 1 0");
		
		lblSemester = new JLabel("Semester:");
		tablePanel.add(lblSemester, "cell 2 0");
		
		comboProfessorship = new JComboBox<String>();
		comboProfessorship.addActionListener(new StartTabCmbboxFilter());
		tablePanel.add(comboProfessorship, "cell 0 1");
		comboProfessorship.setModel(new StartTabCmbboxModelFilter("professorshiptop"));
		comboProfessorship.setEditable(true);
		comboProfessorship.setAutoscrolls(true);
		
		comboLecturer = new JComboBox<String>();
		comboLecturer.addActionListener(new StartTabCmbboxFilter());
		tablePanel.add(comboLecturer, "cell 1 1");
		comboLecturer.setModel(new StartTabCmbboxModelFilter("lecturertop"));
		comboLecturer.setEditable(true);
		comboLecturer.setAutoscrolls(true);
		
		comboBoxSemester = new JComboBox<String>();
		tablePanel.add(comboBoxSemester, "cell 2 1");
		comboBoxSemester.addActionListener(new StartTabCmbboxFilter());
		comboBoxSemester.setModel(new StartTabCmbboxModelFilter("semester"));
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		
		lvOrganisationTableScrollPane = new JScrollPane();
		tablePanel.add(lvOrganisationTableScrollPane, "cell 0 2 5 1,grow");
		
		lvOrganisationTable = new JTable();
		lvOrganisationTable.setModel(new StartTabTableTop());
		lvOrganisationTable.getColumnModel().getColumn(1).setPreferredWidth(59);
		lvOrganisationTable.getColumnModel().getColumn(3).setPreferredWidth(48);
		lvOrganisationTable.getColumnModel().getColumn(4).setPreferredWidth(35);
		lvOrganisationTable.getColumnModel().getColumn(5).setPreferredWidth(49);
		lvOrganisationTable.getColumnModel().getColumn(6).setPreferredWidth(110);
		lvOrganisationTable.getColumnModel().getColumn(7).setPreferredWidth(59);
		lvOrganisationTable.setShowVerticalLines(false);
		lvOrganisationTable.setBackground(SystemColor.activeCaption);
		lvOrganisationTableScrollPane.setViewportView(lvOrganisationTable);
		
		JLabel lblRaumzuordnungen = new JLabel("Raumzuordnungen:");
		lblRaumzuordnungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		tablePanel.add(lblRaumzuordnungen, "cell 0 3");
		
		JLabel label_1 = new JLabel("Lehrstuhl:");
		tablePanel.add(label_1, "cell 0 4");
		
		JLabel label = new JLabel("Dozent:");
		tablePanel.add(label, "cell 1 4");
		
		JLabel lblLehrveranstaltung = new JLabel("Lehrveranstaltung:");
		tablePanel.add(lblLehrveranstaltung, "cell 2 4");
		
		JLabel label_2 = new JLabel("Semester:");
		tablePanel.add(label_2, "cell 3 4");
		
		lblStatus = new JLabel("Ver\u00F6ffentlichungsstatus:");
		tablePanel.add(lblStatus, "cell 4 4");
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new StartTabCmbboxFilter());
		comboBox_1.setModel(new StartTabCmbboxModelFilter("professorshipbottom"));
		comboBox_1.setEditable(true);
		comboBox_1.setAutoscrolls(true);
		tablePanel.add(comboBox_1, "cell 0 5,growx");
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new StartTabCmbboxModelFilter("lecturerbottom"));
		comboBox_2.addActionListener(new StartTabCmbboxFilter());
		comboBox_2.setEditable(true);
		comboBox_2.setAutoscrolls(true);
		tablePanel.add(comboBox_2, "cell 1 5,growx");
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.addActionListener(new StartTabCmbboxFilter());
		comboBox_3.setModel(new StartTabCmbboxModelFilter("course"));
		comboBox_3.setEditable(true);
		comboBox_3.setAutoscrolls(true);
		tablePanel.add(comboBox_3, "cell 2 5,growx");
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.addActionListener(new StartTabCmbboxFilter());
		comboBox_4.setModel(new StartTabCmbboxModelFilter("semesterbottom"));
		comboBox_4.setEditable(true);
		comboBox_4.setAutoscrolls(true);
		tablePanel.add(comboBox_4, "cell 3 5,growx");
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new StartTabCmbboxFilter());
		comboBox.setModel(new StartTabCmbboxModelFilter("status"));
		tablePanel.add(comboBox, "cell 4 5");
		comboBox.setEditable(true);
		comboBox.setAutoscrolls(true);
		
		JScrollPane raumanfragenScrollPane = new JScrollPane();
		tablePanel.add(raumanfragenScrollPane, "cell 0 6 5 1,grow");
		
		roomrequestTable = new JTable();
		roomrequestTable.setModel(new StartTabTableBottom());
		roomrequestTable.setShowVerticalLines(false);
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
		btnPublish.setBounds(0, 194, 100, 23);
		buttonPanel.add(btnPublish);
		
		//Ist hier die Schreibweise gemäß der java code conventions?mit RoomRequest?
		btnRoomRequest = new JButton("Raumanfrage");
		btnRoomRequest.addActionListener(new StartTabBtnsControl("roomrequest"));
		btnRoomRequest.setBounds(0, 160, 100, 23);
		buttonPanel.add(btnRoomRequest);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panel.setBounds(0, 248, 100, 1);
		buttonPanel.add(panel);
		
		JButton btnZurckziehen = new JButton("zur\u00FCckziehen");
		btnZurckziehen.addActionListener(new StartTabBtnsControl("back"));
		btnZurckziehen.setBounds(0, 260, 100, 23);
		buttonPanel.add(btnZurckziehen);
		
		JLabel lbluniIcon = new JLabel("");
		uniIconPanel.add(lbluniIcon);
		lbluniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lbluniIcon.setMaximumSize(new Dimension(50,50));
					

		
	}
}
