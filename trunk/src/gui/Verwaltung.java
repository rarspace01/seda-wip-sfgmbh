package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import modules.coregui.controller.AddToStundenplanButton;
import modules.coregui.controller.DozLoginButton;
import modules.coregui.controller.StudLoginButton;
import modules.coregui.controller.VeranstaltungsFilter;
import modules.coregui.controller.VerwLoginButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import gui.CoreGUI;
import javax.swing.JTextField;
import java.awt.Color;

public class Verwaltung extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldPlatze;
	private JTextField textFieldPCPlatze;
	private JTable raumverwaltungTable;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Verwaltung() {
		setLayout(new MigLayout("", "[grow][][grow][grow][grow][grow][grow]", "[][][][grow]"));
		
		JRadioButton rdbtnRaumverwaltung = new JRadioButton("Raumverwaltung");
		rdbtnRaumverwaltung.setMargin(new Insets(0, 0, 0, 0));
		add(rdbtnRaumverwaltung, "cell 0 0");
		
		Panel spacerPanel = new Panel();
		spacerPanel.setMinimumSize(new Dimension(20, 10));
		add(spacerPanel, "cell 1 0");
		
		JLabel lblGebude = new JLabel("Geb\u00E4ude:");
		add(lblGebude, "cell 2 0");
		
		JLabel lblStockwerk = new JLabel("Stockwerk:");
		add(lblStockwerk, "cell 3 0");
		
		JLabel lblPltze = new JLabel("Pl\u00E4tze:");
		add(lblPltze, "cell 4 0");
		
		JLabel lblPcpltze = new JLabel("PC-Pl\u00E4tze:");
		add(lblPcpltze, "cell 5 0");
		
		JRadioButton rdbtnLehrstuhlverwaltung = new JRadioButton("Lehrstuhlverwaltung");
		rdbtnLehrstuhlverwaltung.setMargin(new Insets(0, 0, 0, 0));
		add(rdbtnLehrstuhlverwaltung, "cell 0 1");
		
		JComboBox<String> comboBoxGebaude = new JComboBox<String>();
		comboBoxGebaude.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxGebaude.setEditable(true);
		comboBoxGebaude.setAutoscrolls(true);
		add(comboBoxGebaude, "cell 2 1,growx");
		
		JComboBox<String> comboBoxStockwerk = new JComboBox<String>();
		comboBoxStockwerk.setModel(new DefaultComboBoxModel(new String[] {"<alle>"}));
		comboBoxStockwerk.setEditable(true);
		comboBoxStockwerk.setAutoscrolls(true);
		add(comboBoxStockwerk, "cell 3 1,growx");
		
		textFieldPlatze = new JTextField();
		textFieldPlatze.setText("<alle>");
		add(textFieldPlatze, "cell 4 1,growx");
		textFieldPlatze.setColumns(10);
		
		textFieldPCPlatze = new JTextField();
		textFieldPCPlatze.setText("<alle>");
		textFieldPCPlatze.setColumns(10);
		add(textFieldPCPlatze, "cell 5 1,growx");
		
		JRadioButton rdbtnNutzerverwaltung = new JRadioButton("Nutzerverwaltung");
		rdbtnNutzerverwaltung.setMargin(new Insets(0, 0, 0, 0));
		add(rdbtnNutzerverwaltung, "cell 0 2");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setMaximumSize(new Dimension(140, 32767));
		panel.setBorder(null);
		add(panel, "cell 0 3,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.TRAILING, TitledBorder.ABOVE_TOP, null, null));
		panel_1.setBounds(0, 6, 140, 313);
		panel.add(panel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("<div style=\"font-family:arial\"><strong>Meldung:</strong><br> In 5 Minuten beginnt SEDA-DMS-B in Raum WP3/04.003</div>");
		textPane.setContentType("text/html");
		textPane.setBackground(UIManager.getColor("ArrowButton.background"));
		textPane.setBounds(6, 108, 123, 90);
		panel_1.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("<div style=\"font-family:arial\"><strong>News:</strong><br> <span style=\"color:red\">Alle</span> Vorlesungen n\u00E4chste Woche entfallen!</div>");
		textPane_1.setContentType("text/html");
		textPane_1.setBackground((Color) null);
		textPane_1.setBounds(6, 6, 123, 90);
		panel_1.add(textPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 319, 140, 161);
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[]", "[]"));
		
		JScrollPane verwaltungTableScrollPane = new JScrollPane();
		add(verwaltungTableScrollPane, "flowx,cell 2 3 4 1,grow");
		
		raumverwaltungTable = new JTable();
		raumverwaltungTable.setShowVerticalLines(false);
		raumverwaltungTable.setBackground(SystemColor.activeCaption);
		verwaltungTableScrollPane.setColumnHeaderView(raumverwaltungTable);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setMinimumSize(new Dimension(80, 10));
		buttonPanel.setMaximumSize(new Dimension(100, 32767));
		add(buttonPanel, "cell 6 3,grow");
		
		JButton btnBearbeiten = new JButton("bearbeiten");
		btnBearbeiten.setBounds(6, 30, 88, 23);
		buttonPanel.add(btnBearbeiten);
		
	}
}