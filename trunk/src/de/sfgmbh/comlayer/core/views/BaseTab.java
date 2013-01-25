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

	private JLayeredPane contentPane_;
	private JTabbedPane mainTabbedContainerPane_;
	private JPanel startScreenPanel_;
	private JComboBox<String> comboBoxChairFilter_;
	private CmbboxFilterChairAcronym comboBoxChairModel_;
	private JComboBox<String> comboBoxLecturerFilter_;
	private CmbboxFilterLecturer comboBoxLecturerModel_;
	private JComboBox<String> comboBoxSemesterFilter_;
	private CmbboxFilterSemester comboBoxSemesterModel_;
	private JComboBox<String> comboBoxOrgaFilter_;
	private JComboBox<String> comboBoxRoomnumberFilter;
	private CmbboxFilterRoomnumber comboBoxRoomnumberModel_;
	private JComboBox<String> comboBoxSeatsFilter;
	private CmbboxFilterSeats comboBoxSeatsModel_;
	private JComboBox<String> comboBoxLevelFilter;
	private CmbboxFilterLevel comboBoxLevelModel_;
	private JLabel lblCourse_;
	private JLabel lblChair_;
	private JLabel lblLecturer_;
	private JLabel lblLoggedIn_;
	private JLabel lblSemester_;
	private JLabel lblRoomnumber_;
	private JLabel lblSeats_;
	private JLabel lblLevel_;
	private JTable organisationTable_;
	private JScrollPane mainTableScrollPane_;
	private JPanel buttonPanel_;
	private JPanel panelLogin_;
	private JPanel panelLogout_;
	private final ButtonGroup buttonGroup_ = new ButtonGroup();
	private JLabel lblPasswort_;
	private JLabel lblUsername_;
	private JButton btnLogin_;
	private JTextField txtUsername_;
	private JPasswordField pwdPassword_;
	private JRadioButton rdbtnCourse_;
	private JRadioButton rdbtnRoom_;
	private JButton btnLogout_;
	private JLabel lblUniIcon_;
	private JTable roomTable_;
	private JPanel mainFilterPanel_;
	private TableRowSorter<TableModel> rowSorterAllocation_;
	private TableRowSorter<TableModel> rowSorterRoom_;
	private JButton btnRoomplan_;
	private JButton btnTimetable_;

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
		contentPane_ = new JLayeredPane();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		contentPane_.setLayout(new CardLayout(0, 0));

		mainTabbedContainerPane_ = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedContainerPane_.setVisible(false);
		contentPane_.add(mainTabbedContainerPane_, "name_5247024799318");

		startScreenPanel_ = new JPanel();
		startScreenPanel_.setMaximumSize(new Dimension(10, 32767));
		contentPane_.setLayer(startScreenPanel_, 1);
		contentPane_.add(startScreenPanel_, "name_5256771068822");
		startScreenPanel_.setLayout(new MigLayout("", "[140px:140px:140px][][600px:600px,grow][110px:110px:110px]", "[68px][][grow][10px:10px:10px,grow,bottom]"));
		startScreenPanel_.add(ViewManager.getInstance().getCoreLiveTickerPanel(), "cell 0 2, grow, aligny top");

		mainFilterPanel_ = new JPanel();
		startScreenPanel_.add(mainFilterPanel_, "cell 2 1,grow");
		mainFilterPanel_.setLayout(new MigLayout("", "[grow][100px:100px:100px,grow][grow][grow]", "[][]"));

		mainFilterPanel_
				.add(getLblLehrveranstaltung(), "cell 0 0,aligny bottom");
		mainFilterPanel_.add(getLblLehrstuhl(), "cell 1 0,aligny bottom");
		mainFilterPanel_.add(getLblDozent(), "cell 2 0,aligny bottom");
		mainFilterPanel_.add(getLblSemester(), "cell 3 0,aligny bottom");

		mainFilterPanel_.add(getComboBoxOrgaFilter(), "cell 0 1,growx");
		mainFilterPanel_.add(getComboBoxChairFilter(), "cell 1 1,alignx center");
		mainFilterPanel_.add(getComboBoxLecturerFilter(), "cell 2 1,growx");
		mainFilterPanel_.add(getComboBoxSemesterFilter(), "cell 3 1,growx");
		startScreenPanel_.add(getRdbtnCourse(),
				"cell 0 0,aligny bottom");
		
				startScreenPanel_.add(getLblUniicon(), "cell 3 0 1 2,alignx right,aligny top");
		startScreenPanel_.add(getRdbtnRooms(), "cell 0 1,aligny top");
		
		mainTableScrollPane_ = new JScrollPane();
		startScreenPanel_.add(mainTableScrollPane_, "cell 2 2,growx,alignx left,aligny top");

		mainTableScrollPane_.setViewportView(getOrganisationTable());
		mainTableScrollPane_.setPreferredSize(new Dimension(600, 2000));
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(120, 10));
		buttonPanel.setMaximumSize(new Dimension(120, 32767));
		startScreenPanel_.add(buttonPanel, "cell 3 2,grow");
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
		return startScreenPanel_;
	}
	/**
	 * 
	 * @return the lblLehrveranstaltung
	 */
	public JLabel getLblLehrveranstaltung() {
		lblCourse_ = new JLabel("Lehrveranstaltung:");
		return lblCourse_;
	}
	/**
	 * 
	 * @return the lblLehrstuhl
	 */
	public JLabel getLblLehrstuhl() {
		lblChair_ = new JLabel("Lehrstuhl:");
		return lblChair_;
	}
	/**
	 * 
	 * @return the lblDozent
	 */
	public JLabel getLblDozent() {
		lblLecturer_ = new JLabel("Dozent:");
		return lblLecturer_;
	}
	/**
	 * 
	 * @return the lblSemester
	 */
	public JLabel getLblSemester() {
		lblSemester_ = new JLabel("Semester:");
		return lblSemester_;
	}
	/**
	 * 
	 * @return the lblRoomnumber
	 */
	public JLabel getLblRoomnumber() {
		lblRoomnumber_ = new JLabel("Raum:");
		return lblRoomnumber_;
	}
	/**
	 * 
	 * @return the lblSeats
	 */
	public JLabel getLblRoomplaces() {
		lblSeats_ = new JLabel("Mehr Plätze als:");
		return lblSeats_;
	}
	/**
	 * 
	 * @return the lblLevel
	 */
	public JLabel getLblLevel() {
		lblLevel_ = new JLabel("Stockwerk:");
		return lblLevel_;
	}
	/**
	 * 
	 * @return the mainTableScollPane
	 */
	public JScrollPane getMainTableScrollPane() {
		return mainTableScrollPane_;
	}
	/**
	 * 
	 * @return the mainFilterPanel
	 */
	public JPanel getMainFilterPanel() {
		return mainFilterPanel_;
	}
	/**
	 * 
	 * @return the comboBoxOrgaFilter
	 */
	public JComboBox<String> getComboBoxOrgaFilter() {
		if (comboBoxOrgaFilter_ == null) {
			comboBoxOrgaFilter_ = new JComboBox<String>();
			comboBoxOrgaFilter_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxOrgaFilter_.setPreferredSize(new Dimension(110, 20));
			comboBoxOrgaFilter_.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxOrgaFilter_.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxOrgaFilter_.setAutoscrolls(true);
			comboBoxOrgaFilter_.setEditable(true);
			comboBoxOrgaFilter_.setModel(new CmbboxFilterCourse(comboBoxOrgaFilter_));
		}

		return comboBoxOrgaFilter_;
	}
	/**
	 * 
	 * @return the comboBocChairFilter
	 */
	public JComboBox<String> getComboBoxChairFilter() {
		if (comboBoxChairFilter_ == null) {
			comboBoxChairFilter_ = new JComboBox<String>();
			comboBoxChairFilter_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxChairFilter_.setPreferredSize(new Dimension(100, 20));
			comboBoxChairFilter_.setMaximumSize(new Dimension(100,20));
			comboBoxChairFilter_.setMinimumSize(new Dimension(100,20));
			comboBoxChairModel_=new CmbboxFilterChairAcronym(comboBoxChairFilter_);
			comboBoxChairFilter_.setModel(comboBoxChairModel_);
			comboBoxChairFilter_.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxChairFilter_.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxChairFilter_.setEditable(true);
			comboBoxChairFilter_.setAutoscrolls(true);
		}

		return comboBoxChairFilter_;
	}
	/**
	 * 
	 * @return the comboBoxLecturerFilter
	 */
	public JComboBox<String> getComboBoxLecturerFilter() {
		if (comboBoxLecturerFilter_ == null) {
			comboBoxLecturerFilter_ = new JComboBox<String>();
			comboBoxLecturerFilter_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxLecturerFilter_.setPreferredSize(new Dimension(110, 20));
			comboBoxLecturerModel_= new CmbboxFilterLecturer(comboBoxLecturerFilter_);
			comboBoxLecturerFilter_.setModel(comboBoxLecturerModel_);
			comboBoxLecturerFilter_.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxLecturerFilter_.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxLecturerFilter_.setEditable(true);
			comboBoxLecturerFilter_.setAutoscrolls(true);
		}

		return comboBoxLecturerFilter_;
	}
	/**
	 * 
	 * @return the comboBoxSemesterFilter
	 */
	public JComboBox<String> getComboBoxSemesterFilter() {
		if (comboBoxSemesterFilter_ == null) {
			comboBoxSemesterFilter_ = new JComboBox<String>();
			comboBoxSemesterFilter_.setToolTipText("<html>Sie können die Liste der <br> Lehrveranstaltungen über <br> diese Filter begrenzen</html>");
			comboBoxSemesterFilter_.setPreferredSize(new Dimension(110, 20));
			comboBoxSemesterModel_ = new CmbboxFilterSemester();
			comboBoxSemesterFilter_.setModel(comboBoxSemesterModel_);
			comboBoxSemesterFilter_.addKeyListener(new BaseCmbboxFilter("allocations"));
			comboBoxSemesterFilter_.addActionListener(new BaseCmbboxFilter("allocations"));
			comboBoxSemesterFilter_.setEditable(true);
			comboBoxSemesterFilter_.setAutoscrolls(true);
		}

		return comboBoxSemesterFilter_;
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
		if (organisationTable_ == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);

			organisationTable_ = new JTable();
			organisationTable_.setBackground(SystemColor.activeCaption);
			organisationTable_.setShowVerticalLines(false);
			organisationTable_.setModel(ViewManager.getInstance()
					.getCoreBaseTableModel());
			
			organisationTable_.getColumnModel().getColumn(0).setResizable(false);
			organisationTable_.getColumnModel().getColumn(8)
					.setCellRenderer(center);
			organisationTable_.getColumnModel().getColumn(6)
					.setCellRenderer(center);
			organisationTable_.getColumnModel().removeColumn(
					organisationTable_.getColumn("Hidden"));

			// Enable table sorting for the model
			rowSorterAllocation_ = new TableRowSorter<TableModel>();
			organisationTable_.setRowSorter(rowSorterAllocation_);
			rowSorterAllocation_.setModel(ViewManager.getInstance()
					.getCoreBaseTableModel());
			rowSorterAllocation_.sort();
		}
		return organisationTable_;
	}
	/**
	 * 
	 * @return the roomTable
	 */
	public JTable getRoomTable() {
		if (roomTable_ == null) {
			DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			center.setHorizontalAlignment(SwingConstants.CENTER);

			roomTable_ = new JTable();
			roomTable_.setShowVerticalLines(false);
			roomTable_.setModel(ViewManager.getInstance()
					.getCoreRoomTableModel());
			
			roomTable_.setBackground(SystemColor.activeCaption);
			mainTableScrollPane_.setViewportView(roomTable_);
			roomTable_.getColumnModel().getColumn(0).setResizable(false);
			roomTable_.getColumnModel().getColumn(8).setCellRenderer(center);
			roomTable_.getColumnModel().getColumn(1).setCellRenderer(center);
			roomTable_.getColumnModel().getColumn(6).setCellRenderer(center);
			roomTable_.getColumnModel().removeColumn(
					roomTable_.getColumn("Hidden"));
			roomTable_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			// Enable table sorting for the model
			rowSorterRoom_ = new TableRowSorter<TableModel>();
			roomTable_.setRowSorter(rowSorterRoom_);
			rowSorterRoom_.setModel(ViewManager.getInstance()
					.getCoreRoomTableModel());
			rowSorterRoom_.sort();

		}
		return roomTable_;
	}
	/**
	 * 
	 * @param organisationTable - set the organisationTable
	 */
	public void setOrganisationTable(JTable organisationTable) {
		this.organisationTable_ = organisationTable;
	}
	/**
	 * 
	 * @return the lblUsername
	 */
	public JLabel getLblUsername() {
		if (lblUsername_ == null) {
			lblUsername_ = new JLabel("Benutzername:");
		}
		return lblUsername_;
	}
	/**
	 * 
	 * @return the btnLogin
	 */
	public JButton getBtnLogin() {
		if (btnLogin_ == null) {
			btnLogin_ = new JButton("Einloggen");
			btnLogin_.setToolTipText("");
			btnLogin_.addActionListener(new BaseLogin());
		}
		return btnLogin_;
	}
	/**
	 * 
	 * @return the btnLogout
	 */
	public JButton getBtnLogout() {
		if (btnLogout_ == null) {
			btnLogout_ = new JButton("Logout");
			btnLogout_.setToolTipText("<html>Hier können Sie sich <br> wieder ausloggen.</html>");
			btnLogout_.setBackground(SystemColor.activeCaption);
			btnLogout_.addActionListener(new BaseLogin("logout"));
		}
		return btnLogout_;
	}
	/**
	 * 
	 * @return the txtUsername
	 */
	public JTextField getTxtUsername() {
		if (txtUsername_ == null) {
			txtUsername_ = new JTextField();
			txtUsername_.setColumns(10);
		}
		return txtUsername_;
	}
	/**
	 * 
	 * @return the pwdPassword
	 */
	public JPasswordField getPwdPasswort() {
		if (pwdPassword_ == null) {
			pwdPassword_ = new JPasswordField();
			pwdPassword_.addActionListener(new BaseLogin());
		}
		return pwdPassword_;
	}
	/**
	 * 
	 * @return the rdbtnCourse
	 */
	public JRadioButton getRdbtnCourse() {
		if (rdbtnCourse_ == null) {
			rdbtnCourse_ = new JRadioButton("Lehrveranstaltungen");
			rdbtnCourse_.setToolTipText("<html>Über diese Einstellung können Sie <br> Lehrveranstaltungen suchen<br>sowie diese sammen und <br> diese in einem Stundenplan ausgeben</html>");
			rdbtnCourse_.addActionListener(new BaseRdbtnTopLeft(
					"course"));
			buttonGroup_.add(rdbtnCourse_);
			rdbtnCourse_.setSelected(true);
			rdbtnCourse_.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnCourse_;
	}
	/**
	 * 
	 * @return the rdbtnRooms
	 */
	public JRadioButton getRdbtnRooms() {
		if (rdbtnRoom_ == null) {
			rdbtnRoom_ = new JRadioButton("R\u00E4ume");
			rdbtnRoom_.setToolTipText("<html>Hier können Sie alle belegten Räume anzeigen lassen<br>sowie einen Wocheplan für die <br>Belegungen dieses Raumes<br> anzeigen lassen</html>");
			rdbtnRoom_.addActionListener(new BaseRdbtnTopLeft("room"));
			buttonGroup_.add(rdbtnRoom_);
			rdbtnRoom_.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRoom_;
	}

	private JLabel getLblUniicon() {
		if (lblUniIcon_ == null) {
			lblUniIcon_ = new JLabel("");
			lblUniIcon_
					.setIcon(new ImageIcon(
							BaseTab.class
									.getResource("/de/sfgmbh/comlayer/core/images/UniBA_logo.png")));
			lblUniIcon_.setMaximumSize(new Dimension(50, 50));

		}
		return lblUniIcon_;
	}
	/**
	 * 
	 * @return the panelLogin
	 */
	public JPanel getPanelLogin() {
		if (panelLogin_ == null) {
			panelLogin_ = new JPanel();
			panelLogin_.setBorder(new LineBorder(new Color(153, 180, 209), 2));
			panelLogin_.setBackground(SystemColor.activeCaptionBorder);
			panelLogin_.setToolTipText("<html>Dozenten und Verwaltungsmitarbeiter können sich hier <br>zu ihrem persönlichen Funktionsbereich einloggen</html>");
			panelLogin_.setBounds(0, 367, 115, 121);
			panelLogin_.setAlignmentX(BOTTOM_ALIGNMENT);
			panelLogin_.setLayout(new MigLayout("", "[grow,center]", "[][][][][]"));
			panelLogin_.add(getLblUsername(), "cell 0 0,alignx left");
			panelLogin_.add(getTxtUsername(), "cell 0 1,growx");
			lblPasswort_ = new JLabel("Passwort:");
			panelLogin_.add(lblPasswort_, "cell 0 2,alignx left");
			panelLogin_.add(getPwdPasswort(), "cell 0 3,growx");
			panelLogin_.add(getBtnLogin(), "cell 0 4,alignx right");
		}
		return panelLogin_;
	}
	/**
	 * 
	 * @return the panelLogout
	 */
	public JPanel getPanelLogout() {
		if (panelLogout_ == null) {
			panelLogout_ = new JPanel();
			panelLogout_.setBorder(new LineBorder(new Color(153, 180, 209), 2));
			panelLogout_.setBackground(SystemColor.activeCaptionBorder);
			panelLogout_.setBounds(0, 432, 115, 55);
			panelLogout_.setLayout(new MigLayout("", "[140px:140px:140px,center]", "[][]"));
			lblLoggedIn_ = new JLabel("Sie sind eingeloggt!");
			panelLogout_.add(lblLoggedIn_, "cell 0 0,alignx left");
			panelLogout_.add(getBtnLogout(), "cell 0 1,alignx left");
			panelLogout_.setVisible(false);
		}
		return panelLogout_;
	}

	/**
	 * @return the mainTabbedContainerPane
	 */
	public JTabbedPane getMainTabbedContainerPane() {
		return mainTabbedContainerPane_;
	}

	/**
	 * @return the contentPane
	 */
	public JLayeredPane getContentPane() {
		return contentPane_;
	}
	/**
	 * 
	 * @return the buttonPanel
	 */
	public JPanel getButtonPanel() {
		return buttonPanel_;
	}
	/**
	 * @return the rowSorterAllocation
	 */
	public TableRowSorter<TableModel> getRowSorterAllocation() {
		return rowSorterAllocation_;
	}

	/**
	 * @return the rowSorterRoom
	 */
	public TableRowSorter<TableModel> getRowSorterRoom() {
		return rowSorterRoom_;
	}
	public JButton getBtnRoomplan() {
		if (btnRoomplan_ == null) {
			btnRoomplan_ = new JButton("Raumplan");
			btnRoomplan_.setBackground(SystemColor.inactiveCaption);
			btnRoomplan_.setToolTipText("<html>Selektieren Sie einen Raum <br> um dafür einen Raumplan zu generieren </html>");
			btnRoomplan_.setVisible(false);
			btnRoomplan_.setEnabled(true);
			btnRoomplan_.setBounds(0, 40, 104, 23);
			btnRoomplan_.addActionListener(new BaseBtns(
					"roomtable"));
		}
		return btnRoomplan_;
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
		if (btnTimetable_ == null) {
			btnTimetable_ = new JButton("Stundenplan");
			btnTimetable_.setToolTipText("<html>Selektieren Sie mit der Taste \"Strg\" und der linken Maustaste<br> eine oder mehrere Veranstaltungen und klicken Sie<br> auf \"Stundenplan\" um eine Wochenübersicht zu generieren</html>");
			btnTimetable_.setEnabled(true);
			btnTimetable_.setBounds(0, 40, 104, 23);
			btnTimetable_.addActionListener(new BaseBtns(
					"timetable"));
		}
		return btnTimetable_;
	}
}
