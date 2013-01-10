
package de.sfgmbh.comlayer.organisation.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import de.sfgmbh.comlayer.core.views.BaseTab;
import de.sfgmbh.comlayer.organisation.controller.CmbboxFilter;
import de.sfgmbh.comlayer.organisation.controller.RequestTabBtnsControl;
import de.sfgmbh.init.Bootstrap;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class RequestTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable raumverwaltungTable;
	private JLabel lblLecturer;
	private JLabel lblProfessorship;
	private JLabel lblStatus;
	private JLabel lblPCSeats;
	private JComboBox<String> comboBoxLecturer;
	private JComboBox<String> comboBoxProfessorship;
	private JPanel leftPanel;
	private JPanel leftTopPanel;
	private JScrollPane organisationTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnFreigeben;
	private JComboBox<String> comboBoxStatus;
	private JComboBox<String> comboBoxSemester;
	private JTextPane tickerMsgPos1;
	private JButton btnFailureprompt;
	private JPanel uniIconPanel;
	private JLabel lblUniIcon;

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
		
		lblProfessorship = new JLabel("Lehrstuhl:");
		add(lblProfessorship, "cell 3 0,aligny bottom");
		
		lblStatus = new JLabel("Freigabestatus:");
		add(lblStatus, "cell 4 0,aligny bottom");
		
		lblPCSeats = new JLabel("Semester:");
		add(lblPCSeats, "cell 5 0,aligny bottom");
		
		comboBoxLecturer = new JComboBox<String>();
		comboBoxLecturer.addActionListener(new CmbboxFilter());
		
		uniIconPanel = new JPanel();
		add(uniIconPanel, "cell 6 0,alignx center,growy");	
		lblUniIcon = new JLabel();
		lblUniIcon.setIcon(new ImageIcon(BaseTab.class.getResource("/de/sfgmbh/comlayer/core/views/UniBA_logo.png")));
		lblUniIcon.setMaximumSize(new Dimension(50,50));
		uniIconPanel.add(lblUniIcon);
		
		comboBoxLecturer.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxLecturer.setEditable(true);
		comboBoxLecturer.setAutoscrolls(true);
		add(comboBoxLecturer, "cell 2 1,growx");
		
		comboBoxProfessorship = new JComboBox<String>();
		comboBoxProfessorship.addActionListener(new CmbboxFilter());
		comboBoxProfessorship.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxProfessorship.setEditable(true);
		comboBoxProfessorship.setAutoscrolls(true);
		add(comboBoxProfessorship, "cell 3 1,growx");
		
		comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.addActionListener(new CmbboxFilter());
		comboBoxStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxStatus.setEditable(true);
		comboBoxStatus.setAutoscrolls(true);
		add(comboBoxStatus, "cell 4 1,growx");
		
		comboBoxSemester = new JComboBox<String>();
		comboBoxSemester.addActionListener(new CmbboxFilter());
		comboBoxSemester.setModel(new DefaultComboBoxModel<String>(new String[] {"<alle>"}));
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		add(comboBoxSemester, "cell 5 1,growx");
		
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setMaximumSize(new Dimension(140, 32767));
		leftPanel.setBorder(null);
		add(leftPanel, "cell 0 2,grow");
		
		leftTopPanel = new JPanel();
		leftTopPanel.setLayout(null);
		leftTopPanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		leftTopPanel.setBounds(0, 6, 140, 385);
		leftPanel.add(leftTopPanel);
		
		tickerMsgPos1 = new JTextPane();
		tickerMsgPos1.setBackground(UIManager.getColor("Button.background"));
		tickerMsgPos1.setText("LiveTickerNews:\r\n\r\nFehler: Es wurden keine Lehrveranstaltungen gefunden werden, die in 10 Minuten beginnen.\r\n\r\nFehler: Es wurden keine Meldungen von Dozenten oder der Hausverwaltung gefunden. \r\n\r\nFehler: Es besteht keine Verbindung zur Datenbank.\r\n");
		tickerMsgPos1.setBounds(6, 11, 124, 368);
		leftTopPanel.add(tickerMsgPos1);
		
		organisationTableScrollPane = new JScrollPane();
		add(organisationTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setModel(Bootstrap.serviceManager.getOrgaRequestTableModel());
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		organisationTableScrollPane.setViewportView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(120, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnFreigeben = new JButton("freigeben");
		btnFreigeben.setBounds(6, 11, 88, 23);
		btnFreigeben.addActionListener(new RequestTabBtnsControl("publish"));
		buttonPanel.add(btnFreigeben);
		
		JButton btnAblehnen = new JButton("ablehnen");
		btnAblehnen.setBounds(6, 45, 88, 23);
		btnAblehnen.addActionListener(new RequestTabBtnsControl("ablehnen"));
		buttonPanel.add(btnAblehnen);
		
		JButton btnGegenvorschlag = new JButton("l\u00F6sen");
		btnGegenvorschlag.setMinimumSize(new Dimension(90, 23));
		btnGegenvorschlag.setMaximumSize(new Dimension(90, 23));
		btnGegenvorschlag.setPreferredSize(new Dimension(50, 23));
		btnGegenvorschlag.addActionListener(new RequestTabBtnsControl("Konflikt"));
		btnGegenvorschlag.setBounds(6, 79, 88, 23);
		buttonPanel.add(btnGegenvorschlag);
		
		btnFailureprompt = new JButton("Ausloggen");
		add(btnFailureprompt, "cell 2 3");
		btnFailureprompt.addActionListener(new RequestTabBtnsControl("Fehlermeldung"));
	}
	
}