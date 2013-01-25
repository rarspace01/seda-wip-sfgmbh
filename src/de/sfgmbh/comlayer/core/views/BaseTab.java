package de.sfgmbh.comlayer.core.views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.BaseBtns;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.BaseLogin;
import de.sfgmbh.comlayer.core.controller.BaseRdbtnTopLeft;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChairAcronym;
import de.sfgmbh.comlayer.core.model.CmbboxFilterCourse;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLecturer;
import de.sfgmbh.comlayer.core.model.CmbboxFilterLevel;
import de.sfgmbh.comlayer.core.model.CmbboxFilterRoomnumber;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSeats;
import de.sfgmbh.comlayer.core.model.CmbboxFilterSemester;

/**
 * The very first content users see when they start the application
 * 
 * @author mario
 * @author anna
 * @author hannes
 * 
 */
public class BaseTab extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLayeredPane contentPane;
	private JTabbedPane mainTabbedContainerPane;
	private JPanel startScreenPanel;
	private JComboBox<String> comboBoxChairFilter;
	private CmbboxFilterChairAcronym comboBoxChairModel_;
	private JComboBox<String> comboBoxLecturerFilter;
	private CmbboxFilterLecturer comboBoxLecturerModel_;
	private JComboBox<String> comboBoxSemesterFilter;
	private CmbboxFilterSemester comboBoxSemesterModel_;
	private JComboBox<String> comboBoxOrgaFilter;
	private JComboBox<String> comboBoxRoomnumberFilter;
	private CmbboxFilterRoomnumber comboBoxRoomnumberModel_;
	private JComboBox<String> comboBoxSeatsFilter;
	private CmbboxFilterSeats comboBoxSeatsModel_;
	private JComboBox<String> comboBoxLevelFilter;
	private CmbboxFilterLevel comboBoxLevelModel_;
	private JLabel lblCourse;
	private JLabel lblChair;
	private JLabel lblLecturer;
	private JLabel lblLoggedIn;
	private JLabel lblSemester;
	private JLabel lblRoomnumber;
	private JLabel lblSeats;
	private JLabel lblLevel;
	private JTable organisationTable;
	private JScrollPane mainTableScrollPane;
	private JPanel buttonPanel;
	private JPanel panelLogin;
	private JPanel panelLogout;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPasswort;
	private JLabel lblUsername;
	private JButton btnLogin;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JRadioButton rdbtnCourse;
	private JRadioButton rdbtnRoom;
	private JButton btnLogout;
	private JLabel lblUniIcon;
	private JTable roomTable;
	private JPanel mainFilterPanel;
	private TableRowSorter<TableModel> rowSorterAllocation;
	private TableRowSorter<TableModel> rowSorterRoom;
	private JButton btnRoomplan;
	private JButton btnTimetable;

	/**
	 * Create the frame. v.175
	 */
	public BaseTab() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						BaseTab.class
								.getResource("/de/sfgmbh/comlayer/core/images/HUT_klein.png")));
		setTitle("UnivIS 2.0 - Stundenpläne, Wochenpläne, Verwaltung von Lehrveranstaltungen, Raumverwaltung,...");
		createContents();
	}

	private void createContents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 675);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		mainTabbedContainerPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedContainerPane.setVisible(false);
		contentPane.add(mainTabbedContainerPane, "name_5247024799318");

		startScreenPanel = new JPanel();
		startScreenPanel.setMaximumSize(new Dimension(10, 32767));
		contentPane.setLayer(startScreenPanel, 1);
		contentPane.add(startScreenPanel, "name_5256771068822");
		startScreenPanel.setLayout(new MigLayout("", "[140px:140px:140px][][600px:600px,grow][110px:110px:110px]", "[68px][][grow][10px:10px:10px,grow,bottom]"));
		startScreenPanel.add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2, grow, aligny top");

		mainFilterPanel = new JPanel();
		startScreenPanel.add(mainFilterPanel, "cell 2 1,grow");
		mainFilterPanel.setLayout(new MigLayout("", "[grow][100px:100px:100px,grow][grow][grow]", "[][]"));

		mainFilterPanel
				.add(getLblLehrveranstaltung(), "cell 0 0,aligny bottom");
		mainFilterPanel.add(getLblLehrstuhl(), "cell 1 0,aligny bottom");
		mainFilterPanel.add(getLblDozent(), "cell 2 0,aligny bottom");
		mainFilterPanel.add(getLblSemester(), "cell 3 0,aligny bottom");

		mainFilterPanel.add(getComboBoxOrgaFilter(), "cell 0 1,growx");
		mainFilterPanel.add(getComboBoxChairFilter(), "cell 1 1,alignx center");
		mainFilterPanel.add(getComboBoxLecturerFilter(), "cell 2 1,growx");
		mainFilterPanel.add(getComboBoxSemesterFilter(), "cell 3 1,growx");
		startScreenPanel.add(getRdbtnCourse(),
				"cell 0 0,aligny bottom");
		
				startScreenPanel.add(getLblUniicon(), "cell 3 0 1 2,alignx right,aligny top");
		startScreenPanel.add(getRdbtnRooms(), "cell 0 1,aligny top");
		
		mainTableScrollPane = new JScrollPane();
		startScreenPanel.add(mainTableScrollPane, "cell 2 2,growx,alignx left,aligny top");

		mainTableScrollPane.setViewportView(getOrganisationTable());
		mainTableScrollPane.setPreferredSize(new Dimension(600, 2000));
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(120, 10));
		buttonPanel.setMaximumSize(new Dimension(120, 32767));
		startScreenPanel.add(buttonPanel, "cell 3 2,grow");
		buttonPanel.setLayout(null);
		buttonPanel.add(getBtnRoomplan());
		buttonPanel.add(getBtnTimetable());
		buttonPanel.add(getPanelLogout());
		buttonPanel.add(getPanelLogin());


	}
	/**
	 * 
	 * @return the startScreenPanel
	 */
	public JPanel getStartScreenPanel() {
		return startScreenPanel;
	}
	/**
	 * 
	 * @return the lblLehrveranstaltung
	 */
	public JLabel getLblLehrveranstaltung() {
		lblCourse = new JLabel("Lehrveranstaltung:");
		return lblCourse;
	}
	/**
	 * 
	 * @return the lblLehrstuhl
	 */
	public JLabel getLblLehrstuhl() {
		lblChair = new JLabel("Lehrstuhl:");
		return lblChair;
	}
	/**
	 * 
	 * @return the lblDozent
	 */
	public JLabel getLblDozent() {
		lblLecturer = new JLabel("Dozent:");
		return lblLecturer;
	}
	/**
	 * 
	 * @return the lblSemester
	 */
	public JLabel getLblSemester() {
		lblSemester = new JLabel("Semester:");
		return lblSemester;
	}
	/**
	 * 
	 * @return the lblRoomnumber
	 */
	public JLabel getLblRoomnumber() {
		lblRoomnumber = new JLabel("Raum:");
		return lblRoomnumber;
	}
	/**
	 * 
	 * @return the lblSeats
	 */
	public JLabel getLblRoomplaces() {
		lblSeats = new JLabel("Mehr Plätze als:");
		return lblSeats;
	}
	/**
	 * 
	 * @return the lblLevel
	 */
	public JLabel getLblLevel() {
		lblLevel = new JLabel("Stockwerk:");
		return lblLevel;
	}
	/**
	 * 
	 * @return the mainTableScollPane
	 */
	public JScrollPane getMainTableScrollPane() {
		return mainTableScrollPane;
	}
	/**
	 * 
	 * @return the mainFilterPanel
	 */
	public JPanel getMainFilterPanel() {
		return mainFilterPanel;
	}
	/**
	 * 
	 * @return the comboBoxOrgaFilter
	 */
	public JComboBox<String> getComboBoxOrgaFilter() {
		if (comboBoxOrgaFilter == null) {
			comboBoxOrgaFilter = new JComboBox<String>();
			comboBoxOrgaFilter.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxOrgaFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxOrgaFilter.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxOrgaFilter.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxOrgaFilter.setAutoscrolls(true);
			comboBoxOrgaFilter.setEditable(true);
			comboBoxOrgaFilter.setModel(new CmbboxFilterCourse(comboBoxOrgaFilter));
		}

		return comboBoxOrgaFilter;
	}
	/**
	 * 
	 * @return the comboBocChairFilter
	 */
	public JComboBox<String> getComboBoxChairFilter() {
		if (comboBoxChairFilter == null) {
			comboBoxChairFilter = new JComboBox<String>();
			comboBoxChairFilter.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxChairFilter.setPreferredSize(new Dimension(100, 20));
			comboBoxChairFilter.setMaximumSize(new Dimension(100,20));
			comboBoxChairFilter.setMinimumSize(new Dimension(100,20));
			comboBoxChairModel_=new CmbboxFilterChairAcronym(comboBoxChairFilter);
			comboBoxChairFilter.setModel(comboBoxChairModel_);
			comboBoxChairFilter.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxChairFilter.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxChairFilter.setEditable(true);
			comboBoxChairFilter.setAutoscrolls(true);
		}

		return comboBoxChairFilter;
	}
	/**
	 * 
	 * @return the comboBoxLecturerFilter
	 */
	public JComboBox<String> getComboBoxLecturerFilter() {
		if (comboBoxLecturerFilter == null) {
			comboBoxLecturerFilter = new JComboBox<String>();
			comboBoxLecturerFilter.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxLecturerFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxLecturerModel_= new CmbboxFilterLecturer(comboBoxLecturerFilter);
			comboBoxLecturerFilter.setModel(comboBoxLecturerModel_);
			comboBoxLecturerFilter.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxLecturerFilter.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxLecturerFilter.setEditable(true);
			comboBoxLecturerFilter.setAutoscrolls(true);
		}

		return comboBoxLecturerFilter;
	}
	/**
	 * 
	 * @return the comboBoxSemesterFilter
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		if (comboBoxSemesterFilter == null) {
			comboBoxSemesterFilter = new JComboBox<String>();
			comboBoxSemesterFilter.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxSemesterFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxSemesterModel_ = new CmbboxFilterSemester();
			comboBoxSemesterFilter.setModel(comboBoxSemesterModel_);
			comboBoxSemesterFilter.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxSemesterFilter.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxSemesterFilter.setEditable(true);
			comboBoxSemesterFilter.setAutoscrolls(true);
		}

		return comboBoxSemesterFilter;
	}
	
	/**
	 * @return the comboBoxSemesterModel_
	 */
	public CmbboxFilterSemester getComboBoxSemesterModel() {
		return comboBoxSemesterModel_;
	}

	public JComboBox<String> getComboBoxRoomnumberFilter() {
		if (comboBoxRoomnumberFilter == null) {
			comboBoxRoomnumberFilter = new JComboBox<String>();
			comboBoxRoomnumberModel_= new CmbboxFilterRoomnumber(comboBoxRoomnumberFilter);
			comboBoxRoomnumberFilter.setModel(comboBoxRoomnumberModel_);
			comboBoxRoomnumberFilter.addKeyListener(new BaseCmbboxFilter("rooms"));
			comboBoxRoomnumberFilter.addActionListener(new BaseCmbboxFilter("rooms"));
			comboBoxRoomnumberFilter.setEditable(true);
			comboBoxRoomnumberFilter.setAutoscrolls(true);
		}

		return comboBoxRoomnumberFilter;

	}
	/**
	 * 
	 * @return the comboBoxSeatsFilter
	 */
	public JComboBox<String> getComboBoxSeatsFilter() {
		if (comboBoxSeatsFilter == null) {
			comboBoxSeatsFilter = new JComboBox<String>();
			comboBoxSeatsModel_ = new CmbboxFilterSeats();
			comboBoxSeatsFilter.setModel(comboBoxSeatsModel_);
			comboBoxSeatsFilter.addKeyListener(new BaseCmbboxFilter("rooms"));
			comboBoxSeatsFilter.addActionListener(new BaseCmbboxFilter("rooms"));
			comboBoxSeatsFilter.setEditable(true);
			comboBoxSeatsFilter.setAutoscrolls(true);
		}

		return comboBoxSeatsFilter;
	}
	/**
	 * 
	 * @return the comboBoxLevelFilter
	 */
	public JComboBox<String> getComboBoxLevelFilter() {
		if (comboBoxLevelFilter == null) {
			comboBoxLevelFilter = new JComboBox<String>();
			comboBoxLevelModel_ = new CmbboxFilterLevel(comboBoxLevelFilter);
			comboBoxLevelFilter.setModel(comboBoxLevelModel_);
			comboBoxLevelFilter.addKeyListener(new BaseCmbboxFilter("rooms"));
			comboBoxLevelFilter.addActionListener(new BaseCmbboxFilter("rooms"));
			comboBoxLevelFilter.setEditable(true);
			comboBoxLevelFilter.setAutoscrolls(true);
		}

		return comboBoxLevelFilter;
	}
	/**
	 * 
	 * @return the btnAddToTimetable
	 */
	public JTable getOrganisationTable() {
		if (organisationTable == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);

			organisationTable = new JTable();
			organisationTable.setBackground(SystemColor.activeCaption);
			organisationTable.setShowVerticalLines(false);
			organisationTable.setModel(ViewManager.getInstance()
					.getCoreBaseTableModel());
			
			organisationTable.getColumnModel().getColumn(0).setResizable(false);
			organisationTable.getColumnModel().getColumn(8)
					.setCellRenderer(center);
			organisationTable.getColumnModel().getColumn(6)
					.setCellRenderer(center);
			organisationTable.getColumnModel().removeColumn(
					organisationTable.getColumn("Hidden"));

			// Enable table sorting for the model
			rowSorterAllocation = new TableRowSorter<TableModel>();
			organisationTable.setRowSorter(rowSorterAllocation);
			rowSorterAllocation.setModel(ViewManager.getInstance()
					.getCoreBaseTableModel());
			rowSorterAllocation.sort();
		}
		return organisationTable;
	}
	/**
	 * 
	 * @return the roomTable
	 */
	public JTable getRoomTable() {
		if (roomTable == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);

			roomTable = new JTable();
			roomTable.setShowVerticalLines(false);
			roomTable.setModel(ViewManager.getInstance()
					.getCoreRoomTableModel());
			
			roomTable.setBackground(SystemColor.activeCaption);
			mainTableScrollPane.setViewportView(roomTable);
			roomTable.getColumnModel().getColumn(0).setResizable(false);
			roomTable.getColumnModel().getColumn(8).setCellRenderer(center);
			roomTable.getColumnModel().getColumn(1).setCellRenderer(center);
			roomTable.getColumnModel().getColumn(6).setCellRenderer(center);
			roomTable.getColumnModel().removeColumn(
					roomTable.getColumn("Hidden"));
			roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			// Enable table sorting for the model
			rowSorterRoom = new TableRowSorter<TableModel>();
			roomTable.setRowSorter(rowSorterRoom);
			rowSorterRoom.setModel(ViewManager.getInstance()
					.getCoreRoomTableModel());
			rowSorterRoom.sort();

		}
		return roomTable;
	}
	/**
	 * 
	 * @param organisationTable - set the organisationTable
	 */
	public void setOrganisationTable(JTable organisationTable) {
		this.organisationTable = organisationTable;
	}
	/**
	 * 
	 * @return the lblUsername
	 */
	public JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Benutzername:");
		}
		return lblUsername;
	}
	/**
	 * 
	 * @return the btnLogin
	 */
	public JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Einloggen");
			btnLogin.setToolTipText("<html>Lehrstuhl- und Verwaltungsmitarbeitern<br> können sich hier anmelden</html>");
			btnLogin.addActionListener(new BaseLogin());
		}
		return btnLogin;
	}
	/**
	 * 
	 * @return the btnLogout
	 */
	public JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setToolTipText("<html>Hier können Sie sich <br> wieder ausloggen.</html>");
			btnLogout.setBackground(SystemColor.activeCaption);
			btnLogout.addActionListener(new BaseLogin("logout"));
		}
		return btnLogout;
	}
	/**
	 * 
	 * @return the txtUsername
	 */
	public JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setColumns(10);
		}
		return txtUsername;
	}
	/**
	 * 
	 * @return the pwdPassword
	 */
	public JPasswordField getPwdPasswort() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.addActionListener(new BaseLogin());
		}
		return pwdPassword;
	}
	/**
	 * 
	 * @return the rdbtnCourse
	 */
	public JRadioButton getRdbtnCourse() {
		if (rdbtnCourse == null) {
			rdbtnCourse = new JRadioButton("Lehrveranstaltungen");
			rdbtnCourse.setToolTipText("<html>Über diese Einstellung können Sie <br> Lehrveranstaltungen suchen<br>sowie diese sammen und <br> diese in einem Stundenplan ausgeben</html>");
			rdbtnCourse.addActionListener(new BaseRdbtnTopLeft(
					"course"));
			buttonGroup.add(rdbtnCourse);
			rdbtnCourse.setSelected(true);
			rdbtnCourse.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnCourse;
	}
	/**
	 * 
	 * @return the rdbtnRooms
	 */
	public JRadioButton getRdbtnRooms() {
		if (rdbtnRoom == null) {
			rdbtnRoom = new JRadioButton("R\u00E4ume");
			rdbtnRoom.setToolTipText("<html>Hier können Sie alle belegten Räume anzeigen lassen<br>sowie einen Wocheplan für die <br>Belegungen dieses Raumes<br> anzeigen lassen</html>");
			rdbtnRoom.addActionListener(new BaseRdbtnTopLeft("room"));
			buttonGroup.add(rdbtnRoom);
			rdbtnRoom.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRoom;
	}

	private JLabel getLblUniicon() {
		if (lblUniIcon == null) {
			lblUniIcon = new JLabel("");
			lblUniIcon
					.setIcon(new ImageIcon(
							BaseTab.class
									.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
			lblUniIcon.setMaximumSize(new Dimension(50, 50));

		}
		return lblUniIcon;
	}
	/**
	 * 
	 * @return the panelLogin
	 */
	public JPanel getPanelLogin() {
		if (panelLogin == null) {
			panelLogin = new JPanel();
			panelLogin.setBorder(new LineBorder(new Color(153, 180, 209), 2));
			panelLogin.setBackground(SystemColor.activeCaptionBorder);
			panelLogin.setToolTipText("<html>Dozenten und Verwaltungsmitarbeiter können sich hier <br>zu ihrem persönlichen Funktionsbereich einloggen</html>");
			panelLogin.setBounds(0, 367, 115, 121);
			panelLogin.setAlignmentX(BOTTOM_ALIGNMENT);
			panelLogin.setLayout(new MigLayout("", "[grow,center]", "[][][][][]"));
			panelLogin.add(getLblUsername(), "cell 0 0,alignx left");
			panelLogin.add(getTxtUsername(), "cell 0 1,growx");
			lblPasswort = new JLabel("Passwort:");
			panelLogin.add(lblPasswort, "cell 0 2,alignx left");
			panelLogin.add(getPwdPasswort(), "cell 0 3,growx");
			panelLogin.add(getBtnLogin(), "cell 0 4,alignx right");
		}
		return panelLogin;
	}
	/**
	 * 
	 * @return the panelLogout
	 */
	public JPanel getPanelLogout() {
		if (panelLogout == null) {
			panelLogout = new JPanel();
			panelLogout.setBorder(new LineBorder(new Color(153, 180, 209), 2));
			panelLogout.setBackground(SystemColor.activeCaptionBorder);
			panelLogout.setBounds(0, 432, 115, 55);
			panelLogout.setLayout(new MigLayout("", "[140px:140px:140px,center]", "[][]"));
			lblLoggedIn = new JLabel("Sie sind eingeloggt!");
			panelLogout.add(lblLoggedIn, "cell 0 0,alignx left");
			panelLogout.add(getBtnLogout(), "cell 0 1,alignx left");
			panelLogout.setVisible(false);
		}
		return panelLogout;
	}

	/**
	 * @return the mainTabbedContainerPane
	 */
	public JTabbedPane getMainTabbedContainerPane() {
		return mainTabbedContainerPane;
	}

	/**
	 * @return the contentPane
	 */
	public JLayeredPane getContentPane() {
		return contentPane;
	}
	/**
	 * 
	 * @return the buttonPanel
	 */
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	/**
	 * @return the rowSorterAllocation
	 */
	public TableRowSorter<TableModel> getRowSorterAllocation() {
		return rowSorterAllocation;
	}

	/**
	 * @return the rowSorterRoom
	 */
	public TableRowSorter<TableModel> getRowSorterRoom() {
		return rowSorterRoom;
	}
	public JButton getBtnRoomplan() {
		if (btnRoomplan == null) {
			btnRoomplan = new JButton("Raumplan");
			btnRoomplan.setBackground(SystemColor.inactiveCaption);
			btnRoomplan.setToolTipText("<html>Selektieren Sie einen Raum <br> um dafür einen Raumplan zu generieren </html>");
			btnRoomplan.setVisible(false);
			btnRoomplan.setEnabled(true);
			btnRoomplan.setBounds(0, 40, 104, 23);
			btnRoomplan.addActionListener(new BaseBtns(
					"roomtable"));
		}
		return btnRoomplan;
	}
	
	/**
	 * Switch to next tab
	 */
	public void switchToNextTab(){
		this.getMainTabbedContainerPane()
		.setSelectedIndex(this.getMainTabbedContainerPane()
				.getTabCount() - 1);
	}
	
	/**
	 * @return the btnTimetable
	 */
	public JButton getBtnTimetable() {
		if (btnTimetable == null) {
			btnTimetable = new JButton("Stundenplan");
			btnTimetable.setToolTipText("<html>Selektieren Sie mit der Taste \"Strg\" und der linken Maustaste<br> eine oder mehrere Veranstaltungen und klicken Sie<br> auf \"Stundenplan\" um eine Wochenübersicht zu generieren</html>");
			btnTimetable.setEnabled(true);
			btnTimetable.setBounds(0, 40, 104, 23);
			btnTimetable.addActionListener(new BaseBtns(
					"timetable"));
		}
		return btnTimetable;
	}
}
