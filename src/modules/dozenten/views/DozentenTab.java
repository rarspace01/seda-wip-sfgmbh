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

public class DozentenTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable raumverwaltungTable;
	private JLabel lblLehrveranstaltung;
	private JLabel lblLehrstuhl;
	private JLabel lblDozent;
	private JLabel lblSemester;
	private JComboBox<String> comboBoxLehrveranstaltung;
	private JComboBox<String> comboLehrstuhl;
	private JComboBox<String> comboDozent;
	private JComboBox<String> comboBoxSemester;
	private JPanel sidePanel;
	private JPanel topSidePanel;
	private JPanel bottomSidePanel;
	private JScrollPane verwaltungTableScrollPane;
	private JPanel buttonPanel;
	private JButton btnHinzufugen;
	private JButton btnBearbeiten;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DozentenTab() {
		setMaximumSize(new Dimension(10, 32767));
		setLayout(new MigLayout("", "[grow][][grow][grow][grow][grow][grow]", "[][][grow]"));
		
		lblLehrveranstaltung = new JLabel("Lehrveranstaltungen:");
		add(lblLehrveranstaltung, "cell 2 0");
		
		lblLehrstuhl = new JLabel("Lehrstuhl:");
		add(lblLehrstuhl, "cell 3 0");
		
		lblDozent = new JLabel("Dozent:");
		add(lblDozent, "cell 4 0");
		
		lblSemester = new JLabel("Semester:");
		add(lblSemester, "cell 5 0");
		
		comboBoxLehrveranstaltung = new JComboBox<String>();
		comboBoxLehrveranstaltung.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxLehrveranstaltung.setEditable(true);
		comboBoxLehrveranstaltung.setAutoscrolls(true);
		add(comboBoxLehrveranstaltung, "cell 2 1,growx");
		
		comboLehrstuhl = new JComboBox<String>();
		comboLehrstuhl.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboLehrstuhl.setEditable(true);
		comboLehrstuhl.setAutoscrolls(true);
		add(comboLehrstuhl, "cell 3 1,growx");
		
		comboDozent = new JComboBox<String>();
		comboDozent.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboDozent.setEditable(true);
		comboDozent.setAutoscrolls(true);
		add(comboDozent, "cell 4 1,growx");
		
		comboBoxSemester = new JComboBox<String>();
		comboBoxSemester.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxSemester.setEditable(true);
		comboBoxSemester.setAutoscrolls(true);
		add(comboBoxSemester, "cell 5 1,growx");
		
		sidePanel = new JPanel();
		sidePanel.setMinimumSize(new Dimension(140, 10));
		sidePanel.setLayout(null);
		sidePanel.setMaximumSize(new Dimension(140, 32767));
		sidePanel.setBorder(null);
		add(sidePanel, "cell 0 2,grow");
		
		topSidePanel = new JPanel();
		topSidePanel.setLayout(null);
		topSidePanel.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		topSidePanel.setBounds(0, 6, 140, 313);
		sidePanel.add(topSidePanel);
		
		bottomSidePanel = new JPanel();
		bottomSidePanel.setBounds(0, 319, 140, 161);
		sidePanel.add(bottomSidePanel);
		bottomSidePanel.setLayout(new MigLayout("", "[]", "[][]"));
		
		JButton btnStundenplan = new JButton("Stundenplan");
		bottomSidePanel.add(btnStundenplan, "cell 0 0");
		
		JButton btnLehrstuhlplan = new JButton("Lehrstuhlplan");
		btnLehrstuhlplan.addActionListener(new LehrstuhlplanButton());
		bottomSidePanel.add(btnLehrstuhlplan, "cell 0 1");
		
		verwaltungTableScrollPane = new JScrollPane();
		add(verwaltungTableScrollPane, "flowx,cell 2 2 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setColumnHeaderView(raumverwaltungTable);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 2,grow");
		
		btnHinzufugen = new JButton("hinzuf\u00FCgen");
		btnHinzufugen.addActionListener(new HinzufugenButton());
		btnHinzufugen.setBounds(0, 30, 88, 23);
		buttonPanel.add(btnHinzufugen);
		
		btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.setBounds(0, 54, 88, 23);
		buttonPanel.add(btnBearbeiten);
	}
}
