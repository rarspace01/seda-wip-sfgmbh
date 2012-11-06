package modules.dozenten.views;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modules.dozenten.controller.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.UIManager;

public class DozentenTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable lvVerwaltungTable;
	private JLabel lblLehrstuhl;
	private JLabel lblDozent;
	private JLabel lblSemester;
	private JComboBox<String> comboLehrstuhl;
	private JComboBox<String> comboDozent;
	private JComboBox<String> comboBoxSemester;
	private JPanel sidePanel;
	private JPanel topSidePanel;
	private JPanel bottomSidePanel;
	private JScrollPane lvVerwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnHinzufugen;
	private JButton btnRaumanfrage;
	private JButton btnBearbeiten;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JLabel lblStatus;
	private JButton btnLivetickerBearbeiten;
	private JPanel tablePanel;
	private JTable raumanfragenTable;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DozentenTab() {
		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout("", "[140px:n:140px,grow][][grow][grow][grow][grow][100px:n:100px,grow]", "[][grow]"));
		
		JLabel lblLehrveranstaltungen = new JLabel("Lehrveranstaltungen:");
		lblLehrveranstaltungen.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblLehrveranstaltungen, "cell 1 0");
		
		sidePanel = new JPanel();
		sidePanel.setMinimumSize(new Dimension(140, 10));
		sidePanel.setLayout(null);
		sidePanel.setMaximumSize(new Dimension(140, 32767));
		sidePanel.setBorder(null);
		add(sidePanel, "cell 0 1,grow");
		
		topSidePanel = new JPanel();
		topSidePanel.setLayout(null);
		topSidePanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		topSidePanel.setBounds(0, 6, 130, 313);
		sidePanel.add(topSidePanel);
		
		lblNewLabel = new JLabel("<html>Info:</html>\r\n");
		lblNewLabel.setBounds(10, 11, 120, 42);
		topSidePanel.add(lblNewLabel);
		
		bottomSidePanel = new JPanel();
		bottomSidePanel.setBounds(0, 319, 140, 161);
		sidePanel.add(bottomSidePanel);
		bottomSidePanel.setLayout(new MigLayout("", "[]", "[][][]"));
		
		JButton btnStundenplan = new JButton("Stundenplan");
		btnStundenplan.addActionListener(new StundenplanButton());
		bottomSidePanel.add(btnStundenplan, "cell 0 0");
		
		JButton btnLehrstuhlplan = new JButton("Lehrstuhlplan");
		btnLehrstuhlplan.addActionListener(new LehrstuhlplanButton());
		bottomSidePanel.add(btnLehrstuhlplan, "cell 0 1");
		
		btnLivetickerBearbeiten = new JButton("LiveTicker bearbeiten");
		bottomSidePanel.add(btnLivetickerBearbeiten, "cell 0 2");
		
		tablePanel = new JPanel();
		add(tablePanel, "cell 1 1 5 1,grow");
		tablePanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][150px:n:200px,grow][][][][100px:n:200px,grow]"));
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		tablePanel.add(lblLehrstuhl, "cell 0 0");
		
		lblDozent = new JLabel("Dozent:");
		tablePanel.add(lblDozent, "cell 1 0");
		
		lblSemester = new JLabel("Semester:");
		tablePanel.add(lblSemester, "cell 2 0");
		
		comboLehrstuhl = new JComboBox<String>();
		tablePanel.add(comboLehrstuhl, "cell 0 1");
		comboLehrstuhl.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboLehrstuhl.setEditable(true);
		comboLehrstuhl.setAutoscrolls(true);
		
		comboDozent = new JComboBox<String>();
		tablePanel.add(comboDozent, "cell 1 1");
		comboDozent.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboDozent.setEditable(true);
		comboDozent.setAutoscrolls(true);
		
		comboBoxSemester = new JComboBox<String>();
		tablePanel.add(comboBoxSemester, "cell 2 1");
		comboBoxSemester.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		
		lvVerwaltungTableScrollPane = new JScrollPane();
		tablePanel.add(lvVerwaltungTableScrollPane, "cell 0 2 5 1,grow");
		
		lvVerwaltungTable = new JTable();
		lvVerwaltungTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"SEDA-WIP-B", "Benker", "WS1213", "4", "30", "2", "nein", "nein"},
			},
			new String[] {
				"Bezeichnung", "Dozent", "Semester", "SWS", "Erw. Teilnehmer", "Termine", "Alle freigegeben", "\u00D6ffentlich"
			}
		));
		lvVerwaltungTable.getColumnModel().getColumn(1).setPreferredWidth(59);
		lvVerwaltungTable.getColumnModel().getColumn(3).setPreferredWidth(48);
		lvVerwaltungTable.getColumnModel().getColumn(4).setPreferredWidth(95);
		lvVerwaltungTable.getColumnModel().getColumn(5).setPreferredWidth(49);
		lvVerwaltungTable.getColumnModel().getColumn(6).setPreferredWidth(91);
		lvVerwaltungTable.getColumnModel().getColumn(7).setPreferredWidth(59);
		lvVerwaltungTable.setShowVerticalLines(false);
		lvVerwaltungTable.setBackground(SystemColor.activeCaption);
		lvVerwaltungTableScrollPane.setViewportView(lvVerwaltungTable);
		
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
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBox_1.setEditable(true);
		comboBox_1.setAutoscrolls(true);
		tablePanel.add(comboBox_1, "cell 0 5,growx");
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBox_2.setEditable(true);
		comboBox_2.setAutoscrolls(true);
		tablePanel.add(comboBox_2, "cell 1 5,growx");
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBox_3.setEditable(true);
		comboBox_3.setAutoscrolls(true);
		tablePanel.add(comboBox_3, "cell 2 5,growx");
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBox_4.setEditable(true);
		comboBox_4.setAutoscrolls(true);
		tablePanel.add(comboBox_4, "cell 3 5,growx");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		tablePanel.add(comboBox, "cell 4 5");
		comboBox.setEditable(true);
		comboBox.setAutoscrolls(true);
		
		JScrollPane raumanfragenScrollPane = new JScrollPane();
		tablePanel.add(raumanfragenScrollPane, "cell 0 6 5 1,grow");
		
		raumanfragenTable = new JTable();
		raumanfragenTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"SEDA-WIP-B", "Benker", "WS1213", "Do.", "12:00 - 14:00", "WP3/02.001", "abgelehnt"},
				{"SEDA-WIP-B", "Benker", "WS1213", "Mo.", "14:00 - 16:00", "WP3/04.002", "wartend"},
			},
			new String[] {
				"Bezeichnung", "Dozent", "Semester", "Tag", "Zeit", "Raum", "Status"
			}
		));
		raumanfragenTable.setShowVerticalLines(false);
		raumanfragenTable.setBackground(SystemColor.activeCaption);
		raumanfragenScrollPane.setViewportView(raumanfragenTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 1,grow");
		
		btnHinzufugen = new JButton("hinzuf\u00FCgen");
		btnHinzufugen.addActionListener(new HinzufugenButton());
		btnHinzufugen.setBounds(0, 76, 88, 23);
		buttonPanel.add(btnHinzufugen);
		
		btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.setBounds(0, 110, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
		JButton btnVerffentlichen = new JButton("ver\u00F6ffentlichen");
		btnVerffentlichen.setBounds(0, 194, 100, 23);
		buttonPanel.add(btnVerffentlichen);
		
		btnRaumanfrage = new JButton("Raumanfrage");
		btnRaumanfrage.addActionListener(new RaumanfrageButton());
		btnRaumanfrage.setBounds(0, 160, 100, 23);
		buttonPanel.add(btnRaumanfrage);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("FormattedTextField.border"));
		panel.setBounds(0, 248, 100, 1);
		buttonPanel.add(panel);
		
		JButton btnZurckziehen = new JButton("zur\u00FCckziehen");
		btnZurckziehen.setBounds(0, 339, 100, 23);
		buttonPanel.add(btnZurckziehen);
	}
}
