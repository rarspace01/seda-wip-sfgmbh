package modules.dozenten.views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modules.dozenten.controller.LVMaskeWindow;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DozentenLVMaskeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public DozentenLVMaskeWindow() {
		setTitle("Lehrveranstaltung hinzuf\u00FCgen und Raumanfrage senden");
		setBounds(100, 100, 475, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][][][][]", "[][][][][][][][][][][][][][][]"));
		
		JLabel lblLehrstuhl = new JLabel("Lehrstuhl:");
		contentPane.add(lblLehrstuhl, "cell 0 0 3 1");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SEDA"}));
		contentPane.add(comboBox, "cell 3 0,growx");
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2, "cell 5 0");
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1, "cell 6 0");
		
		JLabel label = new JLabel("");
		contentPane.add(label, "cell 7 0");
		
		JLabel lblNewLabel_2 = new JLabel("");
		contentPane.add(lblNewLabel_2, "cell 8 0");
		
		JLabel lblNewLabel = new JLabel("Dozent:");
		contentPane.add(lblNewLabel, "cell 1 1 2 1");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Benker", "Wolf", "Sinz"}));
		contentPane.add(comboBox_1, "cell 3 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Bezeichnung:");
		contentPane.add(lblNewLabel_1, "cell 1 2 2 1");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 3 2,growx");
		textField.setColumns(10);
		
		JLabel lblRaumanfrage = new JLabel("Raumanfrage");
		contentPane.add(lblRaumanfrage, "cell 1 4 2 1");
		
		JLabel lblStockwer = new JLabel("Stockwerk:");
		contentPane.add(lblStockwer, "cell 1 5");
		
		JComboBox comboBox_2 = new JComboBox();
		contentPane.add(comboBox_2, "cell 3 5,growx");
		
		JLabel lblGewnschteRaum = new JLabel("gew\u00FCnschte Raum#:");
		contentPane.add(lblGewnschteRaum, "cell 1 6");
		
		JComboBox comboBox_3 = new JComboBox();
		contentPane.add(comboBox_3, "cell 3 6,growx");
		
		JLabel lblPltze = new JLabel("Pl\u00E4tze:");
		contentPane.add(lblPltze, "cell 1 7");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		contentPane.add(comboBox_4, "cell 3 7,growx");
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3, "cell 4 7");
		
		JLabel lblPcarbeitspltze = new JLabel("PC-Arbeitspl\u00E4tze:");
		contentPane.add(lblPcarbeitspltze, "cell 1 8");
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		contentPane.add(comboBox_5, "cell 3 8,growx");
		
		JLabel lblBeamer = new JLabel("Beamer:");
		contentPane.add(lblBeamer, "cell 1 9");
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		contentPane.add(comboBox_6, "cell 3 9,growx");
		
		JLabel lblVisualizer = new JLabel("Visualizer:");
		contentPane.add(lblVisualizer, "cell 1 10");
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		contentPane.add(comboBox_7, "cell 3 10,growx");
		
		JLabel lblOverhead = new JLabel("Overhead:");
		contentPane.add(lblOverhead, "cell 1 11");
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		contentPane.add(comboBox_8, "cell 3 11,growx");
		
		JLabel lblTafeln = new JLabel("Tafeln:");
		contentPane.add(lblTafeln, "cell 1 12");
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		contentPane.add(comboBox_9, "cell 3 12,growx");
		
		JLabel lblWhiteboards = new JLabel("Whiteboards:");
		contentPane.add(lblWhiteboards, "cell 1 13");
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "29"}));
		contentPane.add(comboBox_10, "cell 3 13,growx");
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		contentPane.add(btnAbbrechen, "cell 1 14");
		
		JButton btnNewButton = new JButton("Raumanfrage senden");
		contentPane.add(btnNewButton, "cell 3 14");
		
		addWindowListener(new LVMaskeWindow());
	}

}
