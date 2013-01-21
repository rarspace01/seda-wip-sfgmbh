package de.sfgmbh.comlayer.core.views;

import java.awt.CardLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.controller.BaseBtnAddToStundenplan;
import de.sfgmbh.comlayer.core.controller.BaseBtnFailureprompt;
import de.sfgmbh.comlayer.core.controller.BaseCmbboxFilter;
import de.sfgmbh.comlayer.core.controller.BaseLogin;
import de.sfgmbh.comlayer.core.controller.BaseRdbtnTopLeft;
import de.sfgmbh.comlayer.core.controller.ViewManager;
import de.sfgmbh.comlayer.core.model.CmbboxFilterChair;
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
	private JComboBox<String> comboBoxLecturerFilter;
	private JComboBox<String> comboBoxSemesterFilter;
	private JComboBox<String> comboBoxOrgaFilter;
	private JComboBox<String> comboBoxRoomnumberFilter;
	private JComboBox<String> comboBoxSeatsFilter;
	private JComboBox<String> comboBoxLevelFilter;
	private JLabel lblLehrveranstaltung;
	private JLabel lblLehrstuhl;
	private JLabel lblDozent;
	private JLabel lblLoggedIn;
	private JLabel lblSemester;
	private JLabel lblRoomnumber;
	private JLabel lblSeats;
	private JLabel lblLevel;
	private JTable organisationTable;
	private JScrollPane mainTableScrollPane;
	private JPanel tickerJPanel;
	private JPanel buttonPanel;
	private JPanel panelLogin;
	private JPanel panelLogout;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPasswort;
	private JLabel lblBenutzername;
	private JButton btnNewButton;
	private JButton btnAddToStundenplan;
	private JTextField txtBenutzername;
	private JPasswordField pwdPasswort;
	private JRadioButton rdbtnLehrveranstaltungen;
	private JRadioButton rdbtnRaume;
	private JButton btnFehlermeldung;
	private JButton btnLogout;
	private JLabel lblNewLabel;
	private JTable roomTable;
	private JPanel mainFilterPanel;

	/**
	 * Create the frame. v.175
	 */
	@SuppressWarnings({})
	public BaseTab() {
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						BaseTab.class
								.getResource("/de/sfgmbh/comlayer/core/views/HUT_klein.png")));
		setTitle("UnivIS 2.0");
		createContents();
	}

	private void createContents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 648);
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
		startScreenPanel.setLayout(new MigLayout("", "[grow][grow][grow]",
				"[][][grow][]"));

		mainFilterPanel = new JPanel();
		startScreenPanel.add(mainFilterPanel, "cell 1 0 1 2,grow");
		mainFilterPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow]",
				"[][]"));

		mainFilterPanel
				.add(getLblLehrveranstaltung(), "cell 0 0,aligny bottom");
		mainFilterPanel.add(getLblLehrstuhl(), "cell 1 0,aligny bottom");
		mainFilterPanel.add(getLblDozent(), "cell 2 0,aligny bottom");
		mainFilterPanel.add(getLblSemester(), "cell 3 0,aligny bottom");

		mainFilterPanel.add(getComboBoxOrgaFilter(), "cell 0 1,alignx center");
		mainFilterPanel.add(getComboBoxChairFilter(), "cell 1 1,alignx center");
		mainFilterPanel.add(getComboBoxLecturerFilter(), "cell 2 1,growx");
		mainFilterPanel.add(getComboBoxSemesterFilter(), "cell 3 1,growx");

		startScreenPanel.add(getLblNewLabel(), "cell 2 0,alignx center");
		startScreenPanel.add(getRdbtnLehrveranstaltungen(),
				"cell 0 0,aligny bottom");
		startScreenPanel.add(getRdbtnRaume(), "cell 0 1");

		tickerJPanel = new JPanel();
		tickerJPanel.setMinimumSize(new Dimension(140, 10));
		tickerJPanel.setBorder(null);
		tickerJPanel.setMaximumSize(new Dimension(140, 32767));
		startScreenPanel.add(tickerJPanel, "cell 0 2,grow");
		tickerJPanel.setLayout(null);

		
		tickerJPanel.add(ViewManager.getInstance().getCoreLiveTickerPanel());
		
		tickerJPanel.add(getPanelLogin());

		tickerJPanel.add(getPanelLogout());

		mainTableScrollPane = new JScrollPane();
		mainTableScrollPane.setMinimumSize(new Dimension(800, 600));
		mainTableScrollPane.setMaximumSize(new Dimension(800, 600));
		mainTableScrollPane.setSize(new Dimension(800, 600));
		startScreenPanel.add(mainTableScrollPane, "cell 1 2,grow");

		mainTableScrollPane.setViewportView(getOrganisationTable());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		startScreenPanel.add(buttonPanel, "cell 2 2,grow");
		buttonPanel.setLayout(null);

		buttonPanel.add(getBtnAddToStudenplan());

		btnFehlermeldung = new JButton("Fehlermeldung");
		btnFehlermeldung.addActionListener(new BaseBtnFailureprompt("error"));
		startScreenPanel.add(btnFehlermeldung, "cell 1 3");

	}

	public JPanel getStartScreenPanel() {
		return startScreenPanel;
	}

	public JLabel getLblLehrveranstaltung() {
		lblLehrveranstaltung = new JLabel("Lehrveranstaltung:");
		return lblLehrveranstaltung;
	}

	public JLabel getLblLehrstuhl() {
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		return lblLehrstuhl;
	}

	public JLabel getLblDozent() {
		lblDozent = new JLabel("Dozent:");
		return lblDozent;
	}

	public JLabel getLblSemester() {
		lblSemester = new JLabel("Semester:");
		return lblSemester;
	}

	public JLabel getLblRoomnumber() {
		lblRoomnumber = new JLabel("Raum:");
		return lblRoomnumber;
	}

	public JLabel getLblRoomplaces() {
		lblSeats = new JLabel("Sitzplätze");
		return lblSeats;
	}

	public JLabel getLblLevel() {
		lblLevel = new JLabel("Stockwerk:");
		return lblLevel;
	}

	public JScrollPane getMainTableScrollPane() {
		return mainTableScrollPane;
	}

	public JPanel getMainFilterPanel() {
		return mainFilterPanel;
	}

	public JComboBox<String> getComboBoxOrgaFilter() {
		if (comboBoxOrgaFilter == null) {
			comboBoxOrgaFilter = new JComboBox<String>();
			comboBoxOrgaFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxOrgaFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxOrgaFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxOrgaFilter.setAutoscrolls(true);
			comboBoxOrgaFilter.setEditable(true);
			comboBoxOrgaFilter.setModel(new CmbboxFilterCourse(comboBoxOrgaFilter));
		}

		return comboBoxOrgaFilter;
	}

	public JComboBox<String> getComboBoxChairFilter() {
		if (comboBoxChairFilter == null) {
			comboBoxChairFilter = new JComboBox<String>();
			comboBoxChairFilter.setPreferredSize(new Dimension(440, 20));
			comboBoxChairFilter.setModel(new CmbboxFilterChair(comboBoxChairFilter));
			comboBoxChairFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxChairFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxChairFilter.setEditable(true);
			comboBoxChairFilter.setAutoscrolls(true);
		}

		return comboBoxChairFilter;
	}

	public JComboBox<String> getComboBoxLecturerFilter() {
		if (comboBoxLecturerFilter == null) {
			comboBoxLecturerFilter = new JComboBox<String>();
			comboBoxLecturerFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxLecturerFilter.setModel(new CmbboxFilterLecturer(comboBoxLecturerFilter));
			comboBoxLecturerFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxLecturerFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxLecturerFilter.setEditable(true);
			comboBoxLecturerFilter.setAutoscrolls(true);
		}

		return comboBoxLecturerFilter;
	}

	public JComboBox<String> getComboBoxSemesterFilter() {
		if (comboBoxSemesterFilter == null) {
			comboBoxSemesterFilter = new JComboBox<String>();
			comboBoxSemesterFilter.setPreferredSize(new Dimension(110, 20));
			comboBoxSemesterFilter.setModel(new CmbboxFilterSemester());
			comboBoxSemesterFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxSemesterFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxSemesterFilter.setEditable(true);
			comboBoxSemesterFilter.setAutoscrolls(true);
		}

		return comboBoxSemesterFilter;
	}

	public JComboBox<String> getComboBoxRoomnumberFilter() {
		if (comboBoxRoomnumberFilter == null) {
			comboBoxRoomnumberFilter = new JComboBox<String>();
			comboBoxRoomnumberFilter.setModel(new CmbboxFilterRoomnumber(comboBoxRoomnumberFilter));
			comboBoxRoomnumberFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxRoomnumberFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxRoomnumberFilter.setEditable(true);
			comboBoxRoomnumberFilter.setAutoscrolls(true);
		}

		return comboBoxRoomnumberFilter;

	}

	public JComboBox<String> getComboBoxSeatsFilter() {
		if (comboBoxSeatsFilter == null) {
			comboBoxSeatsFilter = new JComboBox<String>();
			comboBoxSeatsFilter.setModel(new CmbboxFilterSeats());
			comboBoxSeatsFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxSeatsFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxSeatsFilter.setEditable(true);
			comboBoxSeatsFilter.setAutoscrolls(true);
		}

		return comboBoxSeatsFilter;
	}

	public JComboBox<String> getComboBoxLevelFilter() {
		if (comboBoxLevelFilter == null) {
			comboBoxLevelFilter = new JComboBox<String>();
			comboBoxLevelFilter.setModel(new CmbboxFilterLevel(comboBoxLevelFilter));
			comboBoxLevelFilter.addKeyListener(new BaseCmbboxFilter());
			comboBoxLevelFilter.addActionListener(new BaseCmbboxFilter());
			comboBoxLevelFilter.setEditable(true);
			comboBoxLevelFilter.setAutoscrolls(true);
		}

		return comboBoxLevelFilter;
	}

	public JButton getBtnAddToStudenplan() {
		btnAddToStundenplan = new JButton("sammeln");
		btnAddToStundenplan.addActionListener(new BaseBtnAddToStundenplan(
				"plus"));
		btnAddToStundenplan.setBounds(0, 29, 80, 23);

		return btnAddToStundenplan;
	}

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
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>();
			organisationTable.setRowSorter(rowSorter);
			rowSorter.setModel(ViewManager.getInstance()
					.getCoreBaseTableModel());
			rowSorter.sort();
		}
		return organisationTable;
	}

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

		}
		return roomTable;
	}

	public void setOrganisationTable(JTable organisationTable) {
		this.organisationTable = organisationTable;
	}

	public JLabel getLblBenutzername() {
		if (lblBenutzername == null) {
			lblBenutzername = new JLabel("Benutzername:");
		}
		return lblBenutzername;
	}

	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Einloggen");
			btnNewButton.setToolTipText("");
			btnNewButton.addActionListener(new BaseLogin());
		}
		return btnNewButton;
	}

	public JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new BaseLogin("logout"));
		}
		return btnLogout;
	}

	public JTextField getTxtBenutzername() {
		if (txtBenutzername == null) {
			txtBenutzername = new JTextField();
			txtBenutzername.setColumns(10);
		}
		return txtBenutzername;
	}

	public JPasswordField getPwdPasswort() {
		if (pwdPasswort == null) {
			pwdPasswort = new JPasswordField();
			pwdPasswort.addActionListener(new BaseLogin());
		}
		return pwdPasswort;
	}

	public JRadioButton getRdbtnLehrveranstaltungen() {
		if (rdbtnLehrveranstaltungen == null) {
			rdbtnLehrveranstaltungen = new JRadioButton("Lehrveranstaltungen");
			rdbtnLehrveranstaltungen.addActionListener(new BaseRdbtnTopLeft(
					"course"));
			buttonGroup.add(rdbtnLehrveranstaltungen);
			rdbtnLehrveranstaltungen.setSelected(true);
			rdbtnLehrveranstaltungen.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnLehrveranstaltungen;
	}

	public JRadioButton getRdbtnRaume() {
		if (rdbtnRaume == null) {
			rdbtnRaume = new JRadioButton("R\u00E4ume");
			rdbtnRaume.addActionListener(new BaseRdbtnTopLeft("room"));
			buttonGroup.add(rdbtnRaume);
			rdbtnRaume.setMargin(new Insets(0, 0, 0, 0));
		}
		return rdbtnRaume;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel
					.setIcon(new ImageIcon(
							BaseTab.class
									.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
			lblNewLabel.setMaximumSize(new Dimension(50, 50));

		}
		return lblNewLabel;
	}

	public JPanel getPanelLogin() {
		if (panelLogin == null) {
			panelLogin = new JPanel();
			panelLogin.setBounds(0, 319, 140, 150);
			panelLogin.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
			panelLogin.add(getLblBenutzername(), "cell 0 0");
			panelLogin.add(getTxtBenutzername(), "cell 0 1,growx");
			lblPasswort = new JLabel("Passwort:");
			panelLogin.add(lblPasswort, "cell 0 2");
			panelLogin.add(getPwdPasswort(), "cell 0 3,growx");
			panelLogin.add(getBtnNewButton(), "cell 0 4,alignx right");
		}
		return panelLogin;
	}

	public JPanel getPanelLogout() {
		if (panelLogout == null) {
			panelLogout = new JPanel();
			panelLogout.setBounds(0, 319, 140, 150);
			panelLogout.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
			lblLoggedIn = new JLabel("Du bist eingeloggt!");
			panelLogout.add(lblLoggedIn, "cell 0 0");
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

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

}
